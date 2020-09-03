var ws;
//未选择要发送的单条指令类型 ：1
var Type = 1;

function StorageObject(type, name) {
    this.type = type;
    this.name = name;
}

var TYPE_IFSF = new StorageObject(3, "IFSF");
var TYPE_EMULATOR = new StorageObject(1, "EMULATORS");
var TYPE_JSON = new StorageObject(2, "JSON");

/**
 * 发送消息
 */
function sendMessage() {
    if (Type == 1) {
        consoleTip("请选择要发送的单条指令数据类型！", "alert-danger")
        return;
    }
    var sendOne = {
        message: $("#subject").val(),
        type: Type
    };
    $.ajax({
        url: "/sendOne",
        type: "POST",
        datatype: "JSON",
        cache: false,
        data: JSON.stringify(sendOne),
        contentType: "application/json",
        success: function (data) {
            if (data.code === '1') {
                consoleTip(data.message, "")
            }
        }
    })
}

/**
 * 清空数据
 */
function ClearTextArea() {
    $("#message").empty();
}


/**
 * 将dit模拟器放入缓存
 * @param temp
 * @returns {string}
 */
function pushEmulatorToStorage(temp) {
    var emulators = JSON.parse(sessionStorage.getItem(TYPE_EMULATOR.name));
    if (emulators == null) {
        emulators = [];
    }
    emulators.push(temp);
    sessionStorage.setItem(TYPE_EMULATOR.name, JSON.stringify(emulators));
    return emulators;
}

var flag = false;

/**
 * 获取所有的接收器和模拟器
 */
function getAll() {
    $.get(
        "/dit/all",
        null,
        function (data) {
            var objList = data.data;
            flag = true;
            var temp = null;
            for (var i = 0; i < objList.length; i++) {
                temp = objList[i];
                if (temp.type === TYPE_JSON.type) {
                    //json接收器
                    sessionStorage.setItem(TYPE_JSON.name, JSON.stringify(temp));
                } else if (temp.type === TYPE_IFSF.type) {
                    //ifsf接收器
                    sessionStorage.setItem(TYPE_IFSF.name, JSON.stringify(temp));
                } else if (temp.type === TYPE_EMULATOR.type) {
                    //dit模拟器
                    //保存的是一个数组
                    pushEmulatorToStorage(temp);
                }
            }
            afterGetAll();

        },
        "json"
    )
}

var localIp;
var localIfsfPort;
var localJsonPort;
var jsonStatus;
var ifsfStatus;
var websocketIp;
var websocketPort;

//		获取初始状态 ip、json端口、ifsf端口
function getConfig() {
    // 清空 两个端口的状态
    $("#jsonTip").text('');
    $("#ifsfTip").text('');
    $.ajax({
        type: "post",
        url: "/tankchartdc/dit/select",
        data: JSON.stringify({type: '2'}),
        dataType: "json",
        cache: false,
        contentType: 'application/json',
        success: function (data) {
            console.log(data);
            if (data.code === '100') {
                $('#ip-add').val(data.info.ip);
                $('#ifsf-port').val(data.info.ifsf);
                $('#json-port').val(data.info.json);
                localIp = data.info.ip;
                localIfsfPort = data.info.ifsf;
                localJsonPort = data.info.json;
                websocketIp = data.info.websocketIp;
                websocketPort = data.info.websocketPort;
//						端口状态提示
                jsonStatus = data.info.jsonIsRunning;
                ifsfStatus = data.info.ifsfIsRunning;
                var str1 = jsonStatus ? 'JSON 端口正常连接' : 'JSON 端口断开连接';
                var str2 = ifsfStatus ? 'IFSF 端口正常连接' : 'IFSF 端口断开连接';
//						连接、关闭 按钮变更
                var str3 = jsonStatus ? '关闭 JSON端口' : '连接 JSON端口';
                var str4 = ifsfStatus ? '关闭 IFSF端口' : '连接 IFSF端口';

                $("#jsonTip").css(jsonStatus ? {'font-size': '14px', 'color': 'green'} : {
                    'font-size': '14px',
                    'color': 'red'
                });
                $("#jsonTip").text(str1);
                $('#jsonBtn').text(str3);
//                        按钮样式
                $('#jsonBtn').removeAttr('class');
                if (jsonStatus) {
                    $('#jsonBtn').attr("class", "btn btn-success");
                } else {
                    $('#jsonBtn').attr("class", "btn btn-danger");
                }
                $("#ifsfTip").css(ifsfStatus ? {'font-size': '14px', 'color': 'green'} : {
                    'font-size': '14px',
                    'color': 'red'
                });
                $("#ifsfTip").text(str2);
                $('#ifsfBtn').text(str4);
//                        按钮样式
                $('#ifsfBtn').removeAttr('class');
                if (ifsfStatus) {
                    $('#ifsfBtn').attr("class", "btn btn-success");
                } else {
                    $('#ifsfBtn').attr("class", "btn btn-danger");
                }
            }
            getAll();
        }
    });
}

// 修改 ip、json端口 、ifsf端口
function editInfo() {
    if ($("#editInfo").text() === '修改') {
        $("#editInfo").text('提交');
        $("#ip-add").removeAttr("disabled");
        $("#ifsf-port").removeAttr("disabled");
        $("#json-port").removeAttr("disabled");
    } else if ($("#editInfo").text() === '提交') {
        changeConfig();
        $("#editInfo").text('修改');
        $("#ip-add").attr("disabled", "disabled");
        $("#ifsf-port").attr("disabled", "disabled");
        $("#json-port").attr("disabled", "disabled");
        //redtip清空
        $("#jsonTip").text('');
        $("#ifsfTip").text('');
    }
}

// 执行修改方法
function changeConfig() {
    var changePort = {
        ifsf: $("#ifsf-port").val(),
        json: $("#json-port").val(),
        ip: $("#ip-add").val(),
        type: '2'
    };
    $.ajax({
        url: "/tankchartdc/dit/update",
        type: "POST",
        data: JSON.stringify(changePort),
        cache: false,
        processData: false,
        contentType: "application/json",
        success: function (data) {
            if (data.code === "100") {
                consoleTip('配置文件修改成功', '')
            } else {
                consoleTip('配置文件修改失败', 'alert-danger')
            }
            //    修改后重新调getConfig方法
            getConfig()
        }
    })
}

//		连接、关闭 json端口
function jsonPortFun() {
    dealOperate('2', jsonStatus)
}

function ifsfPortFun() {
    dealOperate('3', ifsfStatus)
}

function dealOperate(type, status) {
//		    status = true: 正在连接状态，执行关闭方法
    if (status) {
        $.ajax({
            type: "post",
            url: "/dit/emulator/close",
            data: {type: type},
            dataType: "json",
            cache: false,
            success: function (data) {
                console.log('关闭连接');
                console.log(data);
                if (data.status === "success") {
                    consoleTip('关闭连接-成功');
                    //    修改后重新调getConfig方法
                    getConfig();
                } else {
                    consoleTip('关闭连接-失败', 'alert-danger')
                }
            }
        })
    } else { // 开启连接
        var data = {
            port: type === '2' ? localJsonPort : localIfsfPort,
            ip: localIp,
            type: type
        };
        $.ajax({
            type: "post",
            url: "/dit/emulator/open",
            data: data,
            dataType: "json",
            cache: false,
            success: function (data) {
                if (data) {
                    consoleTip('连接成功');
                    //    修改后重新调getConfig方法
                    getConfig();
                } else {
                    consoleTip('连接失败', 'alert-danger')
                }
            }
        })
    }
}

function backToIndex() {
    this.location.href = 'index.html'
}

function afterGetAll() {

}

//预设指令改变
var messageType = "全部";

function gradeChange(tx) {
    messageType = tx;
}

var interval = 0;

function appendData(data) {
    let msg = $("#message").text();
    console.log(msg.length);
    if (msg.length > 200000) {
        msg = msg.substring(175000, 200000);
        $("#message").empty();
        $("#message").append(msg);
    }
    $("#message").append(data).append("\n");
}

//循环发送时间改变
/**
 *
 * @param type 类型。1=dit模拟器。2=json接收器。3=ifsf接收器
 * @param id    唯一的id
 * @param port    端口
 */
function buildWebsocket(type, id, port, ip) {
    if ("WebSocket" in window) {
        ws = new WebSocket("ws://" + websocketIp + ":" + websocketPort + "/dit/websocket/" + type + "/" + id);
        var name = id;
        //打开事件
        ws.onopen = function () {
            $("#message").append("与&nbsp;&nbsp;" + type === 2 ? 'JSON接收器' : 'IFSF接收器' + "连接成功!" + "\n");
            $("#redTip").css({"color": 'green'})

        };
        //获得消息事件
        ws.onmessage = function (msg) {
            var jsonString = msg.data;
            console.log("data:" + msg.data);
            //根据下拉框筛选数据
            if (messageType === "全部") {
                appendData(msg.data);
            } else if (messageType === "液位数据") {
                if (jsonString.indexOf("液位数据") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "正常交易数据") {
                if (jsonString.indexOf("正常交易数据") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "脱机交易数据") {
                if (jsonString.indexOf("脱机交易数据") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "加油开始结束数据") {
                if (jsonString.indexOf("加油开始结束数据") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "加油中数据") {
                if (jsonString.indexOf("加油中数据") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "枪罐关系") {
                if (jsonString.indexOf("枪罐关系") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "配送数据") {
                if (jsonString.indexOf("配送数据") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "油罐基本信息") {
                if (jsonString.indexOf("油罐基本信息") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "离线交易") {
                if (jsonString.indexOf("离线交易") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "交易数据") {
                if (jsonString.indexOf("交易数据") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "罐存数据") {
                if (jsonString.indexOf("罐存数据") >= 0) {
                    appendData(msg.data);
                }
            } else if (messageType === "加油机油枪对应关系") {
                if (jsonString.indexOf("加油机油枪对应关系") >= 0) {
                    appendData(msg.data);
                }
            }
        };
        //关闭事件
        ws.onclose = function () {
            $("#message").append("与&nbsp;&nbsp;" + type === 2 ? 'JSON接收器' : 'IFSF接收器' + "断开连接!" + "\n");
            // $("#jsonTip").css({"color": 'red'})
            //     $("#ifsfTip").css({"color": 'red'})
            //     if (type === 2) {
            //     $("#jsonTip").text('')
            //     $("#jsonTip").append("JSON断开连接!");
            // } else if (type === 3) {
            //     $("#ifsfTip").text('')
            //     $("#ifsfTip").append("IFSF断开连接!");
            // }
        };
        //发生了错误事件
        ws.onerror = function () {
            //redtip清空
            $("#message").append("与&nbsp;&nbsp;" + type === 2 ? 'JSON接收器' : 'IFSF接收器' + "连接失败!" + "\n");
            // $("#jsonTip").css({"color": 'red'})
            // $("#ifsfTip").css({"color": 'red'})
            // if (type === 2) {
            //     $("#jsonTip").text('')
            //     $("#jsonTip").append("JSON断开连接!");
            // } else if (type === 3) {
            //     $("#ifsfTip").text('')
            //     $("#ifsfTip").append("IFSF断开连接!");
            // }
        };
    } else {
        // 浏览器不支持 WebSocket
        consoleTip("您的浏览器不支持 WebSocket!", 'alert-danger');
    }
}

$('input:radio[name="radio"]').change(function () {
    var preSet = $('input[name="radio"]:checked').val();
    if (preSet == "枪罐关系") {
        $(':radio[id="messageTypeJsonId"]').prop("checked", true)
        Type = 2
        $("#subject").empty();
        $("#subject").val("3230313031095039315F3130303137000003837B2264617461223A5B7B2231223A2231222C2232223A2238222C2233223A22333030383637222C2234223A22323031392D31322D30342030383A34313A3034222C2235223A22323031392D31322D30342030383A34313A3034227D2C7B2231223A2232222C2232223A2237222C2233223A22333030383635222C2234223A22323031392D31322D30342030383A34313A3034222C2235223A22323031392D31322D30342030383A34313A3034227D2C7B2231223A2232222C2232223A2239222C2233223A22333030383635222C2234223A22323031392D31322D30342030383A34313A3034222C2235223A22323031392D31322D30342030383A34313A3034227D2C7B2231223A2232222C2232223A223130222C2233223A22333030383635222C2234223A22323031392D31322D30342030383A34313A3034222C2235223A22323031392D31322D30342030383A34313A3034227D2C7B2231223A2233222C2232223A2236222C2233223A22333030383633222C2234223A22323031392D31322D30342030383A34313A3034222C2235223A22323031392D31322D30342030383A34313A3034227D2C7B2231223A2234222C2232223A2232222C2233223A22333030383633222C2234223A22323031392D31322D30342030383A34313A3034222C2235223A22323031392D31322D30342030383A34313A3034227D2C7B2231223A2234222C2232223A2233222C2233223A22333030383633222C2234223A22323031392D31322D30342030383A34313A3034222C2235223A22323031392D31322D30342030383A34313A3034227D2C7B2231223A2234222C2232223A2235222C2233223A22333030383633222C2234223A22323031392D31322D30342030383A34313A3034222C2235223A22323031392D31322D30342030383A34313A3034227D2C7B2231223A2235222C2232223A2231222C2233223A22333030383633222C2234223A22323031392D31322D30342030383A34313A3034222C2235223A22323031392D31322D30342030383A34313A3034227D2C7B2231223A2235222C2232223A2234222C2233223A22333030383633222C2234223A22323031392D31322D30342030383A34313A3034222C2235223A22323031392D31322D30342030383A34313A3034227D5D2C226D73674944223A2231222C22736F75726365223A22424A3231222C2274696D65223A22323031392D31322D30342030383A34313A3034227D0A");
    } else if (preSet == "配送数据") {
        $(':radio[id="messageTypeJsonId"]').prop("checked", true)
        Type = 2
        $("#subject").empty();
        $("#subject").val("暂无");
    } else if (preSet == "油罐基本信息") {
        $(':radio[id="messageTypeJsonId"]').prop("checked", true)
        Type = 2
        $("#subject").empty();
        $("#subject").val("3230313031095039315F31303032340000008D7B2264617461223A5B7B2231223A2231222C2232223A22333030383637222C2233223A2232303030302E3030222C2234223A22323031392D31322D30342031323A34313A3135222C2235223A2241227D5D2C226D73674944223A223434222C22736F75726365223A22424A3231222C2274696D65223A22323031392D31322D30342031323A34313A3135227D0A");
    } else if (preSet == "离线交易") {
        $(':radio[id="messageTypeJsonId"]').prop("checked", true)
        Type = 2
        $("#subject").empty();
        $("#subject").val("3230313031095039315F3130303034000000B27B226D73674944223A2239393939222C22736F75726365223A2273222C2274696D65223A22323031392D31302D31302031303A30303A3030222C2264617461223A5B7B2231223A223031222C2232223A2233303030303331222C2233223A223132332E34222C2234223A223132332E34222C2235223A223132332E34222C2236223A22323031392D31302D31302031303A30303A3030222C2237223A2231332E3032222C2238223A2231332E3032227D5D7D");
    } else if (preSet == "交易数据") {
        $(':radio[id="messageTypeJsonId"]').prop("checked", true)
        Type = 2
        $("#subject").empty();
        $("#subject").val("3230313031095039315F3130303032000000D47B2264617461223A5B7B2231223A2237222C2232223A22333030383635222C2233223A22322E3231222C2234223A2231352E3030222C2235223A22362E3738222C2236223A22323031392D31322D30362031303A34333A3036222C2237223A22323031392D31322D30362031303A34333A3335222C2238223A223139393431302E3234222C2239223A223139393431322E3435227D5D2C226D73674944223A2232393935222C22736F75726365223A22424A3231222C2274696D65223A22323031392D31322D30362031303A34333A3335227D0A");
    } else if (preSet == "罐存数据") {
        $(':radio[id="messageTypeJsonId"]').prop("checked", true)
        Type = 2
        $("#subject").empty();
        $("#subject").val("3230313031095039315F3130303036000000CE7B226D73674944223A2239393939222C22736F75726365223A227374222C2274696D65223A22323031392D31302D31302031303A30303A3030222C2264617461223A5B7B2231223A224F4B6574222C2232223A223031222C2233223A2231322E3031222C2234223A2231322E3031222C2235223A2231322E3031222C2236223A2231322E3031222C2237223A2232302E3031222C2238223A22323031392D31302D31302031303A30303A3030222C2239223A2233303030303633222C223130223A2231303030302E3030227D5D7D");
    } else if (preSet == "加油机油枪对应关系") {
        $(':radio[id="messageTypeJsonId"]').prop("checked", true)
        Type = 2
        $("#subject").empty();
        $("#subject").val("3230313031095039315F31303030310000008D7B2264617461223A5B7B2231223A2231222C2232223A2231222C2233223A22323031392D31322D30362031303A33393A3331222C2234223A2231222C2235223A22E58AA0E6B2B9E69CBA5F31227D5D2C226D73674944223A2232393838222C22736F75726365223A22424A3231222C2274696D65223A22323031392D31322D30362031303A33393A3331227D0A");
    } else if (preSet == "液位数据") {
        $(':radio[id="messageTypeIfsfId"]').prop("checked", true)
        Type = 3
        $("#subject").empty();
        $("#subject").val("02010901003F002501214004010039114107090000119678764207090000119678764303022130440400000000");
    } else if (preSet == "正常交易数据") {
        $(':radio[id="messageTypeIfsfId"]').prop("checked", true)
        Type = 3
        $("#subject").empty();
        $("#subject").val("02010107003A0034042121304905050600060199060506000083610704040007200801010A0400000001CC070A000036001143CD070A000036009504");
    } else if (preSet == "脱机交易数据") {
        $(':radio[id="messageTypeIfsfId"]').prop("checked", true)
        Type = 3
        $("#subject").empty();
        $("#subject").val("020101050027002F0422215372C80005050600010000060506000013570704040007370801010A0400000060CA0420130721CB03184145");
    } else if (preSet == "加油开始结束数据") {
        $(':radio[id="messageTypeIfsfId"]').prop("checked", true)
        Type = 3
        $("#subject").empty();
        $("#subject").val("02010107008F000E0121640014010515010116020000");
    } else if (preSet == "加油中数据") {
        $(':radio[id="messageTypeIfsfId"]').prop("checked", true)
        Type = 3
        $("#subject").empty();
        $("#subject").val("0201011000800012012166002205060000291623050600000393");
    }
})


$('input:radio[name="messagetype"]').change(function () {
    var messageType = $('input[name="messagetype"]:checked').val();
    if (messageType == "json类型") {
        Type = 2
    } else if (messageType == "ifsf类型") {
        Type = 3
    }
})

// 弹出框的信息,传的值为弹出框的内容和状态
function consoleTip(text, status) {
    $('#alertTip').addClass(status)
    $('#alertTip').show()
    $('#tipcontainer').text(text)
    setTimeout(function () {
        $('#alertTip').hide()
        $('#alertTip').removeClass(status)
        $('#tipcontainer').text('')
    }, 2000)
}