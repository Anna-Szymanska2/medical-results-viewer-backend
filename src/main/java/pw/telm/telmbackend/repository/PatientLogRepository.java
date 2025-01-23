package pw.telm.telmbackend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import pw.telm.telmbackend.model.PatientLog;

import java.util.Optional;


public interface PatientLogRepository extends JpaRepository<PatientLog, Integer> {
    boolean existsByLogin(Integer login);

    Optional<PatientLog> findByLogin(Integer login);
    @Modifying
    @Transactional
    @Query("UPDATE PatientLog p SET p.password = :encode WHERE p.login = :login")
    void updatePasswordByLogin(String encode, Integer login);
    @Modifying
    @Transactional
    @Query("UPDATE PatientLog p SET p.email = :mail WHERE p.login = :login")
    void updateEmailByLogin(String mail, Integer login);



    @Query("SELECT p.login FROM PatientLog p WHERE p.patient.idPatient = :patientId")
    Integer getLoginById(int patientId);
}
