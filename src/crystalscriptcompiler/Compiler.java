/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import crystalscriptcompiler.logic.DependencyLoader;
import crystalscriptcompiler.logic.SymbolTableBuilder;
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
	private SymbolTableBuilder symbolTableBuilder = new SymbolTableBuilder();
	
	public Compiler(File file) throws IOException {
		rootFile = file.getCanonicalFile();
		globalNamespace = new Namespace(rootFile.getParentFile());
	}

	public void start() throws Exception {
		// Step 1: Build syntax trees
		ParseTreeRoot rootTree = buildTree(rootFile);
		globalNamespace.add(Helper.getModuleName(rootFile), rootTree);
		dependencyLoader.start(globalNamespace, rootTree);

		// Step 2: Create symbol table
		symbolTableBuilder.start(globalNamespace);

		// Step 3: Fill symbol table with class/interface/method/extern declarations

		// Step 4: Link dependent symbol tables (for dependency import)

		// Step 5: Fill symbol table with variables

		// Step 6: Validation

		// Step 7: Compile to JavaScript
	}
	
	private ParseTreeRoot buildTree(File file) throws Exception {
		Scanner scanner = new Scanner(new FileReader(file));
		Parser parser = new Parser(scanner);
		parser.parse();
		return parser.getRoot();
	}
}
