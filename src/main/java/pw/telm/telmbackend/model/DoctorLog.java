package pw.telm.telmbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DoctorLog {
    @Id
    @GeneratedValue
    private Integer idDoctorLog;
    private Integer login;
    private String password;
    private String email;
    private String otpCode;

    @OneToOne
    @JoinColumn(name = "id_doctor", referencedColumnName = "idDoctor", nullable = false)
    private Doctor doctor;
}

