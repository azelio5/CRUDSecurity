package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
     //   addDefaultRole();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    @Override
    public Role findById(int id) {
        return roleDAO.findById(id);
    }

    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
        return roleDAO.findByIdRoles(roles);
    }

//    @Override
//    public void addDefaultRole() {
//        addRole(new Role(1L,"ROLE_USER"));
//        addRole(new Role(2L,"ROLE_ADMIN"));
//    }
}