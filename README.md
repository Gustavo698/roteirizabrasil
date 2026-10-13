# Roteiriza Brasil — Descubra o Brasil do jeito certo

Backend Spring Boot (Java 21) + SQL Server do TCC Roteiriza Brasil.

## Banco de dados
1. Abra `bd_roteiriza.sql` no SQL Server Management Studio e execute (F5).
2. Cria o banco `bd_roteiriza` com 9 tabelas + dados de exemplo.

## Configurar e rodar
Edite `src/main/resources/application.properties` se precisar (usuario/senha do SQL Server):
```properties
spring.datasource.url=jdbc:sqlserver://localhost;databaseName=bd_roteiriza;encrypt=true;trustServerCertificate=true;
spring.datasource.username=sa
spring.datasource.password=@ITB123456
```
```powershell
./mvnw spring-boot:run
```

## Endpoints
- `GET /api/v1/destinos` — listar destinos
- `GET /api/v1/atracoes?destinoId=1` — atrações do destino
- `GET /api/v1/guias?destinoId=1` — guias do destino
- `POST /api/v1/usuarios` — cadastro `{nome, email, senha, telefone}`
- `POST /api/v1/roteiros` — criar roteiro
- `POST /api/v1/roteiros/{id}/atividades` — adicionar atividade (recalcula orçamento)
- `POST /api/v1/avaliacoes/guias` e `/api/v1/avaliacoes/atracoes` — nota 1 a 5, sem duplicar
