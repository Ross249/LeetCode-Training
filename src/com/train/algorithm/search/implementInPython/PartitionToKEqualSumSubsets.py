class Solution(object):
    def canPartitionKSubsets(self, nums, k):
        total = sum(nums)
        if total % k:
            return False
        reqSum = total // k
        subSet = [0] * k
        sorted(nums,reverse=True)

        def recurse(i):
            if i == len(nums):
                return True
            for j in range(k):
                if subSet[j] + nums[i] <= reqSum:
                    subSet[j] += nums[i]

                    if recurse(i + 1):
                        return True

                    subSet[j] -= nums[i]

                    if subSet[j] == 0:
                        break

            return False

        return recurse(0)