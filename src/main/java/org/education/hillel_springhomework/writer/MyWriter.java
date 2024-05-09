package org.education.hillel_springhomework.writer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Component
public class MyWriter {

    @Value("${FolderPathWriter}")
    private String folderPath;

    private File createFile() {
        try {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void write(Date date, String methodName, Object[] args) {

        File file = new File(createFile(), methodName + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("Method Name: " + methodName);
            writer.newLine();

            writer.write("Data: " + date);
            writer.newLine();

            for (Object arg : args) {
                writer.write("Argument: " + arg);
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeException(String methodName, Exception exception ) {
        File file = new File(createFile(), methodName + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
             writer.write("Метод не был выполнен из-за ошибки: " + exception.getMessage());
             writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}





