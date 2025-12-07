// PatientForm.java
// Java 8 + Swing + SQLServer
// Compile: javac -cp .;lib/mssql-jdbc-8.4.1.jre8.jar PatientForm.java
// Run: java -cp .;lib/mssql-jdbc-8.4.1.jre8.jar PatientForm

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PatientForm extends JFrame {
    private JTextField txtNome, txtIdade, txtPeso, txtAltura, txtPesquisa;
    private JButton btnIncluir, btnLimpar, btnApresenta, btnPesquisar, btnCreditos, btnSair;

    private Connection conn;

    public PatientForm() {
        setTitle("Cadastro de Pacientes");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0,1));

        conectarBD();

        txtNome = new JTextField();
        txtIdade = new JTextField();
        txtPeso = new JTextField();
        txtAltura = new JTextField();
        txtPesquisa = new JTextField();

        btnIncluir = new JButton("Incluir");
        btnLimpar = new JButton("Limpar");
        btnApresenta = new JButton("Apresenta Dados");
        btnPesquisar = new JButton("Pesquisar");
        btnCreditos = new JButton("Créditos");
        btnSair = new JButton("Sair");

        add(new JLabel("Nome:")); add(txtNome);
        add(new JLabel("Idade:")); add(txtIdade);
        add(new JLabel("Peso:")); add(txtPeso);
        add(new JLabel("Altura:")); add(txtAltura);
        add(btnIncluir);
        add(btnLimpar);
        add(btnApresenta);
        add(new JLabel("Pesquisar por nome:")); add(txtPesquisa);
        add(btnPesquisar);
        add(btnCreditos);
        add(btnSair);

        btnIncluir.addActionListener(e -> incluir());
        btnLimpar.addActionListener(e -> limpar());
        btnApresenta.addActionListener(e -> apresentaDados());
        btnPesquisar.addActionListener(e -> pesquisar());
        btnCreditos.addActionListener(e -> JOptionPane.showMessageDialog(this, "Dupla: Matheus Correia de França, Davi Leite Coelho"));
        btnSair.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void conectarBD() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=hospital;trustServerCertificate=true;";
            String user = "sa";
            String pass = "root";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar BD: " + e.getMessage());
        }
    }

    private void incluir() {
        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            float peso = Float.parseFloat(txtPeso.getText());
            float altura = Float.parseFloat(txtAltura.getText());

            String sql = "INSERT INTO Paciente(nome, idade, peso, altura) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, idade);
            ps.setFloat(3, peso);
            ps.setFloat(4, altura);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Paciente incluído com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao incluir: " + e.getMessage());
        }
    }

    private void apresentaDados() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Paciente");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                  .append(" | Nome: ").append(rs.getString("nome"))
                  .append(" | Idade: ").append(rs.getInt("idade"))
                  .append(" | Peso: ").append(rs.getFloat("peso"))
                  .append(" | Altura: ").append(rs.getFloat("altura"))
                  .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao apresentar dados: " + e.getMessage());
        }
    }

    private void pesquisar() {
        try {
            String nome = txtPesquisa.getText();
            String sql = "SELECT * FROM Paciente WHERE nome LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                  .append(" | Nome: ").append(rs.getString("nome"))
                  .append(" | Idade: ").append(rs.getInt("idade"))
                  .append(" | Peso: ").append(rs.getFloat("peso"))
                  .append(" | Altura: ").append(rs.getFloat("altura"))
                  .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.length()>0?sb.toString():"Nenhum registro encontrado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro na pesquisa: " + e.getMessage());
        }
    }

    private void limpar() {
        txtNome.setText("");
        txtIdade.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
        txtPesquisa.setText("");
    }

    public static void main(String[] args) {
        new PatientForm();
    }
}
