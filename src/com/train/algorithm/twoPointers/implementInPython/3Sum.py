class Solution(object):
    def threeSum(self, nums):
        target = 0
        nums.sort()
        s = set()
        res = []
        for i in range (len(nums)):
            j,k = i + 1, len(nums)-1
            while j < k:
                sum = nums[i] + nums[j] + nums[k]
                if sum == target:
                    s.add((nums[i],nums[j],nums[k]))
                    j += 1
                    k -= 1
                elif sum <= target:
                    j += 1
                else:
                    k -= 1

        res = list(s)
        return res