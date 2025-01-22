package pw.telm.telmbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pw.telm.telmbackend.model.*;
import pw.telm.telmbackend.repository.DoctorRepository;
import pw.telm.telmbackend.service.DicomService;
import pw.telm.telmbackend.service.DoctorService;
import pw.telm.telmbackend.service.PatientService;

import java.io.IOException;


@Component
public class DatabaseInit implements CommandLineRunner {



    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;

    private final DicomService dicomService;
    private final PatientService patientService;

    public DatabaseInit(DoctorService doctorService, DoctorRepository doctorRepository, DicomService dicomService, PatientService patientService) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
        this.dicomService = dicomService;
        this.patientService = patientService;
    }

    @Override
    public void run(String... args) throws Exception {
        //initializeDB();



//        doctor.getDoctorLog().setEmail("email");
//        doctorRepository.save(doctor);

    }

    public void initializeDB() throws IOException {
        doctorService.createDoctorWithLog("Janusz Bury");
        doctorService.createDoctorWithLog("Wiktor Bolibok");
        doctorService.createDoctorWithLog("Renata Szyc");
        Doctor doctor = doctorRepository.findByIdDoctor(1).orElse(null);
        for (int i = 0; i < 5; i++) {
            dicomService.addDicom("src/main/resources/dicoms/dicom" + (i + 1) + ".dcm", doctor);
        }
        doctor = doctorRepository.findByIdDoctor(2).orElse(null);
        for (int i = 5; i < 20; i++) {
            dicomService.addDicom("src/main/resources/dicoms/dicom" + (i + 1) + ".dcm", doctor);
        }
        doctor = doctorRepository.findByIdDoctor(3).orElse(null);
        for (int i = 20; i < 23; i++) {
            dicomService.addDicom("src/main/resources/dicoms/dicom" + (i + 1), doctor);
        }
        patientService.parseAndSaveStudy("src/main/resources/text_studies/text1.txt", "Jolanta Kot");
        patientService.parseAndSaveStudy("src/main/resources/text_studies/text2.txt", "Jolanta Kot");
        patientService.parseAndSaveStudy("src/main/resources/text_studies/text3.txt", "Bob Bobinsky");
        patientService.parseAndSaveStudy("src/main/resources/text_studies/text4.txt", "Stefan Ziombek");
        patientService.parseAndSaveStudy("src/main/resources/text_studies/text5.txt", "Wiktoria Mroz");
    }


}
