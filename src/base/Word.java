package base;

public class Word {
    private String word_target;
    private String word_explain;

    public Word() {
        this.word_target = "";
        this.word_explain = "";
    }

    public static String capitalize(String str) {
        str = str.toLowerCase();
        if(str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public Word(String word_target, String word_explain) {
        word_target = capitalize(word_target);
        word_explain = capitalize(word_explain);
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }
}

