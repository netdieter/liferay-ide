/*******************************************************************************
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************/

package com.liferay.ide.eclipse.portlet.ui.navigator;

import com.liferay.ide.eclipse.portlet.ui.navigator.actions.OpenPortletResourceAction;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;

/**
 * @author <a href="mailto:kamesh.sampath@hotmail.com">Kamesh Sampath</a>
 */
public class PortletResourcesActionProvider extends CommonActionProvider
{

    private OpenPortletResourceAction openAction;

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
     */
    @Override
    public void init( ICommonActionExtensionSite aSite )
    {
        openAction = new OpenPortletResourceAction();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.actions.ActionGroup#setContext(org.eclipse.ui.actions.ActionContext)
     */
    @Override
    public void setContext( ActionContext context )
    {
        if( ( context != null ) && ( context.getSelection() instanceof IStructuredSelection ) )
        {
            IStructuredSelection selection = (IStructuredSelection) context.getSelection();
            this.openAction.selectionChanged( selection );
        }
        
        super.setContext( context );
    }

    @Override
    public void fillContextMenu( IMenuManager menuManager )
    {
        ActionContext context = this.getContext();
        if( ( context == null ) || context.getSelection().isEmpty() )
        {
            return;
        }

        if( this.openAction.isEnabled() )
        {
            menuManager.insertAfter( ICommonMenuConstants.GROUP_OPEN, this.openAction );
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
     */
    @Override
    public void fillActionBars( IActionBars actionBars )
    {
        if( this.openAction.isEnabled() )
        {
            actionBars.setGlobalActionHandler( ICommonActionConstants.OPEN, this.openAction );
        }
    }

}
