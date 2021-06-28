package com.casestudydraft.service;

import com.casestudydraft.model.Role;
import com.casestudydraft.model.User;
import com.casestudydraft.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void save(Role role){
        role.setUpdated_on(new Timestamp(System.currentTimeMillis()));
        roleRepository.save(role);



    }

    public void giveUserRole(User user, Role role){
        //roleRepository
    }

    public List<Role> findByName(String name){
        return roleRepository.findByName(name);
    }

    public AtomicBoolean hasAuthority(Authentication authentication, String authority ){
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
        listAuthorities.addAll(authorities);
        AtomicBoolean hasAuthority = new AtomicBoolean(false);
        authorities.forEach(item-> {
                    if (item.toString().equals(authority)) {
                        hasAuthority.set(true);
                    }
                });
        return hasAuthority;
    }

    public void createAdmin(){
        System.out.println("HERE");
        roleRepository.createAdmin();
    }

    public void setAdmin(){
        System.out.println("HERE");
        roleRepository.setAdmin();
    }
}
