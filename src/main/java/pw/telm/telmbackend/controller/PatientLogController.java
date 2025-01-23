package pw.telm.telmbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pw.telm.telmbackend.service.PatientLogService;

@RestController
@RequestMapping("/patient_log")
public class PatientLogController {

    private final PatientLogService patientLogService;

    public PatientLogController(PatientLogService patientLogService) {
        this.patientLogService = patientLogService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Integer> getPatientsById(@PathVariable Integer id) {
        return ResponseEntity.ok(patientLogService.getLoginById(id));
    }
}
