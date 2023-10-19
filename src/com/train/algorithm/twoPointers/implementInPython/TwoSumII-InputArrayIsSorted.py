class Solution(object):
    def twoSum(self, numbers, target):
        l,r = 0,len(numbers)-1
        while True:
            if numbers[l] + numbers[r] > target:
                r -= 1
            elif numbers[l] + numbers[r] < target:
                l += 1
            else:
                return [l + 1, r + 1]