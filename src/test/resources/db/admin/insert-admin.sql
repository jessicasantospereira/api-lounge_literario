insert into produto (titulo, descricao, preco, qtde_estoque, numero_paginas, autor, editora, categoria, altura, peso, largura, profundidade, is_ativo)
values ('Produto 1', 'Descrição do Produto 1', 10.0, 50, 100, 'Autor 1', 'Editora 1', 'AVENTURA', 10.0, 100.0, 10.0, 10.0, true),
       ('Produto 2', 'Descrição do Produto 2', 20.0, 100, 200, 'Autor 2', 'Editora 2', 'BIOGRAFIA', 20.0, 200.0, 20.0, 20.0, true),
       ('Produto 3', 'Descrição do Produto 3', 30.0, 150, 300, 'Autor 3', 'Editora 3', 'DRAMA', 30.0, 300.0, 30.0, 30.0, true),
       ('Produto 4', 'Descrição do Produto 4', 40.0, 200, 400, 'Autor 4', 'Editora 4', 'FANTASIA', 40.0, 400.0, 40.0, 40.0, true),
       ('Produto 5', 'Descrição do Produto 5', 50.0, 250, 500, 'Autor 5', 'Editora 5', 'AUTO_AJUDA', 50.0, 500.0, 50.0, 50.0, true);

insert into Clientes ( nome, ranking, is_ativo)
values ( 'João', 0, true), ('Pedro Henrique', 10, true);

insert into enderecos (id_endereco, logradouro, numero, bairro, cidade, uf, cep, id_cliente, end_cobranca, end_entrega)
values (15, 'Rua 1', 1, 'Bairro 1', 'Cidade 1', 'Estado 1', '00000-000', 1, true, false),
       (16, 'Rua 2', 2, 'Bairro 2', 'Cidade 2', 'Estado 2', '00000-000', 2, true, true);

insert into
    venda (data_venda, parcelas, status_venda, tem_cupom, tem_troca, total, valor_parcela, id_cliente, id_cupom, id_troca,id_endereco)
values('2025-03-20',0,'EM_PROCESSAMENTO',false,false,500,  null,1,null,null,null);
insert into
    venda (data_venda, parcelas, status_venda, tem_cupom, tem_troca, total, valor_parcela, id_cliente, id_cupom, id_troca,id_endereco)
values('2025-03-21',10,'EM_PROCESSAMENTO',false,false,2500,  250,1,null,null,null);
insert into
    venda (data_venda, parcelas, status_venda, tem_cupom, tem_troca, total, valor_parcela, id_cliente, id_cupom, id_troca,id_endereco)
values('2025-03-22',5,'EM_PROCESSAMENTO',false,false,2500,  500,2,null,null,null);

insert into item_venda (id_produto, quantidade, id_venda)
values (1, 10, 1),
       (2, 20, 1),
       (3, 30, 2),
       (4, 40, 2),
       (5, 50, 3);