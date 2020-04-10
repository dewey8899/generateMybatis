package com.company.project.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_product_base")
public class TbProductBase {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 参考tb_customer_entity_profile
     */
    @Column(name = "entity_code")
    private String entityCode;

    /**
     * 参考tb_customer_entity_role
     */
    @Column(name = "entity_sub_code")
    private String entitySubCode;

    /**
     * 参考tb_product_brand
     */
    @Column(name = "brand_code")
    private String brandCode;

    /**
     * 商品代码【前缀"SKU"+7位SEQ连号】
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 行业
     */
    private String industry;

    /**
     * 商品大分类
     */
    @Column(name = "large_category_code")
    private String largeCategoryCode;

    /**
     * 商品中分类
     */
    @Column(name = "medium_category_code")
    private String mediumCategoryCode;

    /**
     * 商品小分类
     */
    @Column(name = "small_category_code")
    private String smallCategoryCode;

    /**
     * 厂商自用料号
     */
    @Column(name = "material_number")
    private String materialNumber;

    /**
     * 商品名称（中文）
     */
    @Column(name = "product_name_zh")
    private String productNameZh;

    /**
     * 商品名称（英文）
     */
    @Column(name = "product_name_en")
    private String productNameEn;

    /**
     * 商品属性
     */
    @Column(name = "product_attribute")
    private String productAttribute;

    /**
     * EAN码
     */
    @Column(name = "product_ean_code")
    private String productEanCode;

    /**
     * 外箱EAN码
     */
    @Column(name = "box_ean_code")
    private String boxEanCode;

    /**
     * 箱入数
     */
    @Column(name = "box_entry_quantity")
    private Integer boxEntryQuantity;

    /**
     * 保质期
     */
    @Column(name = "quality_guarantee_period")
    private Integer qualityGuaranteePeriod;

    /**
     * 净重
     */
    @Column(name = "net_weight")
    private BigDecimal netWeight;

    /**
     * 毛重
     */
    @Column(name = "gross_weight")
    private BigDecimal grossWeight;

    /**
     * 内包装数量
     */
    @Column(name = "internal_quantity")
    private Integer internalQuantity;

    /**
     * 单位体积（L）
     */
    private BigDecimal volume;

    /**
     * 密度（KG/L）
     */
    private BigDecimal density;

    /**
     * 生产状态
     */
    @Column(name = "production_status")
    private String productionStatus;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 单位
     */
    private String unit;

    /**
     * 上下架状态
     */
    @Column(name = "shelved_status")
    private String shelvedStatus;

    /**
     * 上架时间
     */
    @Column(name = "shelved_time")
    private Date shelvedTime;

    /**
     * 上架操作者的姓名
     */
    @Column(name = "shelved_operator_name")
    private String shelvedOperatorName;

    /**
     * 下架时间
     */
    @Column(name = "unshelved_time")
    private Date unshelvedTime;

    /**
     * 下架操作者的姓名
     */
    @Column(name = "unshelved_operator_name")
    private String unshelvedOperatorName;

    /**
     * 新建操作者的姓名
     */
    @Column(name = "created_operator_name")
    private String createdOperatorName;

    /**
     * 新建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

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
     * 记录更新时间
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
     * 获取参考tb_customer_entity_profile
     *
     * @return entity_code - 参考tb_customer_entity_profile
     */
    public String getEntityCode() {
        return entityCode;
    }

    /**
     * 设置参考tb_customer_entity_profile
     *
     * @param entityCode 参考tb_customer_entity_profile
     */
    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    /**
     * 获取参考tb_customer_entity_role
     *
     * @return entity_sub_code - 参考tb_customer_entity_role
     */
    public String getEntitySubCode() {
        return entitySubCode;
    }

    /**
     * 设置参考tb_customer_entity_role
     *
     * @param entitySubCode 参考tb_customer_entity_role
     */
    public void setEntitySubCode(String entitySubCode) {
        this.entitySubCode = entitySubCode;
    }

    /**
     * 获取参考tb_product_brand
     *
     * @return brand_code - 参考tb_product_brand
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * 设置参考tb_product_brand
     *
     * @param brandCode 参考tb_product_brand
     */
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    /**
     * 获取商品代码【前缀"SKU"+7位SEQ连号】
     *
     * @return product_code - 商品代码【前缀"SKU"+7位SEQ连号】
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置商品代码【前缀"SKU"+7位SEQ连号】
     *
     * @param productCode 商品代码【前缀"SKU"+7位SEQ连号】
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取行业
     *
     * @return industry - 行业
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置行业
     *
     * @param industry 行业
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 获取商品大分类
     *
     * @return large_category_code - 商品大分类
     */
    public String getLargeCategoryCode() {
        return largeCategoryCode;
    }

    /**
     * 设置商品大分类
     *
     * @param largeCategoryCode 商品大分类
     */
    public void setLargeCategoryCode(String largeCategoryCode) {
        this.largeCategoryCode = largeCategoryCode;
    }

    /**
     * 获取商品中分类
     *
     * @return medium_category_code - 商品中分类
     */
    public String getMediumCategoryCode() {
        return mediumCategoryCode;
    }

    /**
     * 设置商品中分类
     *
     * @param mediumCategoryCode 商品中分类
     */
    public void setMediumCategoryCode(String mediumCategoryCode) {
        this.mediumCategoryCode = mediumCategoryCode;
    }

    /**
     * 获取商品小分类
     *
     * @return small_category_code - 商品小分类
     */
    public String getSmallCategoryCode() {
        return smallCategoryCode;
    }

    /**
     * 设置商品小分类
     *
     * @param smallCategoryCode 商品小分类
     */
    public void setSmallCategoryCode(String smallCategoryCode) {
        this.smallCategoryCode = smallCategoryCode;
    }

    /**
     * 获取厂商自用料号
     *
     * @return material_number - 厂商自用料号
     */
    public String getMaterialNumber() {
        return materialNumber;
    }

    /**
     * 设置厂商自用料号
     *
     * @param materialNumber 厂商自用料号
     */
    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    /**
     * 获取商品名称（中文）
     *
     * @return product_name_zh - 商品名称（中文）
     */
    public String getProductNameZh() {
        return productNameZh;
    }

    /**
     * 设置商品名称（中文）
     *
     * @param productNameZh 商品名称（中文）
     */
    public void setProductNameZh(String productNameZh) {
        this.productNameZh = productNameZh;
    }

    /**
     * 获取商品名称（英文）
     *
     * @return product_name_en - 商品名称（英文）
     */
    public String getProductNameEn() {
        return productNameEn;
    }

    /**
     * 设置商品名称（英文）
     *
     * @param productNameEn 商品名称（英文）
     */
    public void setProductNameEn(String productNameEn) {
        this.productNameEn = productNameEn;
    }

    /**
     * 获取商品属性
     *
     * @return product_attribute - 商品属性
     */
    public String getProductAttribute() {
        return productAttribute;
    }

    /**
     * 设置商品属性
     *
     * @param productAttribute 商品属性
     */
    public void setProductAttribute(String productAttribute) {
        this.productAttribute = productAttribute;
    }

    /**
     * 获取EAN码
     *
     * @return product_ean_code - EAN码
     */
    public String getProductEanCode() {
        return productEanCode;
    }

    /**
     * 设置EAN码
     *
     * @param productEanCode EAN码
     */
    public void setProductEanCode(String productEanCode) {
        this.productEanCode = productEanCode;
    }

    /**
     * 获取外箱EAN码
     *
     * @return box_ean_code - 外箱EAN码
     */
    public String getBoxEanCode() {
        return boxEanCode;
    }

    /**
     * 设置外箱EAN码
     *
     * @param boxEanCode 外箱EAN码
     */
    public void setBoxEanCode(String boxEanCode) {
        this.boxEanCode = boxEanCode;
    }

    /**
     * 获取箱入数
     *
     * @return box_entry_quantity - 箱入数
     */
    public Integer getBoxEntryQuantity() {
        return boxEntryQuantity;
    }

    /**
     * 设置箱入数
     *
     * @param boxEntryQuantity 箱入数
     */
    public void setBoxEntryQuantity(Integer boxEntryQuantity) {
        this.boxEntryQuantity = boxEntryQuantity;
    }

    /**
     * 获取保质期
     *
     * @return quality_guarantee_period - 保质期
     */
    public Integer getQualityGuaranteePeriod() {
        return qualityGuaranteePeriod;
    }

    /**
     * 设置保质期
     *
     * @param qualityGuaranteePeriod 保质期
     */
    public void setQualityGuaranteePeriod(Integer qualityGuaranteePeriod) {
        this.qualityGuaranteePeriod = qualityGuaranteePeriod;
    }

    /**
     * 获取净重
     *
     * @return net_weight - 净重
     */
    public BigDecimal getNetWeight() {
        return netWeight;
    }

    /**
     * 设置净重
     *
     * @param netWeight 净重
     */
    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    /**
     * 获取毛重
     *
     * @return gross_weight - 毛重
     */
    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    /**
     * 设置毛重
     *
     * @param grossWeight 毛重
     */
    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    /**
     * 获取内包装数量
     *
     * @return internal_quantity - 内包装数量
     */
    public Integer getInternalQuantity() {
        return internalQuantity;
    }

    /**
     * 设置内包装数量
     *
     * @param internalQuantity 内包装数量
     */
    public void setInternalQuantity(Integer internalQuantity) {
        this.internalQuantity = internalQuantity;
    }

    /**
     * 获取单位体积（L）
     *
     * @return volume - 单位体积（L）
     */
    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * 设置单位体积（L）
     *
     * @param volume 单位体积（L）
     */
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * 获取密度（KG/L）
     *
     * @return density - 密度（KG/L）
     */
    public BigDecimal getDensity() {
        return density;
    }

    /**
     * 设置密度（KG/L）
     *
     * @param density 密度（KG/L）
     */
    public void setDensity(BigDecimal density) {
        this.density = density;
    }

    /**
     * 获取生产状态
     *
     * @return production_status - 生产状态
     */
    public String getProductionStatus() {
        return productionStatus;
    }

    /**
     * 设置生产状态
     *
     * @param productionStatus 生产状态
     */
    public void setProductionStatus(String productionStatus) {
        this.productionStatus = productionStatus;
    }

    /**
     * 获取规格
     *
     * @return specifications - 规格
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * 设置规格
     *
     * @param specifications 规格
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取上下架状态
     *
     * @return shelved_status - 上下架状态
     */
    public String getShelvedStatus() {
        return shelvedStatus;
    }

    /**
     * 设置上下架状态
     *
     * @param shelvedStatus 上下架状态
     */
    public void setShelvedStatus(String shelvedStatus) {
        this.shelvedStatus = shelvedStatus;
    }

    /**
     * 获取上架时间
     *
     * @return shelved_time - 上架时间
     */
    public Date getShelvedTime() {
        return shelvedTime;
    }

    /**
     * 设置上架时间
     *
     * @param shelvedTime 上架时间
     */
    public void setShelvedTime(Date shelvedTime) {
        this.shelvedTime = shelvedTime;
    }

    /**
     * 获取上架操作者的姓名
     *
     * @return shelved_operator_name - 上架操作者的姓名
     */
    public String getShelvedOperatorName() {
        return shelvedOperatorName;
    }

    /**
     * 设置上架操作者的姓名
     *
     * @param shelvedOperatorName 上架操作者的姓名
     */
    public void setShelvedOperatorName(String shelvedOperatorName) {
        this.shelvedOperatorName = shelvedOperatorName;
    }

    /**
     * 获取下架时间
     *
     * @return unshelved_time - 下架时间
     */
    public Date getUnshelvedTime() {
        return unshelvedTime;
    }

    /**
     * 设置下架时间
     *
     * @param unshelvedTime 下架时间
     */
    public void setUnshelvedTime(Date unshelvedTime) {
        this.unshelvedTime = unshelvedTime;
    }

    /**
     * 获取下架操作者的姓名
     *
     * @return unshelved_operator_name - 下架操作者的姓名
     */
    public String getUnshelvedOperatorName() {
        return unshelvedOperatorName;
    }

    /**
     * 设置下架操作者的姓名
     *
     * @param unshelvedOperatorName 下架操作者的姓名
     */
    public void setUnshelvedOperatorName(String unshelvedOperatorName) {
        this.unshelvedOperatorName = unshelvedOperatorName;
    }

    /**
     * 获取新建操作者的姓名
     *
     * @return created_operator_name - 新建操作者的姓名
     */
    public String getCreatedOperatorName() {
        return createdOperatorName;
    }

    /**
     * 设置新建操作者的姓名
     *
     * @param createdOperatorName 新建操作者的姓名
     */
    public void setCreatedOperatorName(String createdOperatorName) {
        this.createdOperatorName = createdOperatorName;
    }

    /**
     * 获取新建时间
     *
     * @return created_time - 新建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置新建时间
     *
     * @param createdTime 新建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
     * 获取记录更新时间
     *
     * @return sys_updated_time - 记录更新时间
     */
    public Date getSysUpdatedTime() {
        return sysUpdatedTime;
    }

    /**
     * 设置记录更新时间
     *
     * @param sysUpdatedTime 记录更新时间
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