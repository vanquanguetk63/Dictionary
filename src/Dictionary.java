import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> wordArrayList = new ArrayList<>();

    /**
     * Them tu vao tu dien.
     * @param word input word
     */
    public void addWordToDictionary(Word word) {
        int length = wordArrayList.size();
        wordArrayList.add(length, word);
    }

    public ArrayList<Word> getWordArrayList() {
        return wordArrayList;
    }

    public void setWordArrayList(ArrayList<Word> wordArrayList) {
        this.wordArrayList = wordArrayList;
    }
}
