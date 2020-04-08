package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.javagda28.tourmanagingcrud.entity.Role;
import pl.sda.javagda28.tourmanagingcrud.entity.User;
import pl.sda.javagda28.tourmanagingcrud.exceptions.TourManagingException;
import pl.sda.javagda28.tourmanagingcrud.dto.UserForm;
import pl.sda.javagda28.tourmanagingcrud.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User createUser(final UserForm userForm) {
        final Long usersCountWithDuplicatedData
                = userRepository.countAllByUsernameOrEmail(userForm.getUsername(), userForm.getEmail());
        if (usersCountWithDuplicatedData > 0) {
            throw new TourManagingException("User with same username or email already exists");
        }
        final User user = userMapper.userFormToUser(userForm);
        return userRepository.save(user);
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public void addRoleToUser(final Long id, final String roleName) {
        final Role role = roleService.getByName(roleName);
        final Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            if (!user.getRoles().contains(role)) {
                user.getRoles().add(role);
                userRepository.save(user);
            }
        }
    }

    public UserForm createUserFormById(final Long id){
        User user = findSpecificUserById(id);

        return userMapper.userToUserForm(user);
    }

    public void updateUser(final UserForm userForm, final Long id) {
        User user = findSpecificUserById(id);
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setUserImage(userForm.getUserImage());
        userRepository.save(user);
    }

    public User findSpecificUserById(final Long id){
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new TourManagingException("Cannot update non existing user"));
        return user;
    }
}
