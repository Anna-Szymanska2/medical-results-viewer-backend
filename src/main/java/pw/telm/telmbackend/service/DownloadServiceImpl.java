package pw.telm.telmbackend.service;

import org.springframework.stereotype.Service;
import pw.telm.telmbackend.repository.StudyRepository;


@Service
public class DownloadServiceImpl implements DownloadService {


    private final SeriesService seriesService;
    private final StudyRepository studyRepository;

    public DownloadServiceImpl(SeriesService seriesService, StudyRepository studyRepository) {
        this.seriesService = seriesService;
        this.studyRepository = studyRepository;
    }



    @Override
    public String getDicomPathById(Integer id, Integer imageNumber) {
        return seriesService.getPathBySeriesId(id, imageNumber);
    }

    @Override
    public String getTextPathById(Integer id) {
        return studyRepository.findByIdStudy(id).get().getPath();
    }
}
