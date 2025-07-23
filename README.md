# My App Ricky and Morty

Aplicación de Android que muestra información sobre personajes y episodios de la serie Rick and Morty. Esta aplicación utiliza la [API de Rick and Morty](https://rickandmortyapi.com/).

## Características

*   Visualización de una lista de personajes.
*   Visualización detallada de un personaje específico, incluyendo:
    *   Imagen del personaje.
    *   Nombre, género, especie, estado y ubicación.
    *   Lista de episodios en los que aparece el personaje.
*   Manejo de estados de carga y errores.



## Tecnologías Utilizadas

*   [Kotlin](https://kotlinlang.org/) como lenguaje de programación principal.
*   [Jetpack Compose](https://developer.android.com/jetpack/compose) para la interfaz de usuario.
*   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) para la lógica de la interfaz de usuario y la gestión del estado.
*   [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) para la inyección de dependencias.
*   [Coil](https://coil-kt.github.io/coil/) para la carga de imágenes.
*   [Retrofit](https://square.github.io/retrofit/) (Probablemente, para las llamadas a la API - ¡añádelo si lo usas!)
*   [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) para la programación asíncrona.

## Prerrequisitos

*   Android Studio (versión recomendada, por ejemplo, Iguana | 2023.2.1 o superior).
*   JDK (versión recomendada, por ejemplo, 17 o superior).
*   Configurar un emulador de Android o tener un dispositivo físico con Android (versión mínima de API, por ejemplo, API 21 o superior).

## Cómo Compilar y Ejecutar

1.  **Clonar el repositorio:**
2.  2.  **Abrir el proyecto en Android Studio:**
    *   Abre Android Studio.
    *   Selecciona "Open" (Abrir).
    *   Navega hasta el directorio donde clonaste el repositorio y selecciónalo.
    *   Android Studio sincronizará el proyecto con Gradle automáticamente.
3.  **Obtener una API Key (Si es necesario):**
    *   La API de Rick and Morty es pública y no requiere una API key para su uso básico. Si en el futuro integras una API que sí lo necesite, deberás indicar aquí cómo obtenerla y dónde configurarla (por ejemplo, en `local.properties`).
4.  **Ejecutar la aplicación:**
    *   Selecciona un dispositivo (emulador o físico) desde la barra de herramientas de Android Studio.
    *   Haz clic en el botón "Run 'app'" (el triángulo verde) o presiona `Shift + F10`.

## Estructura del Proyecto:


*   `data`: Contiene las fuentes de datos (remotas y/o locales), repositorios y modelos de datos (DTOs).
*   `domain`: (Si aplicable) Contiene los casos de uso y modelos de dominio.
*   `presentation`: Contiene la UI (Composable functions, ViewModels) y la lógica de presentación.
*   `di`: (Si aplicable) Módulos de inyección de dependencias (Hilt).

##Acceso
usuario: nestor@mail.com
contraseña: 123456


