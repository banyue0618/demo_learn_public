package com.example.demo.entity.system;

import java.util.List;

/**
 * @author banyue
 * date 2020-06-01
 */

public class AccountEntity extends User {

    private String roles;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
