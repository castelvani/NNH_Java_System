create table usuario(
id integer not null,
nome text,
dtnasc date,
email text not null,
senha text not null,
perfil text not null,
constraint id primary key (id)
);

create table item(
id integer primary key not null,
nome text,
tipo text,
preco real,
descricao text,
vezesPedido integer
);

create table pedido(
id integer primary key not null,
id_itemfk integer not null,id_reservafk integer,
quantidade integer,
status text,
constraint id_itemfk foreign key (id_itemfk) references item (id),
constraint id_reservafk foreign key (id_reservafk) references reserva (id)
);

create table quarto(
id integer primary key not null,
numero integer not null,
valorDiaria varchar(100) not null,
tipo text,
status text,
desconto integer
);

create table reserva(
id integer primary key not null,
id_quartofk integer not null,
id_usuariofk integer not null,
dtReserva date,
dtEntrada date not null,
dtSaida date not null,
tipoPagamento text,
situacao text,
orcamento real,
constraint id_quartofk foreign key (id_quartofk) references quarto (id),
constraint id_usuariofk foreign key (id_usuariofk) references usuario (id)
);

create sequence seq_pedido;
create sequence seq_item;
create sequence seq_usuario;
create sequence seq_hospede;
create sequence seq_cartao;
create sequence seq_quarto;

insert into usuario (id, email, senha, nome, dtNasc, perfil) values (nextval('seq_usuario'),'teste', 'teste', 'teste', '1998-01-01', 'ADMINISTRADOR');

insert into usuario (id, email, senha, nome, dtNasc, perfil) values (nextval('seq_usuario'),'ae', 'ae', 'ae', '1998-01-01', 'COMUM');

insert into reserva (id, id_quartofk, id_usuariofk, dtEntrada, dtSaida) values ((nextval('seq_reserva')),
(select id from quarto where id=1),(select id from usuario where id=3),'2/2/2019','2/2/2019')

