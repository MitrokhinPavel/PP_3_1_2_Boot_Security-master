package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_of_role")
    private String roleName;

    public Role() {

    }

    public Role(String nameOfRole) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return roleName;
    }

    public void setRole(String nameOfRole) {
        this.roleName = nameOfRole;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

    @Override
    public String toString() {
        return String.format("Role [id = %d; nameOfRole = %s]",
                id, roleName);
    }
}
