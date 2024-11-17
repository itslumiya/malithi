package malithi;

import java.text.CharacterIterator;

public class Identifier extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        if (Character.isLetter(code.current())) {
            String letter = readLetter(code);
            if (isEnd(code)) {
                return new Token("ID", letter);
            }
        }
        return null;
    }

    private String readLetter(CharacterIterator code) {
        String letter = "";
        if (Character.isLetter((code.current()))) {
            letter += code.current();
            code.next();

            while (Character.isLetterOrDigit((code.current()))) {
                letter += code.current();
                code.next();
            }
            return letter;
        }
        return letter;
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
