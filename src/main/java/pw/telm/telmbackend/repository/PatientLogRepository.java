package pw.telm.telmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pw.telm.telmbackend.model.PatientLog;

import java.util.Optional;


public interface PatientLogRepository extends JpaRepository<PatientLog, Integer> {
    boolean existsByLogin(Integer login);

    @Query("SELECT p.login FROM PatientLog p WHERE p.patient.idPatient = :patientId")
    Integer getLoginById(int patientId);
}
