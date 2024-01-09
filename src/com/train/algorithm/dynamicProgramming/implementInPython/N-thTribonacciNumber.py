class Solution(object):
    def tribonacci(self, n):
        if n == 0: return n
        t0, t1, t2 = 0, 1,1
        t = 1
        for i in range(3, n+1):
            t = t0 + t1 + t2
            t0 = t1
            t1 = t2
            t2 = t
        return t