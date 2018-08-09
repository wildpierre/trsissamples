/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.lab1.model;

/**
 *
 * @author Pavel.Stepanov
 */
public  class DataModel {
    static volatile int rows=9;
    static volatile int columns=9;
    
    
    public static String getValue(int row, int column){
        return ""+row+"x"+column+"="+(row*column);
    }
    
    public static int getRows(){
        return rows;
    }
    
    public static int getColumns(){
        return columns;
    }
}
