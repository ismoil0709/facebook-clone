package uz.pdp.facebookapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.entity.UserRole;
import uz.pdp.facebookapp.exception.NotFoundException;
import uz.pdp.facebookapp.exception.NullOrEmptyException;
import uz.pdp.facebookapp.repository.UserRoleRepository;
import uz.pdp.facebookapp.service.UserRoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    @Override
    public void save(String name) {
        if (name == null || name.isEmpty() || name.isBlank())
            throw new NullOrEmptyException("Name");
        userRoleRepository.save(UserRole.builder().name(name).build());
    }

    @Override
    public void update(Long id, String name) {
        if (id == null || id < 0)
            throw new NullOrEmptyException("Id");
        if (name == null || name.isEmpty() || name.isBlank())
            throw new NullOrEmptyException("Name");
        if (userRoleRepository.findById(id).isEmpty())
            throw new NotFoundException("UserRole");
        userRoleRepository.save(UserRole.builder().name(name).id(id).build());
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        if (userRoleRepository.findById(id).isEmpty())
            throw new NotFoundException("UserRole");
        userRoleRepository.deleteById(id);
    }

    @Override
    public UserRole getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return userRoleRepository.findById(id).orElseThrow(() -> new NotFoundException("UserRole"));
    }

    @Override
    public List<UserRole> getAll() {
        List<UserRole> all = userRoleRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("UserRoles");
        return all;
    }

    @Override
    public UserRole getByName(String name) {
        if (name == null || name.isEmpty() || name.isBlank())
            throw new NullOrEmptyException("Name");
        return userRoleRepository.findByName(name).orElseThrow(() -> new NotFoundException("UserRole"));
    }
}
