# Franchise-APP

Servicio para gestión de franquicias


## Requisitos

- Java 21+
- Gradle
- MySQL 8+
- Docker (opcional, para levantar la base de datos rápidamente)


## Configuración de la Base de Datos

```sql
CREATE DATABASE franchise_management;

USE franchise_management;

CREATE TABLE franchises (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL
);

CREATE TABLE branches (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    ID_FRANCHISE BIGINT NOT NULL,
    CONSTRAINT fk_branches_franchises
        FOREIGN KEY (ID_FRANCHISE)
        REFERENCES franchises(ID)
        ON DELETE CASCADE
);

CREATE TABLE products (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    STOCK INT NOT NULL,
    ID_BRANCH BIGINT NOT NULL,
    CONSTRAINT fk_products_branch
        FOREIGN KEY (ID_BRANCH)
        REFERENCES branches(ID)
        ON DELETE CASCADE
);
```


## Levantar el proyecto localmente

- Reemplazar el valor de las siguientes propiedades del application.yml por las de tu entorno local:
infrastructure.driven-adapters.my-sql.username
infrastructure.driven-adapters.my-sql.password

- Abrir la consola e ir hasta la ruta dónde clonaste el proyecto, una vez allí ejecuta el servicio de esta forma:

WINDOWS
gradlew.bat bootRun

UBUNTU
./gradlew bootRun


## Endpoints del servicio

- Endpoint para guardar una franquicia

```bash
curl --location 'http://localhost:8080/api/v1/franchise' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Buffalo Chicken"
}'
```

- Endpoint para actualizar el nombre de una franquicia

```bash
curl --location --request PUT 'http://localhost:8080/api/v1/franchise' \
--header 'Content-Type: application/json' \
--data '{
    "id": 1,
    "name": "Veggie Delite"
}'
```

- Endpoint para crear una sucursal

```bash
curl --location 'http://localhost:8080/api/v1/branch' \
--header 'Content-Type: application/json' \
--data '{
    "name": "CC Puerta del Norte",
    "idFranchise": 1
}'
```

- Endpoint para actualizar el nombre de una sucursal

```bash
curl --location --request PUT 'http://localhost:8080/api/v1/branch' \
--header 'Content-Type: application/json' \
--data '{
    "id": 1,
    "name": "CC Puerta del Sur"
}'
```

- Endpoint para guardar un nuevo producto

```bash
curl --location 'http://localhost:8080/api/v1/product' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Italian B.M.T.",
    "stock": 17,
    "idBranch": 1
}'
```

- Endpoint para actualizar el nombre y el stock de un producto

```bash
curl --location --request PUT 'http://localhost:8080/api/v1/product' \
--header 'Content-Type: application/json' \
--data '{
    "id": 1,
    "name": "Tuna",
    "stock": 9
}'
```

- Endpoint para eliminar un producto

```bash
curl --location --globoff --request DELETE 'http://localhost:8080/api/v1/product/{id}'
```

- Endpoint para obtener el producto con más stock de cada sucursal de una franquicia

```bash
curl --location --globoff 'http://localhost:8080/api/v1/product/{id}'
```