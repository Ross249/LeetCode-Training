class Solution(object):
    def minSubArrayLen(self, target, nums):
        i, j, sum, res = 0, 0, 0, 1000000
        while j < len(nums):
            sum += nums[j]

            while sum >= target:
                res = min(res, j - i + 1)
                sum -= nums[i]
                i += 1

            j += 1

        return 0 if res == 1000000 else res