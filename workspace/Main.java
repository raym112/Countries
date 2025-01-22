/* 
 Ray Mukoyama 
 1/22/2025
 This is a Java Geography game to quiz users about different countries. Main class is responsible for managing the game logic 
 * 
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class Main {
    private Country[] countryArray = new Country[10];  // Array for 10 countries
    private int index = 0;
    private JLabel outputLabel = new JLabel();  // To display messages
    private JLabel imageLabel = new JLabel();    // To display country image
    private JTextField inputField = new JTextField(20);  // To get user input for quiz
    private JFrame jFrame = new JFrame("GeoGame"); // JFrame to hold GUI components

    // Method to load countries from CSV file
    public void loadCountries() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("countries-data.csv"));

        // Skip header (if there's one)
        if (scan.hasNextLine()) scan.nextLine();

        // Reading each line from the file
        for (int i = 0; i < countryArray.length; i++) {
            String input = scan.nextLine();  // Read a line
            String[] data = input.split(",");  // Split into array by commas
            System.out.println("Read in " + data[0]);

            // Create a new Country object with data
            Country c = new Country(data[0], data[1], data[2], data[3]);

            // Add the Country object to the array
            countryArray[i] = c;
        }

        scan.close();
    }

    // Method to show country image
    public void showCountry() {
        Country c = countryArray[index];
        String imageFile = c.getImageFile();

        // Display image in GUI (assuming JLabel is used to show images)
        ImageIcon icon = new ImageIcon("images/" + imageFile);
        imageLabel.setIcon(icon);
    }

    // Method to handle Next button click
    public void nextButtonClick() {
        index = (index + 1) % countryArray.length;  // Cycle through countries
        outputLabel.setText("");  // Clear the output label
        showCountry();  // Display the next country
    }

    // Method to handle Review button click
    public void reviewButtonClick() {
        Country c = countryArray[index];
        String countryInfo = c.toString();
        System.out.println(countryInfo);  // Output to console for debugging
        outputLabel.setText(countryInfo);  // Show country information in the label
    }

    // Method to handle Quiz button click
    public void quizButtonClick() {
        outputLabel.setText("");  // Clear output label
        
        // Get the current country
        Country c = countryArray[index];
        String correctAnswer = c.getName();
        
        // Ask user the question
        String question = "What country is this? (Capital: " + c.getCapital() + ")";
        outputLabel.setText(question);  // Display question in label
        
        // Get the user's answer (use input field from GUI)
        String answer = inputField.getText().trim();
        
        // Check if the answer is correct
        if (answer.equalsIgnoreCase(correctAnswer)) {
            outputLabel.setText("Correct!");
        } else {
            outputLabel.setText("Incorrect. The correct answer was " + correctAnswer);
        }

        inputField.setText("");  // Clear the input field for the next round
    }

    // Method to set up the GUI components
    public void setupGUI() {
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(500, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Buttons
        JButton reviewButton = new JButton("Review");
        JButton quizButton = new JButton("Quiz");
        JButton nextButton = new JButton("Next");

        jFrame.add(reviewButton);
        jFrame.add(quizButton);
        jFrame.add(nextButton);

        // Add imageLabel to show the country images
        jFrame.add(imageLabel);
        
        // Output label to show information
        jFrame.add(outputLabel);
        
        // Add the input field for quiz answer
        jFrame.add(inputField);

        // Add event listeners for buttons
        reviewButton.addActionListener(e -> reviewButtonClick());
        quizButton.addActionListener(e -> quizButtonClick());
        nextButton.addActionListener(e -> nextButtonClick());

        jFrame.setVisible(true);
    }

    // Main method for testing
    public static void main(String[] args) {
        Main game = new Main();

        try {
            game.loadCountries();  // Load countries from CSV file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        game.setupGUI();  // Set up the GUI
        
        // Show the first country initially
        game.showCountry();
    }
}
