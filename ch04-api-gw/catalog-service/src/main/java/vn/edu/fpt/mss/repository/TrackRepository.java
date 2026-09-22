package vn.edu.fpt.mss.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.mss.entity.Track;

public interface TrackRepository extends JpaRepository<Track, Integer> {

    List<Track> findByGenreGenreIdOrderByNameAsc(Integer genreId);
}
