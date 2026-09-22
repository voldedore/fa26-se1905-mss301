package vn.edu.fpt.mss.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "MediaType representation")
public class MediaTypeResponse {

    @Schema(example = "1")
    private Integer mediaTypeId;

    @Schema(example = "MPEG audio file")
    private String name;
}