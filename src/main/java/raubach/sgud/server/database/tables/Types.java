/*
 * This file is generated by jOOQ.
 */
package raubach.sgud.server.database.tables;


import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.TableImpl;
import raubach.sgud.server.database.Indexes;
import raubach.sgud.server.database.Sgud;
import raubach.sgud.server.database.tables.records.TypesRecord;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


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
public class Types extends TableImpl<TypesRecord> {

    private static final long serialVersionUID = 1718165237;

    /**
     * The reference instance of <code>sgud.types</code>
     */
    public static final Types TYPES = new Types();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TypesRecord> getRecordType() {
        return TypesRecord.class;
    }

    /**
     * The column <code>sgud.types.id</code>.
     */
    public final TableField<TypesRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>sgud.types.category_id</code>.
     */
    public final TableField<TypesRecord, Integer> CATEGORY_ID = createField("category_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sgud.types.name</code>.
     */
    public final TableField<TypesRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>sgud.types.description</code>.
     */
    public final TableField<TypesRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>sgud.types.icon</code>.
     */
    public final TableField<TypesRecord, String> ICON = createField("icon", org.jooq.impl.SQLDataType.VARCHAR(100), this, "");

    /**
     * The column <code>sgud.types.created_on</code>.
     */
    public final TableField<TypesRecord, Timestamp> CREATED_ON = createField("created_on", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>sgud.types.updated_on</code>.
     */
    public final TableField<TypesRecord, Timestamp> UPDATED_ON = createField("updated_on", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>sgud.types</code> table reference
     */
    public Types() {
        this(DSL.name("types"), null);
    }

    /**
     * Create an aliased <code>sgud.types</code> table reference
     */
    public Types(String alias) {
        this(DSL.name(alias), TYPES);
    }

    /**
     * Create an aliased <code>sgud.types</code> table reference
     */
    public Types(Name alias) {
        this(alias, TYPES);
    }

    private Types(Name alias, Table<TypesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Types(Name alias, Table<TypesRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.TYPES_NAME, Indexes.TYPES_PRIMARY, Indexes.TYPES_TYPE_IBFK_CATEGORY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TypesRecord, Integer> getIdentity() {
        return Internal.createIdentity(raubach.sgud.server.database.tables.Types.TYPES, raubach.sgud.server.database.tables.Types.TYPES.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TypesRecord> getPrimaryKey() {
        return Internal.createUniqueKey(raubach.sgud.server.database.tables.Types.TYPES, "KEY_types_PRIMARY", raubach.sgud.server.database.tables.Types.TYPES.ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TypesRecord>> getKeys() {
        return Arrays.<UniqueKey<TypesRecord>>asList(
              Internal.createUniqueKey(raubach.sgud.server.database.tables.Types.TYPES, "KEY_types_PRIMARY", raubach.sgud.server.database.tables.Types.TYPES.ID)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Types as(String alias) {
        return new Types(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Types as(Name alias) {
        return new Types(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Types rename(String name) {
        return new Types(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Types rename(Name name) {
        return new Types(name, null);
    }
}
