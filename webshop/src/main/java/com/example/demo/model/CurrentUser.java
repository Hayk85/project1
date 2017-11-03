package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * Created by Hayk on 02.09.2017.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private User user;


    public CurrentUser(User user){
        super(user.getEmail(),user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
    }
    public User getUser() {
        return user;
    }

    public Long getId(){return user.getId();}
    public Role getRole(){return  user.getRole();}


    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                '}';
    }

}
