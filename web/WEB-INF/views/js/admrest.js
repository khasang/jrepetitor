var RestAdd = function (name, login, password) {
    var JSONObject = {
        "name": name,
        "login": login,
        "password": password
    };

    $.ajax({
        type: 'POST',
        url: '/add',
        contentType: 'application/json;utf-8',
        data: JSON.stringify(JSONObject),
        dataType: 'json',
        async: false,
        success: function (result) {
            $('#response').html(JSON.stringify(result))
        },
        error: function () {
            $('#response').html(JSON.stringify(jqXHR))
        }
    })
};