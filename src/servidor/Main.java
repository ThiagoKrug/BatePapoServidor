package servidor;


/**
 * Classe para inicializar o programa servidor que cria uma instancia do objeto
 * principal que inicia o servidor.
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public class Main {

    /**
     * Metodo main responsável por inicializar o servidor.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        Principal p = Principal.getInstance();
        p.iniciarServidor();
    }
}
