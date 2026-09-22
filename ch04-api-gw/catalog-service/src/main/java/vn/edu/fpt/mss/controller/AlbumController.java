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
import vn.edu.fpt.mss.dto.request.AlbumRequest;
import vn.edu.fpt.mss.dto.response.AlbumResponse;
import vn.edu.fpt.mss.service.AlbumService;

@RestController
@RequestMapping("/api/v1/albums")
@RequiredArgsConstructor
@Tag(name = "Albums", description = "CRUD operations for albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    @Operation(summary = "Get all albums")
    public List<AlbumResponse> findAll() {
        return albumService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get album by id")
    public AlbumResponse findById(@PathVariable Integer id) {
        return albumService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new album")
    public AlbumResponse create(@Valid @RequestBody AlbumRequest request) {
        return albumService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an album")
    public AlbumResponse update(@PathVariable Integer id, @Valid @RequestBody AlbumRequest request) {
        return albumService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an album")
    public void delete(@PathVariable Integer id) {
        albumService.delete(id);
    }
}