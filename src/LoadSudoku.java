import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadSudoku{
	private FileReader file;
	private BufferedReader buff;
	private int sudokuArray[][][], original[][][];
	private int size;
	private boolean valid=false;
	LoadSudoku(String path){
		try{
			file= new FileReader(path);
			buff= new BufferedReader(file);
			try{
				size=Integer.parseInt(buff.readLine());
				sudokuArray=new int[size][size][2];
				original=new int[size][size][2];
				String str="", sub="";
				boolean isEven=true;
				for(int ctr=0, counter=0; counter<size*2; counter++, isEven=!isEven){
					str=buff.readLine();
					for(int count=0; count<size && isEven; count++){
						sub=str.substring(0, str.indexOf(" "));
						sudokuArray[ctr][count][0]=Integer.parseInt(sub);
						str=str.substring(str.indexOf(" ")+1,str.length());
						}
					for(int count=0; count<size && !isEven; count++){
						sub=str.substring(0, str.indexOf(" "));
						sudokuArray[ctr][count][1]=Integer.parseInt(sub);
						str=str.substring(str.indexOf(" ")+1,str.length());
						}
					if(!isEven)
						ctr++;
					}
				file.close();
				buff.close();
				for(int ctr=0; ctr<size; ctr++){
					for(int count=0; count<size; count++){
						if(sudokuArray[ctr][count][1]==1){
							original[ctr][count][0]=0;
							original[ctr][count][1]=1;
							}
						else{
							original[ctr][count][0]=sudokuArray[ctr][count][0];
							original[ctr][count][1]=0;
							}
						}
					}
				valid=true;
				}
			catch(NumberFormatException e){
				valid=false;
				}
			catch(IOException exception){
				valid=false;
				}
			}
		catch(FileNotFoundException eEE){
			valid=false;
			}
		if(valid){
			Subgrid subgrid[]=new Subgrid[original.length];
			int subDimY=(int)Math.sqrt(original.length);
			int subDimX=original.length/subDimY;
			for(int ctr=0, xCount=0; ctr<original.length; ctr++, xCount++){
				subgrid[ctr]=new Subgrid(xCount*subDimX, ((ctr/subDimY)*subDimY), subDimX, subDimY);
				if((ctr+1)%subDimY==0 && ctr>0)
					xCount=-1;
				}
			Validator val=new Validator(original, subgrid);
			if(!val.checkValidity()){
				valid=false;
				}
			val=null;
			}
		}
	private void sop(Object obj){
		System.out.println(obj+"");
		}
	protected boolean getStatus(){
		return valid;
		}
	protected int[][][] getArray(){
		return sudokuArray;
		}
	protected int getSize(){
		return size;
		}
	}