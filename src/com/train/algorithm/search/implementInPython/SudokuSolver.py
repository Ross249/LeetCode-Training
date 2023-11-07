class Solution(object):
    def solveSudoku(self, board):
        n = 9
        def isValid(r, c, char):
            r, c = int(r), int(c)
            for i in range(9):
                if board[i][c] == char:
                    return False
                if board[r][i] == char:
                    return False
                if board[3 * (r//3)+i//3][3*(c//3)+i%3] == char:
                    return False
            return True

        def solve(r, c):
            if r == n:
                return True
            if c == n:
                return solve(r + 1, 0)
            if board[r][c] == ".":
                for i in range(1, 10):
                    if isValid(r, c, str(i)):
                        board[r][c] = str(i)

                        if solve(r, c + 1):
                            return True
                        else:
                            board[r][c] = "."
                return False
            else:
                return solve(r, c + 1)

        solve(0,0)