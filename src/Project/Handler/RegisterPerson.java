package Project.Handler;

import Project.Domain.Person;
import Project.Services.PersonService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Created by anons on 5/2/16.
 */
public class RegisterPerson extends JFrame{
    JDialog registerDialog;
    Person person;
    PersonService pService;
    public RegisterPerson(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDialog.setDefaultLookAndFeelDecorated(true);

        registerDialog = new JDialog(this,"Register",true);
        registerDialog.setSize(700,680);


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        //Sub Panels
        JPanel titlePanel = new JPanel(new GridBagLayout());
        JPanel registerPanel = new JPanel(new GridBagLayout());


        JLabel titleField = new JLabel();
        titleField.setText("Visitor Registration");
        titleField.setFont(titleField.getFont().deriveFont(34f));

        cons.gridx=2;
        cons.gridy=0;
        cons.weightx=0.5;
        cons.gridheight=1;
        cons.anchor= GridBagConstraints.PAGE_START;
        titlePanel.add(titleField,cons);
        panel.add(titleField,cons);

        //Add border to register panel
        registerPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel firstNameLabel = new JLabel("First Name *");
        JTextField firstName = new JTextField(20);

        JLabel middleNameLabel = new JLabel("Middle Name");
        JTextField middleName = new JTextField(20);
        middleName.setText("");

        JLabel lastNameLabel = new JLabel("Last Name *");
        JTextField lastName = new JTextField(20);

        JLabel userNameLabel = new JLabel("User Name");
        JTextField userName = new JTextField(20);
        userName.setEditable(false);

        JLabel passwordLabel = new JLabel("Password *");
        JPasswordField password = new JPasswordField(20);

        JLabel addressLabel = new JLabel("Address");
        JTextField address = new JTextField(20);
        address.setText("");

        JLabel contactLabel = new JLabel("Contact *");
        JTextField contact = new JTextField(20);

        JLabel emailLabel = new JLabel("Email");
        JTextField email = new JTextField(20);
        email.setText("");

        JLabel typeLabel = new JLabel("Type *");
        String[] visitorType = {"Employee","Student","Guest","Admin"};
        JComboBox<String> type = new JComboBox<>(visitorType);

        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                userName.setText(firstName.getText().toLowerCase()+"."+lastName.getText().toLowerCase());
            }
        });



        cons.gridx=0;
        cons.gridy=0;
        cons.gridwidth=2;
        cons.weightx=0.5;
        cons.ipady=25;
        cons.insets=new Insets(30,30,0,0);
        registerPanel.add(firstNameLabel,cons);
        cons.gridx=2;
        cons.ipady=15;
        cons.insets=new Insets(30,10,0,30);
        registerPanel.add(firstName,cons);

        cons.gridx=0;
        cons.gridy=1;
        cons.ipady=25;
        cons.insets=new Insets(10,30,0,0);
        registerPanel.add(middleNameLabel,cons);
        cons.gridx=2;
        cons.ipady=15;
        cons.insets=new Insets(10,10,0,30);
        registerPanel.add(middleName,cons);

        cons.gridx=0;
        cons.gridy=2;
        cons.ipady=25;
        cons.insets=new Insets(10,30,0,0);
        registerPanel.add(lastNameLabel,cons);
        cons.gridx=2;
        cons.ipady=15;
        cons.insets=new Insets(10,10,0,30);
        registerPanel.add(lastName,cons);

        cons.gridx=0;
        cons.gridy=3;
        cons.ipady=25;
        cons.insets=new Insets(10,30,0,0);
        registerPanel.add(userNameLabel,cons);
        cons.gridx=2;
        cons.ipady=15;
        cons.insets=new Insets(10,10,0,30);
        registerPanel.add(userName,cons);

        cons.gridx=0;
        cons.gridy=4;
        cons.ipady=25;
        cons.insets=new Insets(10,30,0,0);
        registerPanel.add(passwordLabel,cons);
        cons.gridx=2;
        cons.ipady=15;
        cons.insets=new Insets(10,10,0,30);
        registerPanel.add(password,cons);

        cons.gridx=0;
        cons.gridy=5;
        cons.ipady=25;
        cons.insets=new Insets(10,30,0,0);
        registerPanel.add(addressLabel,cons);
        cons.gridx=2;
        cons.ipady=15;
        cons.insets=new Insets(10,10,0,30);
        registerPanel.add(address,cons);

        cons.gridx=0;
        cons.gridy=6;
        cons.ipady=25;
        cons.insets=new Insets(10,30,0,0);
        registerPanel.add(contactLabel,cons);
        cons.gridx=2;
        cons.ipady=15;
        cons.insets=new Insets(10,10,0,30);
        registerPanel.add(contact,cons);

        cons.gridx=0;
        cons.gridy=7;
        cons.ipady=25;
        cons.insets=new Insets(10,30,0,0);
        registerPanel.add(emailLabel,cons);
        cons.gridx=2;
        cons.ipady=15;
        cons.insets=new Insets(10,10,0,30);
        registerPanel.add(email,cons);

        cons.gridx=0;
        cons.gridy=8;
        cons.ipady=25;
        cons.insets=new Insets(10,30,0,0);
        registerPanel.add(typeLabel,cons);
        cons.gridx=2;
        cons.ipady=15;
        cons.insets=new Insets(10,10,0,30);
        registerPanel.add(type,cons);


        JButton registerButton = new JButton("Register");
        cons.gridx=1;
        cons.gridy=9;
        cons.ipady=15;
        cons.insets=new Insets(10,30,30,30);
        cons.anchor= GridBagConstraints.PAGE_START;
        registerPanel.add(registerButton,cons);

        cons.gridy=2;
        cons.ipady=0;
        cons.insets=new Insets(30,10,0,30);
        cons.anchor= GridBagConstraints.CENTER;
        panel.add(registerPanel,cons);



        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pService = new PersonService();
                int id = pService.getUniquePersonId();
                String fName = firstName.getText();
                String mName = middleName.getText();
                String lName = lastName.getText();
                String uName = userName.getText();
                String pass = String.valueOf(password.getPassword());
                String addrss = address.getText();
                String cntct = contact.getText();
                String eml = email.getText();
                String typ = (String) type.getSelectedItem();
                if (fName.isEmpty()||lName.isEmpty()||pass.isEmpty()||cntct.isEmpty()||typ.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please fill up all the fields with * on label");
                }else {
                    person = Person.getPerson(id,fName,mName,lName,uName,pass,addrss,cntct,eml,typ);
                    pService.storePerson(person);
                    JOptionPane.showMessageDialog(null,"Succefully Registered");
                    registerDialog.dispose();
                }

            }
        });
        registerDialog.add(panel);
        registerDialog.setLocationRelativeTo(null);
        registerDialog.setVisible(true);
        setVisible(true);

    }

/*    public static void main(String[] args) {
        RegisterPerson rp = new RegisterPerson();
    }*/
}
