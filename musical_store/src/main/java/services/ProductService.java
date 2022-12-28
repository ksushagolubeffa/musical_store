package services;

import jakarta.servlet.http.Part;
import models.Product;
import repositories.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private static final ProductRepository repository = new ProductRepository();

    public void saveProduct(Product product) {
        repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }

        throw new IllegalArgumentException("No such product");
    }

    public void updateProduct(Product product) {
        repository.update(product);
    }

    public void deleteProduct(Long id) {
        repository.delete(id);
    }

    public void handleUpdateProduct(String name, String description, Integer price, Part image, String fileName, Product product) {
        try {
            if (image.getSubmittedFileName().length() > 0) {

                Product newProduct = Product.builder()
                        .id(product.getId())
                        .name(name)
                        .description(description)
                        .price(price)
                        .image(image.getInputStream().readAllBytes())
                        .imageName(fileName)
                        .build();

                updateProduct(newProduct);

            } else {
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);

                updateProduct(product);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
