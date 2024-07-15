# Literalura

## Descripción del Proyecto

Literalura es una aplicación basada en Spring Boot que permite a los usuarios buscar libros a través de la API de Gutendex, almacenarlos en una base de datos y realizar diversas consultas sobre los libros y sus autores. El objetivo principal del proyecto es facilitar el acceso a la información de libros y autores, permitiendo búsquedas por título, idioma, y otros criterios.

## Funcionalidades

- **Buscar Libros por Título**: Los usuarios pueden buscar libros ingresando el título o parte del título.
- **Listar Libros por Idioma**: Permite listar todos los libros que están en un idioma específico.
- **Listar Autores Vivos en un Año Determinado**: Muestra los autores que estaban vivos en un año ingresado por el usuario.
- **Mostrar Detalles del Libro**: Almacena y muestra detalles de los libros, incluyendo el título, autor, idioma, y número de descargas.

## Cómo Usarlo

1. **Clonar el Repositorio**:
    ```bash
    git clone https://github.com/tu-usuario/literalura.git
    cd literalura
    ```

2. **Configurar la Base de Datos**:
   - Asegúrate de tener PostgreSQL instalado y en funcionamiento.
   - Configura tu archivo `application.properties` en `src/main/resources` con las credenciales de tu base de datos PostgreSQL:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

3. **Compilar y Ejecutar la Aplicación**:
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Interacción con la Aplicación**:
   - La aplicación se ejecuta en la línea de comandos y ofrece un menú para realizar las diferentes funcionalidades descritas.
   - Sigue las instrucciones en pantalla para buscar libros, listar libros por idioma o autores vivos en un año específico.

5. **Ejemplos de uso**:
   Al iniciar la applicacion te encontraras con un menu:
   - La primera opcion nos permite traer libros de gutendex a nuestra base de datos usando una o mas palabras y encontrando el aproximado inmediato
![image](https://github.com/user-attachments/assets/a8c3fea3-30e9-40f9-aa5f-e3e4798713ed)
  - La segunda opcion nos brinda un listado de los libros en la base de datos
![image](https://github.com/user-attachments/assets/f73d3297-df42-4418-80a2-4b0175275036)
  - La tercera opcion nos lista los autores en la base de datos con su respectiva informacion
    
![image](https://github.com/user-attachments/assets/399210b1-8c1c-4fab-a85d-26aec58d872b)
  - La cuarta opcion nos proporciona la necesidad de una segunda entrada de datos con la fecha en la que quieres encontrar que autores de la base de datos aun estan con vida, se ingresa en formato año
    ![image](https://github.com/user-attachments/assets/df9687e9-b140-459c-b22c-5f925e673aac)
  - Y por ultimo la quinta opcion, al igual que la anterior nos mostrara la necesidad de una segunda entrada, esta vez acompañada de un menu con los años disponibles en la informacion de la base de datos, posteriormente listara los libros existentes en dicho idioma
    ![image](https://github.com/user-attachments/assets/97bae7b0-15ef-4619-9fc7-1d6560d5c114)





## Ayuda

Si tienes alguna pregunta o necesitas ayuda con el proyecto, puedes abrir un "issue" en el repositorio de GitHub o contactarnos a través de los siguientes medios:

- **Repositorio de GitHub**: [https://github.com/tu-usuario/literalura/issues](https://github.com/tu-usuario/literalura/issues)
- **Correo Electrónico**: [sirvelasque@gmail.com](mailto:sirvelasque@gmail.com)

## Autores

- **Sirvelasque** - [GitHub](https://github.com/sirvelasque)

Contribuciones, problemas y solicitudes de características son bienvenidos. ¡Siéntete libre de mejorar el proyecto y enviarnos tus comentarios!
