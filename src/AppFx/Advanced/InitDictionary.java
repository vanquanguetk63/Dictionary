package AppFx.Advanced;

import base.*;

import java.util.ArrayList;


public class InitDictionary extends DictionaryManegement  {
    private boolean update = false;
    public InitDictionary() {
        insertFromFile();
    }

    public ArrayList<String> searchWordFromFX(String searchWord) {
        ArrayList<Word> words = this.dictionarySearchAdvanced(searchWord);
        ArrayList<String> results = new ArrayList<>();

        for (Word word : words) {
            results.add(word.getWord_target());
        }

        return results;
    }

    public boolean addWordToDictionary(Word word) {
        ArrayList<Word> list = this.getDictionary().getWordArrayList();
        int length = list.size();
        if (isExist(word) == false){
            update = true;
            list.add(length, word);
            this.getDictionary().setWordArrayList(list);
            this.getDictionary().sortWord();
            this.exportToFile();
            return true;
        }
        update = false;
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

    @Override
    public Word dictionaryLookup(String findWord) {
        return super.dictionaryLookup(findWord);
    }
}
