package vn.edu.fpt.mss.service;

import java.util.List;
import vn.edu.fpt.mss.dto.request.ArtistRequest;
import vn.edu.fpt.mss.dto.response.ArtistResponse;

public interface ArtistService {

    List<ArtistResponse> findAll();

    ArtistResponse findById(Integer id);

    ArtistResponse create(ArtistRequest request);

    ArtistResponse update(Integer id, ArtistRequest request);

    void delete(Integer id);
}