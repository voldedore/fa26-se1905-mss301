package vn.edu.fpt.mss.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.fpt.mss.dto.request.GenreRequest;
import vn.edu.fpt.mss.dto.response.GenreResponse;
import vn.edu.fpt.mss.entity.Genre;
import vn.edu.fpt.mss.exception.ResourceNotFoundException;
import vn.edu.fpt.mss.repository.GenreRepository;
import vn.edu.fpt.mss.service.GenreService;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<GenreResponse> findAll() {
        return genreRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public GenreResponse findById(Integer id) {
        return toResponse(findEntity(id));
    }

    @Override
    public GenreResponse create(GenreRequest request) {
        return toResponse(genreRepository.save(toEntity(request, new Genre())));
    }

    @Override
    public GenreResponse update(Integer id, GenreRequest request) {
        return toResponse(genreRepository.save(toEntity(request, findEntity(id))));
    }

    @Override
    public void delete(Integer id) {
        genreRepository.delete(findEntity(id));
    }

    private Genre findEntity(Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", id));
    }

    private Genre toEntity(GenreRequest request, Genre genre) {
        genre.setName(request.getName());
        return genre;
    }

    private GenreResponse toResponse(Genre genre) {
        return GenreResponse.builder()
                .genreId(genre.getGenreId())
                .name(genre.getName())
                .build();
    }
}