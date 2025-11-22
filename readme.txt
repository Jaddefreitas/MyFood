##Descrição do Projeto

O sistema consiste de um aplicativo onde seja possível realizar cadatro de empresas (restaurantes, mercados, farmacias, etc...), clientes (usuários) e entregadores. Além dos seus dados associados tais como produtos e pedidos.


    Descrição geral do design arquitetural do sistema:

        - Para este projeto, foi usado o design arquitetural Command Service, onde o mesmo faz o tratamento das requisições solicitadas do cliente para a aplicação. Este padrão de projeto ajuda no não acumulo de várias subclasses e evita a produção de muitos bugs, pois ao ter uma função com várias tarefas atreladas a ela, o processo de repetição tende a tornar o código extenso e de difícil leitura de sua lógica.Como neste projeto foi visto que são feitas várias solicitações repetidas vezes, não faria sentido ter várias subclasses para uma mesma função, como no arquivo AdicionarProduto,java, que se encontra na pasta Services.

        - Para a parte de Exceptions, foi utilizado o design DDD, para a aplicaçao das boas práticas e melhor identificação das exceções.


    Principais componentes e suas interações:

    Componentes - Facade que se comunica com o storage, o database composto por model e storage e o services onde estão a parte de criação, getAtributos, listagem, deleção e login.

    Para cada padrão de projeto adotado no desenvolvimento do sistema, inclua uma seção dedicada com os seguintes tópicos:
        Nome do Padrão de Projeto:
        Descrição Geral: Explique o padrão de projeto em termos gerais, focando em sua estrutura e propósito.
        Problema Resolvido: Descreva o tipo específico de problema que o padrão é destinado a resolver.
        Identificação da Oportunidade: Explique como e por que a oportunidade de aplicar este padrão foi identificada no contexto do seu projeto.
        Aplicação no Projeto: Detalhe como o padrão foi implementado no desenvolvimento do sistema, com exemplos práticos de seu uso.
