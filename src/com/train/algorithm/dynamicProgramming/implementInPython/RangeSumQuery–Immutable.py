class NumArray(object):

    def __init__(self, nums):
        self.sums = [0]
        for n in nums:
            self.sums.append(self.sums[-1] + n)
        self.sums = self.sums[1:]


    def sumRange(self, left, right):
        if left == 0:
            return self.sums[right]
        else:
            return self.sums[right] - self.sums[left - 1]