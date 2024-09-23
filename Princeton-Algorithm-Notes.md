## Princeton-Algorithm-Notes

### Union-Find

|           Algorithm            | Initialize | Union | Find  |
| :----------------------------: | :--------: | :---: | :---: |
|           Quick Find           |     N      |   N   |   1   |
|          Quick Union           |     N      |   N   |   N   |
|          Weighted QU           |     N      | logN  | logN  |
|     QU + Path Compression      |     N      | logN  | logN  |
| Weighted QU + Path Compression |     N      | log*N | log*N |

#### Quick Find

~~~java
public class QuickFindUF
{
	private int[] id;
	
	public QuickFindUF(int N)
	{
		id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
	}
    
    public boolean connected(int p, int q)
    {
        return id[p] == id[q];
    }
    
    public void union(int p, int q)
    {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++)
        {
            if(id[i] == pid) 
                id[i] = qid;
        }
    }
}
~~~

#### Quick Union

~~~java
public class QuickUnionUF
{
    private int[] id;
    
    public QuickUnionUF(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
        }
    }
    
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }
    
    public void union(int p, int q)
    {
    	int i = root(p);
        int j = root(q);
        id[i] = j;
    }
    
    private int root(int i)
    {
        while (i != id[i])
        {
            i = id[i];
        }
        return i;
    }
}
~~~

#### Weighted QU

~~~java
public class QuickUnionUF
{
    private int[] id;
    private int[] sz;
    
    public QuickUnionUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }
    
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }
    
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if( i == j) return;
        if (sz[i] < sz[j])
        {
            id[i] = j;
            sz[j] += sz[i];
        }
        else
        {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
    
    private int root(int i)
    {
        while (i != id[i])
        {
            i = id[i];
        }
        return i;
    }
}
~~~

#### QU + Path Compression

~~~java
public class QuickUnionUF
{
 	private int[] id;
    
    public QuickUnionUF(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
        }
    }
    
    public boolean connected(int p, int q)
    {
    	return root(p) == root(q);
	}
    
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
    
    private int root(int i)
    {
        while(i != id[i])
        {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
}
~~~

#### Weighted QU + Path Compression

~~~Java
public class QuickUnionUF
{
    private int[] id;
    private int[] sz;
    
    public QuickUnionUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }
    
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }
    
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        if(sz[i] < sz[j])
        {
            id[i] = j;
            sz[j] += sz[i];
        }
        else
        {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
    
    private root(int i)
    {
        while(i != id[i])
        {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
	}
}
~~~

### Stacks and Queues

~~~java
public class LinkedStackOfStrings
{
	private Node first = null;
    
    private class Node
    {
        String item;
        Node next;
    }
    
    public boolean isEmpty()
    {
        return first == null;
    }
    
    public void push(String item)
    {        
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    
    public String pop()
    {
        String item = first.item;
        first = first.next;
        return item;
    }
}
~~~

~~~java
public class FixedCapacityStackOfStrings
{
    private String[] s;
    private int N = 0;
    
    public FixedCapacityStackOfStrings(int capacity)
    {
        s = new String[capacity];
    }
    
    public boolean isEmpty()
    {
        return N == 0;
    }
    
    public void push(String item)
    {
        s[N++] = item;
    }
    
    public String pop()
    {
        String item = s[--N];
        s[N] = null;
        return item;
    }
}
~~~

~~~java
public ResizingArrayStackOfStrings()
{
	s = new String[1];
}

public void push(String item)
{
    if(N == s.length) resize(2 * s.length)
    s[N++] = item;
}

private void resize(int capacity)
{
    String[] copy = new String[capacity];
    for(int i = 0; i < N; i++)
        copy[i] = s[i];
    s = copy;
}

public String pop()
{
    String item = s[--N];
    s[N] = null;
    if(N > 0 && N == s.length / 4) resize(s.length/2);
    return item;
}
~~~

~~~java
public class QueueOfStrings
{
	private Node first = null, last = null;
    
    private class Node
    {
        String item;
        Node next;
    }
    
    public boolean isEmpty()
    {
        return first == null;
    }
    
    public void enqueue(String item)
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
    }
    
    public String dequeue()
    {
        String item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }
}
~~~

~~~java
public class Stack<Item>
{
	private Node first = null;
    
    private class Node
    {
        Item item;
        Node next;
    }
    
    public boolean isEmpty()
    {
        return first == null;
    }
    
    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    
    public Item pop()
    {
        Item item = first.item;
        first = first.next;
        return item;
    }
}
~~~

~~~java
public class FixedCapacityStack<Item>
{
	private Item[] s;
    private int N = 0;
    
    public FixedCapacityStack(int capacity)
    {
        // s = new Item[capacity];  can not
        s = (Item[]) new Object[capacity]; // ugly cast
    }
    
    public boolean isEmpty()
    {
        return  N == 0;
    }
    
    public void push(Item item)
    {
        s[N++] = item;
    }
    
    public Item pop()
    {
       return s[--N];
    }
}
~~~



