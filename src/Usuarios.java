
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author thiago
 */
public class Usuarios {

    private List<Usuario> listaUsuarios;

    public Usuarios() {
        this.listaUsuarios = new ArrayList<Usuario>();
    }

    public boolean addUsuario(Usuario usuario) {
        return listaUsuarios.add(usuario);
    }

    public boolean nomeDisponivel(String nome) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return false;
            }
        }
        return true;
    }

    public boolean nomeValido(String nome) {
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
    
    public String getNomeEmissor(InetAddress ip) {
        for (Iterator<Usuario> it = listaUsuarios.iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            if (usuario.getIp().equals(ip)) {
                return usuario.getNome();
            }
        }
        return null;
    }
    
    public List<Usuario> getUsuarios() {
        return this.listaUsuarios;
    }
}
