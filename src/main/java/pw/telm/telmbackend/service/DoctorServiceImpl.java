package pw.telm.telmbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.telm.telmbackend.model.Doctor;
import pw.telm.telmbackend.model.DoctorLog;
import pw.telm.telmbackend.repository.DoctorLogRepository;
import pw.telm.telmbackend.repository.DoctorRepository;

import java.util.Random;

@Service
public class DoctorServiceImpl implements DoctorService{

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorLogRepository doctorLogRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorLogRepository = doctorLogRepository;
    }

    private final DoctorRepository doctorRepository;
    private final DoctorLogRepository doctorLogRepository;

    public static Integer generateLogin(){
        Random random = new Random();
        // Generujemy losową liczbę całkowitą w przedziale od 10000000 do 99999999
        return 10000000 + random.nextInt(90000000);
    }
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

        // Zapis doktora do bazy
        doctorRepository.save(doctor);

        // Generowanie unikalnego loginu
        Integer uniqueLogin = generateUniqueLogin();

        // Tworzenie powiązanego DoctorLog
        DoctorLog doctorLog = new DoctorLog();
        doctorLog.setLogin(uniqueLogin); // Przykładowy unikalny login
        doctorLog.setDoctor(doctor); // Powiązanie z doktorem

        // Zapis DoctorLog do bazy
        doctorLogRepository.save(doctorLog);
    }
}
