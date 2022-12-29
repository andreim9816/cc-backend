package com.example.banking.service;

import com.example.banking.dto.request.RegisterDto;
import com.example.banking.exception.CustomException;
import com.example.banking.mapper.Mapper;
import com.example.banking.repository.UserRepository;
import com.example.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;

    public User registerUser(RegisterDto body) throws CustomException {
        isEmailUnique(body.getEmail());
        isPhoneUnique(body.getPhoneNumber());
        isUsernameUnique(body.getUsername());

        User entity = mapper.toEntity(body);
        entity.setPassword(passwordEncoder.encode(body.getPassword()));

        return userRepository.save(entity);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    public void isEmailUnique(String email) throws CustomException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new CustomException("Email already exists!");
        }
    }

    public void isPhoneUnique(String phone) throws CustomException {
        if (userRepository.findByPhoneNumber(phone).isPresent()) {
            throw new CustomException("Phone number already exists!");
        }
    }

    public void isUsernameUnique(String username) throws CustomException {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new CustomException("Username already exists!");
        }
    }
}
