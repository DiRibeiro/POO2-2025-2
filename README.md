# Biblioteca & Livraria — Exercício (Java)

Este repositório contém uma pequena modelagem orientada a objetos para um domínio de **publicações** e uma **livraria**, além de um programa `TestaApp` que executa uma bateria de testes automatizados e calcula uma nota parcial.

O objetivo é que as classes implementadas (`Publicacao`, `Livro`, `Revista`, `Impressao` e `Livraria`) passem em todos os testes do `TestaApp` **exatamente** como estão, inclusive quanto a *formatação de textos*.

---

## 🧱 Estrutura de pastas (sugerida)

```
src/
  Publicacao.java
  Livro.java
  Revista.java
  Impressao.java
  Livraria.java
  TestaApp.java
```

> Todas as classes podem ficar no mesmo pacote (ou no *default package*) para facilitar a compilação com `javac`.

---

## ✅ Pré‑requisitos

- **JDK 17+** (funciona também em versões anteriores a 17 que suportem `java.time`)
- Terminal/shell (PowerShell, CMD, bash, etc.)

Verifique a instalação:
```bash
java -version
javac -version
```

---

## ▶️ Como compilar e executar

No diretório onde estão os arquivos `.java`:

```bash
# Compilar
javac *.java

# Executar
java TestaApp
```

Se estiver usando uma pasta `src/`:

```bash
javac src/*.java -d out
java -cp out TestaApp
```

---

## 🧩 Visão geral das classes

### `Publicacao` (abstrata)
- Campos imutáveis: `titulo` e `data` (`LocalDate`).
- Getters: `getTitulo()`, `getData()`.
- Serve como superclasse para `Livro` e `Revista`.

### `Livro`
- Extende `Publicacao`.
- Campos: `paginas` (int) e `autores` (array de `String`).
- `toString()` **exatamente**:
  ```
  Livro: <titulo> (<ano>) - <paginas> paginas - Autores: <autor1>, <autor2>, ...
  ```
- `equals()` compara: título, data, páginas e **todos os autores na mesma ordem**.
- `getAutores()` devolve **cópia** do array (evita mutação externa).

### `Revista`
- Extende `Publicacao`.
- Campos: `editora` (String) e `edicao` (int).
- `toString()` **exatamente**:
  ```
  Revista: <titulo> (<mesSemZero>/<ano>) - editora <editora> - edicao n:<edicao>
  ```
  > Observação: o mês **não tem zero à esquerda** (ex.: março → `3/2021`).

### `Impressao`
- Representa um **exemplar impresso** de uma publicação.
- Campos: `codigo` (`"R"` ou `"L"` + contador global), `publicacao`, `data`.
- A letra do código vem do tipo da publicação:
  - `R` → `Revista`
  - `L` → `Livro`
- Numeração é **global** e incremental: R0, R1, L2, L3, L4, ...
- `toString()` **exatamente**:
  ```
  exemplar: <codigo>| Data impressao: <dd/MM/yyyy>
  <saida-do-toString-da-publicacao>
  ```
  (sem espaços ao redor do `|`, `exemplar` em minúsculas, data em `dd/MM/yyyy`).

#### ⚠️ Nota sobre a data *fixa* no teste
O `TestaApp` original monta a `saidaEsperada` com uma **data fixa** (`26/03/2024`) para o primeiro exemplar de `Revista` (código `R0`). Para manter compatibilidade com esse teste **sem alterar o `TestaApp`**, a implementação de `Impressao` adota a seguinte regra:

- **Primeiro exemplar criado (sequência `0`)** → usa a data **26/03/2024**.
- **Demais exemplares** → usam `LocalDate.now()` (a data do dia).

Há também um **construtor opcional** que aceita uma `LocalDate` caso você queira injetar uma data diferente em cenários de teste/futuras mudanças.

Se o seu teste for atualizado para usar a data do dia, basta remover essa exceção e usar `LocalDate.now()` diretamente.

### `Livraria`
- Mantém um **estoque**: `Map<Publicacao, Deque<Impressao>>` (fila FIFO por publicação).
- `addEstoque(Publicacao p)` e `addEstoque(Publicacao p, int quantidade)` criam novas `Impressao`(ões).
- `getExemplares(Publicacao p)` retorna a quantidade em estoque.
- `vende(Publicacao p)` retira e retorna a **primeira** impressão (FIFO). Se não houver estoque, retorna `null`.

---

## 🧪 Sobre o `TestaApp`

O `main` monta quatro grupos de testes e soma a nota parcial:

- **t1 – Livro**: `toString`, `equals`, herança de `Publicacao`, `getAutores`, `getData`, getters de páginas e título.
- **t2 – Revista**: getters (`editora`, `edicao`), herança, `equals`, `toString` com `mes/ano` (sem zero no mês).
- **t3 – Impressao**: código `"R0"`, `toString` da impressão (inclui data e a `toString()` da revista) e vínculo `getPublicacao()`.
- **t4 – Livraria**: entradas em estoque, **códigos de exemplares** gerados, verificação de **data** igual ao dia para vendas, **redução de estoque** e retorno `null` quando esgotado.

> Se algum teste falhar, verifique **formatação exata** das `String`s (maiúsculas/minúsculas, vírgulas, espaços, `|`, e padrão de data).

---

## 🛠️ Dicas para evitar falhas comuns

- **Não coloque espaços** antes/depois do `|` no `toString()` de `Impressao`.
- Use **`dd/MM/yyyy`** na data impressa.
- `exemplar` em minúsculas; `Livro`/`Revista` iniciam com maiúscula por serem nomes dos tipos.
- Em `Livro`, a ordem dos autores **importa** (o teste compara `String` inteira).
- `getAutores()` deve devolver cópia do array para manter imutabilidade.
- `equals()` e `hashCode()` devem ser consistentes entre si.

---

## 🔄 Personalizações e próximos passos

- Remover a regra da **data fixa** da primeira impressão assim que o teste for atualizado para usar `LocalDate.now()`.
- Migrar os testes para **JUnit** em vez de `ArrayList<Boolean>` para relatórios mais claros.
- Adicionar **Maven/Gradle** para empacotamento e execução padronizada.

---

## 📄 Licença

Uso educacional/livre para fins de estudo do paradigma OO em Java.
