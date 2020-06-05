-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT IGNORE INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT IGNORE INTO authorities VALUES ('admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT IGNORE INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT IGNORE INTO authorities VALUES ('owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT IGNORE INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT IGNORE INTO authorities VALUES ('vet1','veterinarian');

INSERT IGNORE INTO vets VALUES (1, 'James', 'Carter');
INSERT IGNORE INTO vets VALUES (2, 'Helen', 'Leary');
INSERT IGNORE INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT IGNORE INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT IGNORE INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT IGNORE INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT IGNORE INTO specialties VALUES (1, 'radiology');
INSERT IGNORE INTO specialties VALUES (2, 'surgery');
INSERT IGNORE INTO specialties VALUES (3, 'dentistry');

INSERT IGNORE INTO vet_specialties VALUES (2, 1);
INSERT IGNORE INTO vet_specialties VALUES (3, 2);
INSERT IGNORE INTO vet_specialties VALUES (3, 3);
INSERT IGNORE INTO vet_specialties VALUES (4, 2);
INSERT IGNORE INTO vet_specialties VALUES (5, 1);

INSERT IGNORE INTO types VALUES (1, 'cat');
INSERT IGNORE INTO types VALUES (2, 'dog');
INSERT IGNORE INTO types VALUES (3, 'lizard');
INSERT IGNORE INTO types VALUES (4, 'snake');
INSERT IGNORE INTO types VALUES (5, 'bird');
INSERT IGNORE INTO types VALUES (6, 'hamster');
INSERT IGNORE INTO types VALUES (7, 'komodo dragon');

INSERT IGNORE INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT IGNORE INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT IGNORE INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT IGNORE INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT IGNORE INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT IGNORE INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT IGNORE INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT IGNORE INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT IGNORE INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT IGNORE INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT IGNORE INTO owners VALUES (11, 'Frank', 'De La Jungla', 'Av. Cuesta´s Shelter', 'Tailandia', '666666666', 'owner1');

INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT IGNORE INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Frankomodo', '2003-02-24', 7, 11);

INSERT IGNORE INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT IGNORE INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT IGNORE INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT IGNORE INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (1, 'Otitis', 'Parasitos(acaros), hongos, bacterias', 'Inflamacion del conjunto auditivo, dolor, perdida de audicion', 2, 1);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (2, 'Conjuntivitis', 'Infecciones oculares, alergias, problemas geneticos', 'Inflamacion de la mucosa del ojo, perdida de vision, lagrimeo', 2, 1);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (3, 'Rabia', 'Virus', 'Irritabilidad, exceso de salivacion, fiebre, vomitos', 1, 1);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (4, 'Leucemia felina', 'Contacto de los fluidos corporales', 'Falta de apetito, somnolencia, anemia, debilidad', 3, 1);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (5, 'Panleucopenia felina', 'Parvovirus', 'Fiebre, hipotermia, vomitos, diarrea, debilidad, deshidratacion, anorexia', 3, 1);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (6, 'Inmunodeficiencia felina', 'Lentivirus', 'Infecciones bucales, patologias respiratorias, infecciones intestinales', 3, 1);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (7, 'Peritonitis', 'Virus de la familia de los coronavirus', 'Fiebre, anorexia, aumento del volumen abdominal', 1, 1);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (8, 'Cistitis', 'Formacion de minerales que obstruyen el conducto urinario', 'Dolor al orinar, mucha sed, vomitos', 1, 1);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (9, 'Dermatitis', 'Picadura de pulga', 'Mucho picor', 1, 2);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (10, 'Otitis', 'Cuerpos extraños', 'Inflamacion del conducto auditivo, dolor, picor', 1, 2);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (11, 'Artritis', 'Envejecimiento', 'Inflamacion de las articulaciones, dolor', 2, 2);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (12, 'Periodontitis', 'Formacion de placa bacteriana en dientes y encias', 'Mal aliento, perdida de dientes', 2, 2);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (13, 'Moquillo', 'Virus', 'Fiebre, deshidratacion, tos', 1, 2);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (14, 'Hepatitis', 'Adenovirus', 'Vomitos, sed, dolor', 2, 2);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (15, 'Rabia', 'Mordisco de un animal infectado', 'Fiebre, agresividad, irritabilidad, vomitos', 2, 2);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (16, 'Coronavirus', 'Contacto con secreciones orales y fecales infectadas', 'Deshidratacion, vomitos, diarrea', 1, 2);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (17, 'Conjuntivitis', 'Bacterias', 'Inflamacion y enrojecimiento ocular', 1, 2);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (18, 'Cancer', 'Exposicion a radiaciones ionizantes, ciertos quimicos, motivos geneticos', 'Cansancio, fiebre, dolor', 3, 2);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (19, 'Acaros y hongos', 'Parasitos', 'Picores excesivos, piel irritada, formacion de eccemas', 2, 6);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (20, 'Bronquitis', 'Resfriados, cambios de temperatura, corrientes de aire', 'Dificultad respiratoria, inapetencia, estornudos, ojos lagrimosos, nariz mocosa', 2, 6);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (21, 'Cola mojada', 'Colibacterias', 'Diarrea, deshidratacion', 1, 6);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (22, 'Estreñimiento', 'Mala alimentacion', 'Carencia o disminucion de excrementos, dolor, inapetencia, hinchazon', 1, 6);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (23, 'Cancer', 'Aumento de la division celular', 'Inapetencia, poca actividad, perdida de peso y pelo', 3, 6);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (24, 'Disecdisis', 'Problemas en la muda', 'Infecciones o problemas cutaneos', 1, 4);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (25, 'Septicemia', 'Acaros', 'Dificultad para respirar', 2, 4);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (26, 'Blister', 'Humedad, aseo deficiente del entorno', 'Enrojecimiento en las escamas', 2, 4);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (27, 'Boca Rot', 'Bacterias', 'Acumulacion de pus en las encias y entre los dientes', 1, 4);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (28, 'Septicemia', 'Acaros', 'Dificultad para respirar', 2, 3);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (29, 'Muda', 'Problemas en la muda', 'Infecciones o problemas cutaneos', 2, 3);
INSERT IGNORE INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (30, 'Pajaro Loco', '', '', 0, 5);

INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (1, 'Vacuna A', 'Q,W,E,R', 4, 1);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (2, 'Vacuna B', 'A,S,D,F', 6, 2);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (3, 'Vacuna C', 'Z,X,C,V', 8, 3);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (4, 'Vacuna D', 'G,H,J,K', 2, 4);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (5, 'Vacuna E', 'G,H,J,K', 11, 5);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (6, 'Vacuna F', 'Componente A', 12, 6);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (7, 'Vacuna F2', 'Componente A', 18, 6);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (8, 'Vacunote', '', 0, 6);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (9, 'Vacuna OT1', 'Q,W,E,R', 4, 10);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (10, 'Vacuna OT2', 'Q,W,E,R', 6, 10);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (11, 'Vacuna AR', 'Z,X,C,V', 8, 11);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (12, 'Vacuna PE', '', 0, 12);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (13, 'Vacuna MO', 'G,H,J,K', 10, 13);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (14, 'Vacuna HE1', 'Componente H', 6, 14);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (15, 'Vacuna HE2', 'Componente H', 12, 14);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (16, 'Vacuna RA1', 'G,D,H,T', 1, 15);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (17, 'Vacuna RA2', 'G,D,H,T', 5, 15);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (18, 'Vacuna CO1', '', 0, 16);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (19, 'Vacuna CON1', 'Q,W,E,R', 5, 17);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (20, 'Vacuna CON2', 'Q,W,E,R', 7, 17);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (21, 'Vacuna BRON', 'O,G,T,Y', 4, 20);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (22, 'Vacuna D', 'G,H,J,K', 10, 24);
INSERT IGNORE INTO vaccines(id,name,components,months,sickness_id) VALUES (23, 'Vacuna S', 'Componente S', 6, 25);


INSERT IGNORE INTO medicines(id,name,components,treatment,type_id,sickness_id) VALUES (1, 'Medicina A', 'Componente A', '1 cada 8 horas', 1, 1);
INSERT IGNORE INTO medicines(id,name,components,treatment,type_id,sickness_id) VALUES (2, 'Medicina B', 'Componente B', '1 cada 24 horas', 1, 2);
INSERT IGNORE INTO medicines(id,name,components,treatment,type_id,sickness_id) VALUES (3, 'Medicina C', 'Componente A', '1 cada 8 horas', 2, 1);
INSERT IGNORE INTO medicines(id,name,components,treatment,type_id,sickness_id) VALUES (4, 'Medicina D', 'Componente B', '1 cada 24 horas', 2, 2);
INSERT IGNORE INTO medicines(id,name,components,treatment,type_id,sickness_id) VALUES (5, 'Medicina E', 'Componente A', '1 cada 8 horas', 3, 1);
INSERT IGNORE INTO medicines(id,name,components,treatment,type_id,sickness_id) VALUES (6, 'Medicina F', 'Componente B', '1 cada 24 horas', 3, 2);
INSERT IGNORE INTO medicines(id,name,components,treatment,type_id,sickness_id) VALUES (7, 'Medicina G', 'Componente A', '1 cada 8 horas', 4, 1);
INSERT IGNORE INTO medicines(id,name,components,treatment,type_id,sickness_id) VALUES (8, 'Medicina H', 'Componente B', '1 cada 24 horas', 4, 2);

INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (1, 'Winston Pet Cares','Evergreen Av. 4' ,'Pitsburg','charles@mail.com', '600033472');
INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (2, 'Veterinaria Nervion','Calle Juan 10' ,'Sevilla','vetnervion@mail.com', '685123477');
INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (3, 'Clinica Los Arcos','' ,'','', '');
INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (4, 'Veterinaria AliPet','Calle Paloma 1' ,'Sevilla','alipet@mail.com', '684559612');
INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (5, 'Veterinaria Pablo Millan','Calle Estrella Vega 5' ,'Granada','pmillan@mail.com', '963789654');
INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (6, 'Veterinaria Macarena','Calle Amor 8' ,'Cordoba','vetmac@mail.com', '693258145');
INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (7, 'Veterinaria Los Remedios','Calle Locura 6' ,'Malaga','vetrem@,mail.com', '652369741');
INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (8, 'Veterinaria Santiponce','Calle Cueva 3' ,'Jaen','vetsanti@mail.com', '654123789');
INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (9, 'Vetpinoli','Av Gracia 9' ,'Almeria','vetpinoli@mail.com', '663214852');
INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (10, 'Clinica Hispalis','Calle Paloma 12' ,'Sevilla','hispavet@mail.com', '954231852');
INSERT IGNORE INTO clinics(id,name,address,city,email,telephone) VALUES (11, 'Clinica lalita','Calle Pino 3' ,'Sevilla','lalita@mail.com', '954231852');



INSERT IGNORE INTO product_types(id, name) VALUES (1, 'Higiene');
INSERT IGNORE INTO product_types(id, name) VALUES (2, 'Juguetes');
INSERT IGNORE INTO product_types(id, name) VALUES (3, 'Alimentacion');

INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (1,'champu hidratante','para pelo seco',4.00,3,1,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (2,'Limpiador bucal','contiene laminas masticables para mantener limpia la dentadura del perro',8.00,100,1,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (3,'Limpiador bucal','contiene laminas masticables para mantener limpia la dentadura del perro',8.00,100,2,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (4,'champu para gato','',20.00,1,1,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (5,'champu hidratante','para pelo seco',4.00,34,4,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (6,'champu hidratante','para pelo seco',4.00,39,5,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (7,'cepillo','para peinar a perros y gatos.',5.00,30,6,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (8,'Stomodine 30 mls.','Stomodine gel para perros y gatos.Limpieza bucal, reduce la formacion de placa y sarro. Infecciones cavidad bucal.',6.00,48,1,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (9,'champu hidratante','para pelo seco',4.00,3,7,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (10,'champu hidratante','para pelo seco',4.00,3,8,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (11,'champu hidratante','para pelo seco',4.00,3,9,1);
INSERT IGNORE INTO products(id,name,description,price,stock,clinic_id, product_type_id) VALUES (12,'champu hidratante','para pelo seco',4.00,3,10,1);
