package com.agenda.agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ContactoControllerTest {

    private ContactoController contactoController;

    @BeforeEach
    void setUp() {
        contactoController = new ContactoController();
        contactoController.limpiarContactos(); // ✅ Asegurar un estado limpio
    }

    @Test
    void testAgregarContacto() {
        Contacto contacto = new Contacto("Juan Pérez", "3001234567", "juan.perez@email.com");
        String resultado = contactoController.addContacto(contacto);

        assertEquals("Contacto agregado: Juan Pérez", resultado);
        assertEquals(1, contactoController.getContactos().size());
        assertEquals("Juan Pérez", contactoController.getContactos().get(0).getNombre());
    }

    @Test
    void testAgregarContactoDuplicado() {
        Contacto contacto1 = new Contacto("Juan Pérez", "3001234567", "juan.perez@email.com");
        Contacto contacto2 = new Contacto("Juan Pérez", "3001234567", "juan.perez@email.com");

        contactoController.addContacto(contacto1);
        String resultado = contactoController.addContacto(contacto2);

        assertEquals("El contacto ya existe.", resultado); // Asegúrate de manejar esto en tu código
        assertEquals(1, contactoController.getContactos().size());
    }

    @Test
    void testObtenerContactos() {
        contactoController.addContacto(new Contacto("Juan Pérez", "3001234567", "juan.perez@email.com"));
        contactoController.addContacto(new Contacto("María Gómez", "3019876543", "maria.gomez@email.com"));

        List<Contacto> contactos = contactoController.getContactos();

        assertEquals(2, contactos.size());
        assertEquals("Juan Pérez", contactos.get(0).getNombre());
        assertEquals("María Gómez", contactos.get(1).getNombre());
    }

    @Test
    void testEliminarContacto() {
        contactoController.addContacto(new Contacto("Juan Pérez", "3001234567", "juan.perez@email.com"));
        String resultado = contactoController.deleteContacto("Juan Pérez");

        assertEquals("Contacto eliminado: Juan Pérez", resultado);
        assertEquals(0, contactoController.getContactos().size());
    }

    @Test
    void testEliminarContactoNoExistente() {
        String resultado = contactoController.deleteContacto("Pedro Rodríguez");

        assertEquals("Contacto no encontrado.", resultado);
        assertEquals(0, contactoController.getContactos().size());
    }

    @Test
    void testEliminarContactoNombreNulo() {
        String resultado = contactoController.deleteContacto(null);

        assertEquals("Nombre inválido.", resultado); // Agregar manejo de nulos en `deleteContacto`
    }

    @Test
    void testEliminarContactoNombreVacio() {
        String resultado = contactoController.deleteContacto("");

        assertEquals("Nombre inválido.", resultado); // Agregar validación en tu código
    }
}
