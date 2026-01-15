# MoodUpdate

**MoodUpdate** es una plataforma diseñada para automatizar el envío de datos anímico-relevantes (pasos y sueño) a los psiquiatras de pacientes con trastorno bipolar. El objetivo es proporcionar una herramienta de monitoreo objetivo que ayude en la gestión del tratamiento.

## Módulos del Proyecto

### 1. Aplicación Móvil (Android)
Aplicación nativa que actúa como recolector de datos en el teléfono del paciente.
*   **Integración con Health Connect:** Obtiene datos precisos de actividad física y descanso.
*   **Sincronización en Segundo Plano:** Los datos se suben automáticamente a la base de datos sin requerir intervención del usuario.
*   **Privacidad y Seguridad:** Manejo seguro de información de salud.
*   **Gestión de Identidad:** Generación de un UID único que el paciente puede compartir con su doctor.

### 2. Base de Datos (Supabase / PostgreSQL)
Almacenamiento seguro y escalable alojado en Supabase.
*   **Estructura de Pasos:** Registro del total de pasos realizados cada hora.
*   **Estructura de Sueño:** Registro detallado de sesiones de sueño (inicio, fin y duración).
*   **Seguridad RLS:** Políticas de acceso diferenciadas para App (escritura) y Doctor (lectura).

### 3. Panel Web (Dashboard Médico)
Página web para el psiquiatra con visualización de datos en tiempo real.
*   **Acceso Directo (MVP):** Visualización mediante UID del paciente.
*   **Gráficos Dinámicos:** Implementación con Chart.js para tendencias de actividad y sueño.

---

## 🚀 Hoja de Ruta (Planes Futuros)

### 1. Análisis de Prosodia
*   Implementar un módulo de análisis de voz para detectar biomarcadores digitales en el habla (velocidad, tono, pausas).
*   Utilizar estos datos como predictores de fases maníacas o depresivas.

### 2. Recuperación Histórica (Backfill)
*   Aprovechar la capacidad de Health Connect para extraer datos de los últimos 30 días.
*   Permitir que la App llene automáticamente las tablas de Supabase con el historial previo al momento de la instalación.

### 3. Persistencia y Robustez
*   Implementar una "memoria de sincronización" para que la app recuerde la última vez que subió datos exitosamente.
*   Asegurar que no haya pérdida de información si el teléfono permanece apagado por varios días.

### 4. Seguridad Avanzada
*   Restablecer el sistema de autenticación JWT para el psiquiatra.
*   Implementar encriptación de datos sensibles de extremo a extremo.

### 5. Nuevas Variables Clínicas
*   Integración de Ritmo Cardíaco y Variabilidad de la Frecuencia Cardíaca (HRV).
*   Registro manual de estado de ánimo diario (Mood Journaling).

---
*Este proyecto busca mejorar la calidad de vida de los pacientes mediante el uso de tecnología para un monitoreo clínico más preciso.*
