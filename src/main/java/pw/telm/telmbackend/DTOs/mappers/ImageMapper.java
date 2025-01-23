package pw.telm.telmbackend.DTOs.mappers;
import org.springframework.stereotype.Component;
import pw.telm.telmbackend.DTOs.model.ImageDto;
import pw.telm.telmbackend.model.Image;

/**
 * Mapper class to convert between domain entities and DTOs for Image objects.
 */
@Component
public class ImageMapper {
    /**
     * Converts an Image entity to an ImageDto.
     *
     * @param image the Image entity to convert
     * @return an ImageDto object containing the data from the Image entity
     */
    public static ImageDto toImageDto(Image image){
        ImageDto imageDto = new ImageDto();

        imageDto.setIdImage(image.getIdImage());
        imageDto.setType(image.getType());
        imageDto.setNumberOfFrames(image.getNumFrames());

        return imageDto;
    }
}
