package org.onosproject.netconf.server;


import com.google.common.collect.Lists;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.CommandFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.auth.password.PasswordChangeRequiredException;
import org.apache.sshd.server.auth.pubkey.AcceptAllPublickeyAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.ScpCommandFactory;
import org.apache.sshd.server.session.ServerProxyAcceptor;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.session.SessionFactory;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public class NetconfMinaServer {

    private static final Logger log = LoggerFactory.getLogger(NetconfMinaServer.class);

    private static final String HOST = "127.0.0.1";
    private static final int PORT=9999;
    private static final String USER = "oconf";
    private static final String PASS = "oconf";

    public static void main(String[] args) {
        new NetconfMinaServer().start();
    }

    private void start() {
        // Server configuration.
        SshServer server = SshServer.setUpDefaultServer();
//        server.setHost(HOST);
        server.setPort(PORT);
        server.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
//        server.setPublickeyAuthenticator(AcceptAllPublickeyAuthenticator.INSTANCE);
        server.setPasswordAuthenticator(new PasswordAuthenticator() {
            @Override
            public boolean authenticate(String username, String password, ServerSession session)
                    throws PasswordChangeRequiredException {
                return (username.equals(USER) && password.equals(PASS) && session.isOpen());
            }
        });
        // TODO Timeout parameters are not configured yet.
        List<NamedFactory<Command>> nf = Lists.newArrayList();
        nf.add(new NetconfCommand.InternalNetconfNamedFactory());
        server.setSubsystemFactories(nf);

//        ScpCommandFactory scpCmdFactory=new ScpCommandFactory();
//        scpCmdFactory.setDelegateCommandFactory(new CommandFactory() {
//            public Command createCommand(String command) {
//                System.out.println("command = \"" + command + "\"");
//                return new ProcessShellFactory(("cmd /c "+command).split(" ")).create();
//            }
//        });
//        server.setCommandFactory(scpCmdFactory);

        try {
            server.start();
            log.info("Netconf server started...");
            while(true) {}
        } catch (IOException e) {
            log.error("Listen {}:{} is failed. Please check the permission.", HOST, PORT);
            e.printStackTrace();
        }

    }
}
