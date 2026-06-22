package cn.edu.whut.sept.dungeon.render;

import cn.edu.whut.sept.dungeon.core.GameState;
import cn.edu.whut.sept.dungeon.core.GameText;
import cn.edu.whut.sept.dungeon.room.RoomState;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public final class StatusPanel extends JPanel {
    private static final int PANEL_WIDTH = 260;
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
    private final JLabel inventoryLabel;
    private final JLabel[] materialLabels;

    public StatusPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, TileRenderer.VIEWPORT_HEIGHT * TileRenderer.TILE_SIZE));
        setBackground(new Color(17, 20, 25));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(62, 70, 82)),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)));
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
        inventoryLabel = valueLabel();
        materialLabels = new JLabel[DEFENSE_MATERIALS.length];

        add(titleLabel);
        addGap(14);
        add(depthLabel);
        add(roomLabel);
        addGap(14);
        add(hpBar);
        addGap(14);
        add(sectionLabel("角色"));
        add(levelLabel);
        add(expLabel);
        add(atkLabel);
        add(defLabel);
        addGap(12);
        add(sectionLabel("装备"));
        add(weaponLabel);
        add(armorLabel);
        addGap(12);
        add(sectionLabel("答辩材料"));
        for (int i = 0; i < DEFENSE_MATERIALS.length; i++) {
            materialLabels[i] = valueLabel();
            add(materialLabels[i]);
        }
        addGap(12);
        add(sectionLabel("背包"));
        add(inventoryLabel);
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
            inventoryLabel.setText("空");
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
        inventoryLabel.setText(wrap(GameText.inventorySummary(state.getInventory()), 16));
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

    private String wrap(String text, int maxChars) {
        if (text == null || text.length() <= maxChars) {
            return text;
        }
        return "<html>" + text.replace("、", "、<br>") + "</html>";
    }
}
