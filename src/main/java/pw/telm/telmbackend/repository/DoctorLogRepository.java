package pw.telm.telmbackend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pw.telm.telmbackend.model.DoctorLog;

import java.util.Optional;

public interface DoctorLogRepository extends JpaRepository<DoctorLog, Integer>  {
    boolean existsByLogin(Integer login);

    Optional<DoctorLog> findByLogin(Integer login);

    @Modifying
    @Transactional
    @Query("UPDATE DoctorLog SET password = :newPassword WHERE login = :login")
    int updatePasswordByLogin(String newPassword, Integer login);

}
