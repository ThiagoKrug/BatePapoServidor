
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
    private Mensagem mensagem;

    public Usuario(String nome, InetAddress ip, int porta) {
        this.nome = nome;
        this.ip = ip;
        this.porta = porta;
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
     * @return the mensagem
     */
    public Mensagem getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }
    
}
