package com.company.project.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_report_oem_product_difference")
public class TbReportOemProductDifference {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 参考tb_customer_entity_profile（品牌商用）
     */
    @Column(name = "relation_entity_code")
    private String relationEntityCode;

    /**
     * 参考tb_customer_entity_role（品牌商用）
     */
    @Column(name = "relation_entity_sub_code")
    private String relationEntitySubCode;

    /**
     * 总代参考tb_customer_entity_profile
     */
    @Column(name = "general_agency_entity_code")
    private String generalAgencyEntityCode;

    /**
     * 总代参考tb_customer_entity_role
     */
    @Column(name = "general_agency_entity_sub_code")
    private String generalAgencyEntitySubCode;

    /**
     * 总代编号
     */
    @Column(name = "general_agency_entity_internal_code")
    private String generalAgencyEntityInternalCode;

    /**
     * 总代名称
     */
    @Column(name = "general_agency_entity_company_short_name")
    private String generalAgencyEntityCompanyShortName;

    /**
     * 服务商参考tb_customer_entity_profile
     */
    @Column(name = "service_business_entity_code")
    private String serviceBusinessEntityCode;

    /**
     * 服务商参考tb_customer_entity_role
     */
    @Column(name = "service_business_entity_sub_code")
    private String serviceBusinessEntitySubCode;

    /**
     * 服务商编号
     */
    @Column(name = "service_business_entity_internal_code")
    private String serviceBusinessEntityInternalCode;

    /**
     * 服务商名称
     */
    @Column(name = "service_business_entity_company_short_name")
    private String serviceBusinessEntityCompanyShortName;

    /**
     * 厂商品牌（中文）
     */
    @Column(name = "manufacturer_brand_name_zh")
    private String manufacturerBrandNameZh;

    /**
     * 厂商品牌（英文）
     */
    @Column(name = "manufacturer_brand_name_en")
    private String manufacturerBrandNameEn;

    /**
     * 商品编号
     */
    @Column(name = "material_number")
    private String materialNumber;

    /**
     * 商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 有效数量（配送任务订单时为订购数量，
补货订单或者站点送货订单时=订购数量-关闭数量）
     */
    private Integer quantity;

    /**
     * 服务商-商品单价（未税）
     */
    @Column(name = "service_business_tax_excluded_price")
    private BigDecimal serviceBusinessTaxExcludedPrice;

    /**
     * 服务商价格税率
     */
    @Column(name = "service_business_tax_rate")
    private BigDecimal serviceBusinessTaxRate;

    /**
     * 总代商品单价（未税）
     */
    @Column(name = "general_agency_tax_excluded_price")
    private BigDecimal generalAgencyTaxExcludedPrice;

    /**
     * 总代价格税率
     */
    @Column(name = "general_agency_tax_rate")
    private BigDecimal generalAgencyTaxRate;

    /**
     * 订单类型（配送任务单/价格调整单-总代单、补货订单/站点送货订单-服务商单）
     */
    @Column(name = "order_type")
    private String orderType;

    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 原始单号
     */
    @Column(name = "source_order_code")
    private String sourceOrderCode;

    /**
     * 创建类型（1.开票、2.审核、3.下单）
     */
    @Column(name = "create_date_type")
    private Integer createDateType;

    /**
     * 开票日期/审核时间/下单时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 订购类型【商品，赠品】
     */
    @Column(name = "purchase_type")
    private String purchaseType;

    /**
     * 配送任务订单补货账户规则类型（1：20190831之前的规则，2：20190901之后的规则，9：非配送任务订单不适用）
     */
    @Column(name = "task_order_replenishment_account_rule_type")
    private Integer taskOrderReplenishmentAccountRuleType;

    /**
     * 创建时间
     */
    @Column(name = "sys_created_time")
    private Date sysCreatedTime;

    /**
     * 创建操作者的姓名
     */
    @Column(name = "sys_created_operator_name")
    private String sysCreatedOperatorName;

    /**
     * 更新时间
     */
    @Column(name = "sys_updated_time")
    private Date sysUpdatedTime;

    /**
     * 更新操作者的姓名
     */
    @Column(name = "sys_updated_operator_name")
    private String sysUpdatedOperatorName;

    /**
     * 记录删除状态
     */
    @Column(name = "sys_del_flg")
    private String sysDelFlg;

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取参考tb_customer_entity_profile（品牌商用）
     *
     * @return relation_entity_code - 参考tb_customer_entity_profile（品牌商用）
     */
    public String getRelationEntityCode() {
        return relationEntityCode;
    }

    /**
     * 设置参考tb_customer_entity_profile（品牌商用）
     *
     * @param relationEntityCode 参考tb_customer_entity_profile（品牌商用）
     */
    public void setRelationEntityCode(String relationEntityCode) {
        this.relationEntityCode = relationEntityCode;
    }

    /**
     * 获取参考tb_customer_entity_role（品牌商用）
     *
     * @return relation_entity_sub_code - 参考tb_customer_entity_role（品牌商用）
     */
    public String getRelationEntitySubCode() {
        return relationEntitySubCode;
    }

    /**
     * 设置参考tb_customer_entity_role（品牌商用）
     *
     * @param relationEntitySubCode 参考tb_customer_entity_role（品牌商用）
     */
    public void setRelationEntitySubCode(String relationEntitySubCode) {
        this.relationEntitySubCode = relationEntitySubCode;
    }

    /**
     * 获取总代参考tb_customer_entity_profile
     *
     * @return general_agency_entity_code - 总代参考tb_customer_entity_profile
     */
    public String getGeneralAgencyEntityCode() {
        return generalAgencyEntityCode;
    }

    /**
     * 设置总代参考tb_customer_entity_profile
     *
     * @param generalAgencyEntityCode 总代参考tb_customer_entity_profile
     */
    public void setGeneralAgencyEntityCode(String generalAgencyEntityCode) {
        this.generalAgencyEntityCode = generalAgencyEntityCode;
    }

    /**
     * 获取总代参考tb_customer_entity_role
     *
     * @return general_agency_entity_sub_code - 总代参考tb_customer_entity_role
     */
    public String getGeneralAgencyEntitySubCode() {
        return generalAgencyEntitySubCode;
    }

    /**
     * 设置总代参考tb_customer_entity_role
     *
     * @param generalAgencyEntitySubCode 总代参考tb_customer_entity_role
     */
    public void setGeneralAgencyEntitySubCode(String generalAgencyEntitySubCode) {
        this.generalAgencyEntitySubCode = generalAgencyEntitySubCode;
    }

    /**
     * 获取总代编号
     *
     * @return general_agency_entity_internal_code - 总代编号
     */
    public String getGeneralAgencyEntityInternalCode() {
        return generalAgencyEntityInternalCode;
    }

    /**
     * 设置总代编号
     *
     * @param generalAgencyEntityInternalCode 总代编号
     */
    public void setGeneralAgencyEntityInternalCode(String generalAgencyEntityInternalCode) {
        this.generalAgencyEntityInternalCode = generalAgencyEntityInternalCode;
    }

    /**
     * 获取总代名称
     *
     * @return general_agency_entity_company_short_name - 总代名称
     */
    public String getGeneralAgencyEntityCompanyShortName() {
        return generalAgencyEntityCompanyShortName;
    }

    /**
     * 设置总代名称
     *
     * @param generalAgencyEntityCompanyShortName 总代名称
     */
    public void setGeneralAgencyEntityCompanyShortName(String generalAgencyEntityCompanyShortName) {
        this.generalAgencyEntityCompanyShortName = generalAgencyEntityCompanyShortName;
    }

    /**
     * 获取服务商参考tb_customer_entity_profile
     *
     * @return service_business_entity_code - 服务商参考tb_customer_entity_profile
     */
    public String getServiceBusinessEntityCode() {
        return serviceBusinessEntityCode;
    }

    /**
     * 设置服务商参考tb_customer_entity_profile
     *
     * @param serviceBusinessEntityCode 服务商参考tb_customer_entity_profile
     */
    public void setServiceBusinessEntityCode(String serviceBusinessEntityCode) {
        this.serviceBusinessEntityCode = serviceBusinessEntityCode;
    }

    /**
     * 获取服务商参考tb_customer_entity_role
     *
     * @return service_business_entity_sub_code - 服务商参考tb_customer_entity_role
     */
    public String getServiceBusinessEntitySubCode() {
        return serviceBusinessEntitySubCode;
    }

    /**
     * 设置服务商参考tb_customer_entity_role
     *
     * @param serviceBusinessEntitySubCode 服务商参考tb_customer_entity_role
     */
    public void setServiceBusinessEntitySubCode(String serviceBusinessEntitySubCode) {
        this.serviceBusinessEntitySubCode = serviceBusinessEntitySubCode;
    }

    /**
     * 获取服务商编号
     *
     * @return service_business_entity_internal_code - 服务商编号
     */
    public String getServiceBusinessEntityInternalCode() {
        return serviceBusinessEntityInternalCode;
    }

    /**
     * 设置服务商编号
     *
     * @param serviceBusinessEntityInternalCode 服务商编号
     */
    public void setServiceBusinessEntityInternalCode(String serviceBusinessEntityInternalCode) {
        this.serviceBusinessEntityInternalCode = serviceBusinessEntityInternalCode;
    }

    /**
     * 获取服务商名称
     *
     * @return service_business_entity_company_short_name - 服务商名称
     */
    public String getServiceBusinessEntityCompanyShortName() {
        return serviceBusinessEntityCompanyShortName;
    }

    /**
     * 设置服务商名称
     *
     * @param serviceBusinessEntityCompanyShortName 服务商名称
     */
    public void setServiceBusinessEntityCompanyShortName(String serviceBusinessEntityCompanyShortName) {
        this.serviceBusinessEntityCompanyShortName = serviceBusinessEntityCompanyShortName;
    }

    /**
     * 获取厂商品牌（中文）
     *
     * @return manufacturer_brand_name_zh - 厂商品牌（中文）
     */
    public String getManufacturerBrandNameZh() {
        return manufacturerBrandNameZh;
    }

    /**
     * 设置厂商品牌（中文）
     *
     * @param manufacturerBrandNameZh 厂商品牌（中文）
     */
    public void setManufacturerBrandNameZh(String manufacturerBrandNameZh) {
        this.manufacturerBrandNameZh = manufacturerBrandNameZh;
    }

    /**
     * 获取厂商品牌（英文）
     *
     * @return manufacturer_brand_name_en - 厂商品牌（英文）
     */
    public String getManufacturerBrandNameEn() {
        return manufacturerBrandNameEn;
    }

    /**
     * 设置厂商品牌（英文）
     *
     * @param manufacturerBrandNameEn 厂商品牌（英文）
     */
    public void setManufacturerBrandNameEn(String manufacturerBrandNameEn) {
        this.manufacturerBrandNameEn = manufacturerBrandNameEn;
    }

    /**
     * 获取商品编号
     *
     * @return material_number - 商品编号
     */
    public String getMaterialNumber() {
        return materialNumber;
    }

    /**
     * 设置商品编号
     *
     * @param materialNumber 商品编号
     */
    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    /**
     * 获取商品名称
     *
     * @return product_name - 商品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置商品名称
     *
     * @param productName 商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取有效数量（配送任务订单时为订购数量，
补货订单或者站点送货订单时=订购数量-关闭数量）
     *
     * @return quantity - 有效数量（配送任务订单时为订购数量，
补货订单或者站点送货订单时=订购数量-关闭数量）
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置有效数量（配送任务订单时为订购数量，
补货订单或者站点送货订单时=订购数量-关闭数量）
     *
     * @param quantity 有效数量（配送任务订单时为订购数量，
补货订单或者站点送货订单时=订购数量-关闭数量）
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取服务商-商品单价（未税）
     *
     * @return service_business_tax_excluded_price - 服务商-商品单价（未税）
     */
    public BigDecimal getServiceBusinessTaxExcludedPrice() {
        return serviceBusinessTaxExcludedPrice;
    }

    /**
     * 设置服务商-商品单价（未税）
     *
     * @param serviceBusinessTaxExcludedPrice 服务商-商品单价（未税）
     */
    public void setServiceBusinessTaxExcludedPrice(BigDecimal serviceBusinessTaxExcludedPrice) {
        this.serviceBusinessTaxExcludedPrice = serviceBusinessTaxExcludedPrice;
    }

    /**
     * 获取服务商价格税率
     *
     * @return service_business_tax_rate - 服务商价格税率
     */
    public BigDecimal getServiceBusinessTaxRate() {
        return serviceBusinessTaxRate;
    }

    /**
     * 设置服务商价格税率
     *
     * @param serviceBusinessTaxRate 服务商价格税率
     */
    public void setServiceBusinessTaxRate(BigDecimal serviceBusinessTaxRate) {
        this.serviceBusinessTaxRate = serviceBusinessTaxRate;
    }

    /**
     * 获取总代商品单价（未税）
     *
     * @return general_agency_tax_excluded_price - 总代商品单价（未税）
     */
    public BigDecimal getGeneralAgencyTaxExcludedPrice() {
        return generalAgencyTaxExcludedPrice;
    }

    /**
     * 设置总代商品单价（未税）
     *
     * @param generalAgencyTaxExcludedPrice 总代商品单价（未税）
     */
    public void setGeneralAgencyTaxExcludedPrice(BigDecimal generalAgencyTaxExcludedPrice) {
        this.generalAgencyTaxExcludedPrice = generalAgencyTaxExcludedPrice;
    }

    /**
     * 获取总代价格税率
     *
     * @return general_agency_tax_rate - 总代价格税率
     */
    public BigDecimal getGeneralAgencyTaxRate() {
        return generalAgencyTaxRate;
    }

    /**
     * 设置总代价格税率
     *
     * @param generalAgencyTaxRate 总代价格税率
     */
    public void setGeneralAgencyTaxRate(BigDecimal generalAgencyTaxRate) {
        this.generalAgencyTaxRate = generalAgencyTaxRate;
    }

    /**
     * 获取订单类型（配送任务单/价格调整单-总代单、补货订单/站点送货订单-服务商单）
     *
     * @return order_type - 订单类型（配送任务单/价格调整单-总代单、补货订单/站点送货订单-服务商单）
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型（配送任务单/价格调整单-总代单、补货订单/站点送货订单-服务商单）
     *
     * @param orderType 订单类型（配送任务单/价格调整单-总代单、补货订单/站点送货订单-服务商单）
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取订单编号
     *
     * @return order_code - 订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编号
     *
     * @param orderCode 订单编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取原始单号
     *
     * @return source_order_code - 原始单号
     */
    public String getSourceOrderCode() {
        return sourceOrderCode;
    }

    /**
     * 设置原始单号
     *
     * @param sourceOrderCode 原始单号
     */
    public void setSourceOrderCode(String sourceOrderCode) {
        this.sourceOrderCode = sourceOrderCode;
    }

    /**
     * 获取创建类型（1.开票、2.审核、3.下单）
     *
     * @return create_date_type - 创建类型（1.开票、2.审核、3.下单）
     */
    public Integer getCreateDateType() {
        return createDateType;
    }

    /**
     * 设置创建类型（1.开票、2.审核、3.下单）
     *
     * @param createDateType 创建类型（1.开票、2.审核、3.下单）
     */
    public void setCreateDateType(Integer createDateType) {
        this.createDateType = createDateType;
    }

    /**
     * 获取开票日期/审核时间/下单时间
     *
     * @return create_date - 开票日期/审核时间/下单时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置开票日期/审核时间/下单时间
     *
     * @param createDate 开票日期/审核时间/下单时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取订购类型【商品，赠品】
     *
     * @return purchase_type - 订购类型【商品，赠品】
     */
    public String getPurchaseType() {
        return purchaseType;
    }

    /**
     * 设置订购类型【商品，赠品】
     *
     * @param purchaseType 订购类型【商品，赠品】
     */
    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    /**
     * 获取配送任务订单补货账户规则类型（1：20190831之前的规则，2：20190901之后的规则，9：非配送任务订单不适用）
     *
     * @return task_order_replenishment_account_rule_type - 配送任务订单补货账户规则类型（1：20190831之前的规则，2：20190901之后的规则，9：非配送任务订单不适用）
     */
    public Integer getTaskOrderReplenishmentAccountRuleType() {
        return taskOrderReplenishmentAccountRuleType;
    }

    /**
     * 设置配送任务订单补货账户规则类型（1：20190831之前的规则，2：20190901之后的规则，9：非配送任务订单不适用）
     *
     * @param taskOrderReplenishmentAccountRuleType 配送任务订单补货账户规则类型（1：20190831之前的规则，2：20190901之后的规则，9：非配送任务订单不适用）
     */
    public void setTaskOrderReplenishmentAccountRuleType(Integer taskOrderReplenishmentAccountRuleType) {
        this.taskOrderReplenishmentAccountRuleType = taskOrderReplenishmentAccountRuleType;
    }

    /**
     * 获取创建时间
     *
     * @return sys_created_time - 创建时间
     */
    public Date getSysCreatedTime() {
        return sysCreatedTime;
    }

    /**
     * 设置创建时间
     *
     * @param sysCreatedTime 创建时间
     */
    public void setSysCreatedTime(Date sysCreatedTime) {
        this.sysCreatedTime = sysCreatedTime;
    }

    /**
     * 获取创建操作者的姓名
     *
     * @return sys_created_operator_name - 创建操作者的姓名
     */
    public String getSysCreatedOperatorName() {
        return sysCreatedOperatorName;
    }

    /**
     * 设置创建操作者的姓名
     *
     * @param sysCreatedOperatorName 创建操作者的姓名
     */
    public void setSysCreatedOperatorName(String sysCreatedOperatorName) {
        this.sysCreatedOperatorName = sysCreatedOperatorName;
    }

    /**
     * 获取更新时间
     *
     * @return sys_updated_time - 更新时间
     */
    public Date getSysUpdatedTime() {
        return sysUpdatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param sysUpdatedTime 更新时间
     */
    public void setSysUpdatedTime(Date sysUpdatedTime) {
        this.sysUpdatedTime = sysUpdatedTime;
    }

    /**
     * 获取更新操作者的姓名
     *
     * @return sys_updated_operator_name - 更新操作者的姓名
     */
    public String getSysUpdatedOperatorName() {
        return sysUpdatedOperatorName;
    }

    /**
     * 设置更新操作者的姓名
     *
     * @param sysUpdatedOperatorName 更新操作者的姓名
     */
    public void setSysUpdatedOperatorName(String sysUpdatedOperatorName) {
        this.sysUpdatedOperatorName = sysUpdatedOperatorName;
    }

    /**
     * 获取记录删除状态
     *
     * @return sys_del_flg - 记录删除状态
     */
    public String getSysDelFlg() {
        return sysDelFlg;
    }

    /**
     * 设置记录删除状态
     *
     * @param sysDelFlg 记录删除状态
     */
    public void setSysDelFlg(String sysDelFlg) {
        this.sysDelFlg = sysDelFlg;
    }
}