## DATOS DEL PROYECTO BACKEND
PUERTO: 8081
SPRING BOOT: v3.3.4
JAVA: 17
MAVEN: 2.2.1

## EJEMPLOS

CRUD TABLA CIRCUITOS:
  PETICION GET: Obtiene un o todos los registros de la tabla
    http://localhost:8087/portalWebFormula1/circuitos
    http://localhost:8087/portalWebFormula1/circuitos/1
  PETICION POST: Inserta datos en la tabla. No colocar Id es autoincremental. Retorna booleano
    http://localhost:8087/portalWebFormula1/circuitos
  PETICION PUT: Actualiza los datos de la tabla. Obligatorio el Id. Retorna todos los elementos actualizados
    http://localhost:8087/portalWebFormula1/circuitos
  PTICION DELETE: Elimina datos de la tabla. Retorna booleano
    http://localhost:8087/portalWebFormula1/circuitos
