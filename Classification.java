import com.rapidminer.RapidMiner;
import com.rapidminer.Process;
import com.rapidminer.example.Attribute;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.IOContainer;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
//import com.rapidminer.operator.io.ExcelExampleSource; 
import com.rapidminer.operator.io.*; 
import com.rapidminer.tools.XMLException;


public class Classification {

    public static void main(String [] args) throws Exception{
         ExampleSet resultSet1 = null;
         IOContainer ioInput = null;
        IOContainer ioResult;
        try {
            RapidMiner.setExecutionMode(RapidMiner.ExecutionMode.COMMAND_LINE);
            RapidMiner.init();
            Process pr = new Process(new File("C:\\Users\\MP-TEST\\Desktop\\Rapid_Test\\Wieder_Model.rmp"));
            Operator op = pr.getOperator("Read Excel");
            op.setParameter(ExcelExampleSource.PARAMETER_EXCEL_FILE, "C:\\Users\\MP-TEST\\Desktop\\Rapid_Test\\HaendlerRatings_neu.xls");
            ioResult = pr.run(ioInput);
            if (ioResult.getElementAt(0) instanceof ExampleSet) {
                resultSet1 = (ExampleSet)ioResult.getElementAt(0);

                for (Example example : resultSet1) {
                    Iterator<Attribute> allAtts = example.getAttributes().allAttributes();
                    while(allAtts.hasNext()) {
                        Attribute a = allAtts.next();
                                if (a.isNumerical()) {
                                        double value = example.getValue(a);
                                        System.out.println(value);

                                } else {
                                        String value = example.getValueAsString(a);
                                        System.out.println(value);
                                }
                         }
                }
                    }
        } catch (IOException | XMLException | OperatorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




          }
}
