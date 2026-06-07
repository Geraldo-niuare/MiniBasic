import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;
import symbols.TabDeSimbolos;
import java.io.StringReader;

/**
 * Main.java — Ponto de entrada e suite de testes do Mini BASIC Lexer.
 *
 * Executa 10 testes cobrindo todas as regras léxicas implementadas.
 */
public class Main {

    static int testesPassaram = 0;
    static int testesFalharam = 0;

    // ----------------------------------------------------------------
    // Método auxiliar: corre um teste e imprime os tokens
    // ----------------------------------------------------------------
    static void testar(String nome, String codigo, boolean esperaErro) throws Exception {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║ TESTE: " + nome);
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("CODIGO FONTE:");
        System.out.println(codigo);
        System.out.println("\nTOKENS GERADOS:");

        try {
            Lexer lexer = new Lexer(new StringReader(codigo));
            Token t;
            int count = 0;
            while ((t = lexer.yylex()).type != TokenType.EOF) {
                System.out.println("  " + t);
                count++;
            }
            System.out.println("  Token(EOF        | valor: \"\"           | linha: fim)");
            System.out.printf("\n  Total: %d tokens%n", count + 1);

            if (esperaErro) {
                System.out.println("  [FALHOU] — esperava erro mas nao ocorreu!");
                testesFalharam++;
            } else {
                System.out.println("  [OK] — Teste passou!");
                testesPassaram++;
            }
        } catch (RuntimeException e) {
            if (esperaErro) {
                System.out.println("  ERRO APANHADO (esperado): " + e.getMessage());
                System.out.println("  [OK] — Teste passou!");
                testesPassaram++;
            } else {
                System.out.println("  [FALHOU] — erro inesperado: " + e.getMessage());
                testesFalharam++;
            }
        }
    }

    // ----------------------------------------------------------------
    // Main
    // ----------------------------------------------------------------
    public static void main(String[] args) throws Exception {

        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║     MINI BASIC — SUITE DE TESTES LEXER     ║");
        System.out.println("╚════════════════════════════════════════════╝");

        // --- TESTE 1: Atribuição simples (R1, R2, R3, R4) ---
        testar("T1 — Atribuicao simples",
            "LET x = 10",
            false);

        // --- TESTE 2: Aritmética (R5) ---
        testar("T2 — Operadores aritmeticos",
            "LET resultado = 10 + 2 * 3 - 8 / 4",
            false);

        // --- TESTE 3: Comparação simples (R6) ---
        testar("T3 — Comparacao simples (> <)",
            "IF x > 5 THEN\n    PRINT x\nENDIF",
            false);

        // --- TESTE 4: Comparação composta (R7) ---
        testar("T4 — Comparacao composta (>= <= == !=)",
            "IF x >= 10 THEN\n    PRINT 1\nENDIF\nIF y <= 5 THEN\n    PRINT 0\nENDIF",
            false);

        // --- TESTE 5: Parênteses (R8) ---
        testar("T5 — Parenteses",
            "LET x = (3 + 2) * (10 - 4)",
            false);

        // --- TESTE 6: Espaços e newlines ignorados (R9) ---
        testar("T6 — Espacos e newlines ignorados",
            "LET   x   =   10\n\n\nPRINT   x",
            false);

        // --- TESTE 7: Comentários REM ignorados (R10) ---
        testar("T7 — Comentarios REM",
            "REM Este e um comentario\nLET x = 5\nREM Outro comentario\nPRINT x",
            false);

        // --- TESTE 8: Programa completo — Fatorial ---
        testar("T8 — Programa completo: Fatorial de 5",
            "REM Calcula fatorial de n\n" +
            "LET n = 5\n" +
            "LET resultado = 1\n" +
            "WHILE n > 0\n" +
            "    LET resultado = resultado * n\n" +
            "    LET n = n - 1\n" +
            "ENDWHILE\n" +
            "PRINT resultado",
            false);

        // --- TESTE 9: Programa aninhado ---
        testar("T9 — WHILE aninhado com IF",
            "LET x = 1\n" +
            "WHILE x < 4\n" +
            "    IF x == 2 THEN\n" +
            "        PRINT x\n" +
            "    ENDIF\n" +
            "    LET x = x + 1\n" +
            "ENDWHILE",
            false);

        // --- TESTE 10: Erro léxico — caractere inválido (R12) ---
        testar("T10 — Erro lexico: caractere invalido '@'",
            "LET x = 10 @ 2",
            true);

        // --- Resumo ---
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║               RESUMO DOS TESTES            ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.printf( "║  Passaram: %-31d║%n", testesPassaram);
        System.out.printf( "║  Falharam: %-31d║%n", testesFalharam);
        System.out.printf( "║  Total:    %-31d║%n", testesPassaram + testesFalharam);
        System.out.println("╚════════════════════════════════════════════╝");

        // --- Demonstrar Tabela de Símbolos ---
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║         DEMO: TABELA DE SIMBOLOS           ║");
        System.out.println("╚════════════════════════════════════════════╝");
        TabDeSimbolos tab = new TabDeSimbolos();
        tab.definir("n", 5);
        tab.definir("resultado", 1);
        tab.definir("x", 10);
        tab.imprimir();

        try {
            tab.obter("varNaoDefinida");
        } catch (RuntimeException e) {
            System.out.println("Erro apanhado (esperado): " + e.getMessage());
        }
    }
}
