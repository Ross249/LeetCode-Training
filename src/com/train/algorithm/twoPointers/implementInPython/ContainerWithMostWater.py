class Solution(object):
    def maxArea(self, height):
        i = 0
        n = len(height)
        j = n - 1
        area = 0
        while j > i:
            area = max(area, min(height[i], height[j]) * abs(i - j))
            if height[i] < height[j]:
                i += 1
            else:
                j -= 1

        return area
