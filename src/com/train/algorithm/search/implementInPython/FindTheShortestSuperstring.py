class Solution(object):
    def shortestSuperstring(self, A):
        self.overlaps = [[0 for _ in range(len(A))] for _ in range(len(A))]
        for i in range(len(A)):
            for j in range(len(A)):
                if i == j: continue
                x, y = A[i], A[j]
                for count in range(min(len(x), len(y)), -1, -1):
                    if x.endswith(y[:count]):
                        self.overlaps[i][j] = count
                        break
        self.words = A
        self.cache = {}
        wordsbit = (1 << len(A)) - 1
        return self.helper(wordsbit, -1)

    def helper(self, wordsbit, last_word_idx):
        if not wordsbit:
            return ''
        key = (wordsbit, last_word_idx)
        if key in self.cache:
            return self.cache[key]
        words = []
        for i in range(len(self.words)):
            if (wordsbit >> i) & 1 == 1:
                words.append((i, self.words[i]))

        local_res = None
        for idx, word in words:
            append_idx = 0
            if last_word_idx != -1:
                append_idx = self.overlaps[last_word_idx][idx]
            new_bit = wordsbit - (1 << idx)
            child_res = self.helper(new_bit, idx)
            new_res = word[append_idx:] + child_res
            if not local_res or len(local_res) > len(new_res):
                local_res = new_res
        self.cache[key] = local_res
        return local_res
