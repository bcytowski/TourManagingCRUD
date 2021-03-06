package pl.sda.javagda28.tourmanagingcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.javagda28.tourmanagingcrud.dto.UserForm;
import pl.sda.javagda28.tourmanagingcrud.entity.Role;
import pl.sda.javagda28.tourmanagingcrud.entity.User;
import pl.sda.javagda28.tourmanagingcrud.service.RoleService;
import pl.sda.javagda28.tourmanagingcrud.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final String MODEL_USERS_ATTRIBUTE = "users";
    private static final String MODEL_USER_FORM_ATTRIBUTE = "userForm";
    private static final String MODEL_ROLES_ATTRIBUTE = "roles";
    private static final String MODEL_USER_ATTRIBUTE = "user";

    private static final String USERS_LIST_TEMPLATE_PATH = "users-list";
    private static final String USERS_EDIT_TEMPLATE_PATH = "users-edit";
    private static final String USER_FORM_TEMPLATE_PATH = "user-form";

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public String displayUsers(final ModelMap modelMap) {
        final List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(user -> user.setNonAssignedRoles(roleService.getUnassignedRoles(user)));
        modelMap.addAttribute(MODEL_USERS_ATTRIBUTE, allUsers);

        return USERS_LIST_TEMPLATE_PATH;
    }

    @GetMapping("/add")
    public String viewUserForm(final ModelMap modelMap) {
        List<User> allUsers = userService.getAllUsers();
        List<Role> allRoles = roleService.getAllRoles();
        modelMap.addAttribute(MODEL_USERS_ATTRIBUTE, allUsers);
        modelMap.addAttribute(MODEL_ROLES_ATTRIBUTE, allRoles);
        modelMap.addAttribute(MODEL_USER_FORM_ATTRIBUTE, new UserForm());
        return USER_FORM_TEMPLATE_PATH;
    }


    @PostMapping(path = "/add")
    public String saveUser(@Valid @ModelAttribute final UserForm userForm) {
        userService.createUser(userForm);
        return "redirect:/";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/remove/{id}")
    public String deleteUser(@PathVariable("id") final Long id, final ModelMap modelMap) {
        userService.deleteUser(id);
        return displayUsers(modelMap);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("{id}/roles/{rolename}")
    public String addRoleToUser(@PathVariable("id") final Long id,
                                @PathVariable("rolename") final String rolename,
                                final ModelMap modelMap) {
        userService.addRoleToUser(id, rolename);
        return displayUsers(modelMap);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable("id") final Long id, final ModelMap modelMap) {
        modelMap.addAttribute(MODEL_USER_FORM_ATTRIBUTE, userService.createUserFormById(id));
        modelMap.addAttribute(MODEL_USER_ATTRIBUTE, userService.findSpecificUserById(id));
        return USERS_EDIT_TEMPLATE_PATH;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/edit/{id}")
    public String updateUser(@Valid @ModelAttribute final UserForm userForm,
                             @PathVariable("id") final Long id,
                             final ModelMap modelMap) {
        userService.updateUser(userForm, id);
        return displayUsers(modelMap);
    }
}
