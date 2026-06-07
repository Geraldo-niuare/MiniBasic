package lexer;

/**
 * TokenType — Enumeração de todos os tipos de token do Mini BASIC.
 *
 * Categorias:
 *   1. Palavras-chave  — LET, PRINT, IF, THEN, ENDIF, WHILE, ENDWHILE
 *   2. Literais        — NUMBER (inteiros)
 *   3. Identificadores — IDENT (nomes de variáveis)
 *   4. Operadores aritméticos — PLUS, MINUS, TIMES, DIVIDE
 *   5. Operadores de comparação — GT, LT, EQ (usado em comparações == )
 *   6. Atribuição      — ASSIGN ( = )
 *   7. Agrupamento     — LPAREN, RPAREN
 *   8. Fim de ficheiro — EOF
 */
public enum TokenType {

    // --- Palavras-chave ---
    LET,        // LET
    PRINT,      // PRINT
    IF,         // IF
    THEN,       // THEN
    ENDIF,      // ENDIF
    WHILE,      // WHILE
    ENDWHILE,   // ENDWHILE

    // --- Literais ---
    NUMBER,     // ex: 0, 42, 100   (apenas inteiros)

    // --- Identificadores ---
    IDENT,      // ex: x, contador, var2

    // --- Operadores Aritméticos ---
    PLUS,       // +
    MINUS,      // -
    TIMES,      // *
    DIVIDE,     // /

    // --- Operadores de Comparação ---
    GT,         // >
    LT,         // <
    GTE,        // >=
    LTE,        // <=
    EQ,         // == (comparação de igualdade)
    NEQ,        // != (diferente)

    // --- Atribuição ---
    ASSIGN,     // =  (atribuição simples)

    // --- Agrupamento ---
    LPAREN,     // (
    RPAREN,     // )

    // --- Fim de Ficheiro ---
    EOF         // fim do código fonte
}
