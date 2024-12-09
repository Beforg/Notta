# Notta
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54) ![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)

Notta é um projeto desenvolvido com Java Spring, Python, e MongoDB, projetado para aprofundar os conceitos de Spring Security e integração com APIs REST. O sistema oferece uma plataforma básica para gerenciar notas com autenticação segura e ativação de contas por e-mail.

## Sobre
O Notta é focado em serviços de segurança e autenticação. Ele utiliza os módulos do Spring Security para proteger a API REST e implementar um sistema de registro/login com confirmação de e-mail para ativação da conta. A interface do cliente é básica e é baseada em Python e usa o pacote requests para realizar requisições à API.

## Funcionalidades principais

- **CRUD básico para notas**
  - Criação, leitura, atualização e exclusão de notas.
  - Organização e armazenamento de notas no banco de dados MongoDB.

- **Sistema de autenticação e registro com ativação de conta**
  - Registro de usuários com validação de dados.
  - Envio de e-mails com um link de confirmação para ativação da conta.
  - Login seguro utilizando autenticação JWT (JSON Web Tokens).

- **Interface de linha de comando (CLI)**
  - Escrita em Python para interação com a API REST.
  - Funcionalidades:
    - Registrar uma conta.
    - Fazer login.
    - Gerenciar notas (CRUD).

## Tecnologias utilizadas

### Backend
- **Java Spring Framework**:
  - Spring Security para autenticação e autorização.
  - Spring Boot para facilitar o desenvolvimento da API.
  - Spring Data MongoDB para persistência no banco de dados.
- Integração com serviço de envio de e-mails para confirmação de contas.

### Banco de dados
- **MongoDB** para armazenamento flexível e escalável.

### Frontend/CLI
- **Python**:
  - Pacote `requests` para consumo da API REST.
