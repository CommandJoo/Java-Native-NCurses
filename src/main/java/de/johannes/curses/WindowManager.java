package de.johannes.curses;

import de.johannes.curses.util.Timer;
import de.johannes.curses.window.components.Window;

import java.util.HashMap;

public class WindowManager {

    private static WindowManager instance;
    private boolean running;

    private final HashMap<Integer, Window> windows;

    public final int fps;
    private final Timer fpsTimer;

    public WindowManager(final int fps) {
        if (instance != null) throw new IllegalStateException("Only one WindowManager is allowed per Runtime!");
        instance = this;

        this.fps = fps;
        this.windows = new HashMap<>();
        this.fpsTimer = new Timer();

        Curses.instance();
        this.running = true;
    }

    public void render() {
        new Thread(() -> {
            while (running) {
                if (fpsTimer.check(1000 / fps)) {
                    windows.forEach((id1, window1) -> {
                        Curses.instance().clearBox(window1.x,window1.y,window1.width,window1.height);
                        window1.drawBox(window1.color);
                        windows.forEach((id2, window2) -> {
                            if(window2.x + window2.width == window1.x) {
                                if(window2.y == window1.y) {
                                    Curses.instance().drawTee(window1.x, window1.y, 3, window1.color);
                                    if(window2.height > window1.height) {
                                        Curses.instance().drawTee(window1.x, window2.y+window1.height, 0, window1.color);
                                    } else if(window2.height < window1.height) {
                                        Curses.instance().drawTee(window1.x, window2.y+window2.height, 1, window1.color);
                                    } else {
                                        Curses.instance().drawTee(window1.x, window1.y+window1.height, 2, window1.color);
                                    }
                                }else if(window2.y < window1.y) {
                                    Curses.instance().drawTee(window1.x, window1.y, 0, window1.color);
                                    Curses.instance().drawTee(window1.x, window2.y+window1.height, 1, window1.color);
                                }else if(window2.y < window1.y+window1.height) {
                                    Curses.instance().drawTee(window1.x, window2.y, 1, window1.color);
                                    Curses.instance().drawTee(window1.x, window1.y+window2.height, 0, window1.color);
                                }
                            }
                            else if(window1.x + window1.width == window2.x) {
                                if(window1.y == window2.y) {
                                    Curses.instance().drawTee(window2.x, window2.y, 3, window2.color);
                                    if(window2.height > window1.height) {
                                        Curses.instance().drawTee(window2.x, window1.y+window1.height, 1, window2.color);
                                    } else if(window2.height < window1.height) {
                                        Curses.instance().drawTee(window2.x, window1.y+window2.height, 0, window2.color);
                                    } else {
                                        Curses.instance().drawTee(window2.x, window2.y+window2.height, 2, window2.color);
                                    }
                                }else if(window1.y < window2.y) {
                                    Curses.instance().drawTee(window2.x, window2.y, 0, window2.color);
                                    Curses.instance().drawTee(window2.x, window1.y+window2.height, 1, window2.color);
                                }else if(window1.y < window2.y+window2.height) {
                                    Curses.instance().drawTee(window2.x, window1.y, 1, window2.color);
                                    Curses.instance().drawTee(window2.x, window2.y+window1.height, 0, window2.color);
                                }
                            }
                            if(window2.y + window2.height == window1.y) {
                                if(window2.x == window1.x) {
                                    Curses.instance().drawTee(window1.x, window1.y, 0, window1.color);
                                    Curses.instance().drawTee(window1.x+window1.width, window1.y, 1, window1.color);
                                }else if(window2.x < window1.x) {
                                    Curses.instance().drawTee(window1.x, window1.y, 3, window2.color);
                                    Curses.instance().drawTee(window2.x+window1.width, window1.y, 2, window2.color);
                                }else if(window2.x < window1.x+window1.width) {
                                    Curses.instance().drawTee(window2.x, window1.y, 2, window2.color);
                                    Curses.instance().drawTee(window1.x+window2.width, window1.y, 3, window2.color);
                                }

                            }
                            else if(window1.y + window1.height == window2.y) {
                                if(window1.x == window2.x) {
                                    Curses.instance().drawTee(window2.x, window2.y, 0, window2.color);
                                    Curses.instance().drawTee(window2.x+window2.width, window2.y, 1, window2.color);
                                }else if(window1.x < window2.x) {
                                    Curses.instance().drawTee(window2.x, window2.y, 3, window1.color);
                                    Curses.instance().drawTee(window1.x+window2.width, window2.y, 2, window1.color);
                                }else if(window1.x < window2.x+window2.width) {
                                    Curses.instance().drawTee(window1.x, window2.y, 2, window1.color);
                                    Curses.instance().drawTee(window2.x+window1.width, window2.y, 3, window1.color);
                                }
                            }
                        });
                        window1.drawWindow();
                    });
                    Curses.instance().refresh();
                    fpsTimer.reset();
                }
            }
        }).start();
    }

    public void handleKey() throws InterruptedException {
        new Thread(() -> {
            while (running) {
                int in = Curses.instance().getch();
                if (in == 3) {
                    try {
                        running = false;
                        Thread.sleep((long) (1000/(fps*0.75)));
                        kill();
                        System.exit(0);
                    } catch(Exception _) {
                    }
                    break;
                }
                for(Window window : windows.values()) {
                    window.handleKey((char)in);
                }
            }
        }).start();
    }

    public void kill() {
        Curses.instance().destroy();
    }


    public Window addWindow(int id, Window window) {
        if (this.windows.containsKey(id))
            throw new IllegalArgumentException("Window with ID: " + id + " already registered");
        this.windows.put(id, window);
        return window;
    }

    public Window getWindow(int id) {
        return this.windows.getOrDefault(id, null);
    }

    public static WindowManager instance() {
        if (instance == null) return new WindowManager(30);
        return instance;
    }

}
