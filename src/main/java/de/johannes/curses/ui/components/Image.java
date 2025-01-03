package de.johannes.curses.ui.components;

import de.johannes.curses.Mouse;
import de.johannes.curses.util.ColorBuilder;
import de.johannes.curses.util.ColorUtil;
import de.johannes.curses.ui.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Deprecated
public class Image extends Component {

    private BufferedImage image;
    private boolean error;

    public Image(Window parent, int x, int y, int width, File image) {
        super(parent, x, y, width, 0, false);
        setImage(image);
    }

    private HashMap<Integer, List<Integer>> lines;

    @Override
    public void init() {
        this.lines = new HashMap<>();

        for (int j = 0; j < this.image.getHeight()/2; j++) {
            List<Integer> color = new ArrayList<>();
            for (int i = 0; i < this.image.getWidth(); i++) {
                try {
                    Color background = ColorUtil.averageColor(this.image,  i, 2*j, 1, 1);
                    Color foreground = ColorUtil.averageColor(this.image, i, 2*j, 1, 1);
                    color.add(
                            ColorBuilder
                                    .create()
                                    .defineForeground(foreground)
                                    .defineBackground(background)
                                    .build()
                    );
                } catch (Exception ex) {

                }
            }
            lines.put(j, color);
        }

    }

    @Override
    public void draw() {
        if (error) {
            drawCenteredString(x + width / 2, y + height / 2, "Error: couldn't load image", color);
        } else {
            for (Integer y : lines.keySet()) {
                List<Integer> color = lines.get(y);
                for (int x = 0; x < color.size(); x++) {
                    Integer col = color.get(x);
                    drawString(x, y, "▄", col);//█▄
                }
            }
        }
    }

    @Override
    public boolean handleKey(char ch) {return false;}

    @Override
    public boolean handleClick(Mouse mouse) {return false;}

    public void setImage(File image) {
        BufferedImage original = ColorUtil.readImage(image);
        if(original == null) {
            this.error = true;
            return;
        }
        try {
            this.image = ColorUtil.resizeImage(original, width, (int) (width*((float) original.getHeight() /original.getWidth())));
            this.error = false;
            this.init();

        } catch(Exception ex) {
            this.error = true;
        }
    }
}
