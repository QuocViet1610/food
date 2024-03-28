$(document).ready(function () {
    console.log("test")

    var token = localStorage.getItem("token")
    var idRes = GetParameterValues("id")
    console.log(idRes)

// **********************Get Du liệu **************************************

$(document).ready(function () {
    console.log("test");

    var token = localStorage.getItem("token");
    var idRes = GetParameterValues("id");
    console.log(idRes);

    // **********************Get Du liệu **************************************

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/menu/foodRestaurent?id=" + idRes,
        headers: { 'Authorization': 'Bearer ' + token }
    })
    .done(function (msg) {
        $.each(msg.data, function (index, value) {
            var htmlProduct = `
                <tr>
                    <td><p id="foodId">${value.id}</p></td>
                    <td><input type="text" class="Titel-input" value="${value.tittle}" /></td>
                    <td>
                        <select class="Category-input">
                            <option value="${value.idcategoryDTO}" selected>${value.categoryDTO}</option>
                        </select>
                    </td>
                    <td>
                        <div for="file" class="file-label">${value.image}</div>
                        <input type="file" id="file" class="file-upload" required />
                    </td>
                    <td>
                        <select class="Freeship-input">
                        <option value="True" ${value.freesip === 'flash' ? 'selected' : ''}>True</option>
                        <option value="Flash" ${value.freesip === 'flash' ? 'selected' : ''}>Flash</option>
                        </select>
                    </td>
                    <td><input type="text" class="Price-input" value="${formatMoney(value.price)}" /></td>
                    <td><button id="update"><i class="fa-solid fa-check"></i></button></td>
                    <td><button id="delete"><i class="fa-solid fa-trash"></i></button></td>
                </tr>
            `;
            $('.tbody_food').append(htmlProduct);
        });
        function formatMoney(amount) {
            return amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }).replace('₫', '');
        }
    })
    .then(function () {
        // ***********************get all category ****************************************
        return $.ajax({
            method: "GET",
            url: "http://localhost:8080/category/getAll",
            headers: { 'Authorization': 'Bearer ' + token }
        });
    })
    .done(function (msg) {
        $.each(msg.data, function (index, value) {
            var htmlProduct = `
                <option value="${value.idCategoryDTO}" >${value.nameCate}</option>
            `;
            $('.Category-input').append(htmlProduct);
            $('#Category').append(htmlProduct);

        });
    });
});






// ***********************doi ten file ****************************************

// Lắng nghe sự kiện click trên document, nhưng chỉ xử lý cho các phần tử có class là file-label
$(document).on('click', '.file-label', function() {
    // Tìm phần tử input file tương ứng
    var fileInput = $(this).siblings('.file-upload');

    // Kích hoạt sự kiện click cho input file
    fileInput.click();
});

// Lắng nghe sự kiện change cho input file
$(document).on('change', '.file-upload', function () {
    var fileName = $(this).val();
    var fileNameOnly = fileName.split('\\').pop();

    var fileLabel = $(this).siblings('.file-label');
    fileLabel.text(fileNameOnly !== '' ? fileNameOnly : 'Choose Image');
});


//*************************Update du lieu************************************************* */

$(document).on("click", "#update", function() {
        // Get the file input using jQuery
        const fileUpload = $(".file-upload")[0].files[0];

        // Get other form data
        var Titel = $(this).closest("tr").find(".Titel-input").val();
        var newCategory = $(this).closest("tr").find(".Category-input").val();
        var Freeship = $(this).closest("tr").find(".Freeship-input").val();
        var Price = $(this).closest("tr").find(".Price-input").val();
        console.log(Titel);
        console.log(newCategory);
        console.log(Freeship);
        console.log(Price);
        if (fileUpload === null && Titel === null && newCategory === null && Freeship === null && Price === null) {
       
            console.log("Please complete all information");
        } else {
        var formData = new FormData();
        formData.append('file', fileUpload);
        formData.append('titel', Titel);
        formData.append('price', Price);
        formData.append('is_freeship', Freeship);
        formData.append('cated_id', newCategory);

        // AJAX request
        $.ajax({
            method: "POST",
            enctype: 'multipart/form-data',
            url: "http://localhost:8080/menu/SaveFood",
            data: formData,
            contentType: false,
            // contentType: 'multipart/form-data',
            processData: false,
            headers: { 'Authorization': 'Bearer ' + token }
        })
        .done(function (msg) {
            console.log(msg.data);
        });
        }
    });

//*************************Save************************************************* */
$("#Save").click(function () {
    // Get the file input using jQuery
    const fileUpload = $("#avatar")[0].files[0];
    if (!fileUpload) {
        alert(" No file selected.");
    }
    var title = $("#Titel").val();
    var price = $("#Price").val();
    var freeship = $("#Freeship").val();
    var category = $("#Category").val();

    if (!title && !price && !freeship && !category) {
        alert("Please fill in all required fields"); 
    } else if (isNaN(price)) {

        alert("Price must be a number"); // Hiển thị thông báo lỗi cho người dùng
        // Hoặc có thể thực hiện các hành động khác tương ứng với việc giá không phải là một số.
    }else
    {
    // Hiển thị các giá trị đã lấy ra (để kiểm tra)
    console.log("file: " + fileUpload);
    console.log("Title: " + title);
    console.log("Price: " + price);
    console.log("Freeship: " + freeship);
    console.log("Category: " + category);

    var idRes = GetParameterValues("id")
    // Create FormData object
    var formData = new FormData();
    formData.append('file', fileUpload);
    formData.append('titel', title);
    formData.append('price', price);
    formData.append('is_freeship', freeship);
    formData.append('cated_id', category);
    formData.append('resId', idRes);

    // AJAX request
    $.ajax({
        method: "POST",
        enctype: 'multipart/form-data',
        url: "http://localhost:8080/menu/SaveFood",
        data: formData,
        contentType: false,
        // contentType: 'multipart/form-data',
        processData: false,
        headers: { 'Authorization': 'Bearer ' + token }
    })
    .done(function (msg) {
        console.log(msg.data);
        alert("Successful")
        document.getElementById('loginFormContainer').style.display = 'none';
        window.location.href ='../../fooddetail.html?id='+idRes;
    });

}

});
//*************************delete************************************************* */
$(document).on("click", "#delete", function() {
    var deleteButton = $(this); 
    var confirmation = confirm("you want to delete this?");
    if (confirmation) {
        var productId = deleteButton.closest('tr').find('#foodId').text();
        console.log(productId)
    
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/menu/DeleteFood",
            data: {
                id : productId
            },
            headers: { 'Authorization': 'Bearer ' + token }
        })
        .done(function (msg) {
            console.log(msg.data);
            deleteButton.closest('tr').remove();
            alert("Successful Delete")
        }); 
    } 
});
//*********************************Get URL ************************************************* */
function GetParameterValues(param) {
    var url = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < url.length; i++) {
    var urlparam = url[i].split('='); 
    if (urlparam[0] == param) {
    return urlparam[1];
    }
    }
    }

    document.getElementById('showLoginForm').addEventListener('click', function() {
        document.getElementById('loginFormContainer').style.display = 'block';
    });
    
    document.getElementById('closeLoginForm').addEventListener('click', function() {
        document.getElementById('loginFormContainer').style.display = 'none';
    });
    
});


