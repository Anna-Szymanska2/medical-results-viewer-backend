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

    public Integer getIdFrame() {
        return idFrame;
    }

    public void setIdFrame(Integer idFrame) {
        this.idFrame = idFrame;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}



