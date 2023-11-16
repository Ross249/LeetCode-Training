class Solution(object):
    def updateMatrix(self, mat):
        n = len(mat)
        m = len(mat[0])
        delrow = [-1,0,1,0]
        delcol = [0,-1,0,1]
        def isValid(row, col):
            return (row >=0 and row < n and col >= 0 and col < m)

        visited = [[False for _ in range(m)] for _ in range(n)]
        bfsQueue = []

        res = [[-1 for _ in range(m)] for _ in range(n)]

        for i in range(n):
            for j in range(m):
                if mat[i][j] == 0:
                    visited[i][j] = True
                    res[i][j] = 0
                    bfsQueue.append((i, j))

        while bfsQueue:
            row, col = bfsQueue.pop(0)
            dist = res[row][col]

            for i in range(4):
                nrow = row + delrow[i]
                ncol = col + delcol[i]
                if isValid(nrow,ncol) and not visited[nrow][ncol]:
                    visited[nrow][ncol] = True
                    res[nrow][ncol] = dist + 1
                    bfsQueue.append(((nrow, ncol)))

        return res

