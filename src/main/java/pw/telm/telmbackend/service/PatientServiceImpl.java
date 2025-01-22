package pw.telm.telmbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pw.telm.telmbackend.model.*;
import pw.telm.telmbackend.repository.DoctorLogRepository;
import pw.telm.telmbackend.repository.PatientLogRepository;
import pw.telm.telmbackend.repository.PatientRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date; // java.sql.Date
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static pw.telm.telmbackend.Generators.generateLogin;

@Service
public class PatientServiceImpl implements PatientService{


    private final PatientRepository patientRepository;
    private final PatientLogRepository patientLogRepository;
    private final DoctorLogRepository doctorLogRepository;
    private final DoctorService doctorService;

    public PatientServiceImpl(PatientRepository patientRepository, PatientLogRepository patientLogRepository, DoctorLogRepository doctorLogRepository, DoctorService doctorService) {
        this.patientRepository = patientRepository;
        this.patientLogRepository = patientLogRepository;
        this.doctorLogRepository = doctorLogRepository;
        this.doctorService = doctorService;
    }
    @Transactional
    public void findOrCreatePatient(String patientName, Integer doctorId){
        Patient patient = patientRepository.findByName(patientName).orElse(null);
        if(patient == null){
            patient = new Patient();
            patient.setName(patientName);
            int login = generateLogin();
            while (patientLogRepository.existsByLogin(login) || doctorLogRepository.existsByLogin(login)) {
                login = generateLogin();
            }
            PatientLog patientLog = new PatientLog();
            patientLog.setLogin(login);
            patientLog.setPatient(patient);
            patient.setPatientLog(patientLog);
            Doctor doctor = doctorService.findDoctorById(doctorId);
            patient.setDoctor(doctor);
            patientRepository.save(patient);
        }
    }

    @Transactional
    public void parseAndSaveStudy(String filePath, String patientName) throws IOException, IllegalArgumentException {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            // Odczyt pacjenta z bazy na podstawie nazwy
            Patient patient = patientRepository.findByName(patientName)
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found with name: " + patientName));

            // Odczyt daty
            String studyDateLine = reader.readLine();
            Date studyDate = Date.valueOf(studyDateLine); // Korzystamy z java.sql.Date

            // Odczyt czasu
            String studyTimeLine = reader.readLine();
            Time studyTime = Time.valueOf(studyTimeLine);

            // Odczyt opisu
            String description = reader.readLine();

            // Tworzenie encji Study
            Study study = new Study();
            study.setStudyDate(studyDate);
            study.setStudyTime(studyTime);
            study.setDescription(description);
            study.setText(true);
            study.setPatient(patient);

            String line;
            List<TextStudy> textStudies = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");

                if (parts.length != 6) {
                    throw new IllegalArgumentException("Invalid file format");
                }

                // Parsowanie danych dla TextStudy
                String studyName = parts[0];
                double result = Double.parseDouble(parts[1]);
                String unit = parts[2];
                double min = Double.parseDouble(parts[3]);
                double max = Double.parseDouble(parts[4]);
                String norm = parts[5];

                // Tworzenie encji TextStudy
                TextStudy textStudy = new TextStudy();
                textStudy.setStudyName(studyName);
                textStudy.setResult(result);
                textStudy.setUnit(unit);
                textStudy.setMin(min);
                textStudy.setMax(max);
                textStudy.setNorm(norm);
                textStudy.setStudy(study);
                textStudies.add(textStudy);
            }

            // Powiązanie listy TextStudy ze Study
            study.setTextStudies(textStudies);


            // Dodanie badania do listy badań pacjenta
            patient.getStudies().add(study);

            // Zapis pacjenta (i powiązanych encji Study oraz TextStudy dzięki CascadeType.ALL)
            patientRepository.save(patient);

    }

}
