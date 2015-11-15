# Salão Beleza Pura #

Este arquivo descreve os passos necessários para executar o simulador do Salão Beleza Pura.

### Apresentação ###

Esta aplicação simula um salão de beleza hipotético chamado *Salão Beleza Pura*. A principal finalidade é de simular as operações em tal salão, que contará com **5 cabeleireiras**, **3 manicures**, **2 depiladoras**, **1 massagista** e **2 caixas**.

Este projeto foi implementado em linguagem **Java**, com o auxílio da IDE **Eclipse**. Foi usado o sistema operacional **Ubuntu**.

### Regras de negócio implementadas ###

a) A chegada de clientes deve ser simulada segundo um critério aleatório de tempo
de chegada entre um e outro variando de 1 a 5 unidades de tempo.

b) Os clientes devem ser atendidos na ordem de chegada e da disponibilidade dos
serviços.

c) A cabelereira alocada para realizar um corte também lava o cabelo do cliente.

d) Cada cliente pode desejar de 1 a todos os serviços oferecidos pelo salão.

e) Um cliente não deve prender outro que esteja atrás de si e que deseja um serviço
que esteja disponível.

f) Todo corte deve ser sempre precedido de uma lavagem.

g) O tempo gasto em cada serviço por cada cliente deve ser gerado aleatoriamente
considerando a seguinte ordem decrescente de duração: penteado, corte, depila-
ção, pés e mãos, massagem e lavagem.

h) O preço de cada serviço é de 50 para penteado, 30 para corte, 40 para corte e
penteado, 0 para lavagem, 30 para pedicure, 40 para depilação e 20 para massa-
gem.

i) Em geral 30% dos clientes desejam todos os serviços, 35% desejam 4, 20% dese-
jam 3, 10% apenas 2 e 5% apenas 1.

j) Os serviços também são procurados segundo um percentual médio de 50% para
corte, 40% para penteado, 30% para pedicure, 20% para depilação, 15% para mas-
sagem.

k) A política adotada pelo dono do estabelecimento é que cada profissional recebe
40% do total faturado por ele durante o dia de trabalho.

l) O salão tem por regra de negócio otimizar o tempo do cliente, atendendo-o da
melhor forma e no menor tempo possível.

m) O sistema deve apresentar um resumo do movimento e do faturamento realizado.

n) Tente construir uma representação na tela do monitor da movimentação nas filas
de entrada, de espera por cada profissional.

### Como executar? ###

1. [Baixe aqui](https://github.com/HiagoMayk/Salao_Beleza_Pura/archive/master.zip) este projeto.
2. Extraia o arquivo .zip que foi baixado anteriormente.
3. Execute o arquivo '**Simulador.jar**'.

OBSERVAÇÃO: Para executar o arquivo acima você pode navegar pelo terminal (do Windows ou do Linux) até a pasta gerada anteriormente e então executar o programa com o comando "**java -jar Simulador.jar**".

### Equipe de desenvolvimento ###

* Hiago Mayk (maykhiago@gmail.com)
* Lucas Simonetti (lucassmcardoso@gmail.com)
* Rubem Kalebe (rubemkalebe@gmail.com)
