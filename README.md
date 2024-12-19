# Clean Architecture Project

Este projeto segue os princípios da **Clean Architecture**, garantindo alta modularidade, separação de responsabilidades e facilidade de manutenção.

## Domínios

O projeto abrange os seguintes domínios principais:

1. **Customer**: Gerenciamento de clientes, incluindo cadastro, atualização e consultas
2. **Card**: Gerenciamento de cartões, como cadastro, validação e associação a clientes.
3. **Delivery**
4. **Charge**

## Estrutura do Projeto

O projeto está organizado em camadas, seguindo os princípios da Clean Architecture:

- **Domain**: Contém as entidades principais, interfaces de repositórios e casos de uso. Esta camada é independente de frameworks e bibliotecas.
- **Application**: Implementação dos casos de uso e regras de negócio, coordenando as interações entre a camada de domínio e as externas.
- **Infrastructure**: Implementação de repositórios, serviços externos e integrações com APIs ou bancos de dados.
- **Presentation**: IAPIs REST.

## Requisitos

- **Linguagem**: Java 17
- **Framework**: Spring Boot
- **Banco de Dados**: MySql
- **Outras Dependências**:
  - Spring Data JPA
  - Spring Boot
  - Lombok
  - Migration

## Configuração

1. Compile e execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```

## Testes

O projeto inclui testes unitários e de integração para garantir a qualidade do código:

1. Execute os testes:
   ```bash
   ./mvnw test
   ```

2. Confira a cobertura de testes:
   ```bash
   ./mvnw jacoco:report
   ```
   
**Autor**: Kleberson dos santos silva
