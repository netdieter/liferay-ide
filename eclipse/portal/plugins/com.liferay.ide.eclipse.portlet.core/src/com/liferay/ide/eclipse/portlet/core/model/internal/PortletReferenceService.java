/*******************************************************************************
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
 * Contributors:
 *               Kamesh Sampath - initial implementation
 *******************************************************************************/

package com.liferay.ide.eclipse.portlet.core.model.internal;

import static org.eclipse.sapphire.modeling.util.MiscUtil.equal;

import com.liferay.ide.eclipse.portlet.core.model.IPortlet;
import com.liferay.ide.eclipse.portlet.core.model.IPortletApp;

import org.eclipse.sapphire.services.ReferenceService;

/**
 * @author <a href="mailto:kamesh.sampath@accenture.com">Kamesh Sampath</a>
 */

public final class PortletReferenceService extends ReferenceService {

	private static final String QUERY_BY_NAME = "portlet-name";
	private static final String QUERY_BY_DISPLAY_NAME = "display-name";

	/**
	 * 
	 */
	@Override
	public Object resolve( final String reference ) {
		final IPortletApp portletApp = context( IPortletApp.class );

		if ( portletApp != null ) {
			for ( IPortlet portlet : portletApp.getPortlets() ) {
				if ( params() == null || QUERY_BY_NAME.equals( param( "0" ) ) ) {
					if ( equal( portlet.getPortletName().getContent(), reference ) ) {
						return portlet;
					}
				}
				else if ( params() == null || QUERY_BY_DISPLAY_NAME.equals( param( "0" ) ) ) {
					if ( equal( portlet.getPortletName().getContent(), reference ) ) {
						return portlet;
					}
				}
			}
		}

		return null;
	}

}
