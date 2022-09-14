package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service()

public class UserDetailsServiceImpl implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = em.createQuery("select u from User u where u.username = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%name' not found", name));
        }
        return user;
    }
}