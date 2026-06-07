package symbols;

import java.util.HashMap;
import java.util.Map;

/**
 * TabDeSimbolos — Tabela de Símbolos do Mini BASIC.
 *
 * Responsabilidade:
 *   Guardar e gerir todas as variáveis declaradas com LET.
 *   Cada variável tem um nome (String) e um valor inteiro (int).
 *
 * Regras semânticas suportadas:
 *   - Variável criada no primeiro LET que a referencia
 *   - Usar variável antes de LET é erro semântico
 *   - Todas as variáveis guardam inteiros
 *   - Redefinição de variável é permitida (LET sobrescreve)
 */
public class TabDeSimbolos {

    private final Map<String, Integer> tabela = new HashMap<>();

    /**
     * Define ou actualiza o valor de uma variável.
     * Se a variável não existir, é criada agora.
     */
    public void definir(String nome, int valor) {
        tabela.put(nome, valor);
    }

    /**
     * Devolve o valor de uma variável.
     * Lança erro semântico se a variável não tiver sido declarada.
     */
    public int obter(String nome) {
        if (!tabela.containsKey(nome)) {
            throw new RuntimeException(
                "ERRO SEMANTICO: variavel '" + nome + "' usada antes de ser definida.");
        }
        return tabela.get(nome);
    }

    /**
     * Verifica se uma variável já foi declarada.
     */
    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    /**
     * Remove todas as variáveis (útil para reset entre testes).
     */
    public void limpar() {
        tabela.clear();
    }

    /**
     * Imprime o conteúdo completo da tabela (útil para debug).
     */
    public void imprimir() {
        System.out.println("=== Tabela de Simbolos ===");
        if (tabela.isEmpty()) {
            System.out.println("  (vazia)");
        } else {
            tabela.forEach((nome, valor) ->
                System.out.printf("  %-15s = %d%n", nome, valor));
        }
        System.out.println("==========================");
    }
}
