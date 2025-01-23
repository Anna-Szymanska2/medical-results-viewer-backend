package pw.telm.telmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pw.telm.telmbackend.model.Study;
import pw.telm.telmbackend.DTOs.model.ShortStudyDto;

import java.util.List;
import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Integer> {
    Optional<Study> findByUidStudy(String uidStudy);

    @Query("SELECT new pw.telm.telmbackend.DTOs.model.ShortStudyDto(s.idStudy, s.studyDate, d.name, p.name, s.isText, p.idPatient) " +
            "FROM Study s " +
            "JOIN s.patient p " +
            "JOIN p.doctor d")
    List<ShortStudyDto> findShortStudies();

    Optional<Study> findByIdStudy(Integer id);

}
