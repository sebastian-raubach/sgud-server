/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import raubach.sgud.server.database.tables.ItemRatings;


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
public class ItemRatingsRecord extends UpdatableRecordImpl<ItemRatingsRecord> implements Record5<Integer, Integer, Double, Timestamp, Timestamp> {

    private static final long serialVersionUID = 904006931;

    /**
     * Setter for <code>sgud.item_ratings.item_id</code>.
     */
    public void setItemId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>sgud.item_ratings.item_id</code>.
     */
    public Integer getItemId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>sgud.item_ratings.ratingcategory_id</code>.
     */
    public void setRatingcategoryId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>sgud.item_ratings.ratingcategory_id</code>.
     */
    public Integer getRatingcategoryId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>sgud.item_ratings.rating</code>.
     */
    public void setRating(Double value) {
        set(2, value);
    }

    /**
     * Getter for <code>sgud.item_ratings.rating</code>.
     */
    public Double getRating() {
        return (Double) get(2);
    }

    /**
     * Setter for <code>sgud.item_ratings.created_on</code>.
     */
    public void setCreatedOn(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>sgud.item_ratings.created_on</code>.
     */
    public Timestamp getCreatedOn() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>sgud.item_ratings.updated_on</code>.
     */
    public void setUpdatedOn(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>sgud.item_ratings.updated_on</code>.
     */
    public Timestamp getUpdatedOn() {
        return (Timestamp) get(4);
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
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, Double, Timestamp, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, Double, Timestamp, Timestamp> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return ItemRatings.ITEM_RATINGS.ITEM_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return ItemRatings.ITEM_RATINGS.RATINGCATEGORY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field3() {
        return ItemRatings.ITEM_RATINGS.RATING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return ItemRatings.ITEM_RATINGS.CREATED_ON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return ItemRatings.ITEM_RATINGS.UPDATED_ON;
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
    public Integer component2() {
        return getRatingcategoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component3() {
        return getRating();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component4() {
        return getCreatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component5() {
        return getUpdatedOn();
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
    public Integer value2() {
        return getRatingcategoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value3() {
        return getRating();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getCreatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getUpdatedOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemRatingsRecord value1(Integer value) {
        setItemId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemRatingsRecord value2(Integer value) {
        setRatingcategoryId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemRatingsRecord value3(Double value) {
        setRating(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemRatingsRecord value4(Timestamp value) {
        setCreatedOn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemRatingsRecord value5(Timestamp value) {
        setUpdatedOn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemRatingsRecord values(Integer value1, Integer value2, Double value3, Timestamp value4, Timestamp value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ItemRatingsRecord
     */
    public ItemRatingsRecord() {
        super(ItemRatings.ITEM_RATINGS);
    }

    /**
     * Create a detached, initialised ItemRatingsRecord
     */
    public ItemRatingsRecord(Integer itemId, Integer ratingcategoryId, Double rating, Timestamp createdOn, Timestamp updatedOn) {
        super(ItemRatings.ITEM_RATINGS);

        set(0, itemId);
        set(1, ratingcategoryId);
        set(2, rating);
        set(3, createdOn);
        set(4, updatedOn);
    }
}
