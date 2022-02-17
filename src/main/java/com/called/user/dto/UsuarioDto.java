package com.called.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private String nome;
    private String email;
    private String regras;
    private String local;

}