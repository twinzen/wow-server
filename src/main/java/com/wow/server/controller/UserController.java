package com.wow.server.controller;

import com.wow.server.data.model.User;
import com.wow.server.data.repository.UserRepository;
import com.wow.server.dto.UserDTO;
import com.wow.server.dto.UserMinimalDTO;
import com.wow.server.exception.DataNotFoundException;
import com.wow.server.mapper.UserMapper;
import com.wow.server.mapper.UserMinimalMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users API")
public class UserController {

    private final UserRepository userRepository;
    private final UserMinimalMapper userMinimalMapper;
    private final UserMapper userMapper;

    @Autowired
    public UserController(
            UserRepository userRepository,
            UserMinimalMapper userMinimalMapper,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMinimalMapper = userMinimalMapper;
        this.userMapper = userMapper;
    }

    @GetMapping
    @Operation(summary = "Returns list of all users with minimally required set of fields")
    public List<UserMinimalDTO> getUsers() {
        return userMinimalMapper.toUserMinimalDTOs(userRepository.finAllMinimal());
    }

    @GetMapping("/{userId:\\d+}")
    @Operation(summary = "Returns detailed user representation by userID")
    public UserDTO getUserById(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            String message = String.format("User with ID %s does not exist", userId);
            log.error(message);
            throw new DataNotFoundException(message);
        }

        return userMapper.toUserDTO(user.get());
    }

}
