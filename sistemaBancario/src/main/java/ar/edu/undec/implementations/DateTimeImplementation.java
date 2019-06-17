package ar.edu.undec.implementations;

import java.time.LocalDate;

import ar.edu.undec.interfaces.IDateTime;

public class DateTimeImplementation implements IDateTime{

	@Override
	public LocalDate getDate() {
		return LocalDate.now();
	}

}
