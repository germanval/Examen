package com.examen.pe.Examen01.controller;

import com.examen.pe.Examen01.model.bd.Usuario;
import com.examen.pe.Examen01.model.dto.ResultadoDto;
import com.examen.pe.Examen01.model.dto.UsuarioDto;
import com.examen.pe.Examen01.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/seguridad")
public class SeguridadController {
    private UsuarioService usuarioService;
    @GetMapping("/usuario")
    public String frmMantUsuario(Model model){
        model.addAttribute("listaUsuarios",
                usuarioService.listarUsuario());
        return "seguridad/formusuario";
    }
    @PostMapping("/usuario")
    @ResponseBody
    public ResultadoDto registrarUsuario(@RequestBody UsuarioDto usuarioDto){
        String mensaje = "Usuario registrado correctamente";
        boolean respuesta = true;
        try {
            Usuario usuario = new Usuario();
            usuario.setNombres(usuarioDto.getNombres());
            usuario.setApellidos(usuarioDto.getApellidos());
            if(usuarioDto.getIdusuario() > 0){
                usuario.setIdusuario(usuarioDto.getIdusuario());
                usuario.setActivo(usuarioDto.getActivo());
                usuarioService.actualizarUsuario(usuario);
            }else{
                usuario.setNomusuario(usuarioDto.getNomusuario());
                usuario.setEmail(usuarioDto.getEmail());
                usuarioService.guardarUsuario(usuario);
            }
        }catch (Exception ex){
            mensaje = "Usuario no registrado, error en la BD";
            respuesta = false;
        }
        return ResultadoDto.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
    @PostMapping("/registrar")
    @ResponseBody
    public ResultadoDto cambiarcontrusuario(@RequestBody UsuarioDto usuarioDto){
        String mensaje = "";
        boolean respuesta = false;
        try {
            // Verificar si las contraseñas coinciden
            if (!usuarioDto.getContrasena().equals(usuarioDto.getConfirmacontrasena())) {
                mensaje = "Las contraseñas no coinciden";
            } else {
                // Aquí podrías realizar la lógica para actualizar la contraseña del usuario en la base de datos
                // Por ejemplo:
                Usuario usuario = usuarioService.buscarUsuarioxIdUsuario(usuarioDto.getIdusuario());
                usuario.setPassword(usuarioDto.getContrasena());
                usuarioService.actualizarUsuario(usuario);

                mensaje = "Contraseña actualizada correctamente";
                respuesta = true;
            }
        } catch (Exception ex) {
            mensaje = "Error al actualizar la contraseña: " + ex.getMessage();
        }
        return ResultadoDto.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @GetMapping("/usuario/{id}")
    @ResponseBody
    public Usuario frmMantUsuario(@PathVariable("id") int id){
        return usuarioService.buscarUsuarioxIdUsuario(id);
    }
    @GetMapping("/usuario/lista")
    @ResponseBody
    public List<Usuario> listaUsuario(){
        return usuarioService.listarUsuario();
    }

}
