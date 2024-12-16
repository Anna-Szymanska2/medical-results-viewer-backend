package pw.telm.telmbackend.model;
import jakarta.persistence.*;

@Entity

public class Frame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFrame;

    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_image", referencedColumnName = "idImage", nullable = false)
    private Image image;
}


