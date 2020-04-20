import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String args[]) throws IOException, ParseException {

        ReadExcelFileDemo demo = new ReadExcelFileDemo("src/student.xlsx");
        demo.parser();
        List<String> sentences = demo.getSentences();
        ServerMain serverMain = new ServerMain();
        Map< String,Integer> ans = serverMain.processData(sentences);
        int j =6 ;
        System.out.println(j);

    }
}
