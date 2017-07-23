**Stream e ParallelStream**
Stream é um fluxo de dados em um sistema computacional, em java a API Stream pode ser definida como, uma sequencia de elementos obtida de uma fonte de dados que suporta operações de agregação (filter, sorted, map, collect).
A partir da versão 8 da linguagem a API Stream foi adicionada a linguagem, existem dois tipos, Stream e ParallelStream.
A principal diferença entre Stream e ParallelStream é que, uma Stream executa processamento de forma sequencial e ParallelStream executa de forma paralelizada de acordo com os núcleos disponíveis da máquina em execução.
De forma simplificada, uma ParallelStream ira dividir processamento da stream em pequenos pedaços e executar a operação em paralelo.
Entendido o funcionamento de uma ParallelStream temos alguns pontos de atenção que devem ser observados na hora de escolher qual implementação utilizar.
- Núcleos da máquina local.
Uma ParallelStream utiliza os núcleos disponíveis da máquina local para paralelizar o processamento, caso a máquina local não tenha muitos processadores ou a máquina local é compartilhada por outras aplicações o uso do ParallelStream deve ser repensado.
- Operações Independentes
Como sabemos um ParallelStream quebra em pequenas partes nossa operação e a executa em paralelo, com isso, precisamos garantir que cada operação seja independente, ou seja o processamento de cada operação não deve depende der ou impactar outro elemento de nossa operação.
- Operação thread-Safe
Um pedaço de código é dito thread-safe se ele apenas manipula estruturas de dados compartilhadas de uma forma que garanta uma execução segura através de vários threads ao mesmo tempo.
Caso o processamento de sua operação utilizar alguma função thread-Safe você não terá os benefícios do processamento paralelo de thread-Safe