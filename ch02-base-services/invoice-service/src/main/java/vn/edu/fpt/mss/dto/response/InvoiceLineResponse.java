package vn.edu.fpt.mss.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import vn.edu.fpt.mss.dto.client.TrackInfo;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Invoice line representation")
public class InvoiceLineResponse {

    @Schema(example = "1")
    private Integer invoiceLineId;

    @Schema(example = "1")
    private Integer invoiceId;

    @Schema(example = "1")
    private Integer trackId;

    @Schema(example = "For Those About to Rock (We Salute You)")
    private String trackName;

    @Schema(example = "0.99")
    private BigDecimal unitPrice;

    @Schema(example = "1")
    private Integer quantity;

    @Schema(example = "0.99")
    private BigDecimal lineTotal;

    @Schema(description = "Full track detail from catalog-service; only trackId+name (snapshot) when catalog is unreachable")
    private TrackInfo track;
}