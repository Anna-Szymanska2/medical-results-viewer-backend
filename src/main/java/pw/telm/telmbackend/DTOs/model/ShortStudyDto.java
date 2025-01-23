package pw.telm.telmbackend.DTOs.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.util.Objects;

public class ShortStudyDto {
    private Integer idStudy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Zagreb")
    private Date studyDate;
    private String doctorName;
    private String patientName;
    private String isTextString;
    private boolean isText;
    private Integer patientId;

    public ShortStudyDto(Integer idStudy, Date studyDate, String doctorName, String patientName, boolean isText, Integer patientId) {
        this.idStudy = idStudy;
        this.studyDate = studyDate;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.isText = isText;
        this.patientId = patientId;
    }

    public Integer getIdStudy() {
        return idStudy;
    }

    public void setIdStudy(Integer idStudy) {
        this.idStudy = idStudy;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getIsTextString() {
        return isTextString;
    }

    public void setIsTextString(String isTextString) {
        this.isTextString = isTextString;
    }

    public boolean isText() {
        return isText;
    }

    public void setText(boolean text) {
        isText = text;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "ShortStudyDto{" +
                "idStudy=" + idStudy +
                ", studyDate=" + studyDate +
                ", doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", isTextString='" + isTextString + '\'' +
                ", isText=" + isText +
                ", patientId=" + patientId +
                '}';
    }
}
