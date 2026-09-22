package vn.edu.fpt.mss.service;

import java.util.List;
import vn.edu.fpt.mss.dto.request.AlbumRequest;
import vn.edu.fpt.mss.dto.response.AlbumResponse;

public interface AlbumService {

    List<AlbumResponse> findAll();

    AlbumResponse findById(Integer id);

    AlbumResponse create(AlbumRequest request);

    AlbumResponse update(Integer id, AlbumRequest request);

    void delete(Integer id);
}