# 📄 Documento 01: Especificación de Requisitos (SRS) y Historias de Usuario

**Proyecto:** Lugaritmo

**Versión:** 1.1 (Revisión MVP REST/Provincias)

**Estado:** Análisis Inicial

---

## 1. Introducción y Visión

Lugaritmo es una plataforma de visualización de datos diseñada para democratizar el acceso a la información socioeconómica compleja en España.

La aplicación permite a usuarios ciudadanos, analistas y gestores públicos interactuar con mapas interactivos basados en divisiones provinciales y autonómicas para el MVP, contando con una arquitectura escalable diseñada para integrar secciones censales en fases posteriores. Esto facilita la toma de decisiones informada mediante herramientas de comparación avanzada.

---

## 2. Requisitos Funcionales (RF)

Se utiliza la priorización **MoSCoW** (Must have, Should have, Could have, Won't have).

| ID | Requisito | Descripción | Prioridad |
| --- | --- | --- | --- |
| **RF-01** | **Buscador Geográfico** | El sistema debe permitir localizar áreas por nombre de provincia o comunidad autónoma. | **Must** |
| **RF-02** | **Mapa de Coropletas** | El sistema debe representar indicadores mediante una escala de colores sobre los polígonos del INE. | **Must** |
| **RF-03** | **API RESTful** | El sistema debe exponer endpoints REST estandarizados para la entrega eficiente de indicadores geográficos e información socioeconómica al cliente web. | **Must** |
| **RF-04** | **Modo Comparativo** | El sistema debe permitir una vista de pantalla dividida (Split View) con sincronización de movimiento. | **Should** |
| **RF-05** | **Cálculo de Desviación** | El sistema debe mostrar automáticamente la diferencia del área seleccionada frente a la media nacional. | **Should** |
| **RF-06** | **Exportación de Datos** | El sistema debe permitir descargar la información visualizada en formato CSV o JSON. | **Could** |

---

## 3. Requisitos No Funcionales (RNF)

| ID | Atributo | Especificación |
| --- | --- | --- |
| **RNF-01** | **Rendimiento** | El mapa debe renderizar las geometrías y datos en menos de 2.0 segundos. |
| **RNF-02** | **Escalabilidad** | La arquitectura debe permitir añadir nuevas categorías de indicadores sin modificar el esquema de base de datos. |
| **RNF-03** | **Accesibilidad** | La interfaz debe cumplir con el nivel AA de la norma WCAG 2.1 (paletas aptas para daltónicos). |
| **RNF-04** | **Disponibilidad** | Despliegue en infraestructura con alta disponibilidad (Vercel/Render) y certificado SSL activo. |

---

## 4. Historias de Usuario (User Stories)

Estas historias definen el comportamiento desde la perspectiva del usuario final y establecen el alcance del MVP.

### HU-01: Localización y Contexto

**Como** usuario interesado en la demografía y economía regional,

**quiero** buscar mi provincia en el mapa o buscador,

**para** visualizar de inmediato los indicadores clave actualizados a 2025.

* **Criterio de Aceptación 1:** El buscador debe listar y autocompletar las 50 provincias españolas.
* **Criterio de Aceptación 2:** El mapa debe hacer foco (zoom) en la geometría de la provincia seleccionada.

### HU-02: Análisis de Relación (Split View)

**Como** analista social,

**quiero** abrir el mapa de "Renta Media" a la izquierda y el de "Nivel de Estudios" a la derecha,

**para** identificar visualmente si las zonas con mayor renta coinciden con las de mayor educación.

* **Criterio de Aceptación 1:** Al mover un mapa, el otro debe desplazarse exactamente a las mismas coordenadas.
* **Criterio de Aceptación 2:** Cada lado debe mostrar su propia leyenda de colores.

### HU-03: Consulta de Salud y Riesgos

**Como** ciudadano o investigador,

**quiero** filtrar los datos provinciales por categorías como "Esperanza de vida" o "Tasa de Paro",

**para** comparar visualmente las diferencias territoriales a nivel nacional.

* **Criterio de Aceptación 1:** El mapa debe repintar la escala de colores basándose en la respuesta JSON del nuevo indicador seleccionado.
* **Criterio de Aceptación 2:** El panel lateral debe explicar claramente el significado del indicador.

---

## 5. Notas de Arquitectura Backend (Reglas de Implementación)

El modelo de dominio y la capa de servicios del backend se implementarán con nomenclatura en inglés (ej. `Province`, `DataImportService`), exponiendo los datos a través de controladores REST (`@RestController`).
