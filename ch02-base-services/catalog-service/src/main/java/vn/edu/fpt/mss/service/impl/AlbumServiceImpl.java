package vn.edu.fpt.mss.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.mss.dto.request.AlbumRequest;
import vn.edu.fpt.mss.dto.response.AlbumResponse;
import vn.edu.fpt.mss.dto.response.ArtistResponse;
import vn.edu.fpt.mss.entity.Album;
import vn.edu.fpt.mss.entity.Artist;
import vn.edu.fpt.mss.exception.ResourceNotFoundException;
import vn.edu.fpt.mss.repository.AlbumRepository;
import vn.edu.fpt.mss.repository.ArtistRepository;
import vn.edu.fpt.mss.service.AlbumService;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AlbumResponse> findAll() {
        return albumRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AlbumResponse findById(Integer id) {
        return toResponse(findEntity(id));
    }

    @Override
    @Transactional
    public AlbumResponse create(AlbumRequest request) {
        return toResponse(albumRepository.save(toEntity(request, new Album())));
    }

    @Override
    @Transactional
    public AlbumResponse update(Integer id, AlbumRequest request) {
        return toResponse(albumRepository.save(toEntity(request, findEntity(id))));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        albumRepository.delete(findEntity(id));
    }

    private Album findEntity(Integer id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album", id));
    }

    private Album toEntity(AlbumRequest request, Album album) {
        Artist artist = artistRepository.findById(request.getArtistId())
                .orElseThrow(() -> new ResourceNotFoundException("Artist", request.getArtistId()));
        album.setTitle(request.getTitle());
        album.setArtist(artist);
        return album;
    }

    private AlbumResponse toResponse(Album album) {
        Artist artist = album.getArtist();
        return AlbumResponse.builder()
                .albumId(album.getAlbumId())
                .title(album.getTitle())
                .artist(ArtistResponse.builder()
                        .artistId(artist.getArtistId())
                        .name(artist.getName())
                        .build())
                .build();
    }
}