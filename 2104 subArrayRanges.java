class Solution {
    public long subArrayRanges(int[] nums) {
        long sum = 0;
        
        for(int i = 0; i < nums.length; i++) {
            long max = nums[i];
            long min = nums[i];

            for(int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                sum += max - min;
            }
        }

        return sum;
    }
}
