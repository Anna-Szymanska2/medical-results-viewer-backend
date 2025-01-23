package pw.telm.telmbackend.service;

import pw.telm.telmbackend.dto.LoginDto;
import pw.telm.telmbackend.dto.RegisterDto;
import pw.telm.telmbackend.dto.UserDto;

public interface UserService {

    UserDto login(LoginDto credentialsDto);

    UserDto register(RegisterDto registerDto);

    boolean verifyOtp(Integer login, String otp);
    public void generateAndSendOtp(Integer login);
}
