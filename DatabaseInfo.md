# Documentación de la Base de Datos: Formula Uno

## Información de la Base de Datos

### Detalles de Conexión
- **DB:** MySQLServer
- **Host:** 135.236.97.132
- **Puerto:** 3306
- **Cifrado SSL:** TLS_AES_128_GCM_SHA256

### Servidor
- **Producto:** MySQLServer (Ubuntu)
- **Versión:** 8.0.39-0ubuntu0.24.04.2

### Despliegue
Esta base de datos está desplegada en una máquina virtual en la nube de Azure.

- **Especificaciones de la Maquina VIrtual de Azure**
  - **Nombre del equipo:** MySqlServer
  - **Sistema operativo:** Linux (Ubuntu 24.04)
  - **Generación de VM:** V2
  - **Arquitectura de VM:** x64
  - **Tamaño:** Standard B1s (1 vcpu, 1 GiB de memoria)

## Tablas

### 1. `usuario_registrado`
Almacena información sobre los usuarios registrados.

| Columna       | Tipo                          | Descripción                                |
|---------------|-------------------------------|--------------------------------------------|
| `id`          | INT AUTO_INCREMENT            | Identificador único para cada usuario      |
| `nombre`      | VARCHAR(255) NOT NULL         | Nombre completo del usuario                |
| `usuario`     | VARCHAR(255) NOT NULL UNIQUE  | Nombre de usuario único                    |
| `email`       | VARCHAR(255) NOT NULL UNIQUE  | Dirección de correo electrónico única      |
| `contraseña`  | VARCHAR(255) NOT NULL         | Contraseña                                 |
| `rol`         | ENUM('Responsable de equipo', 'Administrador') NOT NULL | Rol del usuario                            |

### 2. `equipo`
Almacena información sobre los equipos.

| Columna   | Tipo                          | Descripción                                |
|-----------|-------------------------------|--------------------------------------------|
| `id`      | INT AUTO_INCREMENT            | Identificador único para cada equipo       |
| `nombre`  | VARCHAR(255) NOT NULL         | Nombre del equipo                          |
| `logo`    | BLOB                          | Logo del equipo                            |
| `twitter` | VARCHAR(255)                  | Cuenta de Twitter del equipo               |

### 3. `piloto`
Almacena información sobre los pilotos.

| Columna    | Tipo                          | Descripción                                |
|------------|-------------------------------|--------------------------------------------|
| `id`       | INT AUTO_INCREMENT            | Identificador único para cada piloto       |
| `nombre`   | VARCHAR(255) NOT NULL         | Nombre del piloto                          |
| `apellidos`| VARCHAR(255) NOT NULL         | Apellidos del piloto                       |
| `siglas`   | CHAR(3) NOT NULL UNIQUE       | Iniciales únicas del piloto                |
| `dorsal`   | INT NOT NULL UNIQUE           | Número único asignado al piloto            |
| `foto`     | BLOB                          | Foto del piloto                            |
| `pais`     | VARCHAR(255) NOT NULL         | País del piloto                            |
| `twitter`  | VARCHAR(255)                  | Cuenta de Twitter del piloto               |

### 4. `circuito`
Almacena información sobre los circuitos.

| Columna          | Tipo                          | Descripción                                |
|------------------|-------------------------------|--------------------------------------------|
| `id`             | INT AUTO_INCREMENT            | Identificador único para cada circuito     |
| `nombre`         | VARCHAR(255) NOT NULL         | Nombre del circuito                        |
| `ciudad`         | VARCHAR(255) NOT NULL         | Ciudad donde se encuentra el circuito      |
| `pais`           | VARCHAR(255) NOT NULL         | País donde se encuentra el circuito        |
| `trazado`        | BLOB                          | Trazado del circuito                       |
| `numero_vueltas` | INT NOT NULL                  | Número de vueltas                          |
| `longitud`       | FLOAT NOT NULL                | Longitud del circuito                      |
| `curvas_lentas`  | INT NOT NULL                  | Número de curvas lentas                    |
| `curvas_media`   | INT NOT NULL                  | Número de curvas de media velocidad        |
| `curvas_rapidas` | INT NOT NULL                  | Número de curvas rápidas                   |

### 5. `coche`
Almacena información sobre los coches.

| Columna           | Tipo                          | Descripción                                |
|-------------------|-------------------------------|--------------------------------------------|
| `id`              | INT AUTO_INCREMENT            | Identificador único para cada coche        |
| `nombre`          | VARCHAR(255) NOT NULL         | Nombre del coche                           |
| `codigo`          | VARCHAR(255) NOT NULL UNIQUE  | Código único del coche                     |
| `ers_curva_lenta` | FLOAT NOT NULL                | Eficiencia del ERS en curvas lentas        |
| `ers_curva_media` | FLOAT NOT NULL                | Eficiencia del ERS en curvas de media velocidad |
| `ers_curva_rapida`| FLOAT NOT NULL                | Eficiencia del ERS en curvas rápidas       |
| `consumo`         | FLOAT NOT NULL                | Consumo de combustible                     |

### 6. `noticia`
Almacena información sobre las noticias.

| Columna    | Tipo                          | Descripción                                |
|------------|-------------------------------|--------------------------------------------|
| `id`       | INT AUTO_INCREMENT            | Identificador único para cada noticia      |
| `permalink`| VARCHAR(100) NOT NULL UNIQUE  | Enlace permanente único para la noticia    |
| `titulo`   | VARCHAR(100) NOT NULL         | Título de la noticia                       |
| `imagen`   | BLOB                          | Imagen asociada a la noticia               |
| `texto`    | TEXT NOT NULL CHECK (CHAR_LENGTH(texto) BETWEEN 500 AND 2000) | Contenido de la noticia                    |

### 7. `votacion`
Almacena información sobre los eventos de votación.

| Columna       | Tipo                          | Descripción                                |
|---------------|-------------------------------|--------------------------------------------|
| `id`          | INT AUTO_INCREMENT            | Identificador único para cada votación     |
| `permalink`   | VARCHAR(100) NOT NULL UNIQUE  | Enlace permanente único para la votación   |
| `titulo`      | VARCHAR(100) NOT NULL         | Título de la votación                      |
| `descripcion` | VARCHAR(500)                  | Descripción de la votación                 |
| `limite`      | DATETIME NOT NULL             | Fecha límite para la votación              |
| `lista_pilotos`| TEXT                         | Lista de IDs de pilotos (puede ser JSON o serializada) |

### 8. `votaciones_emitidas`
Almacena información sobre los votos emitidos.

| Columna         | Tipo                          | Descripción                                |
|-----------------|-------------------------------|--------------------------------------------|
| `id`            | INT AUTO_INCREMENT            | Identificador único para cada voto         |
| `votacion_id`   | INT NOT NULL                  | ID de la votación                          |
| `nombre_publico`| VARCHAR(255) NOT NULL         | Nombre del votante                         |
| `email`         | VARCHAR(255) NOT NULL         | Correo electrónico del votante             |
| `piloto_id`     | INT NOT NULL                  | ID del piloto votado                       |