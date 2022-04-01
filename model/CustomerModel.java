package sudeep.servlet.hrcproject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerModel {
	private ArrayList<String> mapcustomerList = new ArrayList<String>();

	public void setCustomerList(ArrayList<String> customer) {
		this.mapcustomerList = customer;
	}

	public Map<String, ArrayList<String>> getCustomerMap(ArrayList<String> customer) {
		this.setCustomerList(customer);
		Map<String, ArrayList<String>> mapCustomer = new HashMap<String, ArrayList<String>>();
		mapCustomer.put("customer_number_list", this.mapcustomerList);
		return mapCustomer;
	}
}
