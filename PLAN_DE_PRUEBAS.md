# Plan de Pruebas

## 1. Identificación del proyecto

**Nombre del proyecto:** Motor de Evaluación de Asistencia  
**Tecnología:** Java y Spring Boot  
**Autor:** Mauricio Alejandro Rosales Vergara

## 2. Objetivo del Plan de Pruebas

El objetivo de este plan es comprobar que el Motor de Evaluación de Asistencia procese correctamente los datos ingresados, aplique las reglas de negocio definidas y devuelva un resultado estructurado.

Las pruebas contemplan casos exitosos, escenarios alternativos, datos inválidos, combinaciones de parámetros, valores límite y respuestas controladas de la API REST.

## 3. Datos de entrada

El algoritmo procesa los siguientes datos:

- Cantidad total de obligaciones.
- Cantidad de asistencias efectivas.
- Cantidad de abonos.
- Porcentaje mínimo requerido.

## 4. Resultados esperados

El algoritmo devuelve:

- Total de asistencias computadas.
- Porcentaje de asistencia sin abonos.
- Porcentaje de asistencia con abonos.
- Estado final de la evaluación.
- Mensaje explicativo.

Los estados posibles son:

- `CUMPLE`
- `CUMPLE_CON_ABONOS`
- `EN_RIESGO`
- `NO_CUMPLE`

## 5. Matriz de casos de prueba

### 5.1 Casos exitosos y estados posibles

| ID | Escenario | Obligaciones | Asistencias | Abonos | Mínimo | Total | % sin abonos | % con abonos | Estado esperado |
|---|---|---:|---:|---:|---:|---:|---:|---:|---|
| CP-01 | Cumple mediante asistencias efectivas | 20 | 12 | 2 | 50% | 14 | 60% | 70% | CUMPLE |
| CP-02 | Cumple exactamente el mínimo | 20 | 10 | 0 | 50% | 10 | 50% | 50% | CUMPLE |
| CP-03 | Cumple solamente gracias a los abonos | 20 | 8 | 2 | 50% | 10 | 40% | 50% | CUMPLE_CON_ABONOS |
| CP-04 | Se encuentra exactamente a 10 puntos del mínimo | 20 | 8 | 0 | 50% | 8 | 40% | 40% | EN_RIESGO |
| CP-05 | Se encuentra a más de 10 puntos del mínimo | 20 | 7 | 0 | 50% | 7 | 35% | 35% | NO_CUMPLE |

### 5.2 Casos de datos inválidos

| ID | Escenario | Obligaciones | Asistencias | Abonos | Mínimo | Resultado esperado |
|---|---|---:|---:|---:|---:|---|
| CP-06 | Obligaciones iguales a cero | 0 | 0 | 0 | 50% | Excepción: Las obligaciones deben ser mayores que cero |
| CP-07 | Obligaciones negativas | -10 | 0 | 0 | 50% | Excepción: Las obligaciones deben ser mayores que cero |
| CP-08 | Asistencias negativas | 20 | -1 | 0 | 50% | Excepción: Las asistencias no pueden ser negativas |
| CP-09 | Abonos negativos | 20 | 5 | -1 | 50% | Excepción: Los abonos no pueden ser negativos |
| CP-10 | Asistencias mayores que las obligaciones | 20 | 21 | 0 | 50% | Excepción: Las asistencias no pueden superar las obligaciones |
| CP-11 | Suma de asistencias y abonos mayor que las obligaciones | 20 | 18 | 3 | 50% | Excepción: La suma de asistencias y abonos no puede superar las obligaciones |
| CP-12 | Porcentaje mínimo igual a cero | 20 | 10 | 0 | 0% | Excepción: El porcentaje mínimo debe estar entre 1 y 100 |
| CP-13 | Porcentaje mínimo mayor que cien | 20 | 10 | 0 | 101% | Excepción: El porcentaje mínimo debe estar entre 1 y 100 |
| CP-14 | Porcentaje mínimo negativo | 20 | 10 | 0 | -1% | Excepción: El porcentaje mínimo debe estar entre 1 y 100 |
| CP-15 | Solicitud nula | No aplica | No aplica | No aplica | No aplica | Excepción: La solicitud no puede ser nula |
| CP-16 | Porcentaje mínimo no numérico (`NaN`) | 20 | 10 | 0 | NaN | Excepción: El porcentaje mínimo debe estar entre 1 y 100 |
| CP-17 | Porcentaje mínimo infinito | 20 | 10 | 0 | Infinito | Excepción: El porcentaje mínimo debe estar entre 1 y 100 |

### 5.3 Valores límite y combinaciones especiales

| ID | Escenario | Obligaciones | Asistencias | Abonos | Mínimo | Total | % sin abonos | % con abonos | Estado esperado |
|---|---|---:|---:|---:|---:|---:|---:|---:|---|
| CP-18 | Asistencia completa | 20 | 20 | 0 | 50% | 20 | 100% | 100% | CUMPLE |
| CP-19 | Sin asistencias ni abonos | 20 | 0 | 0 | 50% | 0 | 0% | 0% | NO_CUMPLE |
| CP-20 | Todas las obligaciones cubiertas por abonos | 20 | 0 | 20 | 50% | 20 | 0% | 100% | CUMPLE_CON_ABONOS |
| CP-21 | Porcentaje mínimo en su límite inferior | 100 | 1 | 0 | 1% | 1 | 1% | 1% | CUMPLE |
| CP-22 | Porcentaje mínimo en su límite superior | 20 | 20 | 0 | 100% | 20 | 100% | 100% | CUMPLE |
| CP-23 | Asistencias y abonos igualan las obligaciones | 20 | 15 | 5 | 75% | 20 | 75% | 100% | CUMPLE |
| CP-24 | Cálculo con resultado decimal | 3 | 2 | 0 | 60% | 2 | 66,67% | 66,67% | CUMPLE |
| CP-25 | Una sola obligación cumplida | 1 | 1 | 0 | 50% | 1 | 100% | 100% | CUMPLE |
| CP-26 | Faltan 11 puntos porcentuales para el mínimo | 100 | 39 | 0 | 50% | 39 | 39% | 39% | NO_CUMPLE |

### 5.4 Controlador REST y manejo de errores

| ID | Escenario | Entrada | Resultado esperado |
|---|---|---|---|
| CP-27 | Solicitud válida recibida por el controlador | 20 obligaciones, 8 asistencias, 2 abonos y mínimo 50% | Resultado con estado `CUMPLE_CON_ABONOS` |
| CP-28 | Solicitud inválida enviada a la API | Obligaciones iguales a cero | Respuesta HTTP 400 con mensaje explicativo |
| CP-29 | Conversión de excepción en respuesta estructurada | `DatosAsistenciaInvalidosException` | `ErrorRespuestaDTO` con estado 400 y mensaje correspondiente |

## 6. Reglas de precisión

Los porcentajes incluidos en el resultado serán redondeados a dos decimales.

Para determinar el estado final, el algoritmo utilizará el porcentaje calculado antes de presentarlo, evitando que el redondeo visual modifique incorrectamente la decisión.

En las pruebas que comparan valores de tipo `double` se utilizará una tolerancia de `0.001`.

## 7. Estrategia de pruebas unitarias

Las pruebas serán implementadas con JUnit 5 y utilizarán únicamente aserciones tradicionales, entre ellas:

- `assertEquals`
- `assertNotNull`
- `assertThrows`
- `assertDoesNotThrow`

Cada prueba seguirá el patrón Arrange-Act-Assert:

1. **Arrange:** preparación de los datos y componentes.
2. **Act:** ejecución del método que se desea probar.
3. **Assert:** comprobación del resultado obtenido.

Los servicios y controladores serán instanciados utilizando componentes reales, sin mocks.

## 8. Criterios de aprobación

Una prueba se considerará aprobada cuando:

- El resultado calculado coincida con el resultado esperado.
- El estado final sea el correspondiente a las reglas de negocio.
- Los porcentajes sean calculados y redondeados correctamente.
- Los datos inválidos produzcan la excepción esperada.
- Las respuestas de error utilicen el código HTTP correspondiente.
- La ejecución completa de Maven termine con `BUILD SUCCESS`.

## 9. Comando de ejecución

La suite completa se ejecutará desde la raíz del proyecto mediante:

```powershell
.\mvnw.cmd clean test