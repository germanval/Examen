package com.examen.pe.Examen01.repository;

import com.examen.pe.Examen01.model.bd.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository  extends JpaRepository <Rol,Integer> {

    Rol findByNomRol(String nomrol);
}
