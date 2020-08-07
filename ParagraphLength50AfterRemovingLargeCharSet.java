import java.util.*;
import java.util.stream.Collectors;

/*

Problem Statement:
If you want to jumpstart the process of talking to us about this role, here’s a little challenge: write a program that outputs the largest unique set of characters that can be removed from this paragraph without letting its length drop below 50. For example: [‘H’, ‘i’, ‘!’, ‘ ‘]

Assumptions:
-- If shorter string is sent, we return null
-- We assume that it's case sensitive
-- Space is counted as character
-- Assuming that we will remove largest character set first and continue in descending order and when we reach length 50, we stop and if removing certain character set gets the value below 50, we move on to the next character with lower count to see if its removal satisfies the condition
*/

class Solution {
  public static void main(String[] args) {
    String paragraph = "If you want to jumpstart the process of talking to us about this role, here’s a little challenge: write a program that outputs the largest unique set of characters that can be removed from this paragraph without letting its length drop below 50. For example: [‘H’, ‘i’, ‘!’, ‘ ‘]";
    System.out.println("Answer is: " + getUniqueRemovedCharacters(paragraph));
  }

  public static List<Character> getUniqueRemovedCharacters(String paragraph) {
  
    HashMap<Character, Integer> characterCount = new HashMap<>();
    
    List<Character> uniqueCharacters = new ArrayList<>();
    int Remaininglength = paragraph.length();
    if(paragraph == null || paragraph.length() < 50) {
      return uniqueCharacters;
    }
    for(int i=0; i<paragraph.length(); i++) {
      if(characterCount.containsKey(paragraph.charAt(i))) {
        int oldCount = characterCount.get(paragraph.charAt(i));
        int newCount = oldCount + 1;
        characterCount.put(paragraph.charAt(i), newCount);
      } else {
        characterCount.put(paragraph.charAt(i), 1);
      }
    }
    
    Map<Character, Integer> sortedByCharOccurance = characterCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    
    Iterator mapIterator = sortedByCharOccurance.keySet().iterator();
    while(mapIterator.hasNext()) {
      char key = (char)mapIterator.next();
      int charCount = (int) sortedByCharOccurance.get(key);
      if(Remaininglength - charCount >= 50) {
        uniqueCharacters.add(key);
        Remaininglength -= charCount;
      } else {
        continue;
      }
    }
    System.out.println("Removed unique character count: " + uniqueCharacters.size());

    System.out.println("Remaining paragraph length: " + Remaininglength);

    return uniqueCharacters;
  }
}
