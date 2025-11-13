import java.io.File;
import java.io.FilenameFilter;

public final class EndsWithFilter implements FilenameFilter{
	private static final boolean DEBUGGING = false;
	private final String endsWith;
	public EndsWithFilter( String endsWith ){
        this.endsWith = endsWith.toLowerCase();
        }
    public boolean accept( File dir, String name ){
        File f = new File( dir, name );
        if ( f.isDirectory() ){
            return false;
            }
        return name.toLowerCase().endsWith( endsWith );
        }

    public static void main( String[] args )
        {
        if ( DEBUGGING )
            {
            // find all just the files listed, case insensitive.
            FilenameFilter f = new EndsWithFilter( "filter.class" );

            String[] filenames = new File( "C:/com/mindprod/filter" )
                    .list( f );

            for ( String filename : filenames )
                {
                System.out.println( filename );
                }
            }
        }
    }