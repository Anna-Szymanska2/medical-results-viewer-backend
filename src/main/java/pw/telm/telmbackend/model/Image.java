package pw.telm.telmbackend.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Image {
    @Id
    @GeneratedValue
    private Integer idImage;

    private String type;
    private Integer instance;
    private String samplePerPixel;
    private String photometricInterpretation;
    private Integer rowsImage;
    private Integer columnsImage;
    private Integer bitsAllocated;
    private Integer bitsStored;
    private Integer highBit;
    private String pixelRepresentation;
    private String pixelSpacing;
    private Integer numFrames;

    @ManyToOne
    @JoinColumn(name = "id_series", referencedColumnName = "idSeries", nullable = false)
    private Series series;
}
