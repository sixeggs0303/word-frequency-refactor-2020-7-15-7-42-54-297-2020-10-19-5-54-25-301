import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    private static final String LINE_FEED = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        if (sentence.split(SPACE_REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(SPACE_REGEX);

                List<WordFrequency> wordFrequencyList = new ArrayList<>();

                for (String word : words) {
                    wordFrequencyList.add(new WordFrequency(word, 1));
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordCountMap = getWordCountMap(wordFrequencyList);

                List<WordFrequency> wordCountList = new ArrayList<>();

                for (Map.Entry<String, List<WordFrequency>> entry : wordCountMap.entrySet()) {
                    wordCountList.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
                }

                wordCountList.sort((w1, w2) -> w2.getCount() - w1.getCount());

                StringJoiner wordFrequencyResult = new StringJoiner(LINE_FEED);

                for (WordFrequency word : wordCountList) {
                    String wordFrequencyLine = word.getWord() + " " + word.getCount();
                    wordFrequencyResult.add(wordFrequencyLine);
                }

                return wordFrequencyResult.toString();
            } catch (Exception exception) {
                return CALCULATE_ERROR;
            }
        }
    }


    private Map<String, List<WordFrequency>> getWordCountMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordCountMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordCountMap.containsKey(wordFrequency.getWord())) {
                ArrayList words = new ArrayList<>();
                words.add(wordFrequency);
                wordCountMap.put(wordFrequency.getWord(), words);
            } else {
                wordCountMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordCountMap;
    }


}
