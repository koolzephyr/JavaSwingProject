package Project.Handler;

import Project.Domain.HistoryTableModel;
import Project.Services.HistoryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anons on 5/12/16.
 */
public class History extends JFrame {
    private int pId;
    JDialog registerDialog;
    HistoryService hService = new HistoryService();

    public History(int id){
        this.pId=id;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDialog.setDefaultLookAndFeelDecorated(true);
        JPanel okPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        registerDialog = new JDialog(this,"History",true);
        registerDialog.setSize(800,680);

        JPanel mainPanel = new JPanel(new BorderLayout());

        HistoryTableModel model = new HistoryTableModel(hService.history(pId));

        JTable historyTable = model.getTable();
        JScrollPane pane = new JScrollPane(historyTable);

        JButton okButton = new JButton("OK");
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.PAGE_START;
        okPanel.add(okButton,c);

        mainPanel.add(pane,BorderLayout.NORTH);
        mainPanel.add(okPanel,BorderLayout.CENTER);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerDialog.dispose();
            }
        });
        registerDialog.add(mainPanel);
        registerDialog.setLocationRelativeTo(null);
        registerDialog.setVisible(true);
        setVisible(true);
    }
}
