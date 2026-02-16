
# 📄 Documento 02: ADR 001 - Elección del Stack Tecnológico

**Proyecto:** LUGARITMO

**Estado:** Aceptado

**Fecha:** 2026-02-09

## 1. Contexto

Necesitamos construir una plataforma que maneje datos geográficos complejos (polígonos del INE) y múltiples indicadores socioeconómicos. El sistema debe ser altamente escalable para permitir la comparación de datos sin penalizar el rendimiento, y el coste de infraestructura debe ser **0€** durante la fase de desarrollo y MVP.

## 2. Decisión

Hemos decidido adoptar el siguiente stack tecnológico:

1. **API:** GraphQL (Spring Boot + DGS).
2. **Base de Datos:** PostgreSQL con extensión PostGIS (vía Supabase).
3. **Arquitectura de Código:** Monorepo.

## 3. Justificación

### ¿Por qué GraphQL?

* **Flexibilidad en el Frontend:** En **LUGARITMO**, un usuario puede querer ver solo la "Renta" o comparar "Renta + Salud + Edad". Con GraphQL, el cliente pide exactamente esos campos en una sola petición, evitando el *over-fetching* de datos innecesarios.
* **Contrato Fuertemente Tipado:** Al definir un esquema (`.graphqls`), el equipo de Frontend y Backend tiene una "fuente de verdad" única, reduciendo errores de integración.

### ¿Por qué Supabase (PostgreSQL + PostGIS)?

* **Potencia Geoespacial:** PostGIS es el estándar de oro para manejar las "siluetas" de los barrios. Permite realizar consultas de intersección y cercanía de forma nativa.
* **Costo Cero:** Supabase ofrece una capa gratuita generosa que incluye PostgreSQL real, a diferencia de otras opciones NoSQL que carecen de soporte robusto para coordenadas.

### ¿Por qué Monorepo?

* **Simplicidad de Entrega:** Permite gestionar todo el ciclo de vida del software (documentación, backend y frontend) en un único repositorio de GitHub, facilitando la revisión de código y la coherencia de versiones.

## 4. Consecuencias

* **Positivas:** Reducción drástica de la latencia en el dashboard comparativo y facilidad para añadir nuevos indicadores del INE sin migrar la base de datos.
* **Negativas:** Mayor curva de aprendizaje inicial para configurar los *resolvers* de GraphQL en Spring Boot frente a una API REST tradicional.