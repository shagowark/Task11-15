import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {
    LogicTest() {
    }

   @Test
   void deleteComments1(){
        ArrayList<String> given = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();

        given.add("//asdajfsjka  fas ");
        given.add("while (scanner.hasNext()) {");
        given.add("    line = scanner.nextLine();");
        given.add("}");
        given.add("/**** лафыфыллаыл");
        given.add("dasadfasfafs");
        given.add("asdas */");
        given.add("System.out.println()");
        given.add("//dassfdsa");

       expected.add("while (scanner.hasNext()) {");
       expected.add("    line = scanner.nextLine();");
       expected.add("}");
       expected.add("System.out.println()");

       Assertions.assertEquals(expected, Logic.deleteComments(given));
    }

    @Test
    void deleteComments2(){
        ArrayList<String> given = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();

        given.add("//asdajfsjka  fas ");
        given.add("//while (scanner.hasNext()) {");
        given.add("  //  line = scanner.nextLine();");
        given.add("//}");
        given.add("/**** лафыфыллаыл");
        given.add("dasadfasfafs");
        given.add("asdas */");
        given.add("//System.out.println()");
        given.add("//dassfdsa");

        Assertions.assertEquals(expected, Logic.deleteComments(given));
    }

    @Test
    void deleteComments3(){
        ArrayList<String> given = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();

        given.add("//asdajfsjka  fas ");
        given.add("while (scanner.hasNext()) {");
        given.add("    line = /*stangecomment*/ scanner.nextLine();");
        given.add("}");
        given.add("/**** лафыфыллаыл");
        given.add("dasadfasfafs");
        given.add("asdas */");
        given.add("System.out.println() //sdadsasd");
        given.add("//dassfdsa");

        expected.add("while (scanner.hasNext()) {");
        expected.add("    line = /*stangecomment*/ scanner.nextLine();");
        expected.add("}");
        expected.add("System.out.println() //sdadsasd");

        Assertions.assertEquals(expected, Logic.deleteComments(given));
    }


}