
import java.io.PrintStream;
import java.net.InetAddress;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiago
 */
public class Usuario {
    
    private String nome;
    private InetAddress ip;
    private int porta;
    private PrintStream streamSaida;

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
