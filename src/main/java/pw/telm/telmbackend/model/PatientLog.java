package pw.telm.telmbackend.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class PatientLog {
    @Id
    @GeneratedValue
    private Integer idPatientLog;

    private Integer login;
    private String password;
    private String email;
    private String otpCode;

    @OneToOne
    @JoinColumn(name = "id_patient", referencedColumnName = "idPatient", nullable = false)
    private Patient patient;
}
