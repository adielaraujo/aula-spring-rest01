CREATE TABLE public.pessoa
(
   id serial NOT NULL, 
   nome text  NOT NULL, 
   ativo boolean  NOT NULL,
   logradouro text,
   numero text,
   complemento text,
   bairro text,
   cep text,
   cidade text,
   estado text,
   CONSTRAINT pk_pessoas PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
);


insert into public.pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values('Adiel dos santos', true, 'Rua 2', '235', null,'Centenario', '69300000', 'Boa Vista', 'Roraima');
insert into public.pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values('Rebeca dos santos', true, 'Rua 2', null, null,'Centenario', '69300000', 'Boa Vista', 'Roraima');
insert into public.pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values('Samuel dos santos', true, 'Rua 4', null, null,null, '69300000', 'Boa Vista', 'Roraima');
insert into public.pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values('Emiliana dos santos', true, 'Rua 5', '235', null,'Centenario', '69300000', 'Boa Vista', 'Roraima');
