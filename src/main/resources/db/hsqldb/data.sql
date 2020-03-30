-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities VALUES ('admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities VALUES ('vet1','veterinarian');

INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'komodo dragon');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'Frank', 'De La Jungla', 'Av. Cuesta´s Shelter', 'Tailandia', '666666666', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Frankomodo', '2003-02-24', 7, 11);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (1, 'Otitis', 'Parásitos(ácaros), hongos, bacterias', 'Inflamación del conjunto auditivo, dolor, pérdida de audición', 2, 1);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (2, 'Conjuntivitis', 'Infecciones oculares, alergias, problemas genéticos', 'Inflamación de la mucosa del ojo, pérdida de visión, lagrimeo', 2, 1);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (3, 'Rabia', 'Virus', 'Irritabilidad, exceso de salivación, fiebre, vómitos', 1, 1);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (4, 'Leucemia felina', 'Contacto de los fluidos corporales', 'Falta de apetito, somnolencia, anemia, debilidad', 3, 1);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (5, 'Panleucopenia felina', 'Parvovirus', 'Fiebre, hipotermia, vómitos, diarrea, debilidad, deshidratación, anorexia', 3, 1);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (6, 'Inmunodeficiencia felina', 'Lentivirus', 'Infecciones bucales, patologías respiratorias, infecciones intestinales', 3, 1);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (7, 'Peritonitis', 'Virus de la familia de los coronavirus', 'Fiebre, anorexia, aumento del volumen abdominal', 1, 1);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (8, 'Cistitis', 'Formación de minerales que obstruyen el conducto urinario', 'Dolor al orinar, mucha sed, vómitos', 1, 1);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (9, 'Dermatitis', 'Picadura de pulga', 'Mucho picor', 1, 2);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (10, 'Otitis', 'Cuerpos extraños', 'Inflamación del conducto auditivo, dolor, picor', 1, 2);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (11, 'Artritis', 'Envejecimiento', 'Inflamación de las articulaciones, dolor', 2, 2);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (12, 'Periodontitis', 'Formación de placa bacteriana en dientes y encías', 'Mal aliento, pérdida de dientes', 2, 2);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (13, 'Moquillo', 'Virus', 'Fiebre, deshidratación, tos', 1, 2);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (14, 'Hepatitis', 'Adenovirus', 'Vómitos, sed, dolor', 2, 2);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (15, 'Rabia', 'Mordisco de un animal infectado', 'Fiebre, agresividad, irritabilidad, vómitos', 2, 2);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (16, 'Coronavirus', 'Contacto con secreciones orales y fecales infectadas', 'Deshidratación, vómitos, diarrea', 1, 2);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (17, 'Conjuntivitis', 'Bacterias', 'Inflamación y enrojecimiento ocular', 1, 2);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (18, 'Cáncer', 'Exposición a radiaciones ionizantes, ciertos químicos, motivos genéticos', 'Cansancio, fiebre, dolor', 3, 2);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (19, 'Ácaros y hongos', 'Parásitos', 'Picores excesivos, piel irritada, formación de eccemas', 2, 6);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (20, 'Bronquitis', 'Resfriados, cambios de temperatura, corrientes de aire', 'Dificultad respiratoria, inapetencia, estornudos, ojos lagrimosos, nariz mocosa', 2, 6);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (21, 'Cola mojada', 'Colibacterias', 'Diarrea, deshidratación', 1, 6);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (22, 'Estreñimiento', 'Mala alimentación', 'Carencia o disminución de excrementos, dolor, inapetencia, hinchazón', 1, 6);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (23, 'Cáncer', 'Aumento de la división celular', 'Inapetencia, poca actividad, pérdida de peso y pelo', 3, 6);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (24, 'Disecdisis', 'Problemas en la muda', 'Infecciones o problemas cutáneos', 1, 4);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (25, 'Septicemia', 'Ácaros', 'Dificultad para respirar', 2, 4);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (26, 'Blister', 'Humedad, aseo deficiente del entorno', 'Enrojecimiento en las escamas', 2, 4);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (27, 'Boca Rot', 'Bacterias', 'Acumulación de pus en las encías y entre los dientes', 1, 4);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (28, 'Septicemia', 'Ácaros', 'Dificultad para respirar', 2, 3);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (29, 'Muda', 'Problemas en la muda', 'Infecciones o problemas cutáneos', 2, 3);
INSERT INTO sicknesses(id,name,cause,symptom,severity,type_id) VALUES (30, 'Pájaro Loco', '', '', 0, 5);

INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (1, 'Vacuna A', 'Q,W,E,R', 4, 1);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (2, 'Vacuna B', 'A,S,D,F', 6, 2);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (3, 'Vacuna C', 'Z,X,C,V', 8, 3);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (4, 'Vacuna D', 'G,H,J,K', 2, 4);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (5, 'Vacuna E', 'G,H,J,K', 11, 5);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (6, 'Vacuna F', 'Componente A', 12, 6);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (7, 'Vacuna F2', 'Componente A', 18, 6);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (8, 'Vacunote', '', 0, 6);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (9, 'Vacuna OT1', 'Q,W,E,R', 4, 10);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (10, 'Vacuna OT2', 'Q,W,E,R', 6, 10);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (11, 'Vacuna AR', 'Z,X,C,V', 8, 11);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (12, 'Vacuna PE', '', 0, 12);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (13, 'Vacuna MO', 'G,H,J,K', 10, 13);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (14, 'Vacuna HE1', 'Componente H', 6, 14);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (15, 'Vacuna HE2', 'Componente H', 12, 14);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (16, 'Vacuna RA1', 'G,D,H,T', 1, 15);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (17, 'Vacuna RA2', 'G,D,H,T', 5, 15);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (18, 'Vacuna CO1', '', 0, 16);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (19, 'Vacuna CON1', 'Q,W,E,R', 5, 17);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (20, 'Vacuna CON2', 'Q,W,E,R', 7, 17);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (21, 'Vacuna BRON', 'O,G,T,Y', 4, 20);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (22, 'Vacuna D', 'G,H,J,K', 10, 24);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (23, 'Vacuna S', 'Componente S', 6, 25);

INSERT INTO clinics(id,name,address,telephone,email) VALUES (1, 'Vacunote Clinic', 'C/Reina Mercedes,25','666666666','vacunote@clinic.com');

