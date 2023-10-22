class Solution(object):
    def threeSumClosest(self, nums, target):
        n = len(nums)
        if n == 3:
            return sum(nums)
        nums = sorted(nums)
        minSum, minDif = None, float('inf')

        for i in range(n - 2):
            if i > 0 and nums[i] == nums[i - 1]:
                continue

            l, r = i + 1, n - 1

            while l < r:
                cSum = sum([nums[i], nums[l], nums[r]])
                cDif = abs(target - cSum)

                if cDif < minDif:
                    minDif = cDif
                    minSum = cSum

                    if minDif == 0:
                        return minSum

                if cSum < target:
                    l += 1
                    while l < r and nums[l] == nums[l - 1]:
                        l += 1
                else:
                    r -= 1
                    while l < r and nums[r] == nums[r + 1]:
                        r -= 1

        return minSum