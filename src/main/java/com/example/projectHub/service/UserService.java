package com.example.projectHub.service;

import com.example.projectHub.data.entity.User;
import com.example.projectHub.data.jpa.UserRepository;
import com.example.projectHub.dto.UserDTO;
import com.example.projectHub.util.DtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    public UserDTO getCurrentUser() {
        return new UserDTO(
                1L,
                "F I O",
                "Description",
                Timestamp.from(Instant.now())
        );
    }

    public UserDTO getUserDtoById(Long userId) {
        return dtoMapper.mapUserToUserDTOResponse(getUserById(userId));
    }

    public User findUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findUserByUsernameAndPassword(username, password);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new RuntimeException("User is not presented");
        }
        return user;
    }

    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new RuntimeException("User is not presented");
        }
        return user;
    }

    public User register(String username, String password) {
        User user = new User()
                .setUsername(username)
                .setPassword(password)
                .setDateRegistration(Timestamp.from(Instant.now()));
        userRepository.save(user);
        return user;
    }

    public UserDTO login(String username, String password) {
        Optional<User> optionalUser = userRepository.findUserByUsernameAndPassword(username, password);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new RuntimeException("User is not presented");
        }
        return dtoMapper.mapUserToUserDTOResponse(user);
    }

    public List<UserDTO> search(String userFullName) {
        return userRepository.findAllByFullNameContainingIgnoreCase(userFullName).stream()
                .map(dtoMapper::mapUserToUserDTOResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO update(UserDTO dto) {
        User user = getUserById(dto.getId());
        user.setBio(dto.getBio());
        user.setFullName(dto.getFullName());
        return dtoMapper.mapUserToUserDTOResponse(user);
    }
}
