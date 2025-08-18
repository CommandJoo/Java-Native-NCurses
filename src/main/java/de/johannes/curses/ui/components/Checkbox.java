package de.johannes.curses.ui.components;

import de.johannes.curses.Curses;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.Mouse;
import de.johannes.curses.nerdfont.NFCodicons;
import de.johannes.curses.nerdfont.NFOcticons;
import de.johannes.curses.ui.base.BoxComponent;
import de.johannes.curses.ui.base.TextComponent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

public class Checkbox extends BoxComponent {

    private boolean selected;

    public Checkbox(Window parent, int x, int y, int width, int height, int color, int hoverColor) {
        super(parent, x, y, width, height, color, hoverColor);
    }

    public static Checkbox of(Window parent, int x, int y, int width, int height, int color, int hoverColor) {
        return new Checkbox(parent, x, y, width, height, color, hoverColor);
    }

    @Override
    public void init() {}

    @Override
    public void draw() {
        this.drawBox();
        if(selected()) {
            Curses.reverseClearBox(x()+2, y()+1, width()-3, height()-1, color());
        }
    }

    @Override
    public boolean handleKey(char ch) {
        return false;
    }

    @Override
    public boolean handleClick(Mouse mouse) {
        if(!mouse.check(Mouse.BUTTON1_RELEASED)) {
            setSelected(!selected);
        }
        return true;
    }

    @Override
    public boolean handleHover(int x, int y) {
        return false;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean selected() {
        return selected;
    }
}
