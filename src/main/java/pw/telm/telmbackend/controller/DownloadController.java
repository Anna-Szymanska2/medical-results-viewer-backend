package pw.telm.telmbackend.controller;

import pw.telm.telmbackend.service.DownloadService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * REST controller for handling file download operations.
 * Provides an endpoint to download DICOM files by their ID.
 */
@RestController
@RequestMapping("/download")
public class DownloadController {

    /**
     * Service layer for performing download operations.
     */
    private final DownloadService downloadService;

    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }
    @GetMapping("/dicom/{seriesId}/{imageNumber}")
    public ResponseEntity<Resource> downloadDicom(@PathVariable Integer seriesId, @PathVariable Integer imageNumber) throws IOException {
        String path = downloadService.getDicomPathById(seriesId, imageNumber);

        Resource resource = new FileSystemResource(path);

        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentLength(resource.contentLength());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
    @GetMapping("/text/{studyId}")
    public ResponseEntity<Resource> downloadText(@PathVariable Integer studyId) throws IOException {
        String path = downloadService.getTextPathById(studyId);

        Resource resource = new FileSystemResource(path);

        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentLength(resource.contentLength());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
