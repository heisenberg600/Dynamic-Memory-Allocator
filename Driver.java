import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Driver {
    public static void main(String args[]) throws IOException{
        File myObj = new File("./test60.txt");
        FileWriter fw=new FileWriter("./output3.txt");
        Scanner sc = new Scanner(myObj);
        int numTestCases;
        numTestCases = sc.nextInt();
        while (numTestCases-- > 0) {
            int size;
            size = sc.nextInt();
            A2DynamicMem obj = new A2DynamicMem(size,3);
            int numCommands = sc.nextInt();
            while (numCommands-- > 0) {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
                boolean t=true;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        break;
                    case "Defragment":
                        obj.Defragment();
                        break;
                    default:
                        break;
                }
                String str = String.valueOf(result);
                fw.write(str);
                fw.write("\n");
                
            }

        }
        fw.close();
        sc.close();
    }
}