# Desarrollo2
LENGUAJE DE PROGRAMACIÓN AVANZADO 2 - 2503B04G1G2
# Proyecto seleccionado. 
Agenda con endpint RES, pruebas realizadas por me dio de postman
Ejecución del proyecto 
![image](https://github.com/user-attachments/assets/d3f17a05-6325-4628-8cbc-edd60a2a1f36)
![image](https://github.com/user-attachments/assets/5f8628e9-42d4-4120-9722-66e410731497)

# Integración con una API REST.

Creación de usuarios.

![image](https://github.com/user-attachments/assets/36dbb8df-1585-4ed4-89d6-c46599f5d29d)

![image](https://github.com/user-attachments/assets/bb4f61f1-5897-45ec-b316-47af2943c87f)

Consulta de usuarios.

![image](https://github.com/user-attachments/assets/4648d909-179f-444a-b098-e1991c84988e)

# Eliminar usuario.

![image](https://github.com/user-attachments/assets/dae50c6b-96ab-44bc-89b9-cbc09c6acc79)

# Configuracion de entorno de pruebas.
Herramientas Utilizadas
•	Java: JDK 17
•	Framework: Spring Boot 3
•	Gestor de Dependencias: Maven
•	Base de Datos: H2 en memoria para pruebas
•	Cliente de API: Postman
•	Entorno de Desarrollo: IntelliJ IDEA / VS Code

# Configuración de Dependencias

Las pruebas fueron configuradas utilizando las siguientes dependencias en el archivo pom.xml:
```
<dependencies>
<dependency>
<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>		
		<!-- JUnit 5 -->
		<dependency>
		    <groupId>org.junit.jupiter</groupId>
		    <artifactId>junit-jupiter-api</artifactId>
		    <scope>test</scope>
		</dependency>
		<dependency>
		    <groupId>org.junit.jupiter</groupId>
		    <artifactId>junit-jupiter-engine</artifactId>
		    <scope>test</scope>
		</dependency>
		<!-- Mockito -->
		<dependency>
		    <groupId>org.mockito</groupId>
		    <artifactId>mockito-core</artifactId>
		    <scope>test</scope>
		</dependency>
		<dependency>
		    <groupId>org.mockito</groupId>
		    <artifactId>mockito-junit-jupiter</artifactId>
		    <scope>test</scope>
		</dependency>
			</dependencies>
```

# Configuración de Base de Datos para Pruebas

Se utilizó H2 como base de datos en memoria. La configuración en application.properties es:
```
spring.application.name=agenda
spring.datasource.url=jdbc:h2:mem:agenda
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.security.user.name=admin
spring.security.user.password=admin
spring.h2.console.settings.web-allow-others=true
spring.security.user.name=
spring.security.user.password=
```


# Pruebas unitarias realizadas.

Las pruebas unitarias verifican la lógica de negocio del controlador de contactos sin depender de la API REST. Se implementaron con JUnit y Mockito.

Implementación de Pruebas Unitarias

clase ContactoControllerTest
```
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
        contactoController.limpiarContactos(); // Asegurar un estado limpio
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
```


# Casos de Prueba

1.Agregar un nuevo contacto: Verificar que se agrega correctamente.
2.Intentar agregar un contacto duplicado: Comprobar que se devuelve un mensaje de error.
3.Eliminar un contacto existente: Asegurar que se elimina correctamente.
4.Intentar eliminar un contacto inexistente: Validar que la respuesta indica que el contacto no existe.

# Ejecución de las pruebas.
![image](https://github.com/user-attachments/assets/edf27d47-64eb-4a84-b7b9-99072836af92)

Resultados.
![image](https://github.com/user-attachments/assets/20c7c53c-c4e0-4db7-8da5-4bb91994a57b)

Esto indica que todas las pruebas se ejecutaron correctamente sin fallos ni errores.





   









