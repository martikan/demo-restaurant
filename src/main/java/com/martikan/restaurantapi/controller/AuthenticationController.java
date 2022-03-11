package com.martikan.restaurantapi.controller;

import com.martikan.restaurantapi.dto.authentication.SignUpDTO;
import com.martikan.restaurantapi.dto.user.UserDTO;
import com.martikan.restaurantapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Authentication controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    /**
     * Endpoint for Sign-up action.
     * @param signUpRequest {@link SignUpDTO}
     * @return {@link UserDTO}
     */
    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody final SignUpDTO signUpRequest) {
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }

}
