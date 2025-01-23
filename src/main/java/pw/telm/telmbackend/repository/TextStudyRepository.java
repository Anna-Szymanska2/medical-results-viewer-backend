package pw.telm.telmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.telm.telmbackend.model.Study;
import pw.telm.telmbackend.model.TextStudy;

import java.util.Optional;

public interface TextStudyRepository extends JpaRepository<TextStudy,Integer> {
    Optional<TextStudy> findByIdTextStudy(Integer id);
}
