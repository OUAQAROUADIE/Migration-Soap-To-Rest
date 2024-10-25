package hh.ma.ac.inpt.translationLayer;

import hh.ma.ac.inpt.domaine.Product;
import hh.ma.ac.inpt.domaine.ProductsWrapper;
import hh.ma.ac.inpt.exceptions.NoSuchProductException;
import hh.ma.ac.inpt.service.ProductManager;
import jakarta.jws.WebService;

@WebService(endpointInterface = "hh.ma.ac.inpt.service.ProductManager")
public class ProductManagerWrapper implements ProductManager {

    private final ProductManager proxy;

    public ProductManagerWrapper(ProductManager proxy) {
        this.proxy = proxy;
    }

    @Override
    public long addProduct(Product product) throws NoSuchProductException {
        return proxy.addProduct(product);
    }

    @Override
    public Product getProduct(long id) throws NoSuchProductException {
        return proxy.getProduct(id);
    }

    @Override
    public void DeleteProduct(long id) throws NoSuchProductException {
        proxy.DeleteProduct(id);
    }

    @Override
    public Product updateProduct(long id, Product productUpdates) throws NoSuchProductException {
        return proxy.updateProduct(id, productUpdates);
    }

    @Override
    public ProductsWrapper getAllProducts() {
        return proxy.getAllProducts();
    }
}
