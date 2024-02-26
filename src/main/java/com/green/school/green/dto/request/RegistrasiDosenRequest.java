package com.green.school.green.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class RegistrasiDosenRequest {
    @NotBlank(message = "cannot be null and empty")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "cannot be null and empty")
    private String kelasDosen;

}
