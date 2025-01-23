package pw.telm.telmbackend.service;
import pw.telm.telmbackend.DTOs.model.PatientDto;
import pw.telm.telmbackend.model.Doctor;

import java.util.List;

public interface DoctorService {
    public void createDoctorWithLog(String name);

    List<String> getAllDoctors();
    List<Doctor> getAllWholeDoctors();

    List<PatientDto> getPatientsByDoctorId(Integer id);

    Doctor findDoctorById(Integer idDoctor);
}
