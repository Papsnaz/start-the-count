# core

## Documentação

- Após rodar a aplicação, a documentação estará disponível em http://localhost:8080/swagger-ui/index.html.

- Com exceção dos endpoints `/usuarios/register` e `/usuarios/login`, todos os demais possuem autenticação.

- O token pode ser gerado na criação de um novo usuário (http://localhost:8080/usuarios/register) ou na autenticação de um usuário já existente (http://localhost:8080/usuarios/login).

## Tecnologias

| Tecnologia               | Versão |
| ------------------------ | ------ |
| [Java][java]             | 19     |
| [Gradle][gradle]         | 7.6    |
| [PostgreSQL][postgreSQL] | 15     |

[java]:       <https://java.com>            "Java"
[gradle]:     <https://gradle.org/>         "Gradle"
[postgreSQL]: <https://www.postgresql.org/> "PostgreSQL"
