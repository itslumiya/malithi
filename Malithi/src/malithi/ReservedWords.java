package malithi;

import java.text.CharacterIterator;

public class ReservedWords extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        if (Character.isLetter(code.current())) {
            String reservedWord = readLetter(code);
            if (isEnd(code)) {
                switch (reservedWord) {
                    case "if":
                        return new Token("RESERVED_IF", reservedWord);
                    case "else":
                        return new Token("RESERVED_ELSE", reservedWord);
                    case "elsif":
                        return new Token("RESERVED_ELSIF", reservedWord);
                    case "while":
                        return new Token("RESERVED_WHILE", reservedWord);
                    case "for":
                        return new Token("RESERVED_FOR", reservedWord);
                    case "insert":
                        return new Token("RESERVED_INSERT", reservedWord);
                    case "show":
                        return new Token("RESERVED_SHOW", reservedWord);
                    case "int":
                        return new Token("RESERVED_INT", reservedWord);
                    case "str":
                        return new Token("RESERVED_STR", reservedWord);
                    case "bool":
                        return new Token("RESERVED_BOOL", reservedWord);
                    case "dec":
                        return new Token("RESERVED_DEC", reservedWord);
                    case "true":
                        return new Token("RESERVED_TRUE", reservedWord);
                    case "false":
                        return new Token("RESERVED_FALSE", reservedWord);
                    case "and":
                        return new Token("RESERVED_AND", reservedWord);
                    case "or":
                        return new Token("RESERVED_OR", reservedWord);
                    default:
                        return null;
                }
            }
        }
        return null;
    }

    private String readLetter(CharacterIterator code) {
        String word = "";
        while (Character.isLetter(code.current())) {
            word += code.current();
            code.next();
        }

        return word;
    }

    private boolean isEnd(CharacterIterator code) {
        return code.current() == ' ' ||
                code.current() == '+' ||
                code.current() == '-' ||
                code.current() == '*' ||
                code.current() == '/' ||
                code.current() == '>' ||
                code.current() == '<' ||
                code.current() == '=' ||
                code.current() == '(' ||
                code.current() == ')' ||
                code.current() == '{' ||
                code.current() == '}' ||
                code.current() == ':' ||
                code.current() == ';' ||
                code.current() == '.' ||
                code.current() == '"' ||
                code.current() == '%' ||
                code.current() == '!' ||
                code.current() == '$' ||
                code.current() == '\n' ||
                code.current() == '\t' ||
                code.current() == CharacterIterator.DONE;
    }
}
