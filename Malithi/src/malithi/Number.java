package malithi;

import java.text.CharacterIterator;

public class Number extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        if (Character.isDigit(code.current())) {
            String number = readNumber(code);
            if (isEnd(code)) {
                if (number.contains(".")) {
                    return new Token("DEC_NUM", number);
                }
                return new Token("NUM", number);
            }
        }
        return null;
    }

    private String readNumber(CharacterIterator code) {
        String number = "";
        while (Character.isDigit((code.current()))) {
            number += code.current();
            code.next();
        }
        if (code.current() == '.') {
            number += code.current();
            code.next();
            while (Character.isDigit((code.current()))) {
                number += code.current();
                code.next();
            }
            return number;
        }
        return number;
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
                Character.isLetter(code.current()) ||
                code.current() == CharacterIterator.DONE;
    }
}