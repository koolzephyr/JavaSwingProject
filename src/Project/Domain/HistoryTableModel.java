package Project.Domain;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 * Created by anons on 5/12/16.
 */
public class HistoryTableModel extends AbstractTableModel {

    String[] columnNames={"Id","Visit_date","Visit_time","Purpose"};
    Object[][] cells ;
    public HistoryTableModel(){

    }
    public HistoryTableModel(java.util.List<Visit> visitHistory){
        cells = new Object[visitHistory.size()][4];
        for (int i = 0; i <visitHistory.size() ; i++) {
            cells[i][0]=visitHistory.get(i).getVisitId();
            cells[i][1]=visitHistory.get(i).getVisitDate();
            cells[i][2]=visitHistory.get(i).getVisitTime();
            cells[i][3]=visitHistory.get(i).getVisitPurpose();
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
