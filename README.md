# 🌌 SpaceIJP Java

**Autores:** Iago Nunes, João Gabriel Prado e Pedro Lucas  
![Java](https://img.shields.io/badge/Linguagem-Java-orange)
![Banco de Dados](https://img.shields.io/badge/BD-MySQL-blue)
![Interface](https://img.shields.io/badge/Interface-Swing-yellow)
![Status](https://img.shields.io/badge/Status-Em_desenvolvimento-red)

---

## 🚀 Visão Geral do Projeto

O **SpaceIJP** é um simulador e sistema de gerenciamento de lançamentos espaciais desenvolvido em **Java**.  
O projeto combina **programação orientada a objetos (POO)**, **interface gráfica (Swing)** e **integração com banco de dados MySQL**, permitindo criar, administrar e lançar foguetes de forma interativa.

O objetivo é **educacional e técnico**, demonstrando conceitos avançados de Java, como DAO, herança, polimorfismo e encapsulamento, aplicados em um contexto prático e divertido.

---

## 🧠 Funcionalidades Principais

### 🛰️ Módulos do Sistema
- **Foguetes:** cadastro, combustível, velocidade e design.  
- **Destinos:** planetas e luas com dados de distância, gravidade e pressão.  
- **Cargas:** definição de peso e tipo de material transportado.  
- **Funcionários:** engenheiros, cientistas e outros papéis administrativos.  
- **Sensores:** leitura e monitoramento de variáveis da missão.  
- **Base de Lançamento:** central de controle para autorizar e iniciar missões.

### ⚙️ Recursos Técnicos
- CRUD completo com **DAO (Data Access Object)**  
- Conexão com **MySQL** via `mysql-connector-j`  
- Interface desenvolvida com **Java Swing / NetBeans GUI Builder**  
- **Tratamento de exceções** e **validação de dados**  
- **Animações e imagens** para simular o ambiente espacial (`espaco.gif`, `iconeFoguete.png`)  

---

## 💾 Banco de Dados

O sistema utiliza **MySQL** como banco relacional.  
O script `BD.sql` cria as tabelas necessárias, incluindo:

- `foguete`
- `destino`
- `base`
- `sensor`
- `carga`
- `funcionario`
- `cargo`

### 📥 Importar o Banco
1. Abra o **MySQL Workbench** ou **phpMyAdmin**  
2. Crie um banco com o nome `spaceij`  
3. Execute o script `BD.sql` na base  
4. Verifique as tabelas criadas corretamente  

---

## 🛠️ Como Executar

### 🔧 Pré-requisitos
- Java JDK 17+  
- MySQL instalado e configurado  
- NetBeans (recomendado)  
- Driver `mysql-connector-j-8.2.0.jar` incluído no projeto  

### ▶️ Execução no NetBeans
1. Abra o projeto `SpaceIJP_Java` no NetBeans  
2. Certifique-se de que o driver JDBC está adicionado ao **Classpath**  
3. Configure a conexão no arquivo `ConexaoBD.java` com seu usuário e senha MySQL  
4. Execute o projeto (Shift + F6)  

---

## 🧱 Estrutura do Projeto
```
SpaceIJP_Java/
├── BD.sql
├── SpaceIJP_Java/
│ ├── src/
│ │ ├── DAObd/ConexaoBD.java # Conexão MySQL
│ │ ├── Script/ # Classes DAO e modelo
│ │ ├── janelas/ # Interfaces Swing
│ │ ├── imgs/ # Imagens do projeto
│ │ └── Main.java # Classe principal
│ └── mysql-connector-j-8.2.0.jar
└── README.md
```

---

## 👨‍💻 Tecnologias Utilizadas
| Tecnologia | Função |
|-------------|--------|
| **Java SE** | Lógica principal do sistema |
| **Swing (NetBeans GUI)** | Interface gráfica |
| **MySQL** | Armazenamento dos dados |
| **JDBC** | Conexão entre Java e banco |
| **POO** | Estrutura modular do código |
| **Threads** | Animações e contagem regressiva |

---

## 👨‍🚀 Equipe de Desenvolvimento

| Nome | Função |
|------|--------|
| **Iago Nunes** | 👨‍💻 Programador Diretor |
| **João Gabriel Prado** | 🧠 Desenvolvedor do Banco de Dados |
| **Pedro Lucas** | 🎨 Animador e Designer |

---

## 📜 Licença

Este projeto é distribuído sob a licença **MIT**.  
Sinta-se à vontade para estudar, modificar e contribuir.

---

> Desenvolvido com 💻 e ☕ por **Iago Nunes**, **João Gabriel Prado** e **Pedro Lucas**  
> *SpaceIJP Java — A fronteira final da programação! 🚀*
