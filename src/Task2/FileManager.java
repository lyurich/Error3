package Task2;
import java.io.*;

public class FileManager {

    private Console console;
    private FileOperator fileops;
    private boolean running = true;

    public FileManager(Console c) {
        this.console = c;
        this.fileops = new FileOperator(c);
    }

    public static void main(String[] args) throws InvalidFileException, IOException {
        FileManager app = new FileManager(new Console());
        int status = app.handleUserInput();
        java.lang.System.exit(status);
    }

    public Integer handleUserInput() throws InvalidFileException, IOException {
        this.sendIntro();
        String message = "Введите команду: ";

        while (this.running) {
            String str = console.promptForString(message);
            if (null == str || str.equals("")) {
                throw new InvalidFileException("Ошибка ввода!");
            } else if("help".equals(str)){
                this.sendIntro();
            } else {
                this.processCommand(str);
            }
        }
        console.sendMessage("Вы выходите из диспетчера файлов.");
        return 0;
    }

    private void processCommand(String command) throws InvalidFileException, IOException {
        switch (command) {



            case "информация":
                fileops.info(console.promptForString("Введите название файа: "));
                break;

            case "редактировать":
                fileops.redact(console.promptForString("Введите название файа: "));
                break;

            case "переименовать":
                this.renameFile(command);
                break;

            case "удалить":
                fileops.delete(console.promptForString("Введите название файла, кторый хотите удалить: "));
                break;
            case "выход":
                running = false;
                break;
            default:
                console.sendMessage("Введенная вами команда не существует!");
                break;
        }
    }


    private void renameFile(String str) throws InvalidFileException {
        String s1 = console.promptForString("Введите название файла, который хотите переименовать: ");
        File f = new File(s1);
        if(!f.exists()){
            throw new InvalidFileException("Фал не существует!");
        }
        String s2 = console.promptForString("Введите новое имя файла: ");
        fileops.rename(s1, s2);
    }


    private void sendIntro() {
        String commands = "Введите команду: \nинформация \nредактировать \nпереименовать \nудалить \nвыход\n";
        console.sendMessage(commands);
    }

    public void RedactFile(String str)throws InvalidFileException {
        String s1 = console.promptForString("Введите название файла, который хотите отредактировать: ");
        File f = new File(s1);
        if(!f.exists()){
            throw new InvalidFileException("Фал не существует!");
        }
        String s2 = console.promptForString("Введите новое имя файла: ");
    }
}

