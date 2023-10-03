create table pacientes(
    id bigint not null auto_increment primary key,
    nombre varchar(50) not null ,
    email varchar(50),
    telefono bigint not null ,
    documento varchar(20) unique not null ,
    calle varchar(20),
    numero varchar(10),
    hospital varchar(20),
    ciudad varchar(20),
    codigo_postal varchar(15),
    activo boolean
)