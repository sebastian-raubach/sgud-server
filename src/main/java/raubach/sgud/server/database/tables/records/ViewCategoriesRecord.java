/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.TableRecordImpl;

import raubach.sgud.server.database.tables.ViewCategories;


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
public class ViewCategoriesRecord extends TableRecordImpl<ViewCategoriesRecord> implements Record7<Integer, String, String, String, Timestamp, Timestamp, Long> {

    private static final long serialVersionUID = -558376099;

    /**
     * Setter for <code>sgud.view_categories.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>sgud.view_categories.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>sgud.view_categories.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>sgud.view_categories.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>sgud.view_categories.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>sgud.view_categories.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>sgud.view_categories.icon</code>.
     */
    public void setIcon(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>sgud.view_categories.icon</code>.
     */
    public String getIcon() {
        return (String) get(3);
    }

    /**
     * Setter for <code>sgud.view_categories.created_on</code>.
     */
    public void setCreatedOn(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>sgud.view_categories.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>sgud.view_categories.updated_on</code>.
     */
    public void setUpdatedOn(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>sgud.view_categories.updated_on</code>.
     */
    public Timestamp getUpdatedOn() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>sgud.view_categories.count</code>.
     */
    public void setCount(Long value) {
        set(6, value);
    }

    /**
     * Getter for <code>sgud.view_categories.count</code>.
     */
    public Long getCount() {
        return (Long) get(6);
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, String, String, Timestamp, Timestamp, Long> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, String, String, Timestamp, Timestamp, Long> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return ViewCategories.VIEW_CATEGORIES.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return ViewCategories.VIEW_CATEGORIES.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return ViewCategories.VIEW_CATEGORIES.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return ViewCategories.VIEW_CATEGORIES.ICON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return ViewCategories.VIEW_CATEGORIES.CREATED_ON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return ViewCategories.VIEW_CATEGORIES.UPDATED_ON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field7() {
        return ViewCategories.VIEW_CATEGORIES.COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component5() {
        return getCreatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component6() {
        return getUpdatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component7() {
        return getCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getCreatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getUpdatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value7() {
        return getCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewCategoriesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewCategoriesRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewCategoriesRecord value3(String value) {
        setDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewCategoriesRecord value4(String value) {
        setIcon(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewCategoriesRecord value5(Timestamp value) {
        setCreatedOn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewCategoriesRecord value6(Timestamp value) {
        setUpdatedOn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewCategoriesRecord value7(Long value) {
        setCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewCategoriesRecord values(Integer value1, String value2, String value3, String value4, Timestamp value5, Timestamp value6, Long value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ViewCategoriesRecord
     */
    public ViewCategoriesRecord() {
        super(ViewCategories.VIEW_CATEGORIES);
    }

    /**
     * Create a detached, initialised ViewCategoriesRecord
     */
    public ViewCategoriesRecord(Integer id, String name, String description, String icon, Timestamp createdOn, Timestamp updatedOn, Long count) {
        super(ViewCategories.VIEW_CATEGORIES);

        set(0, id);
        set(1, name);
        set(2, description);
        set(3, icon);
        set(4, createdOn);
        set(5, updatedOn);
        set(6, count);
    }
}
