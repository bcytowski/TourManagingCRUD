package pl.sda.javagda28.tourmanagingcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.javagda28.tourmanagingcrud.entity.User;
import pl.sda.javagda28.tourmanagingcrud.model.UserForm;
import pl.sda.javagda28.tourmanagingcrud.service.RoleService;
import pl.sda.javagda28.tourmanagingcrud.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final String MODEL_USERS_ATTRIBUTE = "users";
    private static final String MODEL_USER_FORM = "userForm";

    private static final String USERS_LIST_TEMPLATE_PATH = "users-list";

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public String displayUsers(final ModelMap modelMap) {
        final List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(user -> user.setNonAssignedRoles(roleService.getUnassignedRoles(user)));
        modelMap.addAttribute(MODEL_USERS_ATTRIBUTE, allUsers);
        modelMap.addAttribute(MODEL_USER_FORM, new UserForm());
        return USERS_LIST_TEMPLATE_PATH;
    }
}
