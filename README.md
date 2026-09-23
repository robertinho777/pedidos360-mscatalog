# Pedidos360 - Microservicio de Catálogo (Catalog)

Microservicio en **Spring Boot 3** responsable de la gestión de productos, inventario y control de stock del sistema **Pedidos360**.

---

## 🏛️ Funcionalidades

- **CRUD de Productos**: Alta, baja, modificación y consulta de artículos.
- **Control de Stock Atómico**: Endpoint `/api/catalog/products/{id}/stock/decrease` ejecutado transaccionalmente cuando un pedido es aceptado.
- **Persistencia en Oracle Autonomous Database (ATP)**: Conexión segura mutual TLS mediante Oracle Wallet (`ojdbc11`, `oraclepki`, `osdt_core`, `osdt_cert`).
- **Respaldo Local (H2)**: Base de datos en memoria para pruebas rápidas sin conectividad externa.

---

## ⚙️ Variables de Entorno

| Variable | Descripción | Valor por Defecto |
| :--- | :--- | :--- |
| `PORT` | Puerto de escucha | `8082` |
| `DB_TNS_NAME` | Nombre del servicio TNS en la Wallet | `pedidos360orders_tp` |
| `TNS_ADMIN` | Ruta a la carpeta que contiene la Wallet | `C:/Wallet360/Wallet_pedidos360orders` |
| `DB_USER` | Usuario de base de datos Oracle | `PEDIDOS_CATALOG` |
| `DB_PASSWORD` | Contraseña del usuario Oracle | `17deAcuario#07` |

---

## 🚀 Compilación y Ejecución

```bash
# Compilar JAR
./mvnw clean package -DskipTests

# Ejecutar con Oracle Wallet en Linux / AWS
java -jar target/ms-pedidos360-catalog-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url="jdbc:oracle:thin:@pedidos360orders_tp?TNS_ADMIN=/home/ec2-user/wallet"
```
