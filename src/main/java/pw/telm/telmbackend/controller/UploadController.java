package pw.telm.telmbackend.controller;

import com.pixelmed.dicom.DicomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pw.telm.telmbackend.exception.NotTextException;
import pw.telm.telmbackend.model.Doctor;
import pw.telm.telmbackend.service.DicomService;
import pw.telm.telmbackend.service.DoctorService;
import pw.telm.telmbackend.service.PatientService;
import pw.telm.telmbackend.service.UploadService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for handling file upload operations.
 * Provides an endpoint to upload DICOM files and save them to the database.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    /**
     * Service layer for performing upload operations.
     */
    private final UploadService uploadService;
    private final DicomService dicomService;
    private final DoctorService doctorService;
    private final PatientService patientService;


    public UploadController(UploadService uploadService, DicomService dicomService, DoctorService doctorService, PatientService patientService) {
        this.uploadService = uploadService;
        this.dicomService = dicomService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    /**
     * Handles file upload of DICOM files.
     *
     * @param file      the DICOM file to upload
     * @param id_doctor the ID of the doctor associated with the uploaded file
     * @return a {@link ResponseEntity} containing a map with a success message or error details
     */
    @PostMapping("/dicom")
    public ResponseEntity<Map<String, Object>> uploadDicom(@RequestParam("file") MultipartFile file,
                                                           @RequestParam("doctor") Integer id_doctor) {


        DicomException dicomFileException = new DicomException("Nieprawidlowy rodzaj pliku");
        Map<String, Object> responseMap = new HashMap<>();

        File fileConverted = null;

        try {
            fileConverted = uploadService.convert(file);
            if (!uploadService.checkIfDicom(fileConverted)) throw dicomFileException;


            String dicomFilePath = uploadService.saveFile(file, "src/main/resources/dicoms/");
            Doctor doctor = doctorService.findDoctorById(id_doctor);
            dicomService.addDicom(dicomFilePath, doctor);
            String message = "Plik został przesłany i zapisany pomyślnie.";

            responseMap.put("message", message);
            return ResponseEntity.ok(responseMap);

        } catch (DicomException | IOException e) {
            String message = "Błąd podczas wprowadzania pliku do bazy. Sprawdź plik. \n" + e;
            responseMap.put("message", message);
            return ResponseEntity.status(600).body(responseMap);
        } finally {
            if (fileConverted != null && fileConverted.exists()) {
                fileConverted.delete();
            }
        }
    }

    @PostMapping("/text")
    public ResponseEntity<Map<String, Object>> uploadText(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("patientName") String patientName) {
        Map<String, Object> responseMap = new HashMap<>();
        File fileConverted = null;

        try {
            // Konwersja pliku na File
            fileConverted = uploadService.convert(file);

            // Sprawdzenie, czy jest to plik tekstowy
            if (!uploadService.isTextFile(fileConverted)) {
                throw new NotTextException("Plik ma niepoprawny format (nie .txt)");
            }

            // Zapis pliku
            String textFilePath = uploadService.saveFile(file, "src/main/resources/text_studies/");

            // Parsowanie i zapis danych pacjenta
            patientService.parseAndSaveStudy(textFilePath, patientName);

            responseMap.put("message", "Plik został przesłany i zapisany pomyślnie.");
            return ResponseEntity.ok(responseMap);

        } catch (NotTextException e) {
            responseMap.put("message", e.getMessage());
            return ResponseEntity.status(600).body(responseMap);

        } catch (IOException e) {
            responseMap.put("message", "Błąd podczas przetwarzania pliku: " + e.getMessage());
            return ResponseEntity.status(500).body(responseMap);

        } finally {
            // Usuwanie pliku tymczasowego, jeśli istnieje
            if (fileConverted != null && fileConverted.exists()) {
                fileConverted.delete();
            }
        }
    }

}