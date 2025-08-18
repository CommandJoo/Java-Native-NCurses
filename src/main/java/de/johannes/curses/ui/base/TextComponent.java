package de.johannes.curses.ui.base;

import de.johannes.curses.CursesConstants;
import de.johannes.curses.ui.components.Text;
import de.johannes.curses.ui.components.Window;

public class TextComponent extends BoxComponent {

    protected String text;

    public TextComponent(Window parent, int x, int y, int width, int height, int color, int hoverColor, String text) {
        super(parent, x, y, width, height, color, hoverColor);
        this.text = text;
    }

    public static TextComponent of(Window parent, int x, int y, int color, int hoverColor, String text) {
        return new TextComponent(parent, x, y, 0, 1, color, hoverColor, text);
    }


    @Override
    public void drawBox() {}

    @Override
    public void drawDecoration(int x, boolean bottom, boolean parens, String deco, int color) {}
}

