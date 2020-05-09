/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.TableImpl;

import raubach.sgud.server.database.Indexes;
import raubach.sgud.server.database.Sgud;
import raubach.sgud.server.database.tables.records.ItemRatingsRecord;


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
public class ItemRatings extends TableImpl<ItemRatingsRecord> {

    private static final long serialVersionUID = -1428653128;

    /**
     * The reference instance of <code>sgud.item_ratings</code>
     */
    public static final ItemRatings ITEM_RATINGS = new ItemRatings();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ItemRatingsRecord> getRecordType() {
        return ItemRatingsRecord.class;
    }

    /**
     * The column <code>sgud.item_ratings.item_id</code>.
     */
    public final TableField<ItemRatingsRecord, Integer> ITEM_ID = createField("item_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sgud.item_ratings.ratingcategory_id</code>.
     */
    public final TableField<ItemRatingsRecord, Integer> RATINGCATEGORY_ID = createField("ratingcategory_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sgud.item_ratings.rating</code>.
     */
    public final TableField<ItemRatingsRecord, Double> RATING = createField("rating", org.jooq.impl.SQLDataType.FLOAT.nullable(false), this, "");

    /**
     * The column <code>sgud.item_ratings.created_on</code>.
     */
    public final TableField<ItemRatingsRecord, Timestamp> CREATED_ON = createField("created_on", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>sgud.item_ratings.updated_on</code>.
     */
    public final TableField<ItemRatingsRecord, Timestamp> UPDATED_ON = createField("updated_on", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>sgud.item_ratings</code> table reference
     */
    public ItemRatings() {
        this(DSL.name("item_ratings"), null);
    }

    /**
     * Create an aliased <code>sgud.item_ratings</code> table reference
     */
    public ItemRatings(String alias) {
        this(DSL.name(alias), ITEM_RATINGS);
    }

    /**
     * Create an aliased <code>sgud.item_ratings</code> table reference
     */
    public ItemRatings(Name alias) {
        this(alias, ITEM_RATINGS);
    }

    private ItemRatings(Name alias, Table<ItemRatingsRecord> aliased) {
        this(alias, aliased, null);
    }

    private ItemRatings(Name alias, Table<ItemRatingsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Sgud.SGUD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ITEM_RATINGS_ITEM_RATINGS_IBFK_RATINGCATEGORY, Indexes.ITEM_RATINGS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ItemRatingsRecord> getPrimaryKey() {
        return Internal.createUniqueKey(raubach.sgud.server.database.tables.ItemRatings.ITEM_RATINGS, "KEY_item_ratings_PRIMARY", raubach.sgud.server.database.tables.ItemRatings.ITEM_RATINGS.ITEM_ID, raubach.sgud.server.database.tables.ItemRatings.ITEM_RATINGS.RATINGCATEGORY_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ItemRatingsRecord>> getKeys() {
        return Arrays.<UniqueKey<ItemRatingsRecord>>asList(
              Internal.createUniqueKey(raubach.sgud.server.database.tables.ItemRatings.ITEM_RATINGS, "KEY_item_ratings_PRIMARY", raubach.sgud.server.database.tables.ItemRatings.ITEM_RATINGS.ITEM_ID, raubach.sgud.server.database.tables.ItemRatings.ITEM_RATINGS.RATINGCATEGORY_ID)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemRatings as(String alias) {
        return new ItemRatings(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemRatings as(Name alias) {
        return new ItemRatings(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ItemRatings rename(String name) {
        return new ItemRatings(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ItemRatings rename(Name name) {
        return new ItemRatings(name, null);
    }
}
