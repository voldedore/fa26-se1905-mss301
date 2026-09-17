package vn.edu.fpt.mss.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Track representation")
public class TrackResponse {

    @Schema(example = "1")
    private Integer trackId;

    @Schema(example = "Highway to Hell")
    private String name;

    private AlbumResponse album;

    private MediaTypeResponse mediaType;

    private GenreResponse genre;

    @Schema(example = "Angus Young / Malcolm Young")
    private String composer;

    @Schema(example = "253256")
    private Integer milliseconds;

    @Schema(example = "8180396")
    private Integer bytes;

    @Schema(example = "0.99")
    private BigDecimal unitPrice;
}