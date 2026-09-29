### Lista de Exericios 2

1. Qual princípio SOLID afirma que “uma classe deve ter apenas um motivo para mudar”?  
a) OCP  
b) LSP  
c) SRP  
d) DIP  
**Resposta: c) SRP**  
*Explicação: SRP (Single Responsibility Principle) diz que cada classe deve ter uma única responsabilidade.*

2. O padrão Strategy é mais adequado para resolver qual problema?  
a) Criar objetos sem expor lógica de construção  
b) Garantir uma única instância global  
c) Trocar algoritmos em tempo de execução  
d) Estender funcionalidades sem herança  
**Resposta: c) Trocar algoritmos em tempo de execução**  
*Explicação: Strategy encapsula algoritmos intercambiáveis, permitindo mudar comportamento dinamicamente.*

3. Violar o princípio LSP geralmente resulta em:  
a) Classes muito grandes  
b) Interfaces com muitos métodos  
c) Subclasses que quebram o contrato da classe base  
d) Dependência direta de implementações concretas  
**Resposta: c) Subclasses que quebram o contrato da classe base**  
*Explicação: LSP exige que subclasses possam substituir a base sem alterar o comportamento esperado.*

4. Em qual situação o padrão Factory é mais útil?  
a) Quando precisa de acesso global a uma instância  
b) Quando a lógica de criação de objetos é complexa ou variável  
c) Quando quer adicionar comportamentos dinamicamente  
d) Quando precisa observar mudanças de estado  
**Resposta: b) Quando a lógica de criação de objetos é complexa ou variável**  
*Explicação: Factory encapsula a criação, permitindo flexibilidade sem expor detalhes ao cliente.*

5. O princípio DIP defende que:  
a) Módulos de alto nível devem depender de módulos de baixo nível  
b) Abstrações devem depender de detalhes concretos  
c) Detalhes devem depender de abstrações  
d) Classes devem ser fechadas para extensão  
**Resposta: c) Detalhes devem depender de abstrações**  
*Explicação: DIP inverte a dependência — ambos os níveis dependem de interfaces, não de implementações.*

6. Qual padrão evita que uma classe implemente métodos que não usa?  
a) Singleton  
b) Strategy  
c) ISP (Segregação de Interface)  
d) OCP  
**Resposta: c) ISP (Segregação de Interface)**  
*Explicação: ISP prega interfaces específicas, evitando forçar classes a implementar métodos irrelevantes.*

7. Uma classe “Pato” que herda de “Ave” mas não pode voar viola qual princípio?  
a) SRP  
b) OCP  
c) LSP  
d) DIP  
**Resposta: c) LSP**  
*Explicação: Se “Ave” tem método voar(), “Pato” deve poder substituí-la sem quebrar contratos — caso contrário, viola Liskov.*

8. O que o princípio OCP busca evitar?  
a) Classes com nomes ruins  
b) Modificar código existente para adicionar novas funcionalidades  
c) Usar interfaces  
d) Testes unitários  
**Resposta: b) Modificar código existente para adicionar novas funcionalidades**  
*Explicação: OCP prega “aberto para extensão, fechado para modificação” — novos comportamentos via extensão, não alteração.*

9. Qual padrão é frequentemente criticado por dificultar testes devido a estado global?  
a) Factory  
b) Strategy  
c) Singleton  
d) Observer  
**Resposta: c) Singleton**  
*Explicação: Singleton mantém estado global, o que dificulta isolamento em testes e pode causar efeitos colaterais.*

10. Aplicar SOLID em um sistema legado geralmente começa por qual princípio?  
a) SRP  
b) OCP  
c) DIP  
d) LSP  
**Resposta: c) DIP**  
*Explicação: DIP permite injetar dependências e mocks, viabilizando testes — primeiro passo seguro para refatorar legado.*
