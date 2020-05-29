package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javagda28.tourmanagingcrud.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class AppUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;


  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return userRepository.findByUsernameWithRoles(username)
        .map(UserDetailsAdapter::new)
        .orElseThrow();
  }
}
