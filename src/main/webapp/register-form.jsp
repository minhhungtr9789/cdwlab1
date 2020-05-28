<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.nlu.fit.utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Khai báo sử dụng JSTL Core Tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Register</title>
    <!-- Bootstrap -->
    <link rel='stylesheet' type='text/css' href="css/bootstrap.min.css">
    <style>
        .error {
            color: red;
            padding: 5px 5px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <div id="formRegister" class="row">
        <div class="col-sm-8 m-auto p-5">
            <h2>Register Form</h2>
            <form class="needs-validation" novalidate $ref="form">
                <div class="form-row">
                    <div class="col-md-12 mb-3">
                        <label for="email">*Email</label>
                        <input type="text" class="form-control" id="email" name="email" placeholder="Email" required
                               onblur="checkEmailExist(event, this.value)">
                        <div class="invalid-feedback">
                            Trường dữ liệu này bắt buộc
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="password">*Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password"
                               required>
                        <div class="invalid-feedback">
                            Trường dữ liệu này bắt buộc
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="rePassword">*Confirm Password</label>
                        <input type="password" class="form-control" id="rePassword" placeholder="Confirm Password"
                               onblur="checkSamePass()" required>
                        <div class="invalid-feedback">
                            Trường dữ liệu này bắt buộc
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName">*First name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First name"
                               required>
                        <div class="invalid-feedback">
                            Trường dữ liệu này bắt buộc
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName">*Last name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last name"
                               required>
                        <div class="invalid-feedback">
                            Trường dữ liệu này bắt buộc
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="industry">Industry</label>
                        <select class="custom-select" id="industry" name="industry">
                            <c:forEach items="${requestScope.industry}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>

                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="jobTitle">Job title</label>
                        <input type="text" class="form-control" id="jobTitle" name="jobTitle" placeholder="Job title">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="company">Company</label>
                        <input type="text" class="form-control" id="company" name="company" placeholder="Company">
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="city">City</label>
                        <select class="custom-select" id="city" name="city">
                            <c:forEach items="${requestScope.city}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="telephone">Telephone</label>
                        <input type="number" class="form-control" id="telephone" name="telephone"
                               placeholder="Telephone">
                        <div class="invalid-feedback">
                            Trường dữ liệu này bắt buộc
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-12 mb-3">
                        <c:forEach items="${requestScope.favorites}" var="item" varStatus="loop">
                            <div class="custom-control custom-checkbox custom-control-inline">
                                <input type="checkbox" class="custom-control-input"
                                       name="favorite"
                                       value="${item.id}"
                                       id="customCheck${loop.index}">
                                <label class="custom-control-label" for="customCheck${loop.index}">${item.name}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-12 mb-3">
                        <c:forEach items="${requestScope.platform}" var="item" varStatus="loop">
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio"
                                       name="platform"
                                       value="${item.id}"
                                       id="customRadioInline${loop.index}"
                                       class="custom-control-input">
                                <label class="custom-control-label"
                                       for="customRadioInline${loop.index}"> ${item.name} </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <button class="btn btn-primary" type="submit">Register</button>
            </form>
        </div>
    </div>
    <div style="display: none" id="userInfo">
        Welcome
        <span id="nameShow"></span>
        <span id="companyShow">
        of
            <span id="companyName"></span>
        company.
        </span>
        <h4>You can log in with:</h4>
        <div>username: <a href="" id="emailShow"></a></div>
        <div>password: <span id="passShow"></span></div>
        <div>Thank you for register!</div>
    </div>
</div>

<script>
    function scroll() {
        const errorElements = document.querySelectorAll(
            "input.form-control:invalid");
        $('html, body').animate({
            scrollTop: $(errorElements[0]).offset().top
        }, 500);
    }
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            const forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            const validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();

                        form.classList.add('was-validated');
                        if ($(this).find('#email').hasClass('is-invalid') === true) {
                            // $('#email').removeClass('form-control');
                            // $('#email').className('form-control').invalid
                        }

                        scroll()
                    } else {
                        event.preventDefault();
                        event.stopPropagation();
                        const formRegister = $('#formRegister');
                        const userInfo = $('#userInfo');

                        console.log('call add user servlet');
                        const form_data = $(this).serialize();
                        console.log(form_data)
                        $.ajax({
                            url: 'addUser',
                            type: 'post',
                            data: form_data,
                            datatype: 'json',
                            headers: {
                                Accept: "application/json; charset=utf-8",
                            },
                            error: function () {
                                console.log('error')
                            },
                            success: function (data) {
                                console.log('success');
                                var myObj = JSON.parse(data);
                                var result = myObj.result;
                                if (result === 'false') {
                                    // show error
                                    console.log('Thêm thất bại')
                                    scroll()

                                } else {
                                    formRegister.css('display', 'none');
                                    userInfo.css('display', 'block');

                                    $('#nameShow').text($('#firstName').val() + ' ' + $('#lastName').val());
                                    if ($('#company').val() !== '') {
                                        $('#companyShow').css('display', 'inline-block');
                                        $('#companyName').text($('#company').val());
                                    } else {
                                        $('#companyShow').css('display', 'none');
                                        $('#companyName').text('');
                                    }

                                    $('#emailShow').text($('#email').val());
                                    $('#passShow').text($('#password').val());
                                }
                            }
                        })

                    }
                });
            });
        }, false);
    })();

    function checkSamePass() {
        const pass = $('#password').val();
        const rePass = $('#rePassword').val();
        const errorDiv = $('#rePassword').parent().find('.invalid-feedback');
        if ($('#rePassword').val().length > 0) {
            if (pass !== rePass) {
                errorDiv.text("Password nhập lại không đúng");
                $('#rePassword').addClass('is-invalid')
            } else {
                errorDiv.text("");
                $('#rePassword').removeClass('is-valid')
                $('#rePassword').removeClass('is-invalid')
            }
        }
    }

    function checkEmailExist(event, email) {
        event.preventDefault(); //prevent default action
        console.log(email);
        console.log('call ajax');
        $.ajax({
            url: 'checkEmailExist',
            type: 'post',
            data: { // Danh sách các thuộc tính sẽ gửi đi
                email: email
            },
            datatype: 'json',
            headers: {
                Accept: "application/json; charset=utf-8",
            },
            error: function () {
                console.log('error')
            },
            success: function (data) {
                console.log('success');
                var myObj = JSON.parse(data);
                var result = myObj.result;
                if (result === 'false') {
                    // show error
                    const errorDiv = $('#email').parent().find('.invalid-feedback');
                    errorDiv.text("Tên người dùng đã được sử dụng. Hãy thử tên khác.");
                    $('#email').removeClass('is-valid');
                    $('#email').addClass('is-invalid')
                } else {
                    $('#email').removeClass('is-invalid');
                    $('#email').removeClass('is-valid');
                }
            }
        })
    }

</script>


<!--  jQuery  -->
<script src="js/jquery.js"></script>

<!-- Bootstrap-->
<script src="js/bootstrap4/popper.min.js"></script>
<script src="js/bootstrap4/bootstrap.min.js"></script>
</body>
</html>
