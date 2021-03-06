package servidor;


import java.net.DatagramPacket;

/**
 * Classe Interpretador utilizada para fazer a quebra das Strins das mensagens 
 * para que o servidor identifique os comandos padronizados do protocolo especificado
 * 
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Krug
 */
public class Interpretador {


    /**
     * Método para fazer a quebra das Strings das mensagens
     * @param mensagem
     * @return 
     */
    public String[] interpretarMensagem(String mensagem) {
        String[] mensagens = mensagem.split("\\ ");
        return mensagens;
    }
    /**
     * Método para concatenar as Strings das mensagens
     * @param mensagem
     * @return 
     */
    public String juntarMensagem(String[] mensagem) {
        return this.juntarMensagem(mensagem, 1);
    }
    /**
     * Metodo responsavel por concaternar as strings das menagens privadas
     * @param mensagem
     * @return 
     */
    public String juntarMensagemPrivada(String[] mensagem) {
        return this.juntarMensagem(mensagem, 2);
    }
    
    /**
     * Metodo responsavel por concaternar uma mensagem, utilizando um index
     * @param mensagem
     * @param index
     * @return 
     */    
    private String juntarMensagem(String[] mensagem, int index) {
        String m = "";
        if (mensagem.length > index) {
            for (int i = index; i < mensagem.length; i++) {
                m += mensagem[i] + " ";
            }
        }
        return m;
    }

}
