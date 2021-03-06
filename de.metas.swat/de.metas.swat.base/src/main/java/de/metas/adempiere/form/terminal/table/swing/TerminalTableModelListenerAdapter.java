package de.metas.adempiere.form.terminal.table.swing;

/*
 * #%L
 * de.metas.swat.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import de.metas.adempiere.form.terminal.table.ITerminalTableModelListener;

public class TerminalTableModelListenerAdapter implements ITerminalTableModelListener
{
	@Override
	public void fireRequestStopEditing()
	{
		// nothing
	}
	
	@Override
	public void fireTableStructureChanged()
	{
		// nothing
	}

	@Override
	public void fireTableRowsInserted(int rowFirst, int rowLast)
	{
		// nothing
	}

	@Override
	public void fireTableRowsUpdated(int rowFirst, int rowLast)
	{
		// nothing
	}

	@Override
	public void fireTableRowsDeleted(int rowFirst, int rowLast)
	{
		// nothing
	}

	@Override
	public void fireSelectionChanged()
	{
		// nothing
	}
}
