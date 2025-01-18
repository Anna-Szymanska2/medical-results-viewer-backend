package pw.telm.telmbackend.controller;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pw.telm.telmbackend.DTOs.model.ShortStudyDto;
import pw.telm.telmbackend.service.StudyService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/study")
public class StudyController {
    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
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


}
