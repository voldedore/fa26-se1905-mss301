package vn.edu.fpt.mss.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.mss.dto.request.MediaTypeRequest;
import vn.edu.fpt.mss.dto.response.MediaTypeResponse;
import vn.edu.fpt.mss.service.MediaTypeService;

@RestController
@RequestMapping("/api/v1/media-types")
@RequiredArgsConstructor
@Tag(name = "MediaTypes", description = "CRUD operations for media types")
public class MediaTypeController {

    private final MediaTypeService mediaTypeService;

    @GetMapping
    @Operation(summary = "Get all media types")
    public List<MediaTypeResponse> findAll() {
        return mediaTypeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get media type by id")
    public MediaTypeResponse findById(@PathVariable Integer id) {
        return mediaTypeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new media type")
    public MediaTypeResponse create(@Valid @RequestBody MediaTypeRequest request) {
        return mediaTypeService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a media type")
    public MediaTypeResponse update(@PathVariable Integer id, @Valid @RequestBody MediaTypeRequest request) {
        return mediaTypeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a media type")
    public void delete(@PathVariable Integer id) {
        mediaTypeService.delete(id);
    }
}