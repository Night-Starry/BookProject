// 展示获取到的数据
function show_data(data) {
    for (let i = 0; i < data.length; i++) {
        let $book = $("<div class='book'></div>");
        $book.attr("id", data[i].isbn);
        let $img = $("<div class='img'></div>");
        let $img_ = $("<img src='#' alt='书籍图片' class='img' style='height: 100%; width: 100%'>");
        $img_.attr("src", "/img?img_file=" + data[i].img + "&" + Math.random());
        let $info = $("<div class='info'></div>");
        let $isbn = $("<div class='.isbn'></div>");
        let $isbn_label = $("<label style='display: inline'>ISBN: </label>");
        let $isbn_div = $("<div style='display: inline'>" + data[i].isbn + "</div>");
        let $name = $("<div class='name'></div>");
        let $name_label = $("<label style='display: inline'>书名: </label>");
        let $name_div = $("<div style='display: inline'>" + data[i].name + "</div>");
        let $author = $("<div class='author'></div>");
        let $author_label = $("<label style='display: inline'>作者: </label>");
        let $author_div = $("<div style='display: inline'>" + data[i].author + "</div>");
        let $price = $("<div class='price'></div>");
        let $price_label = $("<label style='display: inline'>价格: </label>");
        let $price_div = $("<div style='display: inline'>" + data[i].price + "</div>");

        let $operation = $("<div class='operation'></div>");
        let $span = $("<span style='display: block; height: 100%'></span>");
        let $num = $("<span class='num'> 0&nbsp;</span>");

        let $remove = $("<div class='remove'>移除</div>");
        let $decrease = $("<div class='decrease'>- -</div>")
        let $add = $("<div class='add'>++</div>")

        $book.append($img);
        $img.append($img_);
        $book.append($info);
        $book.append($operation);
        $info.append($name);
        $name.append($name_label);
        $name.append($name_div);
        $info.append($isbn);
        $isbn.append($isbn_label);
        $isbn.append($isbn_div);
        $info.append($author);
        $author.append($author_label);
        $author.append($author_div);
        $info.append($price);
        $price.append($price_label);
        $price.append($price_div);
        $operation.append($span);
        $operation.append($remove);
        $operation.append($decrease);
        $operation.append($num);
        $operation.append($add);
        $(".list").append($book);
    }
}

//发起请求，获取数据
$.getJSON(
    "/cart",
    "action=get_cart&name=admin",
    function (data) {
        show_data(data);
    },
    "json"
);

let order = {};
let total = 0;
let pay_success = false;

//定义用户支付，假定5秒后用户支付成功，页面发生变化，这里根据具体情况修改
function pay() {
    if ($(".code").attr("style") === "") {
        pay_success = true;
        $("#exit").click();

        //界面支付成功
        if (pay_success) {
            var tip = $("#pay_tip");
            //清除订单信息
            $(".num").each(function () {
                $(this).text(0);
            })
            total = 0;
            $("#total").text(0);

            //获取货物信息并转化为json字符串
            let $goods = $(".goods").children("div");
            let num = 1;
            let isbn_all = [];
            let num_all = [];
            let price_all = [];
            $goods.each(function () {
                if (num === 1) {
                    isbn_all.push($(this).find(":nth-child(2)").text());
                    num++;
                } else if (num === 2) {
                    price_all.push($(this).find(":nth-child(2)").text());
                    num++;
                } else {
                    num_all.push($(this).find(":nth-child(2)").text());
                    num = 1;
                }
            });
            let order = {
                "action": "add_order",
                "id": $("#seq").text(),
                "time": $("#time").text(),
                "isbn_all": isbn_all,
                "price_all": price_all,
                "num_all": num_all,
                "total": $("#all").text()
            }

            //将成功的订单信息返回给服务器
            $.post(
                "/order",
                order,
                function (data) {
                    if (data === "success") {
                        tip.text("支付成功");
                    } else {
                        tip.text("支付失败，请重新支付，若确认支付，请联系管理员");
                    }
                    tip.attr("style", "visibility:visible");
                    //3秒后隐藏该提示隐藏
                    setTimeout(function () {
                        tip.attr("style", "visibility:hidden");
                        pay_success = false;
                    }, 3000)
                },
                "text"
            );

        }
    }
}


$(document).ready(function () {
    // $(".book").click(function () {
    //     var isbn = $(this).attr("id");
    //     alert(isbn);
    //     //发起请求，跳转页面
    //
    //     self.location.href = "/bookDetail";
    // });
    // $(".remove").click(function (e) {
    //     e.stopPropagation();
    //     var isbn = $(this).parent().parent().attr("id");
    //     alert("remove" + isbn);
    //     //更新页面
    //
    //     $(this).parent().parent().remove();
    // });
    // $(".add").click(function (e) {
    //     e.stopPropagation();
    //     var isbn = $(this).parent().parent().attr("id");
    //     var price = $(this).parent().parent().find(".info > .price > div").text();
    //     //更新页面
    //     total += Number(price);
    //     total = Number(total.toFixed(2));
    //     if(isbn in order){
    //         order[isbn] += 1;
    //     }else {
    //         order[isbn] = 1;
    //     }
    //     var num = $(this).parent().find("span").last();
    //     num.text(order[isbn]);
    //     $("#total").text(total);
    // });
    // $(".decrease").click(function (e) {
    //     e.stopPropagation();
    //     var isbn = $(this).parent().parent().attr("id");
    //     var price = $(this).parent().parent().find(".info > .price > div").text();
    //     //更新页面
    //
    //     if(isbn in order){
    //         order[isbn] -= 1;
    //         total -= Number(price);
    //         total = Number(total.toFixed(2));
    //         $("#total").text(total);
    //         if(order[isbn] === 0){
    //             delete order[isbn];
    //             $(this).parent().find("span").last().text(0);
    //         }else {
    //             $(this).parent().find("span").last().text(order[isbn]);
    //         }
    //     }
    // });

    $("#cal").click(function () {
        //清空原有内容
        $(".goods").empty();
        $("#all").text(0);
        $("#seq").text("");
        $("#user").text("");
        $("#time").text("");
        //判断是否存在内容
        if (total === 0) {
            return false;
        }
        //生成订单编号
        let time = new Date();
        let order_seq = time.getTime();
        $("#seq").text(order_seq);
        //获取客户名称
        let user = "***";
        $("#user").text(user);
        //获取当前时间
        var cur_time = time.toLocaleDateString();
        $("#time").text(cur_time);
        //插入商品信息
        for (const key in order) {
            var price = $("#" + key).find(".info > .price > div").text();
            $(".goods").append("<div><label>图书isbn: </label><label>" + key + "</label></div>")
                .append("<div><label>单价: </label><label>" + price + "</label></div>")
                .append("<div><label>数量: </label><label>" + order[key] + "</label></div>")
        }
        //价格
        $("#all").text(total);
    });

    //弹出二维码
    $("#confirm").click(function () {
        var seq = $("#seq").text()
        if (seq === null || seq === undefined || seq === "") {
            var tip = $("#pay_tip");
            tip.text("没有订单，不能支付");
            tip.attr("style", "visibility:visible");
            setTimeout(function () {
                tip.attr("style", "visibility:hidden");
            }, 1000);
            return;
        }
        $("#qr_img").empty();
        new QRCode(document.getElementById("qr_img"), {
            text: "应支付￥ " + total,
            width: 300,
            height: 300,
            correctLevel: QRCode.CorrectLevel.M  //纠错等级
        });
        $(".code").attr("style", "");

        //假设用户在5秒后支付成功
        setTimeout(pay, 5000);

    });


    $("#exit").click(function () {
        $(".code").attr("style", "display: none");
    });


    $("#cal, #confirm, #exit").mouseenter(function () {
        $(this).css('cursor', 'pointer');
    });

    //给未来对象定义方法
    // 减少
    $(document).on("click", ".decrease", function (e) {
        e.stopPropagation();
        var isbn = $(this).parent().parent().attr("id");
        var price = $(this).parent().parent().find(".info > .price > div").text();
        //更新页面

        if (isbn in order) {
            order[isbn] -= 1;
            total -= Number(price);
            total = Number(total.toFixed(2));
            $("#total").text(total);
            if (order[isbn] === 0) {
                delete order[isbn];
                $(this).parent().find("span").last().text(0);
            } else {
                $(this).parent().find("span").last().text(order[isbn]);
            }
        }
    });

    //移除
    $(document).on("click", ".remove", function (e) {
        e.stopPropagation();
        var isbn = $(this).parent().parent().attr("id");
        //更新页面
        $(this).parent().parent().remove();

        //发送请求
        $.post(
            "/cart",
            "action=remove_from_cart&user_name=admin&isbn=" + isbn,
        );

    });
    //增加
    $(document).on("click", ".add", function (e) {
        e.stopPropagation();
        let isbn = $(this).parent().parent().attr("id");
        let price = $(this).parent().parent().find(".info > .price > div").text();
        //更新页面
        total += Number(price);
        total = Number(total.toFixed(2));
        if (isbn in order) {
            order[isbn] += 1;
        } else {
            order[isbn] = 1;
        }
        let num = $(this).parent().find("span").last();
        num.text(order[isbn]);
        $("#total").text(total);
    });

    $(document).on("click", ".book", function () {
        let isbn = $(this).attr("id");

        //发起请求，跳转页面
        $.post(
            "/book",
            "action=set_isbn&isbn=" + isbn,
        );

        self.location.href = "/bookDetail";
    });

    $(document).on("mouseenter", ".decrease, .remove, .add", function (e) {
        $(this).css('cursor', 'pointer');
    });
});
