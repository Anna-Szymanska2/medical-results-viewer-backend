package pw.telm.telmbackend.DTOs.mappers;


import org.springframework.stereotype.Component;
import pw.telm.telmbackend.DTOs.model.SeriesDto;
import pw.telm.telmbackend.DTOs.model.StudyDto;
import pw.telm.telmbackend.model.Study;

import java.util.List;

/**
 * Mapper class to convert between domain entities and DTOs for Study objects.
 */
@Component
public class StudyMapper {
    /**
     * Converts a Study entity and a list of SeriesDto objects to a StudyDto.
     *
     * @param study        the Study entity to convert
     * @param seriesDtoList the list of SeriesDto objects associated with the Study
     * @return a StudyDto object containing the data from the Study entity and the SeriesDto list
     */
    public static StudyDto toStudyDto(Study study, List<SeriesDto> seriesDtoList) {
        StudyDto studyDto = new StudyDto();
        studyDto.setIdStudy(study.getIdStudy());
        studyDto.setStudyDate(study.getStudyDate());
        studyDto.setStudyTime(study.getStudyTime());
        studyDto.setIdPatient(study.getPatient().getIdPatient());
        studyDto.setSeries(seriesDtoList);
        studyDto.setPatientName(study.getPatient().getName());
        return studyDto;
    }
}
