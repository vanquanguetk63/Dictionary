package AppFx.Advanced;

import AppFx.Controller.Controller;
import base.Word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookMark extends InitDictionary {

    @Override
    public boolean addWordToDictionary(Word word) {
        if (!isExist(word)){
            this.getDictionary().getWordArrayList().add(word);
            this.saveWordToFile(word);
            return true;
        }
        return false;
    }

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
                data2 = data.split(" : ");
                Word word = new Word(data2[0], data2[1]);
                this.getDictionary().addWordToDictionary(word);
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("Can't read file");
            e.printStackTrace();
        }
    }

    @Override
    public void saveWordToFile(Word word) {
        try {
            String data = word.getWord_target() + " : " + word.getWord_explain() + "\n";

            String fileName = "src/BookMark.txt";

            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write(data);
            out.close();
        } catch (IOException e) {
            System.out.println("Cant write");
        }
    }

    @Override
    public Word dictionaryLookup(String findWord) {
        ArrayList<Word> wordArrayList = this.getDictionary().getWordArrayList();

        for (Word word : wordArrayList) {
            word.setWord_target(word.getWord_target().toLowerCase());
            if (word.getWord_target().compareTo(findWord) == 0) {
                return word;
            }
        }
        return null;
    }

    public void removeWord(String remove) {
        remove = remove.toLowerCase();
        Word word = dictionaryLookup(remove);
        if (word != null) {
            this.getDictionary().getWordArrayList().remove(word);
            System.out.println("Xoa thanh cong");
        } else {
            System.out.println("Can not found ");
        }
    }

}
