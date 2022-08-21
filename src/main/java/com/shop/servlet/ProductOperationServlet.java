package com.shop.servlet;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.shop.dao.BrandDaoImp;
import com.shop.dao.TypeDaoImp;
import com.shop.dao.ProductDaoImp;
import com.shop.pojo.Brand;
import com.shop.pojo.Type;
import com.shop.pojo.Product;

/**
 * Servlet implementation class ProductOperationServlet
 */
@MultipartConfig
@WebServlet("/ProductOperationServlet")
public class ProductOperationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductOperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if (action.equals("delete")) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = new ProductDaoImp().getProductById(productId);
//			String productImage=product.getProductPhoto();
//			File f=new File("C:\\admin\\webapp\\productImages\\"+productImage);
//			
//			  f.delete();
//			 
//			
            boolean flag = false;

            flag = new ProductDaoImp().deleteProductById(productId);
            if (flag == true) {
                session.setAttribute("message", "Product Deleted Successfully..!");
                response.sendRedirect("index.jsp");
            } else {
                session.setAttribute("message", "Failed to Delete Product..!");
                response.sendRedirect("index.jsp");
            }

        } else if (action.equals("deleteBrand")) {
            int brandId = Integer.parseInt(request.getParameter("brandId"));
            int totalProduct = new ProductDaoImp().getTotalProductOfBrandById(brandId);
            if (totalProduct > 0) {
                List<Product> product = new ProductDaoImp().getProductByBrand(brandId);
                for (Product p : product) {
                    File f = new File("C:\\admin\\webapp\\productImages\\" + p.getProductPhoto());

                    f.delete();

                }
                boolean flag = new ProductDaoImp().deleteProductByBrandId(brandId);
                if (flag == true) {
                    boolean f = new BrandDaoImp().deleteBrandById(brandId);
                    if (f == true) {
                        session.setAttribute("message", "Type Deleted Successfully..!");
                        response.sendRedirect("index.jsp");
                    } else {
                        session.setAttribute("message", "Failed to Delete Type..!");
                        response.sendRedirect("index.jsp");
                    }
                } else {
                    session.setAttribute("message", "Failed to Delete Type..!");
                    response.sendRedirect("index.jsp");
                }
            } else {

                boolean f = new BrandDaoImp().deleteBrandById(brandId);
                if (f == true) {

                    session.setAttribute("message", "Brand Deleted Successfully..!");
                    response.sendRedirect("index.jsp");
                } else {
                    session.setAttribute("message", "Failed to Delete Brand..!");
                    response.sendRedirect("index.jsp");
                }
            }
        } else if (action.equals("deleteType")) {
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            int totalProduct = new ProductDaoImp().getTotalProductOfTypeById(typeId);
            if (totalProduct > 0) {
                List<Product> product = new ProductDaoImp().getProductByType(typeId);
                for (Product p : product) {
                    File f = new File("C:\\admin\\webapp\\productImages\\" + p.getProductPhoto());

                    f.delete();

                }
                boolean flag = new ProductDaoImp().deleteProductByTypeId(typeId);
                if (flag == true) {
                    boolean f = new TypeDaoImp().deleteTypeById(typeId);
                    if (f == true) {
                        session.setAttribute("message", "Brand Deleted Successfully..!");
                        response.sendRedirect("index.jsp");
                    } else {
                        session.setAttribute("message", "Failed to Delete Brand..!");
                        response.sendRedirect("index.jsp");
                    }
                } else {
                    session.setAttribute("message", "Failed to Delete Brand..!");
                    response.sendRedirect("index.jsp");
                }
            } else {

                boolean f = new TypeDaoImp().deleteTypeById(typeId);
                if (f == true) {

                    session.setAttribute("message", "Brand Deleted Successfully..!");
                    response.sendRedirect("index.jsp");
                } else {
                    session.setAttribute("message", "Failed to Delete Brand..!");
                    response.sendRedirect("index.jsp");
                }
            }
        }


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if (action.equals("addBrand")) {
            String brandTitle = request.getParameter("brandTitle");
            String brandDescription = request.getParameter("brandDescription");

            Brand brand = new Brand(brandTitle, brandDescription);
            BrandDaoImp brandDaoImp = new BrandDaoImp();
            boolean flag = brandDaoImp.addBrand(brand);

            if (flag == true) {
                session.setAttribute("message", "Brand Added Successfully..!");
                response.sendRedirect("admin.jsp");
            } else {
                session.setAttribute("message", "Failed to add brand..!");
                response.sendRedirect("admin.jsp");
            }

        }
        System.out.println("action"+action);

        if (action.equals("addType")) {
            String typeTitle = request.getParameter("typeTitle");
            String typeDescription = request.getParameter("typeDescription");
            System.out.println("typeTitle"+typeTitle);
            System.out.println("typeDescription"+typeDescription);
            Type type = new Type(typeTitle, typeDescription);
            System.out.println("type"+type.toString());
            TypeDaoImp clothDaoImp = new TypeDaoImp();
            boolean flag = clothDaoImp.addType(type);

            if (flag == true) {
                session.setAttribute("message", "Type Added Successfully..!");
                response.sendRedirect("admin.jsp");
            } else {
                session.setAttribute("message", "Failed to add Type..!");
                response.sendRedirect("admin.jsp");
            }

        } else if (action.equals("addProduct")) {
            System.out.println("Add product clicked");
            String productTitle = request.getParameter("productTitle");
            String productDescription = request.getParameter("productDescription");
            int productPrice = Integer.parseInt(request.getParameter("productPrice"));
            int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
            int productDiscount = Integer.parseInt(request.getParameter("productDiscount"));
            int productBrandId = Integer.parseInt(request.getParameter("productBrandId"));
            int productTypeId = Integer.parseInt(request.getParameter("productTypeId"));
            System.out.println("productDescription"+productDescription);
            Part part = request.getPart("productPic");


//			String fileName=part.getSubmittedFileName();
//			
//			String uploadPath="C:\\admin\\webapp\\productImages\\"+fileName;
            byte[] data = null;
            InputStream is = null;
            String base64Image = null;
            try {

                is = part.getInputStream();

                data = IOUtils.toByteArray(is);
                is.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Product product = new Product(productPrice, productDiscount, productQuantity, productBrandId, productTitle, productDescription, data);
            product.setTypeId(productTypeId);
          //  product.setProductFor(productFor);

            ProductDaoImp pdao = new ProductDaoImp();
            System.out.println("ProductDaoImp  product"+product);
            boolean flag = pdao.addProduct(product);

            if (flag == true) {
                session.setAttribute("message", "Product Added Successfully..!");
                response.sendRedirect("admin.jsp");
            } else {
                session.setAttribute("message", "Failed to add product..!");
                response.sendRedirect("admin.jsp");
            }


        } else if (action.equals("searchProduct")) {
            RequestDispatcher rd;
            String search = request.getParameter("search");
            request.setAttribute("enteredText", search);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } else if (action.equals("updateProduct")) {
            boolean flag = false;
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productTitle = request.getParameter("productTitle");
            String productDescription = request.getParameter("productDescription");
            String productPrice = request.getParameter("productPrice");
            String productDiscount = request.getParameter("productDiscount");
            String productQuantity = request.getParameter("productQuantity");
            Part part = request.getPart("updateProductPhoto");
            System.out.println("part"+part);
            byte[] data = null;
            InputStream is = null;
            String base64Image = null;


            try {
                is = part.getInputStream();

                data = IOUtils.toByteArray(is);
                System.out.println("data"+data);


            } catch (Exception e) {
                e.printStackTrace();
            }

            Product product = new Product();
            ProductDaoImp pdao = new ProductDaoImp();
            product.setProductDescription(productDescription);
            product.setProductDiscount(Integer.parseInt(productDiscount));
            product.setProductId(productId);
            product.setProductPrice(Integer.parseInt(productPrice));
            product.setProductQuantity(Integer.parseInt(productQuantity));
            product.setProductTitle(productTitle);

            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            BufferedImage bImage2 = ImageIO.read(bis);
            System.out.println("bImage2"+bImage2);
            if(bImage2 == null){ //  when nothing is uploaded ,use previously uploaded photo of product
                product.setProductPhoto(pdao.getProductById(productId).getProductPhoto());

            }else {
                product.setProductPhoto(data);
            }
            is.close();
            bis.close();

            flag = pdao.updateProduct(product);

            if (flag == true) {
                session.setAttribute("message", "Product Updated Successfully..!");
                response.sendRedirect("index.jsp");
            } else {
                session.setAttribute("message", "Failed to Update Product..!");
                response.sendRedirect("index.jsp");
            }
        } else if (action.equals("searchSmartly")) {
            RequestDispatcher rd;
        //    String productFor = request.getParameter("productFT");
            String brandId = request.getParameter("brandId");
            String typeId = request.getParameter("typeId");
            String productRange = request.getParameter("priceRange");


          //  request.setAttribute("prodFor", productFor);
            request.setAttribute("bId", brandId);
            request.setAttribute("cId", typeId);
            request.setAttribute("prodRange", productRange);

            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

        }


    }


}



