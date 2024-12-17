package pw.telm.telmbackend.model;

import jakarta.persistence.*;


import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

@Entity

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPatient;

    @Column(nullable = false)
    private String name;

    private String sex;
    private String pesel;
    private Date birthDate;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private PatientLog patientLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor", referencedColumnName = "idDoctor", nullable = false)
    private Doctor doctor;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Study> studies = new ArrayList<>();

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PatientLog getPatientLog() {
        return patientLog;
    }

    public void setPatientLog(PatientLog patientLog) {
        this.patientLog = patientLog;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "idPatient=" + idPatient +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", pesel='" + pesel + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
