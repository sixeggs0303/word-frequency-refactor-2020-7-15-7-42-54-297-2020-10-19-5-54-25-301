import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    private static final String LINE_FEED = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordCountList = calculateWordFrequencies(sentence);

            wordCountList.sort((word1, word2) -> word2.getCount() - word1.getCount());

            return buildWordFrequencyResult(wordCountList);
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private String buildWordFrequencyResult(List<WordFrequency> wordCountList) {
        StringJoiner wordFrequencyResult = new StringJoiner(LINE_FEED);

        for (WordFrequency word : wordCountList) {
            wordFrequencyResult.add(buildWordFrequencyLine(word));
        }

        return wordFrequencyResult.toString();
    }

    private List<WordFrequency> calculateWordFrequencies(String sentence) {
        //split the input string with 1 to n pieces of spaces
        List<String> words = Arrays.asList(sentence.split(SPACE_REGEX));

        HashSet<String> distinctWords = new HashSet<>(words);

        return distinctWords.stream().map(word -> new WordFrequency(word, Collections.frequency(words, word))).collect(Collectors.toList());
    }

    private String buildWordFrequencyLine(WordFrequency word) {
        return String.format("%s %d", word.getWord(), word.getCount());
    }

}
