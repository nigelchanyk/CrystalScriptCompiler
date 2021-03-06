/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import crystalscriptcompiler.helpers.Helper;
import crystalscriptcompiler.logic.DeclarationScanner;
import crystalscriptcompiler.logic.DependencyLoader;
import crystalscriptcompiler.logic.SymbolTableBuilder;
import crystalscriptcompiler.logic.Validator;
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
	private Validator validator = new Validator();
	
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

		// Step 3: Fill symbol table with class/interface/extern declarations
		declarationScanner.scanTypes(globalNamespace);

		// Step 4: Link dependent symbol tables (for dependency import)
		symbolTableBuilder.linkDependentTables(globalNamespace);

		// Step 5: Build inheritance tree (Required before step 6 to prevent circular inheritance)
		symbolTableBuilder.createInheritanceTree(globalNamespace);

		// Step 6: Link inherited symbol tables
		symbolTableBuilder.linkInheritedTables(globalNamespace);

		// Step 7: Determine class/interface type reference
		declarationScanner.scanReference(globalNamespace);

		// Step 8: Fill symbol table with methods
		declarationScanner.scanMethodDeclarations(globalNamespace);

		// Step 9: Fill symbol table with variables
		declarationScanner.scanVariableDeclarations(globalNamespace);

		// Step 10: Validate modifiers
		

		// Step 10: Validation
		validator.validate(globalNamespace);

		// Step 11: Compile to JavaScript
	}
	
	private ParseTreeRoot buildTree(File file) throws Exception {
		Scanner scanner = new Scanner(new FileReader(file));
		Parser parser = new Parser(scanner);
		parser.parse();
		return parser.getRoot();
	}

}
