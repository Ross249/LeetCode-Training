def differ(str1, str2):
	"""
	determines if two strings differ by one character.
	"""
	diff = 0

	for i in range(len(str1)):
		if str1[i] != str2[i]:
			diff += 1

	return diff == 1


def convert(words):
	"""
	converts words into the adjacency list representation of a graph, as detailed above.
	"""
	edges = []
	graph = [[] for _ in range(len(words))]

	for i in range(len(words)):
		for j in range(i, len(words)):
			if differ(words[i], words[j]):
				edges.append([i, j])

	for pair in edges:
		graph[pair[0]].append(pair[1])
		graph[pair[1]].append(pair[0])

	return graph


def bfs(graph, start):
	"""
	performs a modified bfs search on graph with start node start.

	Returns:
		- parents, a dictionary that maps each node in the graph to a list of parents that have the shortest distance from the start node.

	start is the index such that wordList[start] = beginWord
	"""
	dist = {start: 0}  # dictionary that maps each node in graph to the shortest distance away from start.
	parents = {start: None}

	for i in range(len(graph)):
		if i != start:
			dist[i] = float('inf')
			parents[i] = []

	queue = [start]

	while queue:
		node = queue.pop()

		for neighbor in graph[node]:
			if dist[neighbor] == float('inf'):  # neighbor has not been visited yet
				dist[neighbor] = dist[node] + 1
				parents[neighbor].append(node)
				queue.insert(0, neighbor)

			else:  # neighbor has been visited!
				if dist[node] + 1 == dist[neighbor]:
					parents[neighbor].append(node)
				elif dist[node] + 1 < dist[neighbor]:  # found a quicker path to neighbor
					dist[neighbor] = dist[node] + 1
					parents[neighbor].clear()
					parents[neighbor].append(node)

	return parents


def findPaths(pathList, currPath, currNode, parents, wordList):
	"""
	traces back to find all paths from the end node to the start node given the parents dictionary. Returns nothing,
	but modifies the input pathList to include all possible paths.
	"""
	if parents[currNode] is None:
		currPath.reverse()
		pathList.append(currPath)

	if parents[currNode]:
		for parent in parents[currNode]:
			findPaths(pathList, currPath + [wordList[parent]], parent, parents, wordList)


class Solution:
	def findLadders(self, beginWord, endWord, wordList):
		if beginWord not in wordList:
			wordList.append(beginWord)

		if endWord not in wordList:
			return []

		endIndex = wordList.index(endWord)
		beginIndex = wordList.index(beginWord)

		graph = convert(wordList)
		parents = bfs(graph, beginIndex)

		pathList = []
		currPath = [endWord]
		findPaths(pathList, currPath, endIndex, parents, wordList)

		return pathList