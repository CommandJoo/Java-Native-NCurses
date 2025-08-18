package de.johannes.curses.ui.components;

public class PasswordField extends TextField {

    public PasswordField(Window parent, int x, int y, int width, int height, int color, int hoverColor) {
        super(parent, x, y, width, height, color, hoverColor);
    }

    public static PasswordField of(Window parent, int x, int y, int width, int height, int color, int hoverColor) {
        return new PasswordField(parent, x, y, width, height, color, hoverColor);
    }

    @Override
    public void draw() {
        this.drawBox();
        if(input != null) {
            String text = !input.isEmpty() ? "*".repeat(input.length()) : placeholder;
            String cursor = !input.isEmpty() ? blinker.check(500) ? " " : "█" : "";
            drawString(1,1, text+cursor, color);
            if(blinker.check(1000)) blinker.reset();
        }
    }

}
