/*
 * This file is generated by jOOQ.
 */
package example.social.domain.user.infrastructure.data.schema.tables.records;


import example.social.domain.user.infrastructure.data.schema.tables.User;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record6<Long, String, String, Long, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>test.user.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>test.user.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>test.user.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>test.user.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>test.user.account</code>.
     */
    public void setAccount(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>test.user.account</code>.
     */
    public String getAccount() {
        return (String) get(2);
    }

    /**
     * Setter for <code>test.user.created_at</code>.
     */
    public void setCreatedAt(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>test.user.created_at</code>.
     */
    public Long getCreatedAt() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>test.user.updated_at</code>.
     */
    public void setUpdatedAt(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>test.user.updated_at</code>.
     */
    public Long getUpdatedAt() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>test.user.deleted_at</code>.
     */
    public void setDeletedAt(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>test.user.deleted_at</code>.
     */
    public Long getDeletedAt() {
        return (Long) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, String, String, Long, Long, Long> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Long, String, String, Long, Long, Long> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return User.USER.ID;
    }

    @Override
    public Field<String> field2() {
        return User.USER.NAME;
    }

    @Override
    public Field<String> field3() {
        return User.USER.ACCOUNT;
    }

    @Override
    public Field<Long> field4() {
        return User.USER.CREATED_AT;
    }

    @Override
    public Field<Long> field5() {
        return User.USER.UPDATED_AT;
    }

    @Override
    public Field<Long> field6() {
        return User.USER.DELETED_AT;
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
        return getCreatedAt();
    }

    @Override
    public Long component5() {
        return getUpdatedAt();
    }

    @Override
    public Long component6() {
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
        return getCreatedAt();
    }

    @Override
    public Long value5() {
        return getUpdatedAt();
    }

    @Override
    public Long value6() {
        return getDeletedAt();
    }

    @Override
    public UserRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public UserRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public UserRecord value3(String value) {
        setAccount(value);
        return this;
    }

    @Override
    public UserRecord value4(Long value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public UserRecord value5(Long value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public UserRecord value6(Long value) {
        setDeletedAt(value);
        return this;
    }

    @Override
    public UserRecord values(Long value1, String value2, String value3, Long value4, Long value5, Long value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(Long id, String name, String account, Long createdAt, Long updatedAt, Long deletedAt) {
        super(User.USER);

        setId(id);
        setName(name);
        setAccount(account);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
    }
}
