import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.SwingUtilities;

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
        
        JButton logOutButton = new JButton("Logout");

        // Add buttons to button panel
        Dimension buttonSize = new Dimension(250, 30);
        userProfileButton.setPreferredSize(buttonSize);
        workoutBuilderButton.setPreferredSize(buttonSize);
        mealPlanningButton.setPreferredSize(buttonSize);
        activityTrackingButton.setPreferredSize(buttonSize);
        calorieBurnCalculatorButton.setPreferredSize(buttonSize);
        
        Dimension LogoutSize = new Dimension (150, 50);
        logOutButton.setPreferredSize(LogoutSize);

        buttonPanel.add(userProfileButton);
        buttonPanel.add(workoutBuilderButton);
        buttonPanel.add(mealPlanningButton);
        buttonPanel.add(activityTrackingButton);
        buttonPanel.add(calorieBurnCalculatorButton);
        buttonPanel.add(logOutButton);

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
        
        logOutButton.addActionListener(e -> {
        	
        	dispose();
        	//LoginPage.setVisible(true);
        });

        buttonPanel.setBackground(new Color(20, 215, 150));
        setContentPane(buttonPanel);
    }
}


@SuppressWarnings("serial")
class UserProfilePage extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField heightField;
    private JTextField weightField;
    private JButton aerobicsButton;
    private JButton weightLiftingButton;
    private JButton saveButton;

    private Map<String, UserProfile> profiles;
    private UserProfile currentProfile;

    public UserProfilePage() {
        setTitle("User Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        // Initialize profiles
        profiles = new HashMap<>();
        profiles.put("Profile 1", new UserProfile("Profile 1"));
        profiles.put("Profile 2", new UserProfile("Profile 2"));
        profiles.put("Profile 3", new UserProfile("Profile 3"));
        currentProfile = profiles.get("Profile 1");

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Profile selection panel
        JPanel profileSelectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton profile1Button = new JButton("Profile 1");
        JButton profile2Button = new JButton("Profile 2");
        JButton profile3Button = new JButton("Profile 3");

        profileSelectionPanel.add(profile1Button);
        profileSelectionPanel.add(profile2Button);
        profileSelectionPanel.add(profile3Button);

        profile1Button.addActionListener(e -> switchProfile("Profile 1"));
        profile2Button.addActionListener(e -> switchProfile("Profile 2"));
        profile3Button.addActionListener(e -> switchProfile("Profile 3"));

        mainPanel.add(profileSelectionPanel, BorderLayout.NORTH);

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
        mainPanel.add(profilePicturePanel, BorderLayout.WEST);

        // User details panel
        JPanel userDetailsPanel = new JPanel(new GridBagLayout());
        userDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        userDetailsPanel.add(nameLabel, gbc);
        nameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        userDetailsPanel.add(nameField, gbc);

        JLabel ageLabel = new JLabel("Age:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        userDetailsPanel.add(ageLabel, gbc);
        ageField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        userDetailsPanel.add(ageField, gbc);

        JLabel heightLabel = new JLabel("Height (cm):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        userDetailsPanel.add(heightLabel, gbc);
        heightField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        userDetailsPanel.add(heightField, gbc);

        JLabel weightLabel = new JLabel("Weight (kg):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        userDetailsPanel.add(weightLabel, gbc);
        weightField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        userDetailsPanel.add(weightField, gbc);

        mainPanel.add(userDetailsPanel, BorderLayout.CENTER);

        // Favorite workouts panel
        JPanel favoriteWorkoutsPanel = new JPanel(new BorderLayout());
        favoriteWorkoutsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        JLabel favoriteLabel = new JLabel("Favorite Workouts:");
        favoriteWorkoutsPanel.add(favoriteLabel, BorderLayout.NORTH);
        JPanel favoriteButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        aerobicsButton = new JButton("Aerobics");
        weightLiftingButton = new JButton("Weight Lifting");
        favoriteButtonsPanel.add(aerobicsButton);
        favoriteButtonsPanel.add(weightLiftingButton);
        favoriteWorkoutsPanel.add(favoriteButtonsPanel, BorderLayout.CENTER);

        // Save button
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveProfileData());
        favoriteWorkoutsPanel.add(saveButton, BorderLayout.SOUTH);

        mainPanel.add(favoriteWorkoutsPanel, BorderLayout.SOUTH);

        // Add action listeners to update profile data
        addListeners();

        // Set initial profile data
        updateProfileData();

        setContentPane(mainPanel);
    }

    private void addListeners() {
        nameField.addActionListener(e -> currentProfile.setName(nameField.getText()));
        ageField.addActionListener(e -> currentProfile.setAge(ageField.getText()));
        heightField.addActionListener(e -> currentProfile.setHeight(heightField.getText()));
        weightField.addActionListener(e -> currentProfile.setWeight(weightField.getText()));
        aerobicsButton.addActionListener(e -> currentProfile.setFavoriteWorkout("Aerobics"));
        weightLiftingButton.addActionListener(e -> currentProfile.setFavoriteWorkout("Weight Lifting"));
    }

    private void switchProfile(String profileName) {
        currentProfile = profiles.get(profileName);
        updateProfileData();
    }

    private void updateProfileData() {
        nameField.setText(currentProfile.getName());
        ageField.setText(currentProfile.getAge());
        heightField.setText(currentProfile.getHeight());
        weightField.setText(currentProfile.getWeight());
        if ("Aerobics".equals(currentProfile.getFavoriteWorkout())) {
            aerobicsButton.setBackground(Color.GREEN);
            weightLiftingButton.setBackground(null);
        } else if ("Weight Lifting".equals(currentProfile.getFavoriteWorkout())) {
            weightLiftingButton.setBackground(Color.GREEN);
            aerobicsButton.setBackground(null);
        } else {
            aerobicsButton.setBackground(null);
            weightLiftingButton.setBackground(null);
        }
    }

    private void saveProfileData() {
        currentProfile.setName(nameField.getText());
        currentProfile.setAge(ageField.getText());
        currentProfile.setHeight(heightField.getText());
        currentProfile.setWeight(weightField.getText());
        if (aerobicsButton.getBackground() == Color.GREEN) {
            currentProfile.setFavoriteWorkout("Aerobics");
        } else if (weightLiftingButton.getBackground() == Color.GREEN) {
            currentProfile.setFavoriteWorkout("Weight Lifting");
        }
        JOptionPane.showMessageDialog(this, "Profile saved successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserProfilePage().setVisible(true));
    }
}

class UserProfile {
    private String name;
    private String age;
    private String height;
    private String weight;
    private String favoriteWorkout;

    public UserProfile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFavoriteWorkout() {
        return favoriteWorkout;
    }

    public void setFavoriteWorkout(String favoriteWorkout) {
        this.favoriteWorkout = favoriteWorkout;
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
        addCustomWorkoutButton = new JButton("Add Custom Workout");
        addCustomWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWorkoutCategoriesPage();
            }
        });
        plusButtonPanel.add(addCustomWorkoutButton);
        mainPanel.add(plusButtonPanel, BorderLayout.NORTH);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private void openWorkoutCategoriesPage() {
        JFrame categoriesPage = new JFrame("Select Workout Category");
        categoriesPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        categoriesPage.setSize(400, 300);

        // Panel for workout categories
        JPanel categoryPanel = new JPanel(new GridLayout(4, 1));
        JButton aerobicsButton = new JButton("Aerobics");
        JButton walkingButton = new JButton("Walking");
        JButton runningButton = new JButton("Running");
        JButton weightLiftingButton = new JButton("Weight Lifting");

        aerobicsButton.addActionListener(e -> openSubCategoryPage("Aerobics"));
        walkingButton.addActionListener(e -> openSubCategoryPage("Walking"));
        runningButton.addActionListener(e -> openSubCategoryPage("Running"));
        weightLiftingButton.addActionListener(e -> openSubCategoryPage("Weight Lifting"));

        categoryPanel.add(aerobicsButton);
        categoryPanel.add(walkingButton);
        categoryPanel.add(runningButton);
        categoryPanel.add(weightLiftingButton);

        categoriesPage.setContentPane(categoryPanel);
        categoriesPage.setVisible(true);
    }

    private void openSubCategoryPage(String category) {
        JFrame subCategoryPage = new JFrame(category + " Workouts");
        subCategoryPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        subCategoryPage.setSize(400, 300);

        // Panel for workout selection
        JPanel workoutSelectionPanel = new JPanel(new GridLayout(0, 1));

        if (category.equals("Aerobics")) {
            addWorkoutButtons(workoutSelectionPanel, "Elliptical Training", "Plyometrics", "Jumping Jacks");
        } else if (category.equals("Walking")) {
            addWorkoutButtons(workoutSelectionPanel, "Brisk Walking", "Jogging");
        } else if (category.equals("Running")) {
            addWorkoutButtons(workoutSelectionPanel, "Interval Training");
        } else if (category.equals("Weight Lifting")) {
            addWorkoutButtons(workoutSelectionPanel, "Squats", "Bench Press", "Bicep Curls", "Deadlifts", "Tricep Dips", "Leg Press");
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saveSelectedWorkouts(category, workoutSelectionPanel);
            subCategoryPage.dispose();
        });

        workoutSelectionPanel.add(saveButton);
        subCategoryPage.setContentPane(workoutSelectionPanel);
        subCategoryPage.setVisible(true);
    }

    private void addWorkoutButtons(JPanel panel, String... workouts) {
        for (String workout : workouts) {
            JCheckBox checkBox = new JCheckBox(workout);
            panel.add(checkBox);
        }
    }

    private void saveSelectedWorkouts(String category, JPanel panel) {
        String workoutLabel = getNextCustomWorkoutTabName();
        JPanel workoutPanel = new JPanel(new BorderLayout());

        StringBuilder workoutBuilder = new StringBuilder(category + " Workouts: ");
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    workoutBuilder.append(checkBox.getText()).append(", ");
                }
            }
        }

        JLabel workoutsLabel = new JLabel(workoutBuilder.toString());
        workoutPanel.add(workoutsLabel, BorderLayout.CENTER);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> editWorkout(workoutLabel, workoutsLabel, category));

        JButton renameButton = new JButton("Rename");
        renameButton.addActionListener(e -> renameWorkout(workoutLabel));

        JButton deleteButton = new JButton(new ImageIcon(getClass().getResource("TrashCan.png")));
        deleteButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this custom workout?", "Delete Custom Workout", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                tabbedPane.removeTabAt(tabbedPane.indexOfComponent(workoutPanel));
                updateAddCustomWorkoutButton();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(editButton);
        buttonPanel.add(renameButton);
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

    private void editWorkout(String workoutLabel, JLabel workoutsLabel, String category) {
        JFrame customWorkoutPage = new JFrame("Edit Custom Workouts");
        customWorkoutPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customWorkoutPage.setSize(400, 300);

        JPanel workoutSelectionPanel = new JPanel(new GridLayout(0, 1));

        if (category.equals("Aerobics")) {
            editWorkoutButtons(workoutSelectionPanel, workoutsLabel.getText(), "Elliptical Training", "Plyometrics", "Jumping Jacks");
        } else if (category.equals("Walking")) {
            editWorkoutButtons(workoutSelectionPanel, workoutsLabel.getText(), "Brisk Walking", "Jogging");
        } else if (category.equals("Running")) {
            editWorkoutButtons(workoutSelectionPanel, workoutsLabel.getText(), "Interval Training");
        } else if (category.equals("Weight Lifting")) {
            editWorkoutButtons(workoutSelectionPanel, workoutsLabel.getText(), "Squats", "Bench Press", "Bicep Curls", "Deadlifts", "Tricep Dips", "Leg Press");
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saveEditedWorkouts(workoutLabel, workoutSelectionPanel, category);
            customWorkoutPage.dispose();
        });

        workoutSelectionPanel.add(saveButton);
        customWorkoutPage.setContentPane(workoutSelectionPanel);
        customWorkoutPage.setVisible(true);
    }

    private void editWorkoutButtons(JPanel panel, String selectedWorkouts, String... workouts) {
        for (String workout : workouts) {
            JCheckBox checkBox = new JCheckBox(workout);
            checkBox.setSelected(selectedWorkouts.contains(workout));
            panel.add(checkBox);
        }
    }

    private void saveEditedWorkouts(String workoutLabel, JPanel panel, String category) {
        JPanel workoutPanel = (JPanel) tabbedPane.getComponentAt(tabbedPane.indexOfTab(workoutLabel));
        JLabel workoutsLabel = (JLabel) workoutPanel.getComponent(0);

        StringBuilder workoutBuilder = new StringBuilder(category + " Workouts: ");
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    workoutBuilder.append(checkBox.getText()).append(", ");
                }
            }
        }

        workoutsLabel.setText(workoutBuilder.toString());
    }

    private void renameWorkout(String workoutLabel) {
        String newLabel = JOptionPane.showInputDialog(this, "Enter new name for the workout:", workoutLabel);
        if (newLabel != null && !newLabel.trim().isEmpty()) {
            int index = tabbedPane.indexOfTab(workoutLabel);
            if (index != -1) {
                tabbedPane.setTitleAt(index, newLabel);
            }
        }
    }

    private void updateAddCustomWorkoutButton() {
        addCustomWorkoutButton.setText("Add Custom Workout");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WorkoutBuilderPage().setVisible(true));
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
                        caloriesBurned = duration * 3; // avg walking speed at 3 mph 
                        break;
                    case "Running":
                        caloriesBurned = duration * 7; // avg running speed at 7 mph
                        break;
                    case "Aerobics":
                        caloriesBurned = duration * 444; // avg cal burned 444 per minute
                        break;
                    case "Weight Lifting":
                        caloriesBurned = duration * 4; //  avg 4 cal burned per minute
                        break;
                }
                resultLabel.setText("Calories burned: " + caloriesBurned);
            }
        });

        setContentPane(panel);
    }
}

