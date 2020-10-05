class Solution {
    public int maxSubArray(int[] nums) {
        int[] maxSubSum = new int[nums.length];
        maxSubSum[0] = nums[0];
        int ans = maxSubSum[0];
        for (int i = 1; i < nums.length; ++i) {
            maxSubSum[i] = nums[i] + (maxSubSum[i - 1] < 0 ? 0 : maxSubSum[i - 1]);
            if (ans < maxSubSum[i]) {
                ans = maxSubSum[i];
            }
        }
        return ans;
    }
}