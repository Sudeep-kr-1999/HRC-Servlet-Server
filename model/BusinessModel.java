package sudeep.servlet.hrcproject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BusinessModel {
	private ArrayList<String> mapbusinessList = new ArrayList<String>();

	public void setBusinessList(ArrayList<String> business) {
		this.mapbusinessList = business;
	}

	public Map<String, ArrayList<String>> getBusinessMap(ArrayList<String> business) {
		this.setBusinessList(business);
		Map<String, ArrayList<String>> mapBusiness = new HashMap<String, ArrayList<String>>();
		mapBusiness.put("business_code_list", this.mapbusinessList);
		return mapBusiness;
	}

}
