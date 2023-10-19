class Solution(object):
    def numRescueBoats(self, people, limit):
        people.sort()
        l,r,res = 0, len(people) - 1,0
        while l <= r:
            if people[l] + people[r] <= limit:
                l += 1
                r -= 1
            else:
                r -= 1
            res += 1
        return res
