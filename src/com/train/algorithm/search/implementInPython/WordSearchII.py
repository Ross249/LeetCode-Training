class Solution:
    def findWords(self, board, words):
        root = {}
        for w in words:
            node = root
            for l in w:
                if l not in node:
                    node[l] = {}
                node = node[l]
            node['$'] = {}

        def remove(w):
            node = root
            stack = []
            stack.append(node)
            for l in w:
                node = node[l]
                stack.append(node)
            node = stack.pop()
            node.pop('$')
            while stack:
                node = stack.pop()
                if '$' in node or len(node) > 1:
                    return
                k = list(node.keys())[0]
                if len(node[k]) == 0:
                    node.pop(k)
                else:
                    return

        def explore(i, j, s, node, seen):
            if '$' in node:
                ans.append("".join(s))
                remove("".join(s))

            if i > 0 and board[i - 1][j] in node and (i - 1, j) not in seen:
                s.append(board[i - 1][j])
                seen.add((i - 1, j))
                explore(i - 1, j, s, node[board[i - 1][j]], seen)
                s.pop()
                seen.remove((i - 1, j))

            if i < len(board) - 1 and board[i + 1][j] in node and (i + 1, j) not in seen:
                s.append(board[i + 1][j])
                seen.add((i + 1, j))
                explore(i + 1, j, s, node[board[i + 1][j]], seen)
                s.pop()
                seen.remove((i + 1, j))

            if j > 0 and board[i][j - 1] in node and (i, j - 1) not in seen:
                s.append(board[i][j - 1])
                seen.add((i, j - 1))
                explore(i, j - 1, s, node[board[i][j - 1]], seen)
                s.pop()
                seen.remove((i, j - 1))

            if j < len(board[0]) - 1 and board[i][j + 1] in node and (i, j + 1) not in seen:
                s.append(board[i][j + 1])
                seen.add((i, j + 1))
                explore(i, j + 1, s, node[board[i][j + 1]], seen)
                s.pop()
                seen.remove((i, j + 1))

        ans = []
        for i, row in enumerate(board):
            for j, val in enumerate(row):
                if val in root:
                    if val in root:
                        explore(i, j, [val], root[val], {(i, j)})
        return ans