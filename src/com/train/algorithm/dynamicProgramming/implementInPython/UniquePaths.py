class Solution(object):
    def uniquePaths(self, m, n):
        matrix = [[0] * m for i in range(n)]
        matrix[0][0] = 1
        for i in range(1, m):
            matrix[0][i] = matrix[0][i-1]
        for j in range(1, n):
            matrix[j][0] = matrix[j-1][0]
        for i in range(1, n):
            for j in range(1, m):
                matrix[i][j] = matrix[i-1][j] + matrix[i][j - 1]
        return matrix[n-1][m-1]