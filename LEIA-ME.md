# LEIA-ME — Monte e entenda seu Cardápio Digital

Este guia foi escrito para você acompanhar a montagem no Android Studio e entender o que deverá explicar ao professor. Todos os códigos já estão nas pastas do projeto: abra os arquivos para consultar, copiar ou estudar. Não é preciso obter código do projeto de filmes.

## 1. O que vamos entregar

O aplicativo se chama **Sabor da Casa** e o projeto se chama **CardapioDigital**. Há duas telas:

1. **Cardápio:** três pratos com ilustração, nome, categoria, preço e botão “Ver detalhes”.
2. **Detalhes:** informações do prato, botões para escolher a quantidade e total estimado.

O total é uma simulação. Nenhum pedido é enviado. Os pratos e preços estão no código, sem API, conta de usuário ou banco.

Usamos XML, Kotlin e findViewById. ViewBinding, Fragments e componentes XML de layout reutilizáveis não foram implementados. Os arquivos XML da pasta drawable são imagens e fundos, não componentes de tela inflados separadamente.

## 2. Escolha uma forma de começar

**Caminho A:** criar um projeto pelo assistente e colocar os arquivos preparados. É o caminho explicado primeiro, para acompanhar a criação desde o começo.

**Caminho B:** abrir diretamente o projeto preparado. Produz o mesmo aplicativo e serve também para conferir sua montagem. Você não precisa executar os dois caminhos.

## 3. Caminho A — criar pelo Android Studio

### Passo 1: criar o projeto vazio

1. Extraia o ZIP recebido em uma pasta de referência, por exemplo `C:\Estudos\ArquivosCardapio`. Dentro dela estará a pasta `CardapioDigital` com os arquivos preparados.
2. Abra o Android Studio.
3. Na tela inicial, clique em **New Project**. Se outro projeto estiver aberto, use **File → New → New Project**.
4. Na categoria **Phone and Tablet**, escolha **Empty Views Activity**. Confira a palavra **Views**, pois o trabalho exige XML.
5. Clique em **Next**.
6. Preencha:

| Campo | Valor |
|---|---|
| Name | `CardapioDigital` |
| Package name | `com.example.cardapiodigital` |
| Save location | Uma pasta diferente da referência, por exemplo `C:\Estudos\CardapioDigital` |
| Language | `Kotlin` |
| Minimum SDK | `API 24: Android 7.0` |
| Build configuration language | `Kotlin DSL`, se o campo aparecer |

7. Clique em **Finish**.
8. Aguarde a configuração inicial. Na primeira vez, o Android Studio pode baixar componentes.

O pacote deve ser exatamente o da tabela porque todos os arquivos Kotlin começam com `package com.example.cardapiodigital`.

### Passo 2: colocar os arquivos preparados

O assistente do Android Studio varia conforme a versão instalada. Para a montagem usar as mesmas configurações do pacote, vamos substituir o código de exemplo e a configuração de build do projeto recém-criado.

1. Feche esse projeto com **File → Close Project**.
2. Abra a pasta do projeto novo pelo Explorador de Arquivos do Windows.
3. Como ele ainda está vazio, mova as pastas **app** e **gradle** geradas pelo assistente para uma pasta de backup fora do projeto, por exemplo `C:\Estudos\BackupModeloCardapio`. Faça isso somente no projeto recém-criado, não em trabalhos antigos.
4. Entre na pasta `CardapioDigital` que foi extraída do ZIP.
5. Copie **todo o conteúdo de dentro dela** para a raiz do projeto novo. Aceite substituir os arquivos de mesmo nome.
6. Preserve o `local.properties` que o Android Studio criou, se ele existir. O pacote não contém esse arquivo porque o caminho do SDK varia por computador.
7. Confira: na raiz devem ficar `app`, `gradle`, `build.gradle.kts`, `settings.gradle.kts`, `gradle.properties`, `gradlew`, `gradlew.bat`, `.gitignore`, `README.md` e `LEIA-ME.md`.

Não crie uma pasta `CardapioDigital` dentro de outra `CardapioDigital` por engano. `app` e `settings.gradle.kts` precisam estar lado a lado na pasta que você abrirá.

Substituir as pastas, em vez de mesclar arquivos, evita que permaneçam telas, temas noturnos e testes de exemplo do assistente. O pacote usa versões fixas das ferramentas para permitir reproduzir a configuração.

### Passo 3: reabrir e sincronizar

1. No Android Studio, clique em **Open**.
2. Escolha a raiz do projeto novo, aquela que contém `settings.gradle.kts`.
3. Aguarde a sincronização do Gradle.
4. Se precisar iniciá-la manualmente, use **File → Sync Project with Gradle Files**.
5. Siga a seção 5 deste guia para configurar JDK, SDK e dispositivo.

Não é necessário acionar “New Activity” para a segunda tela: `DetalhesActivity.kt`, seu XML e o registro no Manifest já estão incluídos.

## 4. Caminho B — abrir diretamente o projeto preparado

1. Extraia o ZIP em uma pasta local, por exemplo `C:\Estudos`.
2. No Android Studio, use **Open**.
3. Selecione a pasta `CardapioDigital` que contém `settings.gradle.kts` e `app`.
4. Aguarde a sincronização e siga a próxima seção.

Esse também é o caminho que o professor pode usar depois de baixar ou clonar seu repositório.

## 5. Preparar o ambiente e executar

### JDK do Gradle

Abra **File → Settings → Build, Execution, Deployment → Build Tools → Gradle**. No campo **Gradle JDK**, escolha um **JDK 17**. Se não houver, use a opção **Download JDK**, selecione versão 17 e aguarde a instalação.

O JDK executa as ferramentas que compilam o projeto. Ele não é a versão do Android do celular. Se a sincronização inicial tiver falhado por JDK, ajuste este campo e sincronize novamente.

### SDK Android

Abra **Tools → SDK Manager**:

1. Em **SDK Platforms**, instale **Android 15 / API 35**.
2. Em **SDK Tools**, confira **Android SDK Platform-Tools** e **Android SDK Build-Tools 35.0.0**. Use “Show Package Details” para localizar a versão dos Build-Tools, se necessário.
3. Se for usar emulador, instale também **Android Emulator**.
4. Clique em **Apply** e aguarde.

As configurações têm funções diferentes:

| Configuração | Significado neste projeto |
|---|---|
| `compileSdk = 35` | SDK usado para compilar. |
| `targetSdk = 35` | Versão de comportamento Android declarada pelo app. |
| `minSdk = 24` | Android mínimo em que o app pode ser instalado. |

Use Android Studio com suporte ao AGP 8.9.2, como Meerkat 2024.3.1 ou uma versão posterior compatível. Não aceite atualização automática de versões durante esta montagem; primeiro valide a configuração fornecida.

### Criar um emulador

1. Abra **Tools → Device Manager**.
2. Clique em **Create Device** ou no botão `+`.
3. Escolha um celular, por exemplo **Pixel 6**.
4. Selecione uma imagem de sistema **API 35** adequada ao seu computador. Baixe-a, se necessário.
5. Finalize a criação e inicie o emulador.
6. Na barra superior do Android Studio, selecione o módulo **app** e esse dispositivo.
7. Clique no botão **Run**, o triângulo verde.

Para testar em celular físico, ative as opções do desenvolvedor e a depuração USB, conecte o aparelho, autorize a depuração na tela do celular e selecione-o na lista de dispositivos.

**A primeira execução pode demorar devido aos downloads.** Depois de instalado, o cardápio não usa internet.

## 6. Onde estão os códigos

No painel esquerdo do Android Studio, a visualização **Android** agrupa arquivos. Troque para **Project** quando quiser enxergar as pastas como aparecem no ZIP.

| Local | Conteúdo |
|---|---|
| `app/src/main/java/com/example/cardapiodigital/` | Os cinco arquivos Kotlin. |
| `app/src/main/res/layout/` | Os dois layouts de tela. |
| `app/src/main/res/drawable/` | Ilustrações dos pratos, ícone e fundos. |
| `app/src/main/res/values/strings.xml` | Textos fixos da interface. |
| `app/src/main/res/values/colors.xml` | Cores. |
| `app/src/main/res/values/themes.xml` | Tema visual. |
| `app/src/main/AndroidManifest.xml` | Declaração das Activities. |
| `app/build.gradle.kts` | Configuração do aplicativo e dependências. |
| `build.gradle.kts` da raiz | Versões dos plugins Android e Kotlin. |

Para ver o código de um layout, abra o XML e selecione **Code** ou **Split** no editor. Textos e imagens preenchidos pelo Kotlin podem não aparecer no preview; confira o resultado executando o aplicativo.

## 7. Entenda os modelos e os mocks

### Prato.kt

É a representação de um prato. `data class` é uma classe feita para guardar dados e disponibiliza funções como `copy()` e comparação por conteúdo.

Todos os campos são `val`, portanto não podem ser reatribuídos após a criação do objeto. Os tipos são valores simples, inclusive o identificador inteiro do recurso de imagem.

`val alergenicos: String?` aceita uma string ou `null`. O ponto de interrogação faz parte do tipo. Um dado desconhecido não deve ser tratado como confirmação de que não há alergênicos.

O preço é inteiro em centavos: `2590` representa R$ 25,90. Assim, o cálculo do total é uma multiplicação inteira. A função `formatarMoeda()` converte apenas para exibição em reais, com vírgula decimal.

### CardapioMock.kt

`object` cria uma instância compartilhada. Sua lista tem os três pratos de exemplo, sem consultar servidor.

`buscarPorId(id)` usa `find` para encontrar o prato. O retorno é `Prato?` porque pode não existir item com aquele ID. A salada possui `alergenicos = null` para demonstrar o tratamento de um campo opcional.

Para mudar nomes, preços e descrições, edite esse arquivo. Mantenha três pratos e IDs únicos nesta versão, porque a primeira tela possui três blocos fixos.

### ItemPedido.kt

Guarda o prato e a quantidade da simulação. `require` valida que a quantidade esteja entre 1 e 20. A propriedade calculada `totalCentavos` multiplica o preço pela quantidade; não é preciso armazenar um segundo valor que possa ficar desatualizado.

## 8. Entenda a primeira tela

`MainActivity` é a classe que controla a tela inicial. O Android chama `onCreate()` quando cria essa Activity.

`setContentView(R.layout.activity_main)` carrega o layout XML. `R` é uma classe de identificadores gerada pelo Android a partir dos recursos do projeto.

No XML, `android:id="@+id/nomeBurger"` cria o identificador de uma View. No Kotlin, `findViewById<TextView>(R.id.nomeBurger)` encontra essa View para permitir alterar sua propriedade `text`.

A função `configurarPrato` recebe o prato e os IDs de seus elementos. Ela evita repetir três vezes o mesmo código Kotlin. Não cria componentes XML e não infla itens dinamicamente: os três blocos já existem em `activity_main.xml`.

`setOnClickListener` registra o que acontece quando o botão é clicado:

```kotlin
val intent = Intent(this, DetalhesActivity::class.java)
intent.putExtra(DetalhesActivity.EXTRA_PRATO_ID, prato.id)
startActivity(intent)
```

É uma **Intent explícita**, pois informa diretamente a Activity de destino. `putExtra` envia um valor junto da navegação. Neste caso, enviamos o ID do prato, suficiente para buscá-lo nos mocks compartilhados.

## 9. Entenda os detalhes e as interações

`DetalhesActivity` recebe o ID com `getIntExtra`. Se o extra estiver ausente, usa `-1`, que não corresponde a nenhum prato.

A busca pode retornar `null`. O código testa isso antes de usar o prato: mostra uma mensagem, chama `finish()` e sai de `onCreate()` com `return`. Esse tratamento evita tentar ler propriedades de um objeto inexistente.

Para os alergênicos, usamos o operador Elvis:

```kotlin
prato.alergenicos ?: getString(R.string.alergenicos_nao_informados)
```

Se a esquerda não for nula, ela será exibida. Caso contrário, será usada a mensagem à direita.

Os botões modificam a referência `itemPedido` usando uma cópia:

```kotlin
itemPedido = itemPedido.copy(quantidade = itemPedido.quantidade + 1)
atualizarResumo()
```

O objeto anterior não tem suas propriedades alteradas. `copy()` cria outro `ItemPedido`, preserva o prato e usa a nova quantidade. A variável da Activity pode apontar para outro objeto porque é `var`; os campos dentro do modelo continuam sendo `val`.

`atualizarResumo()` atualiza quantidade, total e estado dos botões. Em 1 unidade, o botão diminuir fica desabilitado. Em 20, o botão aumentar fica desabilitado.

`finish()` fecha a tela atual e revela a anterior. Não iniciamos uma nova MainActivity a cada retorno, o que evitaria acumular cópias do cardápio.

## 10. XML, rotação e barras do Android

`LinearLayout` organiza elementos em linha ou coluna. `ScrollView` permite rolar o conteúdo; ele recebe um único filho direto, que neste caso é um LinearLayout com os demais elementos. `FrameLayout` contém a imagem de destaque nos detalhes. Textos usam TextView, imagens usam ImageView e ações usam Button.

As dimensões visuais usam `dp`; tamanhos de fonte usam `sp`, para respeitar a configuração de texto do usuário. Os botões têm áreas mínimas de toque, as imagens recebem descrições e o conteúdo pode rolar em telas menores.

`enableEdgeToEdge` configura as barras do sistema. O listener de insets adiciona espaço para relógio, recorte da câmera e navegação, evitando que esses elementos cubram o conteúdo.

Ao girar o aparelho, o Android pode destruir e recriar a Activity. `onSaveInstanceState` guarda a quantidade em um Bundle. O novo `onCreate` recupera esse valor. Isso preserva a simulação durante a recriação; não é armazenamento permanente e não cria banco de dados.

## 11. Confira se funciona

A preparação incluiu revisão estática dos recursos e do código. **Não foi possível compilar ou executar Android no ambiente que gerou o pacote, pois não havia SDK Android. Por isso, execute esta conferência no seu Android Studio antes de entregar.**

| Ação | Resultado esperado |
|---|---|
| Executar o app | Tela “Sabor da Casa” com três pratos. |
| Abrir Burger | Nome correto, R$ 25,90 e quantidade 1. |
| Aumentar para 2 | R$ 51,80. |
| Diminuir até 1 | Botão diminuir desabilitado. |
| Aumentar até 20 | R$ 518,00 e botão aumentar desabilitado. |
| Voltar e abrir Pizza | Detalhes da pizza; quantidade começa em 1. |
| Pizza com quantidade 2 | R$ 65,80. |
| Abrir Salada | Mensagem de alergênicos não disponíveis. |
| Salada com quantidade 2 | R$ 39,80. |
| Girar nos detalhes com quantidade 3 | Mantém prato, quantidade e total. |
| Usar Voltar do Android | Retorna ao cardápio. |
| Desativar internet após instalação | App continua funcionando. |

Também teste com fonte maior nas configurações do aparelho e em orientação horizontal. A rolagem deve permitir acessar todo o conteúdo.

### Se aparecer erro

| Mensagem ou sintoma | O que conferir |
|---|---|
| SDK location not found | Abra pelo Android Studio e configure o SDK Manager. O caminho do SDK é específico de cada computador. |
| Falta android-35 | Instale Android SDK Platform 35. |
| Erro de Java/Gradle JDK | Selecione JDK 17 nas configurações do Gradle. |
| Could not resolve / erro de download | Confira a internet, a configuração de proxy e se o modo offline do Gradle está desativado. |
| Unresolved reference R | Abra o painel Build e procure o primeiro erro XML. Confira se todas as pastas res foram copiadas. |
| Erro com binding ou Compose | Pode ter sobrado código do modelo original. Confira a substituição completa da pasta app. |
| Tema Material ausente | Pode ter sobrado um themes.xml do assistente, inclusive em values-night. Confira a substituição completa de app. |
| No devices | Inicie um emulador no Device Manager ou conecte um celular com depuração. |

Se continuar, envie o **primeiro erro do painel Build** e um print da estrutura de pastas. Não altere versões aleatoriamente para tentar resolver.

## 12. Como publicar no GitHub para entregar

Faça isso depois de confirmar que o app compila e executa.

1. No GitHub, crie um repositório vazio chamado `CardapioDigital`.
2. Selecione a visibilidade exigida pelo professor. Se for privado, conceda acesso ao professor.
3. Como já temos README e .gitignore, deixe desmarcada a criação desses arquivos pelo GitHub.
4. Copie a URL HTTPS do repositório criado.
5. Abra o terminal do Android Studio na raiz do projeto. Execute os comandos abaixo, um por vez. Substitua `SEU_USUARIO` pela sua conta.

```bash
git init
git add .
git status
git commit -m "Implementa cardapio com duas telas XML e Intent"
git branch -M main
git remote add origin https://github.com/SEU_USUARIO/CardapioDigital.git
git push -u origin main
```

Depois de `git status`, confira que local.properties, .idea, .gradle e pastas build não aparecem como arquivos a enviar. O .gitignore fornecido exclui esses itens. O gradle-wrapper.jar, gradlew e gradlew.bat devem ser enviados.

Se o Git solicitar identidade, configure seu nome e e-mail neste repositório com `git config user.name "Seu nome"` e `git config user.email "seu-email"`, e execute o commit novamente. Use a autenticação normal do GitHub quando solicitada.

No GitHub, a página inicial do repositório deve mostrar diretamente `app`, `gradle` e README.md. Não entregue apenas um ZIP dentro do repositório nem envie somente a pasta app.

Para validar a entrega, clone o repositório em outra pasta e abra essa cópia no Android Studio. Repita a compilação e a execução. Registre no README a versão do Android Studio, o dispositivo usado e o resultado real do teste.

A entrega ao professor é o **link do repositório**. Este projeto não utiliza chaves ou senhas e não precisa de `.env`.

## 13. O que saber explicar ao professor

**Qual o objetivo do aplicativo?** Permitir consultar pratos e simular o custo de uma quantidade escolhida.

**Por que duas Activities?** Cada uma controla uma das duas telas; a comunicação ocorre por Intent explícita, conforme a parcial.

**Por que passar somente o ID?** Ele identifica o prato, e a segunda tela recupera as demais informações na mesma fonte de mocks.

**Onde estão os dados?** Em CardapioMock.kt, numa lista local. Nada é consultado na internet.

**Como o Kotlin acessa o XML?** setContentView carrega o layout e findViewById localiza uma View pelo ID.

**O que torna os modelos imutáveis?** As propriedades val e a criação de um novo ItemPedido com copy, em vez de alterar a quantidade do objeto anterior.

**Onde tratamos null?** No campo alergenicos e no resultado da busca de um prato pelo ID.

**O que atualiza a interface?** Os cliques nos botões chamam atualizarResumo, que altera os textos e habilita ou desabilita os controles.

**Por que guardar dinheiro em centavos?** Para calcular o total usando números inteiros e formatar em reais apenas na apresentação.

**Por que não usar RecyclerView agora?** São três itens fixos, e a proposta desta etapa é usar uma estrutura simples sem componentes de layout reutilizáveis. Uma lista maior justificaria um RecyclerView.

**Onde está a arquitetura?** Activities controlam as telas; data classes representam os dados; o objeto CardapioMock fornece os dados simulados. Não há camadas adicionais sem necessidade nesta etapa.

**O que acontece ao girar a tela?** A quantidade é guardada no estado da Activity e recuperada quando ela é recriada.

## 14. Ordem sugerida para estudar

1. Abra Prato.kt e identifique os campos.
2. Abra CardapioMock.kt e encontre onde os três pratos são criados.
3. Abra activity_main.xml e localize os IDs de um dos pratos.
4. Abra MainActivity.kt e relacione esses IDs com findViewById.
5. Encontre a criação da Intent e a passagem do ID.
6. Abra DetalhesActivity.kt e acompanhe a leitura desse ID.
7. Confira o tratamento de null e depois os listeners dos botões.
8. Abra ItemPedido.kt e explique como o total é calculado.
9. Execute o aplicativo e relacione cada interação com o código responsável.

Na próxima conversa, podemos seguir uma etapa por vez a partir do print do seu Android Studio.
