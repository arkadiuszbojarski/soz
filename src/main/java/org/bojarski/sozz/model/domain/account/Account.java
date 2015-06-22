package org.bojarski.sozz.model.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bojarski.sozz.model.views.View.BasicView;
import org.bojarski.sozz.model.views.View.ExtendedView;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.MoreObjects;
import com.mysema.query.annotations.QueryEntity;

@QueryEntity
@Entity
@Table(name = "account")
public class Account {
    
    @JsonView(BasicView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    
    @JsonView(BasicView.class)
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @JsonView(BasicView.class)
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;
    
    @JsonView(ExtendedView.class)
    @Column(name = "enabled", nullable = false)
    private boolean enabled = false;
    
    @JsonView(ExtendedView.class)
    @Column(name = "locked", nullable = false)
    private boolean locked = false;
    
    @JsonView(BasicView.class)
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }
    
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("username", username)
                .add("email", email.replaceFirst("@.*", "***"))
                .add("password", "...")
                .toString();
    }

}


