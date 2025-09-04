# Escopo do Projeto: Ticketeria Online

## 1. Visão Geral
Este documento define o escopo da primeira versão do projeto, uma plataforma online para a criação e reserva de ingressos para eventos. O foco inicial é criar um sistema funcional que atenda às necessidades básicas de organizadores e usuários.

---

## 2. Funcionalidades Incluídas (MVP)

### 2.1. Usuários
* **Autenticação**: Permitir que usuários se cadastrem, façam login e logout usando e-mail e senha.
    * Possibilidade de recuperação de senha, por meio da inserção do email
    * Utilização de token de acesso, com um prazo de validade 
* **Perfis**: Usuários podem visualizar e editar informações básicas do seu perfil.

### 2.2. Papéis de Usuário e Permissões
* **Usuário Comum**:
    * Pode navegar por todos os eventos públicos.
    * Pode comprar ingressos para eventos.
    * Pode visualizar seu histórico de compras.
* **Organizador de Eventos**:
    * Tem todas as permissões de um "Usuário Comum".
    * Pode **criar**, **editar** e **excluir** eventos.
    * Pode gerenciar os ingressos e a quantidade disponível de eventos criados por ele.
* **Administrador do Sistema**:
    * Tem todas as permissões de "Organizador de Eventos".
    * Pode **visualizar**, **editar** e **excluir** qualquer evento na plataforma, independente de quem o criou.
    * Pode visualizar uma lista de todos os usuários cadastrados.

### 2.3. Eventos (para Organizadores)
* **Criação de Eventos**: Organizadores podem criar novos eventos, preenchendo as seguintes informações:
    * Imagem do evento
    * Nome do evento.
    * Data e hora.
    * Local (endereço em texto simples).
    * Descrição do evento.
    * Número total de ingressos disponíveis, divididos em lotes e valores.
    
* **Gestão de Eventos (Organizador)**: Organizadores podem gerenciar apenas os eventos que eles próprios criaram.
* **Gestão de Eventos (Admin)**: O Administrador pode gerenciar todos os eventos na plataforma.


### 2.3. Funcionalidades de Comunicação
* **Notificações**: haverá envio de e-mails para confirmar cadastro, compra ou outras ações.
* **Comentários e Avaliações**: Usuários poderão avaliar ou comentar sobre eventos.

### 3.4. Tipos de Usuários
* **Papéis Avançados**: A distinção será simples (usuário comum vs. organizador). Não haverá papéis como "administrador do site", "moderador", etc.

---

## 3. Funcionalidades Excluídas (fora do escopo do MVP)

### 3.1. Pagamentos e Transações
* **Processamento de Pagamento**: A plataforma não irá processar pagamentos. A reserva de ingressos será puramente lógica, sem valor monetário.
* **Emissão de Boleto/PIX**: Não haverá geração de códigos de pagamento.

### 3.2. Funcionalidades de Comunicação
* **Sistema de Mensagens**: Não haverá chat ou mensagens entre usuários e organizadores.

### 3.3. Funcionalidades de Conteúdo e Social
* **Compartilhamento Social**: Não haverá botões para compartilhar eventos em redes sociais.
