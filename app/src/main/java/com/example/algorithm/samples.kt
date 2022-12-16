package com.example.algorithm

//Creating graphs in adjacency list form
//[[1: [2, 4, 3]], [2: [4, 5]], [3: [6]], [4: [6, 7, 3]], [5: [4, 7]], [7: [6]]]
fun createAdjacencyList(pairs: Array<IntArray>) {
    val graph: HashMap<Int, MutableList<Int>> = hashMapOf()
    pairs.forEach { pair ->
        if (!graph.containsKey(pair[0])) {
            // If the current node isn't in the adjacency list yet,
            // add it and create its dependency list starting with
            // pair[1]
            graph[pair[0]] = mutableListOf(pair[1])
        } else {
            // Otherwise, append pair[1] to its existing dependency
            // list.
            val dependencies = graph[pair[0]]
            if(dependencies!=null){
                dependencies.add(pair[1])
                graph[pair[0]] = dependencies
            }
        }
    }
}

//Breadth first search
fun bfs(nodes: List<List<Int>>) {
    val visited = BooleanArray(nodes.size) { false }
    // Create a queue and add 0 to represent the index of the
    // first node
    val queue: MutableList<Int> = mutableListOf(0)
    while (queue.isNotEmpty()) {
        // Dequeue a node from queue
        val node = queue.removeAt(0)
        // Add all of the node's unvisited neighbors to the queue
        if (!visited[node]) {
            nodes[node].forEach {
                queue.add(it)
            }
            // Mark the dequeued node as visited
            visited[node] = true
        }
    }
}

//Depth first search
fun dfs(nodes: List<List<Int>>) {
    val visited = BooleanArray(nodes.size) { false }
    helper(nodes, 0, visited)
}

fun helper(nodes: List<List<Int>>, node: Int, visited: BooleanArray){
    visited[node] = true
    nodes[node].forEach {
        if (!visited[it]) {
            helper(nodes, it, visited)
        }
    }
}

/*
Tree traversal
Tree problems are very common in interviews. Some examples are finding the lowest common ancestor of two nodes, summing values of all nodes in a tree, etc.

Binary tree
Binary trees are the most common tree you’ll encounter in interviews. A node for a binary tree will look something like this:
 */

class Node(
    var key: Int,
    var left: Node? = null,
    var right: Node? = null
)

//You can construct it using the following code:

val node4 = Node(4)
val node7 = Node(7)
val node6 = Node(6, node4, node7)
val node11 = Node(11)
val node9 = Node(9, node6, node11)
val node2 = Node(2)
val node5 = Node(5)
val node12 = Node(12, node2, node5)
val node3 = Node(3, node12)
val node1 = Node(1, node9, node3)


//Here’s what a pre-order traversal would look like:

fun preOrder(n: Node?) {
    n?.let { node ->
        print(node.key)
        preOrder(node.left)
        preOrder(node.right)
    }
}
//preOrder(node1) //prints 1 9 6 4 7 11 3 12 2 5


//Here’s what an in-order traversal would look like:

fun inOrder(n: Node?) {
    n?.let { node ->
        inOrder(node.left)
        print(node.key)
        inOrder(node.right)
    }
}
//inOrder(node1) // prints 4 6 7 9 11 1 2 12 5 3

//Here’s what a post-order traversal would look like:

fun postOrder(n: Node?) {
    n?.let { node ->
        postOrder(node.left)
        postOrder(node.right)
        print(node.key)
    }
}
//postOrder(node1) // prints 4 7 6 11 9 2 5 12 3 1
//
//Tree with multiple children
//You may also encounter trees that have an array of children rather than left and right nodes. An example of this data structure would be the Android view hierarchy, where each view may have multiple children.

class Node1(var value: Int, val children: List<Node1>) {
}

//In this case, you’d have to recursively call your function on all the children and the code would look something like this:

fun traverse(node: Node1) {
    print(node.value)
    node.children.forEach {
        traverse(it)
    }
}


/*
Iteration
We start from the smallest i and fill the results table from there. Every subproblem we need for the current iteration should be solved already. In the final iteration, we solve for i=n and return that result.
 */

fun fibonacci(n: Int): Int {
    // Initialize an array to keep track of results of subproblems
    // We'll use 0 as the placeholder initial value
    val results = Array(n + 1) { 0 }
    // Set the base cases
    results[1] = 1
    results[2] = 1
    for (i in 3..n) {
        results[i] = results[i-1] + results[i-2]
    }
    return results[n]
}

/*
Recursion
We start from i = n. If the results of the subproblems we need for the current iteration already exist in the results table, we can use them. If not, we’ll call the function recursively to solve them and store the results.
 */

fun fibonacci2(n: Int): Int {
    // Initialize an array to keep track of results of subproblems
    // We'll use 0 as the placeholder initial value
    val results = Array(n + 1) { 0 }
    // Set the base cases
    results[1] = 1
    results[2] = 1
    return helper(n, results)
}
// Write a helper function that takes in the results array as an
// argument
fun helper(n: Int, results: Array<Int>): Int {
    // Check for the result of the subproblem you need in the
    // results table first
    val nMinusOne: Int = if (results[n-1] != 0) {
        results[n-1]
    } else {
        // Only make the recursive call to the subproblem if it's
        // not in the results table yet
        helper(n-1, results)
    }
    val nMinusTwo: Int = if (results[n-2] != 0) {
        results[n-2]
    } else {
        helper(n-2, results)
    }
    // Fill in the results table with the current results
    results[n] = nMinusOne + nMinusTwo
    return nMinusOne + nMinusTwo
}