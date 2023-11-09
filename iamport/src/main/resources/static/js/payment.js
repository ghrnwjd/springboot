$(document).ready(function(){
    $("#payment").click(function(){
        startPayment();
    })
})

function startPayment(data) {
    IMP.init("imp61287003")
    IMP.request_pay({
        pg: "kakaopay.TC0ONETIME",
        pay_method: "card",
        merchant_uid: "3",
        name: "신시책",
        amount: 4000,
        buyer_name: "아무개",
    }), function (res) {
        if(res.success) {
            alert("완료")
        }
         else
         {
            alert("실패")
         }
    }

}