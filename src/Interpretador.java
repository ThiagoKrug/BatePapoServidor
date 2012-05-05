
import java.net.DatagramPacket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
        String[] mensagens = mensagem.split("\\ ");
        return mensagens;
    }
    
}
