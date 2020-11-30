import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    private static final String LINE_FEED = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordCountList = calculateWordFrequencies(sentence);
            sortWordCountList(wordCountList);
            return buildWordFrequencyResult(wordCountList);

        } catch (Exception exception) {
            return CALCULATE_ERROR;

        }
    }

    private void sortWordCountList(List<WordFrequency> wordCountList) {
        wordCountList.sort((word1, word2) -> word2.getCount() - word1.getCount());
    }

    private String buildWordFrequencyResult(List<WordFrequency> wordCountList) {
        return wordCountList.stream().map(this::buildWordFrequencyLine).collect(Collectors.joining(LINE_FEED));
    }

    private List<WordFrequency> calculateWordFrequencies(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_REGEX));

        HashSet<String> distinctWords = new HashSet<>(words);

        return distinctWords.stream().map(word -> new WordFrequency(word, Collections.frequency(words, word))).collect(Collectors.toList());
    }

    private String buildWordFrequencyLine(WordFrequency word) {
        return String.format("%s %d", word.getWord(), word.getCount());
    }

}
