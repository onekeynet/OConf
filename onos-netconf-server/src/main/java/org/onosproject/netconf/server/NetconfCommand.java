package org.onosproject.netconf.server;

import org.apache.sshd.common.Factory;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
public class NetconfCommand implements Command {

    private static final Logger log = LoggerFactory.getLogger(NetconfCommand.class);
    private static final String CMD_NAME = "netconf";

    private InputStream in;
    private OutputStream out;
    private OutputStream err;
    private Environment env;
    private ExitCallback callback;

    private NetconfCommand () {}

    @Override
    public void setInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public void setOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void setErrorStream(OutputStream err) {
        this.err = err;
    }

    @Override
    public void setExitCallback(ExitCallback callback) {
        this.callback = callback;
    }

    @Override
    public void start(Environment env) throws IOException {
        this.env = env;
        NetconfMessageHandler handler = new NetconfMessageHandler(in, out, err, callback);
        log.info("Start a handler to process SSH message.");
        new Thread(handler, "Netconf Port Listener").start();
    }

    @Override
    public void destroy() throws Exception {
        log.info("Command is destroyed.");
    }


    public static final class InternalNetconfNamedFactory implements NamedFactory<Command> {

        @Override
        public Command create() {
            return new NetconfCommand();
        }

        @Override
        public String getName() {
            return CMD_NAME;
        }
    }
}
