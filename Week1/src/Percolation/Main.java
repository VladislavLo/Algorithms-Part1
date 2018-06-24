package Percolation;

public class Main {
    public static void main(String[] args) {
	    Percolation percolation = new Percolation(3);
	    percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(3,2);
        System.out.print(percolation.percolates());
    }
}
