package pw.telm.telmbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pw.telm.telmbackend.model.Study;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study,Integer> {

    Optional<Study> findByUidStudy(String uidStudy);
}
