package vn.edu.fpt.mss.service;

import java.util.List;
import vn.edu.fpt.mss.dto.request.TrackRequest;
import vn.edu.fpt.mss.dto.response.TrackResponse;

public interface TrackService {

    List<TrackResponse> findAll();

    List<TrackResponse> findByGenreId(Integer genreId);

    TrackResponse findById(Integer id);

    TrackResponse create(TrackRequest request);

    TrackResponse update(Integer id, TrackRequest request);

    void delete(Integer id);
}