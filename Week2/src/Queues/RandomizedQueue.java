package Queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private int size = 0;
    private Item[] items;

    public RandomizedQueue()
    {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public void enqueue(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }
        if (size == items.length)
        {
            resize(items.length * 2);
        }
        items[size++] = item;
    }

    private void resize(int max)
    {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < size; i++)
        {
            temp[i] = items[i];
        }
        items = temp;
    }

    public Item dequeue()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        if (size > 0 && size == items.length / 4)
        {
            resize(items.length / 2);
        }
        size--;
        randomSwap(items, size);
        Item item = items[size];
        items[size] = null;
        return item;
    }

    public Item sample()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        randomSwap(items, size - 1);
        Item item = items[size - 1];
        return item;
    }

    private void randomSwap(Item[] queue, int t)
    {
        int index = StdRandom.uniform(t + 1);
        Item tmp = queue[index];
        queue[index] = queue[t];
        queue[t] = tmp;
    }

    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        Item[] itemsCopy;
        int currentIndex;

        public RandomizedQueueIterator()
        {
            itemsCopy = (Item[]) new Object[size];
            for (int i = 0; i < size; i++)
            {
                itemsCopy[i] = items[i];
            }
            StdRandom.shuffle(itemsCopy);

            currentIndex = 0;
        }

        public boolean hasNext()
        {
            return currentIndex < itemsCopy.length;
        }

        public Item next()
        {
            if (currentIndex == itemsCopy.length)
            {
                throw new NoSuchElementException();
            }
            Item item = itemsCopy[currentIndex++];
            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}
