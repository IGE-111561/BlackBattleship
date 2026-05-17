
## Group Members

| Course | Nº | Name | Nickname |
|---|---|---|---|
| LIGE-PL | 123774 | Ana Comparada Reganha | anacomparada |
| LIGE-PL | 93263 | Diogo Soares | IGE-93262, dpsoares |
| LIGE-PL | 123762 | Rodrigo Couceiro | IGE-123762 |
| LIGE-PL | 111561 | Tiago Bensusan | IGE-111561 | 





# Product Backlog — Batalha Naval Online (papergames.io)

Backlog em formato de User Stories Scrum, ordenado por prioridade decrescente. As primeiras stories cobrem o núcleo do produto (jogar uma partida); as seguintes expandem para funcionalidades de comunidade, monetização e suporte.

---

## US01 — Aceder à página do jogo de Batalha Naval

**Como** visitante do site  
**Quero** abrir a página dedicada à Batalha Naval a partir da home  
**Para** poder começar a jogar sem precisar de criar conta

**Critérios de aceitação:**
- A partir da home (`/en/`), existe uma forma visível de navegar para `/en/battleship`
- A página da Batalha Naval apresenta os modos de jogo disponíveis (vs amigo, vs robot, online aleatório, criar torneio)
- O acesso à página não exige login nem registo prévios

---

## US02 — Aceitar ou rejeitar cookies

**Como** visitante  
**Quero** ver um aviso de cookies e poder aceitá-los ou rejeitá-los  
**Para** controlar a minha privacidade ao usar o site

**Critérios de aceitação:**
- Na primeira visita ao site é apresentado um banner de cookies com opções claras de aceitar e rejeitar
- A escolha do utilizador é respeitada nas páginas seguintes sem voltar a pedir
- O banner inclui uma ligação para a Política de Privacidade

---

## US03 — Jogar uma partida contra o robot

**Como** visitante ou jogador registado  
**Quero** iniciar uma partida de Batalha Naval contra o robot  
**Para** experimentar o jogo sem depender de outro jogador humano

**Critérios de aceitação:**
- Na página da Batalha Naval existe uma opção clara para jogar contra o robot
- Ao iniciar, é apresentado um tabuleiro 10x10 com os navios colocados automaticamente
- O robot joga o seu turno automaticamente após a jogada do utilizador

---

## US04 — Posicionar navios no tabuleiro

**Como** jogador  
**Quero** que os meus navios sejam colocados no tabuleiro 10x10 no início da partida  
**Para** poder começar a jogar de imediato

**Critérios de aceitação:**
- Ao iniciar uma partida é mostrado um tabuleiro 10x10 do utilizador com os navios já posicionados aleatoriamente
- Os navios do utilizador são visíveis para o próprio, mas não para o adversário
- Existe uma forma visível de confirmar o posicionamento ou aceitar uma nova distribuição aleatória antes do início da partida

---

## US05 — Disparar contra o tabuleiro adversário

**Como** jogador  
**Quero** clicar numa casa do tabuleiro do adversário para disparar  
**Para** tentar acertar e afundar a sua frota

**Critérios de aceitação:**
- No meu turno consigo selecionar uma casa do tabuleiro adversário e confirmar o disparo
- Após o disparo a casa mostra visualmente se foi água ou acerto
- Quando acerto num navio, o turno mantém-se comigo e posso disparar de novo
- Quando falho, o turno passa ao adversário

---

## US06 — Vencer a partida ao afundar a frota adversária

**Como** jogador  
**Quero** ver o jogo terminar quando afundo todos os navios do adversário (ou ele afunda os meus)  
**Para** saber claramente o resultado da partida

**Critérios de aceitação:**
- Quando toda a frota de um lado é afundada, é apresentado um ecrã de fim de jogo com o vencedor
- O ecrã final permite iniciar uma nova partida ou voltar ao menu do jogo
- Os tabuleiros completos de ambos os jogadores podem ser revistos no fim

---

## US07 — Jogar contra um amigo via link partilhável

**Como** jogador  
**Quero** criar uma partida privada e partilhar o link com um amigo  
**Para** jogar com alguém específico sem depender do matchmaking

**Critérios de aceitação:**
- Existe uma opção "Jogar contra um amigo" na página da Batalha Naval
- Ao escolher essa opção é gerado um link único e visivelmente copiável
- Quando o amigo abre o link, entra diretamente na sala da partida e o jogo arranca quando ambos estão presentes

---

## US08 — Jogar online contra adversário aleatório

**Como** jogador  
**Quero** entrar numa fila de matchmaking para jogar contra um oponente aleatório  
**Para** desafiar outros jogadores reais sem precisar de combinar previamente

**Critérios de aceitação:**
- Existe uma opção clara para jogar online contra adversário aleatório
- Após selecionar a opção, é mostrado um estado de "à procura de adversário"
- A partida começa automaticamente quando outro jogador é encontrado

---

## US09 — Apanhar gifts no tabuleiro para obter armas

**Como** jogador  
**Quero** que existam gifts espalhados pelo tabuleiro durante a partida  
**Para** poder obter armas especiais ao acertar nessas casas

**Critérios de aceitação:**
- Durante a partida existem gifts visíveis no tabuleiro adversário ao serem revelados
- Quando acerto numa casa com gift, recebo uma arma indicada visualmente no ecrã
- As armas disponíveis são apresentadas num inventário acessível durante o meu turno

---

## US10 — Usar armas especiais (simple, big, missile rain, nuclear)

**Como** jogador  
**Quero** poder selecionar e usar as armas especiais que apanhei  
**Para** ganhar vantagem tática na partida

**Critérios de aceitação:**
- Posso escolher entre o disparo normal e qualquer arma disponível no inventário antes de jogar
- A Simple missile atinge 1 casa, a Big missile atinge 5 casas em splash, a Missile rain atinge 7 casas aleatórias e a Nuclear missile atinge 14 casas em splash
- O resultado de cada arma (acertos e falhas) é apresentado visualmente no tabuleiro adversário
- Após usar uma arma, esta deixa de estar disponível no inventário

---

## US11 — Registar conta e iniciar sessão

**Como** visitante  
**Quero** criar uma conta e fazer login no site  
**Para** guardar o meu progresso, ranking e histórico de jogos

**Critérios de aceitação:**
- A partir do site é possível aceder a um formulário de registo e a um de login
- Após registo bem-sucedido fico automaticamente autenticado
- Quando autenticado, o meu nome de utilizador é visível no topo do site em todas as páginas
- Existe uma opção visível para terminar sessão

---

## US12 — Consultar o leaderboard diário

**Como** jogador registado  
**Quero** ver o leaderboard diário da Batalha Naval  
**Para** comparar a minha performance com a de outros jogadores

**Critérios de aceitação:**
- Existe uma secção de leaderboard acessível a partir da página do jogo
- O leaderboard mostra uma lista ordenada de jogadores com pontuação ou métrica de ranking
- O leaderboard atualiza-se diariamente e indica claramente o período a que se refere

---

## US13 — Compreender o sistema de ranking RANKA

**Como** jogador registado  
**Quero** consultar uma página explicativa do sistema RANKA  
**Para** perceber como evolui o meu ranking nos jogos

**Critérios de aceitação:**
- Existe uma ligação visível para `/en/blog/ranka` a partir das páginas de ranking ou do menu
- A página descreve em linguagem clara como o ranking é calculado e atualizado
- A partir dessa página é possível voltar facilmente ao jogo ou ao leaderboard

---

## US14 — Aceder à criação de torneio

**Como** utilizador interessado em organizar competições
**Quero** aceder à funcionalidade de criação de torneios em /en/t/create-tournament
**Para** poder organizar competições com um grupo definido de jogadores

**Critérios de aceitação:**
- O botão "Create tournament" está acessível e visível na navegação do site
- Ao clicar no botão sem sessão iniciada, o sistema apresenta um diálogo de autenticação com o título "Welcome back!"
- O diálogo de autenticação disponibiliza o botão "Continue with email" e opções de login social (Google, Facebook, Discord)
- O diálogo de autenticação pode ser fechado pelo utilizador


---

## US15 — Consultar os meus torneios

**Como** jogador registado  
**Quero** aceder a uma página com os torneios em que participo ou que organizei  
**Para** acompanhar o seu estado e jogar as minhas partidas

**Critérios de aceitação:**
- A página `/en/t/my-tournaments` lista os torneios ativos, futuros e concluídos do utilizador
- Cada torneio mostra o seu estado (em curso, à espera, terminado) e o papel do utilizador (organizador ou participante)
- A partir da lista é possível abrir cada torneio para ver chaveamento e jogar partidas

---

## US16 — Subscrever Plus para remover anúncios

**Como** jogador registado  
**Quero** subscrever o plano Plus em `/en/pricing/plus`  
**Para** jogar sem anúncios e apoiar o serviço

**Critérios de aceitação:**
- A página Plus apresenta de forma clara o preço e os benefícios da subscrição
- Existe um botão visível para iniciar o processo de subscrição
- Após subscrição confirmada, os anúncios deixam de aparecer durante a navegação e durante as partidas

---

## US17 — Subscrever Gruppo para torneios privados

**Como** organizador  
**Quero** subscrever o plano Gruppo em `/en/pricing/gruppo`  
**Para** poder criar e gerir torneios privados de forma recorrente

**Critérios de aceitação:**
- A página Gruppo descreve os benefícios específicos para organização de torneios privados
- O fluxo de subscrição está acessível diretamente a partir dessa página
- Após subscrição, ficam disponíveis as funcionalidades de torneios privados associadas ao plano

---

## US18 — Ajustar definições da conta e do jogo

**Como** jogador registado  
**Quero** aceder a uma página de Settings  
**Para** configurar preferências como idioma, som e notificações

**Critérios de aceitação:**
- Existe uma opção "Settings" visível quando estou autenticado
- A página permite alterar pelo menos idioma e preferências de som/notificações
- As alterações são guardadas e mantêm-se entre sessões no mesmo navegador

---

## US19 — Consultar guias de jogo e changelog

**Como** jogador  
**Quero** aceder aos guias de jogo (`/docs/game-guides/`) e ao changelog (`/en/changelog`)  
**Para** aprender as regras da Batalha Naval e acompanhar novidades da plataforma

**Critérios de aceitação:**
- Existe uma ligação visível no site para os guias de jogo e para o changelog
- A página de guias inclui conteúdo específico para a Batalha Naval com regras e modos
- O changelog apresenta uma lista cronológica de atualizações com data associada

---

## US20 — Aceder a Política de Privacidade e Termos & Condições

**Como** visitante  
**Quero** consultar a Política de Privacidade e os Termos & Condições do site  
**Para** estar informado sobre os meus direitos e obrigações ao usar a plataforma

**Critérios de aceitação:**
- Existem ligações visíveis no rodapé do site para `/en/blog/privacy-policy` e `/en/blog/terms-conditions`
- Cada página apresenta o conteúdo legal completo e legível
- As ligações são acessíveis a partir de qualquer página, incluindo durante uma partida

---

## US21 — Descarregar a aplicação móvel

**Como** jogador  
**Quero** encontrar no site as ligações para a app na Google Play e App Store  
**Para** poder jogar Batalha Naval no telemóvel

**Critérios de aceitação:**
- Na home e/ou na página da Batalha Naval existem botões visíveis para Google Play e App Store
- Os botões abrem as fichas oficiais da aplicação nas respetivas lojas
- Os botões estão visíveis tanto em desktop como em mobile

---

## US22 — Visitar a loja e a página de merch

**Como** jogador  
**Quero** aceder à loja (`/en/shop`) e à página de goodies (`merch.papergames.io`)  
**Para** comprar itens dentro do jogo e merchandising oficial

**Critérios de aceitação:**
- Existe uma entrada visível para a Loja a partir do menu principal do site
- A loja apresenta itens com preço e botão de compra claramente identificado
- A página de merch é acessível a partir do site e abre o catálogo de produtos físicos



## Execução dos testes iniciais - 123762
A classe `MainPageTest` foi executada com sucesso na branch `BlackBattleship-123762`.


## Execução dos testes iniciais - 93263

A classe `MainPageTest` foi executada com sucesso na branch `BlackBattleship-93263`.

Resultados:
- search() ✔
- toolsMenu() ✔
- navigationToAllTools() ✔

Total: 3 testes executados, 3 testes concluídos com sucesso.

Nota: Foram apresentados avisos relativos à versão CDP do Chrome, mas não impediram a execução dos testes.