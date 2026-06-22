package cn.edu.whut.sept.dungeon.render;

import cn.edu.whut.sept.dungeon.core.GameState;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public final class LogPanel extends JPanel {
    private static final int SIDEBAR_WIDTH = 200;
    private static final int LOG_HEIGHT = 110;
    private static final int MAX_LINES = 20;
    private static final Pattern TICK_MESSAGE_PATTERN = Pattern.compile("^(Tick \\d+\\.|第 \\d+ 个 tick。)$");

    private final JTextArea textArea;
    private final Deque<String> lines;
    private long lastTick;
    private String lastMessage;

    public LogPanel() {
        this.lines = new ArrayDeque<String>();
        this.lastTick = Long.MIN_VALUE;
        this.lastMessage = null;
        Dimension logSize = new Dimension(TileRenderer.VIEWPORT_WIDTH * TileRenderer.TILE_SIZE + SIDEBAR_WIDTH,
                LOG_HEIGHT);
        setPreferredSize(logSize);
        setMinimumSize(logSize);
        setMaximumSize(logSize);
        setLayout(new BorderLayout());
        setBackground(new Color(13, 15, 18));
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(62, 70, 82)));
        setFocusable(false);

        textArea = new JTextArea(4, 30);
        textArea.setRows(4);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(8, 12, 10));
        textArea.setForeground(new Color(203, 239, 207));
        textArea.setCaretColor(new Color(203, 239, 207));
        textArea.setFont(GameFonts.plain(14));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(logSize);
        scrollPane.setMaximumSize(logSize);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setState(GameState state) {
        if (state == null) {
            return;
        }
        String message = state.getMessage();
        if (!shouldAppend(message)) {
            return;
        }
        if (state.getTick() == lastTick && message.equals(lastMessage)) {
            return;
        }
        lastTick = state.getTick();
        lastMessage = message;
        String line = "[" + state.getTick() + "] " + message;
        lines.addLast(line);
        while (lines.size() > MAX_LINES) {
            lines.removeFirst();
        }
        redraw();
    }

    int getLineCountForTest() {
        return lines.size();
    }

    private boolean shouldAppend(String message) {
        if (message == null) {
            return false;
        }
        String trimmed = message.trim();
        return trimmed.length() > 0 && !TICK_MESSAGE_PATTERN.matcher(trimmed).matches();
    }

    private void redraw() {
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            if (builder.length() > 0) {
                builder.append('\n');
            }
            builder.append(line);
        }
        textArea.setText(builder.toString());
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
