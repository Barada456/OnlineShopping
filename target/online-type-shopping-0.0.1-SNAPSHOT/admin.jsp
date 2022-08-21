<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="com.shop.pojo.*" %>
<%@ page import="com.shop.dao.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.stream.Collectors" %>
<%--<%--%>
<%--    int productIdForUpdate=(int)session.getAttribute("productIdForUpdate");--%>
<%--    Product productForUpdate = null;--%>
<%--    if(productIdForUpdate!=0) {--%>
<%--        productForUpdate = new ProductDaoImp().getProductById(productIdForUpdate);--%>
<%--    }--%>
<%--%>--%>

<%
    Product productForUpdate = new Product();
    Brand pevioousBrand = new Brand();
    Type peviousType = new Type();
    User user2 = (User) session.getAttribute("active-user");
    if (user2 == null) {
        session.setAttribute("message", "You are not logged in! Login first...");
        response.sendRedirect("login.jsp");
        return;
    } else {
        if (user2.getUserType().equals("normal")) {
            session.setAttribute("message", "You are not Admin! Do not Access this...");
            session.removeAttribute("active-user");
            response.sendRedirect("login.jsp");
            return;
        }
    }
%>

<%
    List<Brand> li = new BrandDaoImp().getAllBrand();
    List<Type> lic = new TypeDaoImp().getAllType();
    List<Product> products = new ProductDaoImp().getAllProduct();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Page-ShopRight</title>
    <%@ include file="components/common_cs_js.jsp" %>

</head>
<body>
<%@ include file="components/navbar.jsp" %>

<div class="container admin mt-2">
    <%@ include file="components/message.jsp" %>
    <div class="row mt-3">
        <div class="col-md-3 " >
            <div class="card-option hover  card-1 "  data-toggle="modal" data-target="#show-users-modal">
                <div class="card-body   text-center">
                    <div class="container">
                        <img style="max-width:120px" class="img-fluid rounded-circle custom-bg" src="images/totalUser.png"
                             alt="users_pic">
                    </div>
                    <h1><%=new UserDaoImp().getTotalUser() %>
                    </h1>
                    <h2 class="text-uppercase text-muted">Users</h2>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card-option hover card-1" data-toggle="modal" data-target="#show-brand-modal">
                <div class="card-body text-center">
                    <div class="container">
                        <img style="max-width:120px" class="img-fluid rounded-circle custom-bg" src="images/brands.png"
                             alt="users_pic">
                    </div>
                    <h1><%=new BrandDaoImp().getTotalBrand() %>
                    </h1>
                    <h2 class="text-uppercase text-muted">Brands</h2>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card-option hover card-1" data-toggle="modal" data-target="#show-type-modal">
                <div class="card-body text-center">
                    <div class="container">
                        <img style="max-width:120px" class="img-fluid rounded-circle custom-bg " src="images/ware.png"
                             alt="users_pic">
                    </div>
                    <h1><%=new TypeDaoImp().getTotalType() %>
                    </h1>
                    <h2 class="text-uppercase text-muted">Types</h2>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card-option hover card-1" data-toggle="modal" data-target="#show-product-modal">
                <div class="card-body text-center">
                    <div class="container">
                        <img style="max-width:120px" class="img-fluid rounded-circle custom-bg" src="images/produts.png"
                             alt="users_pic">
                    </div>
                    <h1><%=new ProductDaoImp().getTotalProduct() %>
                    </h1>
                    <h2 class="text-uppercase text-muted">Products</h2>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-3">
            <div class="card-option hover card-1" data-toggle="modal" data-target="#total-order-modal">
                <div class="card-body text-center">
                    <div class="container">
                        <img style="max-width:120px" class="img-fluid rounded-circle custom-bg " src="images/totalorders.png"
                             alt="users_pic">
                    </div>
                    <h1 class="text-lowercase text-muted"><%=new OrderDaoImp().totalOrdersCount() %>
                    </h1>
                    <h2 class="text-uppercase text-muted">Total Orders</h2>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card-option hover card-1" data-toggle="modal" data-target="#add-brand-modal">
                <div class="card-body text-center">
                    <div class="container md-5">
                        <img style="max-width:120px" class="img-fluid rounded-circle custom-bg" src="images/add-brands.png"
                             alt="users_pic">
                    </div>
                    <p class="text-lowercase text-muted mt-2 " style="font-size:22px">click to add brand</p>
                    <h2 class="text-uppercase text-muted mt-1">Add Brands here</h2>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card-option hover card-1" data-toggle="modal" data-target="#add-type-modal">
                <div class="card-body text-center">
                    <div class="container md-5">
                        <img style="max-width:120px" class="img-fluid rounded-circle custom-bg" src="images/ware-add.png"
                             alt="users_pic">
                    </div>
                    <p class="text-lowercase text-muted mt-2 " style="font-size:22px">click to add type</p>
                    <h2 class="text-uppercase text-muted mt-1" style="font-size:29px">Add Category Types</h2>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card-option hover card-1" data-toggle="modal" data-target="#add-product-modal">
                <div class="card-body text-center">
                    <div class="container">
                        <img style="max-width:120px" class="img-fluid rounded-circle custom-bg" src="images/add-produts.png"
                             alt="users_pic">
                    </div>
                    <p class="text-lowercase text-muted mt-2 " style="font-size:22px">click to add product</p>
                    <h2 class="text-uppercase text-muted">Add Product</h2>
                </div>
            </div>
        </div>

    </div>

</div>

<!-- add brand modal -->

<div class="modal fade" id="add-brand-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white">
                <h5 class="modal-title" id="exampleModalLongTitle">Fill brand details.</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="ProductOperationServlet" method="post">
                    <input type="hidden" name="action" value="addBrand">
                    <div class="form-group">
                        <input type="text" class="form-control" name="brandTitle"
                               placeholder="Enter Brand Title here" required>
                    </div>

                    <div class="form-group">
                        <textarea style="height:250px" class="form-control" name="brandDescription"
                                  placeholder="Enter Brand Description here" required></textarea>
                    </div>

                    <div class="container text-center">
                        <button class="btn custom-bg text-white">Add Brand</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- end add brand modal -->

<!-- add type modal -->

<div class="modal fade" id="add-type-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white">
                <h5 class="modal-title" id="exampleModalLongTitle">Fill Type details.</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="ProductOperationServlet" method="post">
                    <input type="hidden" name="action" value="addType">
                    <div class="form-group">
                        <input type="text" class="form-control" name="typeTitle" placeholder="Enter Type Title here"
                               required>
                    </div>

                    <div class="form-group">
                        <textarea style="height:250px" class="form-control" name="typeDescription"
                                  placeholder="Enter Type Description here" required></textarea>
                    </div>

                    <div class="container text-center">
                        <button class="btn custom-bg text-white">Add Type</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- end add brand modal -->


<!-- add product modal -->
<div class="modal fade" id="add-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white">
                <h5 class="modal-title" id="exampleModalLongTitle">Add Product details.</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="addProduct">
                    <div class="form-group">
                        <input type="text" class="form-control" name="productTitle"
                               placeholder="Enter Product Title here" required>
                    </div>

                    <div class="form-group">
                        <textarea style="height:150px" class="form-control" name="productDescription"
                                  placeholder="Enter Product Description here" required></textarea>
                    </div>

                    <div class="form-group">
                        <input type="number" class="form-control" name="productPrice"
                               placeholder="Enter Product Price here" required>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="productQuantity"
                               placeholder="Enter Product Quantity here" required>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="productDiscount"
                               placeholder="Enter Product Discount in (%)" required>
                    </div>

                    <div class="form-group">
                        <select name="productBrandId" class="form-control">
                            <option value="0">Select Brand</option>
                            <%
                                for (Brand brand : li) {
                            %>
                            <option value="<%=brand.getBrandId()%>"><%=brand.getBrandTitle() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <select name="productTypeId" class="form-control">
                            <option value="0">Select Type</option>
                            <%
                                for (Type type : lic) {
                            %>
                            <option value="<%=type.getTypeId()%>"><%=type.getTypeTitle() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>

<%--                    <div class="form-group">--%>
<%--                        <select class="form-control text-center mt-3" id="type" name="productFor">--%>
<%--                            <option value="0">Select Type</option>--%>
<%--                            <option value="men">Men</option>--%>
<%--                            <option value="women">Women</option>--%>
<%--                            <option value="boy">Boy</option>--%>
<%--                            <option value="girl">Girl</option>--%>
<%--                        </select>--%>
<%--                    </div>--%>

                    <div class="form-group">
                        <label>Select Product Pic</label><br>
                        <input type="file" class="form-control" name="productPic" required>
                    </div>

                    <div class="container text-center">
                        <button class="btn custom-bg text-white">Add Product</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- end add product modal -->

<!-- show users modal -->

<div class="modal fade" id="show-users-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white text-center">
                <h5 class="modal-title" id="exampleModalLongTitle">Total Users</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table">
                        <thead class="custom-bg text-white">
                        <tr>
                            <th scope="col">User Id</th>
                            <th scope="col">Photo</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Mobile</th>
                            <th scope="col">Address</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            UserDaoImp udao = new UserDaoImp();
                            List<User> l = udao.getAllUser("normal");
                        %>

                        <%
                            for (User u : l) {
                        %>
                        <tr>
                            <th scope="row" class="mid-align"><%=u.getUserId() %>
                            </th>
                            <td><img style="max-width:70px" class="img-fluid rounded-circle"
                                     src="data:image/jpeg;base64,<%if(u!=null){ %><%=u.getUserPicInBase() %><%} %>"
                                     alt="users_pic"></td>
                            <td class="mid-align"><%=u.getUserName() %>
                            </td>
                            <td class="mid-align"><%=u.getUserEmail() %>
                            </td>
                            <td class="mid-align"><%=u.getUserPhone() %>
                            </td>
                            <td class="mid-align"><%=u.getUserAddress() %>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- end of show users modal -->

<!-- show brand modal -->

<div class="modal fade" id="show-brand-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white text-center">
                <h5 class="modal-title" id="exampleModalLongTitle">Total Brand</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table">
                        <thead class="custom-bg text-white">
                        <tr>
                            <th scope="col">Brand Id</th>
                            <th scope="col">Brand Name</th>
                            <th scope="col">Brand Description</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            BrandDaoImp cd = new BrandDaoImp();
                            List<Brand> lc = cd.getAllBrand();
                        %>

                        <%
                            for (Brand c : lc) {
                        %>
                        <tr>
                            <td class="mid-align"><%=c.getBrandId() %>
                            </td>
                            <td class="mid-align"><%=c.getBrandTitle() %>
                            </td>
                            <td class="mid-align"><%=c.getBrandDescription() %>
                            </td>
                            <td class="mid-align"><a
                                    href="ProductOperationServlet?brandId=<%=c.getBrandId()%>&action=deleteBrand"><input
                                    type="submit" class="btn btn-danger" value="Delete"></a></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ********************* -->

<!-- show Type modal -->

<div class="modal fade" id="show-type-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white text-center">
                <h5 class="modal-title" id="exampleModalLongTitle">Total Typees</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table">
                        <thead class="custom-bg text-white">
                        <tr>
                            <th scope="col">Type Id</th>
                            <th scope="col">Type Name</th>
                            <th scope="col">Type Description</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            TypeDaoImp cdd = new TypeDaoImp();
                            List<Type> lcc = cdd.getAllType();
                        %>

                        <%
                            for (Type c : lcc) {
                        %>
                        <tr>
                            <td class="mid-align"><%=c.getTypeId() %>
                            </td>
                            <td class="mid-align"><%=c.getTypeTitle() %>
                            </td>
                            <td class="mid-align"><%=c.getTypeDescription() %>
                            </td>
                            <td class="mid-align"><a
                                    href="ProductOperationServlet?typeId=<%=c.getTypeId()%>&action=deleteType"><input
                                    type="submit" class="btn btn-danger" value="Delete"></a></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ********************* -->

<!-- show Product modal -->

<div class="modal fade" id="show-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-lg modal-size" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white text-center">
                <h5 class="modal-title" id="exampleModalLongTitle">Total Product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table">
                        <thead class="custom-bg text-white">
                        <tr>
                            <th scope="col">Product Name</th>
                            <th scope="col">Product Image</th>
                            <th scope="col">Product Description</th>
                            <th scope="col">Product Brand</th>
                            <th scope="col">Product Type</th>
<%--                            <th scope="col">Product For</th>--%>
                            <th scope="col">Product Price</th>
                            <th scope="col">Product Discount</th>
                            <th scope="col">Product Quantity</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            ProductDaoImp pd = new ProductDaoImp();
                            List<Product> lp = pd.getAllProduct();
                            BrandDaoImp c = new BrandDaoImp();
                        %>

                        <%
                            for (Product p : lp) {
                        %>
                        <tr>
                            <td class="mid-align"><%=p.getProductTitle() %>
                            </td>
                            <td class="mid-align"><img style="max-width:70px" class="img-fluid"
                                                       src="data:image/jpeg;base64,<%=p.getProductPicInBase()  %>"
                                                       alt="users_pic"></td>
                            <td class="mid-align"><%=p.getProductDescription()%>
                            </td>
                            <td class="mid-align"><%=c.getBrandNameById(p.getBrandId()) %>
                            </td>
                            <td class="mid-align"><%=new TypeDaoImp().getTypeNameById(p.getTypeId()) %>
                            </td>
                           <%-- <td class="mid-align"><%=p.getProductFor() %>
                            </td>--%>
                            <td class="mid-align"><%=p.getProductPrice() %>
                            </td>
                            <td class="mid-align"><%=p.getProductDiscount() %>
                            </td>
                            <td class="mid-align"><%=p.getProductQuantity() %>
                            </td>
                            <td class="mid-align">
                                <a href="ProductOperationServlet?productId=<%=p.getProductId()%>&action=delete"><input
                                        type="submit" class="btn btn-danger" value="Delete"></a>
                                <input
                                        type="submit" class="btn btn-primary" value="Update" data-toggle="modal" data-target="#update-product-modal" onClick =
                                    <%session.setAttribute("productIdForUpdate",p.getProductId() );%>
                                    <%

    int productIdForUpdate=(int)session.getAttribute("productIdForUpdate");

    if(productIdForUpdate!=0) {
        productForUpdate = new ProductDaoImp().getProductById(productIdForUpdate);
    }
%>

                                        </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ********************* -->

<!-- total order modal -->

<div class="modal fade bd-example-modal-lg" id="total-order-modal" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-size" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white text-center">
                <h5 class="modal-title" id="exampleModalLongTitle">TOTAL ORDERS</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">

                <%
                    List<Order> lor = new OrderDaoImp().getAllOrder();
                    if (lor.isEmpty()) {
                %>
                <div class="text-center">
                    <h1>NO ORDERS MADE BY ANYONE !!</h1>
                    <hr>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
                <%
                } else {


                %>
                <div class="table-responsive">
                    <table class="table">
                        <thead class="custom-bg text-white">
                        <tr class="text-center">
                            <th scope="col">User Id</th>
                            <th scope="col">User Name</th>
                            <th scope="col">User Email</th>
                            <th scope="col">User Phone</th>
                            <th scope="col">Product Image</th>
                            <th scope="col">Product Title</th>
                            <th scope="col">Product Brand</th>
                            <th scope="col">Product Size</th>
                            <th scope="col">Qnty</th>
                            <th scope="col">Price</th>
                            <th scope="col">Ord. date</th>
                            <th scope="col">Del. date</th>
                            <th scope="col">Set Delivery date</th>
                        </tr>
                        </thead>


                        <tbody>

                        <%
                            for (Order o : lor) {
                                int productId = o.getProdctId();
                                System.out.println(productId);
                                Product p1 = new Product();
                                p1 = new ProductDaoImp().getProductById(productId);
                                System.out.println(p1);
                                User us = new UserDaoImp().getUserByUserId(o.getUserId());
                        %>
                        <tr class="text-center">
                            <td class="mid-align"><%=us.getUserId() %>
                            </td>
                            <td class="mid-align"><%=us.getUserName() %>
                            </td>
                            <td class="mid-align"><%=us.getUserEmail() %>
                            </td>
                            <td class="mid-align"><%=us.getUserPhone() %>
                            </td>
                            <td><img style="max-height:100px;max-width:70px;width:auto;"
                                     class="img-fluid mx-auto d-block"
                                     src="data:image/jpeg;base64,<%=p1.getProductPicInBase()  %> " alt="users_pic"></td>
                            <td class="mid-align"><%=p1.getProductTitle() %>
                            </td>
                            <td class="mid-align"><%= new BrandDaoImp().getBrandNameById(p1.getBrandId()) %>
                            </td>
                            <td class="mid-align"><%=o.getProductSize() %>
                            </td>
                            <td class="mid-align"><%=o.getQuantity() %>
                            </td>
                            <td class="mid-align"><%=o.getPrice() %>
                            </td>
                            <td class="mid-align"><%=o.getOrderesDate() %>
                            </td>
                            <td class="mid-align"><%if (o.getDeliveryDate().equals("0")) { %><span
                                    style="color:red;"> <%="NA"%></span> <%} else { %> <%=o.getDeliveryDate() %><%} %>
                            </td>
                            <td class="mid-align">
                                <form action="OrderServlet" method="post">
                                    <input type="hidden" name="action" value="addDelivery">
                                    <input type="hidden" name="userId" value=<%=us.getUserId() %>>
                                    <input type="hidden" name="productId" value="<%=p1.getProductId()%>">
                                    <input type="hidden" name="orderId" value="<%=o.getOrderId()%>">
                                    <div class="form-group mx-sm-3 mb-2">
                                        <input type="text" class="form-control" name="deliveryDate"
                                               placeholder="Delivery Date" size="85" required>
                                    </div>
                                    <button type="submit" class="btn custom-bg text-white mb-2">Set</button>
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>


                    </table>
                </div>
                <hr>

                <div class="text-center">
                    <button type="button" class="btn btn-secondary ml-5" data-dismiss="modal">Close</button>
                </div>
                <%
                    }
                %>

            </div>
        </div>
    </div>
</div>

<!--  -->

<!-- update product modal -->
<div class="modal fade" id="update-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModal"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white">
                <h5 class="modal-title" id="exampleModal">Update Product details.</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="updateProduct">
                    <div class="form-group">
                        <input type="text" class="form-control" name="productId"
                               placeholder="Enter Product ID here" id="updateId" value="<%=productForUpdate.getProductId() %>" readonly required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="productTitle"
                               placeholder="Enter Product Title here" value="<%=productForUpdate.getProductTitle() %>" required>
                    </div>

                    <div class="form-group">
                        <textarea style="height:150px" class="form-control" name="productDescription"
                                  placeholder="Enter Product Description here" required><%=productForUpdate.getProductDescription() %></textarea>
                    </div>

                    <div class="form-group">
                        <input type="number" class="form-control" name="productPrice"
                               placeholder="Enter Product Price here" value="<%=productForUpdate.getProductPrice() %>" required>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="productQuantity"
                               placeholder="Enter Product Quantity here" value="<%=productForUpdate.getProductQuantity() %>" required>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="productDiscount"
                               placeholder="Enter Product Discount in (%)"  value="<%=productForUpdate.getProductDiscount() %>" required>
                    </div>

                    <div class="form-group">
                        <select name="productBrandId" class="form-control" >
                            <option value="<%=productForUpdate.getBrandId() %>" selected>
                                <%
                                    int match = productForUpdate.getBrandId();
                                    pevioousBrand = li.stream()
                                            .filter(p ->p.getBrandId() == match)
                                            .collect(Collectors.toList()).get(0); %> <%=pevioousBrand.getBrandTitle()%></option>
                            <%
                                li.removeIf(x -> x.getBrandId() == match);
                                for (Brand brand : li) {
                            %>

                            <option value="<%=brand.getBrandId()%>"><%=brand.getBrandTitle() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <select name="productTypeId" class="form-control">
                            <option value="<%=productForUpdate.getTypeId() %>" selected>
                                <%
                                    int matchForType = productForUpdate.getBrandId();
                                    peviousType = lic.stream()
                                            .filter(p ->p.getTypeId() == matchForType)
                                            .collect(Collectors.toList()).get(0); %> <%=peviousType.getTypeTitle()%></option>
                            <%
                                lic.removeIf(x -> x.getTypeId() == matchForType);

                                for (Type type : lic) {
                            %>
                            <option value="<%=type.getTypeId()%>"><%=type.getTypeTitle() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>

<%--                    <div class="form-group">--%>
<%--                        <select class="form-control text-center mt-3" id="type" name="productFor">--%>
<%--                            <option value="0">Select Type</option>--%>
<%--                            <option value="men">Men</option>--%>
<%--                            <option value="women">Women</option>--%>
<%--                            <option value="boy">Boy</option>--%>
<%--                            <option value="girl">Girl</option>--%>
<%--                        </select>--%>
<%--                    </div>--%>

                    <div class="form-group">
                        <label>Select Product Pic</label><br>
                        <input type="file" class="form-control" name="updateProductPhoto"

                        >
                    </div>

                    <div class="container text-center">
                        <button class="btn custom-bg text-white">Update Product</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- end update product modal -->

<script type="text/javascript">
    UpdateFunction = function(productId){

        console.log("java Script",productId);

    }
</script>

</body>
</html>