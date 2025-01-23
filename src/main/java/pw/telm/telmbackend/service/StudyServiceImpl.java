package pw.telm.telmbackend.service;

import org.springframework.stereotype.Service;
import pw.telm.telmbackend.DTOs.mappers.StudyDicomMapper;
import pw.telm.telmbackend.DTOs.mappers.StudyTextMapper;
import pw.telm.telmbackend.DTOs.model.*;
import pw.telm.telmbackend.model.Image;
import pw.telm.telmbackend.model.Series;
import pw.telm.telmbackend.model.Study;
import pw.telm.telmbackend.repository.StudyRepository;
import pw.telm.telmbackend.DTOs.mappers.ImageMapper;
import pw.telm.telmbackend.DTOs.mappers.SeriesMapper;
import java.util.ArrayList;
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

        return shortStudyDtos;

    }

    @Override
    public StudyDicomDto getDicomStudyById(Integer idStudy) {
        Study study = studyRepository.findByIdStudy(idStudy).get();
        List<SeriesDto> seriesDtoList = new ArrayList<>();
        List<Series> seriess = study.getSeriesList();

        for (Series series : seriess) {
            List<Image> images = series.getImages();
            List<ImageDto> imageDtoList = new ArrayList<>();
            for (Image image : images) {
                imageDtoList.add(ImageMapper.toImageDto(image));
            }
            seriesDtoList.add(SeriesMapper.toSeriesDto(series, imageDtoList));
        }
        return StudyDicomMapper.toStudyDicomDto(study, seriesDtoList);

    }

    @Override
    public StudyTextDto getTextStudyById(Integer id) {
        Study study = studyRepository.findByIdStudy(id).get();
        return StudyTextMapper.toStudyTextDto(study);
    }
}
