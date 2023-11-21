class Solution(object):
    def check(self, str, start, end):
        while start <= end:
            if str[start] != str[end]:
                return False
            start += 1
            end -= 1
        return True

    def dfs(self, index, ds, res, str):
        if index == len(str):
            res.append(ds[:])
            return
        for i in range(index, len(str)):
            if self.check(str, index, i):
                ds.append(str[index:i + 1])
                self.dfs(i + 1, ds, res, str)
                ds.pop()

    def partition(self, s):
        res = []
        ds = []
        self.dfs(0, ds, res, s)
        return res