package pw.telm.telmbackend.model;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Entity
@Data
public class Study {
    @Id
    @GeneratedValue
    private Integer idStudy;

    private Date studyDate;
    private Time studyTime;

    @ManyToOne
    @JoinColumn(name = "id_patient", referencedColumnName = "idPatient", nullable = false)
    private Patient patient;
}
