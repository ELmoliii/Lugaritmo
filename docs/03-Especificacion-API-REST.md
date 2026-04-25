# 📄 Documento 03: Especificación de la API REST

**Proyecto:** LUGARITMO
**Versión:** 1.0 (REST Implementation)
**Base Path:** `/api/v1`

---

## 1. Introducción
Este documento define los puntos de entrada (endpoints), los formatos de intercambio de datos (JSON) y los códigos de respuesta de la API de LUGARITMO. La API está diseñada siguiendo los principios REST para facilitar la integración con el cliente de visualización cartográfica.

---

## 2. Definición de Recursos

### 2.1. Provinces (Provincias)
Representa la unidad geográfica principal del MVP con sus indicadores socioeconómicos y de salud actualizados a 2025.

#### **GET /provinces**
Obtiene la lista completa de provincias con sus indicadores básicos para el renderizado inicial del mapa.

* **Respuesta Exitosa (200 OK):**
    ```json
    [
      {
        "id": 28,
        "name": "Madrid",
        "population": 6751251.0,
        "unemploymentRate": 8.52,
        "lifeExpectancy": 84.2,
        "updatedAt": "2025-01-15T10:30:00Z"
      },
      {
        "id": 8,
        "name": "Barcelona",
        "population": 5714730.0,
        "unemploymentRate": 8.89,
        "lifeExpectancy": 83.9,
        "updatedAt": "2025-01-15T10:30:00Z"
      }
    ]
    ```

#### **GET /provinces/{id}**
Obtiene el detalle expandido de una provincia específica mediante su código oficial del INE.

* **Parámetros:**
    * `id` (Long): Código identificador de la provincia (ej. 28).
* **Respuesta Exitosa (200 OK):** Devuelve el objeto individual.
* **Error (404 Not Found):** Si la provincia no existe en la base de datos.

---

## 3. Diccionario de Datos (Campos JSON)

| Campo | Tipo | Descripción | Unidad |
| :--- | :--- | :--- | :--- |
| `id` | Long | Código oficial del INE (PK). | - |
| `name` | String | Nombre oficial de la provincia. | - |
| `population` | Double | Población total estimada para 2025. | Personas |
| `unemploymentRate` | Double | Tasa de paro (EPA) último trimestre 2025. | Porcentaje (%) |
| `lifeExpectancy` | Double | Esperanza de vida proyectada (Salud). | Años |
| `updatedAt` | DateTime | Fecha de la última sincronización con el INE. | ISO 8601 |

---

## 4. Códigos de Respuesta Globales

La API utiliza códigos de estado HTTP estándar para indicar el éxito o fracaso de las peticiones:

* **`200 OK`**: La petición ha tenido éxito y se devuelve la información.
* **`400 Bad Request`**: La petición es inválida o los parámetros son incorrectos.
* **`404 Not Found`**: El recurso solicitado (provincia o indicador) no existe.
* **`500 Internal Server Error`**: Error inesperado en el servidor (ej. fallo de conexión con PostgreSQL).

---

## 5. Pruebas de Consumo (CURL)
Para verificar el funcionamiento de la API desde la terminal:

```bash
curl -X GET "http://localhost:8080/api/v1/provinces" -H "accept: application/json"
