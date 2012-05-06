
import java.net.DatagramPacket;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author thiago
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
    
    public String[] interpretarMensagem(String mensagem) {
        String[] mensagens = mensagem.split("\\ ");
        return mensagens;
    }
    
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
