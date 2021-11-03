# Projeto-livraria

O projeto consiste em uma arquitetura de microservices Rest com arquitetura dividida em 5 microservices, 
todos os 5 microservices tem seu proprio banco de dados, estão rodando em containers, expostos via Rest com
testes unitários e de integração. 

1 - USUÁRIO - microservice que tem a responsabilide de gerenciar apenas 2 perfis, Administrador e Vendedor
aonde o Administrador poderá realizar qualquer cadastro em qualquer um dos microservices e o Vendedor
poderá apenas incluir, alterar e excluir vendas e cliente e as demais informações serão de consulta.

2 - LIVRÁRIA - para o cadastro da livrária o endereço deverá ser validado pela API publica http://viacep.com.br/
apenas administradores poderão realizar o cadastro. 

3 - CLIENTE - como no microservice de livrária o cliente também deverá ser validado pela API publica http://viacep.com.br/ 
apenas administradores poderão realizar o cadastro.

4 - ESTOQUE - para o cadastro de um livro obtem validação no microservice de livrária.

5 - VENDA - para realizar uma venda o microservice valida em todos os outros microservices. 

E para gerenciar as requisições nos microservices foi criado também um API-Gateway para auxiliar todos os projetos.  


![Arquitetura](https://user-images.githubusercontent.com/62952498/140090550-d0708c56-c51d-407e-a463-64f009376fcc.jpeg)
