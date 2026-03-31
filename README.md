# Evaluacion-Modular-5---Sistema-de-Reservas-de-Aerolineas
Repositorio de Final del Modulo 5 del Curso Desarrollador de Aplicaciones Full Stack Java Trainee v2.0 0033-1 de Kibernum.

# modular-c8

Proyecto educativo desarrollado en el contexto del **Módulo 5 - Clase 8** del programa de formación **Kibernum**.

---

## Objetivo educativo

Este proyecto tiene como fin practicar e ilustrar la implementación del **patrón de diseño Repository** en una aplicación Java con acceso a base de datos relacional mediante **JDBC puro**, sin el uso de ORM (como Hibernate o JPA). Se refuerzan además conceptos como el patrón **Singleton**, **Servicio**, **Servlet MVC**, el uso de **interfaces**, **enums**, **DTOs**, pruebas unitarias con **JUnit 5**, y una capa de presentación web con **JSP + Bootstrap + Anime.js**.

---

## Dominio del problema

Se modela un sistema simplificado de **reservas de vuelos aéreos**, con tres entidades principales:

| Entidad   | Descripción                                                |
|-----------|------------------------------------------------------------|
| `Vuelo`   | Representa un vuelo con ciudades, fechas y horarios        |
| `Cliente` | Pasajero identificado por nombre, apellido y RUT           |
| `Reserva` | Asocia un cliente con un vuelo, asignando asiento y sección |

---

## Estructura del proyecto

```
modular-c8/
├── docker-compose.yml                          # Base de datos MySQL en Docker
├── pom.xml                                     # Dependencias Maven
├── sql/
│   └── init.sql                                # Creación de tablas e inserción de datos iniciales
└── src/
    ├── main/
    │   ├── java/cl/example/
    │   │   ├── configuration/
    │   │   │   └── DatabaseConnection.java         # Singleton de conexión JDBC
    │   │   ├── model/
    │   │   │   ├── Cliente.java                    # Entidad Cliente
    │   │   │   ├── Vuelo.java                      # Entidad Vuelo
    │   │   │   ├── Reserva.java                    # Entidad Reserva
    │   │   │   ├── EstadoVuelo.java                # Enum: A_TIEMPO, ATRASADO, CANCELADO
    │   │   │   └── SeccionCliente.java             # Enum: ECONOMICA, EJECUTIVA, VIP
    │   │   ├── dtos/
    │   │   │   ├── CrearReservaDTO.java            # DTO para crear una reserva desde el formulario
    │   │   │   ├── ListarReservaDTO.java           # DTO para listado de reservas en la tabla
    │   │   │   └── DetalleReservaDTO.java          # DTO para la vista de detalle de una reserva
    │   │   ├── repository/
    │   │   │   ├── ClienteRepository.java          # Interfaz CRUD de Cliente
    │   │   │   ├── VueloRepository.java            # Interfaz de consulta de Vuelos
    │   │   │   ├── ReservaRepository.java          # Interfaz CRUD de Reserva
    │   │   │   └── implementation/
    │   │   │       ├── ClienteRepositoryImpl.java  # Implementación con JDBC
    │   │   │       ├── VueloRepositoryImpl.java    # Implementación con JDBC
    │   │   │       └── ReservaRepositoryImpl.java  # Implementación con JDBC
    │   │   ├── service/
    │   │   │   ├── VueloService.java               # Interfaz del servicio de vuelos
    │   │   │   ├── ReservaService.java             # Interfaz del servicio de reservas
    │   │   │   └── implementation/
    │   │   │       ├── VueloServiceImpl.java       # Lógica de negocio de vuelos
    │   │   │       └── ReservaServiceImpl.java     # Lógica de negocio de reservas
    │   │   └── servlet/
    │   │       └── ReservaServlet.java             # Controlador HTTP (@WebServlet "/reservas")
    │   └── webapp/
    │       ├── index.jsp                           # Redirige automáticamente a /reservas
    │       └── WEB-INF/
    │           ├── web.xml                         # Configuración del servlet
    │           └── views/
    │               ├── reservas.jsp                # Vista: listado + formulario nueva reserva
    │               └── detalleReserva.jsp          # Vista: detalle de una reserva
    └── test/
        └── java/cl/example/
            ├── configuration/
            │   └── DatabaseConnectionTest.java     # Test de conexión a la BD
            ├── repository/implementation/
            │   ├── ClienteRepositoryImplTest.java  # Tests del repositorio de clientes
            │   ├── VueloRepositoryImplTest.java    # Tests del repositorio de vuelos
            │   └── ReservaRepositoryImplTest.java  # Tests del repositorio de reservas
            └── service/
                ├── VueloServiceImplTest.java       # Tests del servicio de vuelos
                └── ReservaServiceImplTest.java     # Tests del servicio de reservas
```

---

## Tecnologías utilizadas

| Tecnología             | Versión  | Rol en el proyecto                                   |
|------------------------|----------|------------------------------------------------------|
| Java                   | 17+      | Lenguaje principal                                   |
| Maven                  | —        | Gestión de dependencias y build                      |
| Jakarta Servlet API    | 6.0.0    | Controlador HTTP (`@WebServlet`)                     |
| Jakarta CDI API        | 4.0.1    | Inyección de dependencias (declarado)                |
| MySQL Connector/J      | 9.4.0    | Driver JDBC para conexión a MySQL                    |
| JUnit Jupiter          | 5.12.0   | Framework de pruebas unitarias                       |
| MySQL                  | 8.4      | Motor de base de datos (via Docker)                  |
| Docker / Compose       | —        | Levantamiento del entorno de base de datos           |
| Bootstrap              | 5.3.3    | Estilos y componentes UI (CDN)                       |
| Anime.js               | 3.2.1    | Animaciones de interfaz (CDN)                        |
| Apache Tomcat          | 10+      | Servidor de aplicaciones Jakarta EE                  |

---

## Base de datos

La base de datos se llama `repo` y se levanta automáticamente con Docker Compose. El archivo `sql/init.sql` crea las tres tablas y carga **10 vuelos de ejemplo** entre distintas ciudades de Latinoamérica y Europa.

**Tablas:**

- `vuelo` — id, ciudades de despegue/aterrizaje, fechas y horarios
- `cliente` — id, nombre, apellido, RUT (único, `VARCHAR(10)`)
- `reserva` — id, FK a cliente, FK a vuelo, número de asiento (único), estado del vuelo y sección del pasajero

**Credenciales del contenedor:**

| Parámetro  | Valor    |
|------------|----------|
| Host       | localhost |
| Puerto     | 3306     |
| Base       | repo     |
| Usuario    | user     |
| Contraseña | password |

---

## Capa de Servicio

La capa de servicio encapsula la lógica de negocio y actúa como intermediaria entre los controladores (Servlets) y los repositorios.

### `VueloService` / `VueloServiceImpl`

| Método                               | Descripción                                              |
|--------------------------------------|----------------------------------------------------------|
| `List<Vuelo> obtenerVuelos()`        | Devuelve todos los vuelos disponibles                    |
| `Vuelo obtenerVueloPorId(Integer id)`| Devuelve un vuelo por su ID                              |

### `ReservaService` / `ReservaServiceImpl`

| Método                                              | Descripción                                                                |
|-----------------------------------------------------|----------------------------------------------------------------------------|
| `List<ListarReservaDTO> obtenerReservas()`          | Devuelve el listado de reservas enriquecido con datos del cliente y vuelo  |
| `DetalleReservaDTO obtenerReservaPorId(Integer id)` | Devuelve todos los datos de una reserva específica                         |
| `Integer crearReserva(CrearReservaDTO dto)`         | Crea o reutiliza el cliente, luego inserta la reserva; devuelve el ID      |

#### Flujo de `crearReserva`

1. Busca al cliente por RUT. Si no existe, lo crea.
2. Inserta la reserva asociando el cliente al vuelo indicado.
3. Devuelve el ID de la nueva reserva.

---

## DTOs

Los **Data Transfer Objects** desacoplan la vista de las entidades del dominio.

| DTO                  | Campos principales                                                                 |
|----------------------|------------------------------------------------------------------------------------|
| `CrearReservaDTO`    | `nombreCliente`, `apellidoCliente`, `rutCliente`, `idVuelo`, `numeroAsiento`, `estadoVuelo`, `seccionCliente` |
| `ListarReservaDTO`   | `idReserva`, `nombre`, `apellido`, `ciudadDespegue`, `ciudadAterrizaje`, `numeroAsiento`, `estadoVuelo`, `seccionCliente` |
| `DetalleReservaDTO`  | Todos los campos de `ListarReservaDTO` más: `rut`, `fechaSalida`, `fechaLlegada`, `horaAbordaje`, `horaSalida`, `horaLlegada` |

---

## Capa Web (Servlet + JSP)

La capa web sigue el patrón **MVC** usando Jakarta Servlets como controladores y JSPs como vistas puras.

### Arquitectura

```
Navegador → Servlet (lógica + datos) → JSP (solo renderizado)
```

- Los JSPs viven bajo `WEB-INF/views/` para que el navegador **no pueda acceder directamente** a ellos.
- Toda la lógica de negocio y preparación de datos se realiza en el Servlet, que luego hace `forward` al JSP correspondiente.

### `ReservaServlet` — `@WebServlet("/reservas")`

#### `GET /reservas`

- Sin parámetro `id`: carga lista de reservas y vuelos → forward a `reservas.jsp`
- Con parámetro `?id={n}`: carga el detalle de la reserva → forward a `detalleReserva.jsp`
- Recupera mensajes flash de sesión (patrón PRG) y los pasa como atributos de request.

#### `POST /reservas`

- Recibe los datos del formulario de nueva reserva.
- Construye un `CrearReservaDTO` y llama a `reservaService.crearReserva(dto)`.
- Guarda el resultado (éxito o error) en la sesión como **flash message**.
- Redirige a `GET /reservas` (patrón **PRG** — Post/Redirect/Get).

### Tabla de rutas

| Método | URL                  | Acción                                    |
|--------|----------------------|-------------------------------------------|
| GET    | `/reservas`          | Listado de reservas + formulario          |
| GET    | `/reservas?id={n}`   | Detalle de la reserva con ID `n`          |
| POST   | `/reservas`          | Crear nueva reserva → redirect a GET      |

---

## Vistas JSP

### `reservas.jsp`

Vista principal de la aplicación. Recibe tres atributos del servlet:

| Atributo        | Tipo                      | Uso                                   |
|-----------------|---------------------------|---------------------------------------|
| `reservas`      | `List<ListarReservaDTO>`  | Datos de la tabla de reservas         |
| `vuelos`        | `List<Vuelo>`             | Opciones del dropdown del formulario  |
| `mensajeAlerta` | `String`                  | Texto del mensaje flash (opcional)    |
| `tipoAlerta`    | `String`                  | Clase Bootstrap (`success`/`danger`)  |

**Funcionalidades:**

- Tabla de reservas con columnas: ID, Pasajero, Ruta, Asiento, Sección, Estado del Vuelo.
- Botón **"Ver detalle"** en cada fila que navega a `GET /reservas?id={n}`.
- Botón **"+ Nueva Reserva"** que muestra/oculta el formulario con animación.
- Formulario de nueva reserva con campos: nombre, apellido, RUT, vuelo (dropdown), estado del vuelo (dropdown con flags de color), número de asiento, sección del cliente.
- Dropdown de vuelos con **indicadores de color** según estado: 🟢 A TIEMPO, 🟠 ATRASADO, 🔴 CANCELADO.
- Bloqueo de envío del formulario si se selecciona un vuelo con estado **CANCELADO**: el botón se deshabilita y aparece una advertencia visual.
- Mensaje flash que se muestra al regresar luego de crear una reserva.

### `detalleReserva.jsp`

Vista de detalle de una reserva individual. Recibe:

| Atributo       | Tipo                 | Uso                                         |
|----------------|----------------------|---------------------------------------------|
| `detalle`      | `DetalleReservaDTO`  | Todos los datos de la reserva               |
| `errorDetalle` | `String`             | Mensaje de error si la reserva no existe    |

**Secciones:**

1. **Vuelo** — Ciudad de origen → destino, fecha de salida, fecha de llegada, hora de abordaje, hora de salida, hora de llegada.
2. **Pasajero** — Nombre completo y RUT.
3. **Reserva** — Número de asiento, sección del cliente, badge de estado del vuelo con color.

**Badge de estado:**

| Estado    | Estilo               |
|-----------|----------------------|
| A TIEMPO  | Verde (`#dcfce7`)    |
| ATRASADO  | Naranja (`#fff7ed`)  |
| CANCELADO | Rojo (`#fee2e2`)     |

---

## Animaciones (Anime.js)

Ambas vistas utilizan **Anime.js 3.2.1** (vía CDN de cdnjs) para animaciones de entrada orquestadas.

### `reservas.jsp`

| Elemento                  | Animación                                     |
|---------------------------|-----------------------------------------------|
| Navbar                    | Desliza desde arriba (`translateY: -70 → 0`)  |
| Título y botón            | Entran desde lados opuestos (`translateX`)    |
| Mensaje flash             | Escala desde el centro (`scale: 0.8 → 1`)     |
| Filas de la tabla         | Stagger vertical descendente                  |
| Formulario al abrir       | `opacity` + `scaleY` (0 → 1)                 |
| Badge de estado CANCELADO | Efecto shake horizontal al hacer hover        |
| Flag dot (punto de color) | Pulso elástico continuo (`loop: true`)        |

### `detalleReserva.jsp`

| Elemento                    | Animación                                      |
|-----------------------------|------------------------------------------------|
| Navbar                      | Desliza desde arriba                           |
| Botón "Volver"              | Entra desde la izquierda                       |
| Card principal              | Sube con rebote (`easeOutExpo`)                |
| Título de la card           | Entra desde la izquierda                       |
| Badge de estado (header)    | Entra desde la derecha con scale               |
| Ciudad origen               | Entra desde la izquierda                       |
| Ciudad destino              | Entra desde la derecha                         |
| Flecha `→`                  | Scale desde 0 con rebote                       |
| Títulos de sección          | Stagger horizontal                             |
| Labels y valores de datos   | Stagger vertical escalonado                    |
| Separadores `<hr>`          | Se expanden horizontalmente desde el centro    |
| Badge de estado (body)      | Pulso de entrada (`easeOutBack`)               |
| Flag dot (header)           | Pulso continuo en escala y opacidad            |

---

## Restricciones y datos importantes

### Formato de RUT

El campo `rut` en la base de datos es `VARCHAR(10)`. El formulario aplica las siguientes restricciones:

- `maxlength="10"` — impide ingresar más de 10 caracteres.
- `pattern="^\d{7,8}-[\dkK]$"` — valida formato chileno: 7 u 8 dígitos, guion, dígito verificador (0–9, k, K).
- Ejemplo válido: `12345678-9`
- Ejemplo inválido: `12.345.678-9` (con puntos, supera los 10 caracteres)

### Enum `SeccionCliente`

El campo `seccion_cliente` en MySQL es `ENUM('ECONOMICA', 'EJECUTIVO', 'VIP')`. Los valores Java del enum son:

| Constante Java | Valor enviado a MySQL |
|----------------|-----------------------|
| `ECONOMICA`    | `"ECONOMICA"`         |
| `EJECUTIVA`    | `"EJECUTIVO"`         |
| `VIP`          | `"VIP"`               |

> El nombre de la constante Java (`EJECUTIVA`) difiere intencionalmente del valor de la BD (`EJECUTIVO`) por compatibilidad con datos existentes.

### `DatabaseConnection` (Singleton)

La conexión JDBC es compartida como instancia única. Si se usa `try-with-resources` con el objeto `Connection`, este se cierra y la conexión queda inválida. La implementación detecta conexión cerrada y reconecta automáticamente al llamar `getConnection()`.

---

## Conceptos clave trabajados

- **Patrón Repository:** Separación entre la lógica de acceso a datos y el resto de la aplicación.
- **Patrón Service:** Capa intermedia que contiene la lógica de negocio, reutilizable desde cualquier controlador.
- **Patrón MVC con Servlets:** El Servlet actúa como Controller, el JSP como View, y las entidades/DTOs como Model.
- **Patrón PRG (Post/Redirect/Get):** Evita reenvío accidental del formulario al recargar la página.
- **Flash Messages:** Mensajes de confirmación/error almacenados en sesión y consumidos en el siguiente request.
- **Patrón Singleton:** `DatabaseConnection` garantiza una única instancia de conexión a lo largo de la aplicación.
- **JDBC puro:** Uso directo de `Connection`, `PreparedStatement` y `ResultSet` sin ORM.
- **DTOs:** Objetos de transferencia de datos que desacoplan la vista del dominio.
- **Enums con valor:** `EstadoVuelo` y `SeccionCliente` encapsulan los valores permitidos por la BD.
- **Pruebas de integración:** Los tests conectan a la base de datos real levantada con Docker.

---

## Cómo ejecutar

### 1. Levantar la base de datos

```bash
docker-compose up -d
```

Esto levanta un contenedor MySQL en el puerto `3306` e inicializa las tablas y datos automáticamente.

### 2. Compilar el proyecto

```bash
mvn clean compile
```

### 3. Ejecutar los tests

Con la base de datos corriendo:

```bash
mvn test
```

### 4. Ejecutar la aplicación web

La aplicación se despliega en **Apache Tomcat 10+**. Si se utiliza **IntelliJ IDEA con el plugin SmartTomcat**:

> **Importante:** SmartTomcat usa el directorio de salida de IntelliJ (`out/production/`), **no** el directorio `target/` de Maven. Para que los cambios en Servlets y clases Java se reflejen, usar:
>
> **Build → Build Project** (o `Ctrl+F9` / `Cmd+F9`) en lugar de `mvn clean package`.

Una vez iniciado el servidor, la aplicación estará disponible en:

```
http://localhost:8080/{nombre-del-contexto}/
```

La página principal (`index.jsp`) redirige automáticamente a `/reservas`.

### 5. Detener la base de datos

```bash
docker-compose down
```

---

> Proyecto desarrollado con fines exclusivamente educativos en el contexto del programa Kibernum — Módulo 5.
