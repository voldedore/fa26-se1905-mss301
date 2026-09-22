package vn.edu.fpt.mss.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Invoice line payload. Unit price is resolved from catalog-service.")
public class InvoiceLineRequest {

    @NotNull(message = "TrackId is required")
    @Schema(example = "1")
    private Integer trackId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    @Schema(example = "1")
    private Integer quantity;
}
