
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author thiago
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

    public static Principal getInstance() {
        if (principal == null) {
            principal = new Principal();
        }
        return principal;
    }

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
    
    public static Usuarios getUsuarios() {
        return usuarios;
    }
}