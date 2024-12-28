import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

class Property {
    private String title;
    private String description;
    private double price;
    private String imagePath;
    private boolean isRented;
    private int rentalDuration; // Duration of rental (in days)

    public Property(String title, String description, double price, String imagePath) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.isRented = false;
        this.rentalDuration = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isRented() {
        return isRented;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        String status = isRented ? " (Rented for " + rentalDuration + " days)" : " (Available)";
        return title + " - IDR " + price + status;
    }
}

public class RentalApp {
    private JFrame frame;
    private JTextField titleField, descriptionField, priceField, imagePathField;
    private JList<Property> propertyList;
    private DefaultListModel<Property> propertyModel;
    private ArrayList<Property> properties;

    public RentalApp() {
        properties = new ArrayList<>();
        propertyModel = new DefaultListModel<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Property Rental Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // Form Panel (Input fields)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Property"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Title:"), gbc);
        titleField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        formPanel.add(titleField, gbc);

        // Description
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Description:"), gbc);
        descriptionField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        formPanel.add(descriptionField, gbc);

        // Price
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Price (IDR):"), gbc);
        priceField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        formPanel.add(priceField, gbc);

        // Image Path
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Image Path:"), gbc);
        imagePathField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        formPanel.add(imagePathField, gbc);
        JButton uploadButton = new JButton("Upload Image");
        uploadButton.addActionListener(e -> uploadImage());
        gbc.gridx = 2;
        formPanel.add(uploadButton, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        JButton addButton = new JButton("Add Property");
        addButton.addActionListener(e -> addProperty());
        formPanel.add(addButton, gbc);

        gbc.gridx = 1;
        JButton rentButton = new JButton("Rent Property");
        rentButton.addActionListener(e -> rentProperty());
        formPanel.add(rentButton, gbc);

        gbc.gridx = 2;
        JButton viewImageButton = new JButton("View Image");
        viewImageButton.addActionListener(e -> viewImage());
        formPanel.add(viewImageButton, gbc);

        gbc.gridx = 3;
        JButton returnButton = new JButton("Return Property Early");
        returnButton.addActionListener(e -> returnPropertyEarly());
        formPanel.add(returnButton, gbc);

        gbc.gridx = 4;
        JButton updateButton = new JButton("Update Property");
        updateButton.addActionListener(e -> updateProperty());
        formPanel.add(updateButton, gbc);

        gbc.gridx = 5;
        JButton deleteButton = new JButton("Delete Property");
        deleteButton.addActionListener(e -> deleteProperty());
        formPanel.add(deleteButton, gbc);

        frame.add(formPanel, BorderLayout.NORTH);

        // List Panel (Properties List)
        propertyList = new JList<>(propertyModel);
        propertyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(propertyList);
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Properties"));
        frame.add(listScrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void addProperty() {
        try {
            String title = titleField.getText();
            String description = descriptionField.getText();
            double price = Double.parseDouble(priceField.getText());
            String imagePath = imagePathField.getText();

            if (title.isEmpty() || description.isEmpty() || imagePath.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File imageFile = new File(imagePath);
            if (!imageFile.exists() || !imageFile.isFile()) {
                JOptionPane.showMessageDialog(frame, "Invalid image path.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Property property = new Property(title, description, price, imagePath);
            properties.add(property);
            propertyModel.addElement(property);

            // Clear input fields
            titleField.setText("");
            descriptionField.setText("");
            priceField.setText("");
            imagePathField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid price. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rentProperty() {
        Property selectedProperty = propertyList.getSelectedValue();
        if (selectedProperty == null) {
            JOptionPane.showMessageDialog(frame, "Please select a property to rent.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedProperty.isRented()) {
            JOptionPane.showMessageDialog(frame, "This property is already rented.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String durationInput = JOptionPane.showInputDialog(frame, "Enter rental duration (in days):");
            try {
                int duration = Integer.parseInt(durationInput);
                if (duration <= 0) {
                    throw new NumberFormatException();
                }
                selectedProperty.setRented(true);
                selectedProperty.setRentalDuration(duration);
                propertyList.repaint(); // Update the list to show the rented status
                JOptionPane.showMessageDialog(frame, "You have successfully rented: " + selectedProperty.getTitle());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid duration. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void returnPropertyEarly() {
        Property selectedProperty = propertyList.getSelectedValue();
        if (selectedProperty == null) {
            JOptionPane.showMessageDialog(frame, "Please select a property to return.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!selectedProperty.isRented()) {
            JOptionPane.showMessageDialog(frame, "This property is not rented.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to return this property early?", "Confirm Return", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                selectedProperty.setRented(false);
                selectedProperty.setRentalDuration(0);
                propertyList.repaint(); // Update the list to show the returned status
                JOptionPane.showMessageDialog(frame, "Property returned successfully.");
            }
        }
    }

    private void viewImage() {
        Property selectedProperty = propertyList.getSelectedValue();
        if (selectedProperty == null) {
            JOptionPane.showMessageDialog(frame, "Please select a property to view its image.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ImageIcon icon = new ImageIcon(selectedProperty.getImagePath());

            if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                JOptionPane.showMessageDialog(frame, "Failed to load image. Please check the image path.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Image image = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            JOptionPane.showMessageDialog(frame, imageLabel, "Property Image", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to load image. Please check the image path.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProperty() {
        Property selectedProperty = propertyList.getSelectedValue();
        if (selectedProperty == null) {
            JOptionPane.showMessageDialog(frame, "Please select a property to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prompt the user to input new details
        String newTitle = JOptionPane.showInputDialog(frame, "Enter new title:", selectedProperty.getTitle());
        String newDescription = JOptionPane.showInputDialog(frame, "Enter new description:", selectedProperty.getDescription());
        String newPriceInput = JOptionPane.showInputDialog(frame, "Enter new price (IDR):", selectedProperty.getPrice());
        String newImagePath = JOptionPane.showInputDialog(frame, "Enter new image path:", selectedProperty.getImagePath());

        try {
            double newPrice = Double.parseDouble(newPriceInput);

            // Update property details
            selectedProperty.setTitle(newTitle);
            selectedProperty.setDescription(newDescription);
            selectedProperty.setPrice(newPrice);
            selectedProperty.setImagePath(newImagePath);

            propertyList.repaint();
            JOptionPane.showMessageDialog(frame, "Property updated successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid price. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProperty() {
        Property selectedProperty = propertyList.getSelectedValue();
        if (selectedProperty == null) {
            JOptionPane.showMessageDialog(frame, "Please select a property to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this property?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            properties.remove(selectedProperty);
            propertyModel.removeElement(selectedProperty);
            JOptionPane.showMessageDialog(frame, "Property deleted successfully.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RentalApp());
    }
}