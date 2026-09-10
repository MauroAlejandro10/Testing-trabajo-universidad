# Plan de Pruebas

## 1. Identificación del proyecto

**Nombre del proyecto:** Motor de Evaluación de Asistencia

**Tecnología:** Java y Spring Boot

**Autor:** Mauricio Alejandro Rosales Vergara

## 2. Objetivo del Plan de Pruebas

El objetivo de este plan es comprobar que el Motor de Evaluación de Asistencia procese correctamente los datos ingresados, aplique las reglas de negocio definidas y devuelva un resultado estructurado.

Las pruebas contemplarán casos exitosos, escenarios alternativos, datos inválidos, combinaciones de parámetros y valores límite.

## 3. Datos de entrada

El algoritmo procesará los siguientes datos:

- Cantidad total de obligaciones.
- Cantidad de asistencias efectivas.
- Cantidad de abonos.
- Porcentaje mínimo requerido.

## 4. Resultados esperados

El algoritmo devolverá:

- Total de asistencias computadas.
- Porcentaje de asistencia sin abonos.
- Porcentaje de asistencia con abonos.
- Estado final de la evaluación.
- Mensaje explicativo.

## 5. Matriz de casos de prueba

### 5.1 Casos exitosos y estados posibles

| ID | Escenario | Obligaciones | Asistencias | Abonos | Mínimo | Total computado | % sin abonos | % con abonos | Estado esperado |
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
| CP-23 | Solicitud nula | No aplica | No aplica | No aplica | No aplica | Excepción: La solicitud no puede ser nula |
| CP-24 | Porcentaje mínimo no numérico (`NaN`) | 20 | 10 | 0 | NaN | Excepción: El porcentaje mínimo debe estar entre 1 y 100 |
| CP-25 | Porcentaje mínimo infinito | 20 | 10 | 0 | Infinito | Excepción: El porcentaje mínimo debe estar entre 1 y 100 |

### 5.3 Valores límite y combinaciones especiales

| ID | Escenario | Obligaciones | Asistencias | Abonos | Mínimo | Total computado | % sin abonos | % con abonos | Estado esperado |
|---|---|---:|---:|---:|---:|---:|---:|---:|---|
| CP-15 | Asistencia completa | 20 | 20 | 0 | 50% | 20 | 100% | 100% | CUMPLE |
| CP-16 | Sin asistencias ni abonos | 20 | 0 | 0 | 50% | 0 | 0% | 0% | NO_CUMPLE |
| CP-17 | Todas las obligaciones cubiertas por abonos | 20 | 0 | 20 | 50% | 20 | 0% | 100% | CUMPLE_CON_ABONOS |
| CP-18 | Porcentaje mínimo en su límite inferior | 100 | 1 | 0 | 1% | 1 | 1% | 1% | CUMPLE |
| CP-19 | Porcentaje mínimo en su límite superior | 20 | 20 | 0 | 100% | 20 | 100% | 100% | CUMPLE |
| CP-20 | Asistencias y abonos igualan exactamente las obligaciones | 20 | 15 | 5 | 75% | 20 | 75% | 100% | CUMPLE |
| CP-21 | Cálculo con resultado decimal | 3 | 2 | 0 | 60% | 2 | 66,67% | 66,67% | CUMPLE |
| CP-22 | Una sola obligación cumplida | 1 | 1 | 0 | 50% | 1 | 100% | 100% | CUMPLE |

## 6. Reglas de precisión

Los porcentajes mostrados en el resultado serán redondeados a dos decimales.

Para determinar el estado final, el algoritmo utilizará el porcentaje calculado antes de mostrarlo, evitando que el redondeo visual altere incorrectamente la decisión.