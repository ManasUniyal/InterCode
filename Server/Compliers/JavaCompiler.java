package Compliers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class JavaCompiler {

    public void check() {

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(new File("C:\\Users\\Dell\\Desktop"));
        File outputFile = new File("C:\\Users\\Dell\\Desktop\\output.txt");
        File errorFile = new File("C:\\Users\\Dell\\Desktop\\error.txt");
        processBuilder.redirectOutput(outputFile);
        processBuilder.redirectError(errorFile);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("cmd.exe");
        arrayList.add("/c");
        arrayList.add("javac");
        arrayList.add("Hello.java");
        processBuilder.command(arrayList);
        Process process = null;
        try {
            process = processBuilder.start();
            process.waitFor();
            if(errorFile.length()==0)
            {
                arrayList.clear();
                arrayList.add("cmd.exe");
                arrayList.add("/c");
                arrayList.add("java");
                arrayList.add("Hello");
                processBuilder.command(arrayList);
                process = processBuilder.start();
                boolean status = process.waitFor(5, TimeUnit.SECONDS);
                if (status == true) {
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
                else {
                    System.out.println("TLE");
                    arrayList.clear();
                    arrayList.add("taskkill");
                    arrayList.add("/IM");
                    arrayList.add("java.exe");
                    arrayList.add("/F");
                    process = processBuilder.start();
                    process.waitFor();
                }
            }
            else {
                System.out.println("Error in compilation");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            outputFile.delete();
            errorFile.delete();
        }
    }
}
