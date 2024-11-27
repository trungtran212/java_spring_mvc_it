<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Create User</title>

                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <form:form action="/admin/user/update1" method="post" modelAttribute="newUser">
                                <h2 class="text-center">Update User</h2>
                                <div class="mb-3" style="display: none;">
                                    <label class="form-label">Id</label>
                                    <form:input type="text" class="form-control" path="Id" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Email address</label>
                                    <form:input type="email" class="form-control" path="email" disabled="true" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Your Full Name</label>
                                    <form:input type="text" class="form-control" path="fullName" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Your Address</label>
                                    <form:input type="text" class="form-control" path="address" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Your Phone</label>
                                    <form:input type="tel" class="form-control" path="phone" />
                                </div>
                                <button type="submit" class="btn btn-warning">Update</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </body>

            </html>