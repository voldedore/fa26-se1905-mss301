package vn.edu.fpt.mss.service;

import java.util.List;
import vn.edu.fpt.mss.dto.request.MediaTypeRequest;
import vn.edu.fpt.mss.dto.response.MediaTypeResponse;

public interface MediaTypeService {

    List<MediaTypeResponse> findAll();

    MediaTypeResponse findById(Integer id);

    MediaTypeResponse create(MediaTypeRequest request);

    MediaTypeResponse update(Integer id, MediaTypeRequest request);

    void delete(Integer id);
}