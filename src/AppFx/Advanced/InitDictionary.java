package AppFx.Advanced;

import base.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class InitDictionary extends DictionaryManegement  {
    public InitDictionary() {
    }

    public ArrayList<Word> searchWordFromFX(String searchWord) {
        ArrayList<Word> words = this.dictionarySearchAdvanced(searchWord);
        return words;
    }

    public Word editWordFX(Word word, String target, String explain) {
        if (word != null) {
            word.setWord_target(target);
            word.setWord_explain(explain);
        }
        else {
            System.out.println("Can not Edit ");
        }
        return word;
    }

    public boolean addWordToDictionary(Word word) {
        if (isExist(word) == false){
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
            String data = word.getWord_target() + " : " + word.getWord_explain() +"\n";

            String fileName = "src/Dictionary.txt";

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
}
