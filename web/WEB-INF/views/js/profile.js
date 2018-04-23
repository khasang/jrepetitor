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
            $('#response').html(JSON.stringify(jqXHR))
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
        },
        error: function (jqXHR, testStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR))
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

function validateProfile(name, surname, middlename, phone, email) {
    //need more validate rules
    if (name == "" || surname == "" || middlename == "" || phone == "" || email == "") {
        alert("Fields must be filled out");
        return false;
    } else {
        return true;
    }
}