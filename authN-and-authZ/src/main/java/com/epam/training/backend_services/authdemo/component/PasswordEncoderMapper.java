package com.epam.training.backend_services.authdemo.component;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {
    private final PasswordEncoder passwordEncoder;

    @Named("encodePassword")
    public String encode(CharSequence value) {
        return passwordEncoder.encode(value);
    }
}
