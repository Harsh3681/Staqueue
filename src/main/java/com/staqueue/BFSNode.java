package com.staqueue;

public class BFSNode {
    private int id, x, y;

    public BFSNode(int id, int columns) {
        this.id = id;
        this.x = id % columns;
        this.y = id / columns;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof BFSNode)) return false;
        BFSNode o = (BFSNode) obj;
        return o.id == this.id;
    }
}