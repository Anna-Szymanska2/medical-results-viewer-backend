package pw.telm.telmbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue
    private Integer idDoctor;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "doctor")
    private DoctorLog doctorLog;

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoctorLog getDoctorLog() {
        return doctorLog;
    }

    public void setDoctorLog(DoctorLog doctorLog) {
        this.doctorLog = doctorLog;
    }
}
