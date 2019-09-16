package Compliers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PythonCompiler {

    public void check(){

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(new File("C:\\Users\\Dell\\Desktop"));
        File errorFile = new File("C:\\Users\\Dell\\Desktop\\error.txt");
        File outputFile = new File("C:\\Users\\Dell\\Desktop\\output.txt");
        processBuilder.redirectError(errorFile);
        processBuilder.redirectOutput(outputFile);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("python");
        arrayList.add("hello1.py");
        processBuilder.command(arrayList);
        try {
            Process process = processBuilder.start();
            boolean isTimeLimitNotExceeded = process.waitFor(5, TimeUnit.SECONDS);
            if(errorFile.length() == 0) {   //Compiled correctly
                if (isTimeLimitNotExceeded == false) {
                    System.out.println("TLE");
                    arrayList.clear();
                    arrayList.add("taskkill");
                    arrayList.add("/IM");
                    arrayList.add("python.exe");
                    arrayList.add("/F");
                    process = processBuilder.start();
                    process.waitFor();

                } else {
                    ProcessBuilder processBuilderAnswerCheck = new ProcessBuilder();
                    arrayList.clear();
                    arrayList.add("cmd.exe");
                    arrayList.add("/c");
                    arrayList.add("fc");
                    arrayList.add("C:\\Users\\Dell\\Desktop\\test.txt");
                    arrayList.add("C:\\Users\\Dell\\Desktop\\output.txt");
                    processBuilderAnswerCheck.command(arrayList);
                    Process processAnswerCheck = processBuilderAnswerCheck.start();
                    processAnswerCheck.waitFor();
                    int cnt = 0;
                    String str;
                    BufferedReader br = new BufferedReader(new InputStreamReader(processAnswerCheck.getInputStream()));
                    while ((str = br.readLine()) != null) {
                        System.out.println(str);
                        cnt++;
                    }
                    System.out.println(cnt);
                    if (cnt == 3) {
                        System.out.println("AC");
                    } else {
                        System.out.println("WA");
                    }
                }
            }
            else {
                System.out.println("Compilation error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
