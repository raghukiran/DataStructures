package com.ds;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Node {

  private Node left;
  private Node right;
  private String name;

  public Node(@Nonnull String name, Node left, Node right) {
    this.left = left;
    this.right = right;
    this.name = name;
  }

  public static void main(String[] args) {

    Node root = createTree();

    System.out.println("DFS :" + root.DFS());
  }

  @Override
  public String toString() {
    return name;
  }

  private static Node createTree() {


    Node rightChildOfRight = new Node("rightChildOfRight", null, null);
    Node leftChildOfRight = new Node("leftChildOfRight", null, null);

    Node right = new Node("right", leftChildOfRight, rightChildOfRight);
    Node left = new Node("left", null, null);

    return new Node("root", left, right);
  }

  public List<Node> getChildren() {

    return Stream.of(left, right).filter(Objects::nonNull).collect(Collectors.toList());
  }

  /**
   * 1) Start with Root node. Add it to UnVisitedNode List
   * 2) Mark Root as currentNode and Remove Root from UnVisitedNode List (as it is being visited currently)
   * 3) Get Children of currentNode(e.g. Root) i.e. newNodes
   * 4) Add List of Children(i.e. newNodes) to UnVisitedNode List at index 0;
   * 5) Add Root to visitedNodes
   * 6) Loop continues till no Unvisited nodes are left
   */
  public List<Node> DFS() {

    LinkedList<Node> visitedNodes = new LinkedList<>();
    LinkedList<Node> UnVisitedNodes = new LinkedList<>();
    UnVisitedNodes.add(this); // adds the First Node or Root Node

    while (!UnVisitedNodes.isEmpty()) {
      Node currentNode = UnVisitedNodes.remove(0); // returns the Node removed at index 0

      List<Node> newNodes =
          currentNode
              .getChildren()
              .stream()
              .filter(n -> !visitedNodes.contains(n))
              .collect(Collectors.toList());

      UnVisitedNodes.addAll(0, newNodes);
      visitedNodes.add(currentNode);
    }

    return visitedNodes;
  }


}
