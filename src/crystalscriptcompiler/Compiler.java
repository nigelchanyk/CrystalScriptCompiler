/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import crystalscriptcompiler.logic.DependencyLoader;
import crystalscriptcompiler.syntaxtree.ParseTreeRoot;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author User
 */
public class Compiler {
	
	private Namespace globalNamespace;
	private File rootFile;

	private DependencyLoader dependencyLoader = new DependencyLoader();
	
	public Compiler(File file) throws IOException {
		rootFile = file.getCanonicalFile();
		globalNamespace = new Namespace(rootFile.getParentFile());
	}

	public void start() throws Exception {
		// Step 1: Build syntax trees
		ParseTreeRoot rootTree = buildTree(rootFile);
		globalNamespace.add(Helper.getModuleName(rootFile), rootTree);
		dependencyLoader.start(globalNamespace, rootTree);

		// Step 2: Build symbol table for class/interface declarations
	}
	
	private ParseTreeRoot buildTree(File file) throws Exception {
		Scanner scanner = new Scanner(new FileReader(file));
		Parser parser = new Parser(scanner);
		parser.parse();
		return parser.getRoot();
	}
}
