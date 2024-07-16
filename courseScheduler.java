//Time and Space - O(v+e)

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int course = 0; // Counter for courses that can be taken
        int[] degrees = new int[numCourses]; // Array to store indegrees of each course
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>(); // Map to store prerequisite relationships

        // Fill degrees and map
        for (int[] pre : prerequisites) {
            int in = pre[0]; // Course that depends on another
            int out = pre[1]; // Course that is a prerequisite

            degrees[in]++; // Increase the indegree count for the course that depends on others

            // Initialize map entry for prerequisites
            if (!map.containsKey(out)) {
                map.put(out, new ArrayList<Integer>());
            }
            map.get(out).add(in); // Add prerequisite relationship to the map
        }

        Queue<Integer> que = new LinkedList<Integer>(); // Queue to store courses that can be taken
        for (int i = 0; i < numCourses; i++) {
            if (degrees[i] == 0) { // If a course has no prerequisites
                que.add(i); // Add it to the queue
                course++; // Increment the count of courses that can be taken
            }
        }

        if (course == numCourses) return true; // If all courses can be taken without prerequisites

        if (que.isEmpty()) return false; // If no courses can be taken initially

        // Process courses in the queue
        while (!que.isEmpty()) {
            int currentCourse = que.poll(); // Remove the course from the queue
            ArrayList<Integer> children = map.get(currentCourse); // Get courses that depend on the current course

            if (children == null) continue;

            for (Integer child : children) {
                degrees[child]--; // Decrease the indegree count for dependent courses

                if (degrees[child] == 0) { // If a dependent course now has no prerequisites
                    course++; // Increment the count of courses that can be taken
                    que.add(child); // Add it to the queue

                    if (course == numCourses) return true; // If all courses can now be taken
                }
            }
        }

        return false; // Return false if not all courses can be taken
    }
}
