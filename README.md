# API de Gerenciamento de Codinomes Baseado no Desafio Uol Host

Esta API oferece funcionalidades para o gerenciamento de jogadores, permitindo a recuperação, busca por ID, exclusão e criação de novos jogadores. Os jogadores são compostos por nome, email, telefone e pertencem a grupos como Vingadores ou Liga da Justiça. Desafio: https://github.com/uolhost/test-backEnd-Java

## Tecnologias
- **Spring (Boot, JPA)**
- **Ferramentas para Deserialização de JSON e XML**
- **Docker**
- **Design patterns**


## Endpoints Disponíveis

### Recuperar todos os jogadores

- **Método:** GET
- **Endpoint:** `/jogadores`
- **Descrição:** Retorna todos os jogadores cadastrados no sistema.

### Recuperar jogador por ID

- **Método:** GET
- **Endpoint:** `/jogadores/{id}`
- **Descrição:** Retorna um jogador específico com base no ID fornecido.

### Deletar jogador por ID

- **Método:** DELETE
- **Endpoint:** `/jogadores/{id}`
- **Descrição:** Remove um jogador com base no ID fornecido.

### Criar um novo jogador

- **Método:** POST
- **Endpoint:** `/jogadores/novo-jogador`
- **Descrição:** Cria um novo jogador com os seguintes parâmetros no corpo da requisição:
  
```
{
  "nome": "Nome do Jogador",
  "email": "jogador@example.com",
  "telefone": "123456789",
  "grupo": "VINGADORES"
}
```
Grupo deve ser VINGADORES ou LIGA_DA_JUSTICA

Ao criar um novo jogador, é feita a verificação de acordo com o grupo selecionado pelo jogador, e então ocorre a verificação de codinomes disponiveis para realizar a ação. Caso não possua codinomes disponíveis a api retornara uma mensagem avisando. 
`A lista escolhida não possui mais codinomes disponíveis!`

## Configuração Padrão

- **URL Base:** `http://localhost:8080`
- **Banco de Dados:** H2 (em memória)

## Requisitos

Certifique-se de ter o Java e as dependências do projeto instaladas para executá-lo.

## Executando o Projeto

1. Clone o repositório.
2. Configure as dependências e o ambiente de acordo com o arquivo de configuração fornecido.
3. Execute a aplicação.
4. Use as rotas mencionadas acima para interagir com a API.

