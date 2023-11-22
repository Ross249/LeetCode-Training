import itertools


class Solution(object):
    def diffWaysToCompute(self, expression):
        ops = {'+': lambda x, y: x + y,
               '-': lambda x, y: x - y,
               '*': lambda x, y: x * y}
        def ways(s):
            res = []
            for i in range(len(s)):
                if s[i] in "-+*":
                    res += [ops[s[i]](l, r) for l, r in itertools.product(ways(s[0:i]), ways(s[i+1:]))]

            if not res: res.append(int(s))
            return res
        return ways(expression)