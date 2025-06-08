# **Jogo Resta 1 em Java**  

📌 **Versão simplificada do clássico jogo de tabuleiro "Peg Solitaire"**  

---

## **🎯 Descrição**  

Implementação do jogo **Resta 1** em Java, utilizando matrizes para representar o tabuleiro. O objetivo é eliminar todas as peças exceto uma, deixando-a no centro do tabuleiro.  

**Como jogar:**  

- Movimente peças **pulando sobre outras** (como no jogo de damas).  
- Cada movimento **remove a peça pulada**.  
- O jogo acaba quando **não há mais movimentos possíveis**.  

---

## **⚙️ Funcionalidades**  

✔ **Tabuleiro 7x7** com posições válidas em formato de cruz  
✔ **Validação de movimentos** (horizontal/vertical, distância exata de 2 casas)  
✔ **Contador de peças restantes**  
✔ **Detecção automática de fim de jogo**  
✔ **Interface simples via terminal**  

---

## **🚀 Como Executar**  

1. **Pré-requisitos**:  
   - Java JDK 8+ instalado.  

2. **Compilação e execução**:  

   ```bash
   javac RestaUmSimples.java
   java RestaUmSimples
   ```

3. **Instruções durante o jogo**:  
   - Digite as coordenadas no formato:  

     ```
     linha_origem coluna_origem linha_destino coluna_destino
     ```  

   - Exemplo: `2 4 2 2` (move a peça de (2,4) para (2,2)).  

---

## **📝 Regras do Jogo**  

- **Movimento válido**:  
  - Deve ser **horizontal ou vertical** (não diagonal).  
  - **Sobre uma peça adjacente**, para cair em um espaço vazio.  
  - A peça pulada **é removida**.  

- **Vitória**: Apenas **uma peça resta**, preferencialmente no centro (posição (3,3)).  

---

## **🛠️ Estrutura do Código**  

| Método/Funcionalidade | Descrição |  
|-----------------------|-----------|  
| `inicializarTabuleiro()` | Configura o tabuleiro inicial |  
| `exibirTabuleiro()` | Mostra o estado atual do jogo |  
| `moverPeca()` | Valida e executa movimentos |  
| `jogoTerminado()` | Verifica se há movimentos possíveis |  
| `main()` | Controla o fluxo do jogo |  