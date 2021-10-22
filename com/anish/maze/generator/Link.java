package com.anish.maze.generator;

public class Link {
    private Node src;
    private Node dest;

    public Link(Node src, Node dest) {
        this.src = src;
        this.dest = dest;
    }

    public Node getSrc() {
        return src;
    }

    public Node getDest() {
        return dest;
    }
}
