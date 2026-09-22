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
@Schema(description = "Customer data fetched from customer-service")
public class CustomerInfo {

    @Schema(example = "1")
    private Integer customerId;

    @Schema(example = "Luis")
    private String firstName;

    @Schema(example = "Goncalves")
    private String lastName;

    @Schema(example = "Embraer")
    private String company;

    @Schema(example = "Av. Brigadeiro Faria Lima, 2170")
    private String address;

    @Schema(example = "São José dos Campos")
    private String city;

    @Schema(example = "SP")
    private String state;

    @Schema(example = "Brazil")
    private String country;

    @Schema(example = "12227-000")
    private String postalCode;

    @Schema(example = "+55 (12) 3923-5555")
    private String phone;

    @Schema(example = "+55 (12) 3923-5566")
    private String fax;

    @Schema(example = "luisg@embraer.com.br")
    private String email;

    @Schema(example = "3")
    private Integer supportRepId;

    @Schema(example = "Jane Peacock")
    private String supportRepName;
}