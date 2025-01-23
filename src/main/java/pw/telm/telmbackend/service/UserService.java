package pw.telm.telmbackend.service;

import pw.telm.telmbackend.DTOs.model.LoginDto;
import pw.telm.telmbackend.DTOs.model.RegisterDto;
import pw.telm.telmbackend.DTOs.model.UserDto;

public interface UserService {

    UserDto login(LoginDto credentialsDto);

    UserDto register(RegisterDto registerDto);

    boolean verifyOtp(Integer login, String otp);
    public void generateAndSendOtp(Integer login);
}
