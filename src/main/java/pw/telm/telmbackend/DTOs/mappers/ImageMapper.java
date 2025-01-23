package pw.telm.telmbackend.DTOs.mappers;
import org.springframework.stereotype.Component;
import pw.telm.telmbackend.DTOs.model.ImageDto;
import pw.telm.telmbackend.model.Image;


@Component
public class ImageMapper {

    public static ImageDto toImageDto(Image image){
        ImageDto imageDto = new ImageDto();

        imageDto.setIdImage(image.getIdImage());
        imageDto.setType(image.getType());
        imageDto.setNumberOfFrames(image.getNumFrames());

        return imageDto;
    }
}
