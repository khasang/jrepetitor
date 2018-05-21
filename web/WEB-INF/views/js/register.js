var RestPost = function (name, login, password, roleName, surname, middlename, email, phoneNumber) {
    var user = {
        'name': name,
        'login': login,
        'password': password,
        'roleName': roleName,
        profile: {
            'name': name,
            'surname': surname,
            'middlename': middlename,
            'phoneNumber': phoneNumber,
            'email': email
        }
    };

    var service = 'http://localhost:8080/users';
    $.ajax({
        type: 'POST',
        url: service + '/create',
        contentType: 'application/json;utf8',
        data: JSON.stringify(user),
        dataType: 'json',
        async: false,
        success: function (result) {
            var errorStatus = JSON.parse(JSON.stringify(result))
            parseError(errorStatus);
        },
        error: function (jqXHR, testStatus, errorThrown) {
            prepareErrorBlock();
        }
    });
};

var parseError = function (errorStatus) {
    if (errorStatus.ok == true) {
        $('#email-error-place').empty();
        $('#login-error-place').empty();
        $('#phone-error-place').empty();
        $('#success-block').remove();
        $('#error-block').remove();
        prepareSuccessBlock();
        $('#success-body').append("Profile created");
    }
    if (errorStatus.emailExist == true) {
        $('#email-error-place').empty();
        $('#email-error-place').append("Email already in use");

    }
    if (errorStatus.loginExist == true) {
        $('#login-error-place').empty();
        $('#login-error-place').append("Login already in use");
    }
    if (errorStatus.phoneExist == true) {
        $('#phone-error-place').empty();
        $('#phone-error-place').append("Phone already in use");
    }
}

var SendForm = function () {
    var name = document.getElementById('name-input').value;
    var login = document.getElementById('login-input').value;
    var password = document.getElementById('password-input').value;
    var roleName = 'ROLE_USER';
    var surname = document.getElementById('surname-input').value;
    var email = document.getElementById('email-input').value;
    var middlename = document.getElementById('middlename-input').value;
    var phoneNumber = document.getElementById('phone-input').value;
    if (validateForm(name, login, password, roleName, surname, middlename, email, phoneNumber)) {
        RestPost(name, login, password, roleName, surname, middlename, email, phoneNumber);
    }
}

var clearForm = function () {
    $('#name-input').val('');
    $('#surname-input').val('');
    $('#middlename-input').val('');
    $('#phone-input').val('');
    $('#email-input').val('');
    $('#login-input').val('');
    $('#password-input').val('');
    $('#confirm-password-input').val('');
    $('#phone-error-place').empty();
    $('#login-error-place').empty();
    $('#email-error-place').empty();
    $('#success-block').remove();
    $('#error-block').remove();
}

var validateForm = function (name, login, password, roleName, surname, middlename, email, phoneNumber) {
    //need more validate rules
    if (name == "" || name == "" || password == "" || roleName == "" || surname == "" || middlename == ""
        || email == "" || phoneNumber == "") {
        prepareErrorBlock()
        $('#error-body').append("Fields must be filled out")
        return false;
    } else {
        return true;
    }
}

var prepareErrorBlock = function () {
    if ($(document).find('#error-block').length == 0) {
        var allertDiv = "<div class='alert alert-danger' role='alert' id ='error-block'></div>";
        var allertHeader = "<div class='d-inline p-2 ' id='error-header'><strong>Error</strong></div>";
        var allertBody = "<div class='d-inline p-2 error-content'  id='error-body'></div>";
        $('#content').append(allertDiv);
        $('#error-block').append(allertHeader);
        $('#error-header').append(allertBody);
    } else {
        $('#error-body').empty();
    }
}

var prepareSuccessBlock = function () {
    if ($(document).find('#success-block').length == 0) {
        var successDiv = "<div class='alert alert-success' role='alert' id ='success-block'>"
        var successHeader = "<div class='d-inline p-2' id='success-header'><strong>Success</strong></div>";
        var successBody = "<div class='d-inline p-2'  id='success-body'></div>";
        $('#content').append(successDiv);
        $('#success-block').append(successHeader);
        $('#success-header').append(successBody);
    } else {
        $('#success-body').empty();
    }
}