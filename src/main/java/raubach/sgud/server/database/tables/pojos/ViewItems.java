/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables.pojos;


import com.google.gson.JsonArray;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


/**
 * VIEW
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewItems implements Serializable {

    private static final long serialVersionUID = 1394876395;

    private Integer   itemId;
    private String    itemName;
    private String    itemDescription;
    private JsonArray itemTags;
    private Timestamp itemCreatedOn;
    private Integer   manufacturerId;
    private String    manufacturerName;
    private String    manufacturerDescription;
    private String    manufacturerUrl;
    private Integer   sourceId;
    private String    sourceName;
    private String    sourceDescription;
    private String    sourceUrl;
    private Integer   categoryId;
    private String    categoryName;
    private String    categoryDescription;
    private String    categoryIcon;
    private Integer   typeId;
    private String    typeName;
    private String    typeDescription;
    private String    typeIcon;
    private Double    avgItemRating;

    public ViewItems() {}

    public ViewItems(ViewItems value) {
        this.itemId = value.itemId;
        this.itemName = value.itemName;
        this.itemDescription = value.itemDescription;
        this.itemTags = value.itemTags;
        this.itemCreatedOn = value.itemCreatedOn;
        this.manufacturerId = value.manufacturerId;
        this.manufacturerName = value.manufacturerName;
        this.manufacturerDescription = value.manufacturerDescription;
        this.manufacturerUrl = value.manufacturerUrl;
        this.sourceId = value.sourceId;
        this.sourceName = value.sourceName;
        this.sourceDescription = value.sourceDescription;
        this.sourceUrl = value.sourceUrl;
        this.categoryId = value.categoryId;
        this.categoryName = value.categoryName;
        this.categoryDescription = value.categoryDescription;
        this.categoryIcon = value.categoryIcon;
        this.typeId = value.typeId;
        this.typeName = value.typeName;
        this.typeDescription = value.typeDescription;
        this.typeIcon = value.typeIcon;
        this.avgItemRating = value.avgItemRating;
    }

    public ViewItems(
        Integer   itemId,
        String    itemName,
        String    itemDescription,
        JsonArray itemTags,
        Timestamp itemCreatedOn,
        Integer   manufacturerId,
        String    manufacturerName,
        String    manufacturerDescription,
        String    manufacturerUrl,
        Integer   sourceId,
        String    sourceName,
        String    sourceDescription,
        String    sourceUrl,
        Integer   categoryId,
        String    categoryName,
        String    categoryDescription,
        String    categoryIcon,
        Integer   typeId,
        String    typeName,
        String    typeDescription,
        String    typeIcon,
        Double    avgItemRating
    ) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemTags = itemTags;
        this.itemCreatedOn = itemCreatedOn;
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.manufacturerDescription = manufacturerDescription;
        this.manufacturerUrl = manufacturerUrl;
        this.sourceId = sourceId;
        this.sourceName = sourceName;
        this.sourceDescription = sourceDescription;
        this.sourceUrl = sourceUrl;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryIcon = categoryIcon;
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDescription = typeDescription;
        this.typeIcon = typeIcon;
        this.avgItemRating = avgItemRating;
    }

    public Integer getItemId() {
        return this.itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public JsonArray getItemTags() {
        return this.itemTags;
    }

    public void setItemTags(JsonArray itemTags) {
        this.itemTags = itemTags;
    }

    public Timestamp getItemCreatedOn() {
        return this.itemCreatedOn;
    }

    public void setItemCreatedOn(Timestamp itemCreatedOn) {
        this.itemCreatedOn = itemCreatedOn;
    }

    public Integer getManufacturerId() {
        return this.manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerDescription() {
        return this.manufacturerDescription;
    }

    public void setManufacturerDescription(String manufacturerDescription) {
        this.manufacturerDescription = manufacturerDescription;
    }

    public String getManufacturerUrl() {
        return this.manufacturerUrl;
    }

    public void setManufacturerUrl(String manufacturerUrl) {
        this.manufacturerUrl = manufacturerUrl;
    }

    public Integer getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceDescription() {
        return this.sourceDescription;
    }

    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return this.categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryIcon() {
        return this.categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return this.typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypeIcon() {
        return this.typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    public Double getAvgItemRating() {
        return this.avgItemRating;
    }

    public void setAvgItemRating(Double avgItemRating) {
        this.avgItemRating = avgItemRating;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ViewItems (");

        sb.append(itemId);
        sb.append(", ").append(itemName);
        sb.append(", ").append(itemDescription);
        sb.append(", ").append(itemTags);
        sb.append(", ").append(itemCreatedOn);
        sb.append(", ").append(manufacturerId);
        sb.append(", ").append(manufacturerName);
        sb.append(", ").append(manufacturerDescription);
        sb.append(", ").append(manufacturerUrl);
        sb.append(", ").append(sourceId);
        sb.append(", ").append(sourceName);
        sb.append(", ").append(sourceDescription);
        sb.append(", ").append(sourceUrl);
        sb.append(", ").append(categoryId);
        sb.append(", ").append(categoryName);
        sb.append(", ").append(categoryDescription);
        sb.append(", ").append(categoryIcon);
        sb.append(", ").append(typeId);
        sb.append(", ").append(typeName);
        sb.append(", ").append(typeDescription);
        sb.append(", ").append(typeIcon);
        sb.append(", ").append(avgItemRating);

        sb.append(")");
        return sb.toString();
    }
}
