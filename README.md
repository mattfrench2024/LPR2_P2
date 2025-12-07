# 🏥 Sistema de Cadastro de Pacientes – Hospital Java

Este projeto consiste em uma aplicação desktop simples para **cadastro, consulta e pesquisa de pacientes** de um hospital.  
O sistema foi construído com foco em **usabilidade, validação de dados, persistência em banco de dados** e **interface amigável**.

---

## 🚀 Funcionalidades Principais

A tela principal do sistema oferece as seguintes operações:

### ✔️ **1. Incluir**
- Realiza o cadastro de um paciente no banco de dados.
- Valida tipos de dados: `String`, `int`, `float`.
- Após inserção, retorna mensagem de sucesso/erro.

### ✔️ **2. Limpar**
- Limpa todos os campos do formulário.
- Não altera o banco de dados.

### ✔️ **3. Apresenta Dados**
- Exibe todos os pacientes cadastrados.
- Mostra os dados em um `JOptionPane` organizado.

### ✔️ **4. Pesquisar**
- Pesquisa pacientes pelo campo **Nome** utilizando `LIKE` no banco de dados.
- Retorna resultados formatados em `JOptionPane`.

### ✔️ **5. Créditos**
- Exibe um painel com os nomes da equipe responsável pelo projeto.

### ✔️ **6. Sair**
- Fecha o sistema de forma segura.

---

## 📋 Requisitos Funcionais

A tela deve possuir os seguintes campos, todos obrigatórios no banco de dados:

| Campo | Tipo | Descrição |
|------|------|-----------|
| ID | int (auto increment) | Identificador único |
| Nome | String | Nome completo do paciente |
| Idade | int | Idade em anos |
| Peso | float | Peso em kg |
| Altura | float | Altura em metros |

---

## 🗂️ Requisitos Técnicos

### 📌 Banco de Dados
**Tabela: pacientes**

| Nome da coluna | Tipo | Restrição |
|----------------|------|-----------|
| id | INT | PK, AUTO_INCREMENT |
| nome | VARCHAR | NOT NULL |
| idade | INT | NOT NULL |
| peso | FLOAT | NOT NULL |
| altura | FLOAT | NOT NULL |

Exemplo SQL:
```sql
CREATE TABLE pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT NOT NULL,
    peso FLOAT NOT NULL,
    altura FLOAT NOT NULL
);
