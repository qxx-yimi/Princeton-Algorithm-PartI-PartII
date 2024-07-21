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

