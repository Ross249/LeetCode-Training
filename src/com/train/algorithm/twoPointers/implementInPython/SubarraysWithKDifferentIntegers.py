class Solution(object):
    def atMostKDistinct(self, nums, k):
        hashmap = {}
        subarrays = 0
        start, end = 0, 0
        while end < len(nums):
            if nums[end] in hashmap:
                hashmap[nums[end]] += 1
            else:
                hashmap[nums[end]] = 1
            while len(hashmap) > k:
                hashmap[nums[start]] -= 1
                if hashmap[nums[start]] == 0:
                    del hashmap[nums[start]]
                start += 1
            subarrays += end - start + 1
            end += 1
        return subarrays
    def subarraysWithKDistinct(self, nums, k):
        return self.atMostKDistinct(nums, k) - self.atMostKDistinct(nums, k - 1)
