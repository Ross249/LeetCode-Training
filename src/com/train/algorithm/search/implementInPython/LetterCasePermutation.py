class Solution(object):
    def letterCasePermutation(self, s):
        res = []

        def dfs(sub="", i=0):
            if len(sub) == len(s):
                res.append(sub[:])
                return
            for j in range(i, len(s)):
                if s[j].isdigit():
                    dfs(sub + s[j], j + 1)
                else:
                    dfs(sub + s[j].lower(), j + 1)
                    dfs(sub + s[j].upper(), j + 1)

        dfs()
        return res