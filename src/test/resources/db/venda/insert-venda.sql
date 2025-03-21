insert into Clientes (id_cliente, nome, ranking, is_ativo)
values (1, 'João', 0, true);

insert into enderecos (id_endereco, logradouro, numero, bairro, cidade, uf, cep, id_cliente, end_cobranca, end_entrega)
values (1, 'Rua 1', 1, 'Bairro 1', 'Cidade 1', 'Estado 1', '00000-000', 1, true, false);

INSERT INTO Venda (id,  parcelas, valor_parcela, total, id_cliente, tem_cupom, tem_troca )
VALUES (1, 2, 100.0, 200.0, 1, false, false);

