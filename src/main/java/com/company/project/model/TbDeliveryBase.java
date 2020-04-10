package com.company.project.model;

import javax.persistence.*;

@Table(name = "tb_delivery_base")
public class TbDeliveryBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "material_number")
    private String materialNumber;

    private Integer quantity;

    @Column(name = "input_warehouse_code")
    private String inputWarehouseCode;

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
     * @return material_number
     */
    public String getMaterialNumber() {
        return materialNumber;
    }

    /**
     * @param materialNumber
     */
    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
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
     * @return input_warehouse_code
     */
    public String getInputWarehouseCode() {
        return inputWarehouseCode;
    }

    /**
     * @param inputWarehouseCode
     */
    public void setInputWarehouseCode(String inputWarehouseCode) {
        this.inputWarehouseCode = inputWarehouseCode;
    }
}