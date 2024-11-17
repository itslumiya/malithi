package malithi;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {

    List<Token> tokens;
    IDE ide;
    Token token;
    Token tokenAnterior;
    List<Variavel> variaveis = new ArrayList<>();
    Stack<List<Variavel>> pilhaEscopo = new Stack<>();
    boolean escopoAberto = false;
    String javaCode = "";
    String tipoInsert;

    public Parser(List<Token> tokens, IDE ide) {
        this.tokens = tokens;
        this.ide = ide;
    }

    public Token getNextToken() {
        if (tokens.size() > 0) {
            tokenAnterior = token;
            return tokens.remove(0);
        }
        return null;
    }

    public Tree main() {
        token = getNextToken();
        javaCode += "import java.util.Scanner; public class ResultadoCompilador { public static void main(String[] args) { Scanner scanner = new Scanner(System.in);";
        Node main = new Node("main");
        Tree tree = new Tree(main);
        if (Block(main) && token.tipo.equals("EOF")) {
            ide.codigoCorreto = true;
        }
        javaCode += "} }";
        ide.javaCode = javaCode;
        return tree;
    }

    public boolean Block(Node node) {
        Node block = node.addNode("Block");
        if (first("show")) {
            return (Show(block) && Block(block));
        } else if (firstTipo("ID")) {
            return (Attribuition(block) && Block(block));
        } else if (first("int") || first("str") || first("bool") || first("dec")) {
            return (Declaration(block) && Block(block));
        } else if (first("if")) {
            return (If(block) && Block(block));
        } else if (first("while")) {
            return (While(block) && Block(block));
        } else if (first("for")) {
            return (For(block) && Block(block));
        } else if (firstTipo("COMMENT")) {
            return (Comment(block) && Block(block));
        }
        return true;
    }

    /*
    IF → if '(' CONDITION ')' ':' '{' BLOCK '}' EI
    EI → elsif '(' CONDITION ')' ':' '{' BLOCK '}' EI || EL 
    EL → else ':' '{' BLOCK '}' ';'
     */
    public boolean If(Node node) {
        Node If = node.addNode("If");
        return (matchLexema("if", If, "if") && matchLexema("(", If, "(") && Condition(If) && matchLexema(")", If, ")")
                && matchLexema(":", If, "") && matchLexema("{", If, "{") && Block(If) && matchLexema("}", If, "}")
                && (EI(If) || EL(If)));
    }

    // EI → elsif '(' CONDITION ')' ':' '{' BLOCK '}' EI || EL 
    public boolean EI(Node node) {
        Node Elsif = node.addNode("Elsif");
        return (matchLexemaNoError("elsif", Elsif, "else if") && matchLexema("(", Elsif, "(") && Condition(Elsif) && matchLexema(")", Elsif, ")")
                && matchLexema(":", Elsif, "") && matchLexema("{", Elsif, "{") && Block(Elsif) && matchLexema("}", Elsif, "}")
                && (EI(Elsif) || EL(Elsif)));
    }

    // EL → else ':' '{' BLOCK '}' ';'
    public boolean EL(Node node) {
        Node Else = node.addNode("Else");
        return (matchLexema("else", Else, "else") && matchLexema(":", Else, "") && matchLexema("{", Else, "{") && Block(Else)
                && matchLexema("}", Else, "}") && matchLexema(";", Else, ";"));
    }

    // WHILE → while '(' CONDITION ')' ':' '{' BLOCK '}' ';'
    public boolean While(Node node) {
        Node While = node.addNode("While");
        return (matchLexema("while", While, "while") && matchLexema("(", While, "(") && Condition(While) && matchLexema(")", While, ")")
                && matchLexema(":", While, "") && matchLexema("{", While, "{") && Block(While) && matchLexema("}", While, "}")
                && matchLexema(";", While, ";"));
    }

    // FOR → for '(' DECLARATION ';' CONDITION ';' var ('++' || '--') ')' ':' '{' BLOCK '}' ';'
    public boolean For(Node node) {
        Node For = node.addNode("For");
        return (matchLexema("for", For, "for") && matchLexema("(", For, "(") && Declaration(For) && Condition(For)
                && matchLexema(";", For, ";") && matchTipo("ID", For, "ID") && (matchLexemaNoError("++", For, "++") || matchLexema("--", For, "--"))
                && matchLexema(")", For, ")") && matchLexema(":", For, "") && matchLexema("{", For, "{") && Block(For)
                && matchLexema("}", For, "}") && matchLexema(";", For, ";"));
    }

    // INS → insert '(' STRING ')' ';'
    public boolean Insert(Node node, String tipo) {
        Node Insert = node.addNode("Insert");
        switch (tipo) {
            case "str":
                return (matchLexemaNoError("insert", Insert, "scanner.nextLine") && matchLexema("(", Insert, "(") && matchLexema(")", Insert, ")"));
            case "int":
                return (matchLexemaNoError("insert", Insert, "scanner.nextInt") && matchLexema("(", Insert, "(") && matchLexema(")", Insert, ")"));
            case "dec":
                return (matchLexemaNoError("insert", Insert, "scanner.nextDouble") && matchLexema("(", Insert, "(") && matchLexema(")", Insert, ")"));
            case "bool":
                return (matchLexema("insert", Insert, "scanner.nextBoolean") && matchLexema("(", Insert, "(") && matchLexema(")", Insert, ")"));
            default:
                return false;
        }
    }

    // DECINC → ("+=" || "-=") (NUM || DEC_NUM) ';'
    public boolean DecInc(Node node) {
        Node DecInc = node.addNode("DecInc");
        return (matchLexemaNoError("+=", DecInc, "+=") || matchLexema("-=", DecInc, "-="))
                && (matchTipoNoError("NUM", DecInc, "NUM") || matchTipo("DEC_NUM", DecInc, "DEC_NUM")) && matchLexema(";", DecInc, ";");
    }

    // SHOW → show '(' (STRING || ID) CONCATENATE ')' ';'
    public boolean Show(Node node) {
        Node Show = node.addNode("Show");
        return matchLexema("show", Show, "System.out.print") && matchLexema("(", Show, "(")
                && (matchTipoNoError("STRING", Show, "STRING") || matchTipo("ID", Show, "ID")) && Concatenate(Show)
                && matchLexema(")", Show, ")") && matchLexema(";", Show, ";");
    }

    public boolean Concatenate(Node node) {
        Node Concatenate = node.addNode("Concatenate");
        return (matchLexemaNoError("+", Concatenate, "+")
                && (matchTipoNoError("STRING", Concatenate, "STRING") || matchTipoNoError("ID", Concatenate, "ID")) && Concatenate(Concatenate)) || true;
    }

    // ATTRIBUITION → ID (DECINC || ('=' VAR ';') || EXPRESSION || INSERT ';')
    public boolean Attribuition(Node node) {
        Node Attribuition = node.addNode("Attribuition");
        return matchTipo("ID", Attribuition, "ID") && (DecInc(Attribuition) || ((matchLexema("=", Attribuition, "=") && Var(Attribuition)
                && matchLexema(";", Attribuition, ";")) || (Expression(Attribuition) && matchLexema(";", Attribuition, ";"))
                || (Insert(Attribuition, tipoInsert) && matchLexema(";", Attribuition, ";"))));
    }

    /*
    EXPRESSION → TE'
    E´ → ('+' || '-') TE´ || ε
    T → FT´
    T´ → ('*' || '/' || '%') FT´ || ε
    F → VAR || '(' EXPRESSION ')'
     */
    public boolean Expression(Node node) {
        Node Expression = node.addNode("Expression");
        return (T(Expression) && ELine(Expression));
    }

    // E´ → '+'TE´ || '-'TE´ || ε
    public boolean ELine(Node node) {
        Node ELine = node.addNode("Expression -> ELine");
        return ((matchLexemaNoError("+", ELine, "+") || matchLexemaNoError("-", ELine, "-")) && T(ELine) && ELine(ELine)) || true;
    }

    // T → FT´
    public boolean T(Node node) {
        Node T = node.addNode("Expression -> T");
        return (F(T) && TLine(T));
    }

    //T´ → ('*' || '/' || '%') FT´ || ε
    public boolean TLine(Node node) {
        Node TLine = node.addNode("Expression -> TLine");
        return ((matchLexemaNoError("*", TLine, "*") || matchLexemaNoError("/", TLine, "/") || matchLexemaNoError("%", TLine, "%"))
                && F(TLine) && TLine(TLine)) || true;
    }

    // F → VAR || '(' EXPRESSION ')'
    public boolean F(Node node) {
        Node F = node.addNode("Expression -> F");
        return Var(F) || (matchLexema("(", F, "(") && Expression(F) && matchLexema(")", F, ")"));
    }

    /*
    CONDITION → '(' CONDITION ')' ANDOR || S ANDOR
    S → (EXPRESSION || VAR) COMPARATOR EXPRESSION || VAR)
    ANDOR → ('and' || 'or') CONDITION ANDOR || ε
     */
    public boolean Condition(Node node) {
        Node Condition = node.addNode("Condition");
        return (matchLexemaNoError("(", Condition, "(") && Condition(Condition) && matchLexema(")", Condition, ")") && AndOr(Condition)
                || (S(Condition) && AndOr(Condition)));
    }

    // S → (EXPRESSION || VAR) COMPARATOR EXPRESSION || VAR)
    public boolean S(Node node) {
        Node S = node.addNode("Condition -> S");
        return (Expression(S) || Var(S)) && Comparator(S) && (Expression(S) || Var(S));
    }

    // ANDOR → ('and' || 'or') CONDITION ANDOR || ε
    public boolean AndOr(Node node) {
        Node AndOr = node.addNode("AndOr");
        return ((matchLexemaNoError("and", AndOr, "&&") || matchLexemaNoError("or", AndOr, "||")) && Condition(AndOr)) || true;
    }

    // VAR → ID || STRING || NUM || DEC_NUM || 'true' || 'false'
    public boolean Var(Node node) {
        Node Var = node.addNode("Var");
        return matchTipoNoError("ID", Var, "ID") || matchTipoNoError("STRING", Var, "STRING") || matchTipoNoError("NUM", Var, "NUM")
                || matchTipoNoError("DEC_NUM", Var, "DEC_NUM") || matchTipoNoError("RESERVED_TRUE", Var, "RESERVED_TRUE")
                || matchTipo("RESERVED_FALSE", Var, "RESERVED_FALSE");
    }

    // DECLARATION → TYPE ID (';' || '=' (INSERT || EXPRESSION || VAR) ';')
    public boolean Declaration(Node node) {
        Node Declaration = node.addNode("Declaration");
        tipoInsert = "";
        return Type(Declaration) && matchTipo("ID", Declaration, "ID") && (matchLexemaNoError(";", Declaration, ";")
                || (matchLexema("=", Declaration, "=")
                && (Insert(Declaration, tipoInsert) || Expression(Declaration) || (Var(Declaration))) && matchLexema(";", Declaration, ";")));
    }

    // TYPE → "int" || "str" || "bool" || "dec"
    public boolean Type(Node node) {
        Node Type = node.addNode("Type");
        if (matchLexemaNoError("int", Type, "int")) {
            tipoInsert = "int";
            return true;
        } else if (matchLexemaNoError("str", Type, "String")) {
            tipoInsert = "str";
            return true;
        } else if (matchLexemaNoError("bool", Type, "boolean")) {
            tipoInsert = "bool";
            return true;
        } else if (matchLexema("dec", Type, "double")) {
            tipoInsert = "dec";
            return true;
        } else {
            return false;
        }
    }

    // COMPARATOR → "==" || "!=" || ">=" || "<=" || ">" || "<"
    public boolean Comparator(Node node) {
        Node Comparator = node.addNode("Comparator");
        return (matchLexemaNoError("==", Comparator, "==") || matchLexemaNoError("!=", Comparator, "!=")
                || matchLexemaNoError(">=", Comparator, ">=") || matchLexemaNoError("<=", Comparator, "<=")
                || matchLexemaNoError(">", Comparator, ">") || matchLexema("<", Comparator, "<"));
    }

    public boolean Comment(Node node) {
        Node Comment = node.addNode("Comment");
        return matchTipo("COMMENT", Comment, "COMMENT");
    }

    public boolean first(String lexema) {
        if (token.lexema.equals(lexema)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean firstTipo(String tipo) {
        if (token.tipo.equals(tipo)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean matchLexema(String palavra, Node node, String traduzir) {
        if (token.lexema.equals(palavra)) {
            if (token.lexema.equals("{")) {
                List<Variavel> novoEscopo = new ArrayList<>();
                pilhaEscopo.push(novoEscopo);
            } else if (token.lexema.equals("}")) {
                pilhaEscopo.pop();
            }

            javaCode += traduzir + " ";
            node.addNode(token.lexema);
            token = getNextToken();
            return true;
        }
        node.addNode("ERROR: Esperado " + palavra + " / Recebido " + token.lexema);
        return false;
    }

    public boolean matchLexemaNoError(String palavra, Node node, String traduzir) {
        if (token.lexema.equals(palavra)) {
            if (token.lexema.equals("{")) {
                List<Variavel> novoEscopo = new ArrayList<>();
                pilhaEscopo.push(novoEscopo);
            } else if (token.lexema.equals("}")) {
                pilhaEscopo.pop();
            }

            javaCode += traduzir + " ";
            node.addNode(token.lexema);
            token = getNextToken();
            return true;
        }
        return false;
    }

    public boolean matchTipo(String palavra, Node node, String traduzir) {
        if (token.tipo.equals(palavra)) {
            if (CheckDeclaredVariables(node)) {
                if (traduzir.equals("COMMENT")) {
                    String comment = "";
                    comment += "//";
                    comment += token.lexema.substring(2) + "\n";
                    javaCode += comment;
                } else {
                    javaCode += token.lexema;
                }
                node.addNode(token.lexema);
                token = getNextToken();
                return true;
            } else {
                return false;
            }
        }
        node.addNode("ERROR: Esperado " + palavra + " / Recebido " + token.tipo);
        return false;
    }

    public boolean matchTipoNoError(String palavra, Node node, String traduzir) {
        if (token.tipo.equals(palavra)) {
            if (CheckDeclaredVariables(node)) {
                if (traduzir.equals("COMMENT")) {
                    String comment = "";
                    comment += "//";
                    comment += token.lexema.substring(2) + "\n";
                    javaCode += comment;
                } else {
                    javaCode += token.lexema;
                }
                node.addNode(token.lexema);
                token = getNextToken();
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean CheckDeclaredVariables(Node node) {
        if (token.tipo.equals("ID")) {
            if (tokenAnterior.tipo.equals("RESERVED_INT") || tokenAnterior.tipo.equals("RESERVED_STR")
                    || tokenAnterior.tipo.equals("RESERVED_BOOL") || tokenAnterior.tipo.equals("RESERVED_DEC")) {
                if (!pilhaEscopo.isEmpty()) {
                    for (List<Variavel> lista : pilhaEscopo) {
                        for (Variavel var : lista) {
                            if (var.nomeVariavel.equals(token.lexema)) {
                                node.addNode("ERROR: Variável " + token.lexema + " já declarada");
                                return false;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < variaveis.size(); i++) {
                        if (variaveis.get(i).nomeVariavel.equals(token.lexema)) {
                            node.addNode("ERROR: Variável " + token.lexema + " já declarada");
                            return false;
                        }
                    }
                }

                if (!pilhaEscopo.isEmpty()) {
                    Variavel var = new Variavel(token.tipo, token.lexema);
                    pilhaEscopo.peek().add(var);
                } else {
                    Variavel var = new Variavel(token.tipo, token.lexema);
                    variaveis.add(var);
                }
                return true;
            } else {
                boolean variavelDeclarada = false;
                if (!pilhaEscopo.isEmpty()) {
                    for (List<Variavel> lista : pilhaEscopo) {
                        for (Variavel var : lista) {
                            if (var.nomeVariavel.equals(token.lexema)) {
                                return true;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < variaveis.size(); i++) {
                        if (variaveis.get(i).nomeVariavel.equals(token.lexema)) {
                            return true;
                        }
                    }
                }

                if (variavelDeclarada == false) {
                    node.addNode("ERROR: Variável " + token.lexema + " não declarada");
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return true;
        }
    }
}
