# 🐩 Magu Bank 🏦 

Este projeto é um sistema bancário básico desenvolvido em **Spring Boot**, com operações essenciais como **criação de clientes, abertura de contas, depósitos, saques e transferências**.

## 📌 Funcionalidades
- Cadastro de clientes com CPF e email únicos
- Abertura de contas bancárias (corrente ou poupança)
- Consulta de saldo e informações da conta
- Depósito em conta
- Saque de conta (respeitando o saldo)
- Transferência entre contas
- Histórico completo de transações por conta

---

## 🛠️ Tecnologias Utilizadas
- Java 24
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Bean Validation (javax.validation)

---

## 📏 Regras de Negócio
- Saldo inicial da conta é zero.
- CPF e email do cliente são únicos.
- Não permitido saldo negativo.
- Transferência só entre contas diferentes.
- Transações devem ter valor maior que zero.
- Histórico de transações com data e hora.

---

```bash
git clone https://github.com/EnzoPrevitale/Banco-SPRING
