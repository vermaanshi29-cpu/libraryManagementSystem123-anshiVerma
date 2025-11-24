import javax.swing.*;
import java.awt.*;

public class LibraryFrame extends JFrame {
    private User currentUser;

    public LibraryFrame(User user) {
        this.currentUser = user;
        setTitle("Library System - " + currentUser.getUsername());
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 255));

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(infoArea);

        // Common buttons for all users
        JButton viewBooks = new JButton("View Books");
        viewBooks.addActionListener(e -> infoArea.setText(LibraryData.getInstance().listBooks()));

        JButton borrowBook = new JButton("Borrow Book");
        borrowBook.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter book title to borrow:");
            if (title != null) {
                String msg = LibraryData.getInstance().borrowBook(title.trim(), currentUser.getUsername());
                infoArea.setText(msg);
            }
        });

        JButton returnBook = new JButton("Return Book");
        returnBook.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter book title to return:");
            if (title != null) {
                String msg = LibraryData.getInstance().returnBook(title.trim(), currentUser.getUsername());
                infoArea.setText(msg);
            }
        });

        JButton checkFine = new JButton("Check Fine");
        checkFine.addActionListener(e -> {
            int fine = LibraryData.getInstance().getFine(currentUser.getUsername());
            infoArea.setText("Your fine: ₹" + fine);
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        });

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 255));
        buttonPanel.add(viewBooks);
        buttonPanel.add(borrowBook);
        buttonPanel.add(returnBook);
        buttonPanel.add(checkFine);

        // Admin-specific buttons
        if (currentUser.isAdmin()) {
            JButton viewBorrowedBooks = new JButton("View Borrowed Books");
            viewBorrowedBooks.setBackground(new Color(255, 200, 200));
            viewBorrowedBooks.addActionListener(e -> 
                infoArea.setText(LibraryData.getInstance().getBorrowedBooksDetails()));

            JButton viewAllBooks = new JButton("View All Books Status");
            viewAllBooks.setBackground(new Color(255, 200, 200));
            viewAllBooks.addActionListener(e -> 
                infoArea.setText(LibraryData.getInstance().getAllBooksDetails()));

            JButton viewUserBooks = new JButton("View User's Books");
            viewUserBooks.setBackground(new Color(255, 200, 200));
            viewUserBooks.addActionListener(e -> {
                // Show dialog with list of users
                java.util.List<String> usernames = LibraryData.getInstance().getAllUsernames();
                if (usernames.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No users found.");
                    return;
                }
                
                String[] usernameArray = usernames.toArray(new String[0]);
                String selectedUser = (String) JOptionPane.showInputDialog(
                    this,
                    "Select a user to view their borrowed books:",
                    "Select User",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    usernameArray,
                    usernameArray[0]
                );
                
                if (selectedUser != null) {
                    infoArea.setText(LibraryData.getInstance().getUserBorrowingHistory(selectedUser));
                }
            });

            // Add admin buttons to a separate row
            JPanel adminPanel = new JPanel();
            adminPanel.setBackground(new Color(255, 240, 240));
            adminPanel.setBorder(BorderFactory.createTitledBorder("Admin Functions"));
            adminPanel.add(viewBorrowedBooks);
            adminPanel.add(viewAllBooks);
            adminPanel.add(viewUserBooks);

            // Create main button panel with two rows
            JPanel mainButtonPanel = new JPanel(new BorderLayout());
            mainButtonPanel.setBackground(new Color(245, 245, 255));
            
            buttonPanel.add(logoutButton);
            mainButtonPanel.add(buttonPanel, BorderLayout.NORTH);
            mainButtonPanel.add(adminPanel, BorderLayout.SOUTH);
            
            add(mainButtonPanel, BorderLayout.NORTH);
        } else {
            buttonPanel.add(logoutButton);
            add(buttonPanel, BorderLayout.NORTH);
        }

        // Create credits label
        JLabel credits = new JLabel("<html><center><br><b>Developed by:</b><br>"
                + "Prashant Ranjan (24SCSE1011537)<br>"
                + "Avesh Singh (24SCSE1011267)<br>"
                + "Nishant Pandit(24SCSE1010365)"
                + "Kushagra Bhardwaj(24SCSE1010579)</center></html>");
        credits.setHorizontalAlignment(SwingConstants.CENTER);
        credits.setForeground(Color.DARK_GRAY);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(245, 245, 255));
        footerPanel.add(credits);

        add(scroll, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
}