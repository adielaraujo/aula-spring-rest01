CREATE TABLE public.lancamento
(
  id serial NOT NULL,
  descricao text NOT NULL,
  data_vencimento DATE NOT NULL,
  data_pagamento DATE NOT NULL,
  valor DECIMAL(15,2) NOT NULL,
  observacao text,
  tipo TEXT NOT NULL,
  categoria_id INTEGER NOT NULL,
  pessoa_id INTEGER NOT NULL,
  CONSTRAINT pk_lancamento PRIMARY KEY (id),

  CONSTRAINT fk_categoria FOREIGN KEY (categoria_id)
      REFERENCES public.categoria (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pessoa FOREIGN KEY (pessoa_id)
      REFERENCES public.pessoa (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


INSERT INTO public.lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
    VALUES ('Lancamento1', '2019-01-02', '2019-01-02', 5000.00, 'obs: lancamento01', 'RECEITA', 1, 1);

INSERT INTO public.lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
    VALUES ('Lancamento2', '2019-01-02', '2019-01-02', 5000.00, 'obs: lancamento01', 'RECEITA', 2, 2);

INSERT INTO public.lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
    VALUES ('Lancamento3', '2019-01-02', '2019-01-02', 5000.00, 'obs: lancamento01', 'RECEITA', 3, 3);

INSERT INTO public.lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
    VALUES ('Lancamento4', '2019-01-02', '2019-01-02', 5000.00, 'obs: lancamento01', 'RECEITA', 4, 1);

INSERT INTO public.lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
    VALUES ('Lancamento5', '2019-01-02', '2019-01-02', 5000.00, 'obs: lancamento01', 'RECEITA', 5, 2);

INSERT INTO public.lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
    VALUES ('Lancamento6', '2019-01-02', '2019-01-02', 5000.00, 'obs: lancamento01', 'RECEITA', 1, 3);

INSERT INTO public.lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
    VALUES ('Lancamento7', '2019-01-02', '2019-01-02', 5000.00, 'obs: lancamento01', 'DESPESA', 2, 4);

INSERT INTO public.lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
    VALUES ('Lancamento8', '2019-01-02', '2019-01-02', 5000.00, 'obs: lancamento01', 'DESPESA', 3, 1);

INSERT INTO public.lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
    VALUES ('Lancamento9', '2019-01-02', '2019-01-02', 5000.00, 'obs: lancamento01', 'DESPESA', 4, 2);
        
