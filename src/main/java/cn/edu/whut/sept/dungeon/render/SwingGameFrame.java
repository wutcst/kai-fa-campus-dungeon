package cn.edu.whut.sept.dungeon.render;

import cn.edu.whut.sept.dungeon.core.GameEngine;
import cn.edu.whut.sept.dungeon.core.InputCommand;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public final class SwingGameFrame extends JFrame {
    private static final int TICK_DELAY_MILLIS = 50;

    private final GameEngine engine;
    private final TilePanel tilePanel;
    private final StatusPanel statusPanel;
    private final LogPanel logPanel;
    private final Timer timer;

    public SwingGameFrame(GameEngine engine) {
        super("Campus Dungeon");
        GameFonts.installDefaults();
        this.engine = engine;
        this.tilePanel = new TilePanel();
        this.statusPanel = new StatusPanel();
        this.logPanel = new LogPanel();
        this.timer = new Timer(TICK_DELAY_MILLIS, event -> {
            engine.tick();
            refresh();
        });

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(12, 14, 18));
        add(createTileFrame(), BorderLayout.CENTER);
        add(statusPanel, BorderLayout.EAST);
        add(logPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new MovementKeyListener());
        refresh();
        pack();
        setLocationRelativeTo(null);
    }

    public void showWindow() {
        setVisible(true);
        timer.start();
        requestFocusInWindow();
    }

    private void refresh() {
        tilePanel.setState(engine.getState());
        statusPanel.setState(engine.getState());
        logPanel.setState(engine.getState());
    }

    private JPanel createTileFrame() {
        JPanel frame = new JPanel(new BorderLayout());
        frame.setBackground(new Color(12, 14, 18));
        frame.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(18, 18, 18, 18),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(93, 82, 64), 2),
                        BorderFactory.createLineBorder(new Color(28, 32, 39), 8))));
        frame.add(tilePanel, BorderLayout.CENTER);
        return frame;
    }

    private final class MovementKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            InputCommand command = InputCommand.fromKey(event.getKeyChar());
            engine.handleInput(command);
            refresh();
        }
    }
}
