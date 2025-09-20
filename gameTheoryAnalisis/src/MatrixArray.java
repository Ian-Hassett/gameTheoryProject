import java.util.ArrayList;

public class MatrixArray extends ArrayList<MatrixComponet>implements MatrixComponet{
    public String toString(){
        String output = "";
        output += "{";
        for (MatrixComponet elem : this) {
            output+= elem.toString()+"|";
        }
        output = output.substring(0,output.length()-1)+"}";
        return output;
    }
}