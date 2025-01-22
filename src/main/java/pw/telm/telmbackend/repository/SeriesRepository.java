package pw.telm.telmbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pw.telm.telmbackend.model.Series;


import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Integer> {
    Optional<Series> findByUidSeries(String uidSeries);
}