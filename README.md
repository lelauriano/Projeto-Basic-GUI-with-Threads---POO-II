# Projeto-Basic-GUI-with-Threads---POO-II
Desenvolver uma aplicação Java com interface gráfica que explore a criação de interfaces básicas, integração com arquivos, uso de threads e personalização de componentes. A aplicação deve demonstrar a mecânica de ouvintes, manipulação de arquivos, criação de diálogos personalizados e animações dinâmicas.

Descrição do Projeto:
Desenvolver uma aplicação Java completa que utilize uma janela principal (`JFrame`) com os
elementos de interface convencionais, como barra de menus (`JMenuBar`), barra de status,
título, ícone e outros componentes. A aplicação deverá cumprir os seguintes objetivos:

a) Demonstrar a mecânica de ouvintes (implementação interna):
O projeto deve implementar corretamente os ouvintes para interações do usuário,
incluindo menus, botões e outros componentes.

b) Ler arquivos e apresentar o conteúdo na tela conforme escolha do usuário:
O aplicativo deve permitir a leitura de arquivos de texto e exibir seu conteúdo em uma
área da janela principal.

c) Mostrar o uso de threads e gráficos dinâmicos por meio de um fundo continuamente
animado:
A janela principal deve exibir um fundo gráfico que se altera dinamicamente,
demonstrando o uso de threads em conjunto com desenhos gráficos.

d) Usar diálogos padrão para abrir arquivos:
Deve ser utilizado o componente `JFileChooser` para permitir ao usuário selecionar e
abrir arquivos no menu “Arquivo”.

e) Construir um diálogo de ajuda personalizado com imagens, texto rolável e botões:
A aplicação deve conter um diálogo de ajuda acessível pelo menu "Ajuda", incluindo
imagens, texto rolável e botões interativos.

Estrutura da Interface
A interface do programa deve apresentar a telas, menus e submenus EXATAMENTE como
descritos a seguir:

Janela Principal (`JFrame`): A janela principal deve conter barra de título, ícone da aplicação,
barra de status e menus.
Barra de Menus (`JMenuBar`): Deve conter três menus principais: Arquivo, Configuração e
Ajuda.
Menu Arquivo
Abrir Arquivo: Abre um diálogo para selecionar um arquivo de texto e exibir seu
conteúdo.
Fechar Arquivo: Fecha o arquivo atualmente aberto e limpa a área de texto da tela.
-- separador --
Sair: Encerra a aplicação.
Menu Configuração
Padrões: Permite escolher padrões para o comportamento dinâmico do fundo.
Cores: Altera as cores do fundo dinâmico.
Velocidade: Ajusta a velocidade das animações do fundo.
imagens, texto rolável e botões interativos.
Menu Ajuda
Ajuda: Abre um diálogo personalizado com explicações sobre a aplicação.
Sobre: Mostra informações sobre a aplicação (como versão e autores).

Requisitos Técnicos
• Interface Gráfica: Implementar a interface utilizando `JFrame`, `JMenu`, `JMenuItem`,
`JPanel`, `JFileChooser` e outros componentes gráficos.
• Ouvintes e Eventos: Demonstrar o uso de ouvintes, como `ActionListener` para capturar e
tratar eventos do usuário.
• Manipulação de Arquivos: Ler arquivos de texto e exibir o conteúdo de forma organizada na
janela principal.
• Animação e Threads: Implementar uma animação no fundo da janela que seja executada em
uma thread separada, controlável pelos menus de configuração.
• Diálogo Personalizado: Criar um diálogo de ajuda com imagens, texto rolável (`JScrollPane`) e
botões, utilizando `JDialog'.
