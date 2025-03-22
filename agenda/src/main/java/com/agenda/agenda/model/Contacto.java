package com.agenda.agenda.model;

import jakarta.persistence.*;

@Entity  // Indica que esta clase es una entidad de la base de datos
@Table(name = "contactos") // Nombre de la tabla en la base de datos
public class Contacto {

    @Id  // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long id;

    @Column(nullable = false) // No puede ser nulo
    private String nombre;

    @Column(unique = true) // El teléfono debe ser único
    private String telefono;

    private String email;

    // Constructor vacío necesario para JPA
    public Contacto() {
    }

    // Constructor con parámetros (opcional, útil para pruebas)
    public Contacto(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y Setters (para acceder a los atributos)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
