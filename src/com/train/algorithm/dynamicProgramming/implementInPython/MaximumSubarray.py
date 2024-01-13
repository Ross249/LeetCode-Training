class Solution(object):
    def maxSubArray(self, nums):
        arr = []
        arr.append(nums[0])
        max_sum = arr[0]
        for i in range(1, len(nums)):
            arr.append(max(arr[i-1] + nums[i], nums[i]))
            if arr[i] > max_sum:
                max_sum = arr[i]

        return max_sum