package com.epam.training.backend_services.authdemo.service;

import com.epam.training.backend_services.authdemo.domain.model.User;
import com.epam.training.backend_services.authdemo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findOneByEmail(email);

        return user.map(
                usr -> new org.springframework.security.core.userdetails.User(usr.getEmail(),
                        usr.getPassword(),
                        Stream.of(usr.getRoles().split(","))
                                .map(rl -> "ROLE_"+rl)
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toUnmodifiableList())
        )).orElseThrow(() -> new UsernameNotFoundException("Invalid email or password."));
    }
}
