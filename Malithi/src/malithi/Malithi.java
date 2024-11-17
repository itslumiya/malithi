package malithi;

import javax.swing.JOptionPane;

public class Malithi {

    public static void main(String[] args) {
       IDE ide = new IDE();
       ide.setVisible(true);
       JOptionPane.showMessageDialog(ide, "Bem vindo a nossa IDE! \n"
                + "MA  - Marcio Forner N. Almeida - 22.122.040-3\n"
                + "LI  - Livia Lumi Miyabara - 22.122.045-2 \n"
                + "THI - Thiago Garcia Santana - 22.122.003-1");
    }
    
}
