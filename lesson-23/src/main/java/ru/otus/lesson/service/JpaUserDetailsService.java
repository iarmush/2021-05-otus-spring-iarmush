package ru.otus.lesson.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.User;
import ru.otus.lesson.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByName(name);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User with name: " + name + " not found"));

        return new org.springframework.security.core.userdetails.User(
            user.getName(), passwordEncoder.encode(new String(user.getPassword())),
            AuthorityUtils.createAuthorityList(user.getAuthority()));
    }
}
