package servidor;


import java.io.PrintStream;
import java.net.InetAddress;

/**
 * Classe Usuario utilizada para criar a estrutura de um usuário que sera o
 * cliente no chat de bate-papo
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public class Usuario {

    private String nome;
    private InetAddress ip;
    private int porta;
    private PrintStream streamSaida;

    /**
     * Metodo construtor que cria a estrutura de um usuario 
     * 
     * @param nome
     * @param ip
     * @param porta
     * @param streamSaida 
     */
    public Usuario(String nome, InetAddress ip, int porta, PrintStream streamSaida) {
        this.nome = nome;
        this.ip = ip;
        this.porta = porta;
        this.streamSaida = streamSaida;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the ip
     */
    public InetAddress getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    /**
     * @return the porta
     */
    public int getPorta() {
        return porta;
    }

    /**
     * @param porta the porta to set
     */
    public void setPorta(int porta) {
        this.porta = porta;
    }

    /**
     * @return the streamSaida
     */
    public PrintStream getStreamSaida() {
        return streamSaida;
    }

    /**
     * @param streamSaida the streamSaida to set
     */
    public void setStreamSaida(PrintStream streamSaida) {
        this.streamSaida = streamSaida;
    }
}
