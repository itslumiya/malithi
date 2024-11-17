package malithi;

import java.text.CharacterIterator;

public class Comment extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        if (code.current() == '=') {
            code.next();
            String comment = "=";
            if (code.current() == '>') {
                code.next();
                comment += ">";
                comment += readString(code);
                if (isEnd(code)) {
                    code.next();
                    return new Token("COMMENT", comment);
                }
            }
        }
        return null;
    }

    private String readString(CharacterIterator code) {
        String comment = "";
        while (!isEnd(code)) {
            comment += code.current();
            code.next();
        }
        return comment;
    }
    
    private boolean isEnd(CharacterIterator code) {
        return code.current() == '\n' ||
               code.current() == CharacterIterator.DONE;
    }
}
