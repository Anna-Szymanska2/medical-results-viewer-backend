package pw.telm.telmbackend.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Series {
    @Id
    @GeneratedValue
    private Integer idSeries;

    private String modality;
    private String uidSeries;
    private String numBm;
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "id_study", referencedColumnName = "idStudy", nullable = false)
    private Study study;
}
