
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
        String m = "";
        if (mensagem.length > 1) {
            for (int i = 1; i < mensagem.length; i++) {
                m += mensagem[i] + " ";
            }
        }
        return m;
    }

}
