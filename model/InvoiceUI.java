package sudeep.servlet.hrcproject.model;

import java.util.HashMap;
import java.util.Map;

public class InvoiceUI {
	private String sl_no;
	private String business_code;
	private String cust_number;
	private String clear_date;
	private String business_year;
	private String doc_id;
	private String posting_date;
	private String document_create_date;
	private String due_in_date;
	private String invoice_currency;
	private String document_type;
	private String posting_id;
	private String total_open_amount;
	private String baseline_create_date;
	private String cust_payment_terms;
	private String invoice_id;
	private String aging_bucket;
	
	public void setAgingBucket(String value) {
		this.aging_bucket=value;
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

	public Map<String, String> invoiceListMap() {
		Map<String, String> mapUI = new HashMap<String, String>();
		mapUI.put("sl_no", this.sl_no);
		mapUI.put("business_code", this.business_code);
		mapUI.put("cust_number", this.cust_number);
		mapUI.put("clear_date", this.clear_date);
		mapUI.put("business_year", this.business_year);
		mapUI.put("doc_id", this.doc_id);
		mapUI.put("posting_date", this.posting_date);
		mapUI.put("document_create_date", this.document_create_date);
		mapUI.put("due_in_date", this.due_in_date);
		mapUI.put("invoice_currency", this.invoice_currency);
		mapUI.put("document_type", this.document_type);
		mapUI.put("posting_id", this.posting_id);
		mapUI.put("total_open_amount", this.total_open_amount);
		mapUI.put("baseline_create_date", this.baseline_create_date);
		mapUI.put("cust_payment_terms", this.cust_payment_terms);
		mapUI.put("invoice_id", this.invoice_id);
		mapUI.put("aging_bucket", this.aging_bucket);
		return mapUI;
		
	}
}
