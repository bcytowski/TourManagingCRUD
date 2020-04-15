package pl.sda.javagda28.tourmanagingcrud.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.javagda28.tourmanagingcrud.entity.Role;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    void getAllRoles() {
        List<Role> result = roleService.getAllRoles();

        assertEquals(3, result.size());
    }
}
