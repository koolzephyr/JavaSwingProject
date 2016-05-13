package Project.Handler;

import Project.Services.LoginService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by anons on 5/2/16.
 */
public class Login extends JFrame {
    LoginService lService;
    public Login(){
        setSize(1000,680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Main panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        //Sub Panels
        JPanel titlePanel = new JPanel(new GridBagLayout());
//        JPanel descPanel = new JPanel(new GridBagLayout());
        JPanel loginPanel = new JPanel(new GridBagLayout());

        //Application title with large font
        JLabel titleField = new JLabel();
        titleField.setText("Visitor Recorder");
        titleField.setFont(titleField.getFont().deriveFont(64f));

        cons.gridx=1;
        cons.gridy=0;
        cons.weightx=0.5;
        cons.gridheight=1;
        cons.anchor= GridBagConstraints.PAGE_START;
        titlePanel.add(titleField,cons);
        panel.add(titleField,cons);


        //Add border to login panel
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Login title with medium font
        JLabel loginTitle = new JLabel();
        loginTitle.setText("LOGIN");
        loginTitle.setFont(loginTitle.getFont().deriveFont(34f));

        //Label for registration
        JLabel registerLabel = new JLabel();
        registerLabel.setText("Are You a New Visitor?");

        //Login and Register Button
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        //Username and Password field
        JLabel usernameLabel = new JLabel("Username Required!");
        usernameLabel.setVisible(false);
        JTextField username = new JTextField(20);
        username.setText("Enter Username");

        JLabel passwordLabel = new JLabel("Password Required!");
        passwordLabel.setVisible(false);
        JPasswordField password = new JPasswordField(20);
        password.setText("Enter Password");

        //Placeholder for username
        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (username.getText().equals("Enter Username")){
                    username.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (username.getText().equals("")){
                    username.setText("Enter Username");
                }
            }
        });

        //Placeholder for password
        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(password.getPassword()).equals("Enter Password")){
                    password.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (password.getPassword().length==0){
                    password.setText("Enter Password");
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uName = username.getText();
                String pass = String.valueOf(password.getPassword());
                if (uName.equals("Enter Username")){
                    usernameLabel.setVisible(true);
                }
                else if (pass.equals("Enter Password")){
                    passwordLabel.setVisible(true);
                }
                else {
                    lService = new LoginService();
                    int pId = lService.validateUser(uName,pass);
                    if(pId!=0){
                        Dashboard dashboard = new Dashboard(pId);
                        dispose();
                    }
                    else {
                        usernameLabel.setText("Invalid username/password");
                        usernameLabel.setVisible(true);
                    }
                }
            }
        });


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterPerson registerPerson=new RegisterPerson();
            }
        });


        cons.gridx=0;
        cons.gridy=0;
        cons.gridwidth=0;
        cons.weightx=0.5;
        cons.anchor=GridBagConstraints.PAGE_START;
        cons.insets=new Insets(50,0,40,10);
        loginPanel.add(loginTitle,cons);

        cons.gridy=2;
        cons.insets=new Insets(10,30,0,30);
        loginPanel.add(usernameLabel,cons);
        cons.gridy=3;
        cons.ipady=25;
        cons.insets=new Insets(2,30,0,30);
        loginPanel.add(username,cons);

        cons.gridy=4;
        cons.ipady=0;
        cons.insets=new Insets(10,30,0,30);
        loginPanel.add(passwordLabel,cons);
        cons.gridy=5;
        cons.ipady=25;
        cons.insets=new Insets(2,30,0,30);
        loginPanel.add(password,cons);

        cons.gridy=6;
        cons.ipady=0;
        cons.insets=new Insets(10,30,20,30);
        loginPanel.add(loginButton,cons);

        cons.gridy=7;
        cons.gridheight=1;
        cons.gridwidth=0;
        cons.insets=new Insets(10,30,0,10);
        cons.anchor= GridBagConstraints.FIRST_LINE_START;
        loginPanel.add(registerLabel,cons);

        cons.gridy=8;
        cons.insets=new Insets(10,30,50,30);
        cons.anchor=GridBagConstraints.PAGE_START;
        loginPanel.add(registerButton,cons);

        cons.gridy=2;
        cons.anchor = GridBagConstraints.CENTER;
        cons.insets=new Insets(50,0,10,30);
        panel.add(loginPanel,cons);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
