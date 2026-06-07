package lexer;

/**
 * Token — Representa uma unidade léxica do Mini BASIC.
 *
 * Cada token tem:
 *   - type  : o tipo (ex: LET, NUMBER, IDENT)
 *   - value : o texto original do código fonte (ex: "LET", "42", "x")
 *   - line  : o número da linha onde foi encontrado (para mensagens de erro)
 */
public class Token {

    public final TokenType type;
    public final String    value;
    public final int       line;

    public Token(TokenType type, String value, int line) {
        this.type  = type;
        this.value = value;
        this.line  = line;
    }

    @Override
    public String toString() {
        return String.format("Token(%-10s | valor: %-12s | linha: %d)",
                             type, "\"" + value + "\"", line);
    }
}
