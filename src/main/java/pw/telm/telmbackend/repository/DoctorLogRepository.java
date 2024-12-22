package pw.telm.telmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.telm.telmbackend.model.DoctorLog;

public interface DoctorLogRepository extends JpaRepository<DoctorLog, Integer>  {
    boolean existsByLogin(Integer login);
}
