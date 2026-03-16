# SpringEduManager – Proyecto Módulo 6 ABP

Aplicación web desarrollada con **Spring Boot** para la gestión de cursos y estudiantes, implementando arquitectura **MVC**, persistencia con **JPA**, seguridad con **Spring Security** y servicios **REST**.

Este proyecto fue desarrollado como parte del **Proyecto ABP – Módulo 6**, cumpliendo los siguientes requisitos:

- Maven
- MVC con Thymeleaf
- Persistencia con JPA
- Seguridad con Spring Security
- API REST
- Base de datos MySQL
- Control de acceso por roles

---

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Data JPA
- Spring Security
- MySQL
- Maven
- Bootstrap 5
- Postman (para pruebas REST)

---

## Funcionalidades implementadas

### MVC (Interfaz web)

- Login obligatorio
- Logout
- Página principal protegida
- Listado de cursos
- Listado de estudiantes
- Crear curso
- Editar curso
- Eliminar curso
- Crear estudiante
- Editar estudiante
- Eliminar estudiante
- Inscribir estudiante en curso
- Página personalizada de acceso denegado (403)
- Barra superior común con layout Thymeleaf
- Interfaz con Bootstrap

### Seguridad

- Spring Security configurado
- Login con formulario personalizado
- Logout
- Usuarios con roles

#### Usuarios

| Usuario | Contraseña | Rol |
|---------|------------|-----|
| admin   | admin123   | ADMIN |
| user    | user123    | USER |

#### Permisos

- **ADMIN** → crear, editar, eliminar, inscribir
- **USER** → solo ver datos
- **API pública** para pruebas REST

### Persistencia con JPA

#### Entidades

- Curso
- Estudiante

#### Relación

- ManyToMany entre **Curso** y **Estudiante**

#### Componentes utilizados

- Repository
- Service
- Controller
- Base de datos MySQL

La persistencia se realiza tanto desde formularios **MVC** como desde la **API REST**.

### API REST

#### Controladores REST implementados

##### Cursos

```http
GET    /api/cursos
GET    /api/cursos/{id}
POST   /api/cursos
PUT    /api/cursos/{id}
DELETE /api/cursos/{id}
POST   /api/cursos/{cursoId}/estudiantes/{estudianteId}
```

##### Estudiantes

```http
GET    /api/estudiantes
GET    /api/estudiantes/{id}
POST   /api/estudiantes
PUT    /api/estudiantes/{id}
DELETE /api/estudiantes/{id}
```

Las pruebas fueron realizadas con **Postman**.

Las respuestas se entregan en formato **JSON**.

---

## Estructura del proyecto

```text
controller/
├── CursoController
├── EstudianteController
├── CursoViewController
├── EstudianteViewController
├── HomeController
└── LoginController

service/
├── CursoService
└── EstudianteService

repository/
├── CursoRepository
└── EstudianteRepository

model/
├── Curso
└── Estudiante

security/
└── SecurityConfig

templates/
├── layout.html
├── home.html
├── login.html
├── cursos.html
├── estudiantes.html
├── curso-form.html
├── estudiante-form.html
├── inscribir.html
└── 403.html
```

---

## Configuración

### Archivo principal

```properties
application.properties
```

### Contenido configurado

- Conexión MySQL
- Configuración JPA
- Configuración de seguridad

### Ejemplo

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/spring_edu_manager
spring.datasource.username=root
spring.datasource.password=root1234

spring.jpa.hibernate.ddl-auto=update

spring.security.user.name=admin
spring.security.user.password=admin123
spring.security.user.roles=ADMIN
```

---

## Ejecución

### Compilar

```bash
mvnw clean install
```

### Ejecutar

```bash
mvnw spring-boot:run
```

### Abrir en navegador

```text
http://localhost:8080
```

---

## Evidencias funcionales

Se verificó correctamente:

- Login y logout
- CRUD desde interfaz web
- CRUD desde API REST
- Control de acceso por rol
- Persistencia en MySQL
- Inscripción de estudiantes en cursos
- Respuestas JSON en Postman

---

## Autor

Proyecto académico desarrollado para el **Módulo 6 – ABP**.
