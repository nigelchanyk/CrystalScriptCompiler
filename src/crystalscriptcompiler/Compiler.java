/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import crystalscriptcompiler.helpers.Helper;
import crystalscriptcompiler.logic.DeclarationScanner;
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
	private DeclarationScanner declarationScanner = new DeclarationScanner();
	
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
		symbolTableBuilder.createTables(globalNamespace);

		// Step 3: Fill symbol table with class/interface/method/extern declarations
		declarationScanner.start(globalNamespace);

		// Step 4: Link dependent symbol tables (for dependency import)
		symbolTableBuilder.linkDependentTables(globalNamespace);

		// Step 5: Build inheritance tree (Required before step 6 to prevent circular inheritance)
		symbolTableBuilder.createInheritanceTree(globalNamespace);

		// Step 6: Link inherited symbol tables
		symbolTableBuilder.linkInheritedTables(globalNamespace);

		// Step 7: Fill symbol table with variables

		// Step 8: Validation

		// Step 9: Compile to JavaScript
	}
	
	private ParseTreeRoot buildTree(File file) throws Exception {
		Scanner scanner = new Scanner(new FileReader(file));
		Parser parser = new Parser(scanner);
		parser.parse();
		return parser.getRoot();
	}
}
