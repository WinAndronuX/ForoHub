# ForoHub

## Introduccion

ForoHub es una API REST para la gestión de foros en línea. Proporciona una plataforma donde los usuarios pueden registrarse, crear y participar en discusiones sobre diversos temas organizados en cursos.

Este proyecto es la resolución de:
> Challenge Foro Hub - Alura ONE

## Tecnologías

- **Java 17**
- **Spring Framework**
- **Principios SOLID**
- **MySQL**
- **JPA (Java Persistence API)**

## Endpoints

### User Controller

- **Cambiar Contraseña**
    - **PUT** `/api/users/changePassword`
        - **Descripción**: Cambia la contraseña de un usuario.
        - **Cuerpo de la Solicitud**: `UserUpdatePasswordDTO`
        - **Respuesta**: `UserDTO`

- **Registrar Usuario**
    - **POST** `/api/users`
        - **Descripción**: Registra un nuevo usuario.
        - **Cuerpo de la Solicitud**: `UserAddDTO`
        - **Respuesta**: `UserDTO`

- **Obtener Usuario**
    - **GET** `/api/users/{id}`
        - **Descripción**: Obtiene la información de un usuario por ID.
        - **Parámetros**: `id` (integer, path)
        - **Respuesta**: `UserDTO`

- **Eliminar Usuario**
    - **DELETE** `/api/users/{id}`
        - **Descripción**: Elimina un usuario por ID.
        - **Parámetros**: `id` (integer, path)
        - **Respuesta**: 200 OK

### Topic Controller

- **Obtener Topic**
    - **GET** `/api/topics/{id}`
        - **Descripción**: Obtiene los detalles de un topic por ID.
        - **Parámetros**: `id` (integer, path)
        - **Respuesta**: `TopicDTO`

- **Actualizar Topic**
    - **PUT** `/api/topics/{id}`
        - **Descripción**: Edita un topic por ID (campos editables: title, content, course).
        - **Parámetros**: `id` (integer, path)
        - **Cuerpo de la Solicitud**: `TopicAddDTO`
        - **Respuesta**: `TopicDTO`

- **Eliminar Topic**
    - **DELETE** `/api/topics/{id}`
        - **Descripción**: Elimina un topic por ID.
        - **Parámetros**: `id` (integer, path)
        - **Respuesta**: 200 OK

- **Listar Topics**
    - **GET** `/api/topics`
        - **Descripción**: Obtiene el listado de topics.
        - **Parámetros**: `pageable` (query)
        - **Respuesta**: `PageTopicDTO`

- **Crear Topic**
    - **POST** `/api/topics`
        - **Descripción**: Crea un nuevo topic.
        - **Cuerpo de la Solicitud**: `TopicAddDTO`
        - **Respuesta**: `TopicDTO`

- **Listar Respuestas de un Topic**
    - **GET** `/api/topics/{id}/replies`
        - **Descripción**: Obtiene el listado de respuestas de un topic.
        - **Parámetros**: `id` (integer, path), `pageable` (query)
        - **Respuesta**: `PageReplyDTO`

- **Agregar Respuesta a un Topic**
    - **POST** `/api/topics/{id}/replies`
        - **Descripción**: Agrega una respuesta a un topic.
        - **Parámetros**: `id` (integer, path)
        - **Cuerpo de la Solicitud**: `ReplyAddDTO`
        - **Respuesta**: `ReplyDTO`

- **Editar Respuesta**
    - **PUT** `/api/topics/{id}/replies/{replyId}`
        - **Descripción**: Edita una respuesta de un topic.
        - **Parámetros**: `id` (integer, path), `replyId` (integer, path)
        - **Cuerpo de la Solicitud**: `ReplyEditDTO`
        - **Respuesta**: `ReplyDTO`

- **Eliminar Respuesta**
    - **DELETE** `/api/topics/{id}/replies/{replyId}`
        - **Descripción**: Elimina una respuesta de un topic.
        - **Parámetros**: `id` (integer, path), `replyId` (integer, path)
        - **Respuesta**: 200 OK

### Course Controller

- **Obtener Curso**
    - **GET** `/api/courses/{id}`
        - **Descripción**: Obtiene los detalles de un curso por ID.
        - **Parámetros**: `id` (integer, path)
        - **Respuesta**: `CourseDTO`

- **Actualizar Curso**
    - **PUT** `/api/courses/{id}`
        - **Descripción**: Actualiza la información de un curso (campos editables: name, category).
        - **Parámetros**: `id` (integer, path)
        - **Cuerpo de la Solicitud**: `CourseAddDTO`
        - **Respuesta**: `CourseDTO`

- **Eliminar Curso**
    - **DELETE** `/api/courses/{id}`
        - **Descripción**: Elimina un curso por ID.
        - **Parámetros**: `id` (integer, path)
        - **Respuesta**: 200 OK

- **Listar Cursos**
    - **GET** `/api/courses`
        - **Descripción**: Obtiene el listado de cursos.
        - **Parámetros**: `pageable` (query)
        - **Respuesta**: `PageCourseDTO`

- **Registrar Curso**
    - **POST** `/api/courses`
        - **Descripción**: Registra un nuevo curso.
        - **Cuerpo de la Solicitud**: `CourseAddDTO`
        - **Respuesta**: `CourseDTO`

### Authentication Controller

- **Login**
    - **POST** `/login`
        - **Descripción**: API login con JWT.
        - **Cuerpo de la Solicitud**: `AuthUserDTO`
        - **Respuesta**: `JwtTokenDTO`

## Componentes

### Esquemas

#### UserUpdatePasswordDTO
- **Id**: integer
- **password**: string

#### UserDTO
- **Id**: integer
- **username**: string
- **email**: string
- **role**: string (ADMIN, USER)
- **numTopicsCreated**: integer
- **topicsCreated**: array of strings
- **numComments**: integer

#### TopicAddDTO
- **title**: string
- **content**: string
- **userId**: integer
- **courseId**: integer

#### TopicDTO
- **Id**: integer
- **title**: string
- **content**: string
- **author**: string
- **course**: string
- **createdAt**: string
- **numResponses**: integer

#### ReplyEditDTO
- **message**: string

#### ReplyDTO
- **Id**: integer
- **message**: string
- **createdAt**: string
- **repliesTo**: integer
- **User**: string

#### CourseAddDTO
- **name**: string
- **category**: string

#### CourseDTO
- **Id**: integer
- **name**: string
- **category**: string

#### AuthUserDTO
- **username**: string
- **password**: string

#### JwtTokenDTO
- **jwtToken**: string

#### UserAddDTO
- **username**: string
- **email**: string
- **password**: string
- **role**: string

#### ReplyAddDTO
- **message**: string
- **userId**: integer
- **topicId**: integer
- **repliesTo**: integer

#### Pageable
- **page**: integer
- **size**: integer
- **sort**: array of strings

#### PageTopicDTO
- **totalPages**: integer
- **totalElements**: integer
- **size**: integer
- **content**: array of `TopicDTO`
- **number**: integer
- **sort**: `SortObject`
- **pageable**: `PageableObject`
- **first**: boolean
- **last**: boolean
- **numberOfElements**: integer
- **empty**: boolean

#### PageReplyDTO
- **totalPages**: integer
- **totalElements**: integer
- **size**: integer
- **content**: array of `ReplyDTO`
- **number**: integer
- **sort**: `SortObject`
- **pageable**: `PageableObject`
- **first**: boolean
- **last**: boolean
- **numberOfElements**: integer
- **empty**: boolean

#### PageCourseDTO
- **totalPages**: integer
- **totalElements**: integer
- **size**: integer
- **content**: array of `CourseDTO`
- **number**: integer
- **sort**: `SortObject`
- **pageable**: `PageableObject`
- **first**: boolean
- **last**: boolean
- **numberOfElements**: integer
- **empty**: boolean

#### PageableObject
- **offset**: integer
- **sort**: `SortObject`
- **paged**: boolean
- **pageNumber**: integer
- **pageSize**: integer
- **unpaged**: boolean

#### SortObject
- **empty**: boolean
- **sorted**: boolean
- **unsorted**: boolean

### Esquemas de Seguridad

- **bearer-key**
    - **Tipo**: `http`
    - **Esquema**: `bearer`
    - **Formato Bearer**: `JWT`

## Usage

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/WinAndronuX/ForoHub.git
   ```
2. Navegar al directorio del proyecto:
   ```bash
   cd forohub
   ```
3. Configurar la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/forohub
   spring.datasource.username=tuusuario
   spring.datasource.password=tucontraseña
   ```
4. Ejecutar la aplicación:
    ```bash
   ./mvnw spring-boot:run
    ```
   
5. Usuario por default
    ```yaml
    username: admin
    password: admin
    ```
   
