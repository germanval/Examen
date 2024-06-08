package com.examen.pe.Examen01.service;

import com.examen.pe.Examen01.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario buscarUsuarioxNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
    Usuario registrarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    List<Usuario> listarUsuario();
    Usuario buscarUsuarioxIdUsuario(Integer idusuario);
}
