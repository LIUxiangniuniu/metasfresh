package de.metas.contracts.refund.allqties.refundconfigchange;

import org.adempiere.util.Check;

import de.metas.contracts.refund.AssignmentToRefundCandidate;
import de.metas.contracts.refund.RefundConfig;
import de.metas.contracts.refund.RefundConfig.RefundBase;
import de.metas.money.Money;
import de.metas.quantity.Quantity;
import lombok.NonNull;

/*
 * #%L
 * de.metas.contracts
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

public class AmountRefundConfigChangeHandler extends RefundConfigChangeHandler
{
	public static AmountRefundConfigChangeHandler newInstance(
			@NonNull final RefundConfig currentRefundConfig)
	{
		return new AmountRefundConfigChangeHandler(currentRefundConfig);
	}

	private AmountRefundConfigChangeHandler(
			@NonNull final RefundConfig currentRefundConfig)
	{
		super(Check.assumeNotNull(currentRefundConfig, "currentRefundConfig may not be null"));
	}

	@Override
	public AssignmentToRefundCandidate createNewAssignment(@NonNull final AssignmentToRefundCandidate existingAssignment)
	{
		// note: currentRefundConfig can't be null
		final Money amountToApply = getCurrentRefundConfig()
				.getAmount()
				.subtract(getFormerRefundConfig().getAmount());

		final Quantity quantityAssigendToRefundCandidate = existingAssignment.getQuantityAssigendToRefundCandidate();

		final Money moneyToAssign = amountToApply.multiply(quantityAssigendToRefundCandidate.getAsBigDecimal());

		return new AssignmentToRefundCandidate(
				getCurrentRefundConfig().getId(),
				existingAssignment.getAssignableInvoiceCandidateId(),
				existingAssignment.getRefundInvoiceCandidate(),
				existingAssignment.getMoneyBase(),
				moneyToAssign,
				quantityAssigendToRefundCandidate,
				getCurrentRefundConfig().isIncludeAssignmentsWithThisConfigInSum());
	}

	@Override
	protected RefundBase getExpectedRefundBase()
	{
		return RefundBase.AMOUNT_PER_UNIT;
	}
}
