class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        
        while(l <= r) {
            int mid = l + (r - l) / 2;

            if(nums[mid] > target)
                r = mid - 1;

            if(nums[mid] < target)
                l = mid + 1;
            
            if(nums[mid] == target)
                return mid;
        }

        return l;
    }
}
