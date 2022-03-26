/*
 * This file is generated by jOOQ.
 */
package example.app.domain.social.infrastructure.data.schema.tables.records;


import example.app.domain.social.infrastructure.data.schema.tables.Poster;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PosterRecord extends UpdatableRecordImpl<PosterRecord> implements Record4<Long, String, String, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>test.poster.id</code>. id
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>test.poster.id</code>. id
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>test.poster.name</code>. poster name
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>test.poster.name</code>. poster name
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>test.poster.account</code>. poster account
     */
    public void setAccount(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>test.poster.account</code>. poster account
     */
    public String getAccount() {
        return (String) get(2);
    }

    /**
     * Setter for <code>test.poster.deleted_at</code>. the time when the poster was deleted, may be null, means the poster not been deleted yet.
     */
    public void setDeletedAt(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>test.poster.deleted_at</code>. the time when the poster was deleted, may be null, means the poster not been deleted yet.
     */
    public Long getDeletedAt() {
        return (Long) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, String, Long> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, String, String, Long> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Poster.POSTER.ID;
    }

    @Override
    public Field<String> field2() {
        return Poster.POSTER.NAME;
    }

    @Override
    public Field<String> field3() {
        return Poster.POSTER.ACCOUNT;
    }

    @Override
    public Field<Long> field4() {
        return Poster.POSTER.DELETED_AT;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getAccount();
    }

    @Override
    public Long component4() {
        return getDeletedAt();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getAccount();
    }

    @Override
    public Long value4() {
        return getDeletedAt();
    }

    @Override
    public PosterRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public PosterRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public PosterRecord value3(String value) {
        setAccount(value);
        return this;
    }

    @Override
    public PosterRecord value4(Long value) {
        setDeletedAt(value);
        return this;
    }

    @Override
    public PosterRecord values(Long value1, String value2, String value3, Long value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PosterRecord
     */
    public PosterRecord() {
        super(Poster.POSTER);
    }

    /**
     * Create a detached, initialised PosterRecord
     */
    public PosterRecord(Long id, String name, String account, Long deletedAt) {
        super(Poster.POSTER);

        setId(id);
        setName(name);
        setAccount(account);
        setDeletedAt(deletedAt);
    }
}
