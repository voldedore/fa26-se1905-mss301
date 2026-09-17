package vn.edu.fpt.mss.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.fpt.mss.dto.request.MediaTypeRequest;
import vn.edu.fpt.mss.dto.response.MediaTypeResponse;
import vn.edu.fpt.mss.entity.MediaType;
import vn.edu.fpt.mss.exception.ResourceNotFoundException;
import vn.edu.fpt.mss.repository.MediaTypeRepository;
import vn.edu.fpt.mss.service.MediaTypeService;

@Service
@RequiredArgsConstructor
public class MediaTypeServiceImpl implements MediaTypeService {

    private final MediaTypeRepository mediaTypeRepository;

    @Override
    public List<MediaTypeResponse> findAll() {
        return mediaTypeRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public MediaTypeResponse findById(Integer id) {
        return toResponse(findEntity(id));
    }

    @Override
    public MediaTypeResponse create(MediaTypeRequest request) {
        return toResponse(mediaTypeRepository.save(toEntity(request, new MediaType())));
    }

    @Override
    public MediaTypeResponse update(Integer id, MediaTypeRequest request) {
        return toResponse(mediaTypeRepository.save(toEntity(request, findEntity(id))));
    }

    @Override
    public void delete(Integer id) {
        mediaTypeRepository.delete(findEntity(id));
    }

    private MediaType findEntity(Integer id) {
        return mediaTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MediaType", id));
    }

    private MediaType toEntity(MediaTypeRequest request, MediaType mediaType) {
        mediaType.setName(request.getName());
        return mediaType;
    }

    private MediaTypeResponse toResponse(MediaType mediaType) {
        return MediaTypeResponse.builder()
                .mediaTypeId(mediaType.getMediaTypeId())
                .name(mediaType.getName())
                .build();
    }
}