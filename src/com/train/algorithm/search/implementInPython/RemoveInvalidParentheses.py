class Solution(object):
    def removeInvalidParentheses(self, s):
        def getLeftAndRightCounts(s):
            l = 0
            r = 0

            for c in s:
                if c == '(':
                    l += 1
                elif c == ')':
                    if l == 0:
                        r += 1
                    else:
                        l -= 1

            return l, r

        def isValid(s):
            count = 0
            for c in s:
                if c == '(':
                    count += 1
                elif c == ')':
                    count -= 1
                if count < 0:
                    return False
            return True

        ans = []

        def dfs(s, start, l, r):
            if l == 0 and r == 0 and isValid(s):
                ans.append(s)
                return

            for i in range(start, len(s)):
                if i > start and s[i] == s[i - 1]:
                    continue
                if r > 0 and s[i] == ')':
                    dfs(s[:i]+s[i+1:], i, l, r - 1)
                elif l > 0 and s[i] == '(':
                    dfs(s[:i]+s[i+1:], i, l - 1, r)

        l, r = getLeftAndRightCounts(s)
        dfs(s, 0, l, r)
        return ans