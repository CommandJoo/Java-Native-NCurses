package de.johannes.curses.ui.components;

import de.johannes.curses.Mouse;
import de.johannes.curses.ui.base.Component;
import de.johannes.curses.ui.base.TextComponent;
import de.johannes.curses.util.ColorBuilder;

import java.awt.*;
import java.net.URI;


public class Link extends TextComponent {

    private String display, url;

    public Link(Window parent, int x, int y, int width, int height, int color, int hoverColor, String text) {
        super(parent, x, y, width, height, color, hoverColor, text);
    }

    public static Link of(Window parent, int x, int y, int color, int hoverColor, String text) {
        return new Link(parent, x, y, 0, 1, color, hoverColor,text);
    }

    public Link display(String display, String url) {
        this.display = display;
        this.url = url;
        return this;
    }

    @Override
    public void init() {}

    public void open() {
        try {
            Desktop.getDesktop().browse(new URI(this.url));
        } catch(Exception ex) {
            color = new ColorBuilder().defineForeground("#FF0000").build();
        }
    }

    @Override
    public void draw() {
        drawString(0,0,"$u"+this.display+"$r", color);
    }

    @Override
    public boolean handleKey(char ch) {return false;}

    @Override
    public boolean handleClick(Mouse mouse) {
        return false;
    }

    @Override
    public boolean handleHover(int x, int y) {
        return false;
    }
}
