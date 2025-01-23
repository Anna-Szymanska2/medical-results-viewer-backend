package pw.telm.telmbackend.DTOs.mappers;

import org.springframework.stereotype.Component;
import pw.telm.telmbackend.DTOs.model.ImageDto;
import pw.telm.telmbackend.DTOs.model.SeriesDto;
import pw.telm.telmbackend.model.Series;

import java.util.List;

/**
 * Mapper class to convert between domain entities and DTOs for Series objects.
 */
@Component
public class SeriesMapper {
    /**
     * Converts a Series entity and a list of ImageDto objects to a SeriesDto.
     *
     * @param series         the Series entity to convert
     * @param imageDtoList   the list of ImageDto objects associated with the Series
     * @return a SeriesDto object containing the data from the Series entity and the ImageDto list
     */
    public static SeriesDto toSeriesDto(Series series, List<ImageDto> imageDtoList) {
        SeriesDto seriesDto = new SeriesDto();
        seriesDto.setSeriesId(series.getIdSeries());
        seriesDto.setModality(series.getModality());
        seriesDto.setImages(imageDtoList);

        return seriesDto;
    }

}
