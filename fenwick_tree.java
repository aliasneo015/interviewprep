public class MyClass {
    public static class BinaryIndexedTree {
        private int[] tree;
    
        public BinaryIndexedTree(int[] arr) {
            this.tree = new int[arr.length+1];
            tree[0] = 0;
            for(int i=0; i<arr.length; ++i) {
                update(i, arr[i]);
            }
        }
    
        /*
            Query sum from (fromIdx, toIdx)
        */
        public int sum(int fromIdx, int toIdx) {
            if (fromIdx < 0 || toIdx < 0 || fromIdx > tree.length || toIdx > tree.length) {
                throw new RunTimeException("invalid indices: " + formIdx + ", " + toIdx);
            }

            if (fromIdx > toIdx) {
                return sum(toIdx, fromIdx);
            }

            int sum1 = 0;
            if (fromIdx> 0) {
                sum1 = sum(fromIdx-1);
            }
            int sum2 = sum(toIdx);
    
            return sum2 - sum1;
        }
    
        /*
            Query sum from (0, idx)
        */
        public int sum(int toIdx) {
            int sum = 0;
            int idx = toIdx + 1;
            while(idx > 0) {
                sum += tree[idx];
                idx = getParent(idx);
            }
    
            return sum;
        }
    
        private void update(int i, int val) {
            int idx = i + 1;
            while (idx < tree.length) {
                tree[idx] += val;
                idx = getNext(idx);
            }
        }
        
         /*
           To get next
           1. 2's complement of index (since negative numbers are sotred in 2's complement, just -index gives 2's complement of index)
           2. AND this with index
           3. Add from index
        */
        private int getNext(int idx) {
            return idx + (idx & -idx);
        }
    
    
        /*
            To get parent:
            1. 2's complement of index (since negative numbers are sotred in 2's complement, just -index gives 2's complement of index)
            2. AND with index
            3. Subtract to index
        */
        private int getParent(int idx) {
            return idx - (idx & -idx);
        }
    }

    public static void main(String args[]) {
        int[] arr = {3,2,-1,6,5,4,-3,3,7,2,3};
        
        BinaryIndexedTree bit = new BinaryIndexedTree(arr);
        System.out.println("Sum = " + bit.sum(0, 5));
        
    }
}
