package pw.telm.telmbackend.service;
import org.springframework.transaction.annotation.Transactional;
import pw.telm.telmbackend.model.Doctor;

public interface DicomService {
    @Transactional
    public void addDicom(String dicomFilePath, Doctor doctor, int index);
}
