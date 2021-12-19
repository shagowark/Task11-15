import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Logic {
    public static void correctProgramText(String fileNameInput, String fileNameOutput) throws Exception{
        ArrayList<String> programLines = new ArrayList<>(readLinesFromFile(fileNameInput));
        programLines = deleteComments(programLines);
        writeCorrectedProgram(programLines, fileNameOutput);
    }

    public static ArrayList<String> readLinesFromFile(String fileName) throws Exception{
        ArrayList<String> programLines = new ArrayList<>();

        Scanner scanner = new Scanner(new File(fileName), StandardCharsets.UTF_8);
        String line;
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (line == null) {
                break;
            } else {
                programLines.add(line);
            }
        }

        return programLines;

    }

    public static ArrayList<String> deleteComments(ArrayList<String> programLines){
        ArrayList<String> correctedLines = new ArrayList<>();

        boolean multilineComm = false;
        for (String line : programLines){

            boolean skipLine = false;
            if (!multilineComm) {
                int i = 0;
                while (i < line.length() && line.charAt(i) == ' ') {
                    i++;
                }
                if (line.substring(i).startsWith("//")) {
                    skipLine = true;
                } else if (line.substring(i).startsWith("/*")) {
                    multilineComm = true;
                    skipLine = true;
                }

                if (!skipLine){
                    correctedLines.add(line);
                }
            } else{ //asdasd
                if (line.trim().endsWith("*/")){
                    multilineComm = false;
                }
            }
        }

        return correctedLines;
    }

    public static void writeCorrectedProgram(ArrayList<String> programLines, String fileName) throws Exception{
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String line : programLines){
            writer.write(line + "\n");
            writer.flush();
        }
    }
}
