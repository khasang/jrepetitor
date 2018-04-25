var showForm = function () {
    clearField()
    $(".name").append("<input type='text' class='form-control' id='nameinput'>")
    $(".surname").append("<input type='text' class='form-control' id='surnameinput'>")
    $(".middlename").append("<input type='text' class='form-control' id='middlenameinput'>")
    $(".phone").append("<input type='text' class='form-control' id='phoneinput'>")
    $(".email").append("<input type='text' class='form-control' id='emailinput'>")
    $(".first-button").append("<button onclick=\"sendProfile()\" type=\"button\" class=\"btn btn-primary \">Save</button>")
    $(".second-button").append("<button onclick=\"RestGet()\" type=\"button\" class=\"btn btn-primary \">Cancel</button>")
}

var showProfile = function (profile) {
    clearField()
    $('.name').append(" " + profile.name)
    $('.surname').append(" " + profile.surname)
    $('.middlename').append(" " + profile.middlename)
    $('.phone').append(" " + profile.phoneNumber)
    $('.email').append(" " + profile.email);
    $(".first-button").append("<button onclick= \"showForm()\" type=\"button\" class=\"btn btn-primary showFormButton\">Edit</button>")
}

var clearField = function () {
    $(".name").empty()
    $(".surname").empty()
    $(".middlename").empty()
    $(".phone").empty()
    $(".email").empty()
    $(".first-button").empty()
    $(".second-button").empty()
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
            var profile = JSON.parse(JSON.stringify(result))
            showProfile(profile)
        },
        error: function (jqXHR, testStatus, errorThrown) {
            prepareErorBlock()
            $('#error-body').html(JSON.stringify(jqXHR))
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
            var profile = JSON.parse(JSON.stringify(result))
            showProfile(profile)
            prepareSuccessBlock()
            $('#success-body').append("profile updated")
        },
        error: function (jqXHR, testStatus, errorThrown) {
            prepareErrorBlock();
            $('#error-body').html(JSON.stringify(jqXHR))
        }
    });

};

var sendProfile = function () {
    validateProfile()
    var name = document.getElementById("nameinput").value;
    var surname = document.getElementById("surnameinput").value;
    var middlename = document.getElementById("middlenameinput").value;
    var phone = document.getElementById("phoneinput").value;
    var email = document.getElementById("emailinput").value;
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
    //need more validate rules
    if (name == "" || surname == "" || middlename == "" || phone == "" || email == "") {
        prepareErrorBlock()
        $('#error-body').append("Fields must be filled out")
        return false;
    } else {
        return true;
    }
}