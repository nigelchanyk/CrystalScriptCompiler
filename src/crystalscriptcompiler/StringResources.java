/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import java.util.Collection;

/**
 *
 * @author User
 */
public class StringResources {
	
	public static final String ERROR_NO_ARGUMENTS = "No arguments provided.";
	public static final String ERROR_MULTIPLE_ACCESS_MODIFIERS = "Only one access modifier is allowed.";
	
	public static final String HINT_SIMPLE_USAGE = "Usage: java -jar CrystalScriptCompiler.jar [File].";
	
	public static String errorFileNotFound(String fileName) {
		return "[Error] Unable to locate file: " + fileName;
	}

	public static String errorRead(String fileName) {
		return "[Error] Unable to read file: " + fileName;
	}

	public static String errorParseDependencies(Collection<Exception> exceptions) {
		StringBuilder sb = exceptions.size() > 1 ? new StringBuilder("Multiple errors have been detected while parsing dependencies:\n") : new StringBuilder();

		for (Exception exception : exceptions)
			sb.append(exception.getMessage()).append('\n');

		return sb.toString();
	}

	public static String errorDuplicateDeclaration(String id) {
		return "Duplicate declaration detected for " + id;
	}

	public static String errorReferenceNotFound(String fullName) {
		return "Reference to " + fullName + " cannot be found.";
	}

	public static String errorReferenceNotFound(String name, String scope) {
		return "Reference to " + name + " cannot be found in " + scope + ".";
	}

	public static String errorNotInheritable(String typeName, String expectedKind) {
		return typeName + " is not " + prependArticle(expectedKind) + ", thus, it cannot be inherited.";
	}

	public static String errorNotImplementable(String typeName) {
		return typeName + " is not an interface, thus, it cannot be implemented.";
	}

	public static String errorCircularInheritance(String className, String parentName) {
		return "Circular subclass detected. " + className + " cannot be a subclass of " + parentName + " because " + parentName + " is already a subclass of " + className + ".";
	}

	public static String errorTypeMismatch(String expected, String given) {
		return "Expecting " + expected + ", but " + given + " is detected.";
	}

	public static String errorDuplicateSignature(String method) {
		return "Method " + method + " has more than one equivalent signature.";
	}

	public static String errorInvalidModifier(String modifier) {
		return "Modifier " + modifier + " is not allowed in this context.";
	}

	public static String errorModifierConflict(String m1, String m2) {
		return "Modifiers " + m1 + " and " + m2 + " cannot be used at the same time.";
	}

	public static String errorAccessViolation(String target, String accessModifier) {
		return target + " is " + accessModifier + " and cannot be accessed in current context";
	}

	private static String prependArticle(String word) {
		// There are exceptions, but due to its complexity, we shall ignore them for now.
		switch (word.toLowerCase().charAt(0)) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				return "an " + word;
		}
		return "a " + word;
	}
}
