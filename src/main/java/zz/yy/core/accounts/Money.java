package zz.yy.core.accounts;

import java.io.Serializable;

public class Money implements Serializable {

	public static final Money ZERO = Money.build(0D);
	Double amount;

	public Money(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {
		return amount;
	}

	public static Money build(Integer amount) {
		return new Money(new Double(amount));
	}

	public static Money build(Double amount) {
		return new Money(amount);
	}

	public boolean isNegative() {
		return amount < 0;
	}

	public boolean isPositive() {
		return amount > 0;
	}

	public boolean isZero() {
		return amount == 0;
	}

	public Money abs() {
		return Money.build(Math.abs(amount));
	}

	public Money add(Money amountToAdd) {
		return Money.build(this.amount + amountToAdd.amount);
	}

	public Money substract(Money amountToSubstract) {
		return Money.build(this.amount - amountToSubstract.amount);
	}

	@Override
	public String toString() {
		return amount.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Money)) {
			return false;
		}
		Money otherMoney = (Money) obj;
		return amount.equals(otherMoney.amount);
	}
}
