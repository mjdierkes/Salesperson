package com.masondierkes;

public class Tour
{
	/** create an empty tour */
	private Node head;
	private Node tail;

	private int size = 0;
	private double distance = 0.0;

	public Tour() {
		head = null;
		tail = null;
	}
	
	/** create a four-point tour, for debugging */
	public Tour(Point a, Point b, Point c, Point d) {
		this.head = new Node(a);
		this.head.next = new Node(b);
		this.head.next.next = new Node(c);
		this.head.next.next.next = new Node(d);
		this.head.next.next.next.next = head;

		size = 5;
		tail = head;
	}
	
	/** print tour (one point per line) to std output */
	public void show() {
		Node temp = this.head;

		while (temp.next != null && temp.next != head){
			System.out.println(temp.p);
			temp = temp.next;
		}
	}
	
	/** draw the tour using StdDraw */
	public void draw() {
		Node current = head;

		while (current.next != null && current.next != head){
			current.p.drawTo(current.next.p);
			current = current.next;
		}
		current.p.drawTo(head.p);
	}
	
	/** return number of nodes in the tour */
	public int size() {
		return size;
	}
	
	/** return the total distance "traveled", from start to all nodes and back to start */
	public double distance() {
		return distance;
	}

	public void insertInOrder(Point p) {
		if(head == null)
			setHead(p);

		tail.next = new Node(p);
		distance += tail.p.distanceTo(tail.next.p);

		tail = tail.next;
		size += 1;
	}
	
	/** insert p using nearest neighbor heuristic */
    public void insertNearest(Point p) {
    	if(head == null){
			setHead(p);
			return;
		}

		Node node = new Node(p);
    	Node current = head;
		Node closestNode = head;

    	double nearestDistance = Double.MAX_VALUE;

		while ((nearestDistance == Double.MAX_VALUE) || (current.next != head)){
			if(current.p.distanceTo(p) < nearestDistance){
				closestNode = current;
				nearestDistance = current.p.distanceTo(p);
			}
    		current = current.next;
		}

		node.next = closestNode.next;
		closestNode.next = node;
		distance += tail.p.distanceTo(tail.next.p);

		size += 1;
    }

	/** insert p using smallest increase heuristic */
    public void insertSmallest(Point p) 
    {
		if(head == null){
			setHead(p);
			return;
		}

		Node node = new Node(p);
		Node current = head;
		Node closestNode = head;

		double nearestDelta = Double.MAX_VALUE;

		while ((nearestDelta == Double.MAX_VALUE) || (current.next != head)){
			if(((current.p.distanceTo(p) + current.next.p.distanceTo(p)) - current.p.distanceTo(current.next.p)) < nearestDelta){
				closestNode = current;
				nearestDelta = ((current.p.distanceTo(p) + current.next.p.distanceTo(p)) - current.p.distanceTo(current.next.p));
			}
			current = current.next;
		}

		node.next = closestNode.next;
		closestNode.next = node;
		distance += tail.p.distanceTo(tail.next.p);

		size += 1;
    }

	public void insertFarthest(Point p)
	{
		if(head == null){
			setHead(p);
			return;
		}

		Node node = new Node(p);
		Node current = head;
		Node closestNode = head;

		double furthestDistance = Double.MIN_VALUE;

		while ((furthestDistance == Double.MIN_VALUE) || (current.next != head)){
			if(current.p.distanceTo(p) > furthestDistance){
				closestNode = current;
				furthestDistance = current.p.distanceTo(p);
			}
			current = current.next;
		}

		node.next = closestNode.next;
		closestNode.next = node;
		distance += tail.p.distanceTo(tail.next.p);

		size += 1;
	}

    private void setHead(Point p) {
		head = new Node(p);
		head.next = head;
		tail = head;
		size++;
	}


    private static class Node {
    	Point p;
    	Node next;

    	public Node(Point p){
    		this.p = p;
		}
	}
}