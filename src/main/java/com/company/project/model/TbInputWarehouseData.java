package com.company.project.model;

import javax.persistence.*;

@Table(name = "tb_input_warehouse_data")
public class TbInputWarehouseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ean_code")
    private String eanCode;

    @Column(name = "batch_code")
    private String batchCode;

    private Integer quantity;

    /**
     * 库位
     */
    @Column(name = "warehouse_code")
    private String warehouseCode;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return ean_code
     */
    public String getEanCode() {
        return eanCode;
    }

    /**
     * @param eanCode
     */
    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    /**
     * @return batch_code
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * @param batchCode
     */
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    /**
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取库位
     *
     * @return warehouse_code - 库位
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * 设置库位
     *
     * @param warehouseCode 库位
     */
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
}