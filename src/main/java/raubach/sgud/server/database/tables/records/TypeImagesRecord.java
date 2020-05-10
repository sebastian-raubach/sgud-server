/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import raubach.sgud.server.database.tables.TypeImages;


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
public class TypeImagesRecord extends UpdatableRecordImpl<TypeImagesRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = 1233312510;

    /**
     * Setter for <code>sgud.type_images.type_id</code>.
     */
    public void setTypeId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>sgud.type_images.type_id</code>.
     */
    public Integer getTypeId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>sgud.type_images.image_id</code>.
     */
    public void setImageId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>sgud.type_images.image_id</code>.
     */
    public Integer getImageId() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TypeImages.TYPE_IMAGES.TYPE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return TypeImages.TYPE_IMAGES.IMAGE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getImageId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getImageId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeImagesRecord value1(Integer value) {
        setTypeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeImagesRecord value2(Integer value) {
        setImageId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeImagesRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TypeImagesRecord
     */
    public TypeImagesRecord() {
        super(TypeImages.TYPE_IMAGES);
    }

    /**
     * Create a detached, initialised TypeImagesRecord
     */
    public TypeImagesRecord(Integer typeId, Integer imageId) {
        super(TypeImages.TYPE_IMAGES);

        set(0, typeId);
        set(1, imageId);
    }
}