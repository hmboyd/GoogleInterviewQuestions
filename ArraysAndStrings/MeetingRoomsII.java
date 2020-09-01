class Solution {
     public int minMeetingRooms(int[][] intervals) {
        
    // Check for the base case. If there are no intervals, return 0
    if (intervals.length == 0) {
      return 0;
    }
         
    // Sort the intervals by start time
    Arrays.sort(intervals,
        new Comparator<int[]>() {
          public int compare(final int[] a, final int[] b) {
            return a[0] - b[0];
          }
        });

    // Min heap
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(intervals.length);

    // Add the first meeting
    minHeap.add(intervals[0][1]);

    // Iterate over remaining intervals
    for (int i = 1; i < intervals.length; i++) {
      // If the room due to free up the earliest is free, assign that room to this meeting.
      if (intervals[i][0] >= minHeap.peek()) {
        minHeap.poll();
      }
      // If a new room is to be assigned, then also we add to the heap,
      // if an old room is allocated, then also we have to add to the heap with updated end time.
      minHeap.add(intervals[i][1]);
    }

    // The size of the heap tells us the minimum rooms required for all the meetings.
    return minHeap.size();
  }
}