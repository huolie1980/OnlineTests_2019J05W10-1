package com.acquisio.basic.java.question08;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * QUESTION 09: Lambdas (https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
 * Using JDK8 Lambdas, add the code to transform the input file to apply those rules.
 * 1- Keep lines where the amount is greater than or equals to 50$
 * 2- Add a Taxes column right after the Amount column, which is 15% of the Amount.
 * 3- Add a Total column right after the Taxes column, which is the sum of Amount and Taxes.
 * 4- Remove the ShoppingCartTitle columns.
 * <p>
 * The input file contains those columns
 * User,Amount,ShoppingCartTitle,NbItems
 * <p>
 * IMPORTANT: Add all missing javadoc and/or unit tests that you think is necessary.
 */
public class Lambdas {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Lambdas instance = new Lambdas();
        File input = new File(Thread.currentThread().getContextClassLoader().getResource("./carts.csv").toURI());
        File output = new File("./carts_output.csv");
        output.delete();

        instance.convertCarts(input, output);

        if (output.exists()) {
            Files.lines(output.toPath()).forEachOrdered(line -> System.out.println(line));
        }
    }

    void convertCarts(File input, File output) throws IOException {
        // TODO: Insert your code here.
    	List<InputFile> listInput = new ArrayList<>();
    	Files.readAllLines(input.toPath()).forEach(line-> {
    		String[] split = line.split(",");
    		listInput.add(new InputFile(split[0],Double.valueOf(split[1]),split[2],split[3]));
    	});
    	
    	List<String> listOutput = new ArrayList<>();
    	listInput.stream().filter(amount->amount.getAmount()>=50).forEach(amount->{
    		listOutput.add((new OutputFile(amount.getUser(),amount.getAmount(),(double)Math.round(amount.getAmount()*0.15*100)/100,(double)Math.round(amount.getAmount()*1.15*100)/100,amount.getNbItems())).toString());
    	});
    	Files.write(output.toPath(), listOutput, StandardCharsets.UTF_8);
    }
    class InputFile{
    	private String user;
    	private double amount;
    	private String shoppingCartTitle;
    	private String nbItems;
    	
    	
		public InputFile() {
			super();
		}
		
		public InputFile(String user, double amount, String shoppingCartTitle, String nbItems) {
			super();
			this.user = user;
			this.amount = amount;
			this.shoppingCartTitle = shoppingCartTitle;
			this.nbItems = nbItems;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getShoppingCartTitle() {
			return shoppingCartTitle;
		}
		public void setShoppingCartTitle(String shoppingCartTitle) {
			this.shoppingCartTitle = shoppingCartTitle;
		}
		public String getNbItems() {
			return nbItems;
		}
		public void setNbItems(String nbItems) {
			this.nbItems = nbItems;
		}
    }
    class OutputFile{
    	private String user;
    	private double amount;
    	private double taxes;
    	private double total;
    	private String nbItems;
		public OutputFile(String user, double amount, double taxes, double total, String nbItems) {
			super();
			this.user = user;
			this.amount = amount;
			this.taxes = taxes;
			this.total = total;
			this.nbItems = nbItems;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public double getTaxes() {
			return taxes;
		}
		public void setTaxes(double taxes) {
			this.taxes = taxes;
		}
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}
		public String getNbItems() {
			return nbItems;
		}
		public void setNbItems(String nbItems) {
			this.nbItems = nbItems;
		}
		@Override
		public String toString() {
			return user + "," + amount + "," + taxes + "," + total + "," + nbItems;
		}
    	
    	
    }
}
