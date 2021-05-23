package mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class ProductController {

     @Autowired
     ProductRepository productRepository;

    @RequestMapping(value = "/products/checkAndModifyStock",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")

    public boolean checkAndModifyStock(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
            boolean status = false;
            Long productId = Long.valueOf(request.getParameter("productId"));
            int qty = Integer.parseInt(request.getParameter("qty"));

            Product product = productRepository.findByProductId(productId);

            if(product.getStock() >= qty) {
                product.setStock(product.getStock() - qty);
                productRepository.save(product);
                status = true;
            }

            return status;
        }
 }
