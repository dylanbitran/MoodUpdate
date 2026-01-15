# MoodUpdate

**MoodUpdate** es una plataforma diseñada para automatizar el envío de datos anímico-relevantes (pasos y sueño) a los psiquiatras de pacientes con trastorno bipolar. El objetivo es proporcionar una herramienta de monitoreo objetivo que ayude en la gestión del tratamiento.

## Módulos del Proyecto

### 1. Aplicación Móvil (Android)
Aplicación nativa que actúa como recolector de datos en el teléfono del paciente.
*   **Integración con Health Connect:** Obtiene datos precisos de actividad física y descanso.
*   **Sincronización en Segundo Plano:** Los datos se suben automáticamente a la base de datos sin requerir intervención del usuario.
*   **Privacidad y Seguridad:** Manejo seguro de información de salud.
*   **Gestión de Identidad:** Generación de un UID único que el paciente puede compartir con su doctor.
*   **Indicador de Estado:** Visualización clara del estado de conexión y sincronización.

### 2. Base de Datos (Supabase / PostgreSQL)
Almacenamiento seguro y escalable alojado en Supabase.
*   **Estructura de Pasos:** Registro del total de pasos realizados cada hora.
*   **Estructura de Sueño:** Registro detallado de sesiones de sueño, incluyendo hora de inicio, hora de fin, duración y cantidad de eventos en las últimas 24 horas.
*   **Seguridad RLS:** Políticas de seguridad a nivel de fila (Row Level Security) para proteger los datos.

### 3. Panel Web (Dashboard Médico)
Página web técnica, liviana y fácil de usar para el psiquiatra.
*   **Acceso Seguro:** Sistema de autenticación para garantizar la privacidad de los pacientes.
*   **Gestión de Pacientes:** Lista interactiva de pacientes vinculados al psiquiatra.
*   **Visualización de Datos:** 
    *   **Gráfico de Actividad:** Pasos realizados por hora.
    *   **Gráfico de Sueño:** Representación continua de los periodos de descanso (inicio/fin) y su duración.
    *   **Filtros Temporales:** Visualización por día, semana o mes con gráficos suavizados y coordinados.

## Requisitos Técnicos
*   **Android:** SDK 34+ (Compilado con SDK 36 para compatibilidad con librerías modernas).
*   **Backend:** Supabase (Auth, PostgreSQL, Edge Functions).
*   **Frontend Web:** HTML5, CSS3, JavaScript (Supabase-js, Chart.js).

## Instalación y Configuración
1.  **Configuración de Supabase:** Ejecutar los scripts SQL proporcionados para crear las tablas `steps` y `sleep_sessions`.
2.  **Configuración de la App:** Actualizar las credenciales en `SupabaseManager.kt`.
3.  **Despliegue Web:** Subir el dashboard a Supabase Edge Functions para acceso remoto seguro.

---
*Este proyecto busca mejorar la calidad de vida de los pacientes mediante el uso de tecnología para un monitoreo clínico más preciso.*
