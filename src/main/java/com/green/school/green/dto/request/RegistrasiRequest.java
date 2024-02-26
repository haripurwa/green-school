package com.green.school.green.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class RegistrasiRequest {
    @NotBlank(message = "cannot be null and empty")
    private String name;
    @NotBlank(message = "cannot be null and empty")
    private String nik;
    @Email(message = "Nik should be valid")
    private String email;
    @NotBlank(message = "cannot be null and empty")
    private String gender;
    @NotBlank(message = "cannot be null and empty")
    private String address;
    @NotBlank(message = "cannot be null and empty")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name must contain only alphabetic characters")
    private String pob;
    @NotBlank(message = "cannot be null and empty")
    private String dob;
}
