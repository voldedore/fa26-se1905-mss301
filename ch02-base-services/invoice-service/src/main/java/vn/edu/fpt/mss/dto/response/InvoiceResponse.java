package vn.edu.fpt.mss.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import vn.edu.fpt.mss.dto.client.CustomerInfo;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Invoice representation")
public class InvoiceResponse {

    @Schema(example = "1")
    private Integer invoiceId;

    @Schema(example = "1")
    private Integer customerId;

    @Schema(example = "Luis")
    private String customerFirstName;

    @Schema(example = "Goncalves")
    private String customerLastName;

    @Schema(example = "2021-01-01T00:00:00")
    private LocalDateTime invoiceDate;

    private String billingAddress;

    private String billingCity;

    private String billingState;

    private String billingCountry;

    private String billingPostalCode;

    @Schema(example = "1.98")
    private BigDecimal total;

    private List<InvoiceLineResponse> lines;

    @Schema(description = "Full customer detail from customer-service; only customerId+firstName+lastName when unreachable")
    private CustomerInfo customer;
}