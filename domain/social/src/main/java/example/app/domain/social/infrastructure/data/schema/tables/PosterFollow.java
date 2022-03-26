/*
 * This file is generated by jOOQ.
 */
package example.app.domain.social.infrastructure.data.schema.tables;


import example.app.domain.social.infrastructure.data.schema.Keys;
import example.app.domain.social.infrastructure.data.schema.Test;
import example.app.domain.social.infrastructure.data.schema.tables.records.PosterFollowRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PosterFollow extends TableImpl<PosterFollowRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>test.poster_follow</code>
     */
    public static final PosterFollow POSTER_FOLLOW = new PosterFollow();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PosterFollowRecord> getRecordType() {
        return PosterFollowRecord.class;
    }

    /**
     * The column <code>test.poster_follow.poster_id</code>. the poster id who was been followed
     */
    public final TableField<PosterFollowRecord, Long> POSTER_ID = createField(DSL.name("poster_id"), SQLDataType.BIGINT.nullable(false), this, "the poster id who was been followed");

    /**
     * The column <code>test.poster_follow.followed_by</code>. the poster id of the follower
     */
    public final TableField<PosterFollowRecord, Long> FOLLOWED_BY = createField(DSL.name("followed_by"), SQLDataType.BIGINT.nullable(false), this, "the poster id of the follower");

    /**
     * The column <code>test.poster_follow.followed_at</code>. the followed time
     */
    public final TableField<PosterFollowRecord, Long> FOLLOWED_AT = createField(DSL.name("followed_at"), SQLDataType.BIGINT.nullable(false), this, "the followed time");

    private PosterFollow(Name alias, Table<PosterFollowRecord> aliased) {
        this(alias, aliased, null);
    }

    private PosterFollow(Name alias, Table<PosterFollowRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>test.poster_follow</code> table reference
     */
    public PosterFollow(String alias) {
        this(DSL.name(alias), POSTER_FOLLOW);
    }

    /**
     * Create an aliased <code>test.poster_follow</code> table reference
     */
    public PosterFollow(Name alias) {
        this(alias, POSTER_FOLLOW);
    }

    /**
     * Create a <code>test.poster_follow</code> table reference
     */
    public PosterFollow() {
        this(DSL.name("poster_follow"), null);
    }

    public <O extends Record> PosterFollow(Table<O> child, ForeignKey<O, PosterFollowRecord> key) {
        super(child, key, POSTER_FOLLOW);
    }

    @Override
    public Schema getSchema() {
        return Test.TEST;
    }

    @Override
    public UniqueKey<PosterFollowRecord> getPrimaryKey() {
        return Keys.KEY_POSTER_FOLLOW_PRIMARY;
    }

    @Override
    public List<UniqueKey<PosterFollowRecord>> getKeys() {
        return Arrays.<UniqueKey<PosterFollowRecord>>asList(Keys.KEY_POSTER_FOLLOW_PRIMARY);
    }

    @Override
    public List<ForeignKey<PosterFollowRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PosterFollowRecord, ?>>asList(Keys.POSTER_FOLLOWER_FK, Keys.POSTER_FOLLOWER_FK_1);
    }

    private transient Poster _posterFollowerFk;
    private transient Poster _posterFollowerFk_1;

    public Poster posterFollowerFk() {
        if (_posterFollowerFk == null)
            _posterFollowerFk = new Poster(this, Keys.POSTER_FOLLOWER_FK);

        return _posterFollowerFk;
    }

    public Poster posterFollowerFk_1() {
        if (_posterFollowerFk_1 == null)
            _posterFollowerFk_1 = new Poster(this, Keys.POSTER_FOLLOWER_FK_1);

        return _posterFollowerFk_1;
    }

    @Override
    public PosterFollow as(String alias) {
        return new PosterFollow(DSL.name(alias), this);
    }

    @Override
    public PosterFollow as(Name alias) {
        return new PosterFollow(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PosterFollow rename(String name) {
        return new PosterFollow(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PosterFollow rename(Name name) {
        return new PosterFollow(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
