package pw.telm.telmbackend.DTOs.model;



/**
 * Data transfer object (DTO) representing a image.
 */

public class ImageDto {
    private Integer idImage;
    private String type;
    private Integer numberOfFrames;

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

    public Integer getNumberOfFrames() {
        return numberOfFrames;
    }

    public void setNumberOfFrames(Integer numberOfFrames) {
        this.numberOfFrames = numberOfFrames;
    }
}
