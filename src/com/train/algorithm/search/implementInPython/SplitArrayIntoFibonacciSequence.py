class Solution(object):
    def splitIntoFibonacci(self, num):
        res = []
        def backtrack(num, res, index):
            if index == len(num) and len(res) >= 3:
                return True
            for i in range(index, len(num)):
                if num[index] == '0' and i > index:
                    break
                temp, sz = int(num[index: i + 1]), len(res)
                if temp >= 1 << 31:
                    break
                if sz >= 2 and temp > res[-1] + res[-2]:
                    break
                if sz <= 1 or temp == res[-1] + res[-2]:
                    res.append(temp)
                    if backtrack(num, res, i + 1):
                        return True
                    res.pop(-1)
            return False
        backtrack(num, res, 0)
        return res