package com.examen.pe.Examen01.repository;

import com.examen.pe.Examen01.model.bd.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNomUsuario(String nomusuario);
    void
}
