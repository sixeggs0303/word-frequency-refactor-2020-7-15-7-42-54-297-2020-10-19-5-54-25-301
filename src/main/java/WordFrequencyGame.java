import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public String getResult(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<WordFrequency> wordFrequencyList = new ArrayList<>();

                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencyList.add(wordFrequency);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordCountMap = getWordCountMap(wordFrequencyList);

                List<WordFrequency> wordCountList = new ArrayList<>();

                for (Map.Entry<String, List<WordFrequency>> entry : wordCountMap.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    wordCountList.add(wordFrequency);
                }

                wordFrequencyList = wordCountList;

                wordFrequencyList.sort((w1, w2) -> w2.getCount() - w1.getCount());

                StringJoiner wordFrequencyResult = new StringJoiner("\n");

                for (WordFrequency word : wordFrequencyList) {
                    String wordFrequencyLine = word.getWord() + " " + word.getCount();
                    wordFrequencyResult.add(wordFrequencyLine);
                }

                return wordFrequencyResult.toString();
            } catch (Exception exception) {
                return "Calculate Error";
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
