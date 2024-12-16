package pw.telm.telmbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pw.telm.telmbackend.service.DoctorService;

@Component
public class DatabaseInit implements CommandLineRunner {


    @Autowired
    private DoctorService doctorService;

    @Override
    public void run(String... args) throws Exception {
        doctorService.createDoctorWithLog("Janusz Bury");

    }
}
