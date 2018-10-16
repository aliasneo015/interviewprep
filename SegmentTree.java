SegmentTree {
	int[] input;
	int[] segTree;
	
	//This is for Minimum Range Queries
	SegmentTree(int[] arr) {
		this.input = arr;
		
		int len = arr.length; 
		segTree = new int[nextPowerOf2(len) * 2 - 1];
		
		//Initialize segTree with Integer.MAX_VALUE so that the values 
		// unallocated will have max-val which is needed for Minimum queires.
		// Note: If it was a Max Range Queries Tree we would initialize with Integer.MIN_VALUE
		//       and for Sum Range Queries Tree , we would initialize with 0
		Arrays.fill(segTree, Integer.MAX_VALUE);
		
		constructTree(arr, segTree, 0, len-1, 0);
	}
	
	void constructTree(int[] input, int[] segTree, int low, int high, int pos) {
		if (low == high) {
			segTree[pos] = input[low];
			return;
		}
		
		int mid = (low + hight) / 2;
		constructTree(input, segTree, low, mid, pos * 2 + 1);
		constructTree(input, segTree, mid + 1, high, pos * 2 + 2);
		
		segTree[pos] = Math.min(segTree[pos * 2 + 1], segTree[pos * 2 + 2]);	//for Max Range Queries this would be Math.max(left, right)
																				//for Sum Range Queries this would be sum of (left, right)
	}
	
		
	int nextPowerOf2(int num){
        if(num ==0){
            return 1;
        }
        if(num > 0 && (num & (num-1)) == 0){
            return num;
        }
        while((num & (num-1)) > 0){
            num = num & (num-1);
        }
        return num<<1;
    }
	
	int rangeMinQuery(int[] segTree, int qLow, int qHigh, int low, int high, int pos) {
		//check total overlap of query with node range
		if (low >= qLow && hight <= qHigh) {
			return segTree[pos];
		}
		
		//no overlap?
		if (qLow > high || qHigh < low) {
			return Integer.MAX_VALUE;
		}
		
		int mid = (low + high) / 2;
		
		return Math.min( rangeMinQuery(segTree, qLow, qHigh, low, mid, 2 * pos + 1)
						, rangeMinQuery(segTree, qLow, qHigh, low, mid, 2 * pos + 2) );
	}
}
