package de.jsoft.telnetserver;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.ansi.TelnetTerminal;
import com.googlecode.lanterna.terminal.ansi.TelnetTerminalServer;
import de.jsoft.telnetserver.MainWindowTelnet;

import java.io.IOException;
import java.nio.charset.Charset;


public class JTelnetServer
{

    public static void main(String[] args)
    {

        // Charset.forName("utf-8")
        TelnetTerminalServer telnetTerminalServer = null;
        try {
            telnetTerminalServer = new TelnetTerminalServer(23, Charset.forName("ISO-8859-1"));

            System.out.println("TerminalServer is started on port 23");
            //noinspection InfiniteLoopStatement
            while (true) {

                final TelnetTerminal telnetTerminal = telnetTerminalServer.acceptConnection();
                //final KeyStroke keyPressed = telnetTerminal.readInput();

                //telnetTerminal.setBackgroundColor(TextColor.ANSI.WHITE);
                //telnetTerminal.setForegroundColor(TextColor.ANSI.BLACK);

                System.out.println("Accepted connection from " + telnetTerminal.getRemoteSocketAddress());

                Thread thread = new Thread(() -> {
                    try {
                        new MainWindowTelnet(telnetTerminal);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        telnetTerminal.close();
                    }
                    catch(IOException ignore) {}
                });
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

