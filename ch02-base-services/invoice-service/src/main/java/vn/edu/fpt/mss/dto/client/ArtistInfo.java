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
@Schema(description = "Artist data fetched from catalog-service")
public class ArtistInfo {

    @Schema(example = "1")
    private Integer artistId;

    @Schema(example = "AC/DC")
    private String name;
}