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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.mss.dto.request.TrackRequest;
import vn.edu.fpt.mss.dto.response.TrackResponse;
import vn.edu.fpt.mss.service.TrackService;

@RestController
@RequestMapping("/api/v1/tracks")
@RequiredArgsConstructor
@Tag(name = "Tracks", description = "CRUD operations for tracks (soft delete)")
public class TrackController {

    private final TrackService trackService;

    @GetMapping
    @Operation(summary = "Get all tracks, optionally filtered by genreId")
    public List<TrackResponse> findAll(@RequestParam(required = false) Integer genreId) {
        return genreId == null ? trackService.findAll() : trackService.findByGenreId(genreId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get track by id")
    public TrackResponse findById(@PathVariable Integer id) {
        return trackService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new track")
    public TrackResponse create(@Valid @RequestBody TrackRequest request) {
        return trackService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a track")
    public TrackResponse update(@PathVariable Integer id, @Valid @RequestBody TrackRequest request) {
        return trackService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Soft delete a track")
    public void delete(@PathVariable Integer id) {
        trackService.delete(id);
    }
}