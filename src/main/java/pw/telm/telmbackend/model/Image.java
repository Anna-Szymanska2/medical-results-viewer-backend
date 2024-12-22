package pw.telm.telmbackend.model;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImage;

    private String type;
    private Integer instance;
    private Integer samplePerPixel;
    private String photometricInterpretation;
    private Integer rowsImage;
    private Integer columnsImage;
    private Integer bitsAllocated;
    private Integer bitsStored;
    private Integer highBit;
    private Integer pixelRepresentation;
    private String pixelSpacing;
    private Integer numFrames;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_series", referencedColumnName = "idSeries", nullable = false)
    private Series series;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private List<Frame> frames = new ArrayList<>();

    public Integer getIdImage() {
        return idImage;
    }

    public void setIdImage(Integer idImage) {
        this.idImage = idImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getInstance() {
        return instance;
    }

    public void setInstance(Integer instance) {
        this.instance = instance;
    }

    public Integer getSamplePerPixel() {
        return samplePerPixel;
    }

    public void setSamplePerPixel(Integer samplePerPixel) {
        this.samplePerPixel = samplePerPixel;
    }

    public String getPhotometricInterpretation() {
        return photometricInterpretation;
    }

    public void setPhotometricInterpretation(String photometricInterpretation) {
        this.photometricInterpretation = photometricInterpretation;
    }

    public Integer getRowsImage() {
        return rowsImage;
    }

    public void setRowsImage(Integer rowsImage) {
        this.rowsImage = rowsImage;
    }

    public Integer getColumnsImage() {
        return columnsImage;
    }

    public void setColumnsImage(Integer columnsImage) {
        this.columnsImage = columnsImage;
    }

    public Integer getBitsAllocated() {
        return bitsAllocated;
    }

    public void setBitsAllocated(Integer bitsAllocated) {
        this.bitsAllocated = bitsAllocated;
    }

    public Integer getBitsStored() {
        return bitsStored;
    }

    public void setBitsStored(Integer bitsStored) {
        this.bitsStored = bitsStored;
    }

    public Integer getHighBit() {
        return highBit;
    }

    public void setHighBit(Integer highBit) {
        this.highBit = highBit;
    }

    public Integer getPixelRepresentation() {
        return pixelRepresentation;
    }

    public void setPixelRepresentation(Integer pixelRepresentation) {
        this.pixelRepresentation = pixelRepresentation;
    }

    public String getPixelSpacing() {
        return pixelSpacing;
    }

    public void setPixelSpacing(String pixelSpacing) {
        this.pixelSpacing = pixelSpacing;
    }

    public Integer getNumFrames() {
        return numFrames;
    }

    public void setNumFrames(Integer numFrames) {
        this.numFrames = numFrames;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    @Override
    public String toString() {
        return "Image{" +
                "idImage=" + idImage +
                ", type='" + type + '\'' +
                ", instance=" + instance +
                ", samplePerPixel=" + samplePerPixel +
                ", photometricInterpretation='" + photometricInterpretation + '\'' +
                ", rowsImage=" + rowsImage +
                ", columnsImage=" + columnsImage +
                ", bitsAllocated=" + bitsAllocated +
                ", bitsStored=" + bitsStored +
                ", highBit=" + highBit +
                ", pixelRepresentation=" + pixelRepresentation +
                ", pixelSpacing='" + pixelSpacing + '\'' +
                ", numFrames=" + numFrames +
                '}';
    }
}
