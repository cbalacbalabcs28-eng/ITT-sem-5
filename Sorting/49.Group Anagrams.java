import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> anagramMap = new HashMap<>();
        
        for (String str : strs) {
            // Count the frequency of each character (a-z)
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            // Build a unique string signature from the frequency counts
            // Example: "eat" -> "#1#0#0#0#1#0...#1...#0"
            StringBuilder sb = new StringBuilder();
            for (int val : count) {
                sb.append('#');
                sb.append(val);
            }
            String signatureKey = sb.toString();
            
            // Group strings using the built signature key
            if (!anagramMap.containsKey(signatureKey)) {
                anagramMap.put(signatureKey, new ArrayList<>());
            }
            anagramMap.get(signatureKey).add(str);
        }
        
        return new ArrayList<>(anagramMap.values());
    }
}
