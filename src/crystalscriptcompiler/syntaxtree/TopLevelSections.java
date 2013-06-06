/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree;

/**
 *
 * @author User
 */
public class TopLevelSections extends SequentialCollection<TopLevelSection> {

	public TopLevelSections() {
	}
	
	public TopLevelSections(TopLevelSection section) {
		super(section);
	}

	public TopLevelSections(TopLevelSection section, TopLevelSections next) {
		super(section, next);
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		int index = 0;
		for (TopLevelSection section : this)
			section.addVariablesToTable(index++);
	}
	
}
