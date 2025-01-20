package pw.telm.telmbackend.service;

import pw.telm.telmbackend.DTOs.model.ShortStudyDto;
import pw.telm.telmbackend.DTOs.model.StudyDicomDto;

import java.util.Date;
import java.util.List;

public interface StudyService {


    List<ShortStudyDto> getSortedStudies(Date startDate, Date endDate, Boolean isText, String doctorName,
                                         Integer patientId);

    StudyDicomDto getStudyById(Integer id_study);
}
