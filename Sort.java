public class Main
{
    public static void main(String[] args) {
        System.out.println("Hello World");
        Tests test = new Tests();
        test.test_merge_sort();
    }
    
    static void printArr(int[] nums, String msg) {
        System.out.print(msg + " [ ");
        for(int i : nums) {
            System.out.print(i + "   ");
        }
        System.out.print(" ]\n");
    }
    
    public static class Tests {
        public void test_merge_sort() {
            int[] nums = {9, 2, 4, 6, 1, 3, 7, 8, 10};
            Sort sort = new Sort();
            sort.mergeSort(nums);
            printArr(nums, "Sorted nums: ");
        }
    }

    public static class Sort {
        public void mergeSort(int[] nums) {
            if (nums == null || nums.length ==0) {
                return;
            }
     
            int N = nums.length;
            int[] helper = new int[N];
            mergeSort(0, N-1, nums, helper);
        }
        
        private void mergeSort(int l, int h, int[] nums, int[] helper) {
            if (h<=l) {
                return;
            }
            
            int m = l + (h-l)/2;
            mergeSort(l, m, nums, helper);
            mergeSort(m+1, h, nums, helper);
            merge(l, m, h, nums, helper);
        }
        
        private void merge(int l, int m, int h, int[] nums, int[] helper) {
            //copy from l to h into helper. we will merge the orders set into nums
            for(int i=l; i<=h ; ++i) {
                helper[i] = nums[i];
            }
            
            int left = l;
            int right = m+1;
            int pos = l;
            
            //merge sorted 
            while(left <= m && right <= h) {
                if (helper[left] < helper[right]) {
                    nums[pos++] = helper[left++];
                }
                else {
                    nums[pos++] = helper[right++];
                }
            }
            
            //now copy remaining in left (if any). we do not need to see right, since it is already 
            // at the right most end 
            while(left <= m) {
                nums[pos++] = helper[left++];
            }
        }
    }
}
