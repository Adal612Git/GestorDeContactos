package com.gestordecontactos.dao;

import com.gestordecontactos.model.Contacto;

import java.util.List;
import java.util.Optional;

public interface ContactoDAO {
    void create(Contacto contacto);
    Optional<Contacto> findById(int id);
    List<Contacto> findAll();
    void update(Contacto contacto);
    void delete(int id);
}
