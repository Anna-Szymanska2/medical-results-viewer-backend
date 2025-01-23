package pw.telm.telmbackend.service;

import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PatientService {
    @Transactional
    public void parseAndSaveStudy(String filePath, String patientName) throws IOException;

    @Transactional
    public void findOrCreatePatient(String patientName, Integer doctorId);
}
