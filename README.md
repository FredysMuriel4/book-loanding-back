# Backend - Oracle Database | Java 1.8

Este proyecto es un backend desarrollado en Java 1.8, usando Oracle Database como gestor de base de datos.

## Requisitos Previos

- Java JDK 1.8
- Oracle Database 19c o similar
- Maven 3.x
- Oracle SQL Developer (opcional, para gestión de BD)
- Driver JDBC para Oracle

## Configuración Inicial

### 1. Instalar Oracle Database

- Descarga Oracle Database desde: https://www.oracle.com/database/technologies/
- Sigue las instrucciones de instalación según tu sistema operativo.
- Verifica que el servicio de base de datos esté corriendo.

### 2. Crear Usuario en Oracle

Ejecuta en Oracle SQL Developer o cualquier cliente SQL:

```sql
CREATE USER mi_usuario IDENTIFIED BY mi_contraseña;
GRANT CONNECT, RESOURCE TO mi_usuario;
```
Cambia mi_usuario y mi_contraseña por los valores que desees.

### 3. Instalar el Driver JDBC

- Descarga el driver desde: https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html
- Agrega la dependencia en pom.xml:

  <dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc8</artifactId>
    <version>19.8.0.0</version>
  </dependency>

### 4. Configura la conexión en tu application.properties:

```
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=admin_sb
spring.datasource.password=admin
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```
