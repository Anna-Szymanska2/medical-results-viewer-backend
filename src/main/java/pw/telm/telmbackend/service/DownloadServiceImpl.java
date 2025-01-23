package pw.telm.telmbackend.service;

import org.springframework.stereotype.Service;
import pw.telm.telmbackend.repository.StudyRepository;

/**
 * Implementation of {@link DownloadService} that provides methods to retrieve file paths by ID.
 */
@Service
public class DownloadServiceImpl implements DownloadService {

    /**
     * Repository for accessing image data.
     */
    private final SeriesService seriesService;
    private final StudyRepository studyRepository;

    public DownloadServiceImpl(SeriesService seriesService, StudyRepository studyRepository) {
        this.seriesService = seriesService;
        this.studyRepository = studyRepository;
    }


    /**
     * Retrieves the file path associated with the given ID.
     *
     * @param id          the ID of the file
     * @param imageNumber
     * @return a {@link String} representing the file path
     */
    @Override
    public String getDicomPathById(Integer id, Integer imageNumber) {
        return seriesService.getPathBySeriesId(id, imageNumber);
    }

    @Override
    public String getTextPathById(Integer id) {
        return studyRepository.findByIdStudy(id).get().getPath();
    }
}
