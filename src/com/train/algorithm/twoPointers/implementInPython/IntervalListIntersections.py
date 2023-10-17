class Solution(object):
    def intervalIntersection(self, firstList, secondList):
        i,j = 0,0
        res = []
        while i < len(firstList) and j < len(secondList):
            startA,endA = firstList[i]
            startB,endB = secondList[j]
            if startA <= endB  and startB <= endA:
                res.append([max(startB,startA),min(endB,endA)])
            if endA <= endB:
                i += 1
            else:
                j += 1
        return res;