package vn.edu.fpt.mss.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Track create/update payload")
public class TrackRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 200, message = "Name must be at most 200 characters")
    @Schema(example = "Highway to Hell")
    private String name;

    @Schema(example = "1")
    private Integer albumId;

    @NotNull(message = "MediaTypeId is required")
    @Schema(example = "1")
    private Integer mediaTypeId;

    @Schema(example = "1")
    private Integer genreId;

    @Size(max = 220, message = "Composer must be at most 220 characters")
    @Schema(example = "Angus Young / Malcolm Young")
    private String composer;

    @NotNull(message = "Milliseconds is required")
    @Positive(message = "Milliseconds must be positive")
    @Schema(example = "253256")
    private Integer milliseconds;

    @Positive(message = "Bytes must be positive")
    @Schema(example = "8180396")
    private Integer bytes;

    @NotNull(message = "UnitPrice is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "UnitPrice must be greater than 0")
    @Schema(example = "0.99")
    private BigDecimal unitPrice;
}