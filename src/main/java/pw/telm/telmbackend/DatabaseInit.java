package pw.telm.telmbackend;

import com.pixelmed.dicom.DicomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pw.telm.telmbackend.model.*;
import pw.telm.telmbackend.repository.DoctorLogRepository;
import pw.telm.telmbackend.repository.DoctorRepository;
import pw.telm.telmbackend.repository.PatientLogRepository;
import pw.telm.telmbackend.repository.PatientRepository;
import pw.telm.telmbackend.service.DicomService;
import pw.telm.telmbackend.service.DoctorService;

import java.io.File;
import java.io.IOException;

import static pw.telm.telmbackend.Generators.generateLogin;


@Component
public class DatabaseInit implements CommandLineRunner {



    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;

    private final DicomService dicomService;

    public DatabaseInit(DoctorService doctorService, DoctorRepository doctorRepository, DicomService dicomService) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
        this.dicomService = dicomService;
    }

    @Override
    public void run(String... args) throws Exception {
        doctorService.createDoctorWithLog("Janusz Bury");
        Doctor doctor = doctorRepository.findByIdDoctor(1).orElse(null);
        for (int i = 0; i < 20; i++) {
            dicomService.addDicom("src/main/resources/dicoms/dicom" + (i + 1) + ".dcm", doctor, i +1);
        }

        for (int i = 20; i < 23; i++) {
            dicomService.addDicom("src/main/resources/dicoms/dicom" + (i + 1), doctor, i +1);
        }

//        doctor.getDoctorLog().setEmail("email");
//        doctorRepository.save(doctor);

    }


}
