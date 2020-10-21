package AppFx.Advanced;


import base.DictionaryManegement;
import base.Word;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class InitDictionary extends DictionaryManegement {
    public InitDictionary() {
    }

    public ArrayList<Word> searchWordFromFX(String searchWord) {
        ArrayList<Word> words = this.dictionarySearchAdvanced(searchWord);
        return words;
    }

    public boolean addWordToDictionary(Word word) {
        if (isExist(word) == false) {
            this.getDictionary().getWordArrayList().add(word);
            this.saveWordToFile(word);
            return true;
        }
        return false;
    }

    public boolean isExist(Word word) {
        ArrayList<Word> list = this.getDictionary().getWordArrayList();
        for (Word checkWord : list) {
            if (checkWord.getWord_target().compareTo(word.getWord_target()) == 0) {
                return true;
            }
        }
        return false;
    }

    public void saveWordToFile(Word word) {
        try {
            String data = word.getWord_target() + " @ " + word.getWord_explain() + "\n";

            String fileName = "src/main/java/resources/Dictionary.txt";

            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Word dictionaryLookup(String findWord) {
        return super.dictionaryLookup(findWord);
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
