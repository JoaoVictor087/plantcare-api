# 🌱 PlantCare API: Sistema de Monitoramento e Recomendação para Cultivo

## 💡 Proposta Tecnológica (Visão Geral do MVP - Sprint 1)

A PlantCare API é o módulo de backend desenvolvido em Java com Spring Boot e JPA para o Challenge Oracle (2TDS). O objetivo é fornecer uma base de dados sólida para um sistema de gestão de plantas.

O problema central que buscamos solucionar é a gestão descentralizada de dados de cultivo. Nossa API centraliza o gerenciamento de Usuários e o cadastro de suas Plantas, sendo a fundação para futuras integrações com sensores, alertas e recomendações.

### Público-Alvo

- **Clientes (Pagantes)**: Pequenos e médios produtores rurais ou empresas de agricultura de precisão que necessitam de um sistema de gestão de dados centralizado e robusto.

- **Consumidores (Usuários Finais)**: Técnicos agrícolas, agrônomos e cultivadores domésticos que utilizarão a aplicação mobile para monitorar suas plantas.

## 🎥 Link para o Vídeo da Proposta Tecnológica

[Link do YouTube](https://www.youtube.com/watch?v=AWLglePin50&feature=youtu.be "Proposta Tecnológica")

## 👥 Integrantes e Responsabilidades

Este projeto foi desenvolvido por:

| Nome Completo | RM |
|---------------|-----|
| João Victor Alves da Silva | 559726 |
| Vinicius Kenzo Tocuyosi | 559982 |
 | Juan Pablo Rebelo Coelho | 560445 |

## 🛠️ Instruções de Execução da Aplicação (API Java/Spring Boot)

### Pré-requisitos
- Java JDK 25
- Maven
- Azure CLI instalado e configurado
- Banco de Dados Oracle (Autonomous Database ou instalação local)

---

### Configuração do Banco de Dados

⚠️ **ARQUIVO DE CONFIGURAÇÃO (application.properties)**

O arquivo de configuração com as credenciais do banco de dados
(`src/main/resources/application.properties`) está alterado para
utilizar variáveis de ambiente.

O professor responsável pela avaliação receberá o arquivo de ambiente
com as credenciais de acesso ao Oracle Database separadamente para que
possa executar a aplicação e validar a persistência dos dados.

Para rodar o projeto em outro ambiente, crie o arquivo `.env` na raiz
do projeto e preencha com as suas próprias credenciais:

```dotenv
DB_URL=db_url
DB_USERNAME=db_username
DB_PASSWORD=db_password
JWT_SECRET=jwt_secret
```

---

### Execução Local

1. Clone o repositório e navegue até a pasta raiz do projeto:
```bash
git clone <repo-url>
cd <project-folder>
```

2. Compile o projeto:
```bash
./mvnw clean install -DskipTests
```

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

A API estará acessível em `http://localhost:8080/api`.

---

### Deploy na Azure (App Service)

> ⚠️ Os recursos Azure (Resource Group, App Service Plan e Web App)
> já estão criados e configurados. Os passos abaixo referem-se
> apenas ao build e deploy da aplicação.

1. Clone o repositório e navegue até a pasta raiz do projeto:
```bash
git clone <repo-url>
cd <project-folder>
```

2. Faça login na Azure:
```bash
az login
```

3. Gere o JAR da aplicação:
```bash
./mvnw clean package -DskipTests
```

4. Realize o deploy para o Azure App Service:
```bash
az webapp deploy \
  --name <app-name> \
  --resource-group <resource-group-name> \
  --src-path target/<seu-arquivo>.jar \
  --type jar
```

A API estará acessível em `https://<app-name>.azurewebsites.net/api`.

---

### 🧪 Testando os Endpoints

Após o deploy, utilize o Postman ou qualquer cliente HTTP para
testar os endpoints. A URL base da aplicação na Azure é:

```
https://<app-name>.azurewebsites.net/api
```
## 🗃️ Documentação e Testes (Pasta docs/)

Para facilitar a correção, toda a documentação e os arquivos de teste foram organizados na pasta `docs/` na raiz do projeto:

| Arquivo/Item                      | Conteúdo |
|-----------------------------------|----------|
| Documentação Java Sprint01.pdf    | Documentação Completa do projeto, incluindo o Cronograma, o Diagrama MER, e o Diagrama UML (Diagrama de Classes de Entidade). |
| Documentação Java Sprint02.pdf    | Documentação Completa do projeto, incluindo o Cronograma, o Diagrama MER, e o Diagrama UML (Diagrama de Classes de Entidade). |
| PlantCare.postman_collection.json | Collection do Postman com todas as requisições de teste prontas para os endpoints implementados. |
| EndpointsAPI.yaml                 | Endpoints no padrão OpenAPI|

## 🖼️ Imagens dos Diagramas

Os diagramas concebidos para a modelagem do projeto estão a seguir (e em melhor qualidade na documentação PDF).

### Diagrama Entidade-Relacionamento (DER - Modelo Lógico/Físico)

Este é o Diagrama Amarelo que representa a estrutura relacional das tabelas.


![Diagrama DER](./docs/images/Diagrama_Amarelo.png "Diagrama DER")

### Diagrama de Classes de Entidade (Modelo UML - Camada de Domínio)

Este diagrama (presente na documentação como "Diagrama UML") representa o Mapeamento Objeto-Relacional (ORM) das classes Java.


![Diagrama UML](./docs/images/DIAGRAMA-UML-sprint-1.png "Diagrama UML")

## 🌐 Endpoints da API Implementados (Nível de Maturidade 1 - Richardson)

A API segue o Modelo de Maturidade Nível 3, utilizando o HTTP para diferentes ações (POST, PUT, GET, DELETE).

## 3.6) Listagem de Endpoints (Documentação da API)

A `base_url` padrão para a API é `http://localhost:8080/api`.

### Recurso: Autenticação (`/auth`)
Endpoints públicos para registro e login.

| Método HTTP | Rota Completa | Descrição |
|:--- |:--- |:--- |
| POST | `/auth/criarConta` | Cria um novo usuário. |
| POST | `/auth/login` | Autentica um usuário e retorna um Access Token e Refresh Token. |
| POST | `/auth/refresh` | Gera um novo Access Token usando um Refresh Token válido. |

### Recurso: Usuário (`/usuario`)
Endpoints protegidos para gerenciar o usuário autenticado.
**Requer Autenticação: `Bearer Token`**

| Método HTTP | Rota Completa | Descrição |
|:--- |:--- |:--- |
| PUT | `/usuario/me` | Atualiza o nome do usuário que está autenticado. |

### Recurso: Plantas (`/plantas`)
Endpoints protegidos para o CRUD de plantas do usuário autenticado.
**Requer Autenticação: `Bearer Token`**

| Método HTTP | Rota Completa | Descrição |
|:--- |:--- |:--- |
| POST | `/plantas` | Cadastra uma nova planta (associada ao usuário do token). |
| GET | `/plantas` | Lista todas as plantas do usuário (associadas ao usuário do token).|
| GET | `/plantas/{id}` | Busca os detalhes de uma planta específica pelo ID. |
| PUT | `/plantas/{id}` | Atualiza os dados de uma planta específica. |
| DELETE | `/plantas/{id}` | Remove uma planta pelo seu ID. |

### Recurso: Sensor (`/sensor`)
Endpoints para gerenciamento de sensores.

| Método HTTP | Rota Completa | Descrição |
|:--- |:--- |:--- |
| POST | `/sensor/addSensor` | Associa um novo sensor a uma planta. |
| DELETE | `/sensor/removeSensor` | Remove um sensor pelo seu ID (enviado no *Body*). |
| GET | `/sensor/listarSensores` | Lista os sensores de uma planta (ID da planta enviado no *Body*). |

**Observação (Sensor):** Os endpoints `DELETE /removeSensor` e `GET /listarSensores` esperam o ID (do sensor ou da planta, respectivamente) no **corpo (Body)** da requisição, em formato `raw`.

### Recurso: Leitura (`/leitura`)
Endpoint para ingestão de dados dos sensores.

| Método HTTP | Rota Completa | Descrição |
|:--- |:--- |:--- |
| POST | `/leitura` | Registra uma nova leitura de um sensor (ex: umidade, temperatura). |