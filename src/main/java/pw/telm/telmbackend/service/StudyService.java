package pw.telm.telmbackend.service;

import pw.telm.telmbackend.DTOs.model.ShortStudyDto;
import pw.telm.telmbackend.DTOs.model.StudyDicomDto;
import pw.telm.telmbackend.DTOs.model.StudyTextDto;

import java.util.Date;
import java.util.List;

public interface StudyService {


    List<ShortStudyDto> getSortedStudies(Date startDate, Date endDate, Boolean isText, String doctorName,
                                         Integer patientId);

    StudyDicomDto getDicomStudyById(Integer id_study);

    StudyTextDto getTextStudyById(Integer id);
}
