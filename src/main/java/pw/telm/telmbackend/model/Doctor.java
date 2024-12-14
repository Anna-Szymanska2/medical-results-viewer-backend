package pw.telm.telmbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue
    private Integer idDoctor;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "doctor")
    private DoctorLog doctorLog;
}
