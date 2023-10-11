package com.caelius.chatbot;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class ChatBotJava extends JFrame {
    private JTextPane textPane = new JTextPane();
    private JTextField textField = new JTextField("Type your message here...",10);
    private JButton button = new JButton(">");


    public ChatBotJava() {
    	
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(700, 550);
        ChatDatabase.initializeDatabase();
        getContentPane().setBackground(new Color(240,240,240));
        
        setTitle("ChatBot");
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        ImageIcon img=new ImageIcon("icon2.png");
        setIconImage(img.getImage());

        JPanel topPanel = new JPanel();
//        topPanel.setSize(new Dimension(500, 40)); 
        topPanel.setBackground(new Color(38,165,208));
//        topPanel.setForeground(Color.white);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        topPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

        JLabel label = new JLabel("How can I help you?");
        label.setForeground(Color.white);
        label.setFont(new Font("Verdana", Font.BOLD,20 ));
        
        topPanel.add(label);
        add(topPanel, BorderLayout.NORTH);
        
        
        textPane.setFont(new Font("Verdana", Font.PLAIN, 15));
        textPane.setEditable(false);
        textPane.setBackground(Color.white);
        
        
        
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(18, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
        

        
        EmptyBorder paddingBorder = new EmptyBorder(10, 10, 10, 10);
    
        
        Border compoundBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), paddingBorder // Padding border
        );
        scrollPane.setBorder(compoundBorder); //compound border
        add(scrollPane, BorderLayout.CENTER);


        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
            	textField.setFont(new Font("Verdana", Font.PLAIN, 10));
                if (textField.getText().equals("Type your message here...")) {
                	textField.setFont(new Font("Verdana", Font.PLAIN, 20));
                    textField.setText("");
                    textField.setForeground(Color.black); 
                }
            } 
        });
    
        
        

        JPanel bottomPanel = new JPanel(new BorderLayout());
        //bottomPanel.setPreferredSize(new Dimension(600, 45)); 

        
        textField.setForeground(Color.LIGHT_GRAY);
        //textField.setPreferredSize(textField.getPreferredSize());
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                new EmptyBorder(10, 10, 10, 10)));
        
        textField.setFont(new Font("Verdana", Font.PLAIN, 18));
        textField.setBackground(Color.white);

        TextField buttonText=new TextField();
        bottomPanel.add(textField, BorderLayout.CENTER);
        bottomPanel.add(button, BorderLayout.EAST);
        button.setText("Send");
        
        buttonText.setBackground(null);
        buttonText.setText("Send");
        
        button.setPreferredSize(new Dimension(100, 10));
        button.setBackground(new Color(38,165,208));
        button.setForeground(Color.white);

        add(bottomPanel, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String user = textField.getText().toLowerCase();
                appendMessage("You: " + user, Color.black );
                
                
                

                int sleep = 800; //0.8 seconds
                new Thread(() -> {
                    try {
                        Thread.sleep(sleep);
                        SwingUtilities.invokeLater(() -> {
                            String botResponse = ChatDatabase.queryDatabase(user);
                            appendMessage("Bot: " + botResponse, Color.RED);
                            //appendMessage("\n", Color.BLACK);
                            textField.setText("");
                            
                            if (textField.getText().equals("")) {
                                textField.setText("Type your message here...");
                                textField.setForeground(Color.LIGHT_GRAY);
                                textField.setFont(new Font("Verdana", Font.PLAIN, 15));
                            }
                        });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });
    }

    private void appendMessage(String message, Color color) {
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);

        try {
            doc.insertString(doc.getLength(), message + "\n", style);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->
                new ChatBotJava());
    }
}
