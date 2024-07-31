package org.example;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Helper{
    public static void main(String[] args) throws IOException, InterruptedException {
        APIprocesses apiProcesses;
        while(1<2){
            Scanner scanner = new Scanner(System.in);
            System.out.print("İlçe girin :");
            String ilceText = scanner.nextLine();
            ilceText = convertToUTF8(ilceText);
            System.out.print("İl girin   :");
            String ilText = scanner.nextLine();
            ilText = convertToUTF8(ilText);
            apiProcesses = new APIprocesses(ilceText,ilText);
            apiProcesses.getDutyPharmacyList();
        }
    }
}