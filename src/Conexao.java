
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author thiago
 */
public class Conexao extends Thread {

    private Socket conexaoCliente;
    public static String SERVER = "SERVER";
    public static String USER = "USER";
    public static String OK_USERNAME = "OK_USERNAME";
    public static String ERR_INVALIDUSERNAME = "ERR_INVALIDUSERNAME";
    public static String ERR_NEEDMOREPARAMS = "ERR_NEEDMOREPARAMS";
    public static String ERR_ALREADYREGISTRED = "ERR_ALREADYREGISTRED";
    public static String MSG = "MSG";
    public static String MSG_SENDED = "MSG_SENDED";
    public static String PRIVMSG = "PRIVMSG";
    public static String ERR_NOSUCHNICK = "ERR_NOSUCHNICK";
    public static String NOTEXTTOSEND = "NOTEXTTOSEND";
    public static String PRIVMSG_SENDED = "PRIVMSG_SENDED";
    public static String NAMES = "NAMES";
    public static String QUIT = "QUIT";

    public Conexao(Socket conexaoCliente) {
        this.conexaoCliente = conexaoCliente;
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexaoCliente.getInputStream()));
            PrintStream saida = new PrintStream(conexaoCliente.getOutputStream());
            // aqui a cobra fuma!
            Usuario usuario = null;
            // lê o comando SERVER
            String server = entrada.readLine();
            String[] mensagemUsuario = null;
            String[] mensagemServer = new Interpretador().interpretarMensagem(server);
            // se o comando for SERVER, e se o vetor contiver o 2 ou 3 parâmetros, faz:
            if (mensagemServer[0].equalsIgnoreCase(Conexao.SERVER)
                    && mensagemServer.length > 1 && mensagemServer.length < 4) {
                // lê o comando USER
                saida.println("Esperando nome do usuário");
                String usuarioLine = entrada.readLine();
                mensagemUsuario = new Interpretador().interpretarMensagem(usuarioLine);
                // se o comando for USER, e se o vetor contiver 2 parâmetros, faz:
                if (mensagemUsuario[0].equalsIgnoreCase(Conexao.USER)
                        && mensagemUsuario.length == 2) {
                    // se o nome do usuário for inválido
                    if (Usuarios.nomeValido(mensagemUsuario[1])) {
                        // se o nome do usuário está disponível
                        if (Principal.getUsuarios().nomeDisponivel(mensagemUsuario[1])) {
                            // retorna OK_USERNAME <username>
                            saida.println(Conexao.OK_USERNAME + " " + mensagemUsuario[1]);
                            // cria o usuario
                            usuario = new Usuario(mensagemUsuario[1], conexaoCliente.getInetAddress(),
                                    conexaoCliente.getPort(), saida);
                            // adiciona na lista
                            Principal.getUsuarios().addUsuario(usuario);
                            // envia para todos dizendo que o usuário está conectado MSG_SENDED <nome_emissor> <mensagem>
                            this.enviarParaTodos(saida, Conexao.MSG_SENDED, usuario.getNome(), usuario.getNome() + " conectou");
                            // lê a próxima mensagem
                            String linha = entrada.readLine();
                            String[] mensagem = new Interpretador().interpretarMensagem(linha);
                            // monta a String inicial da fala
                            String usuarioDiz = usuario.getNome() + " disse: ";
                            String usuarioPrivadoDiz = usuario.getNome() + " disse reservadamente: ";
                            // enquanto a mensagem não for QUIT, faz:
                            while (!mensagem[0].equalsIgnoreCase(Conexao.QUIT)) {
                                // se for uma mensagem MSG
                                if (mensagem[0].equalsIgnoreCase(Conexao.MSG)) {
                                    this.enviarParaTodos(saida, Conexao.MSG_SENDED, usuario.getNome(),
                                            usuarioDiz + new Interpretador().juntarMensagem(mensagem));
                                    // se a mensagem for PRIVMSG
                                } else if (mensagem[0].equalsIgnoreCase(Conexao.PRIVMSG)) {
                                    // se o usuário existe na lista
                                    if (Principal.getUsuarios().contemUsuario(mensagem[1])) {
                                        // se falta a mensagem
                                        if (mensagem.length > 2) {
                                            this.enviarPrivado(saida, Conexao.PRIVMSG_SENDED, usuario.getNome(),
                                                    mensagem[1], usuarioPrivadoDiz + new Interpretador().juntarMensagem(mensagem));
                                        } else { // senão retorna o erro NOTEXTTOSEND
                                            saida.println(Conexao.NOTEXTTOSEND);
                                        }
                                    } else { // senão retorna o erro ERR_NOSUCHNICK
                                        saida.println(Conexao.ERR_NOSUCHNICK + " " + mensagem[1]);
                                    }
                                    // se a mensagem for NAMES
                                } else if (mensagem[0].equalsIgnoreCase(Conexao.NAMES)) {
                                    this.comandoNames(saida);
                                }
                                linha = entrada.readLine();
                                mensagem = new Interpretador().interpretarMensagem(linha);
                            }
                            // se o a mensagem for QUIT
                            if (mensagem[0].equalsIgnoreCase(Conexao.QUIT)) {
                                // se informou uma mensagem de saida
                                if (mensagem.length > 1) {
                                    this.comandoQuit(saida, usuario.getNome(), new Interpretador().juntarMensagem(mensagem));
                                } else {
                                    this.comandoQuit(saida, usuario.getNome(), "");
                                }
                            }
                        } else { // senão retorna o erro ERR_ALREADYREGISTRED <username>
                            saida.println(Conexao.ERR_ALREADYREGISTRED + " " + mensagemUsuario[1]);
                        }
                    } else { // senão retorna o erro ERR_INVALIDUSERNAME <username>
                        saida.println(Conexao.ERR_INVALIDUSERNAME + " " + mensagemUsuario[1]);
                    }
                } else { // senão retorna o erro ERR_NEEDMOREPARAMS
                    saida.println(Conexao.ERR_NEEDMOREPARAMS);
                }
            }
            if (usuario != null) {
                System.out.println(usuario.getNome() + " desconectou!");
                Principal.getUsuarios().removerUsuario(usuario);
            }
            conexaoCliente.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public void enviarParaTodos(PrintStream saida, String tipoMensagem,
            String nomeEmissor, String mensagem) throws IOException {
        for (Iterator it = Principal.getUsuarios().getUsuarios().iterator(); it.hasNext();) {
            Usuario usuario = (Usuario) it.next();
            PrintStream streamSaida = usuario.getStreamSaida();
            //if (streamSaida != saida) {
            streamSaida.println(tipoMensagem + " " + nomeEmissor + " " + mensagem);
            //}
        }
    }

    public void enviarPrivado(PrintStream saida, String tipoMensagem,
            String nomeEmissor, String destinatario, String mensagem) {

        PrintStream streamSaida = Principal.getUsuarios().getStreamDestinatario(destinatario);
        if (streamSaida != saida) {
            streamSaida.println(tipoMensagem + " " + nomeEmissor + " " + mensagem);
        }
    }

    public void comandoNames(PrintStream saida) {
        saida.println(Conexao.NAMES + " " + Principal.getUsuarios().getNames());
    }

    public void comandoQuit(PrintStream saida, String nomeUsuario, String mensagem) {
        for (Iterator it = Principal.getUsuarios().getUsuarios().iterator(); it.hasNext();) {
            Usuario usuario = (Usuario) it.next();
            PrintStream streamSaida = usuario.getStreamSaida();
            streamSaida.println(Conexao.QUIT + " " + nomeUsuario + " " + mensagem);
        }
    }
}
