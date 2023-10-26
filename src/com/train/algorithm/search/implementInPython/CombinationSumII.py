class Solution(object):
    def dfs(self, index, target, arr, res, path):
        if target == 0:
            res.append(path[:])
            return
        for i in range(index, len(arr)):
            if i > index and arr[i] == arr[i-1]:
                continue
            if arr[i] > target:
                break
            path.append(arr[i])
            self.dfs(i + 1, target - arr[i], arr, res, path)
            path.pop()

    def combinationSum2(self, candidates, target):
        candidates.sort()
        res = []
        path = []
        self.dfs(0, target, candidates, res, path)
        return res