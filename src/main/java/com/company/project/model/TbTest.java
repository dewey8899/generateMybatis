package com.company.project.model;

import javax.persistence.*;

@Table(name = "tb_test")
public class TbTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    /**
     * 采购方公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 采购方公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "bill_order_code")
    private String billOrderCode;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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
     * 获取采购方公司ID
     *
     * @return company_id - 采购方公司ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置采购方公司ID
     *
     * @param companyId 采购方公司ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取采购方公司名称
     *
     * @return company_name - 采购方公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置采购方公司名称
     *
     * @param companyName 采购方公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return bill_order_code
     */
    public String getBillOrderCode() {
        return billOrderCode;
    }

    /**
     * @param billOrderCode
     */
    public void setBillOrderCode(String billOrderCode) {
        this.billOrderCode = billOrderCode;
    }
}