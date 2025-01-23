package pw.telm.telmbackend.DTOs.mappers;


import org.springframework.stereotype.Component;
import pw.telm.telmbackend.DTOs.model.SeriesDto;
import pw.telm.telmbackend.DTOs.model.StudyDicomDto;
import pw.telm.telmbackend.model.Study;

import java.util.List;


@Component
public class StudyDicomMapper {

    public static StudyDicomDto toStudyDicomDto(Study study, List<SeriesDto> seriesDtoList) {
        StudyDicomDto studyDicomDto = new StudyDicomDto();
        studyDicomDto.setIdStudy(study.getIdStudy());
        studyDicomDto.setDescription(study.getDescription());
        studyDicomDto.setStudyDate(study.getStudyDate());
        studyDicomDto.setStudyTime(study.getStudyTime());
        studyDicomDto.setIdPatient(study.getPatient().getIdPatient());
        studyDicomDto.setSeries(seriesDtoList);
        studyDicomDto.setPatientName(study.getPatient().getName());
        return studyDicomDto;
    }
}
