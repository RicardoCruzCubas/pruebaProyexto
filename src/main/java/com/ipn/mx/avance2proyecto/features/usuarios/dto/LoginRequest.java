package com.ipn.mx.avance2proyecto.features.usuarios.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}