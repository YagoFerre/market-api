
# Market API

Este projeto é uma aplicação Java Spring Boot que fornece uma API para gerenciar um mercado virtual, incluindo recursos para autenticação de usuários, gerenciamento de produtos e armazenamento de arquivos.

## Requisitos

- Java 17
- Maven 3.8+
- Docker (para execução do banco de dados PostgreSQL)

## Configuração do Projeto

### Banco de Dados

O projeto utiliza PostgreSQL como banco de dados. Para facilitar a configuração, um arquivo `docker-compose.yml` é fornecido, permitindo a inicialização rápida do banco de dados em um contêiner Docker.

Para iniciar o banco de dados, execute:

```bash
docker-compose up -d
```

### Configurações de Aplicação

As propriedades de configuração são gerenciadas através de arquivos `application.properties` ou `application.yml`. As configurações de armazenamento de arquivos são habilitadas através da anotação `@EnableConfigurationProperties(FileStorageProperties.class)`.

### Compilação e Execução

Para compilar e executar o projeto, utilize os seguintes comandos Maven:

```bash
mvn clean install
mvn spring-boot:run
```

## Estrutura do Projeto

- `MarketApiApplication`: Classe principal que inicia a aplicação Spring Boot.
- `AuthenticationController`: Controlador responsável pela autenticação de usuários.
- `ProdutoController`: Controlador para operações relacionadas a produtos.
- `UsuarioController`: Controlador para operações relacionadas a usuários.
- `ApplicationControllerAdvice`: Classe para tratamento de exceções na aplicação.
- `TokenService`: Serviço responsável pela geração e validação de tokens JWT.
- `SecurityConfiguration`: Configuração de segurança da aplicação.
- `FileService`: Serviço para gerenciamento de arquivos.
- `ProdutoService` e `UsuarioService`: Serviços de domínio para gerenciamento de produtos e usuários.

## Funcionalidades

### Autenticação

- **Registro de Usuário**: Permite que novos usuários se registrem na plataforma.
- **Login**: Autenticação de usuários com retorno de token JWT para acesso a recursos protegidos.

### Gerenciamento de Produtos

- **Criação, Atualização e Exclusão**: Operações CRUD para produtos.
- **Listagem de Produtos**: Listar produtos com suporte a paginação.

### Armazenamento de Arquivos

- **Upload de Arquivos**: Suporte para upload de avatares de usuários e imagens de produtos.

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para o desenvolvimento da aplicação.
- **Spring Security**: Implementação de segurança com suporte a JWT.
- **Spring Data JPA**: Interface de persistência de dados.
- **PostgreSQL**: Banco de dados relacional.
- **Docker**: Ambiente de contêiner para execução do banco de dados.
- **MapStruct**: Mapeamento entre classes DTO e entidades.

## Contribuição

Sinta-se à vontade para contribuir com este projeto. Abra uma issue ou envie um pull request para melhorias ou correções.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).

---

Para mais informações, consulte a [documentação oficial do Spring Boot](https://spring.io/projects/spring-boot) e o [Guia de Referência do Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/).
```
Este `README.md` fornece uma visão geral do projeto, configuração e execução, bem como uma descrição das funcionalidades e tecnologias utilizadas.
