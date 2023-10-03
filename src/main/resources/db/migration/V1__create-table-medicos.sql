create table medicos(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    estado_civil varchar(100) not null,
    edad int not null,
    especialidad varchar(100) not null ,
    calle varchar(100) not null ,
    numero varchar(100) not null,
    hospital varchar(100),
    ciudad varchar(100) not null ,
    codigo_postal varchar(100),
    primary key (id)
);