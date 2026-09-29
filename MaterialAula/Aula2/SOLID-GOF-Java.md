# Princípios SOLID

SOLID é um acrônimo que representa cinco princípios fundamentais da programação orientada a objetos, criados para tornar o software mais flexível, manutenível e escalável.

## 1. S — Single Responsibility Principle (Princípio da Responsabilidade Única)

"Uma classe deve ter apenas uma responsabilidade" 

- Exemplo Ruim:

```java
public class Relatorio {
    public void gerarRelatorio() { /* ... */ }
    public void salvarRelatorio() { /* ... */ }
    public void enviarEmail() { /* ... */ }
}
```

- Exemplo Bom:

```java
public class GeradorRelatorio {
    public void gerar() { /* lógica de geração */ }
}

public class ArmazenadorRelatorio {
    public void salvar(String relatorio) { /* lógica de persistência */ }
}

public class NotificadorEmail {
    public void enviar(String destinatario, String conteudo) { /* envio */ }
}
```

## 2. O — Open/Closed Principle (Princípio Aberto/Fechado)

"Entidades devem estar abertas para extensão, mas fechadas para modificação." 

- Exemplo Ruim:

```java
public class CalculadoraDesconto {
    public double calcularDesconto(String tipoCliente, double valor) {
        if (tipoCliente.equals("VIP")) return valor * 0.2;
        if (tipoCliente.equals("NORMAL")) return valor * 0.1;
        return 0;
    }
}
```

- Exemplo Bom:

```java
interface DescontoStrategy {
    double aplicarDesconto(double valor);
}

class DescontoVIP implements DescontoStrategy {
    public double aplicarDesconto(double valor) { return valor * 0.2; }
}

class DescontoNormal implements DescontoStrategy {
    public double aplicarDesconto(double valor) { return valor * 0.1; }
}

class CalculadoraDesconto {
    public double calcular(DescontoStrategy strategy, double valor) {
        return strategy.aplicarDesconto(valor);
    }
}
```

## 3. L — Liskov Substitution Principle (Princípio da Substituição de Liskov)

"Objetos de uma superclasse devem ser substituíveis por objetos de suas subclasses sem quebrar o comportamento do programa." 

- Exemplo Ruim:

```java
class Ave {
    void voar() { System.out.println("Voando..."); }
}

class Pinguim extends Ave {
    @Override
    void voar() { throw new UnsupportedOperationException("Pinguim não voa!"); }
}
```

- Exemplo Bom:

```java
abstract class Ave { }

class AveQueVoa extends Ave {
    void voar() { System.out.println("Voando..."); }
}

class Pinguim extends Ave {
    void nadar() { System.out.println("Nadando..."); }
}
```

## 4. I — Interface Segregation Principle (Princípio da Segregação de Interface)

"Clientes não devem ser forçados a depender de interfaces que não utilizam." 

- Exemplo Ruim:

```java
interface Impressora {
    void imprimir();
    void escanear();
    void fax();
}

class ImpressoraSimples implements Impressora {
    public void imprimir() { /* ok */ }
    public void escanear() { throw new UnsupportedOperationException(); }
    public void fax() { throw new UnsupportedOperationException(); }
}
```

- Exemplo Bom:

```java
interface Impressora {
    void imprimir();
}

interface Scanner {
    void escanear();
}

interface Fax {
    void enviarFax();
}

class ImpressoraSimples implements Impressora {
    public void imprimir() { /* ok */ }
}

class Multifuncional implements Impressora, Scanner, Fax {
    public void imprimir() { /* ... */ }
    public void escanear() { /* ... */ }
    public void enviarFax() { /* ... */ }
}
```

## 5. D — Dependency Inversion Principle (Princípio da Inversão de Dependência)

"Dependa de abstrações, não de implementações concretas." 

- Exemplo Ruim:

```java
class Motor {
    void ligar() { System.out.println("Motor ligado."); }
}

class Carro {
    private Motor motor = new Motor(); // dependência concreta!
    void acelerar() { motor.ligar(); }
}
```

- Exemplo Bom:

```java
interface IMotor {
    void ligar();
}

class MotorEletrico implements IMotor {
    public void ligar() { System.out.println("Motor elétrico ligado."); }
}

class Carro {
    private IMotor motor;

    public Carro(IMotor motor) { // injeção de dependência
        this.motor = motor;
    }

    void acelerar() { motor.ligar(); }
}

// Uso:
Carro carro = new Carro(new MotorEletrico());
```

# Padrões de Projeto GoF (Gang of Four)

Os 23 Padrões de Projeto GoF são divididos em 3 categorias:

- **Criação** — como objetos são criados.
    - Abstract Factory
    - Builder
    - Factory Method
    - Prototype
    - Singleton 
- **Estruturais** — como objetos são compostos.
    - Adapter 
    - Bridge 
    - Composite 
    - Decorator
    - Facade
    - Flyweight
    - Proxy 
- **Comportamentais** — como objetos interagem.
    - Chain of Responsibility 
    - Command 
    - Interpreter 
    - Iterator
    - Mediator
    - Memento
    - Observer 
    - State 
    - Strategy 
    - Template Method 
    - Visitor 

Vamos ver exemplos práticos dos mais importantes.

## Padrões de Criação

### 1. Singleton

Garante que uma classe tenha apenas uma instância e fornece um ponto global de acesso a ela.

```java
public class ConexaoBanco {
    private static ConexaoBanco instancia;

    private ConexaoBanco() { }

    public static synchronized ConexaoBanco getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoBanco();
        }
        return instancia;
    }

    public void conectar() { System.out.println("Conectado ao banco."); }
}
```

⚠️ Use com cuidado! Pode dificultar testes. 

### 2. Factory Method

Define uma interface para criar um objeto, mas deixa as subclasses decidirem qual classe instanciar.

```java
interface Forma {
    void desenhar();
}

class Circulo implements Forma {
    public void desenhar() { System.out.println("Desenhando círculo."); }
}

class Quadrado implements Forma {
    public void desenhar() { System.out.println("Desenhando quadrado."); }
}

abstract class FabricaForma {
    abstract Forma criarForma();
}

class FabricaCirculo extends FabricaForma {
    Forma criarForma() { return new Circulo(); }
}

class FabricaQuadrado extends FabricaForma {
    Forma criarForma() { return new Quadrado(); }
}
```

### 3. Builder

Separa a construção de um objeto complexo de sua representação.

```java
class Pizza {
    private String massa;
    private String molho;
    private String cobertura;

    private Pizza(Builder builder) {
        this.massa = builder.massa;
        this.molho = builder.molho;
        this.cobertura = builder.cobertura;
    }

    public static class Builder {
        private String massa;
        private String molho;
        private String cobertura;

        public Builder comMassa(String massa) { this.massa = massa; return this; }
        public Builder comMolho(String molho) { this.molho = molho; return this; }
        public Builder comCobertura(String cobertura) { this.cobertura = cobertura; return this; }

        public Pizza build() { return new Pizza(this); }
    }

    @Override
    public String toString() {
        return "Pizza{" + "massa='" + massa + '\'' + ", molho='" + molho + '\'' + ", cobertura='" + cobertura + '\'' + '}';
    }
}

// Uso:
Pizza pizza = new Pizza.Builder()
    .comMassa("fina")
    .comMolho("tomate")
    .comCobertura("queijo")
    .build();
```

### 4. Abstract Factory

Fornece uma interface para criar famílias de objetos relacionados sem especificar suas classes concretas, enquanto o Factory method cria apenas um objeto a partir do método.

```java
interface Botao {
    void render();
}

interface Checkbox {
    void marcar();
}

class BotaoWindows implements Botao {
    public void render() { System.out.println("Botão estilo Windows"); }
}

class CheckboxWindows implements Checkbox {
    public void marcar() { System.out.println("Checkbox Windows marcado"); }
}

class BotaoMac implements Botao {
    public void render() { System.out.println("Botão estilo Mac"); }
}

class CheckboxMac implements Checkbox {
    public void marcar() { System.out.println("Checkbox Mac marcado"); }
}

// Fábrica abstrata (cria família de produtos)
interface FabricaUI {
    Botao criarBotao();
    Checkbox criarCheckbox();
}

class FabricaWindows implements FabricaUI {
    public Botao criarBotao() { return new BotaoWindows(); }
    public Checkbox criarCheckbox() { return new CheckboxWindows(); }
}

class FabricaMac implements FabricaUI {
    public Botao criarBotao() { return new BotaoMac(); }
    public Checkbox criarCheckbox() { return new CheckboxMac(); }
}

// Uso
public class ExemploAbstractFactory {
    public static void main(String[] args) {
        FabricaUI fabrica = new FabricaWindows(); // ou new FabricaMac()

        Botao botao = fabrica.criarBotao();
        Checkbox checkbox = fabrica.criarCheckbox();

        botao.render();
        checkbox.marcar();
    }
}
```

### 5. Prototype

Permite criar novos objetos copiando instâncias existentes, evitando recriação manual. Utilizando quando a criação de um objeto é cara e complexa e você precisa de muitas variações sem ter que criar novamente.

```java
interface Documento extends Cloneable {
    Documento clonar();
    void exibir();
}

class Relatorio implements Documento {
    private String conteudo;

    public Relatorio(String conteudo) {
        this.conteudo = conteudo;
    }

    public Documento clonar() {
        try {
            return (Relatorio) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void exibir() {
        System.out.println("Relatório: " + conteudo);
    }
}

// Uso
public class ExemploPrototype {
    public static void main(String[] args) {
        Relatorio original = new Relatorio("Dados financeiros 2025");
        Relatorio copia = (Relatorio) original.clonar();

        original.exibir();
        copia.exibir();
    }
}
```

## Padrões Estruturais

### 1. Adapter

Permite que classes com interfaces incompatíveis trabalhem juntas.

```java
interface TomadaEuropeia {
    void conectar();
}

class TomadaBrasileira {
    void plugBr() { System.out.println("Plugado na tomada BR."); }
}

class AdaptadorTomada implements TomadaEuropeia {
    private TomadaBrasileira tomada;

    public AdaptadorTomada(TomadaBrasileira tomada) {
        this.tomada = tomada;
    }

    public void conectar() {
        tomada.plugBr();
    }
}
```

### 2. Decorator

Adiciona responsabilidades dinamicamente a um objeto.

```java
interface Cafe {
    double custo();
    String descricao();
}

class CafeSimples implements Cafe {
    public double custo() { return 5.0; }
    public String descricao() { return "Café Simples"; }
}

abstract class DecoratorCafe implements Cafe {
    protected Cafe cafe;

    public DecoratorCafe(Cafe cafe) {
        this.cafe = cafe;
    }
}

class LeiteDecorator extends DecoratorCafe {
    public LeiteDecorator(Cafe cafe) { super(cafe); }

    public double custo() { return cafe.custo() + 2.0; }
    public String descricao() { return cafe.descricao() + ", com leite"; }
}

// Uso:
Cafe meuCafe = new LeiteDecorator(new CafeSimples());
System.out.println(meuCafe.descricao() + " - R$" + meuCafe.custo());
```

### 3. Facade

Fornece uma interface simplificada para um conjunto complexo de subsistemas.

```java
class SistemaAudio {
    void ligar() { System.out.println("Áudio ligado."); }
}

class SistemaVideo {
    void ligar() { System.out.println("Vídeo ligado."); }
}

class SistemaLuz {
    void diminuir() { System.out.println("Luzes diminuídas."); }
}

class HomeTheaterFacade {
    private SistemaAudio audio;
    private SistemaVideo video;
    private SistemaLuz luz;

    public HomeTheaterFacade() {
        this.audio = new SistemaAudio();
        this.video = new SistemaVideo();
        this.luz = new SistemaLuz();
    }

    void assistirFilme() {
        luz.diminuir();
        video.ligar();
        audio.ligar();
        System.out.println("Filme começando...");
    }
}

// Uso:
HomeTheaterFacade home = new HomeTheaterFacade();
home.assistirFilme();
```

### 4. Bridge

Separa uma abstração de sua implementação, permitindo que ambas evoluam independentemente.

```java
interface Dispositivo {
    void ligar();
    void desligar();
}

class TV implements Dispositivo {
    public void ligar() { System.out.println("TV ligada"); }
    public void desligar() { System.out.println("TV desligada"); }
}

class Radio implements Dispositivo {
    public void ligar() { System.out.println("Rádio ligado"); }
    public void desligar() { System.out.println("Rádio desligado"); }
}

abstract class ControleRemoto {
    protected Dispositivo dispositivo;
    public ControleRemoto(Dispositivo dispositivo) { this.dispositivo = dispositivo; }
    public abstract void ligar();
    public abstract void desligar();
}

class ControleSimples extends ControleRemoto {
    public ControleSimples(Dispositivo dispositivo) { super(dispositivo); }
    public void ligar() { dispositivo.ligar(); }
    public void desligar() { dispositivo.desligar(); }
}

// Uso
public class ExemploBridge {
    public static void main(String[] args) {
        ControleRemoto controle = new ControleSimples(new TV());
        controle.ligar();
        controle.desligar();
    }
}
```

> Permite adicionar novos dispositivos sem mexer no código do controle e vice-versa.

### 5. Composite

Permite tratar objetos individuais e composições de objetos da mesma forma.

```java
interface Forma {
    void desenhar();
}

class Circulo implements Forma {
    public void desenhar() { System.out.println("Desenhando círculo"); }
}

class Quadrado implements Forma {
    public void desenhar() { System.out.println("Desenhando quadrado"); }
}

// Composite
class GrupoFormas implements Forma {
    private List<Forma> formas = new ArrayList<>();

    public void adicionar(Forma forma) { formas.add(forma); }
    public void desenhar() {
        for (Forma forma : formas) {
            forma.desenhar();
        }
    }
}

// Uso
public class ExemploComposite {
    public static void main(String[] args) {
        GrupoFormas grupo = new GrupoFormas();
        grupo.adicionar(new Circulo());
        grupo.adicionar(new Quadrado());
        grupo.desenhar(); // Desenha todas as formas
    }
}
```

### 6. Flyweight

Minimiza o uso de memória compartilhando partes imutáveis do estado entre objetos.

```java
// Flyweight
class CirculoFlyweight {
    private String cor; // estado intrínseco (compartilhado)
    public CirculoFlyweight(String cor) { this.cor = cor; }
    public void desenhar(int x, int y) {
        System.out.println("Círculo " + cor + " em (" + x + "," + y + ")");
    }
}

class FabricaCirculos {
    private static final Map<String, CirculoFlyweight> circulos = new HashMap<>();

    public static CirculoFlyweight getCirculo(String cor) {
        return circulos.computeIfAbsent(cor, c -> new CirculoFlyweight(c));
    }
}

// Uso
public class ExemploFlyweight {
    public static void main(String[] args) {
        CirculoFlyweight vermelho = FabricaCirculos.getCirculo("vermelho");
        vermelho.desenhar(10, 20);

        CirculoFlyweight vermelho2 = FabricaCirculos.getCirculo("vermelho");
        vermelho2.desenhar(30, 40); // Reusa o mesmo objeto
    }
}
```

### 7. Proxy

Fornece um substituto para controlar o acesso a um objeto real.

```java
interface Servico {
    void requisitar();
}

class ServicoReal implements Servico {
    public void requisitar() {
        System.out.println("Serviço real executado.");
    }
}

class ServicoProxy implements Servico {
    private ServicoReal servicoReal;

    public void requisitar() {
        if (servicoReal == null) {
            servicoReal = new ServicoReal();
        }
        System.out.println("Proxy: verificação antes de acessar o serviço real.");
        servicoReal.requisitar();
    }
}

// Uso
public class ExemploProxy {
    public static void main(String[] args) {
        Servico servico = new ServicoProxy();
        servico.requisitar();
    }
}
```

> Permite controle de acesso, lazy loading ou cache antes de chamar o objeto real.

## Padrões Comportamentais

### 1. Strategy

Define uma família de algoritmos, encapsula cada um e os torna intercambiáveis.

```java
interface EstrategiaOrdenacao {
    void ordenar(int[] array);
}

class OrdenacaoBubbleSort implements EstrategiaOrdenacao {
    public void ordenar(int[] array) {
        // implementação bubble sort
        System.out.println("Ordenando com Bubble Sort");
    }
}

class OrdenacaoQuickSort implements EstrategiaOrdenacao {
    public void ordenar(int[] array) {
        // implementação quick sort
        System.out.println("Ordenando com Quick Sort");
    }
}

class ContextoOrdenacao {
    private EstrategiaOrdenacao estrategia;

    public void setEstrategia(EstrategiaOrdenacao estrategia) {
        this.estrategia = estrategia;
    }

    public void executarOrdenacao(int[] array) {
        estrategia.ordenar(array);
    }
}
```

### 2. Observer

Define uma dependência um-para-muitos entre objetos, de forma que, quando um objeto muda de estado, todos os seus dependentes são notificados.

```java
import java.util.ArrayList;
import java.util.List;

interface Observador {
    void atualizar(String mensagem);
}

class Usuario implements Observador {
    private String nome;

    public Usuario(String nome) { this.nome = nome; }

    public void atualizar(String mensagem) {
        System.out.println(nome + " recebeu: " + mensagem);
    }
}

class Canal {
    private List<Observador> inscritos = new ArrayList<>();

    public void inscrever(Observador obs) {
        inscritos.add(obs);
    }

    public void notificar(String mensagem) {
        for (Observador obs : inscritos) {
            obs.atualizar(mensagem);
        }
    }
}

// Uso:
Canal canal = new Canal();
canal.inscrever(new Usuario("Alice"));
canal.inscrever(new Usuario("Bob"));
canal.notificar("Novo vídeo publicado!");
```

### 3. Command

Encapsula uma solicitação como um objeto, permitindo parametrizar clientes com diferentes solicitações.

```java
interface Comando {
    void executar();
}

class Luz {
    void ligar() { System.out.println("Luz ligada."); }
    void desligar() { System.out.println("Luz desligada."); }
}

class ComandoLigarLuz implements Comando {
    private Luz luz;
    public ComandoLigarLuz(Luz luz) { this.luz = luz; }
    public void executar() { luz.ligar(); }
}

class ComandoDesligarLuz implements Comando {
    private Luz luz;
    public ComandoDesligarLuz(Luz luz) { this.luz = luz; }
    public void executar() { luz.desligar(); }
}

class ControleRemoto {
    private Comando comando;

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public void pressionarBotao() {
        comando.executar();
    }
}
```

### 4. Chain of Responsibility

Permite passar uma solicitação por uma cadeia de manipuladores até que um deles a processe.

```java
abstract class Handler {
    protected Handler proximo;
    public void setProximo(Handler proximo) { this.proximo = proximo; }
    public abstract void handle(String pedido);
}

class SuporteBasico extends Handler {
    public void handle(String pedido) {
        if (pedido.equals("simples")) {
            System.out.println("Suporte Básico resolveu o problema.");
        } else if (proximo != null) {
            proximo.handle(pedido);
        }
    }
}

class SuporteAvancado extends Handler {
    public void handle(String pedido) {
        if (pedido.equals("complexo")) {
            System.out.println("Suporte Avançado resolveu o problema.");
        } else if (proximo != null) {
            proximo.handle(pedido);
        }
    }
}

// Uso
public class ExemploChain {
    public static void main(String[] args) {
        Handler basico = new SuporteBasico();
        Handler avancado = new SuporteAvancado();
        basico.setProximo(avancado);

        basico.handle("complexo");
    }
}
```

### 5. Interpreter

Define uma gramática e um interpretador para processar sentenças dessa gramática.

```java
interface Expressao {
    int interpretar();
}

class Numero implements Expressao {
    private int numero;
    public Numero(int numero) { this.numero = numero; }
    public int interpretar() { return numero; }
}

class Soma implements Expressao {
    private Expressao esquerda, direita;
    public Soma(Expressao e, Expressao d) { esquerda = e; direita = d; }
    public int interpretar() { return esquerda.interpretar() + direita.interpretar(); }
}

// Uso
public class ExemploInterpreter {
    public static void main(String[] args) {
        Expressao exp = new Soma(new Numero(5), new Numero(3));
        System.out.println("Resultado: " + exp.interpretar());
    }
}
```

> Útil para linguagens simples ou regras de negócio baseadas em expressões.

### 6. Iterator

Fornece uma maneira de acessar elementos de uma coleção sem expor sua implementação interna.

```java
interface MeuIterator<T> {
    boolean hasNext();
    T next();
}

interface Colecao<T> {
    MeuIterator<T> criarIterator();
}

class ListaSimples<T> implements Colecao<T> {
    private T[] itens;
    private int tamanho = 0;

    @SuppressWarnings("unchecked")
    public ListaSimples(int capacidade) {
        itens = (T[]) new Object[capacidade];
    }

    public void adicionar(T item) {
        if (tamanho < itens.length) {
            itens[tamanho++] = item;
        }
    }

    public MeuIterator<T> criarIterator() {
        return new ListaIterator();
    }

    // Implementação do Iterator
    private class ListaIterator implements MeuIterator<T> {
        private int indice = 0;
        public boolean hasNext() {
            return indice < tamanho;
        }
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return itens[indice++];
        }
    }
}

// Uso
public class ExemploIterator {
    public static void main(String[] args) {
        ListaSimples<String> lista = new ListaSimples<>(5);
        lista.adicionar("A");
        lista.adicionar("B");
        lista.adicionar("C");

        MeuIterator<String> it = lista.criarIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
```

> Listas geralmente tem a implementação de interator para facilitar a interação entre seu conteúdo.

### 7. Mediator

Centraliza a comunicação entre objetos para reduzir acoplamento.

```java
interface Mediador {
    void enviarMensagem(String msg, Colega origem);
}

class MediadorConcreto implements Mediador {
    private List<Colega> colegas = new ArrayList<>();
    public void adicionar(Colega colega) { colegas.add(colega); }
    public void enviarMensagem(String msg, Colega origem) {
        for (Colega c : colegas) {
            if (c != origem) c.receber(msg);
        }
    }
}

abstract class Colega {
    protected Mediador mediador;
    public Colega(Mediador m) { mediador = m; }
    public void enviar(String msg) { mediador.enviarMensagem(msg, this); }
    public abstract void receber(String msg);
}

class ColegaConcreto extends Colega {
    public ColegaConcreto(Mediador m) { super(m); }
    public void receber(String msg) { System.out.println("Recebeu: " + msg); }
}

// Uso
public class ExemploMediator {
    public static void main(String[] args) {
        MediadorConcreto mediador = new MediadorConcreto();
        Colega c1 = new ColegaConcreto(mediador);
        Colega c2 = new ColegaConcreto(mediador);
        mediador.adicionar(c1);
        mediador.adicionar(c2);

        c1.enviar("Olá!");
    }
}
```

### 8. Memento

Permite salvar e restaurar o estado de um objeto sem expor sua implementação interna.

```java
class Memento {
    private String estado;
    public Memento(String estado) { this.estado = estado; }
    public String getEstado() { return estado; }
}

class Editor {
    private String texto;
    public void setTexto(String texto) { this.texto = texto; }
    public String getTexto() { return texto; }
    public Memento salvar() { return new Memento(texto); }
    public void restaurar(Memento m) { texto = m.getEstado(); }
}

// Uso
public class ExemploMemento {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.setTexto("Versão 1");
        Memento backup = editor.salvar();
        editor.setTexto("Versão 2");

        editor.restaurar(backup);
        System.out.println("Texto restaurado: " + editor.getTexto());
    }
}
```

### 9. State

Permite que um objeto altere seu comportamento quando seu estado interno muda.

```java
interface Estado {
    void acao();
}

class EstadoLigado implements Estado {
    public void acao() { System.out.println("Dispositivo ligado"); }
}

class EstadoDesligado implements Estado {
    public void acao() { System.out.println("Dispositivo desligado"); }
}

class Dispositivo {
    private Estado estado;
    public void setEstado(Estado e) { estado = e; }
    public void pressionarBotao() { estado.acao(); }
}

// Uso
public class ExemploState {
    public static void main(String[] args) {
        Dispositivo d = new Dispositivo();
        d.setEstado(new EstadoLigado());
        d.pressionarBotao();

        d.setEstado(new EstadoDesligado());
        d.pressionarBotao();
    }
}
```

### 10. Template Method

Define o esqueleto de um algoritmo, permitindo que subclasses personalizem partes dele.

```java
abstract class Relatorio {
    public final void gerar() {
        cabecalho();
        conteudo();
        rodape();
    }
    protected abstract void cabecalho();
    protected abstract void conteudo();
    protected abstract void rodape();
}

class RelatorioFinanceiro extends Relatorio {
    protected void cabecalho() { System.out.println("Cabeçalho Financeiro"); }
    protected void conteudo() { System.out.println("Conteúdo Financeiro"); }
    protected void rodape() { System.out.println("Rodapé Financeiro"); }
}

// Uso
public class ExemploTemplateMethod {
    public static void main(String[] args) {
        Relatorio r = new RelatorioFinanceiro();
        r.gerar();
    }
}
```

### 11. Visitor

Permite adicionar operações a uma hierarquia de classes sem modificar as classes em si.

```java
interface Elemento {
    void aceitar(Visitante visitante);
}

interface Visitante {
    void visitar(Circulo c);
    void visitar(Quadrado q);
}

class Circulo implements Elemento {
    public void aceitar(Visitante visitante) { visitante.visitar(this); }
}

class Quadrado implements Elemento {
    public void aceitar(Visitante visitante) { visitante.visitar(this); }
}

class DesenharVisitante implements Visitante {
    public void visitar(Circulo c) { System.out.println("Desenhando círculo"); }
    public void visitar(Quadrado q) { System.out.println("Desenhando quadrado"); }
}

// Uso
public class ExemploVisitor {
    public static void main(String[] args) {
        Elemento[] elementos = { new Circulo(), new Quadrado() };
        Visitante desenhista = new DesenharVisitante();
        for (Elemento e : elementos) {
            e.aceitar(desenhista);
        }
    }
}
```

> Facilita adicionar novas operações sem mudar o código dos elementos.
