package vn.edu.fpt.mss.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Invoice create/update payload. Billing fields are auto-filled from customer-service.")
public class InvoiceRequest {

    @NotNull(message = "CustomerId is required")
    @Schema(example = "1")
    private Integer customerId;

    @Schema(example = "2026-01-01T10:00:00")
    private LocalDateTime invoiceDate;

    @NotEmpty(message = "At least one line is required")
    @Valid
    @Schema(description = "Invoice lines")
    private List<InvoiceLineRequest> lines;
}
