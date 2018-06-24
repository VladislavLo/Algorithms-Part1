package KdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class PointSET {
    private final SET<Point2D> set;

    public PointSET()
    {
        set = new SET<Point2D>();
    }

    public boolean isEmpty()
    {
        return set.isEmpty();
    }

    public int size()
    {
        return set.size();
    }

    public void insert(Point2D p)
    {
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        set.add(p);
    }

    public boolean contains(Point2D p)
    {
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        return set.contains(p);
    }
    public void draw()
    {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        for (Point2D p : set)
        {
            StdDraw.point(p.x(), p.y());
        }
    }
    public Iterable<Point2D> range(RectHV r)
    {
        if (r == null)
        {
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> list = new ArrayList<Point2D>();
        for(Point2D point : set)
        {
            if (r.contains(point))
            {
                list.add(point);
            }
        }
        return list;
    }

    public Point2D nearest(Point2D p)
    {
        double minDistance = Double.MAX_VALUE;
        Point2D nearest = null;
        for (Point2D point : set)
        {
            double distance = point.distanceTo(p);
            if (distance < minDistance)
            {
                minDistance = distance;
                nearest = point;
            }
        }
        return nearest;
    }
}
