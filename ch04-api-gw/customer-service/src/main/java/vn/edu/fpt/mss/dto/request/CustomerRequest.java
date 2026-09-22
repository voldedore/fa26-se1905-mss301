package vn.edu.fpt.mss.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Customer create/update payload")
public class CustomerRequest {

    @NotBlank(message = "FirstName is required")
    @Size(max = 40, message = "FirstName must be at most 40 characters")
    @Schema(example = "Luis")
    private String firstName;

    @NotBlank(message = "LastName is required")
    @Size(max = 20, message = "LastName must be at most 20 characters")
    @Schema(example = "Goncalves")
    private String lastName;

    @Size(max = 80, message = "Company must be at most 80 characters")
    @Schema(example = "Embraer")
    private String company;

    @Size(max = 70, message = "Address must be at most 70 characters")
    @Schema(example = "Av. Brigadeiro Faria Lima, 2170")
    private String address;

    @Size(max = 40, message = "City must be at most 40 characters")
    @Schema(example = "São José dos Campos")
    private String city;

    @Size(max = 40, message = "State must be at most 40 characters")
    @Schema(example = "SP")
    private String state;

    @Size(max = 40, message = "Country must be at most 40 characters")
    @Schema(example = "Brazil")
    private String country;

    @Size(max = 10, message = "PostalCode must be at most 10 characters")
    @Schema(example = "12227-000")
    private String postalCode;

    @Size(max = 24, message = "Phone must be at most 24 characters")
    @Schema(example = "+55 (12) 3923-5555")
    private String phone;

    @Size(max = 24, message = "Fax must be at most 24 characters")
    @Schema(example = "+55 (12) 3923-5566")
    private String fax;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 60, message = "Email must be at most 60 characters")
    @Schema(example = "luisg@embraer.com.br")
    private String email;

    @Schema(example = "3")
    private Integer supportRepId;
}