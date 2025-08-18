package de.johannes.example;

import de.johannes.curses.Curses;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.Keys;
import de.johannes.curses.Mouse;
import de.johannes.curses.ui.WindowManager;
import de.johannes.curses.ui.base.*;
import de.johannes.curses.ui.components.*;
import lombok.NonNull;

import java.util.List;

public class Example extends Window {

    public Example(Window parent, String title, int x, int y, int width, int height, int color, int originalColor, boolean closeable) {
        super(parent, title, x, y, width, height, color, originalColor, closeable);
    }

    @Override
    public void init() {
        Button button = ((Button) Button.of(this, 1, 1, 20, 2, color(), color())).setText("Click");
        addComponent(0, button);

        TextField textField = ((TextField) TextField.of(this, 1, 4, 20, 2, color(), color())).placeholder("Type Here: ");
        addComponent(1, textField);

        PasswordField passwordField = ((PasswordField) ((PasswordField) PasswordField.of(this, 1, 7, 20, 2, color(), color())).placeholder("Password Here: "));
        addComponent(2, passwordField);

        TextInput textInput = ((TextInput) TextInput.of(this, 1, 10, color(), color(), "Input:")).placeholder("Input:");
        addComponent(3, textInput);

        Selector selector = ((Selector) Selector.of(this, 1, 11, 20, 2, color(), color())).values("Apple", "Banana", "Mango", "Strawberry", "Cherry", "Blueberry");
        addComponent(4, selector);
        Link link = ((Link) Link.of(this, 1, 14, color(), color(), "GitHub")).display("GitHub", "https://github.com/CommandJoo/Java-Native-NCurses");
        addComponent(5, link);

        Text text = Text.of("Example Text")
                .at(1, 15)
                .parent(this)
                .format(color)
                .append(Text.of(" + Appended Text").format("#77AF99").attrib(CursesConstants.ATTRIB_ITALIC));
        addComponent(6, text);

        Window sub = new Window(this, "Sub Window", width / 2, 1, width / 2 - 1, height - 2, color, hoverColor, true) {
            @Override
            public void init() {
                Button btn = ((Button) Button.of(this, 1, 1, 20, 2, color(), color())).setText("Another One").setExecutor((m) -> {
                    WindowManager.instance().kill();
                });
                addComponent(0, btn);

                Listable list = ((Listable) Listable.of(this, 1, 4, 10, 20, color(), color())).content("1", "2", "3", "4", "5", "ABCDEFGHIJKLMNOP");
                addComponent(1, list);

                Dropdown dropdown = ((Dropdown) Dropdown.of(this, 12, 4, 9, 0, color(), color())).values("1", "2", "3", "4", "E", "F", "G");
                addComponent(2, dropdown);

                Checkbox box = ((Checkbox) Checkbox.of(this, 24, 1, 5, 2, color(), color()).rounded(true));
                addComponent(3, box);

                ScrollingText scrollingText = ((ScrollingText)ScrollingText.of(this, 1, 17, color(), color(), "Hello, This is a really long sentence, however it will scroll so you can read it all!")).width(20).scrollSpeedMillis(100);
                addComponent(4, scrollingText);
            }

            @Override
            public void draw() {
            }

            @Override
            public boolean handleKey(char ch) {
                if (ch == Keys.KEY_RIGHT) {
                    Listable list = ((Listable) getComponent(1));
                    list.content("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K");
                }
                for (Component comp : getComponents()) {
                    comp.handleKey(ch);
                }
                return false;
            }

            @Override
            public boolean handleClick(Mouse mouse) {
                for (Component comp : this.getComponents()) {
                    if ((comp instanceof BoxComponent)) {
                        if (mouse.x >= comp.x() && mouse.x <= comp.x() + ((BoxComponent) comp).width() &&
                                mouse.y >= comp.y() && mouse.y <= comp.y() + ((BoxComponent) comp).height()) {
                            if (comp instanceof Link) {
                                comp.setColor(comp.color() == color() ? CursesConstants.LIGHT_RED : color());
                            } else {
                                comp.handleClick(mouse);
                            }
                        }
                    }
                }
                return false;
            }

            @Override
            public boolean handleHover(int x, int y) {
                super.handleHover(x, y);
                return false;
            }
        };
        addComponent(7, sub);
    }

    @Override
    public void draw() {
//        for(int i = 0; i < this.getComponents().size(); i++) {
//            Curses.instance().drawString(this.getComponents().get(i).toString(),1, 1 + i);
//        }
    }

    @Override
    public boolean handleKey(char ch) {
        for (Component comp : getComponents()) {
            comp.handleKey(ch);
        }
        return false;
    }

    @Override
    public boolean handleClick(Mouse mouse) {
        for (Component comp : this.getComponents()) {
            if ((comp instanceof BoxComponent)) {
                if (mouse.x >= comp.x() && mouse.x <= comp.x() + ((BoxComponent) comp).width() &&
                        mouse.y >= comp.y() && mouse.y <= comp.y() + ((BoxComponent) comp).height()) {
                    if (comp instanceof Link) {
                        comp.setColor(comp.color() == color() ? CursesConstants.LIGHT_RED : color());
                    } else {
                        comp.handleClick(mouse);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean handleHover(int x, int y) {
        super.handleHover(x, y);
        return false;
    }
}
