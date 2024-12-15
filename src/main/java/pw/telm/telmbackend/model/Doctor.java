package pw.telm.telmbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter

public class Doctor {
    @Id
    @GeneratedValue
    private Integer idDoctor;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @OneToOne(mappedBy = "doctor")
    private DoctorLog doctorLog;
}
