/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import raubach.sgud.server.database.tables.Types;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TypesRecord extends UpdatableRecordImpl<TypesRecord> implements Record7<Integer, Integer, String, String, String, Timestamp, Timestamp> {

    private static final long serialVersionUID = 417322070;

    /**
     * Setter for <code>sgud.types.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>sgud.types.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>sgud.types.category_id</code>.
     */
    public void setCategoryId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>sgud.types.category_id</code>.
     */
    public Integer getCategoryId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>sgud.types.name</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>sgud.types.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>sgud.types.description</code>.
     */
    public void setDescription(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>sgud.types.description</code>.
     */
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>sgud.types.icon</code>.
     */
    public void setIcon(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>sgud.types.icon</code>.
     */
    public String getIcon() {
        return (String) get(4);
    }

    /**
     * Setter for <code>sgud.types.created_on</code>.
     */
    public void setCreatedOn(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>sgud.types.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>sgud.types.updated_on</code>.
     */
    public void setUpdatedOn(Timestamp value) {
        set(6, value);
    }

    /**
     * Getter for <code>sgud.types.updated_on</code>.
     */
    public Timestamp getUpdatedOn() {
        return (Timestamp) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Integer, String, String, String, Timestamp, Timestamp> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Integer, String, String, String, Timestamp, Timestamp> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Types.TYPES.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Types.TYPES.CATEGORY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Types.TYPES.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Types.TYPES.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Types.TYPES.ICON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return Types.TYPES.CREATED_ON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field7() {
        return Types.TYPES.UPDATED_ON;
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
    public Integer component2() {
        return getCategoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component6() {
        return getCreatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component7() {
        return getUpdatedOn();
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
    public Integer value2() {
        return getCategoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getCreatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value7() {
        return getUpdatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypesRecord value2(Integer value) {
        setCategoryId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypesRecord value3(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypesRecord value4(String value) {
        setDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypesRecord value5(String value) {
        setIcon(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypesRecord value6(Timestamp value) {
        setCreatedOn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypesRecord value7(Timestamp value) {
        setUpdatedOn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypesRecord values(Integer value1, Integer value2, String value3, String value4, String value5, Timestamp value6, Timestamp value7) {
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
     * Create a detached TypesRecord
     */
    public TypesRecord() {
        super(Types.TYPES);
    }

    /**
     * Create a detached, initialised TypesRecord
     */
    public TypesRecord(Integer id, Integer categoryId, String name, String description, String icon, Timestamp createdOn, Timestamp updatedOn) {
        super(Types.TYPES);

        set(0, id);
        set(1, categoryId);
        set(2, name);
        set(3, description);
        set(4, icon);
        set(5, createdOn);
        set(6, updatedOn);
    }
}
