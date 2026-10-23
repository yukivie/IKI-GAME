import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {

    private ArrayList<GameCharacter> characters = new ArrayList<>();

    private JComboBox<String> characterList;
    private JTextArea battleLog;
    private JLabel characterDisplay;
    private JLabel typeDisplay;

    private final Color background = new Color(22, 25, 45);
    private final Color panelColor = new Color(34, 39, 65);
    private final Color purple = new Color(155, 110, 255);
    private final Color blue = new Color(85, 175, 255);
    private final Color white = new Color(240, 240, 255);

    public Main() {

    	setTitle("IKI");
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(background);
        setLayout(new BorderLayout(15, 15));

        characters.add(new Warrior("Alaric"));
        characters.add(new Mage("Trix"));

        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBackground(background);
        header.setBorder(
            BorderFactory.createEmptyBorder(20, 10, 10, 10)
        );

        JLabel title = new JLabel(
            "⚔ IKI ⚔",
            SwingConstants.CENTER
        );

        title.setFont(new Font("Serif", Font.BOLD, 32));
        title.setForeground(purple);

        JLabel subtitle = new JLabel(
            "Choose your hero. Begin your adventure.",
            SwingConstants.CENTER
        );

        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(white);

        header.add(title);
        header.add(subtitle);

        add(header, BorderLayout.NORTH);

        JPanel center = new JPanel();

        center.setLayout(
            new BoxLayout(center, BoxLayout.Y_AXIS)
        );

        center.setBackground(background);

        center.setBorder(
            BorderFactory.createEmptyBorder(0, 25, 15, 25)
        );

        JPanel selectionPanel = new JPanel(
            new GridLayout(2, 1, 5, 5)
        );

        selectionPanel.setBackground(panelColor);

        selectionPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );

        JLabel selectLabel = new JLabel("SELECT YOUR HERO");

        selectLabel.setForeground(blue);
        selectLabel.setFont(
            new Font("SansSerif", Font.BOLD, 15)
        );

        characterList = new JComboBox<>();

        characterList.setFont(
            new Font("SansSerif", Font.PLAIN, 14)
        );

        updateCharacterList();

        selectionPanel.add(selectLabel);
        selectionPanel.add(characterList);

        JPanel heroPanel = new JPanel(
            new GridLayout(3, 1, 5, 5)
        );

        heroPanel.setBackground(panelColor);

        heroPanel.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(purple, 2),
                BorderFactory.createEmptyBorder(20, 10, 20, 10)
            )
        );

        characterDisplay = new JLabel(
            "",
            SwingConstants.CENTER
        );

        characterDisplay.setFont(
            new Font("SansSerif", Font.BOLD, 26)
        );

        characterDisplay.setForeground(white);

        typeDisplay = new JLabel(
            "",
            SwingConstants.CENTER
        );

        typeDisplay.setFont(
            new Font("SansSerif", Font.PLAIN, 15)
        );

        typeDisplay.setForeground(blue);

        JLabel abilities = new JLabel(
            "ATTACK  |  SPECIAL MOVE",
            SwingConstants.CENTER
        );

        abilities.setFont(
            new Font("SansSerif", Font.BOLD, 13)
        );

        abilities.setForeground(purple);

        heroPanel.add(characterDisplay);
        heroPanel.add(typeDisplay);
        heroPanel.add(abilities);

        JPanel buttonPanel = new JPanel(
            new GridLayout(1, 3, 10, 0)
        );

        buttonPanel.setBackground(background);

        JButton attackButton = createButton(
            "ATTACK", purple
        );

        JButton specialButton = createButton(
            "SPECIAL", blue
        );

        JButton addButton = createButton(
            "+ ADD HERO",
            new Color(65, 180, 130)
        );

        buttonPanel.add(attackButton);
        buttonPanel.add(specialButton);
        buttonPanel.add(addButton);

        JLabel logTitle = new JLabel("BATTLE LOG");

        logTitle.setForeground(purple);

        logTitle.setFont(
            new Font("SansSerif", Font.BOLD, 15)
        );

        battleLog = new JTextArea(8, 40);

        battleLog.setEditable(false);
        battleLog.setLineWrap(true);
        battleLog.setWrapStyleWord(true);

        battleLog.setFont(
            new Font("Monospaced", Font.PLAIN, 13)
        );

        battleLog.setBackground(new Color(15, 18, 32));
        battleLog.setForeground(new Color(180, 230, 200));

        battleLog.setMargin(
            new Insets(10, 10, 10, 10)
        );

        JScrollPane scrollPane = new JScrollPane(battleLog);

        scrollPane.setBorder(
            BorderFactory.createLineBorder(
                new Color(70, 75, 110)
            )
        );

        center.add(selectionPanel);
        center.add(Box.createVerticalStrut(15));

        center.add(heroPanel);
        center.add(Box.createVerticalStrut(15));

        center.add(buttonPanel);
        center.add(Box.createVerticalStrut(20));

        center.add(logTitle);
        center.add(Box.createVerticalStrut(8));

        center.add(scrollPane);

        add(center, BorderLayout.CENTER);

        characterList.addActionListener(e -> updateHeroDisplay());

        attackButton.addActionListener(e -> {

            GameCharacter hero = getSelectedCharacter();

            if (hero != null) {
                addLog("⚔ " + hero.attack());
            }

        });

        specialButton.addActionListener(e -> {

            GameCharacter hero = getSelectedCharacter();

            if (hero != null) {
                addLog("✦ " + hero.specialMove());
            }

        });

        addButton.addActionListener(e -> addCharacter());

        updateHeroDisplay();

        addLog("Welcome to IKI!");
        addLog("Your adventure begins...");
    }

    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setFont(
            new Font("SansSerif", Font.BOLD, 12)
        );

        button.setForeground(Color.WHITE);
        button.setBackground(color);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        button.setPreferredSize(
            new Dimension(150, 45)
        );

        return button;
    }

    private void updateCharacterList() {

        if (characterList == null) {
            return;
        }

        characterList.removeAllItems();

        for (GameCharacter character : characters) {

            characterList.addItem(
                character.getName()
                + " - "
                + character.getClass().getSimpleName()
            );

        }
    }

    private GameCharacter getSelectedCharacter() {

        int index = characterList.getSelectedIndex();

        if (index >= 0 && index < characters.size()) {
            return characters.get(index);
        }

        return null;
    }


    private void updateHeroDisplay() {

        GameCharacter hero = getSelectedCharacter();

        if (hero == null || characterDisplay == null) {
            return;
        }

        if (hero instanceof Warrior) {

            characterDisplay.setText(
                "⚔ " + hero.getName()
            );

            typeDisplay.setText(
                "WARRIOR | Strength & Defense"
            );

        } else if (hero instanceof Mage) {

            characterDisplay.setText(
                "✦ " + hero.getName()
            );

            typeDisplay.setText(
                "MAGE | Fire & Lightning Magic"
            );

        }

        addLog("Selected hero: " + hero.getName());
    }

    private void addCharacter() {

        JTextField nameField = new JTextField();

        String[] types = {
            "Warrior",
            "Mage"
        };

        JComboBox<String> typeChoice = new JComboBox<>(types);

        JPanel inputPanel = new JPanel(
            new GridLayout(0, 1, 5, 5)
        );

        inputPanel.add(
            new JLabel("Enter your hero's name:")
        );

        inputPanel.add(nameField);

        inputPanel.add(
            new JLabel("Choose character type:")
        );

        inputPanel.add(typeChoice);

        int result = JOptionPane.showConfirmDialog(
            this,
            inputPanel,
            "Create Your Hero",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String name = nameField.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Hero name cannot be empty!",
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (name.length() > 20) {

            JOptionPane.showMessageDialog(
                this,
                "Name must be 20 characters or fewer!",
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        for (GameCharacter character : characters) {

            if (character.getName().equalsIgnoreCase(name)) {

                JOptionPane.showMessageDialog(
                    this,
                    "That hero name already exists!",
                    "Duplicate Name",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }
        }

        GameCharacter newCharacter;

        if ("Warrior".equals(typeChoice.getSelectedItem())) {

            newCharacter = new Warrior(name);

        } else {

            newCharacter = new Mage(name);

        }

        characters.add(newCharacter);

        updateCharacterList();

        characterList.setSelectedIndex(
            characters.size() - 1
        );

        addLog("New hero created: " + name);
    }

    private void addLog(String message) {

        if (battleLog != null) {

            battleLog.append(message + "\n");

            battleLog.setCaretPosition(
                battleLog.getDocument().getLength()
            );

        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Main game = new Main();

            game.setVisible(true);

        });
    }
}