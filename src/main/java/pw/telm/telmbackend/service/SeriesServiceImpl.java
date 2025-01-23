package pw.telm.telmbackend.service;

import org.springframework.stereotype.Service;
import pw.telm.telmbackend.model.Series;
import pw.telm.telmbackend.repository.SeriesRepository;

@Service
public class SeriesServiceImpl implements SeriesService{

    private final SeriesRepository seriesRepository;

    public SeriesServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public String getImageByIdAndNumber(Integer id_series, Integer image_number, Integer frame_number) {
        Series series = seriesRepository.findByIdSeries(id_series).orElse(null);
        return series.getImages().get(image_number-1).getFrames().get(frame_number-1).getFilePath();

    }
}
