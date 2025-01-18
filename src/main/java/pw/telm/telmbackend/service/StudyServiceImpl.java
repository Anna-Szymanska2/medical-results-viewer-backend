package pw.telm.telmbackend.service;

import org.springframework.stereotype.Service;
import pw.telm.telmbackend.DTOs.model.ShortStudyDto;
import pw.telm.telmbackend.repository.StudyRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudyServiceImpl implements StudyService{

    private final StudyRepository studyRepository;

    public StudyServiceImpl(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public List<ShortStudyDto> getSortedStudies(Date startDate, Date endDate, Boolean isText, String doctorName,
                                                Integer patientId) {
        List<ShortStudyDto> shortStudyDtos = studyRepository.findShortStudies();
        for (ShortStudyDto shortStudyDto: shortStudyDtos) {
            if (shortStudyDto.isText()){
                shortStudyDto.setIsTextString("Tekstowe");
            }
            else{
                shortStudyDto.setIsTextString("Obrazowe");
            }

        }
        if (isText != null) {
            shortStudyDtos = shortStudyDtos.stream()
                    .filter(dto -> Objects.equals(dto.isText(), isText))
                    .collect(Collectors.toList());
        }
        if (doctorName != null) {
            shortStudyDtos = shortStudyDtos.stream()
                    .filter(dto -> Objects.equals(dto.getDoctorName(), doctorName))
                    .collect(Collectors.toList());
        }
        if (patientId != null) {
            shortStudyDtos = shortStudyDtos.stream()
                    .filter(dto -> Objects.equals(dto.getPatientId() , patientId))
                    .collect(Collectors.toList());
        }

        if (startDate != null) {
            shortStudyDtos = shortStudyDtos.stream()
                    .filter(dto -> dto.getStudyDate().after(startDate))
                    .collect(Collectors.toList());
        }

        if (endDate != null) {
            shortStudyDtos = shortStudyDtos.stream()
                    .filter(dto -> dto.getStudyDate().before(endDate))
                    .collect(Collectors.toList());
        }
        for (ShortStudyDto shortStudyDto: shortStudyDtos) {
            System.out.println(shortStudyDto);
        }
        return shortStudyDtos;

    }


}
