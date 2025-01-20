package pw.telm.telmbackend.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity

public class TextStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTextStudy;

    private String studyName;
    private Double result;
    private String unit;
    private Double min;
    private Double max;
    private String norm;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_study", referencedColumnName = "idStudy", nullable = false)
    private Study study;
    public Integer getIdTextStudy() {
        return idTextStudy;
    }

    public void setIdTextStudy(Integer idTextStudy) {
        this.idTextStudy = idTextStudy;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }
}

