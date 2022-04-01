package sudeep.servlet.hrcproject.model;

import java.util.HashMap;
import java.util.Map;

public class InvoiceListModel {
	private String sl_no;
	private String business_code;
	private String cust_number;
	private String clear_date;
	private String business_year;
	private String doc_id;
	private String posting_date;
	private String document_create_date;
	private String document_create_date1;
	private String due_in_date;
	private String invoice_currency;
	private String document_type;
	private String posting_id;
	private String area_business;
	private String total_open_amount;
	private String baseline_create_date;
	private String cust_payment_terms;
	private String invoice_id;
	private String isOpen;
	private String predicted;
	private String is_deleted;
	private String business_name;
	private String name_customer;

	public void setBusinessName(String businessName) {
		this.business_name = businessName;
	}

	public void setNameCustomer(String nameCustomer) {
		this.name_customer = nameCustomer;
	}

	public void setSlNumber(String slNumber) {
		this.sl_no = slNumber;
	}

	public void setBusinessCode(String businessCode) {
		this.business_code = businessCode;
	}

	public void setCustomerNumber(String customerNumber) {
		this.cust_number = customerNumber;
	}

	public void setClearDate(String clearDate) {
		this.clear_date = clearDate;
	}

	public void setBusinessYear(String businessYear) {
		this.business_year = businessYear;
	}

	public void setDocID(String docId) {
		this.doc_id = docId;
	}

	public void setPostingDate(String postingDate) {
		this.posting_date = postingDate;
	}

	public void setDocumentCreateDate(String documentCreateDate) {
		this.document_create_date = documentCreateDate;
	}

	public void setDocumentCreateDate1(String documentCreateDate1) {
		this.document_create_date1 = documentCreateDate1;
	}

	public void setDueInDate(String dueInDate) {
		this.due_in_date = dueInDate;
	}

	public void setInvoiceCurrency(String invoiceCurrency) {
		this.invoice_currency = invoiceCurrency;
	}

	public void setDocumentType(String documentType) {
		this.document_type = documentType;
	}

	public void setPostingID(String postingID) {
		this.posting_id = postingID;
	}

	public void setAreaBusiness(String areaBusinss) {
		this.area_business = areaBusinss;
	}

	public void setTotalOpenAmount(String totalOpenAmount) {
		this.total_open_amount = totalOpenAmount;
	}

	public void setBaselineCreateDate(String baselineCreateDate) {
		this.baseline_create_date = baselineCreateDate;
	}

	public void setCustomerPaymentTerms(String customerPaymentTerms) {
		this.cust_payment_terms = customerPaymentTerms;
	}

	public void setInvoiceID(String invoiceId) {
		this.invoice_id = invoiceId;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public void setPredicted(String predicted) {
		this.predicted = predicted;
	}

	public void setIsDeleted(String isDeleted) {
		this.is_deleted = isDeleted;
	}

	public Map<String, String> invoiceListMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sl_no", this.sl_no);
		map.put("business_code", this.business_code);
		map.put("cust_number", this.cust_number);
		map.put("clear_date", this.clear_date);
		map.put("business_year", this.business_year);
		map.put("doc_id", this.doc_id);
		map.put("posting_date", this.posting_date);
		map.put("document_create_date", this.document_create_date);
		map.put("document_create_date1", this.document_create_date1);
		map.put("due_in_date", this.due_in_date);
		map.put("invoice_currency", this.invoice_currency);
		map.put("document_type", this.document_type);
		map.put("posting_id", this.posting_id);
		map.put("area_business", this.area_business);
		map.put("total_open_amount", this.total_open_amount);
		map.put("baseline_create_date", this.baseline_create_date);
		map.put("cust_payment_terms", this.cust_payment_terms);
		map.put("invoice_id", this.invoice_id);
		map.put("isOpen", this.isOpen);
		map.put("predicted", this.predicted);
		map.put("is_deleted", this.is_deleted);
		map.put("business_name", this.business_name);
		map.put("name_customer", this.name_customer);
		return map;
	}

}
