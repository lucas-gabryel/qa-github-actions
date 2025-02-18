# qa-github-actions

## Descrição

Este repositório contém testes automatizados de API para o sistema [ServeRest](https://serverest.dev/#/). O objetivo é validar a funcionalidade de endpoints relevantes e garantir a qualidade do sistema através da integração com o GitHub Actions.

## Funcionalidades

- Implementação de testes automatizados para os endpoints do ServeRest
- Testes de:
  - Build
  - Health check
  - Contrato
  - Funcional
  - Relatório
- Integração com o GitHub Actions para execução automatizada dos testes

## Estrutura do Projeto

```
qa-github-actions/
│-- tests/                 # Diretório contendo os testes automatizados
│   ├──  
│   ├── 
│   ├── 
│-- .github/workflows/      # Diretório de workflows do GitHub Actions
│   ├── test-pipeline.yml   # Arquivo de configuração da pipeline
│-- package.json           # Dependências do projeto
│-- README.md              # Documentação do repositório
```

## Configuração e Execução

### Pré-requisitos

- Java 17

## Integração com GitHub Actions

O repositório possui um workflow configurado no GitHub Actions para execução automática dos testes a cada novo commit ou pull request.

## Padrão de Branch

- `main`: Branch principal contendo apenas versões estáveis.
- `develop`: Branch para desenvolvimento e integração de novas funcionalidades.
- `feature/nome-da-feature`: Branches criadas para novas funcionalidades.
- `hotfix/nome-do-hotfix`: Branches para correção de bugs urgentes.

## Padrão de Commits

Os commits devem ser descritivos e seguir a estrutura:

```
[Tipo]: Descrição breve
```

## Equipe 13

- [Yasmin Muniz](https://github.com/Yasmiinmuniz)
- [Lucas Gabryel](https://github.com/lucas-gabryel)
