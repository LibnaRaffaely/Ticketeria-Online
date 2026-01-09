package com.project.ticketeria.modules.userBasic.dtos;

import org.hibernate.validator.constraints.Length;

import com.project.ticketeria.modules.userBasic.enums.GenderEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String cpf;

    private String phone;

    @NotBlank
    @Length(min = 8, max = 100)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "A senha deve conter pelo menos:\n1 numero\n 1 letra Maiúscula e minuscula \n1 Caractere especial \nSem espaço em branco")
    private String password;

    @NotBlank
    private String adress;

    @NotNull
    private GenderEnum gender;

    @NotBlank
    private String dateOfBirth;

}
