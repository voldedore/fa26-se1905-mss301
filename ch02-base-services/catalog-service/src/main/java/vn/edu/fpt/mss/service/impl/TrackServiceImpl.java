package vn.edu.fpt.mss.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.mss.dto.request.TrackRequest;
import vn.edu.fpt.mss.dto.response.AlbumResponse;
import vn.edu.fpt.mss.dto.response.ArtistResponse;
import vn.edu.fpt.mss.dto.response.GenreResponse;
import vn.edu.fpt.mss.dto.response.MediaTypeResponse;
import vn.edu.fpt.mss.dto.response.TrackResponse;
import vn.edu.fpt.mss.entity.Album;
import vn.edu.fpt.mss.entity.Genre;
import vn.edu.fpt.mss.entity.MediaType;
import vn.edu.fpt.mss.entity.Track;
import vn.edu.fpt.mss.exception.ResourceNotFoundException;
import vn.edu.fpt.mss.repository.AlbumRepository;
import vn.edu.fpt.mss.repository.GenreRepository;
import vn.edu.fpt.mss.repository.MediaTypeRepository;
import vn.edu.fpt.mss.repository.TrackRepository;
import vn.edu.fpt.mss.service.TrackService;

@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;
    private final MediaTypeRepository mediaTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TrackResponse> findAll() {
        return trackRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrackResponse> findByGenreId(Integer genreId) {
        return trackRepository.findByGenreGenreIdOrderByNameAsc(genreId)
                .stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TrackResponse findById(Integer id) {
        return toResponse(findEntity(id));
    }

    @Override
    @Transactional
    public TrackResponse create(TrackRequest request) {
        return toResponse(trackRepository.save(toEntity(request, new Track())));
    }

    @Override
    @Transactional
    public TrackResponse update(Integer id, TrackRequest request) {
        return toResponse(trackRepository.save(toEntity(request, findEntity(id))));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        trackRepository.delete(findEntity(id));
    }

    private Track findEntity(Integer id) {
        return trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track", id));
    }

    private Track toEntity(TrackRequest request, Track track) {
        track.setName(request.getName());
        track.setAlbum(request.getAlbumId() == null
                ? null
                : albumRepository.findById(request.getAlbumId())
                        .orElseThrow(() -> new ResourceNotFoundException("Album", request.getAlbumId())));
        track.setGenre(request.getGenreId() == null
                ? null
                : genreRepository.findById(request.getGenreId())
                        .orElseThrow(() -> new ResourceNotFoundException("Genre", request.getGenreId())));
        track.setMediaType(mediaTypeRepository.findById(request.getMediaTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("MediaType", request.getMediaTypeId())));
        track.setComposer(request.getComposer());
        track.setMilliseconds(request.getMilliseconds());
        track.setBytes(request.getBytes());
        track.setUnitPrice(request.getUnitPrice());
        track.setDeleted(false);
        return track;
    }

    private TrackResponse toResponse(Track track) {
        Album album = track.getAlbum();
        Genre genre = track.getGenre();
        return TrackResponse.builder()
                .trackId(track.getTrackId())
                .name(track.getName())
                .album(album == null ? null : AlbumResponse.builder()
                        .albumId(album.getAlbumId())
                        .title(album.getTitle())
                        .artist(ArtistResponse.builder()
                                .artistId(album.getArtist().getArtistId())
                                .name(album.getArtist().getName())
                                .build())
                        .build())
                .mediaType(MediaTypeResponse.builder()
                        .mediaTypeId(track.getMediaType().getMediaTypeId())
                        .name(track.getMediaType().getName())
                        .build())
                .genre(genre == null ? null : GenreResponse.builder()
                        .genreId(genre.getGenreId())
                        .name(genre.getName())
                        .build())
                .composer(track.getComposer())
                .milliseconds(track.getMilliseconds())
                .bytes(track.getBytes())
                .unitPrice(track.getUnitPrice())
                .build();
    }
}