package persistencia;
import aplicacao.Contato;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContatoDAO {

    private static Conexao con;

    public static void setConexao(Conexao conexao) {
        con = conexao;
    }

    public static void inserirContato(Contato c1) {
        try {
            con.conectar();

            PreparedStatement instrucao = con.getCon().prepareStatement("INSERT INTO contato (nome, telefone, email) VALUES (?, ?, ?)");
            instrucao.setString(1, c1.getNome());
            instrucao.setString(2, c1.getTelefone());
            instrucao.setString(3, c1.getEmail());

            int linhasAfetadas = instrucao.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Inserção realizada com sucesso.");
            } else {
                System.out.println("Nenhuma linha afetada durante a inserção.");
            }

        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally {
            con.desconectar();
        }
    }

    public static void alterarContato(int id, Contato c1) {
        try {
            con.conectar();
    
            String sql = "UPDATE contato SET nome = ?, telefone = ?, email = ? WHERE id = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            
            instrucao.setString(1, c1.getNome());
            instrucao.setString(2, c1.getTelefone());
            instrucao.setString(3, c1.getEmail());
            instrucao.setInt(4, id);
    
            int linhasAfetadas = instrucao.executeUpdate();
    
            if (linhasAfetadas > 0) {
                System.out.println("Alteração realizada com sucesso.");
            } else {
                System.out.println("Nenhuma linha afetada durante a alteração.");
            }
    
        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally {
            con.desconectar();
        }
    }
    
    public static void deletarContato(int id) {
        try {
            con.conectar();
    
            String sql = "DELETE FROM contato WHERE id = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            
            instrucao.setInt(1, id);
    
            int linhasAfetadas = instrucao.executeUpdate();
    
            if (linhasAfetadas > 0) {
                System.out.println("Alteração realizada com sucesso.");
            } else {
                System.out.println("Nenhuma linha afetada durante a alteração.");
            }
    
        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally {
            con.desconectar();
        }
    }
    
    public static Contato buscarContato(int id) {
        Contato contato = null;
        try {
            con.conectar();

            String sql = "SELECT * FROM contato WHERE id = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, id);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int contatoId = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");

                contato = new Contato(contatoId, nome, telefone, email);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o contato: " + e.getMessage());
        } finally {
            con.desconectar();
        }
        return contato;
    }

    public static ArrayList<Contato> retornarLista(String parteNome) {
        ArrayList<Contato> listaContatos = new ArrayList<>();
    
        try {
            con.conectar();
            String sql = "SELECT * FROM contato WHERE nome ILIKE ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setString(1, "%" + parteNome + "%");
            ResultSet rs = instrucao.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
    
                Contato c1 = new Contato(id, nome, telefone, email);
    
                listaContatos.add(c1);
            }
            con.desconectar();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o contato: " + e.getMessage());
        }
    
        return listaContatos;
    }   
    
    public static ArrayList<Contato> retornarListaCompleta() {
        ArrayList<Contato> listaContatos = new ArrayList<>();
    
        try {
            con.conectar();
            String sql = "SELECT * FROM contato";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            ResultSet rs = instrucao.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
    
                Contato c1 = new Contato(id, nome, telefone, email);
    
                listaContatos.add(c1);
            }
            con.desconectar();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o contato: " + e.getMessage());
        }
    
        return listaContatos;
    }   
}