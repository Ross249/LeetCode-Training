class Solution(object):
    def dfs(self, cur, res, index, temp, count):
        if count > 4:
            return
        if count == 4 and index == len(cur):
            res.append(temp)
        for i in range(1, 4):
            if index + i > len(cur):
                break
            s = cur[index:index + i]
            if (s.startswith("0") and len(s) > 1) or (i == 3 and int(s) >= 256):
                continue
            self.dfs(cur, res, index + i, temp + s + ("." if count < 3 else ""), count + 1)
    def restoreIpAddresses(self, s):
        res = []
        self.dfs(s, res, 0, "", 0)
        return res
