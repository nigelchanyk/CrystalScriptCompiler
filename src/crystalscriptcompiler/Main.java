/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import java.io.File;
import java.io.FileReader;

/**
 *
 * @author User
 */
public class Main {

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
			Compiler compiler = new Compiler(new File(args[0]));
			compiler.start();
		} catch (Exception e) {
			System.err.println("[Error] " + e.getMessage());
			e.printStackTrace(System.err);
		}
	}
}
