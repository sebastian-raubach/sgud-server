/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables.records;


import com.google.gson.JsonArray;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record22;
import org.jooq.Row22;
import org.jooq.impl.TableRecordImpl;

import raubach.sgud.server.database.tables.ViewItems;


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
public class ViewItemsRecord extends TableRecordImpl<ViewItemsRecord> implements Record22<Integer, String, String, JsonArray, Timestamp, Integer, String, String, String, Integer, String, String, String, Integer, String, String, String, Integer, String, String, String, Double> {

    private static final long serialVersionUID = 463870966;

    /**
     * Setter for <code>sgud.view_items.item_id</code>.
     */
    public void setItemId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>sgud.view_items.item_id</code>.
     */
    public Integer getItemId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>sgud.view_items.item_name</code>.
     */
    public void setItemName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>sgud.view_items.item_name</code>.
     */
    public String getItemName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>sgud.view_items.item_description</code>.
     */
    public void setItemDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>sgud.view_items.item_description</code>.
     */
    public String getItemDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>sgud.view_items.item_tags</code>.
     */
    public void setItemTags(JsonArray value) {
        set(3, value);
    }

    /**
     * Getter for <code>sgud.view_items.item_tags</code>.
     */
    public JsonArray getItemTags() {
        return (JsonArray) get(3);
    }

    /**
     * Setter for <code>sgud.view_items.item_created_on</code>.
     */
    public void setItemCreatedOn(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>sgud.view_items.item_created_on</code>.
     */
    public Timestamp getItemCreatedOn() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>sgud.view_items.manufacturer_id</code>.
     */
    public void setManufacturerId(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>sgud.view_items.manufacturer_id</code>.
     */
    public Integer getManufacturerId() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>sgud.view_items.manufacturer_name</code>.
     */
    public void setManufacturerName(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>sgud.view_items.manufacturer_name</code>.
     */
    public String getManufacturerName() {
        return (String) get(6);
    }

    /**
     * Setter for <code>sgud.view_items.manufacturer_description</code>.
     */
    public void setManufacturerDescription(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>sgud.view_items.manufacturer_description</code>.
     */
    public String getManufacturerDescription() {
        return (String) get(7);
    }

    /**
     * Setter for <code>sgud.view_items.manufacturer_url</code>.
     */
    public void setManufacturerUrl(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>sgud.view_items.manufacturer_url</code>.
     */
    public String getManufacturerUrl() {
        return (String) get(8);
    }

    /**
     * Setter for <code>sgud.view_items.source_id</code>.
     */
    public void setSourceId(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>sgud.view_items.source_id</code>.
     */
    public Integer getSourceId() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>sgud.view_items.source_name</code>.
     */
    public void setSourceName(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>sgud.view_items.source_name</code>.
     */
    public String getSourceName() {
        return (String) get(10);
    }

    /**
     * Setter for <code>sgud.view_items.source_description</code>.
     */
    public void setSourceDescription(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>sgud.view_items.source_description</code>.
     */
    public String getSourceDescription() {
        return (String) get(11);
    }

    /**
     * Setter for <code>sgud.view_items.source_url</code>.
     */
    public void setSourceUrl(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>sgud.view_items.source_url</code>.
     */
    public String getSourceUrl() {
        return (String) get(12);
    }

    /**
     * Setter for <code>sgud.view_items.category_id</code>.
     */
    public void setCategoryId(Integer value) {
        set(13, value);
    }

    /**
     * Getter for <code>sgud.view_items.category_id</code>.
     */
    public Integer getCategoryId() {
        return (Integer) get(13);
    }

    /**
     * Setter for <code>sgud.view_items.category_name</code>.
     */
    public void setCategoryName(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>sgud.view_items.category_name</code>.
     */
    public String getCategoryName() {
        return (String) get(14);
    }

    /**
     * Setter for <code>sgud.view_items.category_description</code>.
     */
    public void setCategoryDescription(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>sgud.view_items.category_description</code>.
     */
    public String getCategoryDescription() {
        return (String) get(15);
    }

    /**
     * Setter for <code>sgud.view_items.category_icon</code>.
     */
    public void setCategoryIcon(String value) {
        set(16, value);
    }

    /**
     * Getter for <code>sgud.view_items.category_icon</code>.
     */
    public String getCategoryIcon() {
        return (String) get(16);
    }

    /**
     * Setter for <code>sgud.view_items.type_id</code>.
     */
    public void setTypeId(Integer value) {
        set(17, value);
    }

    /**
     * Getter for <code>sgud.view_items.type_id</code>.
     */
    public Integer getTypeId() {
        return (Integer) get(17);
    }

    /**
     * Setter for <code>sgud.view_items.type_name</code>.
     */
    public void setTypeName(String value) {
        set(18, value);
    }

    /**
     * Getter for <code>sgud.view_items.type_name</code>.
     */
    public String getTypeName() {
        return (String) get(18);
    }

    /**
     * Setter for <code>sgud.view_items.type_description</code>.
     */
    public void setTypeDescription(String value) {
        set(19, value);
    }

    /**
     * Getter for <code>sgud.view_items.type_description</code>.
     */
    public String getTypeDescription() {
        return (String) get(19);
    }

    /**
     * Setter for <code>sgud.view_items.type_icon</code>.
     */
    public void setTypeIcon(String value) {
        set(20, value);
    }

    /**
     * Getter for <code>sgud.view_items.type_icon</code>.
     */
    public String getTypeIcon() {
        return (String) get(20);
    }

    /**
     * Setter for <code>sgud.view_items.avg_item_rating</code>.
     */
    public void setAvgItemRating(Double value) {
        set(21, value);
    }

    /**
     * Getter for <code>sgud.view_items.avg_item_rating</code>.
     */
    public Double getAvgItemRating() {
        return (Double) get(21);
    }

    // -------------------------------------------------------------------------
    // Record22 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row22<Integer, String, String, JsonArray, Timestamp, Integer, String, String, String, Integer, String, String, String, Integer, String, String, String, Integer, String, String, String, Double> fieldsRow() {
        return (Row22) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row22<Integer, String, String, JsonArray, Timestamp, Integer, String, String, String, Integer, String, String, String, Integer, String, String, String, Integer, String, String, String, Double> valuesRow() {
        return (Row22) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return ViewItems.VIEW_ITEMS.ITEM_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return ViewItems.VIEW_ITEMS.ITEM_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return ViewItems.VIEW_ITEMS.ITEM_DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<JsonArray> field4() {
        return ViewItems.VIEW_ITEMS.ITEM_TAGS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return ViewItems.VIEW_ITEMS.ITEM_CREATED_ON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return ViewItems.VIEW_ITEMS.MANUFACTURER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return ViewItems.VIEW_ITEMS.MANUFACTURER_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return ViewItems.VIEW_ITEMS.MANUFACTURER_DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return ViewItems.VIEW_ITEMS.MANUFACTURER_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field10() {
        return ViewItems.VIEW_ITEMS.SOURCE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return ViewItems.VIEW_ITEMS.SOURCE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return ViewItems.VIEW_ITEMS.SOURCE_DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return ViewItems.VIEW_ITEMS.SOURCE_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field14() {
        return ViewItems.VIEW_ITEMS.CATEGORY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return ViewItems.VIEW_ITEMS.CATEGORY_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return ViewItems.VIEW_ITEMS.CATEGORY_DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field17() {
        return ViewItems.VIEW_ITEMS.CATEGORY_ICON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field18() {
        return ViewItems.VIEW_ITEMS.TYPE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field19() {
        return ViewItems.VIEW_ITEMS.TYPE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field20() {
        return ViewItems.VIEW_ITEMS.TYPE_DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field21() {
        return ViewItems.VIEW_ITEMS.TYPE_ICON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field22() {
        return ViewItems.VIEW_ITEMS.AVG_ITEM_RATING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getItemId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getItemName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getItemDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonArray component4() {
        return getItemTags();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component5() {
        return getItemCreatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component6() {
        return getManufacturerId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getManufacturerName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getManufacturerDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getManufacturerUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component10() {
        return getSourceId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getSourceName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component12() {
        return getSourceDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component13() {
        return getSourceUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component14() {
        return getCategoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component15() {
        return getCategoryName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component16() {
        return getCategoryDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component17() {
        return getCategoryIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component18() {
        return getTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component19() {
        return getTypeName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component20() {
        return getTypeDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component21() {
        return getTypeIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component22() {
        return getAvgItemRating();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getItemId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getItemName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getItemDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonArray value4() {
        return getItemTags();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getItemCreatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getManufacturerId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getManufacturerName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getManufacturerDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getManufacturerUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value10() {
        return getSourceId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getSourceName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getSourceDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getSourceUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value14() {
        return getCategoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getCategoryName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value16() {
        return getCategoryDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value17() {
        return getCategoryIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value18() {
        return getTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value19() {
        return getTypeName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value20() {
        return getTypeDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value21() {
        return getTypeIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value22() {
        return getAvgItemRating();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value1(Integer value) {
        setItemId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value2(String value) {
        setItemName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value3(String value) {
        setItemDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value4(JsonArray value) {
        setItemTags(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value5(Timestamp value) {
        setItemCreatedOn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value6(Integer value) {
        setManufacturerId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value7(String value) {
        setManufacturerName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value8(String value) {
        setManufacturerDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value9(String value) {
        setManufacturerUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value10(Integer value) {
        setSourceId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value11(String value) {
        setSourceName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value12(String value) {
        setSourceDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value13(String value) {
        setSourceUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value14(Integer value) {
        setCategoryId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value15(String value) {
        setCategoryName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value16(String value) {
        setCategoryDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value17(String value) {
        setCategoryIcon(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value18(Integer value) {
        setTypeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value19(String value) {
        setTypeName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value20(String value) {
        setTypeDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value21(String value) {
        setTypeIcon(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value22(Double value) {
        setAvgItemRating(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord values(Integer value1, String value2, String value3, JsonArray value4, Timestamp value5, Integer value6, String value7, String value8, String value9, Integer value10, String value11, String value12, String value13, Integer value14, String value15, String value16, String value17, Integer value18, String value19, String value20, String value21, Double value22) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        value21(value21);
        value22(value22);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ViewItemsRecord
     */
    public ViewItemsRecord() {
        super(ViewItems.VIEW_ITEMS);
    }

    /**
     * Create a detached, initialised ViewItemsRecord
     */
    public ViewItemsRecord(Integer itemId, String itemName, String itemDescription, JsonArray itemTags, Timestamp itemCreatedOn, Integer manufacturerId, String manufacturerName, String manufacturerDescription, String manufacturerUrl, Integer sourceId, String sourceName, String sourceDescription, String sourceUrl, Integer categoryId, String categoryName, String categoryDescription, String categoryIcon, Integer typeId, String typeName, String typeDescription, String typeIcon, Double avgItemRating) {
        super(ViewItems.VIEW_ITEMS);

        set(0, itemId);
        set(1, itemName);
        set(2, itemDescription);
        set(3, itemTags);
        set(4, itemCreatedOn);
        set(5, manufacturerId);
        set(6, manufacturerName);
        set(7, manufacturerDescription);
        set(8, manufacturerUrl);
        set(9, sourceId);
        set(10, sourceName);
        set(11, sourceDescription);
        set(12, sourceUrl);
        set(13, categoryId);
        set(14, categoryName);
        set(15, categoryDescription);
        set(16, categoryIcon);
        set(17, typeId);
        set(18, typeName);
        set(19, typeDescription);
        set(20, typeIcon);
        set(21, avgItemRating);
    }
}
