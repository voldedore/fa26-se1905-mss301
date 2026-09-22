package vn.edu.fpt.mss.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Employee create/update payload")
public class EmployeeRequest {

    @NotBlank(message = "LastName is required")
    @Size(max = 20, message = "LastName must be at most 20 characters")
    @Schema(example = "Adams")
    private String lastName;

    @NotBlank(message = "FirstName is required")
    @Size(max = 20, message = "FirstName must be at most 20 characters")
    @Schema(example = "Andrew")
    private String firstName;

    @Size(max = 30, message = "Title must be at most 30 characters")
    @Schema(example = "General Manager")
    private String title;

    @Schema(example = "1")
    private Integer reportsToId;

    @Schema(example = "1962-02-18T00:00:00")
    private LocalDateTime birthDate;

    @Schema(example = "2002-08-14T00:00:00")
    private LocalDateTime hireDate;

    @Size(max = 70, message = "Address must be at most 70 characters")
    @Schema(example = "11120 Jasper Ave NW")
    private String address;

    @Size(max = 40, message = "City must be at most 40 characters")
    @Schema(example = "Edmonton")
    private String city;

    @Size(max = 40, message = "State must be at most 40 characters")
    @Schema(example = "AB")
    private String state;

    @Size(max = 40, message = "Country must be at most 40 characters")
    @Schema(example = "Canada")
    private String country;

    @Size(max = 10, message = "PostalCode must be at most 10 characters")
    @Schema(example = "T5K 2N1")
    private String postalCode;

    @Size(max = 24, message = "Phone must be at most 24 characters")
    private String phone;

    @Size(max = 24, message = "Fax must be at most 24 characters")
    private String fax;

    @Size(max = 60, message = "Email must be at most 60 characters")
    @Schema(example = "andrew@chinookcorp.com")
    private String email;
}