


public class Percolation {
	
	int seed;   
	public WeightedQuickUnionUF grid;
	int topVal, bottomVal;
	int [] state;
	   
	// create N-by-N grid, with all sites blocked
	public Percolation(int N){
		seed = N;
		int numOfCells = N*N;
		topVal = 0;
		bottomVal = numOfCells+1;
		
		grid = new WeightedQuickUnionUF(numOfCells+2);
		
		// Top = 0, connecting top row with Top
		for (int i = 1; i <= seed; i++) {
			grid.union(topVal,i);
		}
		
		// Bottom = n^2, connecting bottom row with BottomVal
		for (int i = numOfCells; i > (numOfCells-seed); i--) {
			grid.union(i, bottomVal);
		}
		
		// Array to store State Information
		state = new int[numOfCells+2];
	};
	   
	// open site (row i, column j) if it is not already
	public void open(int i, int j){
		if(isOpen(i, j))
			return;

		state[xyTo1D(i,j)] = 1;
		
		//top access
		if(i>1 && isOpen(i-1, j)){
			grid.union(xyTo1D(i-1, j), xyTo1D(i,j));
		}
		// Right Access
		if(j<seed && isOpen(i, j+1)){
			grid.union(xyTo1D(i, j+1),xyTo1D(i,j));
		}
		//Bottom Access
		if(i<seed && isOpen(i+1, j)){
			grid.union( xyTo1D(i+1, j),xyTo1D(i,j));
		}
		//Left Access
		if(j>1 && isOpen(i, j-1)){
			grid.union(xyTo1D(i, j-1),xyTo1D(i,j));
		}
		
	};
	   
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		return (state[xyTo1D(i,j)] != 0);
	};
	   
	// is site (row i, column j) full?
	public boolean isFull(int i, int j){
		return (isOpen(i,j) && grid.connected(xyTo1D(i,j),topVal));
	};
	   
	// does the system percolate?
	public boolean percolates(){
		return (grid.connected(topVal, bottomVal));
	};
	
	// Converting 2D array (i,j) to 1D array ID
	public int xyTo1D(int i, int j){
		return ((i-1)*seed + j);
	}
}
