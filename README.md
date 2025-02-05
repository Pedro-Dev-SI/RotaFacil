# RotaFacil

**Sistema de Rotas de Entregas para Supermercados**

O RotaFacil é um sistema desenvolvido para otimizar a gestão de rotas de entregas em supermercados. Ele integra um backend robusto, utilizando Java com Spring Boot, e um frontend moderno desenvolvido em Vue 3 com integração de mapas via Leaflet.

## Tecnologias Utilizadas

### Backend
- **Linguagem:** Java
- **Framework:** Spring Boot
- **Persistência:** Spring Data JPA
- **Banco de Dados:** PostgreSQL (suporte a operações espaciais com Hibernate Spatial e JTS)
- **Migrações de Banco:** Flyway

### Frontend
- **Framework:** Vue 3
- **Bibliotecas:**
  - Axios (para chamadas à API)
  - Vue Router (navegação)
  - Bootstrap & Bootstrap-Vue-3 (estilização)
  - Leaflet e vue3-leaflet (integração de mapas)

## Requisitos

- **Backend:**
  - Java JDK 17 ou superior
  - Maven
  - PostgreSQL (configurar base de dados e usuário conforme necessário)

- **Frontend:**
  - Node.js (versão 14 ou superior)
  - NPM ou Yarn

## Instalação

### Clonando o Repositório
```bash
git clone https://github.com/Pedro-Dev-SI/RotaFacil.git
