# language: pt
  Funcionalidade: Cadastrar e realizar login com usuários na plataforma

      @CT001
      Cenario: Realizar cadastro na plataforma
        Dado ter a massa de dados dos usuarios
          | planilha             | aba      |
          | planilhaDesafio.xlsx | register |
        Quando preencho as informacoes de cadastro e clico em register
        Entao valido que o cadastro do usuario foi realizado apos ser direcionado para a tela de login

      @CT002
      Cenario: Realizar login
        Dado ter a massa de dados dos usuarios
          | planilha             | aba      |
          | planilhaDesafio.xlsx | register |
        E clico em Guest
        E seleciono a opção de login
        Quando preencho as informacoes
        E realizo o login
        Entao valido que o login foi realizado com sucesso

      @CT003
      Cenario: Pesquisar carro pela cor
        Dado ter a massa de dados dos usuarios
          | planilha             | aba      |
          | planilhaDesafio.xlsx | register |
        E clico em Guest
        E seleciono a opção de login
        Quando preencho as informacoes
        E realizo o login
        E seleciono a opção Search
        E pesquiso por um carro
          | planilha             | aba |
          | planilhaDesafio.xlsx | cor |
        Entao valido que a busca trouxe as cores corretas

        @CT004
        Cenário: Pesquisar carro por ano
          Dado seleciono a opção Search
          Quando pesquiso por um carro pelo ano
            | planilha             | aba |
            | planilhaDesafio.xlsx | ano |
          Entao valido que a busca me trouxe apenas de acordo com o ano do carro