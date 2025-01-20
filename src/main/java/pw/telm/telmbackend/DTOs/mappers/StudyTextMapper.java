package pw.telm.telmbackend.DTOs.mappers;

import org.springframework.stereotype.Component;
import pw.telm.telmbackend.DTOs.model.SeriesDto;
import pw.telm.telmbackend.DTOs.model.StudyDicomDto;
import pw.telm.telmbackend.DTOs.model.StudyTextDto;
import pw.telm.telmbackend.model.Study;

import java.util.List;

@Component
public class StudyTextMapper {
    public static StudyTextDto toStudyTextDto(Study study) {
        StudyTextDto studyTextDto = new StudyTextDto();
        studyTextDto.setIdStudy(study.getIdStudy());
        studyTextDto.setDescription(study.getDescription());
        studyTextDto.setStudyDate(study.getStudyDate());
        studyTextDto.setStudyTime(study.getStudyTime());
        studyTextDto.setIdPatient(study.getPatient().getIdPatient());
        studyTextDto.setTextStudy(study.getTextStudy());
        studyTextDto.setPatientName(study.getPatient().getName());
        return studyTextDto;
    }
}
