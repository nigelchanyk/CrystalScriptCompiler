/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import java.io.FileReader;

/**
 *
 * @author User
 */
public class CrystalScriptCompiler {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		if (args.length == 0) {
			System.err.println(StringResources.ERROR_NO_ARGUMENTS);
			System.err.println(StringResources.HINT_SIMPLE_USAGE);
			System.exit(-1);
		}

		try {
			Scanner scanner = new Scanner(new FileReader(args[0]));
			Parser parser = new Parser(scanner);
			ModuleAnalyzer analyzer = new ModuleAnalyzer(parser.parse());
		} catch (Exception e) {
			System.err.println("[Error]" + e.getMessage());
		}
	}
}
