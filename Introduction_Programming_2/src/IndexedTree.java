public class IndexedTree {
    private Node root;

    private String lastDeactivated;
    private String lastActivated;

    private int queries;
    private int minSize;
    private int maxSize;


    public IndexedTree() {
        this.root = new Node();
    }

    public String getLastDeactivated()
    {
        return lastDeactivated;
    }

    public String getLastActivated()
    {
        return lastActivated;
    }

    public void treeSize()
    {
        this.minSize = this.root.childrenBelow;
        this.maxSize = this.root.childrenBelow;
    }

    public int getMinSize()
    {
        return minSize;
    }

    public int getMaxSize()
    {
        return maxSize;
    }

    private void checkMin()
    {
        if(this.minSize > this.root.childrenBelow)
            this.minSize = this.root.childrenBelow;
    }

    private void checkMax()
    {
        if(this.maxSize < this.root.childrenBelow)
            this.maxSize = this.root.childrenBelow;
    }

    public void addNode(String s, int[] data)
    {
        if (s.length() != 8) {
            return;
        }
        this.insert(s, 0, this.root, data);
    }

    private void insert(String s, int pos, Node current, int[] data)
    {
        int index = s.charAt(pos) - 97;
        Node nextNode;
        current.childrenBelow++;

        if (pos == s.length() - 1) {
            if (current.child[index] == null) {
                current.child[index] = new Node(data, s);
                return;
            }
            else {
                System.out.println("Error (TODO catch)");
            }
        }

        if(current.child[index] == null) {
            current.child[index] = new Node();
        }

        nextNode = current.child[index];

        this.insert(s, ++pos, nextNode, data);
    }

    private void remove(String node, int pos, Node current)
    {
        char c = node.charAt(pos);
        int index = c - 97;
        Node next = current.child[index];

        current.childrenBelow--;

        if(next.childrenBelow == 1 || next.childrenBelow == 0)
        {
            current.child[index] = null;
            return;
        }

        this.remove(node, pos + 1, next);
    }

    private Node findNearest(String name)
    {
        if(name.length() != 8) {
            System.err.println("Wrong length");
            return null;
        }

        if(this.root == null){
            return null;
        }
        return this.findNearest(name, 0, this.root);
    }

    private Node findNearest(String name, int pos, Node current)
    {
        if(pos >= name.length()) {
            return current;
        }
        int index = name.charAt(pos) - 97;
        Node next = current.child[index];

        if(next == null) {
            return findLeftest(current, pos, 0);
        }
        return this.findNearest(name, pos + 1, next);
    }

    private Node findLeftest(Node startNode, int level, int index)
    {
        if(startNode == null)
            return null;

        if(level == 8)
            return startNode;

        if(startNode.child[index] != null)
            return this.findLeftest(startNode.child[index], level + 1, 0);

        else
            return this.findLeftest(startNode, level, index + 1);
    }

    private void replace(String oldNode, String newNode)
    {
        this.replace(oldNode, newNode, 0, this.root);
    }

    private void replace(String oldNode, String newNode, int pos, Node current)
    {
        char cOld = oldNode.charAt(pos);
        char cNew = newNode.charAt(pos);
        int indexOld = cOld - 97;
        int indexNew = cNew - 97;

        if(pos >= 8)
            return;

        if(indexOld == indexNew)
        {
            replace(oldNode, newNode, pos + 1, current.child[indexNew]);
            return;
        }

        //remove old Node
        this.remove(oldNode, pos, current);
        //insert new Node
        this.insert(newNode, pos, current, new int[]{0, 0, 0});
    }

    public void handleQuery(String name)
    {
        this.queries++;
        Node myNode = this.findNearest(name);
        int rc = myNode.updateStatus();

        if(rc == 1)
        {
            //now we need to replace the node myNode with newNode
            String sMyNode = myNode.sensor;
            this.lastDeactivated = sMyNode;
            this.replace(sMyNode, name);
        }
        else if(rc == 2)
        {
            //insert new Node 'name'
            this.lastDeactivated = name;
            this.addNode(name, new int[]{0, 0, 0});
            this.checkMax();
        }

        if(this.queries >= 500000)
        {
            this.queries = 0;
            this.removeUnused(this.root);
            this.checkMin();
        }
    }

    private void removeUnused(Node current)
    {
        if(current == null)
            return;

        if(current.child != null)
        {
            for (int i = 0; i < current.child.length; i++)
            {
                this.removeUnused(current.child[i]);
            }
            return;
        }

        String fullName = current.sensor;

        if(current.data[2] == 0)
        {
            this.remove(fullName, 0, this.root);
        }
        else
        {
            current.data[2] = 0;
        }
    }



}

