class RateLimiter {

	private final maxTokens;
	private final long slice;
	
	private long lastAcquiredTime;
	private int numAvailable;
	
	
	public RateLimiter(int tokensPerSec) {
		this.maxTokens = tokensPerSec;
		this.slice = 1000L/tokensPerSec;
		this.lastAcquiredTime = System.currentMillis();
		this.numAvailable = 0;
	}
	
	public int acquire(int tokens) {
		synchronized(this) {
			update();
			if (numAvailable >= tokens) {
				numAvailable -= tokens;
				
				return tokens;
			}
		}
		
		return -1;
	}
	
	private void update() {
		long now = System.currentMillis();
		long elapsed = now - lastAcquiredTime;
		
		//Note: we need to check elpased > slice (smallest unit of time to get 1 token)
		// 	    Otherwise, if elapsed < slice, we will have 0 new tokens and we will keep resetting lastAcquiredTime 
		//      and actually cause to never get new tokens since the lastAcquireTime will never point to right time  when it last acquire token.
		if (elapsed > slice) {
			int numTokens = elapsed / slice;
			
			numAvailable = Math.min(numAvailable + numTokens, maxTokens);
			
			lastAcquiredTime = now;
		}
	}


	public static void main(String[] args) {
		RateLimiter rl = new RateLimiter(4);    //4 tokens per sec


		
	}
}
