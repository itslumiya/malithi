package malithi;

import java.text.CharacterIterator;

public class Strings extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        if (code.current() == '"') {
            code.next();
            String letter = "\"";
            letter += readString(code);
            if (isEnd(code)) {
                code.next();
                letter += "\"";
                return new Token("STRING", letter);
            }
        }
        return null;
    }

    private String readString(CharacterIterator code) {
        String letter = "";
        while (code.current() != '"') {
            letter += code.current();
            code.next();
        }
        return letter;
    }

    private boolean isEnd(CharacterIterator code) {
        return code.current() == '"' ||
                code.current() == '\n' ||
                code.current() == '\t' ||
                code.current() == CharacterIterator.DONE;
    }
}