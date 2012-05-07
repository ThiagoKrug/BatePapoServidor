package servidor;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe principal que cria a estrutura do servidor e inicia-o
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public class Principal {

    private static Principal principal;
    private static Usuarios usuarios;
    private ServerSocket serverSocket;
    private int porta;

    private Principal() {
        usuarios = new Usuarios();
        porta = 5588;
    }

    /**
     * Cria um objto da classe Principal utilizando o padrão Singleton
     *
     * @return principal
     */
    public static Principal getInstance() {
        if (principal == null) {
            principal = new Principal();
        }
        return principal;
    }

    /**
     * Metodo que inicia o servidor e cria um socket que fica aguardando
     * conexões
     *
     */
    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("Servidor iniciado!");
            while (true) {
                System.out.println("Escutando conexões na porta " + porta + "...");
                Socket conexao = serverSocket.accept();
                Thread t = new Conexao(conexao);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
    
    /**
     * Metodo que retorna uma lista de usuarios conectados
     * 
     * @return usuarios
     */
    public static Usuarios getUsuarios() {
        return usuarios;
    }
}