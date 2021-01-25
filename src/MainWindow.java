import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * MainWindow Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class MainWindow {

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    static Game game;
    static ArrayList<Spell> spells;
    static ArrayList<Treasure> treasures;
    static ArrayList<Enemy> enemies;
    static FileInput fileInput;
    static Room room;
    int win_amount = 400;
    String won_txt = "you collected " + win_amount + " gold. you win";
    String lose_txt = "you died";


    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    JFrame frame;
    JPanel main_panel, fight_panel, end_panel;
    JPanel direction, action, status_bar, bars, spell_btn_panel;
    JLabel fight_enemy_image,fight_status, end_txt;
    JButton north,east,west,south, attack, search, stats, spells_list, use_hp_potion,use_sp_potion, exit_fight;
    JProgressBar hp_bar, sp_bar, enemy_hp_bar;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    Color background_color = new Color(0, 0, 0);
    Color buttons_color = new Color(250, 153, 83);
    Color hp_color = new Color(173, 6, 6);
    Color sp_color = new Color(64, 201, 13);
    Color fight_text_color = new Color(0, 234, 255);
    Color end_text_color = new Color(217, 0, 255);
    Color spell_color = new Color(250, 153, 83);
    Color hp_potion_color = new Color(234, 56, 56);
    Color sp_potion_color = new Color(90, 236, 49);
    Color lp_potion_color = new Color(150, 48, 252);

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * Initialize Main Window object
     */
    public MainWindow() {}

    /**
     * initializes the window
     * @throws IOException io
     */
    public void showWindow() throws IOException {
        init();
    }

    /**
     * Initialize the frame
     * @throws IOException io
     */
    private void init() throws IOException {
        frame = new JFrame();
        main_panel = new JPanel();
        frame.setBounds(0,0,1700,1050);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main_panel.setLayout(new BorderLayout());

        initializeGame();

        frame.setContentPane(main_panel);
        frame.setVisible(true);
    }

    /**
     * Initialize the Game
     * @throws IOException io
     */
    private void initializeGame() throws IOException {
        generateGame();
        generateBackground();
        generateDirectionButtons();
        generateActionButtons();
        generateHealthStats();
        setColors();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * generates the game object with spells, treasure, enemies
     * @throws IOException io
     */
    private void generateGame() throws IOException {
        fileInput = new FileInput();
        fileInput.initSpells();
        fileInput.initTreasure();
        fileInput.initEnemy();

        spells = fileInput.getSpells();
        treasures = fileInput.getTreasure();
        enemies = fileInput.getEnemies();

        game = new Game(spells, treasures, enemies);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * generate the room in the background
     * @throws IOException io
     */
    private void generateBackground() throws IOException {

        room = game.getRooms()[game.getXPos()][game.getYPos()];

        if (room.getEnemy() != null && room.getTreasure() != null) {
            room = new Room(room.getEnemy(), room.getTreasure());
        } else if (room.getEnemy() != null) {
            room = new Room(room.getEnemy());
        } else if (room.getTreasure() != null){
            room = new Room(room.getTreasure());
        } else {
            room = new Room();
        }
        main_panel.add(room,BorderLayout.CENTER);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * generate the buttons that control the position
     * of the player, can move north,south,east,west
     * within the set size of the generated level
     */
    private void generateDirectionButtons() {
        north = new JButton("          NORTH          ");
        west = new JButton("          WEST          ");
        east = new JButton("          EAST          ");
        south = new JButton("          SOUTH          ");

        north.setPreferredSize(new Dimension(160,80));
        west.setPreferredSize(new Dimension(160,80));
        east.setPreferredSize(new Dimension(160,80));
        south.setPreferredSize(new Dimension(160,80));

        north.addActionListener(this::directionPerformed);
        west.addActionListener(this::directionPerformed);
        east.addActionListener(this::directionPerformed);
        south.addActionListener(this::directionPerformed);

        direction = new JPanel();
        direction.setLayout(new GridBagLayout());

        add_direction(north,0,0);
        add_direction(west,0,1);
        add_direction(east,0,2);
        add_direction(south,0,3);

        main_panel.add(direction, BorderLayout.EAST);
    }

    /**
     * grid bag layout function for direction buttons
     * @param c component
     * @param x x position
     * @param y y position
     */
    private void add_direction(Component c, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(20,0,0,30);
        direction.add(c,gbc);
    }

    /**
     * action listener for direction buttons
     * @param e ActionEvent
     */
    private void directionPerformed(ActionEvent e) {
        int x = game.getXPos();
        int y = game.getYPos();
        room = game.getRooms()[x][y];

        if (e.getSource() == north) {

            try {
                setRoom(x,y-1);
            } catch (IOException ioException) { ioException.printStackTrace(); }

        } else if (e.getSource() == west) {

            try {
                setRoom(x-1,y);
            } catch (IOException ioException) { ioException.printStackTrace(); }

        } else if (e.getSource() == east) {

            try {
                setRoom(x+1,y);
            } catch (IOException ioException) { ioException.printStackTrace(); }

        } else if (e.getSource() == south) {

            try {
                setRoom(x,y+1);
            } catch (IOException ioException) { ioException.printStackTrace(); }

        }

        north.setEnabled(game.getYPos() != 0);
        west.setEnabled(game.getXPos() != 0);
        east.setEnabled(game.getXPos() != game.getSIZE()-1);
        south.setEnabled(game.getYPos() != game.getSIZE()-1);

        attack.setEnabled(room.getEnemy() != null);
        search.setEnabled(room.getTreasure() != null);

    }

    /**
     * set the room the player is currently in
     * @param x x position
     * @param y y position
     * @throws IOException io
     */
    private void setRoom(int x, int y) throws IOException {

        game.setXPos(x);
        game.setYPos(y);
        room = game.getRooms()[x][y];

        room.setBackground(background_color);

        room.repaint();

        main_panel.add(room,BorderLayout.CENTER);
        main_panel.revalidate();

    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * generate the action buttons so the player
     * can choose to fight an enemy or collect
     * treasure from a room
     */
    private void generateActionButtons () {
        attack = new JButton("        ATTACK ENEMY        ");
        search = new JButton("        GRAB TREASURE        ");

        attack.setPreferredSize(new Dimension(180,80));
        search.setPreferredSize(new Dimension(180,80));

        attack.addActionListener(this::actionPerformed);
        search.addActionListener(this::actionPerformed);

        attack.setEnabled(room.getEnemy() != null);
        search.setEnabled(room.getTreasure() != null);

        action = new JPanel();
        action.setLayout(new GridBagLayout());

        add_action(attack,0,0);
        add_action(search,0,1);

        main_panel.add(action, BorderLayout.WEST);
    }

    /**
     * grid bag layout function for action buttons
     * @param c component
     * @param x x position
     * @param y y position
     */
    private void add_action(Component c, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(20,30,0,0);
        action.add(c,gbc);
    }

    /**
     * action listener for action buttons
     * @param e ActionEvent
     */
    private void actionPerformed(ActionEvent e) {
        if (e.getSource() == attack) {
            try {
                attackEnemy();
            } catch (IOException ioException) { ioException.printStackTrace(); }
        }  else if (e.getSource() == search) {
            try {
                searchRoom();
            } catch (IOException ioException) { ioException.printStackTrace(); }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * attack the enemy function
     * @throws IOException io
     */
    private void attackEnemy() throws IOException {
        fight_panel = new JPanel(new GridBagLayout());
        fight_panel.setBackground(background_color);

        north.setEnabled(false);
        west.setEnabled(false);
        east.setEnabled(false);
        south.setEnabled(false);
        attack.setEnabled(false);
        search.setEnabled(false);

        main_panel.remove(room);

        generateFightScene();

        main_panel.add(fight_panel,BorderLayout.CENTER);
        main_panel.revalidate();
    }

    /**
     * generate the fight panel over main panel
     * enemy image, enemy health bar,
     * spell buttons, potion buttons
     * @throws IOException io
     */
    private void generateFightScene() throws IOException {
        // enemy image
        Image enemy_img = ImageIO.read(this.getClass().getResourceAsStream(room.getEnemy().getURL()));
        enemy_img = enemy_img.getScaledInstance(300,300,Image.SCALE_SMOOTH);
        fight_enemy_image = new JLabel(new ImageIcon(enemy_img));
        add_fight(fight_enemy_image,0,0);

        // enemy hp bar
        enemy_hp_bar = new JProgressBar(0,room.getEnemy().getMAXHealth());
        enemy_hp_bar.setValue(room.getEnemy().getHealth());
        enemy_hp_bar.setString("HP");
        enemy_hp_bar.setStringPainted(true);
        add_fight(enemy_hp_bar,0,1);

        // spell buttons
        spell_btn_panel = new JPanel(new GridLayout(2,2,20,20));

        for (Spell s : game.getPlayer().getSpells()) {

            JButton spell_button = new JButton(s.getName());
            spell_button.setBackground(spell_color);
            String stringCommand = s.getName();
            spell_button.setActionCommand(stringCommand);

            spell_button.addActionListener(e-> {
                JButton b = (JButton) e.getSource();

                String command = b.getActionCommand();
                String str;
                for (Spell s_: game.getPlayer().getSpells()) {
                    str = s_.getName();
                    if (command.equals(str)) {
                        fight(s_);
                    }
                }
            });
            spell_btn_panel.add(spell_button);
        }
        add_fight(spell_btn_panel,0,2);

        // potion buttons
        use_hp_potion = new JButton("Use Health Potion");
        use_sp_potion = new JButton("Use Spell Potion");

        use_hp_potion.setEnabled(game.hasTypeHP());
        use_sp_potion.setEnabled(game.hasTypeSP());

        use_hp_potion.addActionListener(this::potionPerformed);
        use_sp_potion.addActionListener(this::potionPerformed);

        add_fight(use_hp_potion,1,1);
        add_fight(use_sp_potion,1,2);

        // exit fight button
        exit_fight = new JButton("Exit Fight");
        exit_fight.setEnabled(false);
        add_fight(exit_fight,0,4);

        fight_status = new JLabel();
        fight_status.setText("Fighting " + room.getEnemy().getName() + "!");
        fight_status.setVerticalAlignment(JLabel.CENTER);

        fight_status.setPreferredSize(new Dimension(200,200));
        add_fight(fight_status,1,0);

        setFightPanelColor();
    }

    /**
     * grid bag layout function for fight panel
     * @param c component
     * @param x x position
     * @param y y position
     */
    private void add_fight(Component c, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(30,5,0,5);
        fight_panel.add(c,gbc);
    }

    /**
     * takes the spell used by player and does
     * damage to the enemy, enemy does damage
     * back to the player
     * @param spell spell used
     */
    private void fight(Spell spell) {

        Random rand = new Random();
        int dmg = rand.nextInt(room.getEnemy().getMax_damage() - room.getEnemy().getMin_damage()) + room.getEnemy().getMin_damage();

        int current_health = game.getPlayer().getHealth();
        int new_health = current_health - dmg;

        int current_sp = game.getPlayer().getSpell();
        int new_sp = current_sp - spell.getSp_cost();

        int current_enemy_health = room.getEnemy().getHealth();
        int new_enemy_health = current_enemy_health - spell.getDamage();

        if (new_health <= 0) {

            end_panel = new JPanel(new GridBagLayout());
            end_txt = new JLabel(lose_txt);
            end_txt.setForeground(end_text_color);
            end_panel.add(end_txt);
            end_panel.setBackground(background_color);
            frame.setContentPane(end_panel);
            frame.revalidate();

        } else {
            if (new_enemy_health <= 0) {

                int gold_drop = rand.nextInt(100-50)+50;

                game.addGold(gold_drop);
                game.addEnemyKilled();

                fight_status.setText("<html>You Won!<br/>" + room.getEnemy().getName() + " dropped " + gold_drop + " gold!");

                enemy_hp_bar.setValue(0);

                spell_btn_panel.removeAll();

                exit_fight.setEnabled(true);
                exit_fight.addActionListener(e -> {
                    try {
                        exitFight(e);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

            }
            else if (new_sp <= 0) {

                game.getPlayer().setHealth(new_health);
                hp_bar.setValue(game.getPlayer().getHealth());
                fight_status.setText("<html>Not enough SP!<br/> " + room.getEnemy().getName() + " did " + dmg + " damage!</html>");

            } else {

                room.getEnemy().setHealth(String.valueOf(new_enemy_health));
                enemy_hp_bar.setValue(room.getEnemy().getHealth());

                fight_status.setText("<html>You used " + spell.getName() + "<br/>" +
                        "You did " + spell.getDamage() + " damage. <br/>" +
                        room.getEnemy().getName() + " did " + dmg + " damage!</html>");
            }
        }

        game.getPlayer().setHealth(new_health);
        hp_bar.setValue(game.getPlayer().getHealth());

        game.getPlayer().setSpell(new_sp);
        sp_bar.setValue(game.getPlayer().getSpell());

    }

    /**
     * action event listener for potion buttons
     * @param e ActionEvent
     */
    private void potionPerformed (ActionEvent e) {
        if (e.getSource() == use_hp_potion) {
            for (int i = 0; i < game.getCurrent_hp_potions().size(); i ++) {
                if (game.getCurrent_hp_potions().get(i).getType().equals("Health Potion")) {

                    int health_new = game.getPlayer().getHealth() + game.getCurrent_hp_potions().get(i).getPoints();

                    game.getPlayer().setHealth(Math.min(health_new, game.getPlayer().getTOTAL_HEALTH()));

                    game.removeHPPotion(game.getCurrent_hp_potions().get(i));
                    use_hp_potion.setEnabled(game.hasTypeHP());
                    hp_bar.setValue(game.getPlayer().getHealth());

                    fight_panel.revalidate();

                    break;
                }
            }

        } else if (e.getSource() == use_sp_potion) {

            for (int i = 0; i < game.getCurrent_sp_potions().size(); i++) {
                if (game.getCurrent_sp_potions().get(i).getType().equals("Spell Potion")) {

                    int spell_new = game.getPlayer().getSpell() + game.getCurrent_sp_potions().get(i).getPoints();

                    game.getPlayer().setSpell(Math.min(spell_new, game.getPlayer().getTOTAL_SPELL()));

                    game.removeSPPotion(game.getCurrent_sp_potions().get(i));
                    use_sp_potion.setEnabled(game.hasTypeSP());
                    sp_bar.setValue(game.getPlayer().getSpell());

                    fight_panel.revalidate();

                    break;
                }
            }
        }
    }

    /**
     * action event listener for exit fight button
     * removes fight panel and replaces with main panel
     * @param e Action Event
     * @throws IOException io
     */
    private void exitFight(ActionEvent e) throws IOException {
        if (e.getSource() == exit_fight) {

            if (game.getGold() > win_amount) {
                end_panel = new JPanel(new GridBagLayout());
                end_txt = new JLabel(won_txt);
                end_txt.setForeground(end_text_color);
                end_panel.add(end_txt);
                end_panel.setBackground(background_color);
                frame.setContentPane(end_panel);
                frame.revalidate();
            }

            main_panel.remove(fight_panel);
            main_panel.remove(room);

            game.changeRoomRemoveEnemy(game.getXPos(), game.getYPos());
            room = game.getRooms()[game.getXPos()][game.getYPos()];

            room.setBackground(background_color);
            room.repaint();

            main_panel.add(room,BorderLayout.CENTER);
            main_panel.revalidate();

            north.setEnabled(game.getYPos() != 0);
            west.setEnabled(game.getXPos() != 0);
            east.setEnabled(game.getXPos() != game.getSIZE()-1);
            south.setEnabled(game.getYPos() != game.getSIZE()-1);
            attack.setEnabled(room.getEnemy() != null);
            search.setEnabled(room.getTreasure() != null);
        }
    }

    /**
     * set colors of components in fight panel
     */
    private void setFightPanelColor() {
        spell_btn_panel.setBackground(background_color);
        enemy_hp_bar.setForeground(hp_color);
        fight_status.setForeground(fight_text_color);
        use_hp_potion.setBackground(hp_potion_color);
        use_sp_potion.setBackground(sp_potion_color);
        exit_fight.setBackground(buttons_color);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * search the room for treasure
     * generates pop up for treasure and
     * adds it to player inventory
     * @throws IOException io
     */
    private void searchRoom() throws IOException {
        JDialog dialog = new JDialog();

        Treasure tr = game.getRooms()[game.getXPos()][game.getYPos()].getTreasure();

        dialog.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(dialog, tr.toString(), "Treasure Found", JOptionPane.PLAIN_MESSAGE);

        if (tr instanceof Chest) {
            game.addGold(((Chest)tr).getGold());
        } else if (tr instanceof Potion){
            if (tr.getType().equals("Health Potion")) {
                game.addHPPotion((Potion)tr);
            } else {
                game.addSPPotion((Potion)tr);
            }
        }

        if (game.getGold() > win_amount) {
            end_panel = new JPanel(new GridBagLayout());
            end_txt = new JLabel(won_txt);
            end_txt.setForeground(end_text_color);
            end_panel.add(end_txt);
            end_panel.setBackground(background_color);
            frame.setContentPane(end_panel);
            frame.revalidate();
        } else {
            game.changeRoomRemoveTreasure(game.getXPos(), game.getYPos());
            room = game.getRooms()[game.getXPos()][game.getYPos()];

            room.setBackground(background_color);
            room.repaint();

            main_panel.add(room,BorderLayout.CENTER);
            main_panel.revalidate();

            search.setEnabled(room.getTreasure() != null);
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * generate player and game information
     * stats button for current game status
     * spell button to show player's spells
     * hp and sp bars
     */
    private void generateHealthStats() {
        status_bar = new JPanel();
        status_bar.setLayout(new GridBagLayout());

        stats = new JButton("        STATS        ");
        spells_list = new JButton("        SPELLS        ");

        stats.addActionListener(this::statsPerformed);
        spells_list.addActionListener(this::statsPerformed);

        stats.setPreferredSize(new Dimension(180,80));
        spells_list.setPreferredSize(new Dimension(180,80));

        hp_bar = new JProgressBar(0,game.getPlayer().getTOTAL_HEALTH());
        hp_bar.setValue(game.getPlayer().getHealth());
        hp_bar.setString("HP");
        hp_bar.setStringPainted(true);

        sp_bar = new JProgressBar(0,game.getPlayer().getTOTAL_SPELL());
        sp_bar.setValue(game.getPlayer().getSpell());
        sp_bar.setString("SP");
        sp_bar.setStringPainted(true);

        bars = new JPanel(new GridLayout(2,1,10,20));
        bars.add(hp_bar);
        bars.add(sp_bar);

        add_stat(stats, 0,0);
        add_stat(spells_list,1,0);
        add_stat(bars,5,0);

        main_panel.add(status_bar, BorderLayout.NORTH);
    }

    /**
     * grid bag layout function for status panel
     * @param c component
     * @param x x position
     * @param y y position
     */
    private void add_stat(Component c, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(30,5,0,5);
        status_bar.add(c,gbc);
    }

    /**
     * action event listener for stat buttons
     * generates pop up windows with information
     * @param e ActionEvent
     */
    private void statsPerformed(ActionEvent e) {
        JDialog dialog = new JDialog();
        if (e.getSource() == stats) {
            dialog.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(dialog, game.toString(), "Current Status", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == spells_list) {
            dialog.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(dialog, game.getPlayerSpells(), "Spells", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * set game colors
     */
    private void setColors() {
        room.setBackground(background_color);
        direction.setBackground(background_color);
        action.setBackground(background_color);
        bars.setBackground(background_color);
        status_bar.setBackground(background_color);
        north.setBackground(buttons_color);
        east.setBackground(buttons_color);
        west.setBackground(buttons_color);
        south.setBackground(buttons_color);
        attack.setBackground(buttons_color);
        search.setBackground(buttons_color);
        stats.setBackground(buttons_color);
        spells_list.setBackground(buttons_color);
        hp_bar.setForeground(hp_color);
        sp_bar.setForeground(sp_color);
    }
}
