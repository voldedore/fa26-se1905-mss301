package vn.edu.fpt.mss.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Artist representation")
public class ArtistResponse {

    @Schema(example = "1")
    private Integer artistId;

    @Schema(example = "AC/DC")
    private String name;
}