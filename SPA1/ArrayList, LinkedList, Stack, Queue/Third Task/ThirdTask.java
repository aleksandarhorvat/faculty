/*
Write a program that
(a) loads the strings from the file “r1.txt” into the string serving queue
(b) removes strings shorter than 6 characters from the beginning of the service queue
(c) print the content of the resulting line to a file specified by the user

Write a program that
(a) loads the integers from the files “p1.txt” and “p2.txt” onto two different integer stacks
(b) removes all numbers whose last digit is 6 from the top of the first stack
(c) removes from the top of the second stack all numbers greater than its successor
(d) merges data from two stacks into a new one, alternately inserting data (note that the stacks do not have to be of the same length)
(e) writes the content of the thus obtained stack to the file "pp.txt"
*/
import java.io.*;
import java.util.*;
public class ThirdTask {

    private static Red<String> loadStrings(String f) {
        try {
            File file = new File(f);
            Scanner in = new Scanner(file);
            Red<String> queue = new Red<String>();
            while (in.hasNext() && !queue.jePun()) {
                queue.naKraj(in.nextLine());
            }
            in.close();
            return queue;
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
        return null;
    }

    private static Stek<Integer> loadNumbers(String f) {
        try {
            File file = new File(f);
            Scanner in = new Scanner(file);
            Stek<Integer> stek = new Stek<Integer>();
            while (in.hasNext() && !stek.jePun()) {
                stek.stavi(Integer.parseInt(in.next()));
            }
            in.close();
            return stek;
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Red<String> queueStrings = new Red<String>();
        queueStrings = loadStrings("r1.txt");

        System.out.println(queueStrings);

        if (!queueStrings.jePrazan()) {
            while (!queueStrings.jePrazan()) {
                if (queueStrings.prvi().length() % 2 == 0) {
                    queueStrings.izbaciPrvi();
                } else {
                    break;
                }
            }
        }

        System.out.println(queueStrings);

        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the name of the file: ");
            String output = in.nextLine();
            output += ".txt";
            FileWriter fw = new FileWriter(output);
            if (!queueStrings.jePrazan()) {
                while (!queueStrings.jePrazan()) {
                    fw.write(queueStrings.izbaciPrvi());
                    fw.write(System.lineSeparator());
                }
            }
            in.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        Stek<Integer> firstStackNumbers = new Stek<Integer>();
        firstStackNumbers = loadNumbers("p1.txt");
        Stek<Integer> secondStackNumbers = new Stek<Integer>();
        secondStackNumbers = loadNumbers("p2.txt");

        System.out.println();
        System.out.println(firstStackNumbers);

        if (!firstStackNumbers.jePrazan()) {
            while (!firstStackNumbers.jePrazan()) {
                if (firstStackNumbers.vrh() % 10 == 6)
                    firstStackNumbers.skiniVrh();
                else
                    break;
            }
        }

        System.out.println(firstStackNumbers);

        System.out.println();
        System.out.println(secondStackNumbers);

        if (!secondStackNumbers.jePrazan()) {
            while (!secondStackNumbers.jePrazan()) {
                int pom = secondStackNumbers.skiniVrh();
                if (pom <= secondStackNumbers.vrh()) {
                    secondStackNumbers.stavi(pom);
                    break;
                }
            }
        }

        System.out.println(secondStackNumbers);

        System.out.println();
        System.out.println(firstStackNumbers);
        System.out.println(secondStackNumbers);

        Stek<Integer> bothStacks = new Stek<Integer>();
        if (!firstStackNumbers.jePrazan() && !secondStackNumbers.jePrazan()) {
            while ((!firstStackNumbers.jePrazan() || !secondStackNumbers.jePrazan()) && !bothStacks.jePun()) {
				try {
				  bothStacks.stavi(firstStackNumbers.skiniVrh());
				}
				catch(IllegalStateException e) {
					continue;
				}
				try {
				  bothStacks.stavi(secondStackNumbers.skiniVrh());
				}
				catch(IllegalStateException e) {
				  continue;
				}
            }
        }
        
        System.out.println();
        System.out.println(bothStacks);

        try {
            String out = "pp.txt";
            FileWriter fw = new FileWriter(out);
            if (!bothStacks.jePrazan()) {
                while (!bothStacks.jePrazan()) {
                    Svetovid.out(out).println(bothStacks.skiniVrh());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
