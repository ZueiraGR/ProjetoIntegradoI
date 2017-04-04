'create database reserva;
use reserva;

create table cliente(
nome char(15) not null,
sobrenome char(15) not null,
telefone char(8)not null primary key,
cpf char(12) not null,
email char(30) not null
);

create table funcionario(
nome char(15) not null,
sobrenome char(15) not null,
cpf char(12) not null,
chavecargo int not null primary key,
datainclusao datetime not null
);

create table cargos(
chavecargo decimal(1) not null primary key,
nome char(15) not null,
nivelacesso decimal(1) not null
);

create table pedido(
chavepedido int not null auto_increment primary key,
cpfcliente int not null,##não confio em  deixar cpf como chave primaria por isso deixei o telefone como primary key
token char(10), ##nâo sei o que fazer##
horaprevista datetime not null,
quantidadecadeiras int not null,
telefone int not null
);

create table itens_de_pedido(
chavepedido int not null,
chaveproduto int not null,
quantidade decimal(4)
);

create table mesas(
chavemesa int not null auto_increment primary key,
numero decimal(10) not null,
totalcadeiras decimal(10) not null,
localizacao char(30) not null
);

create table reservas(
chavemesa int not null,
token char(10),##não sei o que fazer##
horarioprevisto datetime not null,
quantidadecadeiras decimal(4) not null
);
 alter table pedido add constraint fk_telefonecliente foreign key(telefonecliente) references cliente(telefone);
 alter table pedido add constraint fk_quantidadecadeiras foreign key(quantidadecadeiras) references reservas(quantidadecadeiras);
 alter table itens_de_pedido add constraint fk_chavepedido foreign key(chavepedido) references pedido(chavepedido);
 alter table itens_de_pedido add constraint fk_chaveproduto foreign key(chaveproduto) references produto(chaveproduto);
 alter table reservas add constraint fk_chavemesa foreign key(chavemesa) references mesa(chavemesa);
 
 insert into cliente(nome,sobrenome,telefone,cpf,email)
 value ('Felipe','Gontijo',12345678,12312312333,'felipe.gomapim@gmail.com'),;
 
 insert into funcionario(nome,sobrenome,chavecargo,cpf,datainclusao)
 value('Felipe','Gontijo',null,12312312333,null),;
 ('Ferdnand','Gontijo',null,12314312333,null),
('Edmund','Dante',null,12312312333,null),
('Carter','Gontijo',null,12112312333,null);

 insert into cargos(chavecargo,nome,nivelacesso)
 value(1,'Felipe',1),
(1,'Ferdnand',2),
(1,'Dante',3),
(1,'Carter',3),
 
 insert into pedido(chavepedido,horaprevista.quantidadecadeiras,telefone)
 value(null,null,23,12345678),
(null,null,12,12245678),
(null,null,1,12325678),
(null,null,3,12335678);


Falta tabela inset de item de pedido,mesa e reserva'