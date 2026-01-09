package com.project.ticketeria.modules.userBasic.useCases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ticketeria.exceptions.UserFoundCPFException;
import com.project.ticketeria.exceptions.UserFoundEmailException;
import com.project.ticketeria.modules.userBasic.UserBasicEntity;
import com.project.ticketeria.modules.userBasic.UserBasicRepository;
import com.project.ticketeria.modules.userBasic.dtos.RequestUserDTO;
import com.project.ticketeria.modules.userBasic.dtos.ResponseUserDTO;
import com.project.ticketeria.modules.userBasic.enums.RoleEnum;

@Service
public class CreateUserBasicUseCase {
    @Autowired
    private UserBasicRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseUserDTO execute(RequestUserDTO userDTO) {
        this.userRepository.findByCpf(userDTO.getCpf())
                .ifPresent((user) -> {
                    throw new UserFoundCPFException();
                });

        this.userRepository.findByEmail(userDTO.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundEmailException();
                });

        // codificar senha
        var passwordNew = this.passwordEncoder.encode(userDTO.getPassword());

        // converter tipo da data aniversário
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirthNew;
        try {
            dateOfBirthNew = LocalDate.parse(userDTO.getDateOfBirth(), formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Date format of DateOfBirth (yyyy-MM-dd)");
        }

        // criar entidade
        var userSave = new UserBasicEntity();
        userSave.setName(userDTO.getName());
        userSave.setEmail(userDTO.getEmail());
        userSave.setCpf(userDTO.getCpf());
        userSave.setPhone(userDTO.getPhone());
        userSave.setPassword(passwordNew);
        userSave.setAdress(userDTO.getAdress());
        userSave.setDateOfBirth(dateOfBirthNew);
        userSave.setGender(userDTO.getGender());
        userSave.setRole(RoleEnum.USER);
        userSave.setDeleted(false);
        userSave.setCash(0.0);

        // salvar entidade no BD
        var userSaved = this.userRepository.save(userSave);

        // retornar resposta
        var response = ResponseUserDTO.builder()
                .id(userSaved.getId())
                .username(userSaved.getUsername())
                .email(userSaved.getEmail())
                .cpf(userSaved.getCpf())
                .password(userSaved.getPassword())
                .phone(userSaved.getPhone())
                .adress(userSaved.getAdress())
                .gender(userSaved.getGender())
                .role(userSaved.getRole())
                .dateOfBirth(userSaved.getDateOfBirth())
                .createdAt(userSaved.getCreatedAt())
                .updatedAt(userSaved.getUpdatedAt())
                .deleted(userSaved.isDeleted())
                .cash(userSaved.getCash())
                .build();

        return response;
    }
}
