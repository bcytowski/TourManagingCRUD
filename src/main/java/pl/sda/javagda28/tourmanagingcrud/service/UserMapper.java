package pl.sda.javagda28.tourmanagingcrud.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.javagda28.tourmanagingcrud.entity.User;
import pl.sda.javagda28.tourmanagingcrud.dto.UserForm;


import java.util.List;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User userFormToUser(final UserForm userForm) {
        return new User(null, userForm.getUsername(), userForm.getEmail(),
                passwordEncoder.encode(userForm.getPassword()), List.of(), List.of());
    }

    public UserForm userToUserForm(final User user) {
        return new UserForm(user.getUsername(), user.getEmail(), null, null,  user.getRoles() );
    }
}
