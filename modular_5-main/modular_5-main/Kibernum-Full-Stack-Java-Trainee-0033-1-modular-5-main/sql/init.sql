CREATE TABLE `vuelo` (
  `id_vuelo` INT PRIMARY KEY AUTO_INCREMENT,
  `ciudad_despegue` VARCHAR(25) NOT NULL,
  `ciudad_aterrizaje` VARCHAR(25) NOT NULL,
  `fecha_salida` DATE NOT NULL,
  `fecha_llegada` DATE NOT NULL,
  `hora_abordaje` TIME NOT NULL,
  `hora_salida` TIME NOT NULL,
  `hora_llegada` TIME NOT NULL
);

CREATE TABLE `cliente` (
  `id_cliente` INT PRIMARY KEY AUTO_INCREMENT,
  `nombre` VARCHAR(25) NOT NULL,
  `apellido` VARCHAR(25) NOT NULL,
  `rut` VARCHAR(10) UNIQUE NOT NULL
);

CREATE TABLE `reserva` (
  `id_reserva` INT PRIMARY KEY AUTO_INCREMENT,
  `id_cliente` INT NOT NULL,
  `id_vuelo` INT NOT NULL,
  `numero_asiento` INT NOT NULL,
  `estado_vuelo` ENUM ('A TIEMPO', 'ATRASADO', 'CANCELADO') NOT NULL,
  `seccion_cliente` ENUM ('ECONOMICA', 'EJECUTIVO', 'VIP') NOT NULL,
    CONSTRAINT uq_vuelo_asiento UNIQUE (id_vuelo, numero_asiento)
);

ALTER TABLE `reserva` ADD FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`);

ALTER TABLE `reserva` ADD FOREIGN KEY (`id_vuelo`) REFERENCES `vuelo` (`id_vuelo`);


INSERT INTO vuelo (ciudad_despegue, ciudad_aterrizaje, fecha_salida, fecha_llegada, hora_abordaje, hora_salida, hora_llegada) VALUES
('Santiago', 'Lima', '2026-04-01', '2026-04-01', '07:30:00', '08:00:00', '10:00:00'),
('Santiago', 'Buenos Aires', '2026-04-02', '2026-04-02', '09:00:00', '09:30:00', '11:30:00'),
('Santiago', 'Bogota', '2026-04-03', '2026-04-03', '06:30:00', '07:00:00', '12:00:00'),
('Santiago', 'Madrid', '2026-04-04', '2026-04-05', '21:30:00', '22:00:00', '14:00:00'),
('Santiago', 'Miami', '2026-04-05', '2026-04-05', '10:00:00', '10:30:00', '18:00:00'),
('Lima', 'Santiago', '2026-04-06', '2026-04-06', '13:30:00', '14:00:00', '18:00:00'),
('Buenos Aires', 'Santiago', '2026-04-07', '2026-04-07', '15:00:00', '15:30:00', '17:30:00'),
('Bogota', 'Santiago', '2026-04-08', '2026-04-08', '08:00:00', '08:30:00', '14:00:00'),
('Madrid', 'Santiago', '2026-04-09', '2026-04-10', '19:30:00', '20:00:00', '09:00:00'),
('Miami', 'Santiago', '2026-04-10', '2026-04-10', '11:00:00', '11:30:00', '20:00:00');