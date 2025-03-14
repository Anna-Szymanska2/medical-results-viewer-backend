package pw.telm.telmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.telm.telmbackend.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByName(String name);
    List<Patient> findByDoctor_IdDoctor(Integer doctorId);

}
