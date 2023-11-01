class Solution(object):
    def permuteUnique(self, nums):
        ans = []
        n = len(nums)
        visited = [False] * len(nums)

        nums.sort()

        def backtracking(nums, res):

            if len(res) == n:
                ans.append(res[:])
                return

            for i in range(len(nums)):

                if visited[i] or (i > 0 and nums[i] == nums[i - 1] and not visited[i - 1]):
                    continue

                res.append(nums[i])

                visited[i] = True
                backtracking(nums, res)

                visited[i] = False
                res.pop()

        backtracking(nums, [])
        return ans