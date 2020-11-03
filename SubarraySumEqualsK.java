// one, my solution, enumeration
public int subarraySum(int[] nums, int k) {
    if (nums == null || nums.length == 0) return 0;
    int[] preSum = new int[nums.length + 1];
    preSum[0] = 0;
    int count = 0;
    for (int i = 1; i < preSum.length; ++i) {
        preSum[i] = preSum[i - 1] + nums[i - 1];
        for (int j = i - 1; j >= 0; --j) {
            if (preSum[i] - preSum[j] == k) count += 1;
        }
    }
    return count;
}

// two, space O(1)
public int subarraySum(int[] nums, int k) {
    int count = 0;
    for (int start = 0; start < nums.length; start++) {
        int sum=0;
        for (int end = start; end < nums.length; end++) {
            sum+=nums[end];
            if (sum == k)
                count++;
        }
    }
    return count;
}

// three, map
public int subarraySum(int[] nums, int k) {
    int count = 0, sum = 0;
    HashMap < Integer, Integer > map = new HashMap < > ();
    /*
    * map.put(0, 1); for the corner case
    * array :: 1 1
    * presum:: 1 2
    * k = 1
    * nums[0] == k, if we do not initialize the map,
    * we would omit nums[0]
    * */
    map.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        /*
        * why count += map.get(sum - k); instead of count ++; ?
        * array :: 3 4 7 -2 2 1 4 2
        * presum :: 3 7 14 12 14 15 19 21
        * k = 7, both index3-7 and index 5-7 could have subsum == 7
         * */
        // sum - preSum == k, then it count will change
        if (map.containsKey(sum - k)) count += map.get(sum - k);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
}rn count;
}