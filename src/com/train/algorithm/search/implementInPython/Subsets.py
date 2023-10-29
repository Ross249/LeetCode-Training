class Solution(object):
    def dfs(self, nums, path, res):
        res.append(path)
        for i in range(len(nums)):
            self.dfs(nums[i+1:], path + [nums[i]], res)
    def subsets(self, nums):
        res = []
        self.dfs(nums, [], res)
        return res


        # def backtracking(start, path):
        #
        #     res.append(path[:])
        #
        #     for i in range(start, len(nums)):
        #
        #         path.append(nums[i])
        #
        #         backtracking(i+1, path)
        #
        #         path.pop()
        # res = []
        # backtracking(0, [])
        # return res