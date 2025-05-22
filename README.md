# AFD Reconhecedor em Java

Este projeto implementa um Autômato Finito Determinístico (AFD) em Java, com suporte para:

- Definir estados, transições e alfabeto.
- Processar cadeias e verificar aceitação.
- Reconhecer ocorrências **exatas** de palavras em textos, ignorando prefixos, sufixos e plurais.

## 🧠 Objetivo

Fixar os conceitos de Teoria da Computação por meio da construção prática de um AFD com aplicabilidade real, como o reconhecimento de palavras em textos.

## 📁 Organização atual dos Pacotes

```
src/
├── core/ # Implementação principal do AFD
├── model/ # Estruturas auxiliares (States, StateSymbol)
└── demo/ # Execução de exemplos e testes manuais

```

## 🚀 Como usar

### 1. Definir um AFD

```java
AFDAutomata afd = new AFDAutomata();

afd.defineAlphabet(Set.of('a', 'b'));

State q0 = new State("q0");
State q1 = new State("q1");

afd.defineInitial(q0);
afd.addFinalState(q1);

afd.addTransition(q0, 'a', q1);
afd.addTransition(q1, 'b', q0);

System.out.println(afd.process("ab")); // true

```

### 2. Procurar Uma palavra exata em um texto

```java
AFDAutomata afd = new AFDAutomata();
afd.textSetup("computador");

String text = "O computador é uma máquina. O microcomputador não deve ser contado.";
List<Integer> positions = afd.findOccurrences(text);

// Saída esperada: apenas "computador", ignorando variações da String
```

### 📌 Funcionalidades

- Definição de AFDs por código
- Validação de símbolos com base no alfabeto
- Execução de autômatos para verificar strings
- Reconhecimento de palavras exatas dentro de textos

### 🔧 Tecnologias
- Java 11+
- Nenhuma dependência externa

### 📚 Conceitos envolvidos
- Autômatos Finitos Determinísticos (AFD)
- Reconhecimento de padrões
- Processamento de linguagem natural (básico)
- Teoria da Computação aplicada

### 📈 Possíveis melhorias

- Definição dinâmica de automatos
- Implementação de transdutores
- Representação gráfica
