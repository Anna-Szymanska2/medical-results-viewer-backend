package pw.telm.telmbackend.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pw.telm.telmbackend.dto.*;
import pw.telm.telmbackend.exeption.AppException;
import pw.telm.telmbackend.mail.MailService;
import pw.telm.telmbackend.model.DoctorLog;
import pw.telm.telmbackend.model.PatientLog;
import pw.telm.telmbackend.repository.DoctorLogRepository;
import pw.telm.telmbackend.repository.PatientLogRepository;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final PatientLogRepository patientLogRepository;
    private final DoctorLogRepository doctorLogRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;


    public UserServiceImpl(PatientLogRepository patientLogRepository,
                           DoctorLogRepository doctorLogRepository,
                           PasswordEncoder passwordEncoder, MailService mailService) {
        this.patientLogRepository = patientLogRepository;
        this.doctorLogRepository = doctorLogRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public UserDto login(LoginDto loginDto) {
        Optional<DoctorLog> doctor = doctorLogRepository.findByLogin(loginDto.login());
        if (doctor.isPresent() && passwordEncoder.matches(CharBuffer.wrap(loginDto.password()), doctor.get().getPassword())) {
            UserDto user = UserMapper.doctorLogToUserDto(doctor.get());

            generateAndSendOtp(user.getLogin());
            return user;
        }
        Optional<PatientLog> patient = patientLogRepository.findByLogin(loginDto.login());
        if (patient.isPresent() && passwordEncoder.matches(CharBuffer.wrap(loginDto.password()), patient.get().getPassword())) {
            UserDto user = UserMapper.patientLogToUserDto(patient.get());

            generateAndSendOtp(user.getLogin());
            return user;
        }

        throw new AppException("Invalid login or password", HttpStatus.BAD_REQUEST);
    }
    @Override
    public UserDto register(RegisterDto registerDto) {
        Optional<DoctorLog> doctor = doctorLogRepository.findByLogin(registerDto.login());

        if (doctor.isPresent()) {
            doctorLogRepository.updatePasswordByLogin(passwordEncoder.encode(CharBuffer.wrap(registerDto.password())), doctor.get().getLogin());
            doctorLogRepository.updateEmailByLogin(registerDto.email(), doctor.get().getLogin());
            DoctorLog updatedDoctor = doctorLogRepository.findByLogin(registerDto.login()).get();
            return UserMapper.doctorLogToUserDto(updatedDoctor);
        }

        Optional<PatientLog> patient = patientLogRepository.findByLogin(registerDto.login());
        if (patient.isPresent()) {
            patientLogRepository.updatePasswordByLogin(passwordEncoder.encode(CharBuffer.wrap(registerDto.password())), patient.get().getLogin());
            System.out.println(registerDto.email());
            System.out.println(registerDto.login());

            patientLogRepository.updateEmailByLogin(registerDto.email(), patient.get().getLogin());
            PatientLog updatedPatient = patientLogRepository.findByLogin(registerDto.login()).get();
            return UserMapper.patientLogToUserDto(updatedPatient);
        }

        throw new AppException("Użytkownik o takim kodzie nie znajduje się bazie danych", HttpStatus.BAD_REQUEST);
    }


    @Override
    public void generateAndSendOtp(Integer login) {
        String otp = String.format("%06d", new Random().nextInt(999999));

        Optional<DoctorLog> doctor = doctorLogRepository.findByLogin(login);
        if (doctor.isPresent()) {
            doctor.get().setOtpCode(otp); // Zapisz OTP
            doctorLogRepository.save(doctor.get());
            mailService.sendEmail(doctor.get().getEmail(),"OTP", otp);
            return;
        }

        Optional<PatientLog> patient = patientLogRepository.findByLogin(login);
        if (patient.isPresent()) {
            patient.get().setOtpCode(otp); // Zapisz OTP
            patientLogRepository.save(patient.get());
            mailService.sendEmail(patient.get().getEmail(),"OTP", otp);
            return;
        }

        throw new AppException("User not found", HttpStatus.BAD_REQUEST);
    }
    @Override
    public boolean verifyOtp(Integer login, String otp) {
        Optional<DoctorLog> doctor = doctorLogRepository.findByLogin(login);
        if (doctor.isPresent() && otp.equals(doctor.get().getOtp())) {
            doctor.get().setOtpCode(null);
            doctorLogRepository.save(doctor.get());
            return true;
        }

        Optional<PatientLog> patient = patientLogRepository.findByLogin(login);
        if (patient.isPresent() && otp.equals(patient.get().getOtp())) {
            patient.get().setOtpCode(null);
            patientLogRepository.save(patient.get());
            return true;
        }

        return false;
    }
}

