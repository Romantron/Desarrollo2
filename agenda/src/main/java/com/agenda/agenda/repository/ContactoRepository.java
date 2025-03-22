package com.agenda.agenda.repository;

import com.agenda.agenda.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

    // Buscar un contacto por su teléfono (opcional)
    Optional<Contacto> findByTelefono(String telefono);
}

