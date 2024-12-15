package pw.telm.telmbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue
    private Integer idPatient;

    @Column(nullable = false)
    private String name;

    private String sex;
    private String pesel;
    private Date birthDate;

    @OneToOne(mappedBy = "patient")
    private PatientLog patientLog;
}
