import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        int cnt = 0;
        // reservoir sampling
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            cnt += 1;
            if (cnt <= k) {
                q.enqueue(item);
            }
            else {
                int r = StdRandom.uniformInt(1, cnt + 1);
                if (r <= k) {
                    q.dequeue();
                    q.enqueue(item);
                }
            }
        }

        for (String s : q) {
            StdOut.println(s);
        }
    }
}
