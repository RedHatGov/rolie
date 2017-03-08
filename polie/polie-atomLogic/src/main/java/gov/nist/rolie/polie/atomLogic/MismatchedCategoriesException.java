package gov.nist.rolie.polie.atomLogic;

import org.w3.x2005.atom.CategoryDocument.Category;

public class MismatchedCategoriesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1334772837890779771L;

	
	private Category childCategory;
	
	public MismatchedCategoriesException(Category entryCategory)
	{
		this.childCategory= entryCategory;
	}
	
	public Category getChildCategory()
	{
		return childCategory;
	}
}
