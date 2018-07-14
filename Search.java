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
	}
}
