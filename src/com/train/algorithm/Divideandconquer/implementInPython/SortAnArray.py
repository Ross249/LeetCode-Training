class Solution(object):
    def sortArray(self, nums):
        def mergeTwoSortedArrays(a, b, res):
            i, j, k = 0, 0, 0
            while i < len(a) and j < len(b):
                if a[i] < b[j]:
                    res[k] = a[i]
                    i += 1
                else:
                    res[k] = b[j]
                    j += 1
                k+=1

            res[k:] = a[i:] if i < len(a) else b[j:]

        def mergesort(nums):
            if len(nums) == 1: return
            mid = len(nums)//2
            l = nums[:mid]
            r = nums[mid:]
            mergesort(l)
            mergesort(r)
            mergeTwoSortedArrays(l, r, nums)

        mergesort(nums)
        return nums