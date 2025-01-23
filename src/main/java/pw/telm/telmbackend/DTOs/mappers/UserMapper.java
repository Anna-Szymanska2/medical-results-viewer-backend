package pw.telm.telmbackend.DTOs.mappers;

import pw.telm.telmbackend.DTOs.model.UserDto;
import pw.telm.telmbackend.model.DoctorLog;
import pw.telm.telmbackend.model.PatientLog;

public class UserMapper {


    public static UserDto patientLogToUserDto (PatientLog patientLog) {
        UserDto userDto = new UserDto();
        userDto.setLogin(patientLog.getLogin());
        userDto.setRole("Patient");
        userDto.setId(patientLog.getId_patient());
        return userDto;
    }


    public static UserDto doctorLogToUserDto (DoctorLog doctorLog) {
        UserDto userDto = new UserDto();
        userDto.setLogin(doctorLog.getLogin());
        userDto.setRole("Doctor");
        userDto.setId(doctorLog.getId_doctor());
        return userDto;
    }
}

