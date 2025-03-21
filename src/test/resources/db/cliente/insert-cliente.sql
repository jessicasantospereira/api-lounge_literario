insert into Clientes (id_cliente, nome, ranking, is_ativo)
values (10, 'João', 0, true), (11, 'Pedro Henrique', 10, true);

insert into enderecos (id_endereco, logradouro, numero, bairro, cidade, uf, cep, id_cliente, end_cobranca, end_entrega)
values (15, 'Rua 1', 1, 'Bairro 1', 'Cidade 1', 'Estado 1', '00000-000', 10, true, false),
(16, 'Rua 2', 2, 'Bairro 2', 'Cidade 2', 'Estado 2', '00000-000', 11, true, true);