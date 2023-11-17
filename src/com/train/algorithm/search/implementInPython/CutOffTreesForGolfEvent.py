# Min Heap + BFS
import collections
import heapq


class Solution:
    # 2. use BFS get min distance for tree to tree
    def bfsGetMinSteps(self, forest, currR, currC, targetR, targetC):
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        m, n = len(forest), len(forest[0])
        q = collections.deque([(currR, currC)])
        visited = set([(currR, currC)])
        steps = 0

        while q:
            sameLevelCounts = len(q)
            for _ in range(sameLevelCounts):
                r, c = q.popleft()
                if r == targetR and c == targetC:
                    return steps
                for dr, dc in directions:
                    nextR, nextC = r + dr, c + dc
                    if 0 <= nextR < m and 0 <= nextC < n and (nextR, nextC) not in visited and forest[nextR][
                        nextC] != 0:
                        visited.add((nextR, nextC))
                        q.append((nextR, nextC))
            steps += 1
        return -1

    def cutOffTree(self, forest):
        # 1. use min heap to sort the tree by height
        heap = []
        for r in range(len(forest)):
            for c in range(len(forest[0])):
                if forest[r][c] > 1:
                    heapq.heappush(heap, (forest[r][c], r, c))

        totalSteps, currR, currC = 0, 0, 0
        while heap:
            height, r, c = heapq.heappop(heap)
            steps = self.bfsGetMinSteps(forest, currR, currC, r, c)
            if steps == -1:
                return -1
            currR, currC = r, c
            totalSteps += steps
        return totalSteps

