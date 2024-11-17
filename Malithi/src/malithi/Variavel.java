package malithi;

public class Variavel {
    String tipo;
    String nomeVariavel;
    
    public Variavel(String tipo, String nomeVariavel)
    {
        this.tipo = tipo;
        this.nomeVariavel = nomeVariavel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }

    public void setNomeVariavel(String nomeVariavel) {
        this.nomeVariavel = nomeVariavel;
    }
}