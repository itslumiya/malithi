package malithi;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class IDE extends javax.swing.JFrame {

    List<Token> listaDeTokens = new ArrayList<>();
    String javaCode;
    boolean codigoCorreto = false;

    public IDE() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(255, 255, 255));
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_inputCode = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_saida = new javax.swing.JTextArea();
        btn_compilar = new javax.swing.JButton();
        btn_gerarCodigo = new javax.swing.JButton();
        btn_visualizarTokens = new javax.swing.JButton();
        btn_baixarArquivo = new javax.swing.JButton();
        btn_executarCodigo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 36)); // NOI18N
        jLabel1.setText("MaLiThi");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txt_inputCode.setColumns(20);
        txt_inputCode.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        txt_inputCode.setLineWrap(true);
        txt_inputCode.setRows(5);
        txt_inputCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_inputCodeKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txt_inputCode);

        txt_saida.setColumns(20);
        txt_saida.setLineWrap(true);
        txt_saida.setRows(5);
        jScrollPane2.setViewportView(txt_saida);

        btn_compilar.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btn_compilar.setText("Compilar");
        btn_compilar.setEnabled(false);
        btn_compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_compilarActionPerformed(evt);
            }
        });

        btn_gerarCodigo.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btn_gerarCodigo.setText("Gerar código em java");
        btn_gerarCodigo.setEnabled(false);
        btn_gerarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gerarCodigoActionPerformed(evt);
            }
        });

        btn_visualizarTokens.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btn_visualizarTokens.setText("Ver tokens");
        btn_visualizarTokens.setEnabled(false);
        btn_visualizarTokens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_visualizarTokensActionPerformed(evt);
            }
        });

        btn_baixarArquivo.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btn_baixarArquivo.setText("Baixar código (Java)");
        btn_baixarArquivo.setEnabled(false);
        btn_baixarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_baixarArquivoActionPerformed(evt);
            }
        });

        btn_executarCodigo.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btn_executarCodigo.setText("Executar Código (Java)");
        btn_executarCodigo.setEnabled(false);
        btn_executarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_executarCodigoActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/malithi/Bow.jpg"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/malithi/Bow.jpg"))); // NOI18N

        jMenuBar1.setBackground(new java.awt.Color(253, 205, 221));

        jMenu1.setText("Menu");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Visualizar Tokens");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_compilar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_visualizarTokens)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_gerarCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_baixarArquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_executarCodigo)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(407, 407, 407))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_compilar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gerarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_visualizarTokens, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_baixarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_executarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ViewTokens vt = new ViewTokens();
        vt.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn_compilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_compilarActionPerformed
        txt_saida.setText("");
        listaDeTokens = new ArrayList<>();
        javaCode = "";
        codigoCorreto = false;

        List<Token> tokens = null;
        String code = txt_inputCode.getText();
        Lexer lexer = new Lexer(code);
        tokens = lexer.getTokens();

        for (Token t : tokens) {
            listaDeTokens.add(t);
        }

        Parser parser = new Parser(tokens, this);
        Tree tree = parser.main();
        txt_saida.setText(tree.printTree());
        btn_visualizarTokens.setEnabled(true);

        if (codigoCorreto) {
            btn_gerarCodigo.setEnabled(true);
            btn_baixarArquivo.setEnabled(true);
            btn_executarCodigo.setEnabled(true);
        } else {
            btn_gerarCodigo.setEnabled(false);
            btn_baixarArquivo.setEnabled(false);
            btn_executarCodigo.setEnabled(false);
        }
    }//GEN-LAST:event_btn_compilarActionPerformed

    private void btn_gerarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gerarCodigoActionPerformed
        txt_saida.setText("");
        txt_saida.setText(javaCode);
    }//GEN-LAST:event_btn_gerarCodigoActionPerformed

    private void txt_inputCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_inputCodeKeyPressed
        btn_compilar.setEnabled(true);
        btn_visualizarTokens.setEnabled(false);
        btn_gerarCodigo.setEnabled(false);
        btn_baixarArquivo.setEnabled(false);
        btn_executarCodigo.setEnabled(false);
    }//GEN-LAST:event_txt_inputCodeKeyPressed

    private void btn_visualizarTokensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_visualizarTokensActionPerformed
        txt_saida.setText("");
        String tokens = "";
        for (int i = 0; i < listaDeTokens.size(); i++) {
            tokens += listaDeTokens.get(i) + "\n";
        }
        txt_saida.setText(tokens);
    }//GEN-LAST:event_btn_visualizarTokensActionPerformed

    private void btn_baixarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_baixarArquivoActionPerformed
        try {
            String fileName = "ResultadoCompilador.java";

            String userHome = System.getProperty("user.home");
            Path downloadsDir = Paths.get(userHome, "Downloads", fileName);

            FileWriter fileWriter = new FileWriter(downloadsDir.toFile());
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(javaCode);
            writer.close();

            JOptionPane.showMessageDialog(this, "Arquivo gerado com sucesso em 'Downloads'!");

            Desktop desktop = Desktop.getDesktop();
            desktop.open(downloadsDir.getParent().toFile());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao criar o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_baixarArquivoActionPerformed

    private void btn_executarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_executarCodigoActionPerformed
        try {
            File arquivoJava = new File("ResultadoCompilador.java");
            try (FileWriter writer = new FileWriter(arquivoJava)) {
                writer.write(javaCode);
            }
            
            executarComando("cmd", "/c", "cls");
            executarComando("cmd", "/c", "javac -cp %cd% ResultadoCompilador.java");
            executarComando("cmd", "/c", "java -cp %cd% ResultadoCompilador");
            JOptionPane.showMessageDialog(this, "Execução finalizada!");
            
        } catch (IOException | InterruptedException e) {
            txt_saida.setText("Erro ao compilar arquivo: " + e);
        }
    }//GEN-LAST:event_btn_executarCodigoActionPerformed
    private static void executarComando(String... comando) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(comando);
        processBuilder.inheritIO();  // Redireciona entradas e saídas para o console atual
        Process processo = processBuilder.start();
        processo.waitFor();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_baixarArquivo;
    private javax.swing.JButton btn_compilar;
    private javax.swing.JButton btn_executarCodigo;
    private javax.swing.JButton btn_gerarCodigo;
    private javax.swing.JButton btn_visualizarTokens;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txt_inputCode;
    private javax.swing.JTextArea txt_saida;
    // End of variables declaration//GEN-END:variables
}
