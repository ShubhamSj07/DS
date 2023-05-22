import java.util.Scanner;

public class Ring {

    public static void main(String[] args) {

        int temp, i, j;
        char str[] = new char[10];
        Rr proc[] = new Rr[10];

        // object initialization
        for (i = 0; i < proc.length; i++)
            proc[i] = new Rr();

        // scanner used for getting input from console
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        int num = in.nextInt();

        // getting input from users
        for (i = 0; i < num; i++) {
            proc[i].index = i;
            System.out.println("Enter the id of process: ");
            proc[i].id = in.nextInt();
            proc[i].state = "active";
            proc[i].f = 0;
        }

        // sorting the processes based on ID
        for (i = 0; i < num - 1; i++) {
            for (j = 0; j < num - 1 - i; j++) {
                if (proc[j].id > proc[j + 1].id) {
                    temp = proc[j].id;
                    proc[j].id = proc[j + 1].id;
                    proc[j + 1].id = temp;
                }
            }
        }

        for (i = 0; i < num; i++) {
            System.out.print("  [" + i + "]" + " " + proc[i].id);
        }

        int init;
        int ch;
        int temp1;
        int temp2;
        int ch1;
        int arr[] = new int[10];

        proc[num - 1].state = "inactive";

        System.out.println("\nProcess " + proc[num - 1].id + " selected as coordinator");

        while (true) {
            System.out.println("\n1) Election 2) Quit");
            ch = in.nextInt();

            for (i = 0; i < num; i++) {
                proc[i].f = 0;
            }

            switch (ch) {
                case 1:
                    System.out.println("\nEnter the process number that initiates the election: ");
                    init = in.nextInt();
                    temp2 = init;
                    temp1 = init + 1;

                    i = 0;

                    while (temp2 != temp1) {
                        if ("active".equals(proc[temp1 % num].state) && proc[temp1 % num].f == 0) {
                            System.out.println("\nProcess " + proc[init].id + " sends a message to process "
                                    + proc[temp1 % num].id);
                            proc[temp1 % num].f = 1;
                            init = temp1 % num;
                            arr[i] = proc[temp1 % num].id;
                            i++;
                        }
                        temp1++;
                    }

                    System.out.println(
                            "\nProcess " + proc[init].id + " sends a message to process " + proc[temp1 % num].id);
                    arr[i] = proc[temp1 % num].id;
                    i++;
                    int max = -1;

                    // finding maximum for coordinator selection
                    for (j = 0; j < i; j++) {
                        if (max < arr[j]) {
                            max = arr[j];
                        }
                    }

                    // coordinator is found, then printing on the console
                    System.out.println("\nProcess " + max + " selected as coordinator");

                    for (i = 0; i < num; i++) {
                        if (proc[i].id == max) {
                            proc[i].state = "inactive";
                        }
                    }
                    break;
                case 2:
                    System.out.println("Program terminated.");
                    in.close();
                    return;
                default:
                    System.out.println("\nInvalid response.\n");
                    break;
            }
        }
    }
}

class Rr {
    public int index; // to store the index of the process
    public int id; // to store the id/name of the process
    public int f;
    String state; // indicates whether active or inactive state of node
}
