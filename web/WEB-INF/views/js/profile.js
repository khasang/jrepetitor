var showForm = function () {
    clearField();
    $('#name-field').append("<input type='text' class='form-control' id='nameinput'>");
    $('#surname-field').append("<input type='text' class='form-control' id='surnameinput'>");
    $('#middlename-field').append("<input type='text' class='form-control' id='middlenameinput'>");
    $('#phone-field').append("<input type='text' class='form-control' id='phoneinput'>");
    $('#email-field').append("<input type='text' class='form-control' id='emailinput'>");
    $('#first-button-field').append("<button onclick='sendProfile()' type='button' class='btn btn-primary'>Save</button>");
    $('#second-button-field').append("<button onclick='RestGet()' type='button' class='btn btn-primary'>Cancel</button>");
}

var showProfile = function (profile) {
    clearField()
    $('#name-field').append(profile.name)
    $('#surname-field').append(profile.surname)
    $('#middlename-field').append(profile.middlename)
    $('#phone-field').append(profile.phoneNumber)
    $('#email-field').append(profile.email);
    $('#first-button-field').append("<button onclick= 'showForm()' type='button' class='btn btn-primary showFormButton'>Edit</button>")
}

var clearField = function () {
    $('#name-field').empty()
    $('#surname-field').empty()
    $('#middlename-field').empty()
    $('#phone-field').empty()
    $('#email-field').empty()
    $('#first-button-field').empty()
    $('#second-button-field').empty()
    $('#success-block').remove();
    $('#error-block').remove();
}

var RestGet = function () {
    var service = 'http://localhost:8080/users';
    $.ajax({
        type: 'GET',
        url: service + '/profile',
        dataType: 'json',
        async: false,
        success: function (result) {
            var profile = JSON.parse(JSON.stringify(result));
            showProfile(profile);
        },
        error: function (jqXHR, testStatus, errorThrown) {
            prepareErorBlock();
            $('#error-body').html(JSON.stringify(jqXHR));
        }
    });
};

var RestPost = function (name, middlename, surname, email, phoneNumber) {
    var service = 'http://localhost:8080/users';

    var profile = {
        'name': name,
        'middlename': middlename,
        'surname': surname,
        'email': email,
        'phoneNumber': phoneNumber
    }

    $.ajax({
        type: 'POST',
        url: service + '/profile',
        contentType: 'application/json;utf8',
        data: JSON.stringify(profile),
        dataType: 'json',
        async: false,
        success: function (result) {
            var response = JSON.parse(JSON.stringify(result))
            checkResponse(response);
        },
        error: function (jqXHR, testStatus, errorThrown) {
            prepareErrorBlock();
            $('#error-body').html(JSON.stringify(jqXHR))
        }
    });

};

var checkResponse = function (response) {
    if (response.ok == true) {
        RestGet();
        prepareSuccessBlock();
        $('#success-body').append("Profile update");
    } else {
        prepareErrorBlock();
        if (response.emailExist == true) {

            $('#error-body').append("Email already in use");
        }
        if (response.phoneExist == true) {

            $('#error-body').append(" Phone already in use");
        }
    }
}

var sendProfile = function () {
    validateProfile()
    var name = document.getElementById('nameinput').value;
    var surname = document.getElementById('surnameinput').value;
    var middlename = document.getElementById('middlenameinput').value;
    var phone = document.getElementById('phoneinput').value;
    var email = document.getElementById('emailinput').value;
    if (validateProfile(name, surname, middlename, phone, email)) {
        RestPost(name, middlename, surname, email, phone)
    }
};

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

function validateProfile(name, surname, middlename, phone, email) {
    if (name == "" || surname == "" || middlename == "" || phone == "" || email == "") {
        prepareErrorBlock()
        $('#error-body').append("Fields must be filled out")
        return false;
    } else {
        return true;
    }
}