package com.company.project.service.impl;

import com.company.project.dao.TbDeliveryBaseMapper;
import com.company.project.dao.TbInputWarehouseDataMapper;
import com.company.project.dao.TbProductBaseMapper;
import com.company.project.model.TbDeliveryBase;
import com.company.project.model.TbInputWarehouseData;
import com.company.project.model.TbProductBase;
import com.company.project.service.TbInputWarehouseDataService;
import com.company.project.core.AbstractService;
import com.company.project.utils.IntegerUtils;
import com.company.project.utils.MiscUtils;
import com.company.project.vo.ProductBaseVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Created by dewey on 2020/04/09.
 */
@Service
@Transactional
public class TbInputWarehouseDataServiceImpl extends AbstractService<TbInputWarehouseData> implements TbInputWarehouseDataService {
    static final String ENTITY_SUB_CODE = "ESC0000002";
    @Resource
    private TbInputWarehouseDataMapper tbInputWarehouseDataMapper;
    @Autowired
    private TbProductBaseMapper tbProductBaseMapper;
    @Autowired
    private TbDeliveryBaseMapper tbDeliveryBaseMapper;

    @Override
    public Map<String,List<TbInputWarehouseData>> download() {
        //收货数据
        List<TbInputWarehouseData> tbInputWarehouseDataList = tbInputWarehouseDataMapper.selectAll();
        //根据EAN码、批次号、库位分组汇总数量
        Map<String, Integer> groupByEanCodeBatchCodeWarehouseCodeToTotalQuantityMap = tbInputWarehouseDataList.stream()
                .collect(Collectors.groupingBy(item -> item.getEanCode() + item.getBatchCode() + item.getWarehouseCode(),
                        Collectors.summingInt(TbInputWarehouseData::getQuantity)));

        //根据EAN码分组对象
        LinkedHashMap<String, List<TbInputWarehouseData>> eanCodeToTakeDeliveryBeanMap =
                tbInputWarehouseDataList.stream().collect(Collectors.groupingBy(TbInputWarehouseData::getEanCode, LinkedHashMap::new, Collectors.toList()));

        //数据去重
        eanCodeToTakeDeliveryBeanMap.forEach((k,vlist)->{
            Map<String ,Integer> map = new HashMap<>();
            vlist.forEach(s->{
                String key = s.getEanCode() + s.getBatchCode() + s.getWarehouseCode();
                if (map.containsKey(key)){
                    vlist.remove(s);
                }else {
                    Integer integer = groupByEanCodeBatchCodeWarehouseCodeToTotalQuantityMap.get(key);
                    s.setQuantity(integer);
                    map.put(key, integer);
                }
            });
        });
//        //根据EAN码、批次号、库位分组对象
//        LinkedHashMap<String, List<TbInputWarehouseData>> eanCodeBatchCodeWarehouseCodeToTakeDeliveryBeanMap =
//                tbInputWarehouseDataList.stream().collect(Collectors.groupingBy(item -> item.getEanCode() + item.getBatchCode() + item.getWarehouseCode(),
//                        LinkedHashMap::new, Collectors.toList()));

        Condition condition = new Condition(TbInputWarehouseData.class);
        condition.createCriteria().andEqualTo("entity_sub_code", ENTITY_SUB_CODE);
        List<TbProductBase> tbProductBases = tbProductBaseMapper.selectByExample(condition);
        //product_base表的 materialNumber->boxEanCode
        Map<String, String> materialNumberToBoxEanCodeMap = tbProductBases.stream().collect(Collectors.toMap(TbProductBase::getMaterialNumber, TbProductBase::getBoxEanCode));
        //product_base表的 materialNumber->productEanCode
        Map<String, String> materialNumberToProductEanCodeMap = tbProductBases.stream().collect(Collectors.toMap(TbProductBase::getMaterialNumber, TbProductBase::getProductEanCode));
        // product_base表的外箱EAN码
        Map<String, TbProductBase> boxEanCodeMap = tbProductBases.stream().collect(Collectors.toMap(TbProductBase::getBoxEanCode, Function.identity(), (v1, v2) -> v1));

        Condition deliveryBaseCondition = new Condition(TbDeliveryBase.class);
        deliveryBaseCondition.createCriteria()
                .andEqualTo("entity_sub_code", ENTITY_SUB_CODE);
        //发货数据
        List<TbDeliveryBase> tbDeliveryBases = tbDeliveryBaseMapper.selectByExample(deliveryBaseCondition);
        //入库单号对应的商品数据(排序)
        LinkedHashMap<String, List<TbDeliveryBase>> inputWarehouseCodeMap =
                tbDeliveryBases.stream().collect(Collectors.groupingBy(TbDeliveryBase::getInputWarehouseCode, LinkedHashMap::new, Collectors.toList()));

        //最终的数据
        HashMap<String, List<TbInputWarehouseData>> hashMap = new HashMap<>();

        inputWarehouseCodeMap.forEach((k, deliveryBaseList) -> {
            List<TbInputWarehouseData> list = Lists.newArrayList();
            //一个入库单号下的 mertialNumber汇总数量
            Map<String, Integer> materialNumberToSumQuantityMap = deliveryBaseList.stream()
                    .collect(Collectors.groupingBy(TbDeliveryBase::getMaterialNumber, Collectors.summingInt(TbDeliveryBase::getQuantity)));

            //每个采购入库单号 下的商品编号
            materialNumberToSumQuantityMap.forEach((materialNumber, sumQuantity) -> {
                String eanCode = materialNumberToProductEanCodeMap.get(materialNumber);
                String boxEanCode = materialNumberToBoxEanCodeMap.get(materialNumber);
                //如果ProductEanCode没有找到
                if (StringUtils.isNotEmpty(eanCode)) {
                    List<TbInputWarehouseData> listDeliveryBean = eanCodeToTakeDeliveryBeanMap.get(eanCode);
                    for (TbInputWarehouseData bean : listDeliveryBean) {
                        Integer sub = IntegerUtils.sub(sumQuantity - bean.getQuantity());
                        //如果发货数量小于收货数量，则把收货数量减去发货数量后存储供下个入库单使用
                        if (sub < 0) {
                            TbInputWarehouseData data = new TbInputWarehouseData();
                            MiscUtils.copyBean(bean,data);
                            data.setQuantity(sumQuantity);
                            list.add(data);
                            bean.setQuantity(sub);
                        }
                        //如果发货数量等于收货数量，则把此收货数据移除
                        if (sub == 0) {
                            list.add(bean);
                            eanCodeToTakeDeliveryBeanMap.get(eanCode).remove(bean);
                        }
                        if (sub > 0) {
                            list.add(bean);
                            eanCodeToTakeDeliveryBeanMap.get(eanCode).remove(bean);
                        }
                    }
                }else if (StringUtils.isNotEmpty(boxEanCode)){
                    //如果 BoxEanCode 没有找到
                    List<TbInputWarehouseData> listDeliveryBean = eanCodeToTakeDeliveryBeanMap.get(boxEanCode);
                    listDeliveryBean.forEach(bean -> {
                        TbProductBase tbProductBase = boxEanCodeMap.get(boxEanCode);
                        Optional.ofNullable(tbProductBase).ifPresent(productBase -> {
                            Integer multiplyQuantity = IntegerUtils.multiply(bean.getQuantity(), productBase.getBoxEntryQuantity());
                            Integer sub = IntegerUtils.sub(sumQuantity - multiplyQuantity);
                            //如果发货数量小于收货数量，则把收货数量减小后存储供下个入库单使用
                            if (sub < 0) {
                                TbInputWarehouseData data = new TbInputWarehouseData();
                                BeanUtils.copyProperties(bean,data);
                                data.setQuantity(sumQuantity);
                                list.add(data);
                                bean.setQuantity(sub);
                            }
                            if (sub == 0) {
                                list.add(bean);
                                eanCodeToTakeDeliveryBeanMap.get(boxEanCode).remove(bean);
                            }
                            if (sub > 0) {
                                list.add(bean);
                                eanCodeToTakeDeliveryBeanMap.get(boxEanCode).remove(bean);
                            }
                        });
                    });
                }
            });
            hashMap.put(k, list);
        });
        return hashMap;
    }
}
