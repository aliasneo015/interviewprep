public class Search
{
    public static void main(String[] args) {
        System.out.println("Hello World");
        Tests test = new Tests();
        test.test_binary_search();
    }
    
    public static class Tests {
        private Search search = new Search();
        
        public void test_binary_search() {
            int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int toSearch = 8;
            int found = search.binSearch(nums, toSearch);
            System.out.println("found " + toSearch + " at: " + found);
        }
    }

    public static class Search {
        public int binSearch(int[] nums, int k) {
            int l = 0;
            int h = nums.length - 1;
            while(l<=h) {
                int m = (l+h)/2;
                
                if (nums[m] < k) {
                    l = m + 1;
                }
                else if (nums[m] > k) {
                    h = m - 1;
                }
                else {
                    return m;
                }
            }
            
            return -1;
        }
    
        //
        //Java 8 Arrays.binarySearch:
        //
        /**
         * Searches a range of
         * the specified array of longs for the specified value using the
         * binary search algorithm.
         * The range must be sorted (as
         * by the {@link #sort(long[], int, int)} method)
         * prior to making this call.  If it
         * is not sorted, the results are undefined.  If the range contains
         * multiple elements with the specified value, there is no guarantee which
         * one will be found.
         *
         * @param a the array to be searched
         * @param fromIndex the index of the first element (inclusive) to be
         *          searched
         * @param toIndex the index of the last element (exclusive) to be searched
         * @param key the value to be searched for
         * @return index of the search key, if it is contained in the array
         *         within the specified range;
         *         otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
         *         <i>insertion point</i> is defined as the point at which the
         *         key would be inserted into the array: the index of the first
         *         element in the range greater than the key,
         *         or <tt>toIndex</tt> if all
         *         elements in the range are less than the specified key.  Note
         *         that this guarantees that the return value will be &gt;= 0 if
         *         and only if the key is found.
         * @throws IllegalArgumentException
         *         if {@code fromIndex > toIndex}
         * @throws ArrayIndexOutOfBoundsException
         *         if {@code fromIndex < 0 or toIndex > a.length}
         * @since 1.6
         */
        public static int binarySearch(long[] a, int fromIndex, int toIndex,
                                       long key) {
            rangeCheck(a.length, fromIndex, toIndex);
            return binarySearch0(a, fromIndex, toIndex, key);
        }
    
        // Like public version, but without range checks.
        private static int binarySearch0(long[] a, int fromIndex, int toIndex,
                                         long key) {
            int low = fromIndex;
            int high = toIndex - 1;
    
            while (low <= high) {
                int mid = (low + high) >>> 1;
                long midVal = a[mid];
    
                if (midVal < key)
                    low = mid + 1;
                else if (midVal > key)
                    high = mid - 1;
                else
                    return mid; // key found
            }
            return -(low + 1);  // key not found.
        }

    }
}
