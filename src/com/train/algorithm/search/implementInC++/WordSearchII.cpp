//
// Created by 罗斯 on 21/9/2022.
//
#include <vector>
#include <string>
#include <unordered_set>
using namespace std;

//class TrieNode {
//public:
//    vector<TrieNode*> nodes;
//    const string* word;
//    TrieNode(): nodes(26), word(nullptr) {}
//    ~TrieNode() {
//        for (auto node : nodes) delete node;
//    }
//};

class Solution {
public:
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        unordered_set<string> unique_words(words.begin(),words.end());
        vector<string> res;
        for (const string& word : unique_words){
            if (exist(board, word))
                res.push_back(word);
        }
        return res;
    }
//    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
//        TrieNode root;
//
//        // Add all the words into Trie.
//        for (const string& word : words) {
//            TrieNode* cur = &root;
//            for (char c : word) {
//                auto& next = cur->nodes[c - 'a'];
//                if (!next) next = new TrieNode();
//                cur = next;
//            }
//            cur->word = &word;
//        }
//
//        const int n = board.size();
//        const int m = board[0].size();
//        vector<string> ans;
//
//        function<void(int, int, TrieNode*)> walk = [&](int x, int y, TrieNode* node) {
//            if (x < 0 || x == m || y < 0 || y == n || board[y][x] == '#')
//                return;
//
//            const char cur = board[y][x];
//            TrieNode* next_node = node->nodes[cur - 'a'];
//
//            // Pruning, only expend paths that are in the trie.
//            if (!next_node) return;
//
//            if (next_node->word) {
//                ans.push_back(*next_node->word);
//                next_node->word = nullptr;
//            }
//
//            board[y][x] = '#';
//            walk(x + 1, y, next_node);
//            walk(x - 1, y, next_node);
//            walk(x, y + 1, next_node);
//            walk(x, y - 1, next_node);
//            board[y][x] = cur;
//        };
//
//        // Try all possible pathes.
//        for (int i = 0 ; i < n; i++)
//            for (int j = 0 ; j < m; j++)
//                walk(j, i, &root);
//
//        return ans;
//    }

private:
    int w;
    int h;
    bool exist(vector<vector<char>>& board, string words){
        if (board.size() == 0)return false;
        h = board.size();
        w = board[0].size();
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < h; ++j) {
                if (search(board, words, 0, i, j)) return true;
            }
        }
        return false;
    }

    bool search(vector<vector<char>>& board, const string& word, int d, int x, int y){
        if (x < 0 || x == w || y < 0 || y == h || word[d] != board[y][x]) return false;

        if (d == word.length() - 1) return true;

        char cur = board[y][x];
        board[y][x] = '#';
        bool found = search(board, word, d + 1, x + 1, y)
                    || search(board, word, d + 1, x - 1, y)
                    || search(board, word, d + 1, x, y + 1)
                    || search(board, word, d + 1, x, y - 1);
        board[y][x] = cur;
        return found;
    }
};