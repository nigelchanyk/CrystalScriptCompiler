/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import crystalscriptcompiler.syntaxtree.ParseTreeRoot;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author User
 */
public class Compiler {
	
	private Namespace globalNamespace;
	private File rootFile;
	
	public Compiler(File file) {
		rootFile = file;
		globalNamespace = new Namespace(file.getParentFile());
	}

	public void start() throws Exception {
		ParseTreeRoot rootTree = buildTree(rootFile);
		globalNamespace.add(Helper.getModuleName(rootFile), rootTree);
		
	}
	
	private ParseTreeRoot buildTree(File file) throws Exception {
		Scanner scanner = new Scanner(new FileReader(file));
		Parser parser = new Parser(scanner);
		parser.parse();
		return parser.getRoot();
	}
}
