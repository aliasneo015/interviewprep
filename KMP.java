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

        
        /*
        Source: KMP Wikipedia (https://www.wikiwand.com/en/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm)
            algorithm kmp_search:
                input:
                    an array of characters, S (the text to be searched)
                    an array of characters, W (the word sought)
                output:
                    an array of integers, P (positions in S at which W is found)
                    an integer, nP (number of positions)
            
                define variables:
                    an integer, j ← 0 (the position of the current character in S)
                    an integer, k ← 0 (the position of the current character in W)
                    an array of integers, T (the table, computed elsewhere)
            
                let nP ← 0
            
                while j < length(S) do
                    if W[k] = S[j] then
                        let j ← j + 1
                        let k ← k + 1
                        if k = length(W) then
                            (occurrence found, if only first occurrence is needed, m may be returned here)
                            let P[nP] ← j - k, nP ← nP + 1
                            let k ← T[k] (T[length(W)] can't be -1)
                    else
                        let k ← T[k]
                        if k < 0 then
                            let j ← j + 1
                            let k ← k + 1
        */
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
        
        /*
         algorithm kmp_table:
            input:
                an array of characters, W (the word to be analyzed)
                an array of integers, T (the table to be filled)
            output:
                nothing (but during operation, it populates the table)
        
            define variables:
                an integer, pos ← 1 (the current position we are computing in T)
                an integer, cnd ← 0 (the zero-based index in W of the next character of the current candidate substring)
        
            let T[0] ← -1
        
            while pos < length(W) do
                if W[pos] = W[cnd] then
                    let T[pos] ← T[cnd], pos ← pos + 1, cnd ← cnd + 1
                else
                    let T[pos] ← cnd
        
                    let cnd ← T[cnd] (to increase performance)
        
                    while cnd >= 0 and W[pos] <> W[cnd] do
                        let cnd ← T[cnd]
        
                    let pos ← pos + 1, cnd ← cnd + 1
        
            let T[pos] ← cnd (only need when all word occurrences searched)
        */
        public int[] buildLookup(String pat) {
            int N = pat.length();
            int[] table = new int[N];
            
            table[0] = -1;
            int pos = 1;
            int start = 0;
            while(pos < N) {
                if (pat.charAt(pos) == pat.charAt(start)) {
                    table[pos] = table[start];
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

