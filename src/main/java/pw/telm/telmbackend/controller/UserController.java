package pw.telm.telmbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.telm.telmbackend.dto.LoginDto;
import pw.telm.telmbackend.dto.OtpDto;
import pw.telm.telmbackend.dto.RegisterDto;
import pw.telm.telmbackend.dto.UserDto;
import pw.telm.telmbackend.security.UserAuthProvider;
import pw.telm.telmbackend.service.UserService;

import java.util.Arrays;

/**
 * REST controller for handling user authentication and registration.
 * Provides endpoints for user login and registration, returning user details with JWT tokens.
 */
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    public UserController(UserService userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {
        UserDto user = userService.login(loginDto);
        userService.generateAndSendOtp(user.getLogin());

        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        userService.register(registerDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpDto otpDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Integer login = userAuthProvider.getLoginFromToken(token);

        boolean valid = userService.verifyOtp(login, String.valueOf(otpDto.otp()));

        if (!valid) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }

        return ResponseEntity.ok("OTP");
    }
}