package malithi;

import java.text.CharacterIterator;

public class OtherTokens extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        String token = readCharacters(code);
        if (isEnd(code)) {
            switch (token) {
                case "+":
                    return new Token("OPERATOR_PLUS", "+");
                case "-":
                    return new Token("OPERATOR_MINUS", "-");
                case "*":
                    return new Token("OPERATOR_TIMES", "*");
                case "/":
                    return new Token("OPERATOR_SLASH", "/");
                case "=":
                    return new Token("OPERATOR_EQUAL", "=");
                case ">":
                    return new Token("OPERATOR_GREATER", ">");
                case "<":
                    return new Token("OPERATOR_LESSER", "<");
                case "(":
                    return new Token("CHARACTER_OPARENTHESES", "(");
                case ")":
                    return new Token("CHARACTER_CPARENTHESES", ")");
                case "{":
                    return new Token("CHARACTER_OKEYS", "{");
                case "}":
                    return new Token("CHARACTER_CKEYS", "}");
                case ";":
                    return new Token("CHARACTER_SEMICOLON", ";");
                case ":":
                    return new Token("CHARACTER_COLON", ":");
                case "%":
                    return new Token("CHARACTER_MODULE", "%");
                case "\"":
                    return new Token("CHARACTER_QUOTATIONMARK", " \"");
                case "\n":
                    return new Token("PARAGRAPH", "\n");
                case "\t":
                    return new Token("TAB", "\t");
                case ">=":
                    return new Token("COMPARATOR_GREATEREQUAL", ">=");
                case "<=":
                    return new Token("COMPARATOR_LESSEREQUAL", "<=");
                case "==":
                    return new Token("COMPARATOR_EQUAL", "==");
                case "!=":
                    return new Token("COMPARATOR_DIFFERENT", "!=");
                case "++":
                    return new Token("INCDEC_DOUBLEPLUS", "++");
                case "--":
                    return new Token("INCDEC_DOUBLEMINUS", "--");
                case "+=":
                    return new Token("INCREMENT", "+=");
                case "-=":
                    return new Token("DECREMENT", "-=");
                default:
                    return null;
            }
        }

        return null;
    }

    private String readCharacters(CharacterIterator code) {
        String token = "";
        char[] listaChars = {'+', '-', '*', '/', '=', '>', '<', '(', ')', '{', '}', ';', ':', '%', '!', '"', '\n'};

        for (int i = 0; i < listaChars.length; i++) {
            if (code.current() == listaChars[i]) {
                String tokenAnterior = "";
                tokenAnterior += code.current();
                token += code.current();
                code.next();
                if (tokenAnterior.equals(">") || tokenAnterior.equals("<") || tokenAnterior.equals("=")) {
                    if (code.current() == '=') {
                        token += code.current();
                        code.next();
                    }
                } else if (tokenAnterior.equals("+")) {
                    if (code.current() == '+' || code.current() == '=') {
                        token += code.current();
                        code.next();
                    }
                } else if (tokenAnterior.equals("-")) {
                    if (code.current() == '-' || code.current() == '=') {
                        token += code.current();
                        code.next();
                    }
                } else if (tokenAnterior.equals("!")) {
                    if (code.current() == '=') {
                        token += code.current();
                        code.next();
                    }
                }
                return token;
            }
        }
        return token;
    }

    private boolean isEnd(CharacterIterator code) {
        return code.current() == ' '
                || code.current() == '+'
                || code.current() == '-'
                || code.current() == '*'
                || code.current() == '/'
                || code.current() == '>'
                || code.current() == '<'
                || code.current() == '='
                || code.current() == '('
                || code.current() == ')'
                || code.current() == '{'
                || code.current() == '}'
                || code.current() == ':'
                || code.current() == ';'
                || code.current() == '.'
                || code.current() == '"'
                || code.current() == '%'
                || code.current() == '$'
                || code.current() == '\n'
                || code.current() == '\t'
                || Character.isLetterOrDigit(code.current())
                || code.current() == CharacterIterator.DONE;
    }
}
