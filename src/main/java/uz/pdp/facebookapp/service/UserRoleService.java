package uz.pdp.facebookapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.entity.UserRole;

import java.util.List;

@Service
public interface UserRoleService {
    void save(String name);
    void update(Long id,String name);
    void delete(Long id);
    UserRole getById(Long id);
    List<UserRole> getAll();
    UserRole getByName(String name);
}
