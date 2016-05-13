package Project.Domain;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 * Created by anons on 5/12/16.
 */
public class AllHistoryTableModel extends AbstractTableModel {
    String[] columnNames={"Id","Visit_date","Visit_time","Name", "Contact", "Type", "Purpose"};
    Object[][] cells ;
    public AllHistoryTableModel(){

    }
    public AllHistoryTableModel(java.util.List<Visit> visits, java.util.List<Person> persons){
        cells = new Object[visits.size()][7];
        for (int i = 0; i <visits.size() ; i++) {
            cells[i][0]=visits.get(i).getVisitId();
            cells[i][1]=visits.get(i).getVisitDate();
            cells[i][2]=visits.get(i).getVisitTime();
            cells[i][3]=persons.get(i).getFirstName();
            cells[i][4]=persons.get(i).getContact();
            cells[i][5]=persons.get(i).getType();
            cells[i][6]=visits.get(i).getVisitPurpose();
        }
    }


    @Override
    public int getRowCount() {
        return cells.length;
    }

    @Override
    public int getColumnCount() {
        return cells[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return cells[rowIndex][columnIndex];
    }

    public JTable getTable(){
        return new JTable(cells,columnNames);
    }
}
