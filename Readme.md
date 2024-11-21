```markdown
# LiterAlura - Catálogo de Livros

Bem-vindo ao **LiterAlura**, um projeto que visa construir um catálogo de livros, utilizando Java, Spring Boot, PostgreSQL e a API **Gutendex**, que disponibiliza dados de mais de 70 mil livros. O principal objetivo deste desafio é praticar o consumo de APIs e a persistência de dados em um banco de dados relacional.

## 📝 Descrição do Projeto

O **LiterAlura** permite:

1. Buscar livros diretamente da API Gutendex e adicioná-los ao banco de dados.
2. Listar os livros registrados no banco.
3. Visualizar uma lista de autores e informações adicionais como ano de nascimento e falecimento.
4. Consultar autores vivos em um determinado ano.
5. Listar livros registrados em um idioma específico.

## 🚀 Tecnologias Utilizadas

- **Java**
- **Spring Boot** (Spring Data JPA)
- **PostgreSQL**
- **Gutendex API**

## 🔧 Pré-requisitos

1. **Configuração no Spring Initializer**:
   - Linguagem: Java
   - Ferramenta de Build: Maven
   - Versão do Spring Boot: Compatível com as dependências
   - Dependências:
     - **Spring Data JPA**: Facilita a persistência de dados.
     - **PostgreSQL Driver**: Conexão com o banco de dados PostgreSQL.

2. **Banco de Dados PostgreSQL**:
   - Acesse a página oficial de downloads [aqui](https://www.postgresql.org/download/) para instalar o PostgreSQL no seu sistema operacional.

3. **API Gutendex**:
   - Consulte a documentação da API diretamente em [Gutendex API](https://gutendex.com).

## 📄 Funcionalidades

### 1. Buscar Livro pelo Título
- Realiza uma consulta na API Gutendex e insere o livro no banco de dados.
- Exemplo:
  - Título: **Dom Casmurro**
  - Resultado: Título, autor, idioma e número de downloads.

### 2. Listar Livros Registrados
- Mostra todos os livros armazenados no banco de dados.

### 3. Listar Autores
- Exibe a lista de autores registrados, com detalhes como:
  - Nome
  - Ano de nascimento
  - Ano de falecimento
  - Livros associados

### 4. Listar Autores por Ano
- Retorna autores que estavam vivos em um ano específico.
- Exemplo:
  - Ano: 1800
  - Resultado: Jane Austen.

### 5. Listar Livros por Idioma
- Permite consultar livros em quatro idiomas:
  - Português (PT)
  - Inglês (EN)
  - Espanhol (ES)
  - Francês (FR)
- Exemplo:
  - Idioma: PT
  - Resultado: **Dom Casmurro**.

## 🗂 Estrutura do Projeto no Trello

Organizamos as etapas do projeto no Trello. O fluxo sugerido inclui:

1. **Configuração do Ambiente**.
2. **Consumo da API Gutendex**.
3. **Implementação do Catálogo com Java e Spring Boot**.
4. **Persistência de Dados no Banco**.

Acesse o [Trello do Projeto](#) para obter mais detalhes.

## 🌟 Opções Extras

- Gerar estatísticas com os dados dos livros.
- Criar um ranking com os 10 livros mais baixados.
- Implementar busca de autor pelo nome.
- Criar consultas personalizadas para listar autores.

```