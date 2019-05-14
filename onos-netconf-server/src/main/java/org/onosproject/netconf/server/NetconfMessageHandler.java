package org.onosproject.netconf.server;

import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class NetconfMessageHandler implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(NetconfMessageHandler.class);

    private static final String END_PATTERN = "]]>]]>";

    private InputStream in;
    private OutputStream out;
    private OutputStream err;
    private Environment env;
    private ExitCallback callback;
    private int sessionId;

    public NetconfMessageHandler(InputStream in, OutputStream out, OutputStream err, ExitCallback callback) {
        this.in = in;
        this.out = out;
        this.err = err;
        this.callback = callback;
    }

    @Override
    public void run() {

        StringBuilder message = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        try {
            out.write(helloXml.getBytes());
            while ((line = br.readLine()) != null) {
                log.trace("Line read: '" + line + "'");
                if (line.endsWith(END_PATTERN)) {
                    log.trace("Detected end message.");
                    // remove end char sequence from message
                    line = line.replace(END_PATTERN, "");
                    message.append(line + '\n');
                    log.info(message.toString());
                    // reset message
                    message.setLength(0);
                }
                message.append(line + '\n');
            }
        } catch (IOException e) {
            log.error("IOException: {}.", e.toString());
        }

    }

    String helloXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><nc:hello xmlns:nc=\"urn:ietf:params:xml:ns:netconf:" +
            "base:1.0\"><nc:capabilities><nc:capability>urn:ietf:params:netconf:base:1.0</nc:capability><nc:capabil" +
            "ity>urn:ietf:params:netconf:base:1.1</nc:capability><nc:capability>urn:ietf:params:netconf:capability:" +
            "writable-running:1.0</nc:capability><nc:capability>urn:ietf:params:netconf:capability:candidate:1.0</nc" +
            ":capability><nc:capability>urn:ietf:params:netconf:capability:confirmed-commit:1.0</nc:capability><nc:c" +
            "apability>urn:ietf:params:netconf:capability:rollback-on-error:1.0</nc:capability><nc:capability>urn:iet" +
            "f:params:netconf:capability:startup:1.0</nc:capability><nc:capability>urn:ietf:params:netconf:capability" +
            ":url:1.0?scheme=http,ftp,file,https,sftp</nc:capability><nc:capability>urn:ietf:params:netconf:capabilit" +
            "y:validate:1.0</nc:capability><nc:capability>urn:ietf:params:netconf:capability:xpath:1.0</nc:capability" +
            "><nc:capability>urn:ietf:params:netconf:capability:notification:1.0</nc:capability><nc:capability>urn:li" +
            "berouter:params:netconf:capability:power-control:1.0</nc:capability><nc:capability>urn:ietf:params:netco" +
            "nf:capability:interleave:1.0</nc:capability><nc:capability>urn:ietf:params:netconf:capability:with-defau" +
            "lts:1.0</nc:capability></nc:capabilities></nc:hello>" + END_PATTERN;
}
