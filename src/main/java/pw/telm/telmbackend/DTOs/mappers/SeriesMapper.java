package pw.telm.telmbackend.DTOs.mappers;

import org.springframework.stereotype.Component;
import pw.telm.telmbackend.DTOs.model.ImageDto;
import pw.telm.telmbackend.DTOs.model.SeriesDto;
import pw.telm.telmbackend.model.Series;

import java.util.List;


@Component
public class SeriesMapper {

    public static SeriesDto toSeriesDto(Series series, List<ImageDto> imageDtoList) {
        SeriesDto seriesDto = new SeriesDto();
        seriesDto.setSeriesId(series.getIdSeries());
        seriesDto.setModality(series.getModality());
        seriesDto.setImages(imageDtoList);

        return seriesDto;
    }

}
