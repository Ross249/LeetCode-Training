class Solution(object):
    def reverseOnlyLetters(self, s):
        list(s)
        start, end = 0, len(s) - 1
        while start < end:
            if s[start].isalpha() and s[end].isalpha():
                s[start], s[end] = s[end], s[start]
                start += 1
                end -= 1
            elif s[start].isalpha() and s[end].isalpha() == False:
                end -= 1
            elif s[start].isalpha() == False and s[end].isalpha():
                start += 1
            else:
                start += 1
                end -= 1
        return "".join(s)
