package com.examen.pe.Examen01.service;

import com.examen.pe.Examen01.model.bd.Rol;
import com.examen.pe.Examen01.model.bd.Usuario;
import com.examen.pe.Examen01.repository.RolRepository;
import com.examen.pe.Examen01.repository.UsuarioRepository;
import com.examen.pe.Examen01.util.RandomPassword;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService{

    private RandomPassword randomPassword;
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    @Override
    public Usuario buscarUsuarioxNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setActivo(true);
        Rol usuarioRol=rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        usuario.setPassword(randomPassword.generar(7));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setActivo(true);
        Rol usuarioRol=rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        return usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizarUsuario(
                usuario.getNombres(),usuario.getApellidos(),
                usuario.getActivo(),usuario.getIdusuario()
        );
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioxIdUsuario(Integer idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }
}
