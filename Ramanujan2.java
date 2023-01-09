public class Ramanujan2 {
    // A data type that encapsulates a pair of numbers (i, j) 
    // and the sum of their cubes, ie, i^3 + j^3.
    private static class Pair implements Comparable<Pair> {
        private int i;          // first element of the pair
        private int j;          // second element of the pair
        private int sumOfCubes; // i^3 + j^3

        // Construct a pair (i, j).
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
            sumOfCubes = i * i * i + j * j * j;
        }

        // Compare this pair to the other by sumOfCubes.
        public int compareTo(Pair other) {
            return sumOfCubes - other.sumOfCubes;
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        MinPQ<Pair> pq = new MinPQ<Pair>();
        
        for (int i = 0; i*i*i < N; i++)
        {
            pq.insert(new Pair(i, i+1));
        }
        
        while (pq.size() >= 2)
        {
            int j = pq.min().j;
            if (j*j*j < N)
            {
                pq.insert(new Pair(pq.min().i, j+1));
            }
            Pair prev = pq.delMin();
            if (prev.sumOfCubes == pq.min().sumOfCubes && prev.sumOfCubes <= N)
            {
                System.out.println(prev.sumOfCubes + " = " +prev.i
                +"^3 + "+prev.j+"^3 = "+pq.min().i+"^3 + "
                            +pq.min().j+"^3");
            }
            
            
        }
        
        
    }
}
