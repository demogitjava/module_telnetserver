package de.jsoft.telnetserver;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;

public class KeyStrokeListener implements WindowListener {
    public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent)
    {
        System.out.println("Pressed: "+ keyStroke);
         if(keyStroke.getKeyType() == KeyType.F1)
         {
             System.out.print("F1 to exit" + "\n");
         }
    }

    public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {
        // TODO Auto-generated method stub
    }


    public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {
        // TODO Auto-generated method stub
    }

    public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {
        // TODO Auto-generated method stub
    }


}
