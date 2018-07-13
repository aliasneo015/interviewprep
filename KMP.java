import java.util.List;
import java.util.ArrayList;

public class KMP
{
    public static void printList(List<Integer> list, String msg) {
        System.out.print(msg + " [ ");
        for(int n: list) {
            System.out.print(n + "   ");
        }
        System.out.print(" ]");
    }

	public static void main(String[] args) {
        String S = "AABAACAADAABAABA";
        String pat = "AABA";
        
        KMP kmp = new KMP();
        int found = kmp.find(S, pat, 10);
        System.out.println("found at: " + found);
        
        List<Integer> foundList = kmp.findAll(S, pat);
        printList(foundList, "Found List: ");
    }
    
    public static class KMP {
        public int find(String S, String pat) {
            return find(S, pat, 0);
        }
        
        public List<Integer> findAll(String S, String pat) {
            List<Integer> res = new ArrayList<>();
            int found = 0;
            while (found != -1) {
                found = find(S, pat, found);
                if (found != -1) {
                    res.add(found);
                    found++;
                }
            }
            
            return res;
        }
        
        public int find(String S, String pat, int fromIndex) {
            int M = S.length();
            int N = pat.length();
            int[] lookup = buildLookup(pat);
            
            int i = fromIndex;
            int j = 0;
            while (i<M) {
                if (S.charAt(i) == pat.charAt(j)) {
                    i++;
                    j++;
                }
                else {
                    j = lookup[j];
                    if (j<0) {
                        j++;
                        i++;
                    }
                }
                
                if (j == N) {
                    return i - j;
                }
            }
            
            return -1;
        }
        
        public int[] buildLookup(String pat) {
            int N = pat.length();
            int[] table = new int[N];
            
            table[0] = -1;
            int pos = 1;
            int start = 0;
            while(pos < N) {
                if (pat.charAt(pos) == pat.charAt(start)) {
                    pos++;
                    start++;
                }
                else {
                    table[pos] = start;
                    
                    start = table[start];
                    while(start >= 0 && pat.charAt(pos) != pat.charAt(start)) {
                        start = table[start];
                    }
                    
                    pos++;
                    start++;
                }
            }
            
            return table;
        }
    }
}

