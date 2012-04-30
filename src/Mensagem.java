/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiago
 */
public class Mensagem {
    
    private String mensagem;
    private TipoMensagem tipoMensagem;

    public Mensagem() {
        this.mensagem = "";
        this.tipoMensagem = TipoMensagem.publico;
    }

    public Mensagem(String mensagem, TipoMensagem tipoMensagem) {
        this.mensagem = mensagem;
        this.tipoMensagem = tipoMensagem;
    }
    
    public Mensagem(String mensagem) {
        this.mensagem = mensagem;
        this.tipoMensagem = TipoMensagem.publico;
    }
    
    public boolean enviar() {
        
        return true;
    }
    
}
