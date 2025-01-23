package pw.telm.telmbackend.DTOs.mappers;


import org.springframework.stereotype.Component;
import pw.telm.telmbackend.DTOs.model.SeriesDto;
import pw.telm.telmbackend.DTOs.model.StudyDicomDto;
import pw.telm.telmbackend.model.Study;

import java.util.List;

/**
 * Mapper class to convert between domain entities and DTOs for Study objects.
 */
@Component
public class StudyDicomMapper {
    /**
     * Converts a Study entity and a list of SeriesDto objects to a StudyDto.
     *
     * @param study        the Study entity to convert
     * @param seriesDtoList the list of SeriesDto objects associated with the Study
     * @return a StudyDto object containing the data from the Study entity and the SeriesDto list
     */
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
