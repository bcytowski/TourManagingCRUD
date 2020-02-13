package pl.sda.javagda28.tourmanagingcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sda.javagda28.tourmanagingcrud.entity.User;
import pl.sda.javagda28.tourmanagingcrud.model.UserForm;
import pl.sda.javagda28.tourmanagingcrud.service.RoleService;
import pl.sda.javagda28.tourmanagingcrud.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final String MODEL_USERS_ATTRIBUTE = "users";
    private static final String MODEL_USER_FORM = "userForm";

    private static final String USERS_LIST_TEMPLATE_PATH = "users-list";
    private static final String USERS_EDIT_TEMPLATE_PATH = "users-edit";

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

    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/add")
    public String saveUser(@Valid @ModelAttribute final UserForm userForm, final ModelMap modelMap) {
        userService.createUser(userForm);
        return displayUsers(modelMap);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/remove/{id}")
    public String deleteUser(@PathVariable("id") final Long id, final ModelMap modelMap) {
        userService.deleteUser(id);
        return displayUsers(modelMap);
//    return "redirect:users";
//    return new RedirectView(USERS_TEMPLATE_PATH);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("{id}/roles/{rolename}")
    public String addRoleToUser(@PathVariable("id") final Long id,
                                @PathVariable("rolename") final String rolename,
                                final ModelMap modelMap) {
        userService.addRoleToUser(id, rolename);
        return displayUsers(modelMap);
    }

//    @GetMapping("/edit/{id}")
//    public String showEditPage(@PathVariable("id") final Long id, final ModelMap modelMap) {
//        modelMap.addAttribute(MODEL_USER_FORM, UserForm.builder().username(username).build());
//        return USERS_EDIT_TEMPLATE_PATH;
//    }

//    @Secured("ROLE_ADMIN")
//    @PostMapping("/edit/{username}")
//    public String updateUser(@Valid @ModelAttribute final UserForm userForm,
//                             @PathVariable("username") final String username,
//                             final ModelMap modelMap) {
//        userService.updateUser(userForm, username);
//        return displayUsers(modelMap);
//    }
}
