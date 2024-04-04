package org.example.todoapp.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.example.todoapp.Role;

@Entity
@JsonIgnoreProperties(value = {"password"})
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username", name = "uk_username"))
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String username;
    @Column
    private String name;
    @Column
    private String password;
    @Column(name = "role", length = 20, columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(){}

    public User(String username, String name, String password, Role role) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", name='" + name + '\'' +
               ", password='" + password + '\'' +
               '}';
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

}
