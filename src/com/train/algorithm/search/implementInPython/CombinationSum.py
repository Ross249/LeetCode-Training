class Solution(object):

    def dfs(self, nums, target, path, res):
        if target < 0:
            return
        if target == 0:
            res.append(path)
            return
        for i in range(len(nums)):
            self.dfs(nums[i:],target - nums[i],path + [nums[i]],res)

    def combinationSum(self, candidates, target):
        res = []

        self.dfs(candidates,target,[],res)

        return res
