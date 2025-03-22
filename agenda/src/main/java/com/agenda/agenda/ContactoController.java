package com.agenda.agenda;

import org.springframework.web.bind.annotation.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api/contactos") // Endpoint base
public class ContactoController {

	protected List<Contacto> contactos = new ArrayList<>();



    // Obtener todos los contactos
    @GetMapping
    public List<Contacto> getContactos() {
        return contactos;
    }

    // Agregar un nuevo contacto
    @PostMapping
    public String addContacto(@RequestBody Contacto contacto) {
        if (contacto.getNombre() == null) {
            return "El nombre no puede ser nulo.";
        }

        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(contacto.getNombre())) {
                return "El contacto ya existe.";
            }
        }

        contactos.add(contacto);
        return "Contacto agregado: " + contacto.getNombre();
    }

    // Eliminar un contacto por nombre
    @DeleteMapping("/{nombre}")
    public String deleteContacto(@PathVariable String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Nombre inválido.";
        }

        Iterator<Contacto> iterator = contactos.iterator();
        while (iterator.hasNext()) {
            Contacto c = iterator.next();
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                iterator.remove();
                return "Contacto eliminado: " + nombre;
            }
        }
        return "Contacto no encontrado.";
    }

    
    public void limpiarContactos() {
        this.contactos.clear();
    }
}
