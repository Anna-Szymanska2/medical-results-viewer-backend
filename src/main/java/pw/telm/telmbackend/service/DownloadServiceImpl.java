package pw.telm.telmbackend.service;

import org.springframework.stereotype.Service;
import pw.telm.telmbackend.repository.TextStudyRepository;

/**
 * Implementation of {@link DownloadService} that provides methods to retrieve file paths by ID.
 */
@Service
public class DownloadServiceImpl implements DownloadService {

    /**
     * Repository for accessing image data.
     */
    private final SeriesService seriesService;
    private final TextStudyRepository textStudyRepository;

    public DownloadServiceImpl(SeriesService seriesService, TextStudyRepository textStudyRepository) {
        this.seriesService = seriesService;
        this.textStudyRepository = textStudyRepository;
    }

    /**
     * Retrieves the file path associated with the given ID.
     *
     * @param id the ID of the file
     * @return a {@link String} representing the file path
     */
    @Override
    public String getDicomPathById(Integer id) {
        return seriesService.getPathBySeriesId(id);
    }

    @Override
    public String getTextPathById(Integer id) {
        return textStudyRepository.findByIdTextStudy(id).get().getStudy().getPath();
    }
}
