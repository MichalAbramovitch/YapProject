import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;


public class Answers {

    private String decodeAns;

    public Answers() {

    }
    public void decodingJson(String json_file) throws IOException, ParseException {

        Object obj = new JSONParser().parse(new FileReader(json_file));
        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
        String md_lattice = (String) jo.get("md_lattice");
        this.decodeAns = md_lattice;
    }

    // need to check what about the case per = A
    public int parserAns(){
        int counter = 0;
        String[] words = decodeAns.split("\n");
        String[] attribute;
        for (String word:words) {
            if(word.equals(""))continue;
            attribute = word.split("\t");
            int idx_person = attribute[6].indexOf("per");
            if(idx_person ==-1) continue;
            attribute = attribute[6].split("per=");
            char person_num = attribute[1].charAt(0);
            if(person_num == '1') counter++;
        }

        return counter;
    }
}
