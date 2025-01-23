package pw.telm.telmbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.telm.telmbackend.DTOs.model.LoginDto;
import pw.telm.telmbackend.DTOs.model.OtpDto;
import pw.telm.telmbackend.DTOs.model.RegisterDto;
import pw.telm.telmbackend.DTOs.model.UserDto;
import pw.telm.telmbackend.security.UserAuthProvider;
import pw.telm.telmbackend.service.UserService;

import java.util.Collections;



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
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        userService.register(registerDto);
        return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully").toString());
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
