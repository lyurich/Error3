package Task2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class FileOperator {
    private Console cons;
    private char[] illegalchars = {92, 47, 58, 63, 42, 34, 60, 62, 124};
    private String illegalcharsstring = "\\ / : ? * \" < > |";

    public FileOperator(Console con) {
        this.cons = con;
    }


    public void info(String path) throws InvalidFileException {
        File path1 = new File(path);
        if(path1.exists()){
            cons.sendMessage("Название: " + path1.getName());
            cons.sendMessage("Абсолютный путь: " + path1.getAbsolutePath());
            cons.sendMessage("Относительный путь: " + path1.getPath());
            cons.sendMessage("Размер: " + path1.length());
            Path p = Paths.get(path);
            try {
                BasicFileAttributes bfa = Files.readAttributes(p, BasicFileAttributes.class);
                cons.sendMessage("Created: " + time(bfa.creationTime().toMillis()));
            } catch (IOException ex) {
                cons.sendMessage(ex.toString());
            }
            cons.sendMessage("Последнее изменение: " + time(path1.lastModified()));
        } else {
            throw new InvalidFileException("Введен неверный путь!");
        }
    }

    public String time(long l){
        Instant instant = Instant.ofEpochMilli(l);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
        return dateTime.format(dateTimeFormatter);
    }



    private boolean checkForIllegalChars(String s) {
        for (char kh : illegalchars) {
            if (s.indexOf(kh) >= 0) {
                return true;
            }
        }
        return false;
    }

    public void rename(String of, String nf) throws InvalidFileException {
        File oldFile = new File(of);
        String[] strings = of.split("\\\\+");
        int n = strings.length;
        strings[n-1] = nf;
        StringBuilder sb = new StringBuilder();
        sb.append(strings[0]);
        sb.append("\\");
        for(int i = 1; i <strings.length; i++){
            sb.append("\\");
            sb.append(strings[i]);
        }
        File newFile = new File(sb.toString());
        if(newFile.exists()){
            throw new InvalidFileException("Файл с таким именем уже существует!");
        }
        if(oldFile.renameTo(newFile)){
            cons.sendMessage("Переименование успешно.");
        } else {
            throw new InvalidFileException("Не удалось переименовать!");
        }
    }



    public void delete(String path){
        File file = new File(path);
        if(file.exists()){
            if(file.isFile()){
                file.delete();
                cons.sendMessage("Файл успешно удален!");
            }

        } else {
            cons.sendMessage("Невозможно удалить " + file.getName() + " потому что " + file.getName() + " не существует.");
        }
    }


    public void redact(String path) throws InvalidFileException, IOException {
        FileWriter writer = new FileWriter("s1");
        writer.write("Привет, мир!");
        writer.close();
    }

}