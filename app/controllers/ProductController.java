/**
 * 
 */
package controllers;

import java.util.List;


import models.Product;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;


/**
 * @author dungvst
 *
 */
public class ProductController extends Controller {

	public Result list() {
		List<Product> products = Product.findAll();
		return ok(list.render(products));
	}

	public Result newProduct() {
		Form<Product> productForm = Form.form(Product.class);
		return ok(views.html.products.details.render(productForm));
	}

	public Result details(String ean) {
		Form<Product> productForm = Form.form(Product.class);
		 final Product product = Product.findByEan(ean);
		    if (product == null) {
		      return notFound(String.format("Product %s does not exist.", ean));
		    }

		    Form<Product> filledForm = productForm.fill(product);
		    return ok(views.html.products.details.render(filledForm));
	}

	public Result save() {
		Form<Product> productForm = Form.form(Product.class);
		Form<Product> boundForm = productForm.bindFromRequest();
		  if(boundForm.hasErrors()) {
		    flash("error", "Please correct the form below.");
		    return badRequest(views.html.products.details.render(boundForm));
		  }

		  Product product = boundForm.get();
		  product.save();
		  flash("success",
		      String.format("Successfully added product %s", product));

		  return redirect(routes.ProductController.list());
	}
	
	public Result delete(String ean) {
		  final Product product = Product.findByEan(ean);
		  if(product == null) {
		    return notFound(String.format("Product %s does not exists.", ean));
		  }
		  Product.remove(product);
		  return redirect(routes.ProductController.list());
		}

}
