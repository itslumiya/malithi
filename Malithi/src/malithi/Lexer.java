package malithi;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private List<Token> tokens;
    private List<AFD> afds;
    private CharacterIterator code;

    public Lexer(String code) {
        tokens = new ArrayList<>();
        afds = new ArrayList<>();
        this.code = new StringCharacterIterator(code);
        afds.add(new Strings());
        afds.add(new Comment());
        afds.add(new OtherTokens());
        afds.add(new ReservedWords());
        afds.add(new Number());
        afds.add(new Identifier());
    }

    public void skipWhiteSpace() {
        while (code.current() == ' ' || code.current() == '\n' || code.current() == '\t')
            code.next();
    }

    public List<Token> getTokens() {
        boolean accepted;
        while (code.current() != CharacterIterator.DONE) {
            accepted = false;
            skipWhiteSpace();
            if (code.current() == CharacterIterator.DONE)
                break;

            for (AFD afd : afds) {
                int pos = code.getIndex();
                Token t = afd.evaluate(code);
                if (t != null) {
                    accepted = true;
                    tokens.add(t);
                    break;
                } else {
                    code.setIndex(pos);
                }
            }
            if (accepted)
                continue;
            throw new RuntimeException("Error: Token not recognized: " + code.current());
        }
        tokens.add(new Token("EOF", "$"));
        return tokens;
    }
}
