package vn.edu.fpt.mss.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Track data fetched from catalog-service")
public class TrackInfo {

    @Schema(example = "1")
    private Integer trackId;

    @Schema(example = "Highway to Hell")
    private String name;

    private AlbumInfo album;

    private MediaTypeInfo mediaType;

    private GenreInfo genre;

    @Schema(example = "Angus Young / Malcolm Young")
    private String composer;

    @Schema(example = "253256")
    private Integer milliseconds;

    @Schema(example = "8180396")
    private Integer bytes;

    @Schema(example = "0.99")
    private java.math.BigDecimal unitPrice;
}