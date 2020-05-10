/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables;


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
import raubach.sgud.server.database.tables.records.ItemImagesRecord;


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
public class ItemImages extends TableImpl<ItemImagesRecord> {

    private static final long serialVersionUID = 1141527240;

    /**
     * The reference instance of <code>sgud.item_images</code>
     */
    public static final ItemImages ITEM_IMAGES = new ItemImages();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ItemImagesRecord> getRecordType() {
        return ItemImagesRecord.class;
    }

    /**
     * The column <code>sgud.item_images.item_id</code>.
     */
    public final TableField<ItemImagesRecord, Integer> ITEM_ID = createField("item_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sgud.item_images.image_id</code>.
     */
    public final TableField<ItemImagesRecord, Integer> IMAGE_ID = createField("image_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>sgud.item_images</code> table reference
     */
    public ItemImages() {
        this(DSL.name("item_images"), null);
    }

    /**
     * Create an aliased <code>sgud.item_images</code> table reference
     */
    public ItemImages(String alias) {
        this(DSL.name(alias), ITEM_IMAGES);
    }

    /**
     * Create an aliased <code>sgud.item_images</code> table reference
     */
    public ItemImages(Name alias) {
        this(alias, ITEM_IMAGES);
    }

    private ItemImages(Name alias, Table<ItemImagesRecord> aliased) {
        this(alias, aliased, null);
    }

    private ItemImages(Name alias, Table<ItemImagesRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.ITEM_IMAGES_ITEMIMAGES_IBFK_IMAGES, Indexes.ITEM_IMAGES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ItemImagesRecord> getPrimaryKey() {
        return Internal.createUniqueKey(raubach.sgud.server.database.tables.ItemImages.ITEM_IMAGES, "KEY_item_images_PRIMARY", raubach.sgud.server.database.tables.ItemImages.ITEM_IMAGES.ITEM_ID, raubach.sgud.server.database.tables.ItemImages.ITEM_IMAGES.IMAGE_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ItemImagesRecord>> getKeys() {
        return Arrays.<UniqueKey<ItemImagesRecord>>asList(
              Internal.createUniqueKey(raubach.sgud.server.database.tables.ItemImages.ITEM_IMAGES, "KEY_item_images_PRIMARY", raubach.sgud.server.database.tables.ItemImages.ITEM_IMAGES.ITEM_ID, raubach.sgud.server.database.tables.ItemImages.ITEM_IMAGES.IMAGE_ID)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemImages as(String alias) {
        return new ItemImages(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemImages as(Name alias) {
        return new ItemImages(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ItemImages rename(String name) {
        return new ItemImages(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ItemImages rename(Name name) {
        return new ItemImages(name, null);
    }
}