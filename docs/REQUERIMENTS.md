# Requisitos do Projeto: Ticketeria Online

Este documento detalha as funcionalidades do sistema na forma de histórias de usuário, baseadas no escopo definido. Cada história descreve uma funcionalidade do ponto de vista do usuário e inclui critérios de aceitação.

---

## 1. Módulo de Autenticação e Perfis

### 1.1. Autenticação

* **História de Usuário 1 :** Como um **usuário**, eu quero me **cadastrar** com e-mail e senha para ter acesso seguro ao sistema.
    * **Critérios de Aceitação:**
        * O usuário deve fornecer um e-mail válido, cpf, e uma senha.
        * A senha deve ter no mínimo 8 caracteres, com pelo menos  1 caractere, 1 letra maiúscula e minúscula, 1 número.
        * O sistema deve validar se o e-mail já está em uso e exibir uma mensagem de erro se estiver.
        * Após o cadastro, o usuário deve ser redirecionado para a página de login.

* **História de Usuário 2:** Como um **usuário**, eu quero **fazer login** com meu e-mail e senha para acessar minhas informações e funcionalidades.
    * **Critérios de Aceitação:**
        * O sistema deve verificar as credenciais (e-mail e senha).
        * Se as credenciais estiverem corretas,o sistema deve gerar um token de acordo com a role, e seguir para a tela inicial.
        * Se as credenciais estiverem incorretas, o sistema deve exibir uma mensagem de erro genérica ("E-mail ou senha inválidos").

### 1.2. Gestão de Perfil

* **História de Usuário 3 :** Como um **usuário**, eu quero poder **visualizar e editar meu perfil** para manter minhas informações atualizadas.
    * **Critérios de Aceitação:**
        * O usuário deve ter acesso a uma página de perfil.
        * Nessa página, deve ser possível editar nome, email, senha, telefone, endereço, gênero, data de nascimento.
        * O sistema deve validar se o novo e-mail não está em uso por outro usuário.
        * As mudanças devem ser salvas no banco de dados.

---

## 2. Módulo de Eventos (para Organizadores)

* **História de Usuário 4 :** Como um **organizador**, eu quero **criar um evento** para que os usuários possam reservá-lo.
    * **Critérios de Aceitação:**
        * Apenas usuários com o papel de "organizador" ou "administrador" podem acessar o formulário de criação de evento.
        * O formulário deve conter campos para: imagem, nome, data, local, descrição e número de ingressos de cada lote e valor.
        * Todos os campos, exceto a descrição, são obrigatórios.
        * O número de ingressos deve ser um valor numérico positivo.
        * O evento criado deve ser associado ao ID do organizador.

* **História de Usuário 5:** Como um **organizador**, eu quero **gerenciar meus eventos** para poder editar ou excluir eventos que eu criei.
    * **Critérios de Aceitação:**
        * O organizador deve ter um painel que liste apenas os eventos que ele criou, validando pelo token.
        * Cada evento na lista deve ter botões para "Editar" e "Excluir".
        * Ao clicar em "Editar", o usuário é levado ao formulário de edição pré-preenchido.
        * Ao clicar em "Excluir", o sistema deve pedir uma confirmação antes de remover o evento do banco de dados.

* **História de Usuário 6 :** Como um **organizador**, eu quero **visualizar** o meu evento criado
    * **Critérios de Aceitação:**
        * Apenas usuários com o papel de "organizador" ou "administrador" podem visualizar o evento criado.
        * O retorno deve conter: dados gerais do evento, ingressos totais vendidos, valor total arrecadado, ingressos de cada lote vendido, ingressos de cada lote disponível, valor do ingresso de cada lote. 
---

## 3. Módulo de Compra (para Usuários)

* **História de Usuário:** Como um **usuário**, eu quero **visualizar todos os eventos disponíveis** para escolher qual me interessa.
    * **Critérios de Aceitação:**
        * A página inicial deve exibir uma lista de todos os eventos, paginado.
        * Cada item da lista deve mostrar o nome do evento, data, local e o valor dos ingressos do lote atual.
        * Os eventos devem ser exibidos em ordem cronológica (do mais próximo para o mais distante).

* **História de Usuário:** Como um **usuário**, eu quero **comprar um ingresso** para um evento que eu gostei.
    * **Critérios de Aceitação:**
        * O usuário deve poder clicar em um botão "Comprar Ingresso" na página do evento, selecionando a quantidade x de ingressos.
        * A compra só é possível se houver ingressos disponíveis.
        * A quantidade de ingressos disponíveis para o evento deve ser reduzida em x após a compra.
        * O sistema deve registrar a compra, associando o ingresso ao ID do usuário.
        * O usuário deve receber uma mensagem de sucesso após a reserva pelo email.
        * O valor do ingresso deve ser somado no rendimento do evento

* **História de Usuário:** Como um **usuário**, eu quero **ver um histórico de minhas compras** para saber quais ingressos eu já garanti.
    * **Critérios de Aceitação:**
        * Deve haver uma página ou seção no perfil do usuário que liste todas as suas compras.
        * A lista deve mostrar o nome do evento, a data, a data da reserva e o valor do ingresso.

---
