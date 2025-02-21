INSERT INTO roles(rol) VALUES ('Administrador');
INSERT INTO roles(rol) VALUES ('Jugador');

INSERT INTO personas(altura, dorsal, edad, enabled, peso, fecha_creacion, fecha_modificacion, id_equipo, rol_id, contrasena, correo, nombre) VALUES (NULL, NULL, NULL, 1, NULL, NOW(),NOW(), NULL, (SELECT id_rol FROM roles WHERE rol="Administrador"), "$2a$10$3DAylmUTxVeZeyqz56wrRO0pS06tAl7ryUBiU/fCrUKJ/X9srnAZW", "juan@gmail.com", "Juan Mateus Tovar");


INSERT INTO secret_keys(fecha_creacion, is_valid, secret_key) VALUES (NOW(), 1, "y5HxxnPzNvhgh0n31ML4HnNWHWZr5iwoDCCcuZ77M+vHElaw26H+FmWcVlSmqt8g9DyXgixFrVJ5onDITPrwT/RK5eGR6Gwk8NMPaXlqwwAN08zkMHqu7ya3dHJ7E+13T+SI3dEr+0LKEcr19Uddx8a/Tg015eD15xhF32sCVl0OyS0S9wVU2MAJkfk5r6ICH1oi3D1MImFBGNkpVBMP3fh1ZY6Rw9jpUUtKubIwHBMzpuervcS1AQDKJnto7Sa6fpCSloAg31EXHe94ZmOnPbMOoCP4b/rGxxtyUvMHrXVSjrr/7my86P+7204BwMnnSupTZGNlqCb88SYOIa7PKj2gjP8+dqExV1+FkAClxIe6dHuB/jYgFXxhVS75mSMdhH1muk6ndG2QI9zBlEHDTFIqaBq6t2awp0lN2Ja6C5AgSAZ9ezJiHk3MdOYkllfS5QOte6jdEzC/p1ubCg5pDVioHTeMkWG38hmiWhuBv441ntNGOLUPnQuaWGslWO/2");
INSERT INTO crypto_keys(fecha_creacion, is_valid, crypto_key) VALUES (NOW(), 1, "1Y6Ppnklpi5Z5DhQQWOln8FodOkSRUIXgjSGfrDalJw=");

INSERT INTO modulos(base_path, nombre) VALUES ("/equipos", "EQUIPOS");
INSERT INTO modulos(base_path, nombre) VALUES ("/images", "UPLOADS");
INSERT INTO modulos(base_path, nombre) VALUES ("/login", "LOGIN");
INSERT INTO modulos(base_path, nombre) VALUES ("/personas", "PERSONAS");
INSERT INTO modulos(base_path, nombre) VALUES ("/roles", "ROLES");
INSERT INTO modulos(base_path, nombre) VALUES ("/sanciones", "SANCIONES");
INSERT INTO modulos(base_path, nombre) VALUES ("/torneos", "TORNEOS");
INSERT INTO modulos(base_path, nombre) VALUES ("/swagger.html", "SWAGGER_DOCS_MAIN");
INSERT INTO modulos(base_path, nombre) VALUES ("/swagger-ui", "SWAGGER_DOCS_UI");
INSERT INTO modulos(base_path, nombre) VALUES ("/v3/api-docs", "SWAGGER_DOCS_V3");


INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("CREAR_EQUIPO", 1, "POST", "", 0);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("LISTAR_ENCUENTROS_LOCAL", 1, "GET", "/encuentrosLocal/[0-9]+", 0);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("LISTAR_ENCUENTROS_VISITANTE", 1, "GET", "/encuentrosVisitante/[0-9]+", 0);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("CARGAR_LOGO", 1, "POST", "/image/[0-9]+", 0);

INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("CAMBIAR_LOGO_EQUIPO", 2, "PUT", "/update/[0-9]+", 0);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("VER_LOGO_EQUIPO", 2, "GET", "/([^/]+\.[^/]+)$", 1);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("VER_FOTO_PERSONA", 2, "GET", "/personas/([^/]+\.[^/]+)$", 1);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("CAMBIAR_FOTO_PERSONA", 2, "PUT", "/update/personas/[0-9]+", 0);

INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("LOGIN", 3, "POST", "", 1);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("VER_PERFIL", 3, "GET", "/profile", 0);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("LOGOUT", 3, "POST", "/logout", 1);

INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("REGISTRAR_PERSONA", 4, "POST", "/registro", 1);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("CARGAR_FOTO", 4, "POST", "/upload/[0-9]+", 0);

INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("AGREGAR_PERMISO_A_ROL", 5, "POST", "/permiso/[0-9]+", 0);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("QUITAR_PERMISO_A_ROL", 5, "DELETE", "/permiso/[0-9]+", 0);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("VER_PERMISOS_POR_ROL", 5, "GET", "/permiso/[0-9]+", 0);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("ELIMINAR_ROL", 5, "DELETE", "/[0-9]+", 0);

INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("CREAR_SANCION", 6, "POST", "", 0);

INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("CREAR_TORNEO", 7, "POST", "", 0);
INSERT INTO operaciones(nombre, modulo_id, metodo_http, path, permit_all) VALUES ("AGREGAR_ENCUENTRO", 7, "POST", "/encuentros/[0-9]+", 0);


INSERT INTO operaciones(nombre, path, metodo_http, modulo_id, permit_all) VALUES ("DOCUMENTACION_SWAGGER_MAIN", "", "GET", 8, 1);
INSERT INTO operaciones(nombre, path, metodo_http, modulo_id, permit_all) VALUES ("DOCUMENTACION_SWAGGER_UI", "/.*$", "GET", 9, 1);
INSERT INTO operaciones(nombre, path, metodo_http, modulo_id, permit_all) VALUES ("DOCUMENTACION_SWAGGER_V3_1", "/.*$", "GET", 10, 1);
INSERT INTO operaciones(nombre, path, metodo_http, modulo_id, permit_all) VALUES ("DOCUMENTACION_SWAGGER_V3_2", "", "GET", 10, 1);
INSERT INTO operaciones(nombre, path, metodo_http, modulo_id, permit_all) VALUES ("VALIDAR_CORREO", "/verify", "GET", 4, 1);


INSERT INTO permisos(operacion_id, rol_id) VALUES (1, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (2, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (3, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (4, 1);

INSERT INTO permisos(operacion_id, rol_id) VALUES (2, 2);
INSERT INTO permisos(operacion_id, rol_id) VALUES (3, 2);


INSERT INTO permisos(operacion_id, rol_id) VALUES (5, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (6, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (7, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (8, 1);

INSERT INTO permisos(operacion_id, rol_id) VALUES (6, 2);
INSERT INTO permisos(operacion_id, rol_id) VALUES (7, 2);
INSERT INTO permisos(operacion_id, rol_id) VALUES (8, 2);

INSERT INTO permisos(operacion_id, rol_id) VALUES (10, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (10, 2);

INSERT INTO permisos(operacion_id, rol_id) VALUES (13, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (13, 2);

INSERT INTO permisos(operacion_id, rol_id) VALUES (14, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (15, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (16, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (17, 1);

INSERT INTO permisos(operacion_id, rol_id) VALUES (18, 1);

INSERT INTO permisos(operacion_id, rol_id) VALUES (19, 1);
INSERT INTO permisos(operacion_id, rol_id) VALUES (20, 1);