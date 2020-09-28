package AppFx.Advanced;

import base.*;

import java.util.ArrayList;


public class InitDictionary extends DictionaryManegement  {
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

    @Override
    public Word dictionaryLookup(String findWord) {
        return super.dictionaryLookup(findWord);
    }
}
