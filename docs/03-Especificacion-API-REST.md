# 📄 Documento 03: Especificación de la API REST

**Proyecto:** LUGARITMO
**Versión:** 1.1 (Full Indicator Support)
**Base Path:** `/api/v1`

---

## 1. Introducción
Este documento define el contrato de comunicación entre el Backend (Spring Boot) y el Frontend (Mapa interactivo). La API entrega indicadores multidimensionales (economía, salud, vivienda y demografía) a nivel provincial, permitiendo el filtrado dinámico mediante parámetros para optimizar el rendimiento y la experiencia de usuario.

---

## 2. Endpoints Principales

### 2.1. GET /provinces
Punto de entrada principal para el renderizado del mapa de coropletas y comparativas.

* **Query Parameters (Filtros):**
    * `indicator` (String): Campo técnico del dato a visualizar (ej. `housingPriceSqm`, `unemploymentRate`).
    * `year` (Integer): Año de referencia (por defecto 2025).

* **Ejemplo de Petición (Modo Comparativo):**
    `GET /api/v1/provinces?indicator=housingPriceSqm&year=2025`

* **Respuesta Exitosa (200 OK):**
    ```json
    [
      {
        "id": 28,
        "name": "Madrid",
        "housingPriceSqm": 4250.75
      },
      {
        "id": 8,
        "name": "Barcelona",
        "housingPriceSqm": 4015.20
      }
    ]
    ```

### 2.2. GET /provinces/{id}
Devuelve la ficha técnica detallada de una provincia específica con todos los indicadores disponibles en la base de datos.

* **Ejemplo de Petición:**
    `GET /api/v1/provinces/28`

---

## 3. Diccionario de Datos (Campos JSON)

| Campo | Tipo | Descripción | Unidad |
| :--- | :--- | :--- | :--- |
| **Identificadores** | | | |
| `id` | Long | Código oficial del INE (Primary Key). | - |
| `name` | String | Nombre oficial de la provincia. | - |
| **Demografía** | | | |
| `population` | Double | Población total estimada. | Personas |
| `averageAge` | Double | Edad media de los residentes. | Años |
| `birthRate` | Double | Tasa bruta de natalidad. | Nacimientos x 1.000 hab. |
| **Economía y Trabajo** | | | |
| `averageIncome` | Double | Renta media por hogar. | Euros (€) |
| `unemploymentRate` | Double | Tasa de paro (EPA). | Porcentaje (%) |
| `educationLevel` | Double | % Población con estudios superiores. | Porcentaje (%) |
| **Vivienda** | | | |
| `housingPriceSqm` | Double | Precio medio del metro cuadrado. | Euros/m² |
| `rentalPrice` | Double | Precio medio del alquiler mensual. | Euros (€) |
| **Salud** | | | |
| `lifeExpectancy` | Double | Esperanza de vida al nacer. | Años |
| `tumorMortality` | Double | Riesgo relativo de mortalidad por tumores. | Índice (Base 100) |
| `hospitalizationRate` | Double | Tasa de morbilidad (ingresos hospitalarios). | Altas x 100.000 hab. |

---

## 4. Códigos de Respuesta Globales

* **200 OK**: Petición procesada correctamente.
* **400 Bad Request**: El indicador solicitado no existe o el formato del año es incorrecto.
* **404 Not Found**: El código de provincia (ID) no existe en el sistema.
* **500 Internal Server Error**: Error en el servicio de importación de datos o fallo en PostgreSQL/PostGIS.
