package pw.telm.telmbackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.telm.telmbackend.DTOs.mappers.PatientMapper;
import pw.telm.telmbackend.DTOs.model.PatientDto;
import pw.telm.telmbackend.model.Doctor;
import pw.telm.telmbackend.model.DoctorLog;
import pw.telm.telmbackend.model.Patient;
import pw.telm.telmbackend.repository.DoctorLogRepository;
import pw.telm.telmbackend.repository.DoctorRepository;
import pw.telm.telmbackend.repository.PatientRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static pw.telm.telmbackend.Generators.generateLogin;

@Service
public class DoctorServiceImpl implements DoctorService{

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorLogRepository doctorLogRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorLogRepository = doctorLogRepository;
        this.patientRepository = patientRepository;
    }

    private final DoctorRepository doctorRepository;
    private final DoctorLogRepository doctorLogRepository;
    private final PatientRepository patientRepository;


    private boolean isLoginExists(Integer login) {
        return doctorLogRepository.existsByLogin(login);
    }
    private Integer generateUniqueLogin() {
        Integer login;
        do {
            login = generateLogin();
        } while (isLoginExists(login)); // Powtarzaj, dopóki login istnieje w bazie
        return login;
    }

    public void createDoctorWithLog(String name) {
        // Tworzenie przykładowego doktora
        Doctor doctor = new Doctor();
        doctor.setName(name);
        Integer uniqueLogin = generateUniqueLogin();

        // Tworzenie powiązanego DoctorLog
        DoctorLog doctorLog = new DoctorLog();
        doctorLog.setLogin(uniqueLogin);
        doctorLog.setDoctor(doctor); // Powiązanie z doktorem
        doctor.setDoctorLog(doctorLog);

        // Zapis doktora do bazy
        doctorRepository.save(doctor);


        // Zapis DoctorLog do bazy
       // doctorLogRepository.save(doctorLog);
    }

    @Override
    public List<String> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(Doctor::getName) // Pobieramy nazwiska doktorów
                .distinct()               // Usuwamy duplikaty
                .collect(Collectors.toList()); // Zbieramy do listy
    }

    @Override
    public List<PatientDto> getPatientsByDoctorId(Integer id) {
        List<Patient> patients = patientRepository.findByDoctor_IdDoctor(id);
        return patients.stream()
                .map(PatientMapper::toPatientDto) // Mapowanie Patient na PatientDto
                .collect(Collectors.toList());

    }
}
