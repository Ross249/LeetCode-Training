class Solution(object):
    def isLongPressedName(self, name, typed):
        i,j = 0,0
        n,m = len(name),len(typed)

        while j < m:
            if i < n and name[i] == typed[j]:
                i += 1
            elif j == 0 or typed[j] != typed[j - 1]:
                return False
            j += 1
        return i == n