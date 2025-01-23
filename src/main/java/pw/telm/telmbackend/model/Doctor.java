package pw.telm.telmbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoctor;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    private DoctorLog doctorLog;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> patients = new ArrayList<>();

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
