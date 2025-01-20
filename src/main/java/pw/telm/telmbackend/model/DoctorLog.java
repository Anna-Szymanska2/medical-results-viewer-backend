package pw.telm.telmbackend.model;

import jakarta.persistence.*;


@Entity

public class DoctorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoctorLog;
    private Integer login;
    private String password;
    private String email;
    private String otpCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor", referencedColumnName = "idDoctor", nullable = false)
    private Doctor doctor;

    public Integer getIdDoctorLog() {
        return idDoctorLog;
    }

    public void setIdDoctorLog(Integer idDoctorLog) {
        this.idDoctorLog = idDoctorLog;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {this.doctor = doctor;    }

    public Integer getId_doctor() { return  idDoctorLog;}

    public String getOtp() {return otpCode;}
}

