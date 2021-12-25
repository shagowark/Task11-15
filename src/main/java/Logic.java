import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Logic {
    public static void correctProgramText(String fileNameInput, String fileNameOutput) throws Exception {
        ArrayList<String> programLines = new ArrayList<>(readLinesFromFile(fileNameInput));
        programLines = deleteComments(programLines);
        writeCorrectedProgram(programLines, fileNameOutput);
    }

    public static ArrayList<String> readLinesFromFile(String fileName) throws Exception {
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

    public static ArrayList<String> deleteComments(ArrayList<String> programLines) {
        ArrayList<String> correctedLines = new ArrayList<>();

        boolean isInComm = false;
        boolean isInString = false;


        for (String line : programLines) {
            char prevChar = ' ';
            StringBuilder lineSb = new StringBuilder();
            for (char ch : line.toCharArray()) {
                if (ch == '"' && prevChar != '\\') {
                    isInString = !isInString;
                }

                if (isInString) {
                    lineSb.append(ch);
                    prevChar = ch;
                    continue;
                } else {
                    if (ch == '/' && prevChar == '/') {
                        lineSb.deleteCharAt(lineSb.length()-1);
                        break;
                    } else if (ch == '*' && prevChar == '/') {
                        lineSb.deleteCharAt(lineSb.length()-1);
                        isInComm = true;
                    } else if (ch == '/' && prevChar == '*') {
                        isInComm = false;
                        continue;
                    }
                }

                if (!isInComm) {
                    lineSb.append(ch);
                }

                prevChar = ch;
            }
            correctedLines.add(lineSb.toString());
        }

        return correctedLines;
    }

    public static void writeCorrectedProgram(ArrayList<String> programLines, String fileName) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String line : programLines) {
            writer.write(line + "\n");
            writer.flush();
        }
    }
}
