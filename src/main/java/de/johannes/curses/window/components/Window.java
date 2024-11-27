package de.johannes.curses.window.components;

import de.johannes.curses.Curses;
import de.johannes.curses.CursesConstants;
import de.johannes.curses.window.Component;
import de.johannes.curses.window.Keys;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class Window extends Component {

    protected final String title;
    private Button selected;

    private final HashMap<Integer, Component> components;

    public Window(Window parent, int x, int y, int width, int height) {
        this(parent, x, y, width, height, CursesConstants.WHITE);
    }
    public Window(Window parent, int x, int y, int width, int height, String title) {
        this(parent, x, y, width, height, CursesConstants.WHITE, title);
    }
    public Window(Window parent, int x, int y, int width, int height, int color) {
        this(parent, x, y, width, height, color, "");
    }
    public Window(Window parent, int x, int y, int width, int height, int color, String title) {
        super(parent, x, y, width, height, color);
        this.components = new HashMap<>();
        this.title = title;
        this.init();
    }

    public void drawWindow() {
            for(Component component : this.getComponents()) {
                drawComponent(component);
            }
            this.draw();
    }

    @Override
    public void drawBox(int color) {
        int rendercolor = color == -1 ? this.color : color;
        Curses.instance().setColor(rendercolor);

        Curses.instance().drawCorner(x, y, 2);
        Curses.instance().drawCorner(x + width, y, 1);
        Curses.instance().drawCorner(x, y + height, 3);
        Curses.instance().drawCorner(x + width, y + height, 0);

        Curses.instance().drawHorizontalLine(y, x + 1, x + width);
        Curses.instance().drawHorizontalLine(y + height, x + 1, x + width);
        Curses.instance().drawVerticalLine(x, y + 1, y + height);
        Curses.instance().drawVerticalLine(x + width, y + 1, y + height);

        if(!title.isEmpty()) {
            drawDecoration(width/2-((title.length()+4)/2), false, false, title, rendercolor);
        }
    }

    public void drawComponent(Component comp) {
        if (comp != null) {
            if(comp instanceof Window) {
                Window window = ((Window) comp);
                window.drawWindow();
            } else {
                comp.draw();
            }
        }
    }

    public boolean handleButtonKeys(char ch) {
        List<Button> btns = buttons();
        if(ch >= 258 && ch <= 261) {
            if(this.selected == null || !btns.contains(selected)) this.selected = btns.getFirst();
            int index = btns.indexOf(selected);
            if((int) ch == 258) {//down
                for(int i = index; i < btns.size(); i++) {
                    if(btns.get(i).y > selected.y) {
                        selected.setSelected(false);
                        this.selected = btns.get(i);
                        return true;
                    }
                }
            }else if((int)ch == 259) {//up
                for(int i = index; i >= 0; i--) {
                    if(btns.get(i).y < selected.y) {
                        selected.setSelected(false);
                        this.selected = btns.get(i);
                        return true;
                    }
                }
            }else if((int)ch == 260) {//left
                for(int i = index; i >= 0; i--) {
                    if(btns.get(i).x < selected.x) {
                        selected.setSelected(false);
                        this.selected = btns.get(i);
                        return true;
                    }
                }
            }else if((int)ch == 261) {//right
                for(int i = index; i < btns.size(); i++) {
                    if(btns.get(i).x > selected.x) {
                        selected.setSelected(false);
                        this.selected = btns.get(i);
                        return true;
                    }
                }
            }
        }
        if(ch == Keys.ESCAPE && selected != null) {
            this.selected().setSelected(false);
            this.selected = null;
        }
        return false;
    }

    public boolean handleKeyForSub(Component component, char ch) {
        return component.handleKey(ch);
    }

    public Component addComponent(int id, Component component) {
        if (this.components.containsKey(id))
            throw new IllegalArgumentException("Component with ID: " + id + " already registered");
        this.components.put(id, component);
        return component;
    }
    public List<Component> getComponents() {
        return List.copyOf(this.components.values());
    }
    @SuppressWarnings("unchecked")
    public <T extends Component>List<T> getComponents(Class<T> clazz) {
        List<Component> components = getComponents();
        List<T> elements = new ArrayList<>();
        components.forEach(comp -> {
            if(comp.getClass().isAssignableFrom(clazz)) elements.add((T) comp);
        });
        return elements;
    }
    private List<Button> buttons() {
        List<Button> buttons = getComponents(Button.class);
        buttons.sort(new Comparator<Button>() {
            @Override
            public int compare(Button o1, Button o2) {
                if(o1.y-o2.y == 0) {
                    return o1.x-o2.x;
                }
                return o1.y-o2.y;
            }
        });
        return buttons;
    }
    public Component getComponent(int id) {
        return components.getOrDefault(id, null);
    }

    public Button selected() {
        return selected;
    }

    public void setSelected(Button selected) {
        if(!components.containsValue(selected) && selected != null) throw new IllegalArgumentException("Button must be registered as Component!");
        if(this.selected != null) this.selected.setSelected(false);
        this.selected = selected;
        this.selected.setSelected(true);
    }
}