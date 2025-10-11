-- =============================================================
--  SCRIPT BASE DE DATOS SIGIFAR
--  Autor: Cristian Mariscurrena
--  Fecha: 2025-0-10
-- =============================================================

-- =============================================================
--  LIMPIEZA PREVIA
-- =============================================================
DROP DATABASE IF EXISTS SIGIFAR;
CREATE DATABASE SIGIFAR CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE SIGIFAR;

-- =============================================================
--  CONFIGURACIONES TEMPORALES
-- =============================================================
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================================
--  TABLAS
-- =============================================================

-- -------------------------------------------------------------
--  Tabla: usuarios
-- -------------------------------------------------------------
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    clave_unica INT UNIQUE NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    correo VARCHAR(150) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('normal','superadmin') NOT NULL DEFAULT 'normal',
    imagen LONGBLOB NOT NULL
);

-- -------------------------------------------------------------
--  Tabla: ubicaciones
-- -------------------------------------------------------------
CREATE TABLE ubicaciones (
    id_ubicacion INT AUTO_INCREMENT PRIMARY KEY,
    almacen VARCHAR(100) NOT NULL,
    pasillo VARCHAR(50) NOT NULL,
    estanteria VARCHAR(50) NOT NULL,
    nivel VARCHAR(50) NOT NULL,
    posicion VARCHAR(50) NOT NULL,
    indicaciones_especiales TEXT
);

-- -------------------------------------------------------------
--  Tabla: proveedores
-- -------------------------------------------------------------
CREATE TABLE proveedores (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    contacto VARCHAR(150),
    telefono VARCHAR(50),
    correo VARCHAR(150)
);

-- -------------------------------------------------------------
--  Tabla: productos
-- -------------------------------------------------------------
CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    clave_producto VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    marca VARCHAR(100),
    presentacion VARCHAR(100),
    numero_lote VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL DEFAULT 0,
    fecha_caducidad DATE NOT NULL,
    id_proveedor INT,
    id_ubicacion INT,
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor),
    FOREIGN KEY (id_ubicacion) REFERENCES ubicaciones(id_ubicacion)
);

-- -------------------------------------------------------------
--  Tabla: entradas
-- -------------------------------------------------------------
CREATE TABLE entradas (
    id_entrada INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    numero_lote INT NOT NULL,
    cantidad INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- -------------------------------------------------------------
--  Tabla: transporte
-- -------------------------------------------------------------
CREATE TABLE transporte (
    id_transporte INT AUTO_INCREMENT PRIMARY KEY,
    numero_unidad VARCHAR(50) NOT NULL,
    transportista VARCHAR(150) NOT NULL
);

-- -------------------------------------------------------------
--  Tabla: salidas
-- -------------------------------------------------------------
CREATE TABLE salidas (
    id_salida INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    numero_lote INT NOT NULL,
    cantidad INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    destino VARCHAR(200) NOT NULL,
    id_usuario INT,
    id_transporte INT,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_transporte) REFERENCES transporte(id_transporte)
);

-- =============================================================
--  DATOS DE PRUEBA
-- =============================================================

-- -------------------------------------------------------------
--  Limpieza de tablas antes de insertar (por si existen datos)
-- -------------------------------------------------------------
DELETE FROM salidas;
DELETE FROM entradas;
DELETE FROM productos;
DELETE FROM proveedores;
DELETE FROM ubicaciones;
DELETE FROM transporte;
DELETE FROM usuarios;

-- -------------------------------------------------------------
--  Usuarios
-- -------------------------------------------------------------
INSERT INTO usuarios (clave_unica, nombres, apellidos, correo, contrasena, rol, imagen)
VALUES
(1001, 'Juan', 'Pérez López', 'juan.perez@sigifar.com', '12345', 'superadmin', ''),
(1002, 'María', 'Ramírez Soto', 'maria.ramirez@sigifar.com', 'abcde', 'normal', '');

-- -------------------------------------------------------------
--  Ubicaciones
-- -------------------------------------------------------------
INSERT INTO ubicaciones (almacen, pasillo, estanteria, nivel, posicion, indicaciones_especiales)
VALUES
('Central', 'A1', 'E1', 'N1', 'P1', 'Productos refrigerados'),
('Central', 'A2', 'E2', 'N2', 'P2', 'Evitar humedad');

-- -------------------------------------------------------------
--  Proveedores
-- -------------------------------------------------------------
INSERT INTO proveedores (nombre, contacto, telefono, correo)
VALUES
('Laboratorios Genfar', 'Carlos Gómez', '555-1122', 'cgomez@genfar.com'),
('Distribuidora Medifarma', 'Ana Torres', '555-3344', 'ana@medifarma.com');

-- -------------------------------------------------------------
--  Productos
-- -------------------------------------------------------------
INSERT INTO productos (clave_producto, nombre, marca, presentacion, numero_lote, cantidad, fecha_caducidad, id_proveedor, id_ubicacion)
VALUES
('PROD-001', 'Paracetamol 500mg', 'Genfar', 'Caja 10 tabletas', 'L-001', 100, '2026-05-15', 1, 1),
('PROD-002', 'Amoxicilina 875mg', 'Medifarma', 'Caja 14 tabletas', 'L-002', 50, '2025-12-20', 2, 2);

-- -------------------------------------------------------------
--  Entradas
-- -------------------------------------------------------------
INSERT INTO entradas (id_producto, numero_lote, cantidad, id_usuario)
VALUES
(1, 1, 100, 1),
(2, 2, 50, 2);

-- -------------------------------------------------------------
--  Transporte
-- -------------------------------------------------------------
INSERT INTO transporte (numero_unidad, transportista)
VALUES
('T-001', 'TransMedic S.A.'),
('T-002', 'Logística Farma');

-- -------------------------------------------------------------
--  Salidas
-- -------------------------------------------------------------
INSERT INTO salidas (id_producto, numero_lote, cantidad, destino, id_usuario, id_transporte)
VALUES
(1, 1, 20, 'Clínica San José', 1, 1),
(2, 2, 10, 'Hospital Central', 2, 2);

-- =============================================================
--  REACTIVAR RESTRICCIONES
-- =============================================================
SET FOREIGN_KEY_CHECKS = 1;
SET SQL_SAFE_UPDATES = 1;

-- =============================================================
--  FIN DEL SCRIPT
-- =============================================================
SELECT 'Base de datos SIGIFAR creada y poblada exitosamente.' AS mensaje;
