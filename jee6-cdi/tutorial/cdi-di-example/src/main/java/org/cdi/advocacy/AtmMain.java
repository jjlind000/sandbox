package org.cdi.advocacy;

import java.math.BigDecimal;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;


public class AtmMain {

	public static void main(String[] args) throws Exception {


		//from https://elmland.blog/2017/09/11/boot-cdi-2-javase/
		//you can also disable discovery mode (ie not need a beans.xml)
		SeContainerInitializer initializer = SeContainerInitializer.newInstance();
		try (SeContainer container = initializer.initialize()) {
			/** do some stuff :) */
			AutomatedTellerMachine atm = container.select(AutomatedTellerMachine.class).get();
			atm.deposit(new BigDecimal("1.00"));
		}
	}
}
