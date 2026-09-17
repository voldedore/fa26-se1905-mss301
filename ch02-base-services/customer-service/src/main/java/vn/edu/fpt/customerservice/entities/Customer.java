package vn.edu.fpt.customerservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Nationalized;

@Entity
public class Customer {
    @Id
    @Column(name = "CustomerId")
    private Integer id;
    @Size(max = 80)
    @Nationalized
    @Column(name = "Company", length = 80)
    private String company;
    @Size(max = 70)
    @Nationalized
    @Column(name = "Address", length = 70)
    private String address;
    @Size(max = 40)
    @Nationalized
    @Column(name = "City", length = 40)
    private String city;
    @Size(max = 40)
    @Nationalized
    @Column(name = "State", length = 40)
    private String state;
    @Size(max = 40)
    @Nationalized
    @Column(name = "Country", length = 40)
    private String country;
    @Size(max = 10)
    @Nationalized
    @Column(name = "PostalCode", length = 10)
    private String postalCode;
    @Size(max = 24)
    @Nationalized
    @Column(name = "Phone", length = 24)
    private String phone;
    @Size(max = 24)
    @Nationalized
    @Column(name = "Fax", length = 24)
    private String fax;
    @Size(max = 60)
    @NotNull
    @Nationalized
    @Column(name = "Email", nullable = false, length = 60)
    private String email;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
