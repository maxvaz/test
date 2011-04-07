package zz.yy.core.transactionsproviders;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.accounts.Deposit;
import zz.yy.core.accounts.Money;
import zz.yy.core.accounts.Withdraw;

public class SantanderTransactionProvider implements TransactionProvider {

	private final InputStream dataInputStream;

	public SantanderTransactionProvider(InputStream dataInputStream) {
		this.dataInputStream = dataInputStream;
	}

	public Iterator<AccountTransaction> iterateTransactions() {

		try {
			Workbook wb = new HSSFWorkbook(dataInputStream);

			Sheet lastMovementsSheet = wb.getSheetAt(0);
			Iterator<Row> iterator = lastMovementsSheet.iterator();

			// TODO: Esto deberia hacerse iterando en vez de acumular todas las
			// transacciones
			List<AccountTransaction> accountTransactions = new ArrayList<AccountTransaction>();

			while (iterator.hasNext()) {
				Row row = iterator.next();

				if (row.getRowNum() <= 6) {
					continue;
				}

				if (row.getCell(1, Row.RETURN_BLANK_AS_NULL) == null) {
					break;
				}

				Date date = row.getCell(1).getDateCellValue();
				String description = row.getCell(3).getStringCellValue();
				Money amount = Money.build(row.getCell(5).getNumericCellValue());

				// TODO: Refactorizar la construccion de transacciones
				if (amount.isNegative()) {
					Withdraw withdraw = new Withdraw(amount.abs(), date, description);
					accountTransactions.add(withdraw);
				} else {
					Deposit deposit = new Deposit(amount.abs(), date, description);
					accountTransactions.add(deposit);
				}
			}

			return accountTransactions.iterator();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
