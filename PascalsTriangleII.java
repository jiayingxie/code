// my solution
public List<Integer> getRow(int rowIndex) {
        int[] nums = new int[rowIndex + 1];
        nums[0] = 1;
        for (int i = 1; i <= rowIndex; ++i) {
            for (int j = i; j >= 1; --j) {
                nums[j] += nums[j - 1];
            }
        }
        List<Integer> ans = Arrays.stream(nums).boxed().collect(Collectors.toList());
        return ans;
}

// others
public class Solution {
    public List<Integer> getRow(int k) {
        Integer[] arr = new Integer[k + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;

        for (int i = 1; i <= k; i++)
            for (int j = i; j > 0; j--)
                arr[j] = arr[j] + arr[j - 1];

        return Arrays.asList(arr);
    }
}