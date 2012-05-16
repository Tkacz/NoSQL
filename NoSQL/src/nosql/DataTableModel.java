/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nosql;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rafal Tkaczyk
 */
public class DataTableModel extends AbstractTableModel{

    private Class[] classes = {Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class};
    private String[] headers = {"Id","Date Occurred","Date Reported","Location","Short Description","Duration","Long Description","US City","US State","Year Month"};
    private Object data[][];
    
    int rows;
    final int cols = 10;
    
    public DataTableModel(){
	data = null;
        rows = 0;
    }
    
    public DataTableModel(int size){
	data = new Object[size][cols];
    }
    
    @Override
    public int getRowCount() {
	return rows;
    }

    @Override
    public int getColumnCount() {
	return headers.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
	return headers[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
	return classes[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	data[rowIndex][columnIndex] = aValue;
	fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public void setValueAt(ArrayList<Event> events) {
        rows = events.size();
        data = new Object[rows][cols];
        for(int i = 0; i < rows; i++) {
            data[i][0] = events.get(i).getId();
            data[i][1] = events.get(i).getDateOccurred();
            data[i][2] = events.get(i).getDateReported();
            data[i][3] = events.get(i).getLocation();
            data[i][4] = events.get(i).getShortDescription();
            data[i][5] = events.get(i).getDuration();
            data[i][6] = events.get(i).getLongDescription();
            data[i][7] = events.get(i).getUSCity();
            data[i][8] = events.get(i).getUSState();
            data[i][9] = events.get(i).getYearMonth();
        }
        fireTableDataChanged();
    }
}
