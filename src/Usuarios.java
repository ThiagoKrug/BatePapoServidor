
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Usuarios {

    private List<Usuario> listaUsuarios;

    public Usuarios() {
        this.listaUsuarios = new ArrayList<Usuario>();
    }

    public boolean addUsuario(Usuario usuario) {
        return listaUsuarios.add(usuario);
    }
    
    public boolean removerUsuario(Usuario usuario) {
        return listaUsuarios.remove(usuario);
    }

    public boolean nomeDisponivel(String nome) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return false;
            }
        }
        return true;
    }

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
    
    public PrintStream getStreamDestinatario(String nomeDestinatario) {
        for (Iterator<Usuario> it = listaUsuarios.iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            if (usuario.getNome().equals(nomeDestinatario)) {
                return usuario.getStreamSaida();
            }
        }
        return null;
    }
    
    public List<Usuario> getUsuarios() {
        return this.listaUsuarios;
    }
    
    public boolean contemUsuario(String nomeUsuario) {
        for (Iterator<Usuario> it = listaUsuarios.iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            if (usuario.getNome().equals(nomeUsuario)) {
                return true;
            }
        }
        return false;
    }
    
    public String getNames() {
        String retorno = "";
        for (Iterator<Usuario> it = listaUsuarios.iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            retorno += usuario.getNome() + " ";
        }
        return retorno;
    }
}
