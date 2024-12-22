package pw.telm.telmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.telm.telmbackend.model.PatientLog;


public interface PatientLogRepository extends JpaRepository<PatientLog, Integer> {
    boolean existsByLogin(Integer login);
}
