class Solution(object):
    def majorityElement(self, nums):
        count = 0
        res = 0
        for num in nums:
            if count == 0 and res != num:
                res = num
                count += 1
            elif res == num:
                count += 1
            else:
                count -= 1
        return  res