package pw.telm.telmbackend.model;

import jakarta.persistence.*;

@Entity

public class PatientLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPatientLog;

    private Integer login;
    private String password;
    private String email;
    private String otpCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", referencedColumnName = "idPatient", nullable = false)
    private Patient patient;

    public Integer getIdPatientLog() {
        return idPatientLog;
    }

    public void setIdPatientLog(Integer idPatientLog) {
        this.idPatientLog = idPatientLog;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getId_patient() { return idPatientLog;}

    public String getOtp() {return otpCode;}
}
