package pw.telm.telmbackend.model;
import jakarta.persistence.*;


@Entity

public class TextStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTextStudy;

    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_study", referencedColumnName = "idStudy", nullable = false)
    private Study study;
}

