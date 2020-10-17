package base;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManegement {
    Dictionary dictionary = new Dictionary();
    Scanner scanner = new Scanner(System.in);

    public DictionaryManegement() {
        this.insertFromFile();
    }

    public void insertFromCommandline() {
        System.out.println("New Console.Word EL");
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

    public void editWord(Word word, int index) {
        this.getDictionary().getWordArrayList().get(index).setWord_target(word.getWord_target());
        this.getDictionary().getWordArrayList().get(index).setWord_explain(word.getWord_explain());
    }

    public void removeWord(Word word) {
        if (word != null) {
            this.getDictionary().getWordArrayList().remove(word);

            System.out.println("Xoa thanh cong");
        }
        else {
            System.out.println("Can not found ");
        }
    }

    public void insertFromFile() {
        try {
            System.out.println("insert");
            File file = new File("src/main/resources/Dictionary.txt");
            Scanner myReader = new Scanner(file);
            int index = 0;
            String data;
            String[] data2;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                data2 = data.split( " @ " );
                Word word = new Word(data2[0], data2[1]);
                dictionary.addWordToDictionary(word);
            }
            myReader.close();
        }
        catch (Exception e) {
            System.out.println("Can't read file");
            e.printStackTrace();
        }
        dictionary.sortWord();
    }

    public void exportToFile(){
        try {
            FileWriter myFile = new FileWriter("src/main/resources/Dictionary.txt");
            int index = 0;
            while (! dictionary.getWordArrayList().isEmpty() && index < dictionary.getWordArrayList().size()) {
                Word word = dictionary.getWordArrayList().get(index);
                myFile.write(word.getWord_target() + " @ " + word.getWord_explain() + "\n");
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


    public ArrayList<String> searchIfNone(String str) {
        ArrayList<String> listNew = new ArrayList<>();
        ArrayList<Word> list = this.getDictionary().getWordArrayList();
        str = str.toLowerCase();
        for (Word word : list ) {
            String lower = word.getWord_target().toLowerCase();

            int m = str.length();
            int n = lower.length();

            if (n >= m -1  && n <= m + 2) {
                if (isSubSequence(str, lower, 0, m)) {
                    listNew.add(word.getWord_target());
                }
            }
        }

        return listNew;
    }

    public  boolean isSubSequence(String str1, String str2, int m, int n) {
        // Base Cases

        if (str1.length() == 2) {
            if (m == 2)
                return true;
        } else if (str1.length() > 2) {
            if (m > 2) return true;
        }
        if (n == 0 )
            return false;
        if (str2.contains(str1)) {
            return true;
        }

        // If last characters of two strings are matching
        char a = str1.charAt(m);
        String b = Character.toString(a);
        if (str2.contains(b)) {
//            if (m == 0) {
//                if (str2.charAt(0) != a) {
//                    return false;
//                }
//            }
            return isSubSequence(str1, str2, m+1 , n);
        }  else {
            return isSubSequence(str1, str2, m, n-1);
        }
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}

