package servidor;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe Usuarios utilizada para criar uma lista de usuarios que estao
 * conectados no chat em um determinado momento
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public class Usuarios {

    private List<Usuario> listaUsuarios;

    /**
     * Metodo construtor que cria a lista de usuarios
     */
    public Usuarios() {
        this.listaUsuarios = new ArrayList<Usuario>();
    }

    /**
     * Metodo que adiciona um novo cliente que se conectou no chat na lista de
     * usuarios conectados
     *
     * @param usuario
     * @return true caso foi adicionado com sucesso
     */
    public boolean addUsuario(Usuario usuario) {
        return listaUsuarios.add(usuario);
    }

    /**
     * Metodo que remove um cliente da lista assim que ele tenha se desconectado
     * do chat
     *
     * @param usuario
     * @return true caso foi removido com sucesso
     */
    public boolean removerUsuario(Usuario usuario) {
        return listaUsuarios.remove(usuario);
    }

    /**
     * Metodo que busca se o nome de um usuario que entrou por ultimo no chat ja
     * nao esta sendo utilizado por outra pessoa, pois duas pessoas naum podem
     * ter o mesmo nome dentro do chat
     *
     * @param nome
     * @return true se o nome estiver disponivel para ser usado
     * @return false caso o nome ja tiver estiver sendo utilizado
     */
    public boolean nomeDisponivel(String nome) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo que verifica se o nome digitado por um cliente é valido ou não, ou
     * seja se ele nao digitou algum caratere invalido
     *
     * @param nome
     * @return true se o nome é valido
     * @return false se o nome não é valido
     */
    public static boolean nomeValido(String nome) {
        if (nome.length() < 10) {
            for (int i = 0; i < nome.length(); i++) {
                char letra = nome.charAt(i);
                if (!(Character.isLetterOrDigit(letra) || letra == "_".charAt(0))) {
                    System.out.println(letra);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Metodo para pegar o o fluxo de dados do destinatario da mensagem
     *
     * @param nomeDestinatario
     * @return
     */
    public PrintStream getStreamDestinatario(String nomeDestinatario) {
        for (Iterator<Usuario> it = listaUsuarios.iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            if (usuario.getNome().equals(nomeDestinatario)) {
                return usuario.getStreamSaida();
            }
        }
        return null;
    }

    /**
     * Metodo que retorna a lista de usuarios
     *
     * @return lista de usuarios
     */
    public List<Usuario> getUsuarios() {
        return this.listaUsuarios;
    }

    /**
     * Metodo que retorna true caso o nome de um usuario esteja na lista
     *
     * @param nomeUsuario
     * @return true caso o nome esteja na lista
     */
    public boolean contemUsuario(String nomeUsuario) {
        for (Iterator<Usuario> it = listaUsuarios.iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            if (usuario.getNome().equals(nomeUsuario)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que retorna o nome de todos os usuarios contidos na lista
     *
     * @return retorno
     */
    public String getNames() {
        String retorno = "";
        for (Iterator<Usuario> it = listaUsuarios.iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            retorno += usuario.getNome() + " ";
        }
        return retorno;
    }
}
