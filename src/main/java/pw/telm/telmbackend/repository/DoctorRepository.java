package pw.telm.telmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.telm.telmbackend.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
