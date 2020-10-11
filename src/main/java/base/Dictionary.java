package main.java.base;


import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> wordArrayList = new ArrayList<>();

    /**
     * Them tu vao tu dien.
     * @param word input word
     */
    public void addWordToDictionary(Word word) {
        int length = wordArrayList.size();
        if (isExist(word) == false){
            wordArrayList.add(length, word);
        }
    }

    public boolean isExist(Word word) {
        for (Word checkWord : wordArrayList) {
            if (checkWord.getWord_target().compareTo(word.getWord_target()) == 0) {
                return true;
            }
        }
        return false;
    }

    public void sortWord() {
        for (int i = 0;i < wordArrayList.size() - 1; i++) {
            for (int j = i + 1;j < wordArrayList.size(); j++) {
                int compareTwoWord = wordArrayList.get(i).getWord_target()
                                    .compareTo(wordArrayList.get(j).getWord_target());
                if (compareTwoWord > 0) {
                    Word swapWord = wordArrayList.get(j);
                    wordArrayList.set(j, wordArrayList.get(i));
                    wordArrayList.set(i, swapWord);
                }
            }
        }
    }

    public ArrayList<Word> getWordArrayList() {
        return wordArrayList;
    }

    public void setWordArrayList(ArrayList<Word> wordArrayList) {
        this.wordArrayList = wordArrayList;
    }
}
