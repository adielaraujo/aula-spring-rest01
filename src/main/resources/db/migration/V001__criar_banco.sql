CREATE TABLE public.categoria
(
   id serial NOT NULL, 
   descricao text, 
   CONSTRAINT pk_categorias PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
);

