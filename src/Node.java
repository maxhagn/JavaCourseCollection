public class Node {
    protected String sensor;
    protected int[] data;
    protected Node[] child;
    protected int childrenBelow;

    protected Node()
    {
        child = new Node[26];
    }

    protected Node(int[] data, String sensor)
    {
        this.data = data;
        this.sensor = sensor;
    }

    protected int updateStatus()
    {
        this.data[0]++;
        this.data[1]++;
        this.data[2]++;

        if(data[0] >= 1000)
        {
            return 1;
        }
        if(data[1] >= 250)
        {
            data[1] = 0;
            return 2;
        }
        return 0;
    }
}
