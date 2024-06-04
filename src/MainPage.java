import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

@SuppressWarnings({ "serial", "unused" })
class MainPage extends JFrame {
    public MainPage() {
        setTitle("Fitness Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Panel for holding buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 150));
        
        

        // Button for each feature
        JButton userProfileButton = new JButton("User Profile");
        JButton workoutBuilderButton = new JButton("Workout Builder");
        JButton mealPlanningButton = new JButton("Meal Planning");
        JButton activityTrackingButton = new JButton("Activity Tracking");
        JButton calorieBurnCalculatorButton = new JButton("Calorie Burn Calculator");

        // Add buttons to button panel
        Dimension buttonSize = new Dimension(250, 30);
        userProfileButton.setPreferredSize(buttonSize);
        workoutBuilderButton.setPreferredSize(buttonSize);
        mealPlanningButton.setPreferredSize(buttonSize);
        activityTrackingButton.setPreferredSize(buttonSize);
        calorieBurnCalculatorButton.setPreferredSize(buttonSize);

        buttonPanel.add(userProfileButton);
        buttonPanel.add(workoutBuilderButton);
        buttonPanel.add(mealPlanningButton);
        buttonPanel.add(activityTrackingButton);
        buttonPanel.add(calorieBurnCalculatorButton);

        // Action listeners for each button
        userProfileButton.addActionListener(e -> {
            // Open user profile page
            new UserProfilePage().setVisible(true);
        });

        workoutBuilderButton.addActionListener(e -> {
            // Open workout builder page
            new WorkoutBuilderPage().setVisible(true);
        });

        mealPlanningButton.addActionListener(e -> {
            // Open meal planning page
            new MealPlanningPage().setVisible(true);
        });

        activityTrackingButton.addActionListener(e -> {
            // Open activity tracking page
            new ActivityTrackingPage().setVisible(true);
        });

        calorieBurnCalculatorButton.addActionListener(e -> {
            // Open calorie burn calculator page
            new CalorieBurnCalculatorPage().setVisible(true);
        });

        buttonPanel.setBackground(new Color(20, 215, 150));
        setContentPane(buttonPanel);
    }
}


@SuppressWarnings("serial")
class UserProfilePage extends JFrame {
    public UserProfilePage() {
        setTitle("User Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Profile picture panel
        JPanel profilePicturePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int diameter = Math.min(getWidth(), getHeight());
                Ellipse2D.Double circle = new Ellipse2D.Double(30, 0, diameter, diameter);
                g2d.setClip(circle);
                // Draw placeholder profile picture (gray circle)
                g2d.setColor(Color.lightGray);
                g2d.fill(circle);
                g2d.dispose();
            }
        };
        profilePicturePanel.setPreferredSize(new Dimension(100, 100)); // Adjust size as needed
        profilePicturePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Add border for spacing
        mainPanel.add(profilePicturePanel, BorderLayout.NORTH);

        // User details panel
        JPanel userDetailsPanel = new JPanel(new GridLayout(4, 2));
        userDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel heightLabel = new JLabel("Height (cm):");
        JTextField heightField = new JTextField();
        JLabel weightLabel = new JLabel("Weight (kg):");
        JTextField weightField = new JTextField();
        userDetailsPanel.add(nameLabel);
        userDetailsPanel.add(nameField);
        userDetailsPanel.add(ageLabel);
        userDetailsPanel.add(ageField);
        userDetailsPanel.add(heightLabel);
        userDetailsPanel.add(heightField);
        userDetailsPanel.add(weightLabel);
        userDetailsPanel.add(weightField);
        mainPanel.add(userDetailsPanel, BorderLayout.CENTER);

        // Favorite workouts panel
        JPanel favoriteWorkoutsPanel = new JPanel(new BorderLayout());
        favoriteWorkoutsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        JLabel favoriteLabel = new JLabel("Favorite Workouts:");
        favoriteWorkoutsPanel.add(favoriteLabel, BorderLayout.NORTH);
        JPanel favoriteButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton aerobicsButton = new JButton("Aerobics");
        JButton weightLiftingButton = new JButton("Weight Lifting");
        favoriteButtonsPanel.add(aerobicsButton);
        favoriteButtonsPanel.add(weightLiftingButton);
        favoriteWorkoutsPanel.add(favoriteButtonsPanel, BorderLayout.CENTER);
        mainPanel.add(favoriteWorkoutsPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }
}


@SuppressWarnings("serial")
class WorkoutBuilderPage extends JFrame {
    private JTabbedPane tabbedPane;
    private int customWorkoutCount = 1;
    private JButton addCustomWorkoutButton;

    public WorkoutBuilderPage() {
        setTitle("Workout Builder");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Plus button panel
        JPanel plusButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addCustomWorkoutButton = new JButton("+ Add Custom Workout");
        addCustomWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCustomWorkoutPage();
            }
        });
        plusButtonPanel.add(addCustomWorkoutButton);
        mainPanel.add(plusButtonPanel, BorderLayout.NORTH);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private void openCustomWorkoutPage() {
        JFrame customWorkoutPage = new JFrame("Custom Workouts");
        customWorkoutPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customWorkoutPage.setSize(400, 300);

        // Panel for workout selection
        JPanel workoutSelectionPanel = new JPanel(new GridLayout(4, 1));
        JCheckBox walkingCheckBox = new JCheckBox("Walking");
        JCheckBox runningCheckBox = new JCheckBox("Running");
        JCheckBox aerobicsCheckBox = new JCheckBox("Aerobics");
        JCheckBox weightLiftingCheckBox = new JCheckBox("Weight Lifting");

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSelectedWorkouts(walkingCheckBox.isSelected(), runningCheckBox.isSelected(),
                        aerobicsCheckBox.isSelected(), weightLiftingCheckBox.isSelected());
                customWorkoutPage.dispose();
            }
        });

        workoutSelectionPanel.add(walkingCheckBox);
        workoutSelectionPanel.add(runningCheckBox);
        workoutSelectionPanel.add(aerobicsCheckBox);
        workoutSelectionPanel.add(weightLiftingCheckBox);

        workoutSelectionPanel.add(saveButton);
        customWorkoutPage.setContentPane(workoutSelectionPanel);
        customWorkoutPage.setVisible(true);
    }

    private void saveSelectedWorkouts(boolean walking, boolean running, boolean aerobics, boolean weightLifting) {
        String workoutLabel = getNextCustomWorkoutTabName();
        JPanel workoutPanel = new JPanel(new BorderLayout());

        StringBuilder workoutBuilder = new StringBuilder("Workouts: ");
        if (walking) workoutBuilder.append("Walking, ");
        if (running) workoutBuilder.append("Running, ");
        if (aerobics) workoutBuilder.append("Aerobics, ");
        if (weightLifting) workoutBuilder.append("Weight Training");
        
        

        JLabel workoutsLabel = new JLabel(workoutBuilder.toString());
        workoutPanel.add(workoutsLabel, BorderLayout.CENTER);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editWorkout(workoutLabel, workoutsLabel);
            }
        });

        JButton deleteButton = new JButton(new ImageIcon(getClass().getResource("TrashCan.png")));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this custom workout?",
                        "Delete Custom Workout", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    tabbedPane.removeTabAt(tabbedPane.indexOfComponent(workoutPanel));
                    updateAddCustomWorkoutButton();
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(new JLabel("Edit"));
        buttonPanel.add(editButton);
        buttonPanel.add(new JLabel("Delete"));
        buttonPanel.add(deleteButton);
        workoutPanel.add(buttonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab(workoutLabel, workoutPanel);
        updateAddCustomWorkoutButton();
    }

    private String getNextCustomWorkoutTabName() {
        String workoutLabel;
        while (tabbedPane.indexOfTab("Custom Workout " + customWorkoutCount) != -1) {
            customWorkoutCount++;
        }
        workoutLabel = "Custom Workout " + customWorkoutCount++;
        return workoutLabel;
    }

    private void editWorkout(String workoutLabel, JLabel workoutsLabel) {
        JFrame customWorkoutPage = new JFrame("Edit Custom Workouts");
        customWorkoutPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customWorkoutPage.setSize(400, 300);

        JPanel workoutSelectionPanel = new JPanel(new GridLayout(4, 1));
        JCheckBox walkingCheckBox = new JCheckBox("Walking");
        JCheckBox runningCheckBox = new JCheckBox("Running");
        JCheckBox aerobicsCheckBox = new JCheckBox("Aerobics");
        JCheckBox weightLiftingCheckBox = new JCheckBox("Weight Lifting");

        // Set the checkboxes based on the current selections
        String workoutsText = workoutsLabel.getText();
        walkingCheckBox.setSelected(workoutsText.contains("Walking"));
        runningCheckBox.setSelected(workoutsText.contains("Running"));
        aerobicsCheckBox.setSelected(workoutsText.contains("Aerobics"));
        weightLiftingCheckBox.setSelected(workoutsText.contains("Weight Lifting"));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEditedWorkouts(workoutLabel, walkingCheckBox.isSelected(), runningCheckBox.isSelected(),
                        aerobicsCheckBox.isSelected(), weightLiftingCheckBox.isSelected());
                customWorkoutPage.dispose();
            }
        });

        workoutSelectionPanel.add(walkingCheckBox);
        workoutSelectionPanel.add(runningCheckBox);
        workoutSelectionPanel.add(aerobicsCheckBox);
        workoutSelectionPanel.add(weightLiftingCheckBox);

        workoutSelectionPanel.add(saveButton);
        customWorkoutPage.setContentPane(workoutSelectionPanel);
        customWorkoutPage.setVisible(true);
    }

    private void saveEditedWorkouts(String workoutLabel, boolean walking, boolean running, boolean aerobics, boolean weightLifting) {
        JPanel workoutPanel = (JPanel) tabbedPane.getComponentAt(tabbedPane.indexOfTab(workoutLabel));
        JLabel workoutsLabel = (JLabel) workoutPanel.getComponent(0);

        StringBuilder workoutBuilder = new StringBuilder("Workouts: ");
        if (walking) workoutBuilder.append("Walking, ");
        if (running) workoutBuilder.append("Running, ");
        if (aerobics) workoutBuilder.append("Aerobics, ");
        if (weightLifting) workoutBuilder.append("Weight Lifting");

        workoutsLabel.setText(workoutBuilder.toString());
    }

    private void updateAddCustomWorkoutButton() {
        StringBuilder customWorkouts = new StringBuilder("Custom Workouts: ");
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            String tabTitle = tabbedPane.getTitleAt(i);
            if (tabTitle.startsWith("Custom Workout")) {
                customWorkouts.append(tabTitle).append(", ");
            }
        }
        if (customWorkouts.length() > 17) {
            customWorkouts.delete(customWorkouts.length() - 2, customWorkouts.length()); // Remove trailing comma and space
        } else {
            customWorkouts.append("None");
        }
        addCustomWorkoutButton.setText(customWorkouts.toString());
    }
}
@SuppressWarnings("serial")
class MealPlanningPage extends JFrame {
    public MealPlanningPage() {
        setTitle("Meal Planning");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Link boxes panel
        JPanel linkBoxesPanel = new JPanel(new GridLayout(3, 1));
        linkBoxesPanel.setBorder(BorderFactory.createTitledBorder("Diet Plans"));

        // Carnivore Diet link box
        JLabel carnivoreDietLabel = new JLabel("Carnivore Diet");
        carnivoreDietLabel.setForeground(Color.BLUE);
        carnivoreDietLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        carnivoreDietLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openWebPage("https://www.primalkitchen.com/blogs/recipes/carnivore-diet-meal-plan-for-beginners");
            }
        });
        linkBoxesPanel.add(carnivoreDietLabel);

        // Keto Diet link box
        JLabel ketoDietLabel = new JLabel("Keto Diet");
        ketoDietLabel.setForeground(Color.BLUE);
        ketoDietLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ketoDietLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openWebPage("https://www.delish.com/cooking/g4798/easy-keto-diet-dinner-recipes/");
            }
        });
        linkBoxesPanel.add(ketoDietLabel);

        // Vegan Diet link box
        JLabel veganDietLabel = new JLabel("Vegan Diet");
        veganDietLabel.setForeground(Color.BLUE);
        veganDietLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        veganDietLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openWebPage("https://www.feastingathome.com/vegan-dinner-recipes/");
            }
        });
        linkBoxesPanel.add(veganDietLabel);

        mainPanel.add(linkBoxesPanel, BorderLayout.CENTER);

        // Add the main panel to the frame's content pane
        setContentPane(mainPanel);
    }

    // Method to open a web page in the default browser
    private void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

@SuppressWarnings("serial")
class ActivityTrackingPage extends JFrame {
    public ActivityTrackingPage() {
        setTitle("Activity Tracking");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Most recent workout panel
        JPanel recentWorkoutPanel = new JPanel(new GridLayout(3, 2));
        recentWorkoutPanel.setBorder(BorderFactory.createTitledBorder("Most Recent Workout"));
        JLabel dateLabel = new JLabel("Date:");
        JLabel dateValueLabel = new JLabel("2024-04-30"); // Example date, replace with actual value
        JLabel durationLabel = new JLabel("Duration:");
        JLabel durationValueLabel = new JLabel("45 minutes"); // Example duration, replace with actual value
        JLabel typeLabel = new JLabel("Type:");
        JLabel typeValueLabel = new JLabel("Running"); // Example type, replace with actual value
        recentWorkoutPanel.add(dateLabel);
        recentWorkoutPanel.add(dateValueLabel);
        recentWorkoutPanel.add(durationLabel);
        recentWorkoutPanel.add(durationValueLabel);
        recentWorkoutPanel.add(typeLabel);
        recentWorkoutPanel.add(typeValueLabel);
        mainPanel.add(recentWorkoutPanel, BorderLayout.CENTER);

        // Add the main panel to the frame's content pane
        setContentPane(mainPanel);
    }
}

@SuppressWarnings("serial")
class CalorieBurnCalculatorPage extends JFrame {
    public CalorieBurnCalculatorPage() {
        setTitle("Calorie Burn Calculator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        // Create UI components
        JLabel activityLabel = new JLabel("Activity:");
        JComboBox<String> activityComboBox = new JComboBox<>(new String[]{"Walking", "Running", "Aerobics", "Weight Lifting"});
        JLabel durationLabel = new JLabel("Duration (in minutes):");
        JTextField durationField = new JTextField();
        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel("Calories burned: ");
        
        calculateButton.setPreferredSize(new Dimension(25,30));

        // Layout components using a grid layout
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(activityLabel);
        panel.add(activityComboBox);
        panel.add(durationLabel);
        panel.add(durationField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        // Action listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform calculation and update result label
                double duration = Double.parseDouble(durationField.getText());
                double caloriesBurned = 0;
                String selectedActivity = (String) activityComboBox.getSelectedItem();
                switch (selectedActivity) {
                    case "Walking":
                        caloriesBurned = duration * 3.5; // 
                        break;
                    case "Running":
                        caloriesBurned = duration * 9; // 
                        break;
                    case "Aerobics":
                        caloriesBurned = duration * 25; // 
                        break;
                    case "Weight Lifting":
                        caloriesBurned = duration * 12; // 
                        break;
                }
                resultLabel.setText("Calories burned: " + caloriesBurned);
            }
        });

        setContentPane(panel);
    }
}

