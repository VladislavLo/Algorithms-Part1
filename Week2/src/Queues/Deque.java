package Queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int count;

    private class Node
    {
        Item item;
        Node next;
        Node previous;

        Node(Item item)
        {
            this.item = item;
        }
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node node;

        DequeIterator()
        {
            node = first;
        }

        public boolean hasNext()
        {
            return node != null;
        }

        public Item next()
        {
            if (node == null)
            {
                throw new NoSuchElementException();
            }
            else
            {
                Item item = node.item;
                node = node.next;
                return item;
            }
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    public Deque()
    {
        first = null;
        last = null;
        count = 0;
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public int size()
    {
        return count;
    }

    public void addFirst(Item item)
    {
        if (item == null)
        {
            throw  new IllegalArgumentException();
        }
        else
        {
            Node oldFirst = first;
            first = new Node(item);
            first.next = oldFirst;
            if (isEmpty())
            {
                last = first;
            }
            else
            {
                oldFirst.previous = first;
            }
            count++;
        }
    }

    public void addLast(Item item)
    {
        if (item == null)
        {
            throw  new IllegalArgumentException();
        }
        else
        {
            Node oldLast = last;
            last = new Node(item);
            if (isEmpty())
            {
                first = last;
            }
            else
            {
                oldLast.next = last;
                last.previous = oldLast;
            }
            count++;
        }
    }

    public Item removeFirst()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        else
        {
            Item item = first.item;
            first = first.next;
            count--;
            if (isEmpty())
            {
                last = null;
            }
            else
            {
                first.previous = null;
            }
            return item;
        }
    }

    public Item removeLast()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        else
        {
            Item item = last.item;
            last = last.previous;
            count--;
            if (isEmpty())
            {
                first = null;
            }
            else
            {
                last.next = null;
            }
            return item;
        }
    }

    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
}
