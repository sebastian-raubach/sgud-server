/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables;


import com.google.gson.JsonArray;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.TableImpl;

import raubach.sgud.server.binding.JsonStringArrayBinding;
import raubach.sgud.server.database.Indexes;
import raubach.sgud.server.database.Sgud;
import raubach.sgud.server.database.tables.records.ItemsRecord;


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
public class Items extends TableImpl<ItemsRecord> {

    private static final long serialVersionUID = 1537399727;

    /**
     * The reference instance of <code>sgud.items</code>
     */
    public static final Items ITEMS = new Items();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ItemsRecord> getRecordType() {
        return ItemsRecord.class;
    }

    /**
     * The column <code>sgud.items.id</code>.
     */
    public final TableField<ItemsRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>sgud.items.category_id</code>.
     */
    public final TableField<ItemsRecord, Integer> CATEGORY_ID = createField("category_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sgud.items.type_id</code>.
     */
    public final TableField<ItemsRecord, Integer> TYPE_ID = createField("type_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sgud.items.manufacturer_id</code>.
     */
    public final TableField<ItemsRecord, Integer> MANUFACTURER_ID = createField("manufacturer_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sgud.items.source_id</code>.
     */
    public final TableField<ItemsRecord, Integer> SOURCE_ID = createField("source_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>sgud.items.name</code>.
     */
    public final TableField<ItemsRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>sgud.items.description</code>.
     */
    public final TableField<ItemsRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>sgud.items.tags</code>.
     */
    public final TableField<ItemsRecord, JsonArray> TAGS = createField("tags", org.jooq.impl.DefaultDataType.getDefaultDataType("\"sgud\".\"items_tags\""), this, "", new JsonStringArrayBinding());

    /**
     * The column <code>sgud.items.created_on</code>.
     */
    public final TableField<ItemsRecord, Timestamp> CREATED_ON = createField("created_on", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>sgud.items.updated_on</code>.
     */
    public final TableField<ItemsRecord, Timestamp> UPDATED_ON = createField("updated_on", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>sgud.items</code> table reference
     */
    public Items() {
        this(DSL.name("items"), null);
    }

    /**
     * Create an aliased <code>sgud.items</code> table reference
     */
    public Items(String alias) {
        this(DSL.name(alias), ITEMS);
    }

    /**
     * Create an aliased <code>sgud.items</code> table reference
     */
    public Items(Name alias) {
        this(alias, ITEMS);
    }

    private Items(Name alias, Table<ItemsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Items(Name alias, Table<ItemsRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.ITEMS_ITEMS_IBFK_CATEGORIES, Indexes.ITEMS_ITEMS_IBFK_MANUFACTURER, Indexes.ITEMS_ITEMS_IBFK_SOURCE, Indexes.ITEMS_ITEMS_IBFK_TYPE, Indexes.ITEMS_NAME, Indexes.ITEMS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ItemsRecord, Integer> getIdentity() {
        return Internal.createIdentity(raubach.sgud.server.database.tables.Items.ITEMS, raubach.sgud.server.database.tables.Items.ITEMS.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ItemsRecord> getPrimaryKey() {
        return Internal.createUniqueKey(raubach.sgud.server.database.tables.Items.ITEMS, "KEY_items_PRIMARY", raubach.sgud.server.database.tables.Items.ITEMS.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ItemsRecord>> getKeys() {
        return Arrays.<UniqueKey<ItemsRecord>>asList(
              Internal.createUniqueKey(raubach.sgud.server.database.tables.Items.ITEMS, "KEY_items_PRIMARY", raubach.sgud.server.database.tables.Items.ITEMS.ID)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Items as(String alias) {
        return new Items(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Items as(Name alias) {
        return new Items(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Items rename(String name) {
        return new Items(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Items rename(Name name) {
        return new Items(name, null);
    }
}
