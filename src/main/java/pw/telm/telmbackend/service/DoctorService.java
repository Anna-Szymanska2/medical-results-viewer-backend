package pw.telm.telmbackend.service;
import pw.telm.telmbackend.DTOs.model.PatientDto;
import java.util.List;

public interface DoctorService {
    public void createDoctorWithLog(String name);

    List<String> getAllDoctors();

    List<PatientDto> getPatientsByDoctorId(Integer id);
}
