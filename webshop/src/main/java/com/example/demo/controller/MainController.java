package com.example.demo.controller;

import com.example.demo.model.Brand;
import com.example.demo.model.Carusel;
import com.example.demo.model.Category;
import com.example.demo.model.Product;

import com.example.demo.service.BrandService;
import com.example.demo.service.CaruselService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@Controller
public class MainController {
    @Value("${online-shop.filedownload.path}")
    private String fileDownloadPath;
    @Autowired
     private ProductService productService;

    private CaruselService caruselServise=new CaruselService();
    @Autowired
    private BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @RequestMapping(value = "/")
    public String main(ModelMap modelMap) {

        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        List<Brand> brands = brandService.getAll();
        List<Carusel> carusels=caruselServise.getCarusels(productService.getProductsBySale("SALE"));
        List<Carusel> caruselsNew=caruselServise.getCarusels(productService.getProductsBySale("NEW"));

        modelMap.addAttribute("carusels", carusels);
        modelMap.addAttribute("carusels1", caruselsNew);


        modelMap.addAttribute("brands", brands);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);


        return "index";
    }

    @RequestMapping(value = "/shop")
    public String home1(ModelMap modelMap) {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        List<Brand> brands = brandService.getAll();


        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("brands", brands);
        return "shop";
    }



    @ResponseBody
    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public byte[] getImageAsByteArray(@RequestParam("filename") String filename) throws Exception {
        InputStream in = new FileInputStream(fileDownloadPath + filename);

        return org.apache.commons.io.IOUtils.toByteArray(in);
    }


    @RequestMapping(value = "/product-details")
    public String toProductDetails(ModelMap modelMap) {

        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        List<Brand> brands = brandService.getAll();


        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("brands", brands);

        return "product-details";
    }


    @RequestMapping(value = "/checkout")
    public String toCheckout() {
        return "checkout";
    }

    @RequestMapping(value = "/cart")
    public String toCart() {
        return "cart";
    }


    @RequestMapping(value = "/blog")
    public String toBlog() {
        return "blog";
    }

    @RequestMapping(value = "/blog-single")
    public String toBlogSingle() {
        return "blog-single";
    }



    @RequestMapping(value = "/contact-us")
    public String toContactUs() {
        return "contact-us";
    }

    @RequestMapping(value = "/admin")
    public String toAdminJSP(ModelMap modelMap) {

        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        List<Brand> brands = brandService.getAll();
      //  List<Carousel> carousels = carouselService.getCarouselListOrderedByPosition();


        modelMap.addAttribute("brands", brands);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);
       // modelMap.addAttribute("carousels", carousels);
        return "admin";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "login";
    }

}
