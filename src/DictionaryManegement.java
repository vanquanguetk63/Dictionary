import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManegement {
    Dictionary dictionary = new Dictionary();
    Scanner scanner = new Scanner(System.in);
    DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

    public DictionaryManegement() {
        insertFromFile();
    }

    public void insertFromCommandline() {
        System.out.println("New Word EL");
        String word_target = scanner.nextLine();
        System.out.println("VietNamese");
        String word_explain = scanner.nextLine();
        Word word = dictionaryLookup(word_target);
        if (word == null) {
            word = new Word(word_target, word_explain);
            dictionary.addWordToDictionary(word);
        }
        else {
            System.out.println("Tu da ton tai");
        }
    }

    public void editWord(String edit) {
        Word word = dictionaryLookup(edit);
        if (word != null) {
            System.out.println("Nhap vao chuoi tu ban muon sua cach nhau boi dau cach");
            String[] editWord;
            String input = scanner.nextLine();
            editWord = input.split(" ");
            word.setWord_target(editWord[0]);
            word.setWord_explain(editWord[1]);
            dictionary.sortWord();
            System.out.println("Sua Thanh cong");
        }
        else {
            System.out.println("Can not found ");
        }
    }

    public void removeWord(String remove) {
        Word word = dictionaryLookup(remove);
        if (word != null) {
            dictionary.getWordArrayList().remove(word);
            System.out.println("Xoa thanh cong");
        }
        else {
            System.out.println("Can not found ");
        }
    }

    public void insertFromFile() {
        try {
            File file = new File("src/Dictionary.txt");
            Scanner myReader = new Scanner(file);
            int index = 0;
            String data;
            String[] data2;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                data2 = data.split("\t");
                Word word = new Word(data2[0], data2[1]);
                dictionary.addWordToDictionary(word);
            }
            myReader.close();
        }
        catch (Exception e) {
            System.out.println("Can't read file");
            e.printStackTrace();
        }
    }

    public void exportToFile(){
        try {
            FileWriter myFile = new FileWriter("src/Dictionary.txt");
            int index = 0;
            while (! dictionary.getWordArrayList().isEmpty() && index < dictionary.getWordArrayList().size()) {
                Word word = dictionary.getWordArrayList().get(index);
                myFile.write(word.getWord_target() + "\t" + word.getWord_explain() + "\n");
                index++;
            }
            myFile.close();
        } catch (IOException e) {
            System.out.println("Error to Write");
            e.printStackTrace();
        }
    }
    public int binarySearchIndex(ArrayList<Word> words, int first, int last, String findWord) {
        if (last >= first) {
            int mid = first + (last - first) / 2;
            String wordOfList = words.get(mid).getWord_target().toLowerCase();
            int compareTwoStr = wordOfList.compareTo(findWord);
            if (compareTwoStr == 0 ) {
                return mid;
            }
            if (compareTwoStr > 0)
                return binarySearchIndex(words, first, mid - 1, findWord);

            return binarySearchIndex(words, mid + 1, last, findWord);
        }
        return -1;
    }

    public Word dictionaryLookup(String findWord) {
        findWord = findWord.toLowerCase();
        Word word;
        int indexOfWord = binarySearchIndex(dictionary.getWordArrayList(), 0, dictionary.getWordArrayList().size() - 1, findWord);
        if (indexOfWord != -1) {
            word = dictionary.getWordArrayList().get(indexOfWord);
        } else {
            return null;
        }
        return word;
    }

    public ArrayList<Word> dictionarySearchAdvanced(String searchWord) {
        ArrayList<Word> list = new ArrayList<>();
        searchWord = searchWord.toLowerCase();
        for (Word word : dictionary.getWordArrayList()) {
            String wordTarget = word.getWord_target();
            wordTarget = wordTarget.toLowerCase();
            if (wordTarget.startsWith(searchWord)) {
                list.add(word);
            }
        }
        return list;
    }
}

