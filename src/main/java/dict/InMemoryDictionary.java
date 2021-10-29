package dict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;


/**
 * Implements a persistent dictionary that can be held entirely in memory.
 * When flushed, it writes the entire dictionary back to a file.
 * 
 * The file format has one keyword per line:
 * <pre>word:def1:def2:def3,...</pre>
 * 
 * Note that an empty definition list is allowed (in which case the entry would have the form: <pre>word:</pre> 
 * 
 * @author talm
 *
 */
public class InMemoryDictionary extends TreeMap<String,String> implements PersistentDictionary  {
	private static final long serialVersionUID = 1L; // (because we're extending a serializable class)

	File dictfile;
	public InMemoryDictionary(File dictFile) {
		this.dictfile = dictfile;
	}
	
	@Override
	public void open() throws IOException {
		FileReader fr = new FileReader(dictfile);
		BufferedReader br = new BufferedReader(fr);

		String line;
		while ((line = br.readLine()) != null) { // read line by line
			String word;
			String def;
			int dividor = line.indexOf(':'); //split string by :
			if (dividor < 0) {
				word = line;
				def = null;
			} else {
				word = substring(0, dividor);
				def = substring(dividor+1,line.length());
			}
			put(key,value);
		}
		br.close();
	}

	@Override
	public void close() throws IOException {
		FileWriter fw = new FileWriter(dictfile);
		BufferedReader bw = new BufferedReader(fw);
		for (Map.Entry<String, String> entry : this.entrySet()) {
			bf.append(entry.getKey() + ':' + entry.getValau() + "\n");
		}
		bf.close();
	}

}
