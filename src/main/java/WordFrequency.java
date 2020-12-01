public class WordFrequency {
    private String word;
    private int count;

    public WordFrequency(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return this.word;
    }

    public int getCount() {
        return this.count;
    }

    public String buildWordFrequencyLine() {
        return String.format("%s %d", this.getWord(), this.getCount());
    }
}
