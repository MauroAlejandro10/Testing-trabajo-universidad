# Motor de Evaluación de Asistencia

Proyecto desarrollado en Java y Spring Boot para evaluar el cumplimiento de asistencia de una persona según sus obligaciones, asistencias efectivas, abonos y porcentaje mínimo requerido.

## Autor

Mauricio Alejandro Rosales Vergara

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Maven
- JUnit 5
- Lombok
- Git y GitHub
- Visual Studio Code

## Funcionalidades

El sistema permite:

- Recibir una solicitud de evaluación mediante una API REST.
- Validar los datos ingresados.
- Calcular la asistencia total.
- Calcular el porcentaje sin abonos.
- Calcular el porcentaje con abonos.
- Redondear los porcentajes a dos decimales.
- Determinar el estado final de asistencia.
- Entregar un mensaje explicativo.
- Responder con un error HTTP 400 cuando los datos son inválidos.

## Datos de entrada

La solicitud contiene:

- `obligaciones`: cantidad total de obligaciones.
- `asistencias`: cantidad de asistencias efectivas.
- `abonos`: cantidad de asistencias abonadas.
- `porcentajeMinimo`: porcentaje exigido para cumplir.

Ejemplo:

```json
{
  "obligaciones": 20,
  "asistencias": 8,
  "abonos": 2,
  "porcentajeMinimo": 50
}
```

## Estados posibles

| Estado | Descripción |
|---|---|
| `CUMPLE` | Cumple el porcentaje solamente con asistencias efectivas. |
| `CUMPLE_CON_ABONOS` | Cumple el porcentaje al sumar los abonos. |
| `EN_RIESGO` | No cumple, pero está como máximo a 10 puntos porcentuales del mínimo. |
| `NO_CUMPLE` | Se encuentra a más de 10 puntos porcentuales del mínimo. |

## Reglas de validación

El sistema comprueba que:

- La solicitud no sea nula.
- Las obligaciones sean mayores que cero.
- Las asistencias no sean negativas.
- Los abonos no sean negativos.
- Las asistencias no superen las obligaciones.
- La suma de asistencias y abonos no supere las obligaciones.
- El porcentaje mínimo esté entre 1 y 100.
- El porcentaje mínimo no sea `NaN` ni infinito.

Si alguna regla no se cumple, se genera una excepción controlada y la API devuelve un error con estado HTTP 400.

Ejemplo:

```json
{
  "estado": 400,
  "mensaje": "Las obligaciones deben ser mayores que cero"
}
```

## Endpoint de la API

### Evaluar asistencia

```http
POST /api/asistencia/evaluar
```

Dirección local:

```text
http://localhost:8080/api/asistencia/evaluar
```

Ejemplo de respuesta exitosa:

```json
{
  "obligaciones": 20,
  "asistencias": 8,
  "abonos": 2,
  "totalComputado": 10,
  "porcentajeMinimo": 50.0,
  "porcentajeSinAbonos": 40.0,
  "porcentajeConAbonos": 50.0,
  "estado": "CUMPLE_CON_ABONOS",
  "mensaje": "Cumple gracias a los abonos"
}
```

## Estructura principal

- `Controller`: recibe las solicitudes HTTP.
- `DTO`: contiene los datos de entrada, salida y error.
- `Services`: contiene las validaciones y los cálculos.
- `Model`: contiene los estados posibles.
- `Exception`: contiene la excepción personalizada y el manejador global.
- `src/test`: contiene las pruebas automatizadas.

## Pruebas automatizadas

El proyecto contiene pruebas para comprobar:

- La carga del contexto de Spring Boot.
- Las reglas de validación.
- Los cálculos de porcentajes.
- Los cuatro estados posibles.
- Los valores límite.
- El redondeo a dos decimales.
- El controlador REST.
- La conversión de excepciones en respuestas HTTP 400.

Actualmente, el proyecto ejecuta **21 pruebas automatizadas** correctamente.

Para ejecutar todas las pruebas en Windows:

```powershell
.\mvnw.cmd clean test
```

Resultado esperado:

```text
Tests run: 21, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Ejecutar la aplicación

En Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

La aplicación quedará disponible en:

```text
http://localhost:8080
```

Para detenerla, presione `Ctrl + C` en la terminal donde se está ejecutando.

## Plan de pruebas

El archivo `PLAN_DE_PRUEBAS.md` documenta:

- Casos exitosos.
- Datos inválidos.
- Valores límite.
- Combinaciones especiales.
- Resultados esperados.
- Criterios de aceptación.