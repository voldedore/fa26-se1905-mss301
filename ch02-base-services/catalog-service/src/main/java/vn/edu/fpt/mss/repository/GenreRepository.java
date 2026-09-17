package vn.edu.fpt.mss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.mss.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
