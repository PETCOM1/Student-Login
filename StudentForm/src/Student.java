
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import jdk.jfr.events.FileReadEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Munzhedzi Munyadziwa Petrus @Petcom Digital.com
 */
public class Student extends JFrame implements ActionListener{

    
    //Declare panels
    private JPanel headerPanel;
    private JPanel clientelDetailsPanel;
    private JPanel studentNoPanel;
    private JPanel namePanel;
    private JPanel surnamePanel;
    
    
    private JPanel displayAreaPanel;
    private JPanel buttonsPanel;
    
    private JPanel collectivePanel2;
    private JPanel collectivePanel3;
    
    private JPanel mainPanel;
    
    //Labels
    private JLabel headerLabel;
    private JLabel studentNoLabel;
    private JLabel nameLabel;
    private JLabel surnameLabel;

    //Textfields
    private JTextField studentNoTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    
    //TextAreas
    private JTextArea displayTextArea;
    
    //Buttons
    private JButton registerButton;
    private JButton clearButton;
    private JButton cancelButton;
    
    private File myFile = new File("SystemFile.txt");
    public Student() {
        
        //Initialize jFrame
        setSize(450,550);
        setTitle("Student Bio");
        //setLayout();
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //Initialize all panels all at once
        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //Client panel
        clientelDetailsPanel = new JPanel(new GridLayout(3, 1));
        clientelDetailsPanel.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 1), "Client"));
        studentNoPanel = new JPanel(new GridLayout(1,2,8,10));
        namePanel = new JPanel(new GridLayout(1,2,8,10));
        surnamePanel = new JPanel(new GridLayout(1,2,8,10));
        
        
        //TextArea
        displayAreaPanel = new JPanel(new FlowLayout());
        displayAreaPanel.setBorder(new TitledBorder("Students"));
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        //Collectivepanels
        collectivePanel2 = new JPanel(new BorderLayout(3, 3));
        collectivePanel3 = new JPanel(new BorderLayout(3, 3));
        
        
        //Main Panel
        mainPanel = new JPanel(new BorderLayout());
        
        
        
        //Initialize all Labels 
        headerLabel = new JLabel("Student Form");
        headerLabel.setForeground(Color.BLUE);
        headerLabel.setBounds(5, 5, 5, 5);
        
        studentNoLabel = new JLabel("Student no");
        nameLabel = new JLabel("Name");
        surnameLabel = new JLabel("Surname");
        
        //Initialize TextFields
        studentNoTextField = new JTextField(20);
        nameTextField = new JTextField(20);
        surnameTextField = new JTextField(20);
        
        //Initialize TextArea
        displayTextArea = new JTextArea(25, 50);
        
        //Buttons
        registerButton = new JButton("Register");
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);
        
        clearButton = new JButton("Clear");
        clearButton.setFocusable(false);
        clearButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(this);
        
        //Now put everything together
        headerPanel.add(headerLabel);
        
        studentNoPanel.add(studentNoLabel);
        studentNoPanel.add(studentNoTextField);
        
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        
        surnamePanel.add(surnameLabel);
        surnamePanel.add(surnameTextField);
        
        clientelDetailsPanel.add(studentNoPanel);
        clientelDetailsPanel.add(namePanel);
        clientelDetailsPanel.add(surnamePanel);
        
        
        displayAreaPanel.add(displayTextArea);
        
        buttonsPanel.add(registerButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(cancelButton);
        
        collectivePanel2.add(headerLabel,BorderLayout.CENTER);
        collectivePanel2.add(clientelDetailsPanel,BorderLayout.SOUTH);
        
        collectivePanel3.add(displayAreaPanel,BorderLayout.NORTH);
        collectivePanel3.add(buttonsPanel,BorderLayout.SOUTH);
        
        mainPanel.add(collectivePanel2,BorderLayout.NORTH);
        mainPanel.add(collectivePanel3,BorderLayout.SOUTH);
        
        add(mainPanel);
        pack();
        setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()== cancelButton){
            
            System.exit(0);
        }
        
        if(e.getSource()== clearButton){
            
            studentNoTextField.setText(null);
            nameTextField.setText(null);
            surnameTextField.setText(null);
            
            displayTextArea.setText(null);
            
        }
        
        if(e.getSource()== registerButton){
            
            
                JFileChooser flChooser = new JFileChooser();
                int response = flChooser.showSaveDialog(null);
                String path = null;
                
                if(response == JFileChooser.APPROVE_OPTION){
                    path = flChooser.getSelectedFile().getAbsolutePath();
                }
                
                System.out.print(path);
                
                myFile = new File(path);
            try {
                
                FileWriter flwrite = new FileWriter(myFile,true);
                BufferedWriter bfwrite = new BufferedWriter(flwrite);
                
                StringBuilder buildString = new StringBuilder();
                buildString.append(studentNoTextField.getText()+"#" + nameTextField.getText() +"#"+surnameTextField.getText());
                bfwrite.write(buildString.toString());
                
                
                //Also display on the textArea
                displayTextArea.setText(buildString.toString());
                bfwrite.close();
                
                JOptionPane.showMessageDialog(null, "Successful");
            } catch (IOException x) {
                
                System.out.println("Couldnt find the file");
            }
        }
    }
    
    public static void main(String[] args) {
        
        new Student();
    }
}
