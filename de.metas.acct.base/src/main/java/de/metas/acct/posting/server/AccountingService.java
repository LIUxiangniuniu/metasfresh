package de.metas.acct.posting.server;

import org.adempiere.acct.api.IPostingRequestBuilder.PostImmediate;
import org.adempiere.acct.api.IPostingService;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.util.Services;
import org.compiere.util.Env;
import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import de.metas.Profiles;
import de.metas.acct.posting.DocumentPostRequest;
import de.metas.acct.posting.DocumentPostRequestHandler;
import de.metas.logging.LogManager;

/*
 * #%L
 * de.metas.acct.base
 * %%
 * Copyright (C) 2018 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

@Component
@Profile(Profiles.PROFILE_AccountingService)
public class AccountingService implements DocumentPostRequestHandler
{
	private static final Logger logger = LogManager.getLogger(AccountingService.class);

	@Override
	public void handleRequest(final DocumentPostRequest request)
	{
		logger.debug("Posting: {}", request);

		final IPostingService postingService = Services.get(IPostingService.class);
		postingService.newPostingRequest()
				.setContext(Env.getCtx(), ITrx.TRXNAME_None)
				.setAD_Client_ID(request.getAdClientId())
				.setDocument(request.getRecord().getAD_Table_ID(), request.getRecord().getRecord_ID())
				.setForce(request.isForce())
				.setFailOnError(true)
				.setPostWithoutServer() // we are on server side now, so don't try to contact the server again
				.setPostImmediate(PostImmediate.Yes) // make sure we are posting it immediate
				//
				// Execute the posting
				.postIt();
	}
}
