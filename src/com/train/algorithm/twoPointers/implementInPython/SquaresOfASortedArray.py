class Solution(object):
    def sortedSquares(self, nums):
        l,r = 0, len(nums)-1
        res = []
        while l <= r:
            if abs(nums[l]) > abs(nums[r]):
               res.append(nums[l]**2)
               l += 1
            else:
                res.append(nums[r]**2)
                r -= 1

        return res[::-1]