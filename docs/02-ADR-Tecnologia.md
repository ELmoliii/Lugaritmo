# 📄 Documento 02: Registro de Decisiones Arquitectónicas (ADR)

**Proyecto:** LUGARITMO

---

## ADR 001: Elección del Stack Tecnológico Inicial

**Estado:** Superado por ADR 002

### 1. Contexto
Necesitamos construir una plataforma que maneje datos geográficos complejos (polígonos del INE) y múltiples indicadores socioeconómicos. El sistema debe ser altamente escalable para permitir la comparación de datos sin penalizar el rendimiento, y el coste de infraestructura debe ser **0€** durante la fase de desarrollo y MVP.

### 2. Decisión
Hemos decidido adoptar el siguiente stack tecnológico:

1. **API:** GraphQL (Spring Boot + DGS).
2. **Base de Datos:** PostgreSQL con extensión PostGIS (vía Supabase).
3. **Arquitectura de Código:** Monorepo.

### 3. Justificación

* **¿Por qué GraphQL?** En **LUGARITMO**, un usuario puede querer ver solo la "Renta" o comparar "Renta + Salud + Edad". Con GraphQL, el cliente pide exactamente esos campos en una sola petición, evitando el *over-fetching* de datos innecesarios.
* **Contrato Fuertemente Tipado:** Al definir un esquema (`.graphqls`), el equipo de Frontend y Backend tiene una "fuente de verdad" única, reduciendo errores de integración.
* **¿Por qué Supabase (PostgreSQL + PostGIS)?** PostGIS es el estándar de oro para manejar las "siluetas" de los barrios. Permite realizar consultas de intersección y cercanía de forma nativa.
* **Costo Cero:** Supabase ofrece una capa gratuita generosa que incluye PostgreSQL real, a diferencia de otras opciones NoSQL que carecen de soporte robusto para coordenadas.
* **¿Por qué Monorepo?** Permite gestionar todo el ciclo de vida del software (documentación, backend y frontend) en un único repositorio de GitHub, facilitando la revisión de código y la coherencia de versiones.

### 4. Consecuencias
* **Positivas:** Reducción drástica de la latencia en el dashboard comparativo y facilidad para añadir nuevos indicadores del INE sin migrar la base de datos.
* **Negativas:** Mayor curva de aprendizaje inicial para configurar los *resolvers* de GraphQL en Spring Boot frente a una API REST tradicional.

---

## ADR 002: Transición a API REST y Alcance Provincial

**Estado:** Aceptado

### 1. Contexto
Tras evaluar el desarrollo inicial, se identificaron bloqueos en la configuración de los *resolvers* de GraphQL y una falta de datos oficiales del INE actualizados a 2025 para el nivel de secciones censales. Además, se reevaluó la dirección del proyecto para alinearla con los estándares más demandados en el mercado laboral actual.

### 2. Decisión
Se han tomado las siguientes decisiones de cambio arquitectónico:

1. **Arquitectura de Comunicación:** Sustituir GraphQL por una **API RESTful** estándar utilizando Spring Boot.
2. **Nivel Geográfico:** Pivotar del nivel de sección censal al nivel de **Provincias y Comunidades Autónomas** para el MVP.
3. **Nomenclatura:** Todo el desarrollo de backend (clases y funciones) se realizará estrictamente en inglés (ej. `Province`, `RestController`, `DataImportService`).

### 3. Justificación

* **Oportunidades Laborales y Experiencia:** El dominio de **APIs REST** con Spring Boot es el estándar más extendido en la industria. Centrar el desarrollo en esta tecnología proporciona una experiencia técnica más alineada con los requisitos de las empresas de desarrollo de software actuales, optimizando el perfil profesional del autor para obtener un beneficio propio y experiencia laboral útil.
* **Eficiencia en el Desarrollo:** REST reduce la complejidad innecesaria en la gestión de esquemas, permitiendo centrar el esfuerzo en la lógica de negocio y en la visualización de datos, lo que agiliza la entrega del MVP.
* **Fiabilidad de los Datos (Target 2025):** El nivel provincial es el único que garantiza el acceso a datos reales y oficiales del INE para el año **2025**. Esto evita mostrar información desactualizada, aportando un valor real y práctico a la herramienta.
* **Escalabilidad:** Mantener la arquitectura en Spring Boot asegura que, en fases futuras, el sistema pueda escalar de nuevo a niveles micro-geográficos sin cambiar el paradigma de comunicación.

### 4. Consecuencias
* **Positivas:** Desarrollo más fluido, mayor facilidad de pruebas mediante *endpoints* directos y un código más mantenible y profesional.
* **Negativas:** Se descarta la flexibilidad de consultas personalizadas que ofrecía GraphQL, aunque en este nivel de datos (50 provincias) el impacto en el rendimiento es inexistente.
