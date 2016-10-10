package rmMicro;

import com.rapidminer.RapidMiner;
import com.rapidminer.Process;
import com.rapidminer.ProcessContext;
import com.rapidminer.example.Attribute;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.IOContainer;
//import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorException;
//import org.apache.log4j.*;
//import org.apache.log4j.varia.NullAppender;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
//import com.rapidminer.operator.nio.ExcelExampleSource;
import com.rapidminer.tools.XMLException;


public class Classification {

    public static void main(String [] args) throws Exception{
        java.util.logging.LogManager.getLogManager().reset();
        RapidMiner.setExecutionMode(RapidMiner.ExecutionMode.COMMAND_LINE);
        RapidMiner.init();
//RepositoryLocation loc = new RepositoryLocation("//Repo/path/process");
//Process process = new RepositoryProcessLocation(loc).load(null);
//Repository repo = RepositoryManager.getInstance(null).getRepository("Local Repository");
//RepositoryProcessLocation processLocation = new RepositoryProcessLocation(new RepositoryLocation(repo.getLocation(), "nameOfMyProcess"));
//      LogManager.getLogManager().reset();
//      Logger.getRootLogger().removeAllAppenders();
//      Logger.getRootLogger().addAppender(new NullAppender());
//      Logger.getRootLogger().setLevel(Level.OFF);
//      org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);

        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        //----------------------------------
        int i=0;
        String procfile = "ExampleProcess.rmp";
        while (i<1) {
                startTime = System.currentTimeMillis();
                readExcel(procfile);
                endTime = System.currentTimeMillis();
                duration = (endTime - startTime);
                System.out.format("%d.Read Excel -> took %d\n",i,duration);
                i++;
            }
    }

        public static void readExcel(String procFile) {
         ExampleSet resultSet1 = null;
         IOContainer ioInput = null;
        IOContainer ioResult;
        try {
//            RapidMiner.setExecutionMode(RapidMiner.ExecutionMode.COMMAND_LINE);
//            RapidMiner.init();
//              Repository repo = RepositoryManager.getInstance(null).getRepository("Local Repository");
//              RepositoryProcessLocation processLocation = new RepositoryProcessLocation(new RepositoryLocation(repo.getLocation(), "nameOfMyProcess"));

            Process pr = new Process(new File(procfile));
            ProcessContext pc = pr.getContext();
//            Operator op = pr.getOperator("Read Excel");
            
//            op.setParameter(ExcelExampleSource.PARAMETER_EXCEL_FILE, "C:\\Users\\MP-TEST\\Desktop\\Rapid_Test\\HaendlerRatings_neu.xls");
            IOContainer ic = new IOContainer();

            ioResult = pr.run(ioInput);
            try {

                if (ioResult.getElementAt(0) instanceof ExampleSet) {
                    resultSet1 = (ExampleSet)ioResult.getElementAt(0);

                    for (Example example : resultSet1) {
                        Iterator<Attribute> allAtts = example.getAttributes().allAttributes();
                        Attribute pred = example.getAttributes().get("prediction(Status)");
                        Attribute pid = example.getAttributes().get("PID");

                        System.out.format("p=%s pid=%s\n", example.getValueAsString(pred), example.getValueAsString(pid));

//                        while(allAtts.hasNext()) {
//                            Attribute a = allAtts.next();
//                            if (a.getName().equals("prediction(Status)" ) ) {
//
//                            }
//                                    if (a.isNumerical()) {
//                                            double value = example.getValue(a);
////                                            System.out.println(value);
//
//                                    } else {
//                                            String value = example.getValueAsString(a);
////                                            System.out.println(value);
//                                    }
//                             }

                    }
                }
            } catch(Exception e) {
                //
            }

        } catch (IOException | XMLException | OperatorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
}
