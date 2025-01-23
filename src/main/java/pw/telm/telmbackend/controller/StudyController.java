package pw.telm.telmbackend.controller;


import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.telm.telmbackend.DTOs.model.ShortStudyDto;
import pw.telm.telmbackend.DTOs.model.StudyDicomDto;
import pw.telm.telmbackend.DTOs.model.StudyTextDto;
import pw.telm.telmbackend.service.SeriesService;
import pw.telm.telmbackend.service.StudyService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/study")
public class StudyController {
    private final StudyService studyService;
    private final SeriesService seriesService;

    public StudyController(StudyService studyService, SeriesService seriesService) {
        this.studyService = studyService;
        this.seriesService = seriesService;
    }

    @GetMapping("/dicom/{id}")
    public ResponseEntity<StudyDicomDto> getDicomStudyById(@PathVariable Integer id) throws IOException {
        return ResponseEntity.ok(studyService.getDicomStudyById(id));
    }

    @GetMapping("/text/{id}")
    public ResponseEntity<StudyTextDto> getTextStudyById(@PathVariable Integer id) throws IOException {
        return ResponseEntity.ok(studyService.getTextStudyById(id));
    }

    @PostMapping("/sorted-studies")
    public ResponseEntity<List<ShortStudyDto>> getSortedStudies(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(required = false) Boolean isText,
            @RequestParam(required = false) String doctorName,
            @RequestParam(required = false) Integer patientId) {

        return ResponseEntity.ok(studyService.getSortedStudies(startDate, endDate, isText, doctorName,
                patientId));
    }

    @GetMapping("/image/{id_series}/{image_number}/{frame_number}")
    public ResponseEntity<Resource> getImage(@PathVariable Integer id_series, @PathVariable Integer image_number,
                                             @PathVariable Integer frame_number) throws IOException {
        String path = seriesService.getImageByIdAndNumber(id_series, image_number, frame_number);

        Resource resource = new FileSystemResource(path);

        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(resource.contentLength());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

    }
}
