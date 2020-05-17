/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables.records;


import org.jooq.Field;
import org.jooq.Record16;
import org.jooq.Row16;
import org.jooq.impl.TableRecordImpl;
import raubach.sgud.server.database.tables.ViewItems;

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
public class ViewItemsRecord extends TableRecordImpl<ViewItemsRecord> implements Record16<Integer, String, String, Integer, String, String, String, Integer, String, String, String, Integer, String, String, String, Double> {

    private static final long serialVersionUID = 356258546;

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
     * Setter for <code>sgud.view_items.manufacturer_id</code>.
     */
    public void setManufacturerId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>sgud.view_items.manufacturer_id</code>.
     */
    public Integer getManufacturerId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>sgud.view_items.manufacturer_name</code>.
     */
    public void setManufacturerName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>sgud.view_items.manufacturer_name</code>.
     */
    public String getManufacturerName() {
        return (String) get(4);
    }

    /**
     * Setter for <code>sgud.view_items.manufacturer_description</code>.
     */
    public void setManufacturerDescription(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>sgud.view_items.manufacturer_description</code>.
     */
    public String getManufacturerDescription() {
        return (String) get(5);
    }

    /**
     * Setter for <code>sgud.view_items.manufacturer_url</code>.
     */
    public void setManufacturerUrl(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>sgud.view_items.manufacturer_url</code>.
     */
    public String getManufacturerUrl() {
        return (String) get(6);
    }

    /**
     * Setter for <code>sgud.view_items.category_id</code>.
     */
    public void setCategoryId(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>sgud.view_items.category_id</code>.
     */
    public Integer getCategoryId() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>sgud.view_items.category_name</code>.
     */
    public void setCategoryName(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>sgud.view_items.category_name</code>.
     */
    public String getCategoryName() {
        return (String) get(8);
    }

    /**
     * Setter for <code>sgud.view_items.category_description</code>.
     */
    public void setCategoryDescription(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>sgud.view_items.category_description</code>.
     */
    public String getCategoryDescription() {
        return (String) get(9);
    }

    /**
     * Setter for <code>sgud.view_items.category_icon</code>.
     */
    public void setCategoryIcon(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>sgud.view_items.category_icon</code>.
     */
    public String getCategoryIcon() {
        return (String) get(10);
    }

    /**
     * Setter for <code>sgud.view_items.type_id</code>.
     */
    public void setTypeId(Integer value) {
        set(11, value);
    }

    /**
     * Getter for <code>sgud.view_items.type_id</code>.
     */
    public Integer getTypeId() {
        return (Integer) get(11);
    }

    /**
     * Setter for <code>sgud.view_items.type_name</code>.
     */
    public void setTypeName(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>sgud.view_items.type_name</code>.
     */
    public String getTypeName() {
        return (String) get(12);
    }

    /**
     * Setter for <code>sgud.view_items.type_description</code>.
     */
    public void setTypeDescription(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>sgud.view_items.type_description</code>.
     */
    public String getTypeDescription() {
        return (String) get(13);
    }

    /**
     * Setter for <code>sgud.view_items.type_icon</code>.
     */
    public void setTypeIcon(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>sgud.view_items.type_icon</code>.
     */
    public String getTypeIcon() {
        return (String) get(14);
    }

    /**
     * Setter for <code>sgud.view_items.avg_item_rating</code>.
     */
    public void setAvgItemRating(Double value) {
        set(15, value);
    }

    /**
     * Getter for <code>sgud.view_items.avg_item_rating</code>.
     */
    public Double getAvgItemRating() {
        return (Double) get(15);
    }

    // -------------------------------------------------------------------------
    // Record16 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row16<Integer, String, String, Integer, String, String, String, Integer, String, String, String, Integer, String, String, String, Double> fieldsRow() {
        return (Row16) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row16<Integer, String, String, Integer, String, String, String, Integer, String, String, String, Integer, String, String, String, Double> valuesRow() {
        return (Row16) super.valuesRow();
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
    public Field<Integer> field4() {
        return ViewItems.VIEW_ITEMS.MANUFACTURER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return ViewItems.VIEW_ITEMS.MANUFACTURER_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return ViewItems.VIEW_ITEMS.MANUFACTURER_DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return ViewItems.VIEW_ITEMS.MANUFACTURER_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field8() {
        return ViewItems.VIEW_ITEMS.CATEGORY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return ViewItems.VIEW_ITEMS.CATEGORY_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return ViewItems.VIEW_ITEMS.CATEGORY_DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return ViewItems.VIEW_ITEMS.CATEGORY_ICON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field12() {
        return ViewItems.VIEW_ITEMS.TYPE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return ViewItems.VIEW_ITEMS.TYPE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return ViewItems.VIEW_ITEMS.TYPE_DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return ViewItems.VIEW_ITEMS.TYPE_ICON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field16() {
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
    public Integer component4() {
        return getManufacturerId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getManufacturerName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getManufacturerDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getManufacturerUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component8() {
        return getCategoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getCategoryName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getCategoryDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getCategoryIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component12() {
        return getTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component13() {
        return getTypeName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component14() {
        return getTypeDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component15() {
        return getTypeIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component16() {
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
    public Integer value4() {
        return getManufacturerId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getManufacturerName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getManufacturerDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getManufacturerUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value8() {
        return getCategoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getCategoryName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getCategoryDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getCategoryIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value12() {
        return getTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getTypeName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getTypeDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getTypeIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value16() {
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
    public ViewItemsRecord value4(Integer value) {
        setManufacturerId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value5(String value) {
        setManufacturerName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value6(String value) {
        setManufacturerDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value7(String value) {
        setManufacturerUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value8(Integer value) {
        setCategoryId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value9(String value) {
        setCategoryName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value10(String value) {
        setCategoryDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value11(String value) {
        setCategoryIcon(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value12(Integer value) {
        setTypeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value13(String value) {
        setTypeName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value14(String value) {
        setTypeDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value15(String value) {
        setTypeIcon(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord value16(Double value) {
        setAvgItemRating(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewItemsRecord values(Integer value1, String value2, String value3, Integer value4, String value5, String value6, String value7, Integer value8, String value9, String value10, String value11, Integer value12, String value13, String value14, String value15, Double value16) {
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
    public ViewItemsRecord(Integer itemId, String itemName, String itemDescription, Integer manufacturerId, String manufacturerName, String manufacturerDescription, String manufacturerUrl, Integer categoryId, String categoryName, String categoryDescription, String categoryIcon, Integer typeId, String typeName, String typeDescription, String typeIcon, Double avgItemRating) {
        super(ViewItems.VIEW_ITEMS);

        set(0, itemId);
        set(1, itemName);
        set(2, itemDescription);
        set(3, manufacturerId);
        set(4, manufacturerName);
        set(5, manufacturerDescription);
        set(6, manufacturerUrl);
        set(7, categoryId);
        set(8, categoryName);
        set(9, categoryDescription);
        set(10, categoryIcon);
        set(11, typeId);
        set(12, typeName);
        set(13, typeDescription);
        set(14, typeIcon);
        set(15, avgItemRating);
    }
}
