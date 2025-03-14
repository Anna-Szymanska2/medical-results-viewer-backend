package pw.telm.telmbackend.service;

/**
 * Service interface for downloading operations.
 * Provides a method to retrieve the file path by its ID.
 */
public interface DownloadService {
    /**
     * Retrieves the file path associated with the given ID.
     *
     * @param id          the ID of the file
     * @param imageNumber
     * @return a {@link String} representing the file path
     */
    String getDicomPathById(Integer id, Integer imageNumber);
    String getTextPathById(Integer id);
}
