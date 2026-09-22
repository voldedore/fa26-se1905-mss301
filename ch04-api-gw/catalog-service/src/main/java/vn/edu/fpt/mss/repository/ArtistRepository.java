package vn.edu.fpt.mss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.mss.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}
