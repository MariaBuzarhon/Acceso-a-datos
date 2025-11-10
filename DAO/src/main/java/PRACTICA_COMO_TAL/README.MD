

### **Persona 1: Gestión de Libros y Autores**

**Responsabilidades:**

1. **Modelo y CRUD de Libro**

   * Crear clase `Libro` con atributos y métodos.
   * Implementar DAO (`LibroDAO`) con operaciones CRUD usando JDBC.
2. **Modelo y CRUD de Autor**

   * Crear clase `Autor` con atributos y métodos.
   * Implementar DAO (`AutorDAO`) con operaciones CRUD usando JDBC.
3. **Relación Libro-Autor**

   * Implementar la tabla intermedia `LibroAutor` y su DAO (`LibroAutorDAO`).
   * Métodos para asociar un libro con uno o varios autores y viceversa.

**Opcional:** Crear funciones de búsqueda de libros por autor o de autores por libro.

---

### **Persona 2: Gestión de Usuarios y Préstamos**

**Responsabilidades:**

1. **Modelo y CRUD de Usuario**

   * Crear clase `Usuario` con atributos y métodos.
   * Implementar DAO (`UsuarioDAO`) con operaciones CRUD usando JDBC.
2. **Modelo y CRUD de Préstamo**

   * Crear clase `Prestamo` con atributos y métodos.
   * Implementar DAO (`PrestamoDAO`) con operaciones CRUD usando JDBC.
3. **Relaciones de préstamo**

   * Gestionar la asociación entre `Prestamo`, `Usuario` y `Libro`.
   * Métodos para prestar libros, devolver libros y consultar préstamos activos por usuario.

**Opcional:** Validar disponibilidad de libros antes de hacer un préstamo.

---

### **Tareas compartidas**

* Configuración de la base de datos y scripts de creación de tablas.
* Clases utilitarias para conexión JDBC (`ConexionDB`).
* Pruebas unitarias o de integración para verificar que todo funciona.
* Documentación del proyecto y UML de clases/relaciones.


