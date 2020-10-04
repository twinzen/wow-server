package com.wow.server.api;

import com.wow.server.exception.DataNotFoundException;
import com.wow.server.holding.HoldingDTO;
import com.wow.server.holding.HoldingMapper;
import com.wow.server.user.UserEntity;
import com.wow.server.user.UserRepository;
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
@RequestMapping("/api/holdings")
@Tag(name = "Holdings API")
@RequiredArgsConstructor
public class HoldingController {

    private final UserRepository userRepository;
    private final HoldingMapper holdingMapper;

    @GetMapping("/{userId:\\d+}")
    @Operation(summary = "Returns Holding list for given user ID")
    public List<HoldingDTO> getHoldingsByUserId(
            @PathVariable(name = "userId") Long userId
    ) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            String message = String.format("User with id %d doesn't exist", userId);
            log.error(message);
            throw new DataNotFoundException(message);
        }

        return holdingMapper.toHoldingDTOs(user.get().getHoldings());
    }

}
