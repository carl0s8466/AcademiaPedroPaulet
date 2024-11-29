-- Insertar datos en academia_cargo
INSERT INTO `academia_cargo` (`id_cargo`, `nombre_cargo`) VALUES
(1, 'Profesor'),
(2, 'Asistente'),
(3, 'Coordinador'),
(4, 'Recepcionista'),
(5, 'Mantenimiento'),
(6, 'Bibliotecario'),
(7, 'Administrador'),
(8, 'Supervisor'),
(9, 'Contador'),
(10, 'Técnico de TI'),
(11, 'Psicólogo Escolar'),
(12, 'Tutor Académico'),
(13, 'Director'),
(14, 'Secretario'),
(15, 'Jefe de Área');


-- Insertar datos en academia_estudiante
INSERT INTO `academia_estudiante` (`id_estudiante`, `apellido_materno`, `apellido_paterno`, `dni`, `medio_interes`, `nombre_completo`) VALUES
(1, 'García', 'Pérez', '12345678', 'Redes Sociales', 'Carlos García'),
(2, 'Flores', 'Lopez', '87654321', 'Amigos', 'Ana Flores'),
(3, 'Castro', 'Mejía', '45612378', 'Publicidad', 'Juan Castro'),
(4, 'Hernández', 'Torres', '78965412', 'Familiares', 'Sofía Hernández'),
(5, 'Rojas', 'Gómez', '32145678', 'Correo Electrónico', 'Diego Rojas'),
(6, 'Ramírez', 'Martínez', '65478932', 'Eventos Educativos', 'Laura Ramírez'),
(7, 'López', 'Silva', '14725836', 'Web de la Academia', 'Patricia López'),
(8, 'Mendoza', 'Fernández', '96385274', 'Ferias Educativas', 'Luis Mendoza'),
(9, 'Vargas', 'Ríos', '25896314', 'Anuncios en Radio', 'Andrea Vargas'),
(10, 'Cruz', 'Velásquez', '85274196', 'Google Ads', 'Hugo Cruz'),
(11, 'Pérez', 'Salas', '15975348', 'Recomendaciones de Profesores', 'Carla Pérez'),
(12, 'Silva', 'Luna', '75315962', 'Panfletos', 'Jorge Silva'),
(13, 'Fernández', 'Morales', '95175328', 'Instagram Ads', 'Elena Fernández'),
(14, 'Mejía', 'Chávez', '35795162', 'TikTok Ads', 'Mario Mejía'),
(15, 'Gómez', 'Reyes', '74185296', 'Carteles en Universidades', 'Lucía Gómez');


-- Insertar datos en academia_insidencia
INSERT INTO `academia_insidencia` (`id_insidencia`, `castigo`, `descripcion`, `estudiante`) VALUES
(1, 'Suspensión', 'Comportamiento inapropiado', 'Carlos García'),
(2, 'Advertencia', 'Llegadas tardías', 'Ana Flores'),
(3, 'Suspensión', 'Agresión verbal a un compañero', 'Luis Pérez'),
(4, 'Advertencia', 'Falta de tarea recurrente', 'María López'),
(5, 'Expulsión', 'Robo dentro de la academia', 'Juan Torres'),
(6, 'Suspensión', 'Uso de lenguaje ofensivo', 'Sofía Hernández'),
(7, 'Advertencia', 'Mal uso de las instalaciones', 'Diego Vargas'),
(8, 'Suspensión', 'Falsificación de documentos', 'Laura Gómez'),
(9, 'Advertencia', 'Desobediencia en clase', 'Pedro Martínez'),
(10, 'Suspensión', 'Pelea física con un compañero', 'Elena Rojas'),
(11, 'Advertencia', 'Uso indebido de dispositivos electrónicos', 'Carla Silva'),
(12, 'Advertencia', 'Inasistencia injustificada', 'Hugo Ramírez'),
(13, 'Suspensión', 'Consumo de sustancias prohibidas', 'Jorge Castro'),
(14, 'Advertencia', 'Interrupciones constantes en clase', 'Patricia Mendoza'),
(15, 'Suspensión', 'Amenazas a un docente', 'Andrea Fernández');


-- Insertar datos en academia_mediointeres
INSERT INTO `academia_mediointeres` (`id_medio_interes`, `nombre_medio`) VALUES
(1, 'Redes Sociales'),
(2, 'Amigos'),
(3, 'Publicidad'),
(4, 'Familiares'),
(5, 'Panfletos'),
(6, 'Correo Electrónico'),
(7, 'Anuncios en Radio'),
(8, 'Televisión'),
(9, 'Eventos Educativos'),
(10, 'Web de la Academia'),
(11, 'Recomendaciones de Profesores'),
(12, 'Ferias Educativas'),
(13, 'Carteles en Universidades'),
(14, 'Google Ads'),
(15, 'Instagram Ads'),
(16, 'TikTok Ads');


-- Insertar datos en academia_trabajador
INSERT INTO `academia_trabajador` (`id_trabajador`, `apellido`, `cargo`, `contrasena`, `nombre`) VALUES
(1, 'Ruiz', 'Profesor', '12345', 'Mario'),
(2, 'Salas', 'Asistente', 'abcde', 'Lucía'),
(3, 'Córdova', 'Coordinador', '98765', 'Pedro'),
(4, 'López', 'Profesor', '54321', 'Ana'),
(5, 'Pérez', 'Recepcionista', 'abcd1234', 'Carla'),
(6, 'Gómez', 'Profesor', 'pass123', 'Juan'),
(7, 'Martínez', 'Asistente', 'qwerty', 'Sofía'),
(8, 'Vargas', 'Coordinador', 'admin456', 'Carlos'),
(9, 'Fernández', 'Mantenimiento', 'mante123', 'Luis'),
(10, 'Torres', 'Profesor', 'teach789', 'Andrea'),
(11, 'Castro', 'Asistente', 'helpme', 'Diana'),
(12, 'Rojas', 'Bibliotecario', 'book123', 'Hugo'),
(13, 'Mendoza', 'Profesor', 'prof456', 'Elena'),
(14, 'Silva', 'Coordinador', 'manage123', 'Jorge'),
(15, 'Ramírez', 'Asistente', 'simple123', 'Patricia');



-- Insertar datos en upeu_accesos con íconos de Material Design
INSERT INTO `upeu_accesos` (`id_acceso`, `icono`, `nombre`, `url`) VALUES
(1, 'assignment_ind', 'Cargos', '/pages/cargo'), -- Representa "Cargos" relacionados con personal.
(2, 'school', 'Estudiantes', '/pages/estudiante'), -- Relacionado con estudiantes y educación.
(3, 'report_problem', 'Insidencias', '/pages/insidencia'), -- Para problemas o incidentes.
(4, 'public', 'Medios De Interes', '/pages/mediointeres'), -- Relacionado con medios de comunicación o interés.
(5, 'work', 'Trabajadores', '/pages/trabajador'); -- Relacionado con trabajadores o empleo.

-- Insertar datos en upeu_roles
INSERT INTO `upeu_roles` (`id_rol`, `descripcion`, `nombre`) VALUES
(1, 'Administrador del sistema', 'ADMIN'),
(2, 'Base de datos', 'DBA'),
(3, 'Usuario regular', 'USER');
-- Insertar datos en upeu_acceso_rol
INSERT INTO `upeu_acceso_rol` (`rol_id`, `acceso_id`) VALUES
(1, 1), -- ADMIN: Acceso a "Cargos"
(1, 2), -- ADMIN: Acceso a "Estudiantes"
(1, 3), -- ADMIN: Acceso a "Insidencias"
(1, 4), -- ADMIN: Acceso a "Medios De Interes"
(1, 5), -- ADMIN: Acceso a "Trabajadores"
(2, 1), -- DBA: Acceso a "Cargos"
(2, 3), -- DBA: Acceso a "Insidencias"
(2, 5), -- DBA: Acceso a "Trabajadores"
(3, 1), -- USER: Acceso a "Cargos"
(3, 3), -- USER: Acceso a "Insidencias"
(3, 4), -- USER: Acceso a "Medios De Interes"
(3, 5); -- USER: Acceso a "Trabajadores"




