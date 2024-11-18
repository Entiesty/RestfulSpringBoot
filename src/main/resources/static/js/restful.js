// 运行函数
del();

// 提交POST请求
function post() {
    // 组织为JSON
    let user = {
        "userName": "userName_new",
        "sexId": 1,
        "note": "note_new"
    };
    // Ajax请求
    axios({
        method: "POST", // POST请求
        url: "./user", // 请求路径
        data: JSON.stringify(user), // 转化为字符串
        headers: { // 设置请求体为JSON数据类型
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(resp => {
        // 请求响应结果
        let result = resp.data;
        if (result.success) {
            alert("插入成功，数据：" + JSON.stringify(result.data));
            return;
        } else {
            alert("插入成功，后台消息：" + result.message);
        }
    })
}


function get() {
    axios({
        method: "GET", // PUT请求
        url: "./user/5", // 请求路径
    }).then(resp => {
        // 请求响应结果
        let result = resp.data;
        if (result != null && result.success) {
            let user = result.data;
            alert("获取数据：" + JSON.stringify(user));
        } else {
            alert("获取数据失败");
        }
    })
}

function findUsers() {
    axios({
        method: "GET", // PUT请求
        url: "./users/u/1/0/5", // 请求路径
    }).then(resp => {
        // 请求响应结果
        let users = resp.data;
        if (users != null) {
            alert("获取数据：" + JSON.stringify(users));
        } else {
            alert("获取数据失败");
        }
    })
}

function put() {
    // 组织为JSON
    let user = {
        "userName": "userName_update",
        "sexId": 0,
        "note": "note_update"
    };
    // Ajax请求
    axios({
        method: "PUT", // PUT请求 ①
        url: "./user/1", // 请求路径
        data: JSON.stringify(user), // 转化为字符串
        headers: { // 设置请求体为JSON数据类型
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(resp => {
        // 请求响应结果
        let user = resp.data;
        if (user.id) {
            alert("插入成功，数据：" + JSON.stringify(user));
        } else {
            alert("插入失败");
        }
    })
}


function patch() {
    axios({
        method:"PATCH", // PATCH请求
        url: "./user/1/user_name_996"
    }).then(resp => {
        // 请求响应结果
        let result = resp.data;
        if (result.success) {
            alert("更新成功。");
        } else {
            alert("插入失败。");
        }
    });
}

function del() {
    // Ajax请求
    axios({
        method: "DELETE", // PUT请求
        url: "./user/5", // 请求路径
    }).then(resp => {
        // 请求响应结果
        var result = resp.data;
        if (result != null && result.success) {
            let user = result.data;
            alert("删除成功，数据：" + JSON.stringify(user));
        } else {
            alert("删除失败");
        }
    })
}


function postStatus() {
    // 组织为JSON
    let user = {
        "userName": "userName_new",
        "sexId": 1,
        "note": "note_new"
    };
    // Ajax请求
    axios({
        method: "POST", // POST请求
        url: "./user2/entity", // 请求路径
        // url: "./user2/annotation"
        data: JSON.stringify(user), // 转化为字符串
        headers: { // 设置请求体为JSON数据类型
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(resp => {
        // 请求响应结果
        let result = resp.data;
        let success = resp.headers.get("success");
        let status = resp.status;
        alert("响应头："+success+"\n"
            +"HTTP影响状态" + status +"\n"
            +"响应消息为:" + JSON.stringify(result))
    })
}