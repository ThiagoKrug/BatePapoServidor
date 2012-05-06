
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que implementa o inicio da conexão por prte do cliente
 *
 * @author thiago
 */
public class Principal2 {

    private static Principal2 p;
    private Interpretador interpretador;
    private Usuarios listaUsuarios;

    private Principal2() {
        interpretador = new Interpretador();
        listaUsuarios = new Usuarios();
    }

    public static Principal2 getInstance() {
        if (p == null) {
            p = new Principal2();
        }
        return p;
    }

    public int iniciar() {
        

        return -1;
    }

    public void teste() {
        DatagramSocket socket = null;
        String comando[];
        try {
            socket = new DatagramSocket(5588);//Instancia do atributo conexao do tipo Socket, conecta a porta do servidor
            byte[] buffer = new byte[1000];
            while (true) {// Enquanto existir o servidor ele fica aguardando
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);//Recebe um pacote de dados do cliente e separa um espaço na memória para armazenar os dados
                socket.receive(request);// Armazena os dados na variável request
                System.out.println("recebi: " + new String(request.getData()));//Servidor responde para o cliente que recebeu o pacote de dados
                comando = interpretador.interpretarMensagem(request);//Dependendo do comando enviado o servidor seleciona a resposta válida
                DatagramPacket reply = null;// Inicializa a variável como null

                if (comando[0].equalsIgnoreCase("SERVER")) {//Comando para solicitar a conexão com o Servidor
                    String msgReply = "Esperando usuario";// Mensagem que o servidor exibe enquanto um cliente não conecta
                    reply = new DatagramPacket(msgReply.getBytes(), msgReply.length(), request.getAddress(), request.getPort());// Mensagem passada para o servidor 
                    socket.send(reply);//Comando que retorna a mensagem ao cliente
                    System.out.println("enviei: " + new String(reply.getData()));
                } else if (comando[0].equalsIgnoreCase("USER")) {//Comando para identificar o cliente no servidor
                    String msgReply = "";
                    if (comando.length > 1) {
                        boolean nomeValido = this.listaUsuarios.nomeValido(comando[1]);//Comando para testar se o nome do cliente é válido
                        if (nomeValido) {
                            boolean nomeDisponivel = this.listaUsuarios.nomeDisponivel(comando[1]);
                            if (nomeDisponivel) {
                                //this.listaUsuarios.addUsuario(new Usuario(comando[1], request.getAddress(), request.getPort()));
                                msgReply = "OK_USERNAME " + comando[1];//nome de usuário já existe. O servidor responde com o nome informado
                                reply = new DatagramPacket(msgReply.getBytes(), msgReply.length(), request.getAddress(), request.getPort());
                            } else {
                                msgReply = "ERR_ALREADYREGISTRED " + comando[1];//nome de usuário já existe. O servidor responde com o nome informado
                                reply = new DatagramPacket(msgReply.getBytes(), msgReply.length(), request.getAddress(), request.getPort());
                            }
                        } else {
                            msgReply = "ERR_INVALIDUSERNAME " + comando[1];//nome de usuário inválido. O servidor responde com o nome informado
                            reply = new DatagramPacket(msgReply.getBytes(), msgReply.length(), request.getAddress(), request.getPort());
                        }
                    } else {
                        msgReply = "ERR_NEEDMOREPARAMS";//: o parâmetro <username> está ausente.
                        reply = new DatagramPacket(msgReply.getBytes(), msgReply.length(), request.getAddress(), request.getPort());
                    }
                    socket.send(reply);//Comando que retorna a mensagem ao cliente
                    System.out.println("enviei: " + new String(reply.getData()));
                } else if (comando[0].equalsIgnoreCase("MSG")) { //Comando usado pelo Cliente para enviar uma mensagem ao Servidor
                    //String nomeUsuario = this.listaUsuarios.getNomeEmissor(request.getAddress());
                    //String msgReply = "MSG_SENDED " + nomeUsuario + " " + comando[1];
                    //this.sendForAll(socket, msgReply);
                    //System.out.println("enviei: " + msgReply);
                } else if (comando[0].equalsIgnoreCase("PRIVMSG")) {//Comando que permite que um cliente converse reservadamente com outro. A confirmação do envio é dada pelo PRIVMSG_SENDED 
                    socket.send(reply);//Comando que retorna a mensagem ao cliente
                    System.out.println("enviei: " + new String(reply.getData()));
                } else if (comando[0].equalsIgnoreCase("NAMES")) {//Comando utilizado pelos clientes para obter a lista de todos os clientes conectados naquele momento.
                    socket.send(reply);//Comando que retorna a mensagem ao cliente
                    System.out.println("enviei: " + new String(reply.getData()));
                } else if (comando[0].equalsIgnoreCase("QUIT")) {//Comando do cliente para deixar o bate-papo
                    socket.send(reply);//Comando que retorna a mensagem ao cliente
                    System.out.println("enviei: " + new String(reply.getData()));
                }

                comando = null;//Seta a variável como null
                buffer = null;//Seta a variável como null
                buffer = new byte[1000];//Seta o buffer para o máximo de 1000 bytes novamente
            }
        } catch (SocketException ex) {
            Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    private void sendForAll(DatagramSocket socket, String msgReply) throws IOException {
        List<Usuario> usuarios = this.listaUsuarios.getUsuarios();
        for (Iterator<Usuario> it = usuarios.iterator(); it.hasNext();) {
            Usuario usuario = it.next();
            DatagramPacket reply = new DatagramPacket(msgReply.getBytes(), msgReply.length(), usuario.getIp(), usuario.getPorta());
            socket.send(reply);
            reply = null;
        }
    }
}
