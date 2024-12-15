package pw.telm.telmbackend.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Frame {
    @Id
    @GeneratedValue
    private Integer idFrame;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "id_image", referencedColumnName = "idImage", nullable = false)
    private Image image;
}

