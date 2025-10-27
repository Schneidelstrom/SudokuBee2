import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.text.SimpleDateFormat;

class PrintResult{
	private FileWriter writer;
	private PrintWriter print;
	private SimpleDateFormat dateFormat;
	private String filename;
	PrintResult(String filename){
		this.filename=filename;
		try{
			writer=new FileWriter(new java.io.File(filename));
			print= new PrintWriter(writer);
			}
		catch(Exception e){
			e.printStackTrace();
			}
		dateFormat=new SimpleDateFormat("HHmmssSSS");
		}
	protected void print(Object text){
		print.print(text+"\n");
		}
	protected void close(){
		print.close();
		}
	protected void delete(){
		new java.io.File(filename).delete();
		}
	protected double getTime(){
		return (Double.parseDouble(dateFormat.format(Calendar.getInstance().getTime())));
		}
	}
