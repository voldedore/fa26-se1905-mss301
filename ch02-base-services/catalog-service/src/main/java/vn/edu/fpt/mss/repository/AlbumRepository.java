package vn.edu.fpt.mss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.mss.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
