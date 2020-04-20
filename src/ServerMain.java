import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ServerMain {

    public static String sendCommand(String[] command) {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process client;
        StringBuilder output = new StringBuilder();
        try {
            client = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
            return output.toString();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        int exitCode;
        try {
            exitCode = client.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return output.toString();
        }
        client.destroy();
        return output.toString();
    }


    public static int evaluateOutput(String output) throws IOException, ParseException {
        CreateJson createJson = new CreateJson();
        createJson.CreatingFile("ans" , output);
        Answers answers = new Answers();
        answers.decodingJson("ans.json");
        return answers.parserAns();
    }
    public Map< String,Integer> processData(List<String> lines) throws IOException, ParseException {
        Map< String,Integer> evlAns = new HashMap<>();
        Server server = new Server();
        server.start();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] command = {"curl", "-s", "-X", "GET", "-H", "Content-Type: application/json", "-d", "",
                "http://localhost:8000/yap/heb/joint", "|", "jq", "."};
        int number = 0;
        String output;
        command[7] = "{\"text\": \"שורה אחרת  \"}";
        System.out.println(command[7]);
        output = sendCommand(command);
        while (output.length() == 0) {
            output = sendCommand(command);
        }

        System.out.println(output);
        //server.interrupt();
        for (String line: lines
             ) {
            System.out.println(line);
            command[7] = "{\"text\": \""+line+"  \"}";
            System.out.println(command[7]);
            output = sendCommand(command);
            number = evaluateOutput(output);
            System.out.println(number);
            evlAns.put(line, number);

        }

        server.interrupt();
        System.out.println(number);
        return evlAns;

    }

}