package Project.Handler;

import Project.Domain.Visit;
import Project.Services.HistoryService;
import Project.Services.PersonService;
import Project.Services.VisitService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by anons on 5/10/16.
 */
public class Dashboard extends JFrame{
    private int pId;
    private java.sql.Date date;
    private VisitService vService = new VisitService();
    private PersonService pService = new PersonService();
    private HistoryService hService = new HistoryService();
    public Dashboard(int pId){
        this.pId=pId;

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("History");
        JMenuItem hist = new JMenuItem("History",'I');
        JMenuItem aHist = new JMenuItem("All History",'A');

        menu.add(hist);
        if (pService.getUserType(pId).equals("Admin")){
            menu.add(aHist);
        }
        menu.setMnemonic('H');

        menuBar.add(menu);
        setJMenuBar(menuBar);

        JPanel mainPane = new JPanel(new BorderLayout());
        JPanel logoutPanel = new JPanel();
        JPanel reasonPanel = new JPanel(new GridBagLayout());


        GridBagConstraints c = new GridBagConstraints();

        JButton logout = new JButton("LOGOUT");


        JTextArea reason = new JTextArea(30,30);
        reason.setText("Enter Visit Purpose Here....");
        JButton submit = new JButton("Submit");

        logoutPanel.add(logout);

        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.PAGE_START;
        reasonPanel.add(new ClockPane(),c);
        c.gridy=1;
        c.anchor=GridBagConstraints.CENTER;
        c.insets=new Insets(20,0,0,0);
        reasonPanel.add(reason,c);
        c.gridy=2;
        c.insets=new Insets(20,0,0,0);
        c.anchor=GridBagConstraints.PAGE_END;
        reasonPanel.add(submit,c);


        mainPane.add(logoutPanel,BorderLayout.NORTH);
        mainPane.add(reasonPanel,BorderLayout.CENTER);

        reason.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (reason.getText().equals("Enter Visit Purpose Here....")){
                    reason.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (reason.getText().equals("")){
                    reason.setText("Enter Visit Purpose Here....");
                }
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (reason.getText().equals("Enter Visit Purpose Here....")||reason.getText().equals("")){
                    JOptionPane.showMessageDialog(Dashboard.this,"Please enter Visit Purpose","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Calendar calendar = Calendar.getInstance();
                    Time currentTime = new Time(calendar.getTime().getTime());
                    java.sql.Date currentDate = date;

                    Visit visit = Visit.getVisit(vService.getUniqueVisitId(),pId,currentDate,currentTime,reason.getText());
                    vService.storeVisit(visit);
                    JOptionPane.showMessageDialog(null,"Visit Purpose Recorded. Don't forget to LOGOUT!!!");
                    submit.setEnabled(false);
                    submit.setBackground(Color.RED);
                    reason.setEnabled(false);
                }

            }
        });


        hist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                History history = new History(pId);

            }
        });


        aHist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllHistory aHistory = new AllHistory(pId);
            }
        });


        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Calendar calendar = Calendar.getInstance();
                date = new java.sql.Date(calendar.getTime().getTime());
                if (vService.loggedVisit(pId,date)){
                    submit.setEnabled(false);
                    submit.setBackground(Color.RED);
                    reason.setEnabled(false);
                }
            }
        });
        add(mainPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,680);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public class ClockPane extends JPanel {

        private JLabel clock;

        public ClockPane() {
            setLayout(new BorderLayout());
            clock = new JLabel();
            clock.setHorizontalAlignment(JLabel.CENTER);
            clock.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 24f));
            tickTock();
            add(clock);

            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tickTock();
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            timer.start();
        }

        public void tickTock() {
            clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
        }
    }
}
