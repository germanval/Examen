package com.examen.pe.Examen01.model.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private Integer idusuario;
    private String nomusuario;
    private String nombres;
    private String apellidos;
    private Boolean activo;
    private String email;
    private String contrasena;
    private String confirmacontrasena;

}
