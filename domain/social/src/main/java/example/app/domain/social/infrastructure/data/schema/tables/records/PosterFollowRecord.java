/*
 * This file is generated by jOOQ.
 */
package example.app.domain.social.infrastructure.data.schema.tables.records;


import example.app.domain.social.infrastructure.data.schema.tables.PosterFollow;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PosterFollowRecord extends UpdatableRecordImpl<PosterFollowRecord> implements Record3<Long, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>test.poster_follow.poster_id</code>. the poster id who was been followed
     */
    public void setPosterId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>test.poster_follow.poster_id</code>. the poster id who was been followed
     */
    public Long getPosterId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>test.poster_follow.followed_by</code>. the poster id of the follower
     */
    public void setFollowedBy(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>test.poster_follow.followed_by</code>. the poster id of the follower
     */
    public Long getFollowedBy() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>test.poster_follow.followed_at</code>. the followed time
     */
    public void setFollowedAt(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>test.poster_follow.followed_at</code>. the followed time
     */
    public Long getFollowedAt() {
        return (Long) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, Long, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return PosterFollow.POSTER_FOLLOW.POSTER_ID;
    }

    @Override
    public Field<Long> field2() {
        return PosterFollow.POSTER_FOLLOW.FOLLOWED_BY;
    }

    @Override
    public Field<Long> field3() {
        return PosterFollow.POSTER_FOLLOW.FOLLOWED_AT;
    }

    @Override
    public Long component1() {
        return getPosterId();
    }

    @Override
    public Long component2() {
        return getFollowedBy();
    }

    @Override
    public Long component3() {
        return getFollowedAt();
    }

    @Override
    public Long value1() {
        return getPosterId();
    }

    @Override
    public Long value2() {
        return getFollowedBy();
    }

    @Override
    public Long value3() {
        return getFollowedAt();
    }

    @Override
    public PosterFollowRecord value1(Long value) {
        setPosterId(value);
        return this;
    }

    @Override
    public PosterFollowRecord value2(Long value) {
        setFollowedBy(value);
        return this;
    }

    @Override
    public PosterFollowRecord value3(Long value) {
        setFollowedAt(value);
        return this;
    }

    @Override
    public PosterFollowRecord values(Long value1, Long value2, Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PosterFollowRecord
     */
    public PosterFollowRecord() {
        super(PosterFollow.POSTER_FOLLOW);
    }

    /**
     * Create a detached, initialised PosterFollowRecord
     */
    public PosterFollowRecord(Long posterId, Long followedBy, Long followedAt) {
        super(PosterFollow.POSTER_FOLLOW);

        setPosterId(posterId);
        setFollowedBy(followedBy);
        setFollowedAt(followedAt);
    }
}
