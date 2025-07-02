package com.scm.scm20.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {
    @NotBlank(message = "Username is required")
    @Size(min=3,message = "Min 3 Characters is required")
    private String name;
    @Email(message="Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "password cannot be blank")
    @Size(min=6,message = "Minimum 6 charcters is required")
    private String password;
    @NotBlank(message = "About is required")
    private String about;
    @Size(min=8,max=12,message="Invalid number")
    private String phoneNumber;

}
