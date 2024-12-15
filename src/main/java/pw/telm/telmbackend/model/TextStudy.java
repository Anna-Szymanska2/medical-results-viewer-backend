package pw.telm.telmbackend.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TextStudy {
    @Id
    @GeneratedValue
    private Integer idTextStudy;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "id_study", referencedColumnName = "idStudy", nullable = false)
    private Study study;
}

