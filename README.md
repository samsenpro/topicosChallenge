# Documentación de la API Rest

Autor: **Samuel Martinez**  
[LinkedIn](https://www.linkedin.com/in/samuel-martinez-beleno/)

## Descripción General

Esta API proporciona un sistema robusto para la autenticación de usuarios, gestión de cursos y discusión de tópicos. La API está construida usando Spring Boot y sigue principios RESTful, con endpoints protegidos mediante autenticación basada en JWT.

---

## Características

### Autenticación
- **Endpoint:** `/login`
- **Método:** `POST`
- **Descripción:** Autentica un usuario utilizando sus credenciales de inicio de sesión y devuelve un token JWT.
- **Cuerpo de la Solicitud:**
  ```json
  {
      "login": "user@example.com",
      "clave": "password"
  }
  ```
- **Respuesta:**
  ```json
  {
      "jwtToken": "<token>"
  }
  ```

### Registro de Usuarios
- **Endpoint:** `/registro`
- **Método:** `POST`
- **Descripción:** Registra un nuevo usuario en el sistema.
- **Cuerpo de la Solicitud:**
  ```json
  {
      "nombre": "John Doe",
      "email": "john@example.com",
      "clave": "password",
      "rol": "ESTUDIANTE"
  }
  ```
- **Respuesta:**
  ```json
  {
      "id": 1,
      "nombre": "John Doe",
      "email": "john@example.com",
      "rol": "ESTUDIANTE"
  }
  ```

---

### Gestión de Cursos
#### Registrar un Curso
- **Endpoint:** `/cursos`
- **Método:** `POST`
- **Descripción:** Crea un nuevo curso.
- **Cuerpo de la Solicitud:**
  ```json
  {
      "nombre": "Nombre del Curso",
      "categoria": "Categoría"
  }
  ```
- **Respuesta:**
  ```json
  {
      "id": 1,
      "nombre": "Nombre del Curso",
      "categoria": "Categoría"
  }
  ```

#### Listar Cursos
- **Endpoint:** `/cursos`
- **Método:** `GET`
- **Descripción:** Recupera una lista paginada de cursos.

#### Obtener Curso por ID
- **Endpoint:** `/cursos/{id}`
- **Método:** `GET`
- **Descripción:** Recupera los detalles de un curso por ID.

#### Actualizar Curso
- **Endpoint:** `/cursos/{id}`
- **Método:** `PUT`
- **Descripción:** Actualiza los detalles de un curso.
- **Cuerpo de la Solicitud:**
  ```json
  {
      "nombre": "Nombre Actualizado",
      "categoria": "Categoría Actualizada"
  }
  ```

#### Eliminar Curso
- **Endpoint:** `/cursos/{id}`
- **Método:** `DELETE`
- **Descripción:** Elimina un curso por ID.

---

### Gestión de Tópicos
#### Registrar un Tópico
- **Endpoint:** `/topicos`
- **Método:** `POST`
- **Descripción:** Crea un nuevo tópico de discusión.
- **Cuerpo de la Solicitud:**
  ```json
  {
      "titulo": "Título del Tópico",
      "mensaje": "Contenido del Mensaje",
      "autorId": 1,
      "cursoId": 1
  }
  ```

#### Listar Tópicos
- **Endpoint:** `/topicos`
- **Método:** `GET`
- **Descripción:** Recupera una lista paginada de tópicos.

#### Obtener Tópico por ID
- **Endpoint:** `/topicos/{id}`
- **Método:** `GET`
- **Descripción:** Recupera los detalles de un tópico por ID.

#### Actualizar Tópico
- **Endpoint:** `/topicos/{id}`
- **Método:** `PUT`
- **Descripción:** Actualiza los detalles de un tópico.
- **Cuerpo de la Solicitud:**
  ```json
  {
      "titulo": "Título Actualizado",
      "mensaje": "Mensaje Actualizado",
      "status": "SOLUCIONADO"
  }
  ```

#### Eliminar Tópico
- **Endpoint:** `/topicos/{id}`
- **Método:** `DELETE`
- **Descripción:** Elimina un tópico por ID.

---

## Seguridad
- Autenticación basada en JWT.
- Rutas protegidas:
  - `/topicos`: Requiere autenticación para `GET`. Roles Admin y Moderador para `POST`, `PUT` y `DELETE`.
  - `/cursos`: Requiere autenticación para `GET`. Roles Admin y Moderador para `POST`, `PUT` y `DELETE`.

---

## Manejo de Errores
- `404 Not Found`: Entidad no encontrada.
- `400 Bad Request`: Errores de validación.
- `401 Unauthorized`: Credenciales inválidas.
- `500 Internal Server Error`: Errores inesperados.

---

## DTOs
- `DatosRegistroCurso`: Maneja el registro de cursos.
- `DatosActualizarCurso`: Maneja la actualización de cursos.
- `DatosRespuestaCurso`: Maneja los datos de respuesta de cursos.
- `DatosRegistroUsuario`: Maneja el registro de usuarios.
- `DatosRespuestaRegistro`: Maneja la respuesta del registro de usuarios.
- `DatosRegistroTopico`: Maneja el registro de tópicos.
- `DatosActualizarTopico`: Maneja la actualización de tópicos.
- `DatosRespuestaTopico`: Maneja los datos de respuesta de tópicos.

---

## Modelos
- `Usuario`: Representa los usuarios del sistema.
- `Cursos`: Representa los cursos.
- `Topico`: Representa los tópicos de discusión.

---

## Repositorios
- `UsuarioRepository`: Maneja la persistencia de usuarios.
- `CursoRepository`: Maneja la persistencia de cursos.
- `TopicoRepository`: Maneja la persistencia de tópicos.

---

## Servicios
- `UsuarioService`: Administra operaciones relacionadas con usuarios.
- `CursoService`: Administra operaciones relacionadas con cursos.
- `TopicoService`: Administra operaciones relacionadas con tópicos.
- `TokenService`: Maneja la generación y validación de JWT.

---

## Middleware
- `SecurityFilter`: Valida los tokens JWT y autentica las solicitudes.
- `TratadorDeErrores`: Maneja respuestas de error personalizadas.

---

## Primeros Pasos
1. Clona el repositorio.
2. Configura la conexión a la base de datos en `application.properties`.
3. Define la clave secreta para JWT en `application.properties`.
4. Construye y ejecuta la aplicación.

---

## Agradecimiento
Este proyecto fue realizado con el proposito del challenge forohub de alura latam como estudiante One.


