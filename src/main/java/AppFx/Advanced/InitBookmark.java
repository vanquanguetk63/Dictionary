package AppFx.Advanced;

import base.Word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InitBookmark extends InitDictionary {

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
            File file = new File("src/main/resources/Bookmark.txt");
            Scanner myReader = new Scanner(file);
            String data;
            String[] data2;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                data2 = data.split(" @ ");
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
            String data = word.getWord_target() + " @ " + word.getWord_explain() + "\n";

            String fileName = "src/main/resources/Bookmark.txt";

            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write(data);
            out.close();
        } catch (IOException e) {
            System.out.println("Cant write");
        }
    }

    public void exportToFileBM() {
        try {
            FileWriter myFile = new FileWriter("src/main/resources/Bookmark.txt");
            int index = 0;
            while (! this.getDictionary().getWordArrayList().isEmpty() && index < this.getDictionary().getWordArrayList().size()) {
                Word word = this.getDictionary().getWordArrayList().get(index);
                myFile.write(word.getWord_target() + " @ " + word.getWord_explain() + "\n");
                index++;
            }
            myFile.close();
        } catch (IOException e) {
            System.out.println("Error to Write");
            e.printStackTrace();
        }
    }

    @Override
    public Word dictionaryLookup(String findWord) {
        ArrayList<Word> wordArrayList = this.getDictionary().getWordArrayList();
        findWord = findWord.toLowerCase();
        for (Word word : wordArrayList) {
            String str = word.getWord_target().toLowerCase();
            if (str.compareTo(findWord) == 0) {
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

    public int indexOfWordBm(Word word) {
        ArrayList<Word> list = this.getDictionary().getWordArrayList();
        int index = 0;

        for (Word checkWord : list) {
            String str = checkWord.getWord_target().toLowerCase();
            if (str.compareTo(word.getWord_target().toLowerCase()) == 0) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public String[] initExplainWebView(String wordExplain) {
        String[] list = wordExplain.split("</>");
        String explain = "";
        for (int i = 1; i < list.length; i++) {
            explain += "<p>" + list[i] + "</p>";
        }
        list[1] = explain;
        return list;
    }

    public String[] initExplain(String wordExplain) {
        String[] list = wordExplain.split("</>");
        if (list.length > 2) {
            for (int i = 2; i < list.length; i++) {
                list[1] += "\n" + list[i];
            }
        }
        return list;
    }

}
