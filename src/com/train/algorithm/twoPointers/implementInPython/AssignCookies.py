class Solution(object):
    def findContentChildren(self, g, s):
        g.sort()
        s.sort()
        i, j = 0, 0
        while i < len(g) and j < len(s):
            if(s[j] >= g[i]):
                i += 1
            j += 1
        return i