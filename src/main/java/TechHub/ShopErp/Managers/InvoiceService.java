package TechHub.ShopErp.Managers;

import java.util.List;

import TechHub.ShopErp.tables.Invoice;

public interface InvoiceService {
	
	    public Invoice saveInvice(Invoice invoice);
	    public List<Invoice> getAllInvoices();
	    public Invoice getInvoiceById(Long id);
	    public void deleteInvoiceById(Long id);
	    public void updateInvoice(Invoice invoice);

}
