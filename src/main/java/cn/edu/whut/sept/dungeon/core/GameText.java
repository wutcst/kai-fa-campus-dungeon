package cn.edu.whut.sept.dungeon.core;

import cn.edu.whut.sept.dungeon.entity.Inventory;
import cn.edu.whut.sept.dungeon.room.RoomStatus;
import cn.edu.whut.sept.dungeon.room.RoomType;

import java.util.ArrayList;
import java.util.List;

public final class GameText {
    private GameText() {
    }

    public static String ready() {
        return "系统就绪。";
    }

    public static String noInput() {
        return "没有输入。";
    }

    public static String noSavedGame() {
        return "没有找到存档。";
    }

    public static String incompatibleSave() {
        return "存档版本不兼容。";
    }

    public static String loadedSave() {
        return "已读取存档。";
    }

    public static String saveRequested() {
        return "已保存并准备退出。";
    }

    public static String unknownInput() {
        return "无法识别的输入。";
    }

    public static String unknownInput(char input) {
        return "无法识别的输入：" + input + "。";
    }

    public static String invalidTickCount() {
        return "tick 数量无效。";
    }

    public static String skillLocked() {
        return "技能还没有解锁。";
    }

    public static String newGame(long seed) {
        return "新游戏开始，种子：" + seed + "。";
    }

    public static String tick(long tick) {
        return "第 " + tick + " 个 tick。";
    }

    public static String gameOver() {
        return "游戏结束，生命值归零。";
    }

    public static String gameOverActionBlocked() {
        return "游戏已经结束，请重新开始挑战。";
    }

    public static String startNewGameFirst() {
        return "请先开始新游戏。";
    }

    public static String blockedByWall() {
        return "被墙壁挡住了。";
    }

    public static String moved(Direction direction) {
        return "向" + directionName(direction) + "移动。";
    }

    public static String combatRoomLockedExit() {
        return "战斗房已封锁，请先清空敌人。";
    }

    public static String combatRoomLocked() {
        return "战斗房封锁中，清空敌人后出口会重新开启。";
    }

    public static String bossRoomSealed() {
        return "Boss 房已封锁，请击败答辩委员会。";
    }

    public static String roomClearedRewardUnlocked() {
        return "房间已清理，奖励已解锁。";
    }

    public static String firedKeyboardPistol(Direction direction) {
        return "键盘手枪向" + directionName(direction) + "开火！";
    }

    public static String defenseHallGuarded() {
        return "答辩大厅仍被答辩委员会把守，请先击败它。";
    }

    public static String defenseCompleted(int steps) {
        return "答辩完成！你用 " + steps + " 步交出了一份漂亮的软件工程实训作品。";
    }

    public static String defenseHallLocked(String missingMaterials) {
        return "答辩大厅尚未开放，缺少材料：" + missingMaterials + "。";
    }

    public static String nothingHere() {
        return "这里暂时没有可以交互的东西。";
    }

    public static String descendedToDepth(int depth) {
        return "进入第 " + depth + " 层。";
    }

    public static String mavenCorrect() {
        return "回答正确，Maven 项目的核心配置文件就是 pom.xml。";
    }

    public static String mavenIncorrect() {
        return "回答错误。提示：Maven 会把项目配置放在名为 pom.xml 的文件里。";
    }

    public static String coffeeBoostActive() {
        return "灵感咖啡生效，下一次攻击获得 +3 攻击力。";
    }

    public static String inventory(Inventory inventory) {
        return "背包：" + inventorySummary(inventory) + "。";
    }

    public static String usedPotion(String potionId, int restoredHp) {
        return "使用了" + itemName(potionId) + "，恢复 " + restoredHp + " 点生命值。";
    }

    public static String playerTookDamage(int amount) {
        return "你受到了 " + amount + " 点伤害。";
    }

    public static String enemyAttack(String enemyType, int damage) {
        return enemyName(enemyType) + "命中你，造成 " + damage + " 点伤害";
    }

    public static String bossFinalQuestionDamage(int damage) {
        return "答辩委员会抛出终极追问，造成 " + damage + " 点伤害";
    }

    public static String damageTrap(int damage) {
        return "触发扣血陷阱，失去 " + damage + " 点生命值。";
    }

    public static String teleportTrap() {
        return "触发传送陷阱，已被送回入口。";
    }

    public static String weaknessTrap(int penalty) {
        return "触发虚弱陷阱，攻击力降低 " + penalty + " 点。";
    }

    public static String genericTrap() {
        return "触发了一个机关。";
    }

    public static String hitEnemy(String enemyType, int damage) {
        return "击中" + enemyName(enemyType) + "，造成 " + damage + " 点伤害！";
    }

    public static String defeatedEnemy(String enemyType, int expReward) {
        return "击败" + enemyName(enemyType) + "，获得 " + expReward + " 点经验。";
    }

    public static String defeatedBoss() {
        return "击败答辩委员会，最终答辩已经可以提交。";
    }

    public static String projectileHitWall() {
        return "子弹撞上墙壁并消散。";
    }

    public static String projectileHitEnemy(String enemyType, int damage) {
        return "子弹命中" + enemyName(enemyType) + "，造成 " + damage + " 点伤害。";
    }

    public static String projectileDefeatedEnemy(String enemyType, int expReward) {
        return "子弹击败" + enemyName(enemyType) + "，获得 " + expReward + " 点经验。";
    }

    public static String enemyProjectileHitPlayer(int damage) {
        return "敌方弹幕命中你，造成 " + damage + " 点伤害。";
    }

    public static String projectileExpired() {
        return "子弹射程耗尽。";
    }

    public static String pickedUp(String itemName) {
        return "拾取了" + itemName + "。";
    }

    public static String equippedWeapon(String itemName, int atk) {
        return "装备了" + itemName + "，攻击力提升为 " + atk + "。";
    }

    public static String equippedArmor(String itemName, int def) {
        return "装备了" + itemName + "，防御力提升为 " + def + "。";
    }

    public static String librarianNeedsCard() {
        return "图书馆老师：先把学生证带来，我才能把答辩论文借给你。";
    }

    public static String librarianGrantsReport() {
        return "图书馆老师：学生证核验通过，这份学报论文拿去镇场。";
    }

    public static String librarianAlreadyDone() {
        return "图书馆老师：论文已经在你的背包里了，别在馆里反复横跳。";
    }

    public static String assistantNeedsUsb() {
        return "助教：先找到 U盘，演示幻灯片还等着导出。";
    }

    public static String assistantMavenPuzzle() {
        return "助教：请提交你的 Maven 核心配置文件名！提示：使用 !answer(pom.xml) 格式进行回答。";
    }

    public static String assistantGrantsSlides() {
        return "助教：U盘读取成功，笔记本电脑就位，演示幻灯片已经导出。";
    }

    public static String teacherNeedsMaterials() {
        return "指导老师：论文、电脑和幻灯片都准备好后，我再给你答辩通过绿卡。";
    }

    public static String teacherGrantsPass() {
        return "指导老师：材料齐全，这张答辩通过绿卡拿好，别丢。";
    }

    public static String teacherAlreadyDone() {
        return "指导老师：绿卡已经给你了，去答辩大厅证明自己吧。";
    }

    public static String npcFallback(String npcName) {
        return npcName(npcName) + "：继续探索，把答辩准备扎实。";
    }

    public static String shooterFires(String enemyType) {
        return enemyName(enemyType) + "发射了一张审稿意见。";
    }

    public static String bossPhaseOne() {
        return "答辩委员会第一阶段抛出一个直接问题。";
    }

    public static String bossPhaseTwo() {
        return "答辩委员会第二阶段开始三连审稿。";
    }

    public static String bossPhaseThreeSummons() {
        return "答辩委员会第三阶段召唤终极追问。";
    }

    public static String bossPhaseThreeFires() {
        return "答辩委员会第三阶段发起终极追问。";
    }

    public static String itemName(String itemId) {
        if ("student-card".equals(itemId)) {
            return "学生证";
        }
        if ("small-potion".equals(itemId)) {
            return "初级恢复药水";
        }
        if ("big-potion".equals(itemId)) {
            return "高级恢复药水";
        }
        if ("usb".equals(itemId)) {
            return "U盘";
        }
        if ("wooden-keyboard".equals(itemId)) {
            return "木质键盘";
        }
        if ("steel-keyboard".equals(itemId)) {
            return "机械键盘";
        }
        if ("refactor-blade".equals(itemId)) {
            return "重构之刃";
        }
        if ("lab-coat".equals(itemId)) {
            return "实验服";
        }
        if ("review-robe".equals(itemId)) {
            return "审稿人长袍";
        }
        if ("defense-suit".equals(itemId)) {
            return "答辩战衣";
        }
        if ("coffee".equals(itemId)) {
            return "灵感咖啡";
        }
        if ("report".equals(itemId)) {
            return "学报论文";
        }
        if ("laptop".equals(itemId)) {
            return "笔记本电脑";
        }
        if ("slides".equals(itemId)) {
            return "演示幻灯片";
        }
        if ("pass".equals(itemId)) {
            return "答辩通过绿卡";
        }
        if ("none".equals(itemId)) {
            return "无";
        }
        if (itemId != null && itemId.startsWith("room-reward-")) {
            return "房间奖励";
        }
        return itemId == null ? "未知物品" : itemId;
    }

    public static String enemyName(String enemyType) {
        if ("Bug Slime".equals(enemyType)) {
            return "Bug 史莱姆";
        }
        if ("Deadline Runner".equals(enemyType)) {
            return "DDL 冲刺者";
        }
        if ("Review Shooter".equals(enemyType)) {
            return "审稿射手";
        }
        if ("Defense Committee".equals(enemyType)) {
            return "答辩委员会";
        }
        return enemyType == null ? "未知敌人" : enemyType;
    }

    public static String npcName(String npcName) {
        if ("Librarian".equals(npcName) || "librarian".equals(npcName)) {
            return "图书馆老师";
        }
        if ("Assistant".equals(npcName) || "assistant".equals(npcName)) {
            return "助教";
        }
        if ("Teacher".equals(npcName) || "teacher".equals(npcName)) {
            return "指导老师";
        }
        return npcName == null ? "路过的同学" : npcName;
    }

    public static String directionName(Direction direction) {
        if (direction == null) {
            return "前方";
        }
        switch (direction) {
            case NORTH:
                return "北";
            case SOUTH:
                return "南";
            case WEST:
                return "西";
            case EAST:
                return "东";
            default:
                return direction.name();
        }
    }

    public static String inventorySummary(Inventory inventory) {
        if (inventory == null || inventory.getItemIds().isEmpty()) {
            return "空";
        }
        List<String> names = new ArrayList<String>();
        for (String itemId : inventory.getItemIds()) {
            names.add(itemName(itemId));
        }
        return join(names);
    }

    public static String roomTypeName(RoomType type) {
        if (type == null) {
            return "走廊";
        }
        switch (type) {
            case ENTRANCE:
                return "入口";
            case COMBAT:
                return "战斗房";
            case BOSS:
                return "Boss 房";
            case REWARD:
                return "奖励房";
            case NPC:
                return "NPC 房";
            case MATERIAL:
                return "材料房";
            default:
                return type.name();
        }
    }

    public static String roomStatusName(RoomStatus status) {
        if (status == null) {
            return "无";
        }
        switch (status) {
            case LOCKED:
                return "未触发";
            case ACTIVE:
                return "封锁中";
            case CLEARED:
                return "已清理";
            case COMPLETED:
                return "已完成";
            default:
                return status.name();
        }
    }

    public static String statusName(GameStatus status) {
        if (status == null) {
            return "未知";
        }
        switch (status) {
            case PLAYING:
                return "探索中";
            case COMPLETED:
                return "已通关";
            case GAME_OVER:
                return "挑战失败";
            default:
                return status.name();
        }
    }

    private static String join(List<String> values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) {
                builder.append("、");
            }
            builder.append(values.get(i));
        }
        return builder.toString();
    }
}
