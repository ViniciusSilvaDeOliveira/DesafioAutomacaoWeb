# language: pt
  Funcionalidade: Cadastrar e realizar login com usuários na plataforma

      @CT001
      Cenario: Realizar cadastro na plataforma
        Dado ter a massa de dados dos usuarios
          | planilha             | aba      |
          | planilhaDesafio.xlsx | register |
        Quando preencho as informacoes de cadastro e clico em register
        Entao valido que o cadastro do usuario foi realizado apos ser direcionado para a tela de login