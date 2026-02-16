
# 📄 Documento 01: Especificación de Requisitos (SRS) y Historias de Usuario

**Proyecto:** Lugaritmo

**Versión:** 1.0

**Estado:** Análisis Inicial

---

## 1. Introducción y Visión

Lugaritmo es una plataforma de visualización de datos diseñada para democratizar el acceso a la información socioeconómica compleja en España.

La aplicación permite a usuarios ciudadanos, analistas y gestores públicos interactuar con mapas de alta resolución basados en secciones censales, facilitando la toma de decisiones informada mediante herramientas de comparación avanzada.

---

## 2. Requisitos Funcionales (RF)

Se utiliza la priorización **MoSCoW** (Must have, Should have, Could have, Won't have).

| ID | Requisito | Descripción | Prioridad |
| --- | --- | --- | --- |
| **RF-01** | **Buscador Geográfico** | El sistema debe permitir localizar áreas por dirección, municipio o código de sección censal. | **Must** |
| **RF-02** | **Mapa de Coropletas** | El sistema debe representar indicadores mediante una escala de colores sobre los polígonos del INE. | **Must** |
| **RF-03** | **Consulta GraphQL** | El sistema debe permitir la obtención de múltiples indicadores en una sola petición de red. | **Must** |
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

**Como** usuario interesado en el mercado inmobiliario,

**quiero** buscar mi código postal o calle en el mapa,

**para** visualizar de inmediato el nivel de renta y edad media de mi barrio actual.

* **Criterio de Aceptación 1:** El buscador debe autocompletar direcciones reales.
* **Criterio de Aceptación 2:** El mapa debe hacer foco (zoom) en la sección censal correspondiente.

### HU-02: Análisis de Relación (Split View)

**Como** analista social,

**quiero** abrir el mapa de "Renta Media" a la izquierda y el de "Nivel de Estudios" a la derecha,

**para** identificar visualmente si las zonas con mayor renta coinciden con las de mayor educación.

* **Criterio de Aceptación 1:** Al mover un mapa, el otro debe desplazarse exactamente a las mismas coordenadas.
* **Criterio de Aceptación 2:** Cada lado debe mostrar su propia leyenda de colores.

### HU-03: Consulta de Salud y Riesgos

**Como** ciudadano preocupado por el entorno,

**quiero** filtrar los datos por la categoría de "Salud - Mortalidad por Tumores",

**para** conocer si mi zona de residencia presenta un riesgo relativo superior a la media.

* **Criterio de Aceptación 1:** El sistema debe normalizar el dato para que sea comparable con la media nacional (Base 100).
* **Criterio de Aceptación 2:** El panel lateral debe explicar claramente el significado del indicador.

