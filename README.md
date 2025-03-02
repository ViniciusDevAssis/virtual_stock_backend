Este repositório contém o backend da aplicação desenvolvido com **Spring Boot** na versão **Java 17 (LTS)**.

## 📦 Requisitos
Antes de iniciar, certifique-se de ter instalado:
- [Java 17 (LTS)](https://www.azul.com/downloads/?version=java-17-lts&package=jdk#zulu)
- [Maven](https://maven.apache.org/) (caso não utilize o wrapper do Maven)

## 🔧 Como Rodar o Projeto

A aplicação estará configurada para rodar no **perfil de teste**, utilizando o banco de dados **H2**. O **PostgreSQL** será utilizado apenas para produção.

1. Clone este repositório:
   ```sh
   git clone git@github.com:ViniciusDevAssis/virtual_stock_backend.git
   ```
2. Acesse a pasta do projeto:
   ```sh
   cd nome-do-projeto
   ```
3. Inicie a aplicação na sua IDE ou com o comando:
   ```sh
   ./mvnw spring-boot:run
   ```
   ou, se estiver usando o Maven instalado no sistema:
   ```sh
   mvn spring-boot:run
   ```

4. O servidor iniciará em:
   ```
   http://localhost:8080
   ```

### Extra
- [Diagrama do Modelo de Dominio](https://pt.scribd.com/document/834321933/Modelo-de-Dominio)

Feito por [ViniciusDevAssis](https://github.com/ViniciusDevAssis) 🚀

