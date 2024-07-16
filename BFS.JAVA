class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
       Queue<TreeNode> que = new LinkedList<TreeNode>(); // Queue for BFS
       List<List<Integer>> result = new ArrayList(); // List to store level order traversal

       if (root == null) return result; // If the root is null, return empty result

       que.add(root); // Add the root node to the queue
       while (!que.isEmpty()) { // While there are nodes to process
           int size = que.size(); // Get the current size of the queue (nodes in the current level)
           ArrayList<Integer> sublist = new ArrayList(); // List to store nodes at the current level

           for (int i = 0; i < size; i++) { // Process all nodes in the current level
               TreeNode currentNode = que.poll(); // Remove and retrieve the node from the front of the queue
               sublist.add(currentNode.val); // Add node value to the sublist

               // Add left and right children to the queue if they exist
               if (currentNode.left != null) que.add(currentNode.left);
               if (currentNode.right != null) que.add(currentNode.right);
           }

           result.add(sublist); // Add the sublist (current level nodes) to the result list
       }

       return result; // Return the level order traversal of the tree
    }
}
