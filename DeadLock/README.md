**Deadlock**
Um DeadLock acontece quando uma thread A esta bloqueada e B esta bloqueada esperando a thread A ser desbloqueada.
Vamos fazer uma analogia.
Imagine que precisamos programar a travessia de uma ponte que liga dois pontos, essa ponte é unidirecional e só possível atravessar um carro por vez.
Precisamos orquestrar a travessia dos carros na ponte para que não ocorram acidentes.
Imagine o seguinte cenário, um veículo, no momento em que esta atravessando a ponte tenha o pneu seu pneu furado, nesse momento, tem carros de ambos os lados da ponte esperando o carro com o pneu furado completar a travessia, temos um DeadLock.
Deadlocks, na maioria das vezes são criados por seus criadores, normalmente o problema esta no momento onde determinamos qual trecho de nosso código deve ser executado de forma uma única em uma única thread, e em nossa analogia esse momento é  a travessia da ponte.
Com o motivo do DeadLocks Identificado, deveríamos simular o cenário de uma a travessia e furar o pneu de uma carro, tendo esse cenário simulado, poderíamos propor algumas alternativas para a solução.
- Se o pneu estiver furado, continuar a travessia.
- Se um carro furar o pneu no momento da travessia, não permitir que os carros esperem para atravessar.
- Se um carro furar o pneu no momento da travessia abrir a ponte e deixar o carro cair.
As soluções são variadas e podem ser desde soluções de engenharia, tratativas de erro ou infraestrutura, mas normalmente são ocasionadas por erros de implementação.