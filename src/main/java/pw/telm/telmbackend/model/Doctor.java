package pw.telm.telmbackend.model;

import jakarta.persistence.*;



@Entity

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoctor;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
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
