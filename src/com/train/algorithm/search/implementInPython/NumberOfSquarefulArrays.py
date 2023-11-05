import collections


class Solution(object):
    def numSquarefulPerms(self, A):
        c = collections.Counter(A)
        cand = {i: {j for j in c if int((i + j)**0.5) ** 2 == i + j} for i in c}

        def dfs(x, left=len(A) - 1):
            c[x] -= 1
            count = sum(dfs(y, left - 1) for y in cand[x] if c[y]) if left else 1
            c[x] += 1
            return count
        return sum(map(dfs, c))