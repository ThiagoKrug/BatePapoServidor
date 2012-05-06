
import java.net.DatagramPacket;

/**
 * Classe Interpretador utilizada para fazer a quebra das Strins das mensagens 
 * para que o servidor identifique os comandos padronizados do protocolo especificado
 * 
 * @author Thiago Krug
 */
public class Interpretador {

    public String[] interpretarMensagem(DatagramPacket request) {
        byte[] buffer = request.getData();
        byte[] buffer2 = new byte[request.getLength()];
        for (int i = 0; i < request.getLength(); i++) {
            buffer2[i] = buffer[i];
        }
        String mensagem = new String(buffer2);
        return this.interpretarMensagem(mensagem);
    }
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
    
    public String juntarMensagemPrivada(String[] mensagem) {
        return this.juntarMensagem(mensagem, 2);
    }
    
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
