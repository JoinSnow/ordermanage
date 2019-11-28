package service;

import dao.RoleDao;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public void add(Role role) {
        role.setId(UUID.randomUUID().toString());
        roleDao.add(role);
    }

    public List<Role> findOtherByUserId(String userId){
        return roleDao.findOtherByUserId(userId);
    }
}
