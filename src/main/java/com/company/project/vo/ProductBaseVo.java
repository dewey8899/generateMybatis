package com.company.project.vo;

import lombok.Data;

/**
 * Created by deweydu
 * Date on 2020-4-9 16:40:57
 */
@Data
public class ProductBaseVo {
    private String productEanCode;

    /**
     * 外箱EAN码
     */
    private String boxEanCode;

    /**
     * 箱入数
     */
    private Integer boxEntryQuantity;
}
