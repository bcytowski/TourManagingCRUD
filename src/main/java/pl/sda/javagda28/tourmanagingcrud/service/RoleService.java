package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javagda28.tourmanagingcrud.entity.Role;
import pl.sda.javagda28.tourmanagingcrud.entity.User;
import pl.sda.javagda28.tourmanagingcrud.exceptions.TourManagingException;
import pl.sda.javagda28.tourmanagingcrud.repository.RoleRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public List<Role> getUnassignedRoles(final User user) {
        final List<Role> allRoles = roleRepository.findAll();
        allRoles.removeAll(user.getRoles());
        return allRoles;
    }


    public Role getByName(final String roleName) {
        return roleRepository.findById(roleName)
                .orElseThrow(() -> new TourManagingException("Role with name " + roleName + " does not exist"));
    }
}
