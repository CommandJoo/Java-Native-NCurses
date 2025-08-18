package de.johannes.curses.ui.base;

import de.johannes.curses.CursesConstants;
import de.johannes.curses.Mouse;
import de.johannes.curses.ui.UI;
import de.johannes.curses.ui.components.Window;
import de.johannes.curses.util.ColorBuilder;

public class Component {

    protected final Window parent;
    protected int x, y;
    protected int color, originalColor;
    protected int hoverColor;

    public Component(Window parent, int x, int y, int color, int hoverColor) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.color = color;
        this.originalColor = color;
        this.hoverColor = hoverColor;
        if(!(this instanceof Window)) {
            this.init();
        }
    }

    public static Component of(Window parent, int x, int y, int color, int hoverColor) {
        return new Component(parent, x, y, color, hoverColor);
    }

    public Component() {
        this.parent = null;
        this.x = Integer.MIN_VALUE;
        this.y = Integer.MIN_VALUE;
        this.color = CursesConstants.WHITE;
        this.originalColor = this.color;
        this.hoverColor = CursesConstants.BLACK;
    }

    public Window parent() {
        return parent;
    }

    public int x() {
        return parent != null ? this.parent().x()+this.x : this.x;
    }

    public int y() {
        return parent != null ? this.parent().y()+this.y : this.y;
    }

    public int color() {
        return color;
    }

    public void init() {}
    public void draw() {}
    public boolean handleKey(char ch) {return false;}
    public boolean handleClick(Mouse mouse) {return false;}
    public boolean handleHover(int x, int y) {return false;}
    public void drawString(int x, int y, String s, int color) {
        UI.drawString(s, this.x() + x, this.y() + y, color);
    }
    public void drawCenteredString(int x, int y, String s, int color) {
        UI.drawCenteredString(s, this.x() + x, this.y() + y, color);
    }
    public void drawStringIndependent(int x, int y, String s, int color) {
        UI.drawString(s, x, y, color);
    }
    public void drawCenteredStringIndependent(int x, int y, String s, int color) {
        UI.drawCenteredString(s, x, y, color);
    }

    public Component setColor(int color) {
        this.color = color;
        return this;
    }

    public Component setColor(String hex) {
        this.color = new ColorBuilder().defineForeground(hex).build();
        return this;
    }
}
