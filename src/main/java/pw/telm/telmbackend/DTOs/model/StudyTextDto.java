package pw.telm.telmbackend.DTOs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import pw.telm.telmbackend.model.TextStudy;

import java.sql.Date;
import java.sql.Time;

public class StudyTextDto {

    private Integer idStudy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Zagreb")
    private Date studyDate;
    private Time studyTime;
    private Integer idPatient;
    private String patientName;
    private String description;
    private TextStudy textStudy;

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

    public Time getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Time studyTime) {
        this.studyTime = studyTime;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TextStudy getTextStudy() {
        return textStudy;
    }

    public void setTextStudy(TextStudy textStudy) {
        this.textStudy = textStudy;
    }
}
