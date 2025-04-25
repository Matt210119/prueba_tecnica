-- CREATE DATABASE banco_db;
-- Database: banco_db

DROP DATABASE IF EXISTS banco_db;

CREATE DATABASE banco_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'es-ES'
    LC_CTYPE = 'es-ES'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Crear las tablas
CREATE TABLE clientes (
    cliente_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    edad INT NOT NULL,
    email VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cuentas (
    cuenta_id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    numero_cuenta VARCHAR(50) NOT NULL UNIQUE,
    fecha_creacion DATE NOT NULL,
    saldo_actual DECIMAL(10, 2) DEFAULT 0.00,
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id)
);

CREATE TABLE categorias_movimiento (
    categoria_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(100)
);

CREATE TABLE tipos_movimiento (
    tipo_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE transacciones (
    transaccion_id SERIAL PRIMARY KEY,
    cuenta_ordenante_id INT NOT NULL,
    cuenta_beneficiario_id INT NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    fecha_proceso TIMESTAMP NOT NULL,
    estado VARCHAR(50) DEFAULT 'COMPLETADA',
    referencia VARCHAR(100),
    FOREIGN KEY (cuenta_ordenante_id) REFERENCES cuentas(cuenta_id),
    FOREIGN KEY (cuenta_beneficiario_id) REFERENCES cuentas(cuenta_id)
);

CREATE TABLE movimientos (
    movimiento_id SERIAL PRIMARY KEY,
    cuenta_id INT NOT NULL,
    tipo_id INT NOT NULL,
    categoria_id INT NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    fecha_movimiento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descripcion VARCHAR(100),
    transaccion_id INT,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(cuenta_id),
    FOREIGN KEY (tipo_id) REFERENCES tipos_movimiento(tipo_id),
    FOREIGN KEY (categoria_id) REFERENCES categorias_movimiento(categoria_id),
    FOREIGN KEY (transaccion_id) REFERENCES transacciones(transaccion_id)
);

-- Insertar registros en cada tabla

-- 1. Insertar clientes
INSERT INTO clientes (nombre, apellido, edad, email) VALUES
('Jaimito', 'Pérez', 38, 'jaimito@example.com'),
('Luis', 'Pérez', 42, 'luisperez@example.com'),
('Galo', 'Mendoza', 35, 'galomendoza@hotmail.com'),
('Ana', 'Gómez', 29, 'anagomez@example.com');

-- 2. Insertar tipos de movimiento
INSERT INTO tipos_movimiento (nombre) VALUES
('Crédito'),
('Débito'),
('Transferencia Interna'),
('Ajuste');

-- 3. Insertar categorías de movimiento
INSERT INTO categorias_movimiento (nombre, descripcion) VALUES
('Salario', 'Pago de nómina mensual'),
('Compra Online', 'Compras realizadas en internet'),
('Transferencia', 'Transferencia a otras cuentas'),
('Ahorro', 'Depósito para ahorro personal');

-- 4. Insertar cuentas (una para cada cliente)
INSERT INTO cuentas (cliente_id, numero_cuenta, fecha_creacion, saldo_actual) VALUES
(1, '98583123000', '2020-10-08', 1558.00),
(2, '13289456000', '2019-05-15', 2500.00),
(3, '13288789000', '2021-02-22', 1200.00),
(4, '45678012000', '2022-01-10', 3400.00);

-- 5. Insertar transacciones
INSERT INTO transacciones (cuenta_ordenante_id, cuenta_beneficiario_id, valor, fecha_proceso, estado, referencia) VALUES
(2, 3, 100.00, '2025-04-23 10:30:00', 'COMPLETADA', 'Transferencia entre cuentas'),
(1, 4, 50.00, '2025-04-20 15:45:00', 'COMPLETADA', 'Pago de deuda'),
(4, 2, 200.00, '2025-04-22 09:15:00', 'COMPLETADA', 'Devolución préstamo'),
(3, 1, 75.00, '2025-04-24 14:20:00', 'COMPLETADA', 'Pago de servicio');

-- 6. Insertar movimientos
-- Movimientos para la transacción 1 (Luis a Galo)
INSERT INTO movimientos (cuenta_id, tipo_id, categoria_id, monto, descripcion, transaccion_id, fecha_movimiento) VALUES
(2, 2, 3, 100.00, 'Transferencia a Galo Mendoza', 1, '2025-04-23 10:30:00'),
(3, 1, 3, 100.00, 'Transferencia recibida de Luis Pérez', 1, '2025-04-23 10:30:00');

-- Movimientos para la transacción 2 (Jaimito a Ana)
INSERT INTO movimientos (cuenta_id, tipo_id, categoria_id, monto, descripcion, transaccion_id, fecha_movimiento) VALUES
(1, 2, 3, 50.00, 'Pago de deuda a Ana Gómez', 2, '2025-04-20 15:45:00'),
(4, 1, 3, 50.00, 'Transferencia recibida de Jaimito Pérez', 2, '2025-04-20 15:45:00');

-- Movimientos para la transacción 3 (Ana a Luis)
INSERT INTO movimientos (cuenta_id, tipo_id, categoria_id, monto, descripcion, transaccion_id, fecha_movimiento) VALUES
(4, 2, 3, 200.00, 'Devolución préstamo a Luis Pérez', 3, '2025-04-22 09:15:00'),
(2, 1, 3, 200.00, 'Transferencia recibida de Ana Gómez', 3, '2025-04-22 09:15:00');

-- Movimientos para la transacción 4 (Galo a Jaimito)
INSERT INTO movimientos (cuenta_id, tipo_id, categoria_id, monto, descripcion, transaccion_id, fecha_movimiento) VALUES
(3, 2, 3, 75.00, 'Pago de servicio a Jaimito Pérez', 4, '2025-04-24 14:20:00'),
(1, 1, 3, 75.00, 'Transferencia recibida de Galo Mendoza', 4, '2025-04-24 14:20:00');

-- Movimientos adicionales (no relacionados con transacciones)
INSERT INTO movimientos (cuenta_id, tipo_id, categoria_id, monto, descripcion, fecha_movimiento) VALUES
(1, 1, 1, 1500.00, 'Depósito de salario', '2025-04-15 09:00:00'),
(2, 1, 1, 2400.00, 'Depósito de salario', '2025-04-15 09:15:00'),
(3, 2, 2, 25.00, 'Compra en Amazon', '2025-04-18 16:30:00'),
(4, 1, 4, 150.00, 'Depósito para ahorro', '2025-04-19 11:45:00');

-- Actualizar los saldos de las cuentas según los movimientos
UPDATE cuentas
SET saldo_actual = (
    SELECT COALESCE(SUM(
        CASE 
            WHEN m.tipo_id = 1 THEN m.monto  -- Crédito suma
            WHEN m.tipo_id = 2 THEN -m.monto -- Débito resta
            ELSE 0
        END
    ), 0)
    FROM movimientos m
    WHERE m.cuenta_id = cuentas.cuenta_id
)
WHERE cuenta_id IN (1, 2, 3, 4);

--RESUMEN TRASACCION

SELECT 
    co.nombre || ' ' || co.apellido AS nombre_ordenante,
    cb.nombre || ' ' || cb.apellido AS nombre_beneficiario,
    cb.email AS email_beneficiario,
    TO_CHAR(t.fecha_proceso, 'DD/MM/YYYY') AS fecha_proceso,
    cuo.numero_cuenta AS cuenta_ordenante,
    cub.numero_cuenta AS cuenta_beneficiario,
    t.valor AS valor_transaccion
FROM 
    transacciones t
JOIN 
    cuentas cuo ON t.cuenta_ordenante_id = cuo.cuenta_id
JOIN 
    cuentas cub ON t.cuenta_beneficiario_id = cub.cuenta_id
JOIN 
    clientes co ON cuo.cliente_id = co.cliente_id
JOIN 
    clientes cb ON cub.cliente_id = cb.cliente_id
WHERE 
    co.nombre = 'Luis' AND co.apellido = 'Pérez'
    AND cb.nombre = 'Galo' AND cb.apellido = 'Mendoza'
    AND TO_CHAR(t.fecha_proceso, 'DD/MM/YYYY') = '23/04/2025';