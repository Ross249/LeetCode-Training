class Solution(object):
    def findMin(self, nums):
        n = len(nums)
        left, right = 0, n-1
        while left <= right:
            mid = (left + right)// 2
            if nums[mid] < nums[(mid-1+n)%n] and nums[mid] < nums[(mid+1)%n]:
                return nums[mid]
            elif nums[mid] > nums[right]:
                left = mid+1
            else:
                right = mid-1
        return nums[0]