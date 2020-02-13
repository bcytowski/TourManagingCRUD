package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.javagda28.tourmanagingcrud.entity.User;
import pl.sda.javagda28.tourmanagingcrud.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
