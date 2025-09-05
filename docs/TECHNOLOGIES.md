
## Design Arquitetural

| Camada/Componente  | Tecnologia                | Justificativa |
| -------------      |  -------------            |:-------------:|
| Backend            | Java com Spring Boot      |Spring Boot é o framework de facto para o desenvolvimento de aplicações Java, simplificando a configuração e acelerando o desenvolvimento de APIs RESTful|
| Segurança          | Spring Security com JWT   |O Spring Security gerencia a autenticação e autorização. O uso de JWT permitirá uma arquitetura stateless, onde a autenticação do usuário é validada a cada requisição através de um token, o que é ideal para APIs REST|
|Mapeamento de Dados | Spring Data JPA com Hibernate    |Permite interagir com o banco de dados usando objetos Java, eliminando a necessidade de escrever SQL manualmente|
|Comunicação (APIs) | REST APIs | O padrão de mercado para a comunicação entre o frontend e o backend|
| Serviço de E-mail | JavaMailSender | Integrado ao Spring Boot, facilita o envio de e-mails para confirmações e notificações aos usuário|