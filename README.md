## Datos del proyecto
- PUERTO: 8087
- SPRING BOOT: v3.3.4
- JAVA: 17
- MAVEN: 3.9.6
- Patrón de diseño: MVC

## Empezando

1. Instalar maven, para ello siga los pasos de la página oficial de apache:
   https://maven.apache.org/install.html
2. Abrir el proyecto en su entorno de desarrollo, en este caso se usa Eclipse **File / Open Proyects From File System**, elegir la ruta al directorio y finish
3. Limpiar las librerías que se tienen actualmente, dando clic derecho sobre el proyecto **Run As / mvn clean install**
4. Instalar las dependencias del proyecto. Para ello clic derecho sobre el proyecto **Run As / Mvn install**
5. Ejecutar el proyecto, como SpringBoot App. Para ello clic derecho sobre el proyecto **Run As / Spring Boot App**
6. Verificar ejecución usando las rutas de ejemplo:

- GET: Obtiene un o todos los registros de la tabla
  http://localhost:8087/portalWebFormula1/circuitos
  http://localhost:8087/portalWebFormula1/circuitos/1
- POST: Inserta datos en la tabla. No colocar Id es autoincremental. Retorna booleano
- PUT: Actualiza los datos de la tabla. Obligatorio el Id. Retorna todos los elementos actualizados
- DELETE: Elimina datos de la tabla. Retorna booleano

