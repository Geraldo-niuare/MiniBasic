package lexer;

/* ============================================================
   minibasic.flex — Especificação Léxica do Mini BASIC
   Ferramenta: JFlex
   Geração:    jflex minibasic.flex  →  produz Lexer.java
   ============================================================

   REGRAS LÉXICAS IMPLEMENTADAS:
   ------------------------------
   R1.  Palavras-chave: LET, PRINT, IF, THEN, ENDIF, WHILE, ENDWHILE
   R2.  Identificadores: letra seguida de letras/dígitos  ex: x, contador, var2
   R3.  Números inteiros: um ou mais dígitos              ex: 0, 42, 100
   R4.  Operador de atribuição: =
   R5.  Operadores aritméticos: + - * /
   R6.  Operadores de comparação simples: > <
   R7.  Operadores de comparação compostos: >= <= == !=
   R8.  Parênteses: ( )
   R9.  Espaço em branco: espaços, tabs, newlines — ignorados
   R10. Comentários: linha começada por REM — ignorada
   R11. Fim de ficheiro: devolve token EOF
   R12. Caractere inválido: lança RuntimeException com linha
   ============================================================ */

%%

/* --- Secção de Directivas --- */

%public
%class  Lexer
%type   Token
%line
%unicode

/* --- Macros Auxiliares --- */

LETRA       = [a-zA-Z]
DIGITO      = [0-9]
IDENT       = {LETRA}({LETRA}|{DIGITO})*
NUMERO      = {DIGITO}+
ESPACO      = [ \t\r\n]+
COMENTARIO  = "REM"[^\r\n]*

/* --- Valor devolvido no fim do ficheiro (R11) --- */

%eofval{
    return new Token(TokenType.EOF, "", yyline + 1);
%eofval}

%%

/* ============================================================
   REGRAS DE RECONHECIMENTO
   Ordem importa: palavras-chave ANTES de identificadores,
   operadores compostos (>=, <=) ANTES dos simples (>, <)
   ============================================================ */

/* R10 — Comentários (linha REM — ignorar) */
{COMENTARIO}    { /* ignorar comentário */ }

/* R1 — Palavras-chave (devem vir ANTES de {IDENT}) */
"LET"           { return new Token(TokenType.LET,      yytext(), yyline + 1); }
"PRINT"         { return new Token(TokenType.PRINT,    yytext(), yyline + 1); }
"IF"            { return new Token(TokenType.IF,       yytext(), yyline + 1); }
"THEN"          { return new Token(TokenType.THEN,     yytext(), yyline + 1); }
"ENDIF"         { return new Token(TokenType.ENDIF,    yytext(), yyline + 1); }
"WHILE"         { return new Token(TokenType.WHILE,    yytext(), yyline + 1); }
"ENDWHILE"      { return new Token(TokenType.ENDWHILE, yytext(), yyline + 1); }

/* R2 — Identificadores (vêm depois das palavras-chave) */
{IDENT}         { return new Token(TokenType.IDENT,    yytext(), yyline + 1); }

/* R3 — Números inteiros */
{NUMERO}        { return new Token(TokenType.NUMBER,   yytext(), yyline + 1); }

/* R5 — Operadores aritméticos */
"+"             { return new Token(TokenType.PLUS,     yytext(), yyline + 1); }
"-"             { return new Token(TokenType.MINUS,    yytext(), yyline + 1); }
"*"             { return new Token(TokenType.TIMES,    yytext(), yyline + 1); }
"/"             { return new Token(TokenType.DIVIDE,   yytext(), yyline + 1); }

/* R7 — Operadores compostos (DEVEM vir antes dos simples!) */
">="            { return new Token(TokenType.GTE,      yytext(), yyline + 1); }
"<="            { return new Token(TokenType.LTE,      yytext(), yyline + 1); }
"=="            { return new Token(TokenType.EQ,       yytext(), yyline + 1); }
"!="            { return new Token(TokenType.NEQ,      yytext(), yyline + 1); }

/* R6 — Operadores de comparação simples */
">"             { return new Token(TokenType.GT,       yytext(), yyline + 1); }
"<"             { return new Token(TokenType.LT,       yytext(), yyline + 1); }

/* R4 — Atribuição */
"="             { return new Token(TokenType.ASSIGN,   yytext(), yyline + 1); }

/* R8 — Parênteses */
"("             { return new Token(TokenType.LPAREN,   yytext(), yyline + 1); }
")"             { return new Token(TokenType.RPAREN,   yytext(), yyline + 1); }

/* R9 — Espaço em branco — ignorar */
{ESPACO}        { /* ignorar */ }

/* R12 — Qualquer outro caractere — erro léxico */
.               { throw new RuntimeException(
                    "ERRO LEXICO: caractere invalido '" + yytext() +
                    "' na linha " + (yyline + 1)); }
