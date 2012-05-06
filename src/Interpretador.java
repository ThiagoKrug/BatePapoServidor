
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
