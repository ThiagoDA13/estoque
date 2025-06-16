# Visão Geral
Aplicação FullStack para gestão de estoque com:

**Backend:** Java Spring Boot (v3.1.0)  
**Frontend:** Vue.js (v3)  
**Banco de Dados:** H2 (dev) / MySQL (prod)  

## Funcionalidades Principais

### Gestão de Produtos
- Cadastro completo (código, descrição, tipo, valor, quantidade)
- Controle de estoque com validação de saldo
- Filtros por tipo de produto

### Movimentações
- Registro de entradas/saídas
- Histórico com filtro por produto
- Bloqueio de saídas sem estoque suficiente

### Relatórios
- Cálculo de lucro por produto
- Visualização de movimentações por período

## Instalação

### Backend:
```bash
cd backend
mvn spring-boot:run
```

### Frontend:
```bash
cd frontend
npm install
npm run dev
```

## Endpoints Principais

| Método | Endpoint                 | Descrição                      |
|--------|---------------------------|---------------------------------|
| GET    | /api/produtos             | Lista todos os produtos         |
| POST   | /api/movimentos           | Registra nova movimentação      |
| GET    | /api/relatorios/lucro     | Lucro por produto               |

## Tecnologias
- Java 17 + Spring Boot
- Vue 3 + Vue Router
- Axios para chamadas HTTP
- H2 Database (em memória)

## Configuração
Edite `application.properties` para:
- Configurar conexão com banco de dados
- Definir porta do servidor
- Ajustar políticas de CORS

**Importante:** Para produção, configure um banco de dados persistente e atualize as credenciais no arquivo de configuração.
