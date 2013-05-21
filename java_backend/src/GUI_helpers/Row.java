package GUI_helpers;

/**
 *
 * @author LeonHuzen
 */
public class Row {
    public Object[] columnNames;
    public Object[] columnValues;
    
    public Row (Object[] columnNames, Object[] columnValues) {
        this.columnNames = columnNames;
        this.columnValues = columnValues;
    }
}
