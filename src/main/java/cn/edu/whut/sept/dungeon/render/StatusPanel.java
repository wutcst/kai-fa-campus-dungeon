package cn.edu.whut.sept.dungeon.render;

import cn.edu.whut.sept.dungeon.core.GameState;
import cn.edu.whut.sept.dungeon.core.GameText;
import cn.edu.whut.sept.dungeon.entity.Inventory;
import cn.edu.whut.sept.dungeon.room.RoomState;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public final class StatusPanel extends JPanel {
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_PADDING = 12;
    private static final int CONTENT_WIDTH = PANEL_WIDTH - PANEL_PADDING * 2;
    private static final String[] DEFENSE_MATERIALS = {"report", "laptop", "slides", "pass"};

    private final JLabel titleLabel;
    private final JLabel depthLabel;
    private final JLabel roomLabel;
    private final JProgressBar hpBar;
    private final JLabel levelLabel;
    private final JLabel expLabel;
    private final JLabel atkLabel;
    private final JLabel defLabel;
    private final JLabel weaponLabel;
    private final JLabel armorLabel;
    private final JPanel inventoryListPanel;
    private final JLabel[] materialLabels;

    public StatusPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, TileRenderer.VIEWPORT_HEIGHT * TileRenderer.TILE_SIZE));
        setMaximumSize(new Dimension(PANEL_WIDTH, Integer.MAX_VALUE));
        setBackground(new Color(17, 20, 25));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(62, 70, 82)),
                BorderFactory.createEmptyBorder(PANEL_PADDING, PANEL_PADDING, PANEL_PADDING, PANEL_PADDING)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setFocusable(false);

        titleLabel = header("Campus Dungeon");
        depthLabel = valueLabel();
        roomLabel = valueLabel();
        hpBar = new JProgressBar();
        hpBar.setStringPainted(true);
        hpBar.setForeground(new Color(194, 54, 67));
        hpBar.setBackground(new Color(58, 22, 28));
        hpBar.setBorder(BorderFactory.createLineBorder(new Color(230, 219, 188)));
        hpBar.setFont(GameFonts.bold(12));
        levelLabel = valueLabel();
        expLabel = valueLabel();
        atkLabel = valueLabel();
        defLabel = valueLabel();
        weaponLabel = valueLabel();
        armorLabel = valueLabel();
        inventoryListPanel = new JPanel();
        inventoryListPanel.setLayout(new BoxLayout(inventoryListPanel, BoxLayout.Y_AXIS));
        inventoryListPanel.setOpaque(false);
        inventoryListPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        inventoryListPanel.setPreferredSize(new Dimension(CONTENT_WIDTH, 88));
        inventoryListPanel.setMaximumSize(new Dimension(CONTENT_WIDTH, 140));
        materialLabels = new JLabel[DEFENSE_MATERIALS.length];

        add(titleLabel);
        addGap(10);
        add(depthLabel);
        add(roomLabel);
        addGap(10);
        add(hpBar);
        addGap(10);
        add(sectionLabel("角色"));
        add(levelLabel);
        add(expLabel);
        add(atkLabel);
        add(defLabel);
        addGap(8);
        add(sectionLabel("装备"));
        add(weaponLabel);
        add(armorLabel);
        addGap(8);
        add(sectionLabel("答辩材料"));
        for (int i = 0; i < DEFENSE_MATERIALS.length; i++) {
            materialLabels[i] = valueLabel();
            add(materialLabels[i]);
        }
        addGap(8);
        add(sectionLabel("背包"));
        add(inventoryListPanel);
        add(Box.createVerticalGlue());
        setState(null);
    }

    public void setState(GameState state) {
        if (state == null || !state.isStarted()) {
            titleLabel.setText("Campus Dungeon");
            depthLabel.setText("等待新游戏");
            roomLabel.setText("状态：未开始");
            hpBar.setMaximum(1);
            hpBar.setValue(0);
            hpBar.setString("HP -/-");
            levelLabel.setText("等级：-");
            expLabel.setText("经验：-");
            atkLabel.setText("攻击：-");
            defLabel.setText("防御：-");
            weaponLabel.setText("武器：键盘手枪");
            armorLabel.setText("防具：无");
            setInventoryList(Inventory.empty());
            for (int i = 0; i < materialLabels.length; i++) {
                setMaterialText(materialLabels[i], false, GameText.itemName(DEFENSE_MATERIALS[i]));
            }
            return;
        }

        titleLabel.setText(GameText.statusName(state.getStatus()));
        depthLabel.setText("楼层：" + state.getDepth() + "    Seed：" + state.getSeed());
        roomLabel.setText(roomText(state));
        int maxHp = Math.max(1, state.getPlayer().getMaxHp());
        hpBar.setMaximum(maxHp);
        hpBar.setValue(Math.max(0, state.getPlayer().getHp()));
        hpBar.setString("HP " + state.getPlayer().getHp() + "/" + maxHp);
        levelLabel.setText("等级：" + state.getPlayer().getLevel());
        expLabel.setText("经验：" + state.getPlayer().getExp());
        atkLabel.setText("攻击：" + state.getPlayer().effectiveAtk());
        atkLabel.setForeground(state.getPlayer().getCoffeeBoost() > 0 ? new Color(247, 210, 95) : new Color(220, 226, 218));
        defLabel.setText("防御：" + state.getPlayer().getDef());
        weaponLabel.setText("武器：" + GameText.itemName(state.getPlayer().getWeapon()));
        armorLabel.setText("防具：" + GameText.itemName(state.getPlayer().getArmor()));
        setInventoryList(state.getInventory());
        for (int i = 0; i < materialLabels.length; i++) {
            String material = DEFENSE_MATERIALS[i];
            setMaterialText(materialLabels[i], state.getInventory().contains(material), GameText.itemName(material));
        }
    }

    private JLabel header(String text) {
        JLabel label = new JLabel(text, SwingConstants.LEFT);
        label.setFont(GameFonts.bold(20));
        label.setForeground(new Color(242, 235, 210));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel sectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(GameFonts.bold(14));
        label.setForeground(new Color(130, 207, 165));
        label.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel valueLabel() {
        JLabel label = new JLabel();
        label.setFont(GameFonts.plain(14));
        label.setForeground(new Color(220, 226, 218));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setMaximumSize(new Dimension(CONTENT_WIDTH, 24));
        return label;
    }

    private void addGap(int height) {
        add(Box.createRigidArea(new Dimension(1, height)));
    }

    private String roomText(GameState state) {
        RoomState room = state.currentRoomState();
        if (room == null) {
            return "位置：走廊";
        }
        return "房间：" + GameText.roomTypeName(room.getType()) + " #" + room.getId()
                + " / " + GameText.roomStatusName(room.getStatus());
    }

    private void setMaterialText(JLabel label, boolean collected, String name) {
        label.setText((collected ? "[✔] " : "[✘] ") + name);
        label.setForeground(collected ? new Color(106, 213, 130) : new Color(94, 101, 110));
    }

    private void setInventoryList(Inventory inventory) {
        inventoryListPanel.removeAll();
        List<String> itemIds = inventory.getItemIds();
        if (itemIds.isEmpty()) {
            inventoryListPanel.add(inventoryLine("空"));
        } else {
            for (String itemId : itemIds) {
                inventoryListPanel.add(inventoryLine(GameText.itemName(itemId)));
            }
        }
        inventoryListPanel.revalidate();
        inventoryListPanel.repaint();
    }

    private JLabel inventoryLine(String itemName) {
        JLabel label = valueLabel();
        String text = "• " + itemName;
        label.setText("<html><body style='width:" + (CONTENT_WIDTH - 6) + "px'>" + escapeHtml(text) + "</body></html>");
        label.setToolTipText(text);
        label.setPreferredSize(new Dimension(CONTENT_WIDTH, 22));
        label.setMaximumSize(new Dimension(CONTENT_WIDTH, 44));
        return label;
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    int getInventoryLineCountForTest() {
        return inventoryListPanel.getComponentCount();
    }

    int getInventoryListWidthForTest() {
        return inventoryListPanel.getPreferredSize().width;
    }
}
