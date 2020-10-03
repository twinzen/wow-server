package com.wow.server.api;

import com.wow.server.data.model.User;
import com.wow.server.data.repository.UserRepository;
import com.wow.server.dto.UserDTO;
import com.wow.server.dto.UserMinimalDTO;
import com.wow.server.exception.DataNotFoundException;
import com.wow.server.user.following.FollowerInformationDTO;
import com.wow.server.user.following.FollowerInformationMapper;
import com.wow.server.mapper.UserMapper;
import com.wow.server.mapper.UserMinimalMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserMinimalMapper userMinimalMapper;
    private final UserMapper userMapper;
    private final FollowerInformationMapper followerInformationMapper;

    @GetMapping
    @Operation(summary = "Returns list of all users with minimally required set of fields")
    public List<UserMinimalDTO> getUsers() {
        return userMinimalMapper.toUserMinimalDTOs(userRepository.finAllMinimal());
    }

    @GetMapping("/{userId:\\d+}")
    @Operation(summary = "Returns detailed user representation by userID")
    public UserDTO getUserById(@PathVariable Long userId) {

        return userMapper.toUserDTO(retrieveUser(userId));
    }

    @GetMapping("/{userId:\\d+}/following")
    @Operation(summary = "Returns list of users followed by user with specified userId")
    public List<FollowerInformationDTO>  getFollowedUsersOf(@PathVariable Long userId) {
        return followerInformationMapper.toFollowerInformationDTOs(retrieveUser(userId).getFollowing());

    }


    @GetMapping("/{userId:\\d+}/followers")
    @Operation(summary = "Returns list of users following specified userId")
    public List<FollowerInformationDTO>  getFollowersOfUser(@PathVariable Long userId) {
        return followerInformationMapper.toFollowerInformationDTOs(retrieveUser(userId).getFollowers());

    }


    private User retrieveUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            String message = String.format("User with ID %s does not exist", userId);
            log.error(message);
            throw new DataNotFoundException(message);
        }
        return user.get();
    }
}
