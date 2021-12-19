import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception{

        if (args.length == 0) {
            System.err.println("Укажите input и output файлы");
        } else {
            runInConsole(args);
        }
    }

    private static void runInConsole (String[] args) throws Exception {
        ConsoleModule.CmdLineArgsParser argsParser = new ConsoleModule.CmdLineArgsParser(args);

        String inputFilePath = argsParser.getArgumentValue("-i", "--input-file");
        String outputFilePath = argsParser.getArgumentValue("-o", "--output-file");

        Logic.correctProgramText(inputFilePath, outputFilePath);
    }
}
