package vn.edu.fpt.mss.service;

import java.util.List;
import vn.edu.fpt.mss.dto.request.GenreRequest;
import vn.edu.fpt.mss.dto.response.GenreResponse;

public interface GenreService {

    List<GenreResponse> findAll();

    GenreResponse findById(Integer id);

    GenreResponse create(GenreRequest request);

    GenreResponse update(Integer id, GenreRequest request);

    void delete(Integer id);
}