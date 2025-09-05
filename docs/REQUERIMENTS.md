# Requisitos do Projeto: Ticketeria Online

Este documento detalha as funcionalidades do sistema na forma de histórias de usuário, baseadas no escopo definido. Cada história descreve uma funcionalidade do ponto de vista do usuário e inclui critérios de aceitação.

---

## 1. Módulo de Autenticação e Perfis

### 1.1. Autenticação

* **História de Usuário 1 :** Como um **usuário**, eu quero me **cadastrar** com e-mail e senha para ter acesso seguro ao sistema.
    * **Critérios de Aceitação:**
        * O usuário deve fornecer um e-mail válido, cpf, e uma senha.
        * A senha deve ter no mínimo 8 caracteres, com pelo menos  1 caractere, 1 letra maiúscula e minúscula, 1 número.
        * O sistema deve validar se o e-mail ou cpf já está em uso e exibir uma mensagem de erro se estiver.
        * Após o cadastro, o usuário deve ser redirecionado para a página de login.

* **História de Usuário 2:** Como um **usuário**, eu quero **fazer login** com meu e-mail e senha para acessar minhas informações e funcionalidades.
    * **Critérios de Aceitação:**
        * O sistema deve verificar as credenciais (e-mail e senha).
        * Se as credenciais estiverem corretas,o sistema deve gerar um token de acordo com a role, e seguir para a tela inicial.
        * Se as credenciais estiverem incorretas, o sistema deve exibir uma mensagem de erro genérica ("E-mail ou senha inválidos").
* **História de Usuário 3:** Como um **usuário**, eu quero **fazer logout**  e sair da pagina
    * **Critérios de Aceitação:**
        * o sistema deve validar o token de acesso
        * se o token estiver correto para aquele usuário, ele deve ser deslogado e o token invalidado em seguida
        * Se o token for invalido para aquele usuário, deve emitir um erro

### 1.2. Gestão de Perfil

* **História de Usuário 4 :** Como um **usuário**, eu quero poder **visualizar e editar meu perfil** para manter minhas informações atualizadas.
    * **Critérios de Aceitação:**
        * O usuário deve ter acesso a uma página de perfil.
        * Nessa página, deve ser possível editar nome, email, senha, telefone, endereço, gênero, data de nascimento.
        * O sistema deve validar se o novo e-mail não está em uso por outro usuário.
        * As mudanças devem ser salvas no banco de dados.

* **História de Usuário 5:** Como um **usuário**, eu quero **recuperar minha senha**  informando cpf ou email
    * **Critérios de Aceitação:**
        * o sistema deve validar o email/cpf nos registros
        * Se validado enviar email com um código de 4 números para liberar a troca de senha
        * O usuário deve inserir esse código e caso válido, registrar a nova senha.
        * Ao registrar, deve realizar o login normalmente com a senha atualizada. 

---

## 2. Módulo de Eventos (para Organizadores)

* **História de Usuário 6 :** Como um **organizador**, eu quero **criar um evento** para que os usuários possam comprá-lo.
    * **Critérios de Aceitação:**
        * Apenas usuários com o papel de "organizador" ou "administrador" podem acessar o formulário de criação de evento.
        * O formulário deve conter campos para: imagem, nome, data, local, descrição.
        * Todos os campos, exceto a descrição, são obrigatórios.
        * O evento criado deve ser associado ao ID do organizador.

* **História de Usuário 7:** Como um **organizador**, eu quero **criar ou editar um lote** para que os usuários possam comprá-lo.
    * **Critérios de Aceitação:**
        * Apenas usuários com o papel de "organizador"  podem acessar o formulário de criação de lote.
        * O formulário deve conter campos para: nome, número de ingressos de cada lote e valor.
        * Todos os campos, são obrigatórios.
        * O número de ingressos deve ser um valor numérico positivo.
        * O lote criado deve ser associado ao ID do evento.

* **História de Usuário 8:** Como um **organizador**, eu quero **gerenciar meus eventos** para poder editar ou excluir eventos que eu criei.
    * **Critérios de Aceitação:**
        * O organizador deve ter um painel que liste apenas os eventos que ele criou, validando pelo token.
        * Cada evento na lista deve ter botões para "Editar" e "Excluir".
        * Ao clicar em "Editar", o usuário é levado ao formulário de edição pré-preenchido.
        * Ao clicar em "Excluir", o sistema deve pedir uma confirmação antes de remover o evento do banco de dados.

* **História de Usuário 9:** Como um **organizador**, eu quero **visualizar** o meu evento criado
    * **Critérios de Aceitação:**
        * Apenas usuários com o papel de "organizador" ou "administrador" podem visualizar o evento criado, em detalhes.
        * O retorno deve conter: dados gerais do evento, ingressos totais vendidos, valor total arrecadado, ingressos de cada lote vendido, ingressos de cada lote disponível, valor do ingresso de cada lote. 

* **História de Usuário 10 :** Como um **administrador**, eu quero **Validar** o evento criado, caso fuja das regras do site, poderá ser banido
    * **Critérios de Aceitação:**
        * Apenas usuários com o papel "administrador" podem validar e banir o evento criado, caso fuja das regras do site.
        * Se o evento estiver nos conformes ele será aprovado para a venda de ingressos
        * O organizador do evento receberá um email sobre a aprovação ou banimento do evento criado por ele. 
---
## 3. Módulo de Avaliação 

* **História de Usuário 11 :** Como um **usuário**, eu quero **avaliar** um evento que comprei, com uma nota e comentário
    * **Critérios de Aceitação:**
        * o Usuário deverá ter comprado um ingresso do evento, para poder avaliar de 1 - 5 estrelas e adicionar um comentário.
        * Apenas o campo de avaliação é obrigatório.
        * A avaliação e comentário devem ser associados ao ID do Usuário
        
* **História de Usuário 12 :** Como um **usuário**, eu quero **ver** a nota do evento e os comentários
    * **Critérios de Aceitação:**
        * O usuário deve estar logado para receber essa listagem e Nota de avaliação do evento
        * todas as Roles podem ter acesso a essa Listagem de comentários e nota do evento
        
* **História de Usuário  13:** Como um **Administrador**, eu quero **excluir** comentários e avaliações que fogem das regras do site
    * **Critérios de Aceitação:**
        * O administrador deve estar logado, e validado o token, para liberar a exclusão


## 4. Módulo de Compra (para Usuários)

* **História de Usuário 14:** Como um **usuário**, eu quero **visualizar todos os eventos disponíveis** para escolher qual me interessa.
    * **Critérios de Aceitação:**
        * A página inicial deve exibir uma lista de todos os eventos, paginado.
        * Cada item da lista deve mostrar o nome do evento, data, local e o valor dos ingressos do lote atual.
        * Os eventos devem ser exibidos em ordem cronológica (do mais próximo para o mais distante).

* **História de Usuário 15:** Como um **usuário**, eu quero **comprar um ingresso** para um evento que eu gostei.
    * **Critérios de Aceitação:**
        * O usuário deve poder clicar em um botão "Comprar Ingresso" na página do evento, selecionando a quantidade x de ingressos.
        * A compra só é possível se houver ingressos disponíveis.
        * A quantidade de ingressos disponíveis para o evento deve ser reduzida em x após a compra.
        * O sistema deve registrar a compra, associando o ingresso ao ID do usuário.
        * O usuário deve receber uma mensagem de sucesso após a compra pelo email.
        * O valor do ingresso deve ser somado no rendimento do evento
* **História de Usuário 16:** Como um **usuário**, eu quero **desistir do ingresso** para um evento que eu já realizei a compra.
    * **Critérios de Aceitação:**
        * O usuário deve poder clicar em um botão "desistir do ingresso" na pagina do ingresso.
        * A desistência só é permitida no prazo de 7 dias corridos. 
        * A quantidade de ingressos disponíveis para o evento deve ser aumentada em 1 após a desistencia.
        * O sistema deve registrar a desistência, associando ao registro de compra uma flag de desistencia.
        * O usuário deve receber uma mensagem de sucesso após a desistencia pelo email.
        * o usuário receberá um saldo na conta do ingresso da desistência. 
        * O valor do ingresso deve ser subtraído no rendimento do evento.

* **História de Usuário 17:** Como um **usuário**, eu quero **ver um histórico de minhas compras** para saber quais ingressos eu já garanti.
    * **Critérios de Aceitação:**
        * Deve haver uma página ou seção no perfil do usuário que liste todas as suas compras.
        * A lista deve mostrar o nome do evento, a data, a data da compra e o valor do ingresso.

---

## 4. Módulo de Comunicação 

* **História de Usuário 18:** Como um **usuário**, eu quero **receber um email** para confirmar a realização do cadastro
    * **Critérios de Aceitação:**
    * **Critérios de Aceitação:**
    * O e-mail deve ser enviado automaticamente para o endereço fornecido no cadastro.
    * O envio deve ocorrer imediatamente após o usuário submeter o formulário de cadastro com sucesso.
    * O e-mail deve ter um assunto claro, como "Confirmação de Cadastro - TicketHub".
    * O corpo do e-mail deve conter uma mensagem de boas-vindas e informar que a conta foi criada com sucesso.

* **História de Usuário 19:** Como um **usuário**, eu quero **receber um email** para confirmar a compra do ingresso
    * **Critérios de Aceitação:**
        * O e-mail deve ser enviado automaticamente para o endereço do usuário que realizou a reserva.
        * O envio deve ocorrer imediatamente após a reserva do ingresso ser processada com sucesso.
        * O e-mail deve ter um assunto claro, como "Confirmação de Reserva de Ingresso - TicketHub".
        * O corpo do e-mail deve conter os detalhes da reserva: nome do evento, data, local e a confirmação de que o ingresso foi reservado em nome do usuário.
        * O e-mail deve ser enviado apenas para a reserva de ingressos e não para a desistência.

* **História de Usuário 20:** Como um **usuário**, eu quero **receber um email** para confirmar a desistência do ingresso
    * **Critérios de Aceitação:**
        * O e-mail deve ser enviado automaticamente para o endereço do usuário que desistiu do ingresso.
        * O envio deve ocorrer imediatamente após a ação de "cancelar reserva" ser realizada com sucesso pelo usuário.
        * O e-mail deve ter um assunto claro, como "Confirmação de Cancelamento de Reserva - TicketHub".
        * O corpo do e-mail deve conter uma mensagem confirmando o cancelamento e os detalhes do evento que foi cancelado.
        * Após o cancelamento, a quantidade de ingressos disponíveis para o evento deve ser atualizada.


---
