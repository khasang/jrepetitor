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
            var profile = JSON.parse(JSON.stringify(result))
            //showProfile(profile)
            //prepareSuccessBlock()
            $('#success-body').append("profile updated")
        },
        error: function (jqXHR, testStatus, errorThrown) {
            prepareErrorBlock();
            $('#error-body').html(JSON.stringify(jqXHR))
        }
    });
};

var SendForm = function () {
    var name = document.getElementById('name-input').value;
    var login = document.getElementById('login-input').value;
    var password = document.getElementById('password-input').value;
    var roleName = 'ROLE_USER';
    var surname = document.getElementById('surname-input').value;
    var email = document.getElementById('email-input').value;
    var middlename = document.getElementById('middlename-input').value;
    var phoneNumber = document.getElementById('phone-input').value;
    RestPost(name, login, password, roleName, surname, middlename, email, phoneNumber);
}



