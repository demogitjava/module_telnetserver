package de.jsoft.telnetserver;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.ansi.TelnetTerminal;

import com.google.common.base.Joiner;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWindowTelnet
{

    //public Screen screen = null;

    // username
    private static final List<TextBox> ALL_TEXTBOXES = new ArrayList<>();

    // password
    private static final List<TextBox> ALL_TEXTPASS = new ArrayList<>();


    public MainWindowTelnet(final TelnetTerminal telnetTerminal) throws IOException {



        final Screen screen = new TerminalScreen(telnetTerminal);

        screen.startScreen();
        final MultiWindowTextGUI textGUI = new MultiWindowTextGUI(screen);

        textGUI.setBlockingIO(false);
        textGUI.setEOFWhenNoWindows(true);

        try {
            final BasicWindow window = new BasicWindow("Your Company Name");

            window.setHints(Arrays.asList(Window.Hint.FULL_SCREEN));
           // window.setSize(MainWindowTelnet);
            KeyStrokeListener listener = new KeyStrokeListener();
            window.addWindowListener(listener);

            //    window.setSize(new TerminalSize(80,24));
            Panel contentArea = new Panel();
            contentArea.setSize(screen.getTerminalSize());
            contentArea.setLayoutManager(new LinearLayout(Direction.VERTICAL));



            final Label lbuser = new Label("username:").setForegroundColor(TextColor.ANSI.BLACK);

            final TextBox textBox = new TextBox(new TerminalSize(20, 1)) {
                @Override
                public Result handleKeyStroke(KeyStroke keyStroke) {
                    try
                    {
                        return super.handleKeyStroke(keyStroke);
                    }
                    finally
                    {
                        for(TextBox textBox: ALL_TEXTBOXES) {
                            if(this != textBox) {
                                textBox.setText(getText());
                            }
                        }


                    }
                }
            };



            contentArea.addComponent(lbuser);
            contentArea.addComponent(textBox);


            final Label lbpassw = new Label("password").setForegroundColor(TextColor.ANSI.BLACK);
            final TextBox txpass = new TextBox(new TerminalSize(20, 1)) {
                @Override
                public Result handleKeyStroke(KeyStroke keyStroke) {
                    if(keyStroke.getKeyType() == KeyType.Enter) {
                        //action.run()




                        String stuserst = ALL_TEXTBOXES.get(0).getText();
                        String stpass = ALL_TEXTPASS.get(0).getText();
                        return Result.HANDLED;
                    }
                    return super.handleKeyStroke(keyStroke);
                }
            };

            // set Mask for password field
            txpass.setMask('*');

            // ************************************

            ALL_TEXTBOXES.add(textBox);
            ALL_TEXTPASS.add(txpass);

            contentArea.addComponent(lbpassw);
            contentArea.addComponent(txpass);

            //contentArea.addComponent(new Button("login", window.addA));
            window.setComponent(contentArea);

            textGUI.addWindowAndWait(window);
        }
        finally {
            try {
                screen.stopScreen();
            }
            catch (SocketException ignore) {
                // If the telnet client suddenly quit, we'll get an exception when we try to get the client to exit
                // private mode, but that's fine, no need to report this
            }
        }


    }

    public void keyPressed(KeyEvent evt_)
    {
        int key = evt_.getKeyCode();

    }




}



