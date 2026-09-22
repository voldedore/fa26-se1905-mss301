package vn.edu.fpt.mss.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Employee representation")
public class EmployeeResponse {

    @Schema(example = "1")
    private Integer employeeId;

    @Schema(example = "Andrew")
    private String firstName;

    @Schema(example = "Adams")
    private String lastName;

    @Schema(example = "General Manager")
    private String title;

    @Schema(example = "2")
    private Integer reportsToId;

    @Schema(example = "Nancy Edwards")
    private String reportsToName;

    @Schema(example = "1962-02-18T00:00:00")
    private LocalDateTime birthDate;

    @Schema(example = "2002-08-14T00:00:00")
    private LocalDateTime hireDate;

    @Schema(example = "11120 Jasper Ave NW")
    private String address;

    @Schema(example = "Edmonton")
    private String city;

    @Schema(example = "AB")
    private String state;

    @Schema(example = "Canada")
    private String country;

    @Schema(example = "T5K 2N1")
    private String postalCode;

    @Schema(example = "+1 (780) 428-9482")
    private String phone;

    @Schema(example = "+1 (780) 428-3457")
    private String fax;

    @Schema(example = "andrew@chinookcorp.com")
    private String email;
}