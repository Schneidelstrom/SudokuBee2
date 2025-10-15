class Validator{
	private int[][][] grid;
	private Subgrid subgrid[];
	private int subDimX;
	private int subDimY;
	Validator(int[][][] grid, Subgrid subgrid[]){
		this.grid=grid;
		this.subgrid=subgrid;
		}
	Validator(int[][][] grid, int subDimX, int subDimY){
		this.grid=grid;
		this.subDimX=subDimX;
		this.subDimY=subDimY;
		}
	protected boolean isValid(int num, int locX, int locY, Subgrid subgrid){
		for(int i=0; i<grid.length; i++){
			if(num==grid[i][locY][0] || num==grid[locX][i][0])
				return false;
			}
		return true;
		}
	protected void updateGrid(int[][][] newGrid){
		grid=null;
		grid=newGrid;
		}
	private void sop(Object obj){
		System.out.println(obj+"");
		}
	protected boolean checkValidity(){
		customSet hor=new customSet();
		customSet ver=new customSet();
		customSet sub=new customSet();
		for(int ctr=0; ctr<grid.length; ctr++){
			hor.clear();
			ver.clear();
			sub.clear();
			for(int y=subgrid[ctr].getStartY(), limY=y+subgrid[ctr].getDimY(); y<limY; y++){
				for(int x=subgrid[ctr].getStartX(), limX=x+subgrid[ctr].getDimX(); x<limX; x++){
					if(grid[y][x][1]==0 && sub.contains(grid[y][x][0])){
						return false;
						}
					else if(grid[y][x][1]==0){
						sub.add(Integer.valueOf(grid[y][x][0]));
						}
					}
				}
			for(int ct=0; ct<grid.length; ct++){
				if(grid[ctr][ct][1]==0 && hor.contains(grid[ctr][ct][0])){
					return false;
					}
				else if(grid[ctr][ct][1]==0)
					hor.add(Integer.valueOf(grid[ctr][ct][0]));
				if(grid[ct][ctr][1]==0 && ver.contains(grid[ct][ctr][0])){
					return false;
					}
				else if(grid[ct][ctr][1]==0)
					ver.add(Integer.valueOf(grid[ct][ctr][0]));
				}
			}
		return true;
		}
	protected boolean checkAnswer(){
		customSet hor=new customSet();
		customSet ver=new customSet();
		customSet sub=new customSet();
		for(int ctr=0; ctr<grid.length; ctr++){
			hor.clear();
			ver.clear();
			sub.clear();
			for(int y=subgrid[ctr].getStartY(), limY=y+subgrid[ctr].getDimY(); y<limY; y++){
				for(int x=subgrid[ctr].getStartX(), limX=x+subgrid[ctr].getDimX(); x<limX; x++){
					if(sub.contains(grid[y][x][0])){
						return false;
						}
					else{
						sub.add(Integer.valueOf(grid[y][x][0]));
						}
					}
				}
			for(int ct=0; ct<grid.length; ct++){
				if(hor.contains(grid[ctr][ct][0])){
					return false;
					}
				else
					hor.add(Integer.valueOf(grid[ctr][ct][0]));
				if(ver.contains(grid[ct][ctr][0])){
					return false;
					}
				else
					ver.add(Integer.valueOf(grid[ct][ctr][0]));
				}
			}
		return true;
		}
	}