package pw.telm.telmbackend.service;

import org.springframework.stereotype.Service;
import pw.telm.telmbackend.repository.PatientLogRepository;

@Service
public class PatientLogServiceImpl implements PatientLogService{
    private final PatientLogRepository patientLogRepository;

    public PatientLogServiceImpl(PatientLogRepository patientLogRepository) {
        this.patientLogRepository = patientLogRepository;
    }

    @Override
    public Integer getLoginById(Integer id) {
      return patientLogRepository.getLoginById(id);
    }
}
