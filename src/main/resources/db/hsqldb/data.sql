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
INSERT INTO owners VALUES (11, 'Frank', 'De La Jungla', 'Av. Cuesta´s Shelter', 'Tailandia', '6266755877', 'owner1');

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
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Frankomodo', '2012-07-19', 7, 11);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (1, 'Otitis', 'Inflamación del conjunto auditivo, dolor', 1, 1);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (2, 'Conjuntivitis', 'Inflamación de la mucosa del ojo', 1, 1);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (3, 'Rabia', 'Exceso de salivación', 1, 1);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (4, 'Leucemia felina', 'Falta de apetito, somnolencia, anemia', 3, 1);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (5, 'Panleucopenia felina', 'Hipotermia, vómitos, diarrea, debilidad, deshidratación, anorexia', 3, 1);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (6, 'Inmunodeficiencia felina', 'Infecciones bucales, patologías respiratorias, infecciones intestinales', 2, 1);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (7, 'Peritonitis', 'Fiebre, anorexia, aumento del volumen abdominal', 2, 1);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (8, 'Cistitis', 'Dolor al orinar, mucha sed', 1, 1);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (9, 'Dermatitis', 'Mucho picor', 1, 2);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (10, 'Otitis', 'Inflamación, dolor, picor', 1, 2);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (11, 'Artritis', 'Inflamación de las articulaciones, dolor', 2, 2);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (12, 'Periodontitis', 'Mal aliento, pérdida de dientes', 2, 2);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (13, 'Diarrea', 'Malestar intestinal, heces acuosas', 1, 2);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (14, 'Moquillo', 'Deshidratación', 1, 2);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (15, 'Rabia', 'Exceso de salivación', 1, 2);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (16, 'Coronavirus', 'Vómitos, diarrea', 1, 2);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (17, 'Conjuntivitis', 'Inflamación y enrojecimiento ocular', 1, 2);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (18, 'Cáncer', 'Malestar general', 3, 2);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (19, 'Ácaros y hongos', 'Picores excesivos, piel irritada, formación de eccemas', 2, 6);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (20, 'Bronquitis', 'Dificultad respiratoria, inapetencia, estornudos, ojos lagrimosos, nariz mocosa', 2, 6);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (21, 'Estreñimiento', 'Carencia o disminución de excrementos', 1, 6);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (22, 'Cáncer', 'Inapetencia, poca actividad, pérdida de peso y pelo', 3, 6);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (23, 'Rabia', 'Esputa muchas babas,está muy rabioso', 1, 4);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (24, 'Coronavirus', 'fiebre,tos,problemas respiratorios', 1, 4);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (25, 'Hepatitis', 'inapetencia,letargia', 1, 5);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (26, 'Coronavirus', 'fiebre,tos,problemas respiratorios,no da vueltas en la rueda', 1, 3);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (27, 'Aspergilosis', 'Dificultad al respirar,dejar de comer', 1, 5);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (28, 'SIDA', 'Esputa muchas babas,está muy rabioso', 2, 3);
INSERT INTO sicknesses(id,name,symptom,severity,type_id) VALUES (29, 'Prueba', '', 0, 2);

INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (1, 'Vacuna A', 'A,S,D,F', 4, 3);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (2, 'Vacuna B', 'Q,W,E,R', 6, 1);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (3, 'Vacuna C', 'Z,X,C,V', 8, 2);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (4, 'Vacuna D', 'T,Y,U,I', 2, 4);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (5, 'Vacuna E', 'G,H,J,K', 11, 5);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (6, 'Vacunote', 'V,B,N,M', 14, 6);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (7, 'Vacunote', 'V,B,N,M', 19, 6);
INSERT INTO vaccines(id,name,components,months,sickness_id) VALUES (8, 'Vacunote', '', 0, 6);
