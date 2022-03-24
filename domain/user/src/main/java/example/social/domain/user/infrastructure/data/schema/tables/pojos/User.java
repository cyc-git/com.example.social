/*
 * This file is generated by jOOQ.
 */
package example.social.domain.user.infrastructure.data.schema.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long   id;
    private String name;
    private String account;
    private Long   createdAt;
    private Long   updatedAt;

    public User() {}

    public User(User value) {
        this.id = value.id;
        this.name = value.name;
        this.account = value.account;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public User(
        Long   id,
        String name,
        String account,
        Long   createdAt,
        Long   updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Getter for <code>test.user.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>test.user.id</code>.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>test.user.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>test.user.name</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>test.user.account</code>.
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * Setter for <code>test.user.account</code>.
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Getter for <code>test.user.created_at</code>.
     */
    public Long getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>test.user.created_at</code>.
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Getter for <code>test.user.updated_at</code>.
     */
    public Long getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>test.user.updated_at</code>.
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(account);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}
