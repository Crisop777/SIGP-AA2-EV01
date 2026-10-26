CREATE DATABASE IF NOT EXISTS sigp_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE sigp_db;

CREATE TABLE IF NOT EXISTS tareas (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT,
    estado VARCHAR(30) NOT NULL DEFAULT 'Pendiente',
    PRIMARY KEY (id)
) ENGINE=InnoDB;
