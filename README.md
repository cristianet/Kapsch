# Kapsch Onboarding Test

Bienvenido al proyecto de Onboarding Test de Kapsch

## Artefactos

### Back-End
- **Framework:** Java Spring Boot 3
- **Gestor de Dependencias:** Maven
- **Pruebas:** JUnit 5

URL: http://localhost:8080/weather?latitude=52.52&longitude=13.41

URL: http://localhost:8080/weather-requests

URL: http://localhost:8080/weather-details/1

URL: http://localhost:8080/weather-details

### Front-End
- **Framework:** Angular v18
- **Estilos:** Bootstrap

## Requisitos Previos

Asegúrate de tener instalados los siguientes programas antes de comenzar:

- Java Development Kit (JDK) 17
- Apache Maven
- Node.js y npm (para el front-end)
- Angular CLI (para el front-end)

## Configuración del Proyecto

### Back-End

1. **Clonar el repositorio**:
    ```sh
    git clone <https://github.com/cristianet/Kapsch.git>
    cd backend-kapsch
    ```

2. **Compilar y ejecutar el proyecto**:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

3. **Propiedades de la aplicación**:
    El archivo `application.properties` contiene las configuraciones principales:
    ```properties
    spring.application.name=back-end-kapsch
    ```

### Front-End

1. **Clonar el repositorio** (si no se encuentra en el mismo directorio):
    ```sh
    git clone <https://github.com/cristianet/Kapsch.git>
    cd frontend-kapsch
    ```

2. **Instalar dependencias**:
    ```sh
    npm install
    ```

3. **Ejecutar la aplicación**:
    ```sh
    ng serve
    ```

## Pruebas Unitarias

### Back-End

Para ejecutar las pruebas unitarias del back-end, utiliza el siguiente comando:

```sh
mvn test
```

## Arquitectura

```sh
┌──────────────────────────────────────────────────────────────┐
│                        Spring Boot Application               │
│                                                              │
│  ┌──────────────┐                                            │
│  │  Controller  │                                            │
│  │              │                                            │
│  │ WeatherController  ◄──────────┐                           │
│  └──────────────┘                │                           │
│                                  │                           │
│  ┌──────────────┐                │                           │
│  │   Services   │                │                           │
│  │              │                │                           │
│  │ WeatherService    ◄───────────┘                           │
│  │              │                                            │
│  └──────────────┘                    │                       │
│                                      │                       │
│  ┌──────────────┐                    │                       │
│  │ Repositories │                    │                       │
│  │              │                    │                       │
│  │ WeatherRequestRepository          │                       │
│  │              │                    │                       │
│  │ WeatherDetailRepository           │                       │
│  └──────────────┘                    │                       │
│                                      │                       │
│  ┌──────────────┐                    │                       │
│  │   Entities   │                    │                       │
│  │              │                    │                       │
│  │ WeatherRequest ───────────────────┘                       │
│  │              │                                            │
│  │ WeatherDetail                                             │
│  └──────────────┘                                            │
│                                                              │
└──────────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────────┐
│                        External API                          │
│                                                              │
│ ┌──────────────┐                                             │
│ │  Open-Meteo  │                                             │
│ └──────────────┘                                             │
└──────────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────────┐
│                        Database                              │
│                                                              │
│ ┌──────────────┐                                             │
│ │   SQLite     │                                             │
│ └──────────────┘                                             │
│ ┌────────────────┐                                           │
│ │ Weather_Request                                            │
│ └────────────────┘                                           │
│ ┌────────────────┐                                           │
│ │ Weather_Detail                                             │
│ └────────────────┘                                           │
└──────────────────────────────────────────────────────────────┘
```
