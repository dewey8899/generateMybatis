package com.company.project.vo;

import com.company.project.utils.excel.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Created by deweydu
 * Date on 2020-4-9 16:40:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductBaseVo {
    @Column(index = 0, type = Cell.CELL_TYPE_STRING, title = "EAN码", width = 30 * 50)
    private String eanCode;

    @Column(index = 1, type = Cell.CELL_TYPE_NUMERIC, title = "批次号", width = 30 * 150)
    private String batchCode;

    @Column(index = 2, type = Cell.CELL_TYPE_NUMERIC, title = "数量", width = 30 * 50)
    private Integer quantity;

    @Column(index = 4, type = Cell.CELL_TYPE_NUMERIC, title = "仓库库位", width = 30 * 150)
    private String warehouseCode;
}
