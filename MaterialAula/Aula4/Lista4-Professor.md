# Lista de Exercício 4

**1. Qual padrão é usado para migrar gradualmente de um monólito para microsserviços, envolvendo o sistema legado?**  
A) Circuit Breaker  
B) SAGA  
C) Strangler Fig  
D) CQRS  
✅ **Resposta: C) Strangler Fig**

**2. No Domain-Driven Design, qual conceito define uma área com linguagem e modelo de domínio próprios?**  
A) Entity  
B) Aggregate Root  
C) Bounded Context  
D) Value Object  
✅ **Resposta: C) Bounded Context**

**3. Qual componente atua como ponto único de entrada para clientes em uma arquitetura de microsserviços?**  
A) Service Discovery  
B) Load Balancer  
C) API Gateway  
D) Message Broker  
✅ **Resposta: C) API Gateway**

**4. O padrão Circuit Breaker é usado principalmente para:**  
A) Aumentar a velocidade de comunicação  
B) Evitar falhas em cascata ao detectar serviços indisponíveis  
C) Compartilhar estado entre serviços  
D) Criar logs estruturados  
✅ **Resposta: B) Evitar falhas em cascata ao detectar serviços indisponíveis**

**5. Em uma SAGA orquestrada, quem controla o fluxo das transações?**  
A) O banco de dados central  
B) Um serviço coordenador (orchestrator)  
C) Os eventos publicados pelos serviços  
D) O API Gateway  
✅ **Resposta: B) Um serviço coordenador (orchestrator)**

**6. Qual ferramenta é comumente usada para Service Discovery em ambientes Java/Spring?**  
A) RabbitMQ  
B) Hystrix  
C) Eureka  
D) Grafana  
✅ **Resposta: C) Eureka**

**7. Qual tipo de comunicação entre microsserviços NÃO bloqueia o serviço chamador enquanto aguarda resposta?**  
A) REST síncrono  
B) gRPC  
C) Chamada direta via HTTP  
D) Mensageria assíncrona  
✅ **Resposta: D) Mensageria assíncrona**

**8. O que o padrão SAGA resolve em microsserviços?**  
A) Autenticação centralizada  
B) Consistência de dados em operações distribuídas  
C) Descoberta de serviços  
D) Versionamento de APIs  
✅ **Resposta: B) Consistência de dados em operações distribuídas**

**9. Qual das opções NÃO é uma boa prática para segurança em microsserviços?**  
A) Usar mTLS entre serviços  
B) Compartilhar banco de dados para facilitar transações  
C) Gerenciar secrets com Vault ou KMS  
D) Validar JWT em cada serviço  
✅ **Resposta: B) Compartilhar banco de dados para facilitar transações**

**10. Qual padrão arquitetural separa operações de leitura e escrita?**  
A) Hexagonal  
B) Event Sourcing  
C) CQRS  
D) Strangler Fig  
✅ **Resposta: C) CQRS**