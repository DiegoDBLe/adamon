# Adamon
## Descrição
Este projeto, chamado Adamon, foi desenvolvido durante o módulo de Teste Automatizado do curso Ada, oferecido pela empresa Americanas. O Adamon é um sistema que permite o cadastro e gerenciamento de monstros fictícios com diferentes atributos, como força, nome, ataque, inteligência, poder, velocidade, foto e vida.

O objetivo deste projeto foi aplicar conceitos e práticas de testes automatizados em uma aplicação Java com Spring Boot e banco de dados PostgreSQL.

## Tecnologias utilizadas
* Docker: utilizado para gerar a imagem do banco de dados PostgreSQL.
* Java 17: linguagem de programação utilizada para desenvolver a aplicação.
* Spring Boot: framework utilizado para facilitar o desenvolvimento da aplicação.
* Lombok: biblioteca utilizada para reduzir a verbosidade do código.
* Dbvear: ferramenta utilizada para modelagem e visualização do banco de dados.
* Postman: ferramenta utilizada para testar as requisições da API.
* JUnit: framework utilizado para escrever e executar testes automatizados.
* IntelliJ IDEA 2022.3.1

### Configuração do ambiente
Para executar a aplicação é necessário ter as seguintes ferramentas instaladas no seu computador:

* Docker: [https://www.docker.com/]
* Java 17: [https://www.oracle.com/java/technologies/downloads/]
* Postman: [https://www.postman.com/]
Após a instalação das ferramentas acima, siga os passos abaixo:

1. Clone o repositório do GitHub:
``git clone https://github.com/<SEU_USUARIO>/adamon.git``
2. Acesse o diretório do projeto:
``cd adamon``
3. Execute o comando para gerar a imagem do PostgreSQL:
``docker-compose up -d``
4. Importe o projeto na sua IDE preferida e execute a classe principal AdamonApplication.

5. Para testar as requisições da API, importe o arquivo adamon.postman_collection.json no Postman.

### Execução dos testes
Os testes automatizados foram escritos utilizando o framework JUnit. Para executá-los, siga os passos abaixo:

1. Abra a sua IDE e acesse o diretório do projeto.

2. Execute o comando abaixo para executar os testes:

``./mvnw test``
3. Aguarde até que todos os testes sejam executados.
### Contribuição
Este projeto foi desenvolvido como parte do curso Ada da empresa Americanas. No entanto, sinta-se livre para fazer um fork deste repositório e contribuir para o projeto. Caso encontre algum bug ou queira sugerir alguma melhoria, abra uma issue ou submeta um pull request.
