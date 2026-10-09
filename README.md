# Sabor da Casa — Cardápio Digital

Aplicativo acadêmico da disciplina Mobile 1. Permite consultar três pratos, abrir os detalhes e simular o valor total conforme a quantidade escolhida. Utiliza Kotlin, Android Views e layouts XML.

O nome exibido no celular é **Sabor da Casa**. A pasta do projeto é **CardapioDigital** e o pacote é `com.example.cardapiodigital`.

## Como executar localmente

1. Clone este repositório ou baixe e extraia o ZIP.
2. No Android Studio, use **Open** e selecione a pasta que contém `settings.gradle.kts`, `gradlew` e `app`. Não abra somente a pasta `app`.
3. Use Android Studio com suporte ao Android Gradle Plugin 8.9.2, como Meerkat 2024.3.1 ou uma versão posterior compatível.
4. No SDK Manager, instale **Android SDK Platform 35**, **Android SDK Build-Tools 35.0.0** e **Android SDK Platform-Tools**. Para emulador, instale também Android Emulator e uma imagem de sistema.
5. Configure o **Gradle JDK como JDK 17** em Settings → Build, Execution, Deployment → Build Tools → Gradle. Se necessário, use Download JDK e selecione versão 17.
6. Aguarde a sincronização do Gradle. É necessário acesso à internet para baixar o Gradle e as dependências na primeira configuração.
7. Selecione o módulo `app` e um emulador ou celular com **Android 7.0/API 24 ou superior**.
8. Clique em **Run**. O aplicativo inicia no cardápio.

As versões de compilação estão declaradas no projeto. Não é necessário alterar o código, configurar API, criar banco de dados ou fornecer `.env`. Depois de instalado, o aplicativo funciona offline.

O Android Studio normalmente cria `local.properties` com a localização do SDK de cada máquina. Esse arquivo não deve ser versionado.

### Compilação pelo terminal

Com JDK 17 e SDK configurados, na raiz do projeto:

Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
```

Linux/macOS:

```bash
chmod +x gradlew
./gradlew assembleDebug
```

O APK é gerado em `app/build/outputs/apk/debug/app-debug.apk`.

## Funcionalidades

- Cardápio com Burger da Casa, Pizza Margherita e Salada da Horta.
- Detalhes específicos do prato selecionado, incluindo ilustração, categoria, preço, descrição e informação opcional sobre alergênicos.
- Quantidade entre 1 e 20, com atualização do total e bloqueio dos botões nos limites.
- Preservação da quantidade ao recriar a tela, por exemplo após rotação.
- Retorno ao cardápio pelo botão da tela ou pela navegação do Android.

A simulação não envia pedidos. Ao sair dos detalhes e abrir um prato novamente, a quantidade começa em 1. Não há carrinho, pagamento ou persistência em banco.

## Organização e decisões técnicas

| Arquivo | Responsabilidade |
|---|---|
| `MainActivity.kt` | Preenche o cardápio e abre os detalhes por Intent explícita. |
| `DetalhesActivity.kt` | Recebe o identificador, encontra o prato e atualiza quantidade e total. |
| `Prato.kt` | Modelo imutável do prato e função de formatação monetária. |
| `ItemPedido.kt` | Modelo imutável da simulação e cálculo do total. |
| `CardapioMock.kt` | Fonte de dados locais simulados e busca por identificador. |
| `activity_main.xml` | Layout da tela do cardápio. |
| `activity_detalhes.xml` | Layout da tela de detalhes. |
| `AndroidManifest.xml` | Registra as duas Activities e define a tela inicial. |

A arquitetura é pequena: Activities controlam a interface, modelos representam os dados e um objeto fornece os mocks. As Views são conectadas com `findViewById`. A navegação envia apenas o ID do prato; a segunda tela consulta os mesmos mocks.

Os três blocos do cardápio estão diretamente no XML. `ScrollView` permite rolagem; `LinearLayout` organiza os elementos; `FrameLayout` contém a ilustração do detalhe. A opção evita introduzir Adapter e componentes de layout reutilizáveis nesta parcial. Para um cardápio maior, um RecyclerView seria uma evolução adequada.

As propriedades dos modelos são `val`. Para mudar a quantidade, a Activity substitui a referência por um novo objeto criado com `copy()`. Os preços são armazenados como inteiros em centavos; a formatação usa reais e o padrão brasileiro.

`alergenicos: String?` pode ser nulo. Nesse caso, é apresentada uma mensagem de informação indisponível. Um ID inexistente ou ausente gera uma mensagem e fecha a tela de detalhes, sem acessar um objeto nulo.

## Bibliotecas utilizadas

| Dependência direta | Versão | Finalidade |
|---|---|---|
| AndroidX Core KTX | 1.15.0 | Utilitários AndroidX e tratamento dos espaços das barras do sistema. |
| AndroidX AppCompat | 1.7.0 | Base AppCompatActivity e tema compatível para Views. |
| AndroidX Activity KTX | 1.10.1 | Suporte às Activities e configuração das barras com enableEdgeToEdge. |

Ferramentas de compilação: **AGP 8.9.2**, **Gradle 8.11.1**, **Kotlin 2.1.10** e **JDK 17**. `compileSdk` e `targetSdk` são 35; `minSdk` é 24. O Gradle Wrapper acompanha o projeto.

As ilustrações são recursos vetoriais XML locais. Não existe biblioteca de imagens, chamada de rede, Jetpack Compose, ViewBinding ou Fragment implementado no aplicativo.

## Requisitos da parcial

| Requisito | Implementação |
|---|---|
| Duas telas em Views/XML | `activity_main.xml` e `activity_detalhes.xml`. |
| Intent explícita com dados | `Intent(this, DetalhesActivity::class.java)` com extra `prato_id`. |
| Conexão entre Kotlin e Views | `findViewById` nas duas Activities. |
| Interação | Abrir detalhes; aumentar/diminuir quantidade e atualizar total. |
| Modelos imutáveis | `Prato` e `ItemPedido`, com propriedades `val`. |
| Tratamento de opcionais | Alergênicos nulos e prato não encontrado. |
| Mocks | Lista local em `CardapioMock`. |

## Verificação antes da entrega

A estrutura e as referências XML/Kotlin foram revisadas estaticamente durante a preparação. **A compilação e a execução Android ainda precisam ser confirmadas no Android Studio; não foram executadas no ambiente de preparação.**

- Compilar com `assembleDebug` e executar o aplicativo.
- Abrir os três pratos e confirmar que os detalhes correspondem à escolha.
- Burger: 1 unidade = R$ 25,90; 2 = R$ 51,80; 20 = R$ 518,00.
- Pizza: 2 unidades = R$ 65,80. Salada: 2 = R$ 39,80.
- Confirmar que diminuir fica desabilitado em 1 e aumentar em 20.
- Confirmar a mensagem de alergênicos indisponíveis na salada.
- Girar o celular com quantidade 3: o prato, a quantidade e o total devem permanecer.
- Testar a rolagem e o botão voltar, inclusive em orientação horizontal.
- Antes da entrega, clonar o repositório em outra pasta e repetir compilação e execução. Registrar o resultado real da validação neste README.

O guia de montagem, a explicação do código e o passo a passo do GitHub estão em **[LEIA-ME.md](LEIA-ME.md)**.

## Referências

- [Layouts Android Views](https://developer.android.com/develop/ui/views/layout/declaring-layout)
- [Intents e filtros](https://developer.android.com/guide/components/intents-filters)
- [Data classes em Kotlin](https://kotlinlang.org/docs/data-classes.html)
- [Segurança contra nulos em Kotlin](https://kotlinlang.org/docs/null-safety.html)
- [Compatibilidade do AGP 8.9](https://developer.android.com/build/releases/agp-8-9-0-release-notes)

O fluxo de lista e detalhes foi inspirado na referência didática AC322A_MediaTracker enviada para estudo. O tema, os dados, as telas e a lógica da simulação foram preparados para este cardápio.
