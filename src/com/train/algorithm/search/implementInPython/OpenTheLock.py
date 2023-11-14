import collections


class Solution(object):
    def openLock(self, deadends, target):
        def findNeighbours(code):
            res = []
            for i in range(4):
                updatedDigit = str((int(code[i]) + 1) % 10)
                res.append(code[:i] + updatedDigit + code[i + 1:])

                updatedDigit = str((int(code[i]) - 1) % 10)
                res.append(code[:i] + updatedDigit + code[i + 1:])
            return res

        visited = set(deadends)
        if '0000' in visited:
            return -1
        q = collections.deque([('0000', 0)])
        while q:
            code, moves = q.popleft()
            if code == target:
                return moves
            for neigh in findNeighbours(code):
                if neigh not in visited:
                    q.append((neigh, moves + 1))
                    visited.add(neigh)
        return -1