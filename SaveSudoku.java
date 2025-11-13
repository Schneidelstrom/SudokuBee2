import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

public class SaveSudoku{
	private FileWriter writer;
	private PrintWriter print;
	SaveSudoku(){
		}
	protected int save(String fileName, int[][][] sudokuArray){
		try{
			File file = new File("save/"+fileName+".sav");
			if(file.exists()){
				return 2;
				}
			writer=new FileWriter("save/"+fileName+".sav");
			print= new PrintWriter(writer);
			print.print(sudokuArray.length+"\n");
			for(int x=0; x<sudokuArray.length; x++){
				String num="", type="";
				for(int y=0; y<sudokuArray.length; y++){
					num=num+sudokuArray[x][y][0]+" ";
					type=type+sudokuArray[x][y][1]+" ";
					}
				print.print(num+"\n");
				print.print(type+"\n");
				}
			print.close();
			}
		catch(IOException e){
			return 1;
			}
		return 0;
		}
	protected void delete(String filename){
		new File("save/"+filename+".sav").delete();
		}
	}