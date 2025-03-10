# 📚 Hexagonal Arquitectura en Java 🚀
Este proyecto es un ejemplo de cómo implementar una arquitectura hexagonal en Java. 

### 🛠️ Tecnologías Utilizadas
- Java: Lenguaje de programación principal.

- MySQL: Base de datos relacional para almacenar clientes y productos.

- JDBC: Para la conexión y operaciones con la base de datos.

- Maven: Gestión de dependencias y construcción del proyecto.

- Singleton Pattern: Para la gestión de configuraciones globales.

- Arquitectura Hexagonal: Separación de la lógica de negocio de las interfaces externas.

### 🏗️ Estructura del Proyecto
- El proyecto está organizado en diferentes paquetes que representan las capas de la arquitectura hexagonal:

- com.hexagonal.domain: Contiene la lógica de negocio y las entidades del dominio.

- entity: Define las entidades Client y Product.

- repository: Interfaces que definen los puertos para interactuar con el exterior (por ejemplo, ClientRepository y ProductRepository).

- com.hexagonal.application: Contiene los casos de uso que orquestan la lógica de negocio.

- usecase: Implementaciones de los casos de uso (ClientUseCase y ProductUseCase).

- com.hexagonal.infrastructure: Implementaciones concretas de los adaptadores que interactúan con el mundo exterior.

- persistence: Implementaciones de los repositorios para interactuar con la base de datos (ClientRepositoryImpl y ProductRepositoryImpl).

- database: Conexión y configuración de la base de datos (ConnectionDb, ConnMySql, ConnectionFactory).

- com.hexagonal.config: Configuración global del proyecto utilizando el patrón Singleton (HexaSingleton).

### 🚀 Cómo Ejecutar el Proyecto
- Configuración de la Base de Datos:

- Asegúrate de tener MySQL instalado y en ejecución.

- Crea una base de datos.

- Configura las credenciales de la base de datos en el archivo config.properties:

properties
````bash
db.url=jdbc:mysql://localhost:3306/elnombre_de_la_base_de_datos
db.user=tu_usuario
db.password=tu_contraseña
````
### Compilación y Ejecución:

- Clona el repositorio.

- La aplicación presenta un menú interactivo en la consola para gestionar clientes y productos.

- Puedes registrar, buscar, listar, actualizar y eliminar clientes y productos.

### 📋 Funcionalidades Principales
- Gestión de Clientes:

- Registrar un nuevo cliente.

- Buscar un cliente por ID.

- Listar todos los clientes.

- Actualizar la información de un cliente.

- Eliminar un cliente.

- Gestión de Productos:

- Registrar un nuevo producto.

- Buscar un producto por ID.

- Listar todos los productos.

- Actualizar la información de un producto.

- Eliminar un producto.
