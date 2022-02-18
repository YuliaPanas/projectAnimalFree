package com.example.projectAnimalFree.config;

import com.example.projectAnimalFree.entity.User;
import com.example.projectAnimalFree.entity.UserRole;
import com.example.projectAnimalFree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    //@Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);
        return getByUser(user);
    }


    private Principle getByUser(User user) {
        Set<UserRole> roles = user.getRoles();
        return new Principle(user.getEmail(),user.getPassword(), roles);
    }


}
