package vn.edu.fpt.mss.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.fpt.mss.dto.request.ArtistRequest;
import vn.edu.fpt.mss.dto.response.ArtistResponse;
import vn.edu.fpt.mss.entity.Artist;
import vn.edu.fpt.mss.exception.ResourceNotFoundException;
import vn.edu.fpt.mss.repository.ArtistRepository;
import vn.edu.fpt.mss.service.ArtistService;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Override
    public List<ArtistResponse> findAll() {
        return artistRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public ArtistResponse findById(Integer id) {
        return toResponse(findEntity(id));
    }

    @Override
    public ArtistResponse create(ArtistRequest request) {
        return toResponse(artistRepository.save(toEntity(request, new Artist())));
    }

    @Override
    public ArtistResponse update(Integer id, ArtistRequest request) {
        return toResponse(artistRepository.save(toEntity(request, findEntity(id))));
    }

    @Override
    public void delete(Integer id) {
        artistRepository.delete(findEntity(id));
    }

    private Artist findEntity(Integer id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist", id));
    }

    private Artist toEntity(ArtistRequest request, Artist artist) {
        artist.setName(request.getName());
        return artist;
    }

    private ArtistResponse toResponse(Artist artist) {
        return ArtistResponse.builder()
                .artistId(artist.getArtistId())
                .name(artist.getName())
                .build();
    }
}