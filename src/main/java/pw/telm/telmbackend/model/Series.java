package pw.telm.telmbackend.model;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity

public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeries;

    private String modality;
    private String uidSeries;
    private String numBm;
    private String manufacturer;
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_study", referencedColumnName = "idStudy", nullable = false)
    private Study study;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    public Integer getIdSeries() {
        return idSeries;
    }

    public void setIdSeries(Integer idSeries) {
        this.idSeries = idSeries;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getUidSeries() {
        return uidSeries;
    }

    public void setUidSeries(String uidSeries) {
        this.uidSeries = uidSeries;
    }

    public String getNumBm() {
        return numBm;
    }

    public void setNumBm(String numBm) {
        this.numBm = numBm;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Series{" +
                "idSeries=" + idSeries +
                ", modality='" + modality + '\'' +
                ", uidSeries='" + uidSeries + '\'' +
                ", numBm='" + numBm + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", number=" + number +
                '}';
    }
}
