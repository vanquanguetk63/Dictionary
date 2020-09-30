package AppFx.Advanced;

import AppFx.Controller.Controller;
import base.Word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BookMark extends InitDictionary {
    @Override
    public void insertFromFile() {
        try {
            System.out.println("insert BM");
            File file = new File("src/BookMark.txt");
            Scanner myReader = new Scanner(file);
            String data;
            String[] data2;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                data2 = data.split( " : " );
                Word word = new Word(data2[0], data2[1]);
                this.getDictionary().addWordToDictionary(word);
            }
            myReader.close();
        }
        catch (Exception e) {
            System.out.println("Can't read file");
            e.printStackTrace();
        }
    }

    @Override
    public void saveWordToFile(Word word) {
        try {
            String data = word.getWord_target() + " : " + word.getWord_explain() +"\n";

            String fileName = "src/BookMark.txt";

            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
