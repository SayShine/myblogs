package com.xk.myblogs.client.entity.myblog;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * TABLE  t_purchase_record
 * MyBatis Generator Create
 */
public class PurchaseRecord implements Serializable {
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "产品编号")
    private Long productId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "总价")
    private BigDecimal sum;

    @ApiModelProperty(value = "购买日期")
    private Date purchaseDate;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "原商品剩余数量")
    private Integer afterpurchase;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getAfterpurchase() {
        return afterpurchase;
    }

    public void setAfterpurchase(Integer afterpurchase) {
        this.afterpurchase = afterpurchase;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", productId=").append(productId);
        sb.append(", price=").append(price);
        sb.append(", quantity=").append(quantity);
        sb.append(", sum=").append(sum);
        sb.append(", purchaseDate=").append(purchaseDate);
        sb.append(", note=").append(note);
        sb.append(", afterpurchase=").append(afterpurchase);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}