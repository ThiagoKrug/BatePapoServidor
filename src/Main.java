
/**
 * Classe para inicializar o programa servidor que cria uma instancia do objeto
 * principal que inicia o servidor utilizando o Padrão Singleton
 *
 * @author Thiago Krug
 */
public class Main {

    public static void main(String[] args) {
        Principal p = Principal.getInstance();
        p.iniciarServidor();
    }
}
