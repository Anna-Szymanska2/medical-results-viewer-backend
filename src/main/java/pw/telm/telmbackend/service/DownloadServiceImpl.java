package pw.telm.telmbackend.service;

import org.springframework.stereotype.Service;

/**
 * Implementation of {@link DownloadService} that provides methods to retrieve file paths by ID.
 */
@Service
public class DownloadServiceImpl implements DownloadService {

    /**
     * Repository for accessing image data.
     */
    private final SeriesService seriesService;

    public DownloadServiceImpl(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    /**
     * Retrieves the file path associated with the given ID.
     *
     * @param id the ID of the file
     * @return a {@link String} representing the file path
     */
    @Override
    public String getPathById(Integer id) {
        return seriesService.getPathBySeriesId(id);
    }
}
