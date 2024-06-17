package mas.project.web.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mas.project.model.Invoice;
import mas.project.service.InvoiceService;
import mas.project.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/invoice")
@Data
@Slf4j
public class InvoiceController {

    private InvoiceService invoiceService;
    private OrderService orderService;

    public InvoiceController(InvoiceService invoiceService, OrderService orderService) {
        this.invoiceService = invoiceService;
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Invoice> getInvoiceByOrderId(@PathVariable UUID orderId) {
        var invoice = invoiceService.getInvoiceByOrderId(orderId);
        if (invoice == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
}
