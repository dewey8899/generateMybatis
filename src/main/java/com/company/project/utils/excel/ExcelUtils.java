package com.company.project.utils.excel;

import com.company.project.core.BusinessException;
import com.company.project.utils.BigDecimalUtils;
import com.company.project.utils.DateUtils;
import com.company.project.utils.IntegerUtils;
import com.company.project.utils.excel.v3x.MSExcelHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {

    static Logger logger = LogManager.getLogger(ExcelUtils.class);

    public static <T> List<T> read(String contentType, InputStream input, Class<T> model, int sheetIndex,
                                   boolean hasHeaderRow, int startRowIndex)
            throws InvalidFormatException, InstantiationException, IllegalAccessException, IOException {

        Workbook workbook = null;

        List<T> listRecord = new ArrayList<T>();

        try {

            ExcelUtils.checkContentType(contentType);

            workbook = ExcelUtils.getWorkbook(input, contentType);

            // 强制重新计算
            workbook.setForceFormulaRecalculation(true);

            Sheet sheet = workbook.getSheetAt(sheetIndex);

            if (sheet == null) {

                throw new IllegalAccessException("第一个工作表不存在。");
            }

            Iterator<Row> rowIterator = sheet.iterator();

            listRecord = read(rowIterator, model, sheetIndex, hasHeaderRow, startRowIndex);

        } catch (OfficeXmlFileException e) {

            throw new IllegalAccessException("文件格式错误，请用Office2007以上的版本打开该文件后另存成为Office2007以上的版本格式，扩展名（.xlsx）。");

        } finally {

            if (workbook != null) {

                workbook.close();
            }
        }

        return listRecord;

    }

    public static <T> List<T> read(String contentType, InputStream input, Class<T> model, int sheetIndex,
                                   boolean hasHeaderRow)
            throws InvalidFormatException, InstantiationException, IllegalAccessException, IOException {

        return ExcelUtils.read(contentType, input, model, sheetIndex, hasHeaderRow, 0);
    }

    private static <T> List<T> read(Iterator<Row> rowIterator, Class<T> model, int sheetIndex, boolean hasHeaderRow,
                                    int startRowIndex) throws IllegalAccessException {

        return ExcelUtils.read(rowIterator, model, sheetIndex, hasHeaderRow, startRowIndex, -1, null);
    }

    private static <T> List<T> read(Iterator<Row> rowIterator, Class<T> model, int sheetIndex, boolean hasHeaderRow,
                                    int startRowIndex, int endLineColumnIndex, String endLineDelimiter) throws IllegalAccessException {

        int rowNumber = 0;

        int columnIndex = 0;

        List<T> listRecord = new ArrayList<T>();

        Field[] fields = model.getDeclaredFields();

        Map<String, String> errors = new HashMap<String, String>();

        boolean finished = false;

        try {

            while (rowIterator.hasNext() && !finished) {

                T record = model.newInstance();

                Row row = rowIterator.next();

                rowNumber = row.getRowNum();

                if (rowNumber < startRowIndex) {

                    continue;
                }

                if (hasHeaderRow && rowNumber == 0) {

                    // 忽略表头
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext() && !finished) {

                    Cell cell = cellIterator.next();

                    columnIndex = cell.getColumnIndex();

                    for (int i = 0; i < fields.length; i++) {

                        Field field = fields[i];

                        field.setAccessible(true);

                        Column column = fields[i].getAnnotation(Column.class);

                        try {
                            if (column != null && columnIndex == column.index()) {

                                switch (column.type()) {

                                    case Cell.CELL_TYPE_NUMERIC: {

                                        if (field.getType().getName().equals(Integer.class.getName())) {

                                            try {

                                                field.set(record, Double.valueOf(cell.getNumericCellValue()).intValue());

                                            } catch (IllegalStateException | IllegalAccessException e) {
                                                logger.error(e.getMessage(), e);
                                                try {

                                                    field.set(record, new BigDecimal(cell.getStringCellValue()).intValue());
                                                } catch (NumberFormatException ex) {
                                                    logger.error(ex.getMessage(), ex);
                                                    throw new IllegalStateException();
                                                }
                                            }

                                        } else if (field.getType().getName().equals(Long.class.getName())) {

                                            try {

                                                field.set(record, Double.valueOf(cell.getNumericCellValue()).longValue());

                                            } catch (IllegalStateException | IllegalAccessException e) {
                                                logger.error(e.getMessage(), e);
                                                try {

                                                    field.set(record, new BigDecimal(cell.getStringCellValue()).longValue());
                                                } catch (NumberFormatException ex) {
                                                    logger.error(ex.getMessage(), ex);
                                                    throw new IllegalStateException();
                                                }
                                            }
                                        } else if (field.getType().getName().equals(Double.class.getName())) {

                                            field.set(record, cell.getNumericCellValue());

                                        } else if (field.getType().getName().equals(Date.class.getName())) {
                                            try {
                                                field.set(record, cell.getDateCellValue());
                                            } catch (IllegalStateException e) {

                                                Column com = field.getAnnotation(Column.class);
                                                String format = com.format();
                                                SimpleDateFormat sdf = new SimpleDateFormat(format);
                                                try {
                                                    field.set(record, sdf.parse(cell.getStringCellValue()));
                                                } catch (IllegalArgumentException | ParseException e1) {
                                                    throw new IllegalStateException();
                                                }
                                            }

                                        } else if (field.getType().getName().equals(String.class.getName())) {
                                            DecimalFormat df = new DecimalFormat(column.format());// 最多保留几位小数，就用几个#，最少位就用0来确定
                                            String str = df.format(new Double(cell.getNumericCellValue()));
                                            field.set(record, str);

                                        } else {

                                            field.set(record, new BigDecimal(cell.getNumericCellValue()));
                                        }
// 解决空cell中有脏数据无法读取的问
//                  try {
//
//                    if (field.getType().getName().equals(Integer.class.getName())) {
//
//                      field.set(record, Double.valueOf(cell.getNumericCellValue()).intValue());
//
//                    }else if (field.getType().getName().equals(Double.class.getName())) {
//
//                      field.set(record, cell.getNumericCellValue());
//
//                    } else if (field.getType().getName().equals(Date.class.getName())) {
//
//                      field.set(record, cell.getDateCellValue());
//
//                    } else if (field.getType().getName().equals(String.class.getName())) {
//                      DecimalFormat df = new DecimalFormat(column.format());// 最多保留几位小数，就用几个#，最少位就用0来确定
//                      String str = df.format(new Double(cell.getNumericCellValue()));
//                      field.set(record, str);
//
//                    } else {
//
//                      field.set(record, new BigDecimal(cell.getNumericCellValue()));
//                    }
//
//                  } catch (IllegalStateException | IllegalAccessException e) {
//
//                    cell.setCellType(Cell.CELL_TYPE_STRING);
//
//                    try{
//
//                      field.set(record, Double.valueOf(cell.getStringCellValue()).intValue());
//
//                    }catch(NumberFormatException ex){
//
//                      field.set(record, null);
//                    }
//                  }

                                        break;
                                    }
                                    case Cell.CELL_TYPE_STRING: {

                                        if(field.getType().getName().equals(String.class.getName())){

                                            try {

                                                field.set(record, cell.getStringCellValue());

                                            } catch (IllegalStateException | IllegalAccessException e) {

                                                int value1 = Double.valueOf(cell.getNumericCellValue()).intValue();
                                                Double value2 = Double.valueOf(cell.getNumericCellValue());
                                                if (value2 != null &&
                                                        value2.compareTo(Double.valueOf(value1)) == 0) {

                                                    field.set(record,
                                                            String.valueOf(value1));
                                                } else {

                                                    field.set(record,
                                                            String.valueOf(value2));
                                                }
                                            }
                                        }else if(field.getType().getName().equals(BigDecimal.class.getName())){
                                            String value = cell.getStringCellValue();
                                            value = StringUtils.isEmpty(value) ? "0" : value;
                                            field.set(record, BigDecimalUtils.getBigDecimal(value));
                                        }else if(field.getType().getName().equals(Integer.class.getName())){
                                            String value = cell.getStringCellValue();
                                            value = StringUtils.isEmpty(value) ? "0" : value;
                                            field.set(record, IntegerUtils.getInteger(value));
                                        }

                                        break;
                                    }
                                    case Cell.CELL_TYPE_FORMULA: {

                                        break;
                                    }
                                    case Cell.CELL_TYPE_BLANK: {

                                        break;
                                    }
                                    case Cell.CELL_TYPE_BOOLEAN: {

                                        field.set(record, cell.getBooleanCellValue());

                                        break;
                                    }
                                    case Cell.CELL_TYPE_ERROR: {

                                        break;
                                    }
                                }
                            }

                            // 读取到结束位置时，即为结束
                            if (endLineColumnIndex == columnIndex
                                    && field.get(record) != null
                                    && String.valueOf(field.get(record)).equals(endLineDelimiter)) {

                                finished = true;

                                break;
                            }
                        } catch (IllegalStateException | IllegalAccessException e) {

                            int rowIndex = hasHeaderRow ? rowNumber + 2 : rowNumber + 1;

                            errors.put(String.valueOf(rowIndex),
                                    String.format("第%s行的第%s列的数据类型错误。", rowIndex, columnIndex + 1));
                        }
                    }
                }

                if (!finished) {

                    listRecord.add(record);
                }

            }
        } catch (InstantiationException e) {

            throw new IllegalAccessException("文件格式错误，请重新下载导入模板。");

        }

        if (errors.size() > 0) {

            throw new BusinessException(errors, listRecord);
        }

        return listRecord;
    }

    /**
     * 读取福特格式
     *
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> readFord(String contentType, InputStream input, Class<T> model, int sheetIndex,
                                       int endLineColumnIndex, String endLineDelimiter)
            throws InvalidFormatException, InstantiationException, IllegalAccessException, IOException {

        Workbook workbook = null;

        List<T> listRecord = new ArrayList<T>();

        String stationEntityInternalCode = null;

        Date manufacturerOrderDate = null;

        try {

            ExcelUtils.checkContentType(contentType);

            workbook = ExcelUtils.getWorkbook(input, contentType);

            // 强制重新计算
            workbook.setForceFormulaRecalculation(true);

            Sheet sheet = workbook.getSheetAt(sheetIndex);

            try {

                // 站点实体内部代码
                stationEntityInternalCode = sheet.getRow(2).getCell(3).getStringCellValue();

            } catch (IllegalStateException e) {

                // 站点实体内部代码
                stationEntityInternalCode = String.valueOf(Double.valueOf(sheet.getRow(2).getCell(3).getNumericCellValue()).intValue());
            }
            try {

                // 原厂订单日期
                manufacturerOrderDate = sheet.getRow(3).getCell(5).getDateCellValue();

            } catch (IllegalStateException e) {

                throw new IllegalAccessException("原厂日期格式错误。");
            }

            Iterator<Row> rowIterator = sheet.iterator();

            listRecord = ExcelUtils.read(rowIterator, model, sheetIndex, false, 8, endLineColumnIndex,
                    endLineDelimiter);

            int iSize = listRecord.size();

            for (int i = 0; i < iSize; i++) {

                T record = listRecord.get(i);

                // 站点实体内部代码
                Field field = record.getClass().getDeclaredField("stationEntityInternalCode");
                field.setAccessible(true);
                field.set(record, stationEntityInternalCode);

                // 原厂订单日期
                field = record.getClass().getDeclaredField("manufacturerOrderDate");
                field.setAccessible(true);
                field.set(record, manufacturerOrderDate);

                // 原厂订单号
                field = record.getClass().getDeclaredField("manufacturerOrderCode");
                field.setAccessible(true);
                field.set(record, "2");
            }

        } catch (OfficeXmlFileException e) {

            logger.warn(e, e);

            throw new IllegalAccessException("文件格式错误，请用Office2007以上的版本打开该文件后另存成为Office2007以上的版本格式，扩展名（.xlsx）。");

        } catch (SecurityException | NoSuchFieldException | NullPointerException e) {

            logger.error(e, e);

            throw new BusinessException("解析福特格式的Excel时，发生错误，请联系软件供应商。");

        } catch (BusinessException e) {

            // 发生异常后数据部返回，以业务异常的形式抛出后，
            // 对站点内部代码，原厂订单日期，原厂订单号进行赋值

            listRecord = (List<T>) e.getExtra();

            int iSize = listRecord.size();

            for (int i = 0; i < iSize; i++) {

                T record = listRecord.get(i);

                try {

                    // 站点实体内部代码
                    Field field = record.getClass().getDeclaredField("stationEntityInternalCode");
                    field.setAccessible(true);
                    field.set(record, stationEntityInternalCode);

                    // 原厂订单日期
                    field = record.getClass().getDeclaredField("manufacturerOrderDate");
                    field.setAccessible(true);
                    field.set(record, manufacturerOrderDate);

                    // 原厂订单号
                    field = record.getClass().getDeclaredField("manufacturerOrderCode");
                    field.setAccessible(true);
                    field.set(record, "2");

                } catch (NoSuchFieldException e1) {

                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                } catch (SecurityException e1) {

                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            throw e;

        } finally {

            if (workbook != null) {

                workbook.close();
            }
        }

        return listRecord;
    }

    /**
     * 读取长安福特格式
     *
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> readFordCA(String contentType, InputStream input, Class<T> model, int sheetIndex,
                                         int endLineColumnIndex, String endLineDelimiter)
            throws InvalidFormatException, InstantiationException, IllegalAccessException, IOException {

        Workbook workbook = null;

        List<T> listRecord = new ArrayList<T>();

        String stationEntityInternalCode = null;

        Date manufacturerOrderDate = null;

        try {

            ExcelUtils.checkContentType(contentType);

            workbook = ExcelUtils.getWorkbook(input, contentType);

            // 强制重新计算
            workbook.setForceFormulaRecalculation(true);

            Sheet sheet = workbook.getSheetAt(sheetIndex);

            try {

                // 站点实体内部代码
                stationEntityInternalCode = sheet.getRow(1).getCell(3).getStringCellValue();

            } catch (IllegalStateException e) {

                // 站点实体内部代码
                stationEntityInternalCode = String.valueOf(Double.valueOf(sheet.getRow(1).getCell(3).getNumericCellValue()).intValue());
            }
            try {

                // 原厂订单日期
                manufacturerOrderDate = sheet.getRow(2).getCell(6).getDateCellValue();

            } catch (IllegalStateException e) {

                throw new IllegalAccessException("原厂日期格式错误。");
            }

            Iterator<Row> rowIterator = sheet.iterator();

            listRecord = ExcelUtils.read(rowIterator, model, sheetIndex, false, 7, endLineColumnIndex,
                    endLineDelimiter);

            int iSize = listRecord.size();

            for (int i = 0; i < iSize; i++) {

                T record = listRecord.get(i);

                // 站点实体内部代码
                Field field = record.getClass().getDeclaredField("stationEntityInternalCode");
                field.setAccessible(true);
                field.set(record, stationEntityInternalCode);

                // 原厂订单日期
                field = record.getClass().getDeclaredField("manufacturerOrderDate");
                field.setAccessible(true);
                field.set(record, manufacturerOrderDate);

                // 原厂订单号
                field = record.getClass().getDeclaredField("manufacturerOrderCode");
                field.setAccessible(true);
                field.set(record, "4");
            }

        } catch (OfficeXmlFileException e) {

            throw new IllegalAccessException("文件格式错误，请用Office2007以上的版本打开该文件后另存成为Office2007以上的版本格式，扩展名（.xlsx）。");

        } catch (SecurityException | NoSuchFieldException | NullPointerException e) {

            throw new BusinessException("解析福特格式的Excel时，发生错误，请联系软件供应商。");

        } catch (BusinessException e) {

            // 发生异常后数据部返回，以业务异常的形式抛出后，
            // 对站点内部代码，原厂订单日期，原厂订单号进行赋值

            listRecord = (List<T>) e.getExtra();

            int iSize = listRecord.size();

            for (int i = 0; i < iSize; i++) {

                T record = listRecord.get(i);

                try {
                    // 站点实体内部代码
                    Field field;
                    field = record.getClass().getDeclaredField("stationEntityInternalCode");
                    field.setAccessible(true);
                    field.set(record, stationEntityInternalCode);

                    // 原厂订单日期
                    field = record.getClass().getDeclaredField("manufacturerOrderDate");
                    field.setAccessible(true);
                    field.set(record, manufacturerOrderDate);

                    // 原厂订单号
                    field = record.getClass().getDeclaredField("manufacturerOrderCode");
                    field.setAccessible(true);
                    field.set(record, "4");

                } catch (NoSuchFieldException e1) {

                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                } catch (SecurityException e1) {

                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            throw e;

        } finally {

            if (workbook != null) {

                workbook.close();
            }
        }

        return listRecord;
    }

    /**
     * 读取大师格式
     *
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> readMaster(String contentType, InputStream input, Class<T> model, int sheetIndex,
                                         int endLineColumnIndex, String endLineDelimiter)
            throws InvalidFormatException, InstantiationException, IllegalAccessException, IOException {

        Workbook workbook = null;

        List<T> listRecord = new ArrayList<T>();

        String stationEntityInternalCode = null;

        Date manufacturerOrderDate = null;

        try {

            ExcelUtils.checkContentType(contentType);

            workbook = ExcelUtils.getWorkbook(input, contentType);

            // 强制重新计算
            workbook.setForceFormulaRecalculation(true);

            Sheet sheet = workbook.getSheetAt(sheetIndex);

            try {

                // 站点实体内部代码
                stationEntityInternalCode = sheet.getRow(2).getCell(3).getStringCellValue();

            } catch (IllegalStateException e) {

                // 站点实体内部代码
                stationEntityInternalCode = String.valueOf(Double.valueOf(sheet.getRow(2).getCell(3).getNumericCellValue()).intValue());
            }
            try {

                // 原厂订单日期
                manufacturerOrderDate = sheet.getRow(3).getCell(5).getDateCellValue();

            } catch (IllegalStateException e) {

                throw new IllegalAccessException("原厂日期格式错误。");
            }

            Iterator<Row> rowIterator = sheet.iterator();

            listRecord = ExcelUtils.read(rowIterator, model, sheetIndex, false, 8, endLineColumnIndex,
                    endLineDelimiter);

            int iSize = listRecord.size();

            for (int i = 0; i < iSize; i++) {

                T record = listRecord.get(i);

                // 站点实体内部代码
                Field field = record.getClass().getDeclaredField("stationEntityInternalCode");
                field.setAccessible(true);
                field.set(record, stationEntityInternalCode);

                // 原厂订单日期
                field = record.getClass().getDeclaredField("manufacturerOrderDate");
                field.setAccessible(true);
                field.set(record, manufacturerOrderDate);

                // 原厂订单号
                field = record.getClass().getDeclaredField("manufacturerOrderCode");
                field.setAccessible(true);
                field.set(record, "3");
            }

        } catch (OfficeXmlFileException e) {

            logger.warn(e, e);

            throw new IllegalAccessException("文件格式错误，请用Office2007以上的版本打开该文件后另存成为Office2007以上的版本格式，扩展名（.xlsx）。");

        } catch (SecurityException | NoSuchFieldException | NullPointerException e) {

            logger.error(e, e);

            throw new BusinessException("解析福特格式的Excel时，发生错误，请联系软件供应商。");

        } catch (BusinessException e) {

            // 发生异常后数据部返回，以业务异常的形式抛出后，
            // 对站点内部代码，原厂订单日期，原厂订单号进行赋值

            listRecord = (List<T>) e.getExtra();

            int iSize = listRecord.size();

            for (int i = 0; i < iSize; i++) {

                T record = listRecord.get(i);

                try {

                    // 站点实体内部代码
                    Field field = record.getClass().getDeclaredField("stationEntityInternalCode");
                    field.setAccessible(true);
                    field.set(record, stationEntityInternalCode);

                    // 原厂订单日期
                    field = record.getClass().getDeclaredField("manufacturerOrderDate");
                    field.setAccessible(true);
                    field.set(record, manufacturerOrderDate);

                    // 原厂订单号
                    field = record.getClass().getDeclaredField("manufacturerOrderCode");
                    field.setAccessible(true);
                    field.set(record, "3");

                } catch (NoSuchFieldException e1) {

                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                } catch (SecurityException e1) {

                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            throw e;


        } finally {

            if (workbook != null) {

                workbook.close();
            }
        }

        return listRecord;
    }

    private static void checkContentType(String contentType) throws IllegalAccessException {

        if (!ContentType.contains(contentType)) {
            throw new IllegalAccessException("文件格式错误，请重新下载导入模板。");
        }
    }

    private static Workbook getWorkbook(InputStream input, String contentType) throws IOException {

        Workbook workbook = null;

        if (ContentType.office2003.getName().equals(contentType)
                || ContentType.office2000.getName().equals(contentType)) {

            workbook = new HSSFWorkbook(input);

        } else if (ContentType.office2007.getName().equals(contentType)) {

            workbook = new XSSFWorkbook(input);

        }

        return workbook;
    }

    public static SXSSFWorkbook write2007(List<?> dataSet, Class<?> clazz) {

        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

        workbook.setCompressTempFiles(true);

        SXSSFSheet sheet = workbook.createSheet("数据");

        sheet.setDefaultColumnWidth(15);

        try {

            SXSSFRow row = sheet.createRow(0);

            DataFormat[] formats = new DataFormat[clazz.getDeclaredFields().length];

            CellStyle[] styles = new CellStyle[clazz.getDeclaredFields().length];

            for (int i = 0; i < clazz.getDeclaredFields().length; i++) {

                Field field = clazz.getDeclaredFields()[i];

                Column column = field.getAnnotation(Column.class);

                if (column != null) {

                    SXSSFCell cell = row.createCell(column.index());

                    cell.setCellValue(column.title());

                    if (!StringUtils.isBlank(column.format())) {

                        formats[i] = workbook.createDataFormat();

                        styles[i] = workbook.createCellStyle();

                        styles[i].setDataFormat(formats[i].getFormat(column.format()));

                    } else {

                        formats[i] = null;

                        styles[i] = null;
                    }

                    sheet.setColumnWidth(column.index(), column.width());
                }
            }

            for (int i = 0; i < dataSet.size(); i++) {

                row = sheet.createRow(i + 1);

                Object bean = dataSet.get(i);

                for (int j = 0; j < clazz.getDeclaredFields().length; j++) {

                    Field field = clazz.getDeclaredFields()[j];

                    field.setAccessible(true);

                    Column column = field.getAnnotation(Column.class);

                    if (column != null) {

                        SXSSFCell cell = row.createCell(column.index(), column.type());

                        if (!StringUtils.isBlank(column.format())) {
                            cell.setCellStyle(styles[j]);
                        }

                        Object value = field.get(bean);

                        if (value != null) {
                            switch (column.type()) {

                                case Cell.CELL_TYPE_STRING: {

                                    cell.setCellValue(String.valueOf(field.get(bean)));

                                    break;
                                }

                                case Cell.CELL_TYPE_NUMERIC: {

                                    if (field.getType().getName().equals(Date.class.getName())) {

                                        cell.setCellValue((Date) value);

                                    } else {

                                        cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                                    }

                                    break;
                                }

                                case Cell.CELL_TYPE_FORMULA: {

                                    cell.setCellValue(field.toString());

                                    break;
                                }

                                case Cell.CELL_TYPE_BLANK: {

                                    cell.setCellValue(field.toString());

                                    break;
                                }

                                case Cell.CELL_TYPE_BOOLEAN: {

                                    cell.setCellValue(field.getBoolean(bean));

                                    break;
                                }

                                default: {

                                    break;
                                }
                            }
                        } else {
                            if (field.getType().getName().equals(Date.class.getName())) {
                                cell.setCellValue("");
                            }
                        }
                    }
                }
            }

            return workbook;

        } catch (IllegalAccessException e) {

            logger.error(e, e);

            throw new BusinessException("文件生成失败。");
        }
    }

    public static void write2007(List<?> dataSet, Class<?> clazz, File file) {

        SXSSFWorkbook workbook = ExcelUtils.write2007(dataSet, clazz);

        try {

            workbook.write(new FileOutputStream(file));

        } catch (IOException e) {

            logger.error(e, e);

            throw new BusinessException("文件生成失败。");

        } finally {

            try {

                if (workbook != null) {

                    workbook.close();
                }

            } catch (IOException e) {

                throw new BusinessException("该文件无法关闭。");
            }
        }
    }

    public static void write2007(List<?> dataSet, Class<?> clazz, String fileName, HttpServletResponse response) throws IllegalAccessException {
        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            fileName += DateUtils.formatDateByWebFormat(new Date()) + ".xlsx";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            MSExcelHelper.write(outputStream, "数据", dataSet, MSExcelHelper.MSExcelFileType.excel2007, 1000);
            outputStream.flush();
        } catch (IOException e) {
            logger.error("导出Excel失败。");
            logger.error(e, e);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    public static void write2007ForTwoSheet(List<?> dataSet1, Class<?> clazz1, List<?> dataSet2, Class<?> clazz2,
                                            String fileName, HttpServletResponse response) throws InvocationTargetException, InstantiationException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        workbook.setCompressTempFiles(true);

        SXSSFSheet sheet1 = workbook.createSheet("对账单");

        SXSSFSheet sheet2 = workbook.createSheet("商品明细");

        sheet1.setDefaultColumnWidth(15);
        sheet2.setDefaultColumnWidth(15);

        OutputStream out = null;

        try {

            SXSSFRow row1 = sheet1.createRow(0);
            SXSSFRow row2 = sheet2.createRow(0);

            DataFormat[] formats1 = new DataFormat[clazz1.getDeclaredFields().length];
            DataFormat[] formats2 = new DataFormat[clazz2.getDeclaredFields().length];

            CellStyle[] styles1 = new CellStyle[clazz1.getDeclaredFields().length];
            CellStyle[] styles2 = new CellStyle[clazz2.getDeclaredFields().length];

            for (int i = 0; i < clazz1.getDeclaredFields().length; i++) {

                Field field = clazz1.getDeclaredFields()[i];

                Column column = field.getAnnotation(Column.class);

                if (column != null) {

                    SXSSFCell cell = row1.createCell(column.index());

                    cell.setCellValue(column.title());

                    if (!StringUtils.isBlank(column.format())) {

                        formats1[i] = workbook.createDataFormat();

                        styles1[i] = workbook.createCellStyle();

                        styles1[i].setDataFormat(formats1[i].getFormat(column.format()));

                    } else {

                        formats1[i] = null;

                        styles1[i] = null;
                    }

                    sheet1.setColumnWidth(column.index(), column.width());
                }
            }

            for (int i = 0; i < clazz2.getDeclaredFields().length; i++) {

                Field field = clazz2.getDeclaredFields()[i];

                Column column = field.getAnnotation(Column.class);

                if (column != null) {

                    SXSSFCell cell = row2.createCell(column.index());

                    cell.setCellValue(column.title());

                    if (!StringUtils.isBlank(column.format())) {

                        formats2[i] = workbook.createDataFormat();

                        styles2[i] = workbook.createCellStyle();

                        styles2[i].setDataFormat(formats2[i].getFormat(column.format()));

                    } else {

                        formats2[i] = null;

                        styles2[i] = null;
                    }

                    sheet2.setColumnWidth(column.index(), column.width());
                }
            }

            for (int i = 0; i < dataSet1.size(); i++) {

                row1 = sheet1.createRow(i + 1);

                Object bean = dataSet1.get(i);

                for (int j = 0; j < clazz1.getDeclaredFields().length; j++) {

                    Field field = clazz1.getDeclaredFields()[j];

                    field.setAccessible(true);

                    Column column = field.getAnnotation(Column.class);

                    if (column != null) {

                        SXSSFCell cell = row1.createCell(column.index(), column.type());

                        if (!StringUtils.isBlank(column.format())) {
                            cell.setCellStyle(styles1[j]);
                        }

                        Object value = field.get(bean);

                        if (value != null) {
                            switch (column.type()) {

                                case Cell.CELL_TYPE_STRING: {

                                    cell.setCellValue(String.valueOf(field.get(bean)));

                                    break;
                                }

                                case Cell.CELL_TYPE_NUMERIC: {

                                    if (field.getType().getName().equals(Date.class.getName())) {

                                        cell.setCellValue(sdf.format(value));

                                    } else {

                                        cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                                    }

                                    break;
                                }

                                case Cell.CELL_TYPE_FORMULA: {

                                    cell.setCellValue(field.toString());

                                    break;
                                }

                                case Cell.CELL_TYPE_BLANK: {

                                    cell.setCellValue(field.toString());

                                    break;
                                }

                                case Cell.CELL_TYPE_BOOLEAN: {

                                    cell.setCellValue(field.getBoolean(bean));

                                    break;
                                }

                                default: {

                                    break;
                                }
                            }
                        } else {
                            if (field.getType().getName().equals(Date.class.getName())) {
                                cell.setCellValue("");
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < dataSet2.size(); i++) {

                row2 = sheet2.createRow(i + 1);

                Object bean = dataSet2.get(i);

                for (int j = 0; j < clazz2.getDeclaredFields().length; j++) {

                    Field field = clazz2.getDeclaredFields()[j];

                    field.setAccessible(true);

                    Column column = field.getAnnotation(Column.class);

                    if (column != null) {

                        SXSSFCell cell = row2.createCell(column.index(), column.type());

                        if (!StringUtils.isBlank(column.format())) {
                            cell.setCellStyle(styles2[j]);
                        }

                        Object value = field.get(bean);

                        if (value != null) {
                            switch (column.type()) {

                                case Cell.CELL_TYPE_STRING: {

                                    cell.setCellValue(String.valueOf(field.get(bean)));

                                    break;
                                }

                                case Cell.CELL_TYPE_NUMERIC: {

                                    if (field.getType().getName().equals(Date.class.getName())) {

                                        cell.setCellValue(sdf.format(value));

                                    } else {

                                        cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                                    }

                                    break;
                                }

                                case Cell.CELL_TYPE_FORMULA: {

                                    cell.setCellValue(field.toString());

                                    break;
                                }

                                case Cell.CELL_TYPE_BLANK: {

                                    cell.setCellValue(field.toString());

                                    break;
                                }

                                case Cell.CELL_TYPE_BOOLEAN: {

                                    cell.setCellValue(field.getBoolean(bean));

                                    break;
                                }

                                default: {

                                    break;
                                }
                            }
                        }
                    }
                }
            }

            out = response.getOutputStream();

            fileName += DateUtils.formatDateByWebFormat(new Date()) + ".xlsx";

            fileName = URLEncoder.encode(fileName, "UTF-8");

            fileName = StringUtils.replace(fileName, "+", "%20");

            if (fileName.length() > 150) {
                fileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                fileName = StringUtils.replace(fileName, " ", "%20");
            }

            response.reset();

            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            workbook.write(out);

            out.flush();

            out.close();

        } catch (IllegalAccessException e) {
            e.printStackTrace();

            throw new BusinessException("文件下载失败。");
        } catch (IOException e) {

            throw new BusinessException("文件下载失败。");

        } finally {

            try {

                if (workbook != null) {

                    workbook.close();
                }

            } catch (IOException e) {

                throw new BusinessException("该文件无法关闭。");
            }
        }
    }

    public static enum ContentType {

        office2007("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"), office2003(
                "application/vnd.ms-excel",
                "application/vnd.ms-excel"), office2000("application/octet-stream", "application/octet-stream");

        private String name;

        private String index;

        private ContentType(String index, String name) {
            this.name = name;
            this.index = index;
        }

        public static ContentType getElement(String index) {

            for (ContentType e : ContentType.values()) {
                if (e.getIndex().equals(index)) {
                    return e;
                }
            }
            return null;
        }

        public static String getElementName(String index) {

            ContentType e = ContentType.getElement(index);

            return e == null ? null : e.toString();
        }

        public static boolean contains(String index) {

            return ContentType.getName(index) != null;
        }

        public static String getName(String index) {

            for (ContentType e : ContentType.values()) {
                if (e.getIndex().equals(index)) {
                    return e.name;
                }
            }
            return null;
        }

        public String getIndex() {
            return this.index;
        }

        public String getName() {
            return this.name;
        }

    }
}
