# 🖥️ Retro Hardware

Aplicativo Android desenvolvido para a atividade de desenvolvimento mobile, tendo como tema a **História do Hardware na Computação**.

O aplicativo apresenta um catálogo interativo de tecnologias e componentes que marcaram diferentes períodos da evolução da computação, permitindo que o usuário consulte informações sobre cada item por meio de cards e telas de detalhes.

---

## 📚 Tema do aplicativo

O tema escolhido para o aplicativo é **História do Hardware (Computação)**.

A proposta é apresentar alguns dos principais componentes e tecnologias que fizeram parte da evolução dos computadores, desde tecnologias utilizadas nos primeiros equipamentos eletrônicos até o desenvolvimento dos circuitos integrados.

O catálogo aborda os seguintes temas:

* **Válvulas eletrônicas**
* **Cartões perfurados**
* **ENIAC**
* **Primeiros microchips**

O aplicativo busca apresentar essas informações de forma simples e interativa, permitindo que o usuário navegue pelo catálogo e consulte os detalhes de cada tecnologia.

---

## 🎯 Objetivo

O objetivo do Retro Hardware é proporcionar uma forma simples de conhecer tecnologias importantes para a história da computação, relacionando cada item com seu período histórico e sua importância para o desenvolvimento do hardware.

Além da apresentação do conteúdo, o projeto também tem como objetivo demonstrar a utilização de recursos de desenvolvimento Android, autenticação de usuários e armazenamento de dados em um backend.

---

## 📱 Funcionalidades

O aplicativo possui as seguintes funcionalidades:

* Cadastro de usuários;
* Login utilizando e-mail e senha;
* Autenticação através do Firebase Authentication;
* Catálogo de componentes e tecnologias históricas;
* Cards clicáveis para cada item;
* Tela de detalhes dos itens;
* Informações sobre ano, categoria, descrição e importância histórica;
* Logout;
* Dados dos itens carregados através do Cloud Firestore.

---

## 🧩 Wireframes

Antes do desenvolvimento da interface, foram planejadas as principais telas do aplicativo por meio de wireframes.

Os wireframes foram utilizados para definir a organização dos elementos da interface, o fluxo de navegação e a disposição das informações antes da implementação em Jetpack Compose.

### Tela de Login

Tela responsável por permitir que usuários cadastrados acessem o aplicativo utilizando e-mail e senha.

![Wireframe da tela de Login](images/wireframe-login.png)

### Tela de Cadastro

Tela utilizada para a criação de uma nova conta de acesso ao aplicativo.

![Wireframe da tela de Cadastro](images/wireframe-cadastro.png)

### Catálogo

Tela principal do aplicativo, responsável por apresentar os itens históricos em formato de cards.

![Wireframe da tela de Catálogo](images/wireframe-home.png)

### Tela de Detalhes

Tela apresentada quando o usuário seleciona um dos itens do catálogo, exibindo informações mais completas sobre a tecnologia escolhida.

![Wireframe da tela de Detalhes](images/wireframe-detalhes.png)

> **Observação:** Os wireframes representam o planejamento inicial da interface. A versão implementada no aplicativo recebeu uma identidade visual própria baseada no conceito de hardware e computação retrô.

---

## 🗄️ Banco de dados e Backend

O aplicativo utiliza o **Firebase** como infraestrutura de backend.

Foram utilizados dois recursos principais:

### 🔐 Firebase Authentication

O Firebase Authentication é responsável pelo gerenciamento dos usuários do aplicativo.

A autenticação utiliza o método:

**E-mail e senha**

O fluxo de autenticação funciona da seguinte maneira:

```text
Usuário
   ↓
Tela de Login
   ↓
Firebase Authentication
   ↓
Usuário autenticado
   ↓
Catálogo do aplicativo
```

Também é possível realizar o cadastro de novos usuários através da tela de registro.

### ☁️ Cloud Firestore

O **Cloud Firestore** é utilizado para armazenar os dados referentes aos itens do catálogo.

Os dados são organizados em uma coleção chamada:

```text
hardware
```

Cada documento representa um item da história do hardware.

Exemplo de estrutura:

```text
hardware
│
├── valvulas
│   ├── nome
│   ├── ano
│   ├── categoria
│   ├── descricao
│   ├── importancia
│   └── componentes
│
├── cartoes_perfurados
│   ├── nome
│   ├── ano
│   ├── categoria
│   ├── descricao
│   ├── importancia
│   └── componentes
│
├── eniac
│   ├── nome
│   ├── ano
│   ├── categoria
│   ├── descricao
│   ├── importancia
│   └── componentes
│
└── primeiro_microchip
    ├── nome
    ├── ano
    ├── categoria
    ├── descricao
    ├── importancia
    └── componentes
```

O aplicativo realiza uma consulta à coleção `hardware` e utiliza os dados retornados pelo Firestore para montar os cards e as telas de detalhes.

O fluxo pode ser representado da seguinte forma:

```text
Cloud Firestore
      ↓
FirebaseRepository
      ↓
HardwareItem
      ↓
HomeScreen
      ↓
HardwareCard
      ↓
DetailScreen
```

---

## 🎨 Identidade visual

A interface foi desenvolvida utilizando uma identidade visual inspirada na estética de computadores e equipamentos eletrônicos antigos.

O nome **Retro Hardware** foi utilizado para reforçar a proposta do aplicativo.

A interface utiliza elementos como:

* Ícones relacionados à computação;
* Cards com cantos arredondados;
* Hierarquia visual entre títulos e informações;
* Cores inspiradas em equipamentos eletrônicos e tecnologia;
* Organização em formato de linha do tempo;
* Botões e componentes do Material 3.

A identidade visual tem como objetivo combinar uma aparência moderna com referências visuais ao tema retrô da computação.

---

## 🛠️ Tecnologias utilizadas

* **Kotlin** — linguagem de programação;
* **Jetpack Compose** — construção da interface gráfica;
* **Material 3** — componentes e identidade visual da interface;
* **Firebase Authentication** — autenticação de usuários;
* **Cloud Firestore** — armazenamento dos dados do catálogo;
* **Android Studio** — ambiente de desenvolvimento.

---

## 📂 Estrutura do projeto

A aplicação foi organizada de forma a separar os principais componentes do projeto:

```text
com.example.retrohardware
│
├── data
│   ├── AuthRepository.kt
│   ├── FirebaseRepository.kt
│   └── HardwareItem.kt
│
├── navigation
│   └── AppNavigation.kt
│
├── screens
│   ├── LoginScreen.kt
│   ├── RegisterScreen.kt
│   ├── HomeScreen.kt
│   └── DetailScreen.kt
│
└── ui
    └── theme
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

---

## 🔄 Fluxo de navegação

O fluxo principal do aplicativo pode ser representado da seguinte forma:

```text
             ┌──────────────┐
             │    Login     │
             └──────┬───────┘
                    │
          ┌─────────┴─────────┐
          │                   │
          ▼                   ▼
   ┌─────────────┐      ┌─────────────┐
   │   Cadastro  │      │    Login    │
   └──────┬──────┘      └──────┬──────┘
          │                    │
          └──────────┬─────────┘
                     ▼
              ┌─────────────┐
              │    Home     │
              │  Catálogo   │
              └──────┬──────┘
                     │
                     ▼
              ┌─────────────┐
              │  Detalhes   │
              │  do item    │
              └─────────────┘
```

O usuário precisa estar autenticado para acessar o catálogo. A partir da tela principal, cada card pode ser selecionado para visualizar informações detalhadas sobre o item.

---

## 📌 Conclusão

O Retro Hardware combina uma interface desenvolvida com Jetpack Compose, autenticação através do Firebase Authentication e armazenamento de informações utilizando o Cloud Firestore.

A aplicação foi desenvolvida com o objetivo de unir a aprendizagem de desenvolvimento Android com o estudo da evolução histórica do hardware e da computação.

---

## 👨‍💻 Projeto acadêmico

Projeto desenvolvido como atividade acadêmica de desenvolvimento de aplicativos Android.

**Tema:** História do Hardware (Computação)

**Plataforma:** Android

**Linguagem:** Kotlin

**Interface:** Jetpack Compose

**Backend:** Firebase
