package pw.telm.telmbackend.model;
import jakarta.persistence.*;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStudy;

    private String description;
    private Date studyDate;
    private Time studyTime;
    private String uidStudy;

    private boolean isText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", referencedColumnName = "idPatient", nullable = false)
    private Patient patient;

    @OneToOne(mappedBy = "study", cascade = CascadeType.ALL)
    private TextStudy textStudy;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<Series> seriesList = new ArrayList<>();

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public TextStudy getTextStudy() {
        return textStudy;
    }

    public void setTextStudy(TextStudy textStudy) {
        this.textStudy = textStudy;
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
    }

    public String getUidStudy() {
        return uidStudy;
    }

    public void setUidStudy(String uidStudy) {
        this.uidStudy = uidStudy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isText() {
        return isText;
    }

    public void setText(boolean text) {
        isText = text;
    }

    @Override
    public String toString() {
        return "Study{" +
                "idStudy=" + idStudy +
                ", studyDate=" + studyDate +
                ", studyTime=" + studyTime +
                ", patient=" + patient +
                '}';
    }
}
