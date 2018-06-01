# FS-PROJECT

Projeto base para compra de produtos.
O sistema consiste na criação de compras e clientes.

O arquivo DBSeeder já cria automaticamente as tabelas do banco de dados e insere dados ficticios, mas para isso é necessário que já esteja criado um schema "fs_database".

O projeto base está configurado para rodar na porta localhost:8080

### Execução

Para executar o software execute como Spring Boot Application o arquivo \FS-Project\src\main\java\br\com\fs\FsProjectApplication.java.


### Instalação

Para gerar um pacote e fazer upload em um servidor de aplicação faça o seguinte comando
```
mvn install
```


### Tecnologias utilizadas

- Java 8
- Spring Boot 2.0.2.RELEASE
- Spring Data (compatible version)
- Spring MVC (compatible version)
- JPA (compatible version)
- Model Mapper 2.0.0

### Observações

- A primeira execução irá retornar erros, pois as tabelas serão criadas pelo JPA.
- De START primeiro no projeto FS-Project-Licenca para geração de licenças.

