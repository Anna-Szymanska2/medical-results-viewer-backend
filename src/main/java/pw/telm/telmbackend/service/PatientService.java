package pw.telm.telmbackend.service;

import org.springframework.transaction.annotation.Transactional;

public interface PatientService {
    @Transactional
    public void parseAndSaveStudy(String filePath, String patientName);
}
