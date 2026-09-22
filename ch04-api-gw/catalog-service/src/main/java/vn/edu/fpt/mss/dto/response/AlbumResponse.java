package vn.edu.fpt.mss.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Album representation")
public class AlbumResponse {

    @Schema(example = "1")
    private Integer albumId;

    @Schema(example = "For Those About to Rock We Salute You")
    private String title;

    private ArtistResponse artist;
}