package base;


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

    public void insertionSort() {
        for (int i = 0; i < this.getWordArrayList().size(); i++) {
            for (int j = i; j > 0; j--) {
                Word word1 = this.getWordArrayList().get(j);
                Word word2 = this.getWordArrayList().get(j - 1);
                if (word1.getWord_target().compareTo(word2.getWord_target()) < 0) {
                    Word swapWord = wordArrayList.get(j);
                    wordArrayList.set(j, wordArrayList.get(j - 1));
                    wordArrayList.set(j - 1, swapWord);
                }
            }
        }
    }


    public void sortWord() {
        insertionSort();
    }

    public ArrayList<Word> getWordArrayList() {
        return wordArrayList;
    }

    public void setWordArrayList(ArrayList<Word> wordArrayList) {
        this.wordArrayList = wordArrayList;
    }
}
