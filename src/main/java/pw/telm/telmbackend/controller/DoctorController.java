package pw.telm.telmbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pw.telm.telmbackend.DTOs.model.PatientDto;
import pw.telm.telmbackend.model.Doctor;
import pw.telm.telmbackend.service.DoctorService;

import javax.print.Doc;
import java.util.List;


@RestController
@RequestMapping("/doctor")
public class DoctorController {


    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/all-whole")
    public ResponseEntity<List<Doctor>> getAllWholeDoctors() {
        return ResponseEntity.ok(doctorService.getAllWholeDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PatientDto>> getPatientsById(@PathVariable Integer id) {
        return ResponseEntity.ok(doctorService.getPatientsByDoctorId(id));
    }
}