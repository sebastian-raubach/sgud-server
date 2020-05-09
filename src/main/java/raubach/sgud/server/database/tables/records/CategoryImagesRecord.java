/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import raubach.sgud.server.database.tables.CategoryImages;


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
public class CategoryImagesRecord extends UpdatableRecordImpl<CategoryImagesRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = -58660570;

    /**
     * Setter for <code>sgud.category_images.category_id</code>.
     */
    public void setCategoryId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>sgud.category_images.category_id</code>.
     */
    public Integer getCategoryId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>sgud.category_images.image_id</code>.
     */
    public void setImageId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>sgud.category_images.image_id</code>.
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
        return CategoryImages.CATEGORY_IMAGES.CATEGORY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return CategoryImages.CATEGORY_IMAGES.IMAGE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getCategoryId();
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
        return getCategoryId();
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
    public CategoryImagesRecord value1(Integer value) {
        setCategoryId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryImagesRecord value2(Integer value) {
        setImageId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryImagesRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CategoryImagesRecord
     */
    public CategoryImagesRecord() {
        super(CategoryImages.CATEGORY_IMAGES);
    }

    /**
     * Create a detached, initialised CategoryImagesRecord
     */
    public CategoryImagesRecord(Integer categoryId, Integer imageId) {
        super(CategoryImages.CATEGORY_IMAGES);

        set(0, categoryId);
        set(1, imageId);
    }
}
