class Solution:
    def permute(self, nums):
        result = []
        self.helper([], nums, result)
        return result

    def helper(self, curr, nums, result):
        if len(curr) == len(nums):
            result.append(list(curr))
            return

        for num in nums:
            if num not in curr:
                curr.append(num)
                self.helper(curr, nums, result)
                curr.pop()
