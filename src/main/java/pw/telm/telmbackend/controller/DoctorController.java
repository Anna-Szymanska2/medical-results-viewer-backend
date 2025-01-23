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

/**
 * REST controller for handling operations related to doctors.
 * Provides endpoints to retrieve information about doctors and their patients.
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    /**
     * Service layer for performing operations related to doctors.
     */
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * Retrieves a list of all doctors.
     *
     * @return a {@link ResponseEntity} containing a list of doctor names
     */
    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/all-whole")
    public ResponseEntity<List<Doctor>> getAllWholeDoctors() {
        return ResponseEntity.ok(doctorService.getAllWholeDoctors());
    }
    /**
     * Retrieves a list of patients associated with a doctor specified by ID.
     *
     * @param id the ID of the doctor
     * @return a {@link ResponseEntity} containing a list of {@link PatientDto} representing the doctor's patients
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<PatientDto>> getPatientsById(@PathVariable Integer id) {
        return ResponseEntity.ok(doctorService.getPatientsByDoctorId(id));
    }
}