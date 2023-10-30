class Solution(object):
    def combinationSum3(self, k, n):
        max = 9
        def dfs(index, n, k, path, res):
            if len(path) == k and sum(path) == n:
                res.append(path[:])
                return
            for i in range(index, max + 1):
                path.append(i)
                dfs(i+1, n, k, path, res)
                path.pop()

        res = []
        dfs(1, n, k, [], res)
        return res